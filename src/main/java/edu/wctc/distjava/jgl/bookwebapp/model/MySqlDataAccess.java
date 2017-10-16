package edu.wctc.distjava.jgl.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

public class MySqlDataAccess implements DataAccess {

    private final int ALL_RECORDS = 0;
    private final boolean DEBUG = true;

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public void openConnection(String driverClass,
            String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     *
     * @param tableName
     * @param colNames
     * @param colValues
     * @return
     * @throws SQLException
     */
    @Override
    public int addRecord(String tableName, List<String> colNames,
            List<Object> colValues) throws SQLException {

        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(", ", "(", ")");

        for (String col : colNames) {
            sj.add(col);
        }

        if (DEBUG) {
            System.out.println(sj.toString());
        }

        sql += sj.toString();
        sql += " VALUE ";

        sj = new StringJoiner(", ", "(", ")");
        for (Object value : colValues) {
            sj.add("?");
        }
        sql += sj.toString();

        if (DEBUG) {
            System.out.println(sql);
        }
        pstmt = conn.prepareStatement(sql);

        for (int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i - 1));
        }
        return pstmt.executeUpdate();
    }

    /**
     *
     * @param tableName
     * @param colNames
     * @param colValues
     * @param pkColName
     * @param pkValue
     * @return
     * @throws SQLException
     */
    @Override
    public int updateRecord(String tableName, List<String> colNames,
            List<Object> colValues, String pkColName
            , Object pkValue) throws SQLException {

        String sql = "UPDATE " + tableName + " SET ";
        
        StringJoiner sj = new StringJoiner(" = ?, ", "", " = ?");

        for (String col : colNames) {
            sj.add(col);
        }

        sql += sj + " WHERE " + pkColName + " = ?;"; 

        if (DEBUG) {
            System.out.println(sql);
        }

        pstmt = conn.prepareStatement(sql);

        for (int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i - 1));
        }
        pstmt.setObject(colValues.size() + 1, pkValue);
        return pstmt.executeUpdate();
    }

    @Override
    public int deleteRecordById(String tableName, String pkColName,
            Object pkValue) throws SQLException {

        String sql = "DELETE FROM " + tableName + " WHERE "
                + pkColName + " = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, pkValue);
        int recsDeleted = pstmt.executeUpdate();

        return recsDeleted;
    }
    
    @Override
    public Map<String, Object> findRecordById(String tableName, String pkColName, Object pkValue)
            throws SQLException {
        String sql = null;

        if (pkValue != null) {
            sql = "select * from " + tableName + " where " + pkColName + " = ?";
        } 

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, pkValue);
        rs = pstmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println("Meta Data " + rsmd);
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
        }
        return record;
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
            throws SQLException {

        List<Map<String, Object>> rawData = new Vector<>();
        String sql;

        if (maxRecords > ALL_RECORDS) {
            sql = "select * from " + tableName + " limit " + maxRecords;
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
        return rawData;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DataAccess db = new MySqlDataAccess();
        db.openConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin");

//        db.addRecord("author", Arrays.asList("author_name", "date_added"), Arrays.asList("Gathi", "2017-03-05"));
//
//        int recsDeleted = db.deleteRecordById("author", "author_id", 1);
//        System.out.println("No. of Recs Deleted: " + recsDeleted);
//       
//        List<Map<String, Object>> list = db.getAllRecords("author", 0);
//        
//        for (Map<String, Object> rec : list) {
//            System.out.println(rec);
//        }
//
//        db.updateRecord("author", Arrays.asList("author_name", "date_added"),
//                Arrays.asList("Kall Lewis", "2017-08-05"), "author_id", 3);
//        
//        List<Map<String, Object>> list1 = db.getAllRecords("author", 0);
//        
//        for (Map<String, Object> rec : list1) {
//            System.out.println(rec);
//        }
        
        Map<String, Object> rec = db.findRecordById("author", "author_id", 15);
        //if (!record.isEmpty()) System.out.println(" Record found " + record.get(0).get("author_name"));

            System.out.println(" Record found : " + rec.get("author_id") + " "
                    + rec.get("author_name") + " " + rec.get("date_added"));
   
        db.closeConnection();
    }
}
