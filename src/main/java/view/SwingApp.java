package view;

import org.example.joseFrontend.model.User;
import org.example.joseFrontend.repository.Repository;
import org.example.joseFrontend.repository.UserRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SwingApp extends JFrame {
  private final Repository<User>  userRepository;

  private final JTable userTable;

  public SwingApp(){
    // Configurar la ventana
    setTitle("Gestion De Usuarios");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 230);

    //Crear una tabla para mostrar los usuarios
    userTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(userTable);
    add(scrollPane, BorderLayout.CENTER);

    //Crear botones para las acciones
    JButton agregarButton = new JButton("Agregar");
    JButton actualizarButton = new JButton("Actualizar");
    JButton eliminarButton = new JButton("Eliminar");

    //Configurar el panel de botones
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(agregarButton);
    buttonPanel.add(actualizarButton);
    buttonPanel.add(eliminarButton);
    add(buttonPanel, BorderLayout.SOUTH);

    //Establecer estilos para los botones
    agregarButton.setBackground(new Color(46, 204, 113));
    agregarButton.setForeground(Color.WHITE);
    agregarButton.setFocusPainted(false);

    actualizarButton.setBackground(new Color(52, 152, 219));
    actualizarButton.setForeground(Color.WHITE);
    actualizarButton.setFocusPainted(false);

    eliminarButton.setBackground(new Color(231, 76, 60));
    eliminarButton.setForeground(Color.WHITE);
    eliminarButton.setFocusPainted(false);

    //Crear el objeto repository para acceder a las BD
    userRepository = new UserRepository();

    //Cargar los users iniciales a la tabla
    refreshUserTable();

    //Agregar ActionListener para los botones
    agregarButton.addActionListener(e -> {
      try{
        agregarUser();
      }catch (SQLException exception){
        throw new RuntimeException(exception);
      }
    });

    actualizarButton.addActionListener(e -> actualizarUser());
    eliminarButton.addActionListener(e -> eliminarUser());
  }

  private void refreshUserTable(){
    //Obtener la lista actualizada de users desde la BD
    try{
      List<User> users = userRepository.findAll();

      //Crear un modelo de tabla y establecer los datos de los usuarios
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("user_id");
      model.addColumn("login");
      model.addColumn("password");
      model.addColumn("nickaname");
      model.addColumn("email");
      for(User user: users){
        Object[] rowData = {
            user.getUser_id(),
            user.getLogin(),
            user.getPassword(),
            user.getNickname(),
            user.getEmail()
        };
        model.addRow(rowData);
      }
//      users.forEach(System.out::println);
      userTable.setModel(model);
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error al obtener los usuarios");
    }
  }

  private void agregarUser() throws SQLException {
    //Crear formulario para agregar un usuario
//    JTextField userIDField = new JTextField();
    JTextField loginField = new JTextField();
    JTextField passwordField = new JTextField();
    JTextField nickanameField = new JTextField();
    JTextField emailField = new JTextField();

    Object[] fields = {
//        "UserID:", userIDField,
        "Login:", loginField,
        "Password:", passwordField,
        "Nickaname:", nickanameField,
        "Email:", emailField
    };

    int result = JOptionPane.showConfirmDialog(this, fields, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);
    if(result == JOptionPane.OK_OPTION){
      //Crear un nuevo objeto User con los datos ingresados
      User user = new User();
      user.setLogin(loginField.getText());
      user.setPassword(passwordField.getText());
      user.setNickname(nickanameField.getText());
      user.setEmail(emailField.getText());

      System.out.println("New User = "+user.toString());

      //Guardar el nuevo user en la BD
      userRepository.save(user);
      //Actualizar la tabla con los users actualizados
      refreshUserTable();

      JOptionPane.showMessageDialog(this, "Empleado agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private void actualizarUser(){
    //Obtener el ID del user a actualizar
    String userIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del usuario a actualizar", JOptionPane.QUESTION_MESSAGE);
    if (userIdStr != null) {
      try {
        int userId = Integer.parseInt(userIdStr);

        // Obtener el user desde la base de datos
        User user = userRepository.getByUid(userId);

        if (user != null) {
          // Crear un formulario con los datos del user
//          JTextField userIdField = new JTextField(user.getUser_id());
          JTextField loginField = new JTextField(user.getLogin());
          JTextField passwordField = new JTextField(user.getLogin());
          JTextField nickanameField = new JTextField(user.getNickname());
          JTextField emailField = new JTextField(user.getEmail());

          Object[] fields = {
//              "UserID:", userIdField,
              "Login:", loginField,
              "Password:", passwordField,
              "Nickaname:", nickanameField,
              "Email:", emailField
          };

          int confirmResult = JOptionPane.showConfirmDialog(this, fields, "Actualizar Empleado", JOptionPane.OK_CANCEL_OPTION);
          if (confirmResult == JOptionPane.OK_OPTION) {
            // Actualizar los datos del user con los valores ingresados en el formulario
//            user.setUser_id(userIdField.getText());
            user.setLogin(loginField.getText());
            user.setPassword(passwordField.getText());
            user.setNickname(nickanameField.getText());
            user.setEmail(emailField.getText());

            // Guardar los cambios en la base de datos
            userRepository.save(user);

            // Actualizar la tabla de users en la interfaz
            refreshUserTable();
          }
        } else {
          JOptionPane.showMessageDialog(this, "No se encontró ningún user con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID", "Error", JOptionPane.ERROR_MESSAGE);
      } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al obtener los datos del user de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }

  }
  private void eliminarUser(){
    // Obtener el ID del usuario a eliminar
    String userIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del usuario a eliminar:", "Eliminar Usuario", JOptionPane.QUESTION_MESSAGE);
    if (userIdStr != null) {
      try {
        int userId = Integer.parseInt(userIdStr);

        // Confirmar la eliminación del user
        int confirmResult = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el Usuario?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmResult == JOptionPane.YES_OPTION) {
          // Eliminar el user de la base de datos
          userRepository.delete(userId);

          // Actualizar la tabla de users en la interfaz
          refreshUserTable();
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID del Usuario", "Error", JOptionPane.ERROR_MESSAGE);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
