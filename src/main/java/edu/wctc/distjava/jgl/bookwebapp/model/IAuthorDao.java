package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for the operations for the Author object
 * @author jlombardo
 */
public interface IAuthorDao {

/**
 * Removes the author record for the given author id 
 * @param id
 * @return
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
   public abstract int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException;
   
   /**
    * Gets the List of all authors from the Authors table
    * @return
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   public abstract List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;
   
   /**
    * Adds the author record with the given parameters
    * @param colValue
    * @return
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   public abstract int addAuthor(List<Object> colValue) 
           throws SQLException, ClassNotFoundException;
   
   /**
    * Returns an Author object from the details found in the database for the given author id
    * @param id
    * @return
    * @throws ClassNotFoundException
    * @throws SQLException 
    */
   public abstract Author findAuthorById(Integer id) throws ClassNotFoundException, SQLException;
   
   /**
    * Updates the author record with the given details in the Author object in the parameter. 
    * Returns 1 if a record is updated. Returns 0 if no reocrd is updated.
    * @param colValue
    * @param pkValue
    * @return
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   public int updateAuthor(List<Object> colValue, Object pkValue)
           throws SQLException, ClassNotFoundException;
}
