/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import edu.wctc.distjava.jgl.bookwebapp.repository.AuthorRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roshan
 */
@Service
public class AuthorService  {

    @Autowired
    private AuthorRepository authorRepo;


    public AuthorService() {

    }
    
    public List<Author> findAll(){
        return authorRepo.findAll();
    }
    
    public Author findById(String id){
        return authorRepo.findOne(Integer.parseInt(id));
    }
    
    public void addAuthor(String authorName){
        Date dateAdded = new Date();
        Author author = new Author();
        author.setAuthorName(authorName);
        author.setDateAdded(dateAdded);
        
        authorRepo.save(author);
        //authorRepo.saveAndFlush(author);
        
    }

    public void addAuthor(List<Object> colValues) throws ParseException{
        Author author = new Author();
        author.setAuthorName(colValues.get(0).toString());
        author.setDateAdded(getDate(colValues.get(1).toString()));
        authorRepo.save(author);
    }
//    
    public void updateAuthorById(List<Object> colValues, String id) throws ParseException{
        Author author = (Author) findById(id);
        author.setAuthorName(colValues.get(0).toString());
        author.setDateAdded(getDate(colValues.get(1).toString()));
        authorRepo.save(author); 
    }
    
    public void removeAuthorById(String id) throws Exception {
        authorRepo.delete(findById(id));
    }    


        public final Date getDate(String sDate) throws ParseException{
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        return date;
    }
    
    public final String currentDate(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }
}
