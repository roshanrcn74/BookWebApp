package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Implementation class for the database operations for a MySQL database. 
 * Contains CRUD operations for any database table with any number of columns
 * 
 * @author Roshan
 */
public class MsSqlServerDataAccess implements DataAccess{

    private final int ALL_RECORDS = 0;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    /**
     * Default constructor
     */
    public MsSqlServerDataAccess() {

    }

    /**
     * Opens the connection for a MySQL server database with the given parameters
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public void openConnection(String driverClass,
            String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    /**
     * Closes the connection for a given MySQL database 
     * @throws SQLException 
     */
    @Override
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * Deletes any record from any table of the database if the table name and 
     * the Id is given.
     * @param tableName
     * @param pkColName
     * @param pkValue
     * @return
     * @throws SQLException 
     */
    @Override
    public int deleteRecordById(String tableName, String pkColName,
            Object pkValue) throws SQLException {

        String sql = "DELETE FROM " + tableName + " WHERE "
                + pkColName + " = ";

        if (pkValue instanceof String) {
            sql += "'" + pkValue.toString() + "'";
        } else {
            sql += Long.parseLong(pkValue.toString());
        }

        stmt = conn.createStatement();
        int recsDeleted = stmt.executeUpdate(sql);
        closeConnection();

        return recsDeleted;
    }

    /**
     * Returns records from a table. Requires and open connection.
     *
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, Object>> getAllRecords(String tableName, int maxRecords)
            throws SQLException, ClassNotFoundException {

        List<Map<String, Object>> rawData = new Vector<>();
        String sql = "";

        if (maxRecords > ALL_RECORDS) {
            sql = "select TOP " + maxRecords + " from " + tableName;
        } else {
            sql = "select * from " + tableName;
        }

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            rawData.add(record);
        }

        closeConnection();

        return rawData;
    }

    /**
     * Adds a record to any table with the given details
     * @param tableName
     * @param colNames
     * @param colValue
     * @return
     * @throws SQLException 
     */
    @Override
    public int addRecord(String tableName, List<String> colNames, List<Object> colValue) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Updates the record with the given details
     * @param tableName
     * @param colNames
     * @param colValue
     * @param pkName
     * @param pkValue
     * @return
     * @throws SQLException 
     */
    @Override
    public int updateRecord(String tableName, List<String> colNames, List<Object> colValue, String pkName, Object pkValue) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Main method for testing
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        MsSqlServerDataAccess db = new MsSqlServerDataAccess();
        db.openConnection("org.apache.derby.jdbc.ClientDriver",
                          "jdbc:derby://localhost:1527/sample",
                          "app", "app");

        List<Map<String, Object>> list = db.getAllRecords("CUSTOMER", 3);

        for (Map<String, Object> rec : list) {
            System.out.println(rec);
        }

    }

    /**
     * Finds any record from any table for a given id and the table name
     * @param tableName
     * @param pkColName
     * @param pkValue
     * @return
     * @throws SQLException 
     */
    @Override
    public Map<String, Object> findRecordById(String tableName, String pkColName, Object pkValue) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
