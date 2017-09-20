/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.rnn.bookwebapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roshan
 */
public class AuthorService {
    
    private List<Author> authorList;
    
    public List<Author> getAuthorList(){
        authorList.add(new Author("Roshan"));
        
        return authorList;
        
    }
    
    
    
    
}
