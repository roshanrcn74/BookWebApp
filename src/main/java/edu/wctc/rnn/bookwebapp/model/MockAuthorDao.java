/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rnn.bookwebapp.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Roshan
 */
public class MockAuthorDao implements IAuthorDao {

    public MockAuthorDao() {

    }
    
    
    @Override
    public List<Author> getListofAuthors() 
            throws SQLException, ClassNotFoundException{
        
        List<Author> list = Arrays.asList(
        new Author(1, "John Doe", new Date()),
        new Author(2, "Bob Smith", new Date())         
        );
        
        
        return list;
    }


    
    //psvm
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        IAuthorDao dao = new MockAuthorDao();

//        DataAccess db = new MySqlDataAccess(
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book1",
//                "root",
//                "admin");
//
//        AuthorDao dao = new AuthorDao(
//                db
//        );
        
        
        
//        System.out.println(dao.getListofAuthors());
        
        List<Author> list = dao.getListofAuthors();
        
        for (Author a: list){
            System.out.println(a.getAuthorId() + " " + a.getAuthorName() + " " + a.getDateAdded());
        }
    }
            
}
