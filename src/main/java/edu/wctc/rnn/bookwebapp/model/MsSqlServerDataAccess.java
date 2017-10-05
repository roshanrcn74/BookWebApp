/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rnn.bookwebapp.model;

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
 *
 * @author Roshan
 */
public class MsSqlServerDataAccess implements DataAccess {

    private Connection conn;
    private Statement stmt;
    private final int ALL_RECORDS = 0;
    private ResultSet rs;

    private String url;
    private String driverClass;
    private String userName;
    private String passWord;
    private DataAccess db;

    public MsSqlServerDataAccess(String driverClass, String url, String userName, String passWord) {
        setUrl(url);
        setDriverClass(driverClass);
        setUserName(userName);
        setPassWord(passWord);
        setDb(db);

    }

    @Override
    public void openConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, passWord);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * Returns
     *
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    @Override
    public List<Map<String, Object>> getAllRecords(String tableName, int maxRecords)
            throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> rawData = new Vector<>(); //can use ArrayList due to multithreddig issue use Vector
        String sql = "";

        if (maxRecords > ALL_RECORDS) {
            sql = "select Top " + maxRecords + " * from  " + tableName;
        } else {
            sql = "select * from " + tableName;
        }
        openConnection();
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
    
    @Override
    public int creatRecord(String tableName, List<String> colNames, List<Object> colValues) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
     public int deleteRecordbyId(String tableName, String colName, Object pkValue) 
            throws ClassNotFoundException, SQLException {
        int recordDeleted = 0;
        if (tableName != null && !"".equals(tableName) && colName != null 
                && !"".equals(colName) && pkValue != null && pkValue!= ""){
            String sql = "delete from " + tableName + " where " + colName + " = ";
            if (pkValue instanceof String){
                sql += "'" + pkValue.toString() + "'";
            } else {
                sql += Long.parseLong(pkValue.toString());
            }
            
            openConnection();
            stmt = conn.createStatement();
            recordDeleted = stmt.executeUpdate(sql);
            closeConnection();
        }
        
        return recordDeleted;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataAccess db = new MsSqlServerDataAccess(

//                "org.apache.derby.jdbc.ClientDriver",
//                "jdbc:derby://localhost:1527/sample",
//                "app",
//                "app"
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root",
                "admin"
        );
        List<Map<String, Object>> list = db.getAllRecords("author", 0);
            System.out.println(list.get(1).keySet());
        list.forEach((Map<String, Object> rec) -> {
            System.out.println(rec.values());
        });    
    }
    
}
