package org.example.joseFrontend.repository;
import java.sql.SQLException;
import java.util.List;
public interface Repository<T> {
  List<T> findAll() throws SQLException;
  T getByUid(Integer id) throws SQLException;
  void save(T t) throws SQLException;
  void delete(Integer id) throws SQLException;
}
