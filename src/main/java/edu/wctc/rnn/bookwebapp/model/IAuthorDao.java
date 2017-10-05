/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rnn.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Roshan
 */
public interface IAuthorDao {

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public abstract List<Author> getListofAuthors() throws SQLException, ClassNotFoundException;
    public abstract int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException;
    
}
