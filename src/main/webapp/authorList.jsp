<%-- 
    Document   : authorList
    Created on : Sep 19, 2017, 8:35:54 PM
    Author     : jlombardo
--%>

<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Author"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <title>Author List</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <input type="button" class="btn-warning" value="Book List" onclick="location.href = 'bc?action=displayList'">
            <h3>Author List</h3>
            <input type="button" class="btn-warning" value="Add Author" onclick="location.href = 'ac?action=Add'">
            <table class="tabe table-striped table-bordered table-hover table-condensed">
                <thead>
                    <tr>
                        <th>Author Name</th>
                        <th>Date Added</th>
                        <th>BookList</th>
                        <th>Edit</th>
                        <th>Delete</th>
                        <th>Add Book</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${authorList}">
                        <tr>
                            <td class="col-xs-12 col-sm-6 col-md-4">${a.authorName}</td>
                            <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.dateAdded}" /></td>
                            <td>
                                <select name="bTitle" size="1" width="30">
                                    <c:forEach var="b" items="${bookList}">                                       
                                            <c:choose>
                                                <c:when test="${a.authorId == b.author.authorId}">                                                    
                                                        <option value="">
                                                            ${b.title}                                                   
                                                            <br/>
                                                        </option>                                                    
                                                </c:when>            
                                            </c:choose>                                      
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="button" class="btn-warning" value="Edit" onclick="location.href = 'ac?action=Edit&id=${a.authorId}'"></td>
                            <td><input type="button" class="btn-danger" value="Delete" onclick="location.href = 'ac?action=Delete&id=${a.authorId}'"></td>
                            <td><input type="button" class="btn-danger" value="Add Book" onclick="location.href = 'bc?action=Add&bAuthorId=${a.authorId}'"></td>
                        </tr>
                    </c:forEach>
                </tbody>    
            </table>
            <input type="button" class="btn-warning" value="Add Author" onclick="location.href = 'ac?action=Add'">
        </div>     
    </body>
</html>
