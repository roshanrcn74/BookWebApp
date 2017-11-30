/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import edu.wctc.distjava.jgl.bookwebapp.repository.AuthorRepository;
import edu.wctc.distjava.jgl.bookwebapp.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roshan
 */
@Service
public class BookService{
    
    @Autowired
    private AuthorRepository authorRepo;
    
    @Autowired
    private BookRepository bookRepo;



    public BookService() {

    }

//    public void addNewBook(String title, String isbn, String authorId){
//        Book book = new Book();
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        Author author = getEm().find(Author.class, new Integer(authorId));
//        book.setAuthor(author);
//        getEm().merge(book);
//        
//    }
  
    
    public void addOrUpdateBook(String bookId, String title, String isbn, String authorId) {

        Book book;
        if (bookId == null || bookId.isEmpty()) {
            //must be new record
            book = new Book();
            
        } else {
            //must be updated recoed
            book = new Book(new Integer(bookId));
        }

        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = authorRepo.findOne(Integer.parseInt(authorId));
        book.setAuthor(author);

        bookRepo.save(book);
    }
    
    public void deleteById(String bookId){
//        Book book = getEm().find(Book.class, bookId);
//        getEm().remove(book);
          Book book = bookRepo.findOne(Integer.parseInt(bookId));
          bookRepo.delete(book);
    }
    
    public Book findBook(String bookId){

        return bookRepo.findOne(Integer.parseInt(bookId));
    }
    
    public List<Book> findAll(){
        return bookRepo.findAll();
    }
}
