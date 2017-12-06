/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import edu.wctc.distjava.jgl.bookwebapp.repository.AuthorRepository;
import edu.wctc.distjava.jgl.bookwebapp.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roshan
 */
@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookRepository bookRepo;

    private final List bookIdList;

    public BookService() {
        this.bookIdList = new ArrayList();
    }

    public void addNewBook(String title, String isbn, String authorId) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = authorRepo.findOne(new Integer(authorId));
        book.setAuthor(author);
        bookRepo.save(book);

    }

    public void addOrUpdateBook(String bookId, String title, String isbn, String authorId) {
        
        cheanBookIdList();
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

    public void deleteById(String bookId) {
        cheanBookIdList();
        Book book = bookRepo.findOne(Integer.parseInt(bookId));
        bookRepo.delete(book);
    }

    public Book findBook(String bookId) {

        return bookRepo.findOne(Integer.parseInt(bookId));
    }
    public List<Book> findAll() {
        return bookRepo.findAll();
    }
    
    public List<String> selectBookIdList(String id) {
        if (bookIdList.size() > 0) {
            boolean notFound = true;
            for (int i = 0; i < bookIdList.size(); i++) {
                if (id.equals(bookIdList.get(i).toString())) {
                    bookIdList.remove(i);
                    notFound = false;
                }
            }
            if (notFound) {
                bookIdList.add(id);
            }
        } else {
            bookIdList.add(id);
        }
        return bookIdList;
    }
    public void deleteBooks() {
        int size = bookIdList.size();
        if (bookIdList.size() > 0) {
            for (int i = 0; i < size; i++) {
                Book book = bookRepo.findOne(Integer.parseInt(bookIdList.get(i).toString()));
                bookRepo.delete(book);
            }
        }
        cheanBookIdList();
    }

    public void cheanBookIdList() {
        int j = 0;
        while (!bookIdList.isEmpty()) {
            bookIdList.remove(0);
        }
    }
}
