package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jlombardo
 */
public interface IAuthorDao {

   public abstract int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException;
   public abstract List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;
   public abstract int addAuthor(String tableName, List<String> colName, List<Object> colValue) 
           throws SQLException, ClassNotFoundException;
   public int updateAuthor(String tableName, List<String> colName, List<Object> colValue, String pkName, Object pkValue)
           throws SQLException, ClassNotFoundException;
}
