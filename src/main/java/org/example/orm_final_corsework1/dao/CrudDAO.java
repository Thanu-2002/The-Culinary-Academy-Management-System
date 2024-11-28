package org.example.orm_final_corsework1.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO{

    public  boolean add(T entity) throws SQLException;

    public  boolean update(T entity) throws SQLException;

    public  boolean delete(T entity) throws SQLException;

    public List<T> getAll() throws SQLException;

    public List<String> getIds() throws SQLException;

}
