<%-- 
    Document   : bookList
    Created on : Nov 15, 2017, 11:20:43 PM
    Author     : Roshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Author"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <title>BookList</title>
    </head>
        <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <input type="button" class="btn-warning" value="Author List" onclick="location.href = 'ac?action=displayList'">
            <h3>Book List</h3>
            <input type="button" class="btn-warning" value="Add Book" onclick="location.href = 'bc?action=Add'">
            <input type="button" class="btn-warning" value="Delete Selected Books" onclick="location.href = 'bc?action=Delete'">
            <table class="tabe table-striped table-bordered table-hover table-condensed">
                <thead>
                    <tr>
                        <th>Select ID</th>
                        <th>Book ID</th>
                        <th>Book Title</th>
                        <th>ISBN</th>
                        <th>Author</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="b" items="${bookList}">
                        <tr>
                            <td><input type="checkbox" name="booksId" value="${b.bookId}" 
                                    <c:forEach var="id" items="${bookIdList}">                                       
                                            <c:choose>
                                                <c:when test="${id == b.bookId}">                                                    
                                                         checked = "checked"                                                
                                                </c:when>            
                                            </c:choose>                                      
                                    </c:forEach> onclick="location.href = 'bc?action=Find&bId=${b.bookId}'"></td>
                            <td>${b.bookId}</td>
                            <td class="col-xs-12 col-sm-6 col-md-6">${b.title}</td>
                            <td>${b.isbn}</td>
                            <td>${b.author.authorName}</td>
                            <td><input type="button" class="btn-warning" value="Edit" onclick="location.href = 'bc?action=Edit&bId=${b.bookId}'"></td>
                            <td><input type="button" class="btn-danger" value="Delete" onclick="location.href = 'bc?action=Delete&bId=${b.bookId}'"></td>
                        </tr>
                    </c:forEach>
                </tbody>    
            </table>
            <input type="button" class="btn-warning" value="Add Book" onclick="location.href = 'bc?action=Add'">
            <input type="button" class="btn-warning" value="Delete Selected Books" onclick="location.href = 'bc?action=Delete'">
        </div>     
    </body>
</html>
