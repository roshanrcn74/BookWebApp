package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Interface that holds the operations for any given database table object
 * @author jlombardo
 */
public interface DataAccess {

    /**
     * Closes the database connection
     * @throws SQLException 
     */
    void closeConnection() throws SQLException;

    /**
     * Returns records from a table. Requires an open connection.
     * @param tableName
     * @param pkColName
     * @param pkValue
     * @return
     * @throws SQLException
     */
    public abstract int deleteRecordById(String tableName, String pkColName, 
            Object pkValue) throws SQLException;
    /**
     * Method signature to add the Table record to the database with the given parameters
     * @param tableName
     * @param colNames
     * @param colValue
     * @return
     * @throws SQLException 
     */
    public abstract int addRecord(String tableName, List<String> colNames, List<Object> colValue) throws SQLException;
    
    /**
     * Finds the record for a given id 
     * @param tableName
     * @param pkColName
     * @param pkValue
     * @return
     * @throws SQLException 
     */
    public Map<String, Object> findRecordById(String tableName, String pkColName, Object pkValue)
            throws SQLException;
    
    /**
     * Gets all records from any given table. The table name and the number of records need to be provided 
     * as parameters
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public abstract List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) throws SQLException, ClassNotFoundException;
    
    /**
     * Updates a given record in any table with the given parameters
     * @param tableName
     * @param colNames
     * @param colValue
     * @param pkName
     * @param pkValue
     * @return
     * @throws SQLException 
     */
    public abstract int updateRecord(String tableName, List<String> colNames, List<Object> colValue, String pkName, Object pkValue) throws SQLException;

    /**
     * Opens the connection to a database for the given database driver, URL, username and the 
     * password
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public abstract void openConnection(String driverClass, 
            String url, String userName, String password) 
            throws ClassNotFoundException, SQLException;
    
}
