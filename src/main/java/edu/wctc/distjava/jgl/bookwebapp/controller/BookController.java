/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.controller;

import edu.wctc.distjava.jgl.bookwebapp.model.Book;
import edu.wctc.distjava.jgl.bookwebapp.model.BookService;
import edu.wctc.distjava.jgl.bookwebapp.model.AuthorService;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Roshan
 */
@WebServlet(name = "BookController", urlPatterns = {"/bc"})
public class BookController extends HttpServlet {

    @EJB
    private AuthorService authorService;
    
    @EJB
    private BookService bookService;

    public static final String ACTION = "action";
    public static final String LIST_ACTION = "displayList";
    public static final String DELETE_ACTION = "Delete";
    public static final String EDIT_ACTION = "Edit";
    public static final String ADD_ACTION = "Add";
    public static final String SAVECANCEL_ACTION = "SaveCancel";
    public static final String BUTTON_ACTION = "button_action";
    public static final String BOOK_ID = "bId";
    public static final String BOOK_TITLE = "bTitle";
    public static final String BOOK_ISBN = "bIsbn";
    public static final String BOOK_AUTHOR = "bAuthorId";
    
    public static final String BOOK_LIST_PAGE = "/bookList.jsp";
    public static final String BOOK_ADD_EDIT_PAGE = "/bookAddEdit.jsp";

    private static final long serialVersionUID = 1L;



    @Override
    public void init() throws ServletException {

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = BOOK_LIST_PAGE; // default
        String action = request.getParameter(ACTION);
        String bookId = request.getParameter(BOOK_ID);
        String bookTitle = request.getParameter(BOOK_TITLE);
        String bookIsbn = request.getParameter(BOOK_ISBN);
        String bookAuthorId = request.getParameter(BOOK_AUTHOR);
        
        String butt_action = request.getParameter(BUTTON_ACTION);
        
        try {

            // Book book;
            if (action.equalsIgnoreCase(LIST_ACTION)) {
                refreshBookList(request);
            } else if (action.equalsIgnoreCase(DELETE_ACTION)) {
                bookService.deleteById(bookId);
                refreshBookList(request);
            } else if (action.equalsIgnoreCase(ADD_ACTION)){
                request.setAttribute("authorList", authorService.getList());
                request.setAttribute("authorId", bookAuthorId);
                destination = BOOK_ADD_EDIT_PAGE;
                
            } else if (action.equalsIgnoreCase(EDIT_ACTION)){
                request.setAttribute("authorList", authorService.getList());
                request.setAttribute("book", bookService.findBook(bookId));
                destination = BOOK_ADD_EDIT_PAGE;
                
                
            }else if (action.equalsIgnoreCase(SAVECANCEL_ACTION)) {
                if (butt_action.equalsIgnoreCase("Save")) {
                    if (bookId == null || bookId.isEmpty()){
                        
                    }
                    bookService.addOrUpdateBook(bookId, bookTitle, bookIsbn, bookAuthorId);
                }
                refreshBookList(request);
            }
            
        } catch (Exception ex) {
            destination = "/error.jsp";
            request.setAttribute("errMessage", ex.getMessage());

        }

        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }

    private void refreshBookList(HttpServletRequest request) {
        List<Book> bookList;
        bookList = bookService.getList();
        request.setAttribute("bookList", bookList);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
