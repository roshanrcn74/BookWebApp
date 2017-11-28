/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Roshan
 */
@Stateless
public class BookService extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public BookService() {
        super(Book.class);
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
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);

        getEm().merge(book);
    }
    
    public void deleteById(String bookId){
//        Book book = getEm().find(Book.class, bookId);
//        getEm().remove(book);
          Book book = getEm().find(Book.class, new Integer(bookId));
          remove(book);
    }
    
    public Book findBook(String bookId){
        int id = new Integer(bookId);
        return getEm().find(Book.class, id);
    }
}
