/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rnn.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Roshan
 */
public interface DataAccess {

    void closeConnection() throws SQLException;

    /**
     * Returns
     *
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) 
            throws SQLException, ClassNotFoundException;

    void openConnection() throws ClassNotFoundException, SQLException;
    
    int deleteRecordbyId(String tableName, String colName, Object priKey) 
            throws ClassNotFoundException, SQLException;
    
    public int creatRecord(String tableName, List<String> colNames, List<Object> colValues);
}
