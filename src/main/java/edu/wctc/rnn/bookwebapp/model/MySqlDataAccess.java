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
public class MySqlDataAccess implements DataAccess {

    private Connection conn;
    private Statement stmt;
    private final int ALL_RECORDS = 0;
    private ResultSet rs;

    private String url;
    private String driverClass;
    private String userName;
    private String passWord;

    public MySqlDataAccess(String driverClass, String url, String userName, String passWord) {
        setUrl(url);
        setDriverClass(driverClass);
        setUserName(userName);
        setPassWord(passWord);
        //setDb(db);

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
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
            sql = "select * from " + tableName + "limit " + maxRecords;
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

    /**
     *
     * @param tableName
     * @param colName
     * @param priKey
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public int deleteRecords(String tableName, String colName, Object priKey) 
            throws ClassNotFoundException, SQLException {
        int updateRecords = 0;
        if (tableName != null && tableName != "" && colName != null 
                && colName!= "" && priKey != null && priKey!= ""){
            String sql = "delete from " + tableName + " where " + colName + " = " + priKey+";";
            openConnection();
            stmt = conn.createStatement();
            updateRecords = stmt.executeUpdate(sql);
            closeConnection();
        }
        
        return updateRecords;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySqlDataAccess db = new MySqlDataAccess(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root",
                "admin"
        );

        List<Map<String, Object>> list = db.getAllRecords("author", 0);
        list.forEach((Map<String, Object> rec) -> {
            System.out.println(rec);
        });

        System.out.println("No of record(s) deleted : " + db.deleteRecords("author", "author_id", 6));
        
        List<Map<String, Object>> list1 = db.getAllRecords("author", 0);
        list1.forEach((Map<String, Object> rec) -> {
            System.out.println(rec);
        });
    }
}
