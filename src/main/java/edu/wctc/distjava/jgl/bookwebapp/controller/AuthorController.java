/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.controller;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import edu.wctc.distjava.jgl.bookwebapp.model.AuthorService;
import edu.wctc.distjava.jgl.bookwebapp.model.BookService;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * This class acts as the Controller servlet for the Author operations. This
 * controller handles all the operations that come from the UI and diverts the
 * request to the appropriate method of the service class.
 *
 * @author jlombardo
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/ac"})
public class AuthorController extends HttpServlet {

    public static final String ACTION = "action";
    public static final String LIST_ACTION = "displayList";
    public static final String DELETE_ACTION = "Delete";
    public static final String EDIT_ACTION = "Edit";
    public static final String SAVECANCEL_ACTION = "SaveCancel";
    public static final String ADD_ACTION = "Add";
    public static final String A_NAME = "author_name";
    public static final String AUTHOR_NAME = "aName";
    public static final String D_ADDED = "date_added";
    public static final String DATE_ADDED = "aDateAdded";
    public static final String UPDATE = "update";
    public static final String REC_ADD = "rec";
    public static final String ID = "id";
    private static final long serialVersionUID = 1L;

    // Ask Spring for object to inject
    private ServletContext sctx;
    private WebApplicationContext ctx;
    private AuthorService authorService;
    private BookService bookService;

//    @Override
//    public void init(){
//        sctx = getServletContext();
//        ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);
//        authorService = (AuthorService) ctx.getBean("authorService");
//        bookService = (BookService) ctx.getBean("bookService");
//    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.This method contains the logic to divert the requests to the
     * appropriate method of the service class based on the requests coming from
     * the UI.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = "/authorList.jsp"; // default

        try {
            String action = request.getParameter(ACTION);
            String aName = request.getParameter(AUTHOR_NAME);
            String aDateAdded = request.getParameter(DATE_ADDED);
            String addEdit = request.getParameter(REC_ADD);
            String id = request.getParameter(ID);
            String butt_action = request.getParameter("button_action");

            Author author;

            if (action.equalsIgnoreCase(LIST_ACTION)) {
                refreshList(authorService, request);
            } else if (action.equalsIgnoreCase(DELETE_ACTION)) {
                try {
                    authorService.removeAuthorById(id);
                    refreshList(authorService, request);
                } catch (Exception ex) {
                    destination = "/error.jsp";
                    request.setAttribute("errMessage", ex.getMessage());
                    //Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
                }

                refreshList(authorService, request);
            } else if (action.equalsIgnoreCase(EDIT_ACTION)) {
                try {
                    author = authorService.findById(id);
                    request.setAttribute("author", author);
                } catch (Exception ex) {
                    Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
                }

                destination = "/authorEdit.jsp";

            } else if (action.equalsIgnoreCase(SAVECANCEL_ACTION)) {
                if (butt_action.equalsIgnoreCase("Save")) {
                    try {
                        if (addEdit.equalsIgnoreCase(UPDATE)) {
                            authorService.updateAuthorById(Arrays.asList(aName, aDateAdded), id);
                        } else {

                            authorService.addAuthor(Arrays.asList(aName, aDateAdded));
                            refreshList(authorService, request);

                        }
                    } catch (ClassNotFoundException | SQLException | ParseException ex) {
                        destination = "/error.jsp";
                        request.setAttribute("errMessage", ex.getMessage());

                    }
                }
                refreshList(authorService, request);
            } else if (action.equalsIgnoreCase(ADD_ACTION)) {
                request.setAttribute("date", authorService.currentDate());
                destination = "/authorAdd.jsp";

            }

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            destination = "/error.jsp";
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);

    }

    private void refreshList(AuthorService authorService, HttpServletRequest request)
            throws ClassNotFoundException, SQLException {
        List<Author> authorList = null;
        try {
            authorList = authorService.findAll();
        } catch (Exception e) {

        }
        request.setAttribute("authorList", authorList);
        request.setAttribute("bookList", bookService.findAll());
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
    }

    @Override
    public void init(){
        sctx = getServletContext();
        ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);
        authorService = (AuthorService) ctx.getBean("authorService");
        bookService = (BookService) ctx.getBean("bookService");
    }// </editor-fold>
}
