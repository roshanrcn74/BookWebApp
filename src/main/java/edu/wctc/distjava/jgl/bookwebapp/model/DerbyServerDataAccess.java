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

public class DerbyServerDataAccess implements DataAccess{

    private final int ALL_RECORDS = 0;
    private final boolean DEBUG = true;

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public DerbyServerDataAccess() {

    }

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

    @Override
    public int deleteRecordById(String tableName, String pkColName,
            Object pkValue) throws SQLException {
        //DELETE  FROM APP.CUSTOMER WHERE CUSTOMER_ID = 25;

        String sql = "DELETE FROM " + tableName + " WHERE "
                + pkColName + " = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, pkValue);
        int recsDeleted = pstmt.executeUpdate();

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
//            SELECT * FROM APP.CUSTOMER FETCH FIRST 5 ROWS ONLY;

            sql = "select * from " + tableName + " FETCH FIRST " + maxRecords + " ROWS ONLY";
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

    @Override
    public int addRecord(String tableName, List<String> colNames, List<Object> colValues) throws SQLException {

        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(", ", "(", ")");

        for (String col : colNames) {
            sj.add(col);
        }

        if (DEBUG) {
            System.out.println(sj.toString());
        }

        sql += sj.toString();
        sql += " VALUES ";

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

        return pstmt.executeUpdate();    }

    @Override
    public int updateRecord(String tableName, List<String> colNames, 
            List<Object> colValues, String pkName, Object pkValue) throws SQLException {
            String sql = "UPDATE " + tableName + " SET ";

        for (int i = 0; i < colNames.size(); i++) {
            if (i < colNames.size() - 1) {
                sql += colNames.get(i) + " = " + "?, ";
            } else {
                sql += colNames.get(i) + " = " + "?";
            }
        }
        sql += " WHERE " + pkName + " = " + pkValue + ";";

        if (DEBUG) {
            System.out.println(sql);
        }

        pstmt = conn.prepareStatement(sql);

        for (int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i - 1));
        }
        return pstmt.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DerbyServerDataAccess db = new DerbyServerDataAccess();
        db.openConnection("org.apache.derby.jdbc.ClientDriver",
                          "jdbc:derby://localhost:1527/sample",
                          "app", "app");
        
        //db.addRecord("CUSTOMER", Arrays.asList("CUSTOMER_ID", "DISCOUNT_CODE", "ZIP", "NAME"), Arrays.asList(20, "M", 95117, "Roshan"));
        //db.deleteRecordById("CUSTOMER", "CUSTOMER_ID", 3);
        List<Map<String, Object>> list = db.getAllRecords("CUSTOMER", 4);

        for (Map<String, Object> rec : list) {
            System.out.println(rec);
        }
        db.closeConnection();

    }
}
