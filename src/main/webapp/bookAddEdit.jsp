<%-- 
    Document   : bookAdd
    Created on : Nov 25, 2017, 2:51:06 PM
    Author     : Roshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Book"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <title>Add Book</title>
    </head>
    <body>
        <div class="container">
            <h3>Edit or Add New Book </h3>
            <form name="book" method="POST" action="bc?action=SaveCancel&rec=new">
                <input type="hidden" name="bId" value="${book.bookId}">
                <div style="border:5px;">                       
                    <table class="tabe table-striped table-bordered table-hover table-condensed">
                        <tr>
                            <td>Book title : </td>
                            <td class="col-xs-12 col-sm-6 col-md-8"><input type="text" maxlength=46 class="form-control" placeholder="Title" name="bTitle" value="${book.title}"></td>
                        </tr>

                        <tr>
                            <td>ISBN : </td>
                            <td class="col-xs-12 col-sm-6 col-md-8"><input type="text" maxlength=46 class="form-control" placeholder="ISBN" name="bIsbn" value="${book.isbn}"></td>
                        </tr>

                        <tr>
                            <td>Author : </td>
                            <td>
                                <select name="bAuthorId" size="1" width="30">
                                    <c:forEach var="b" items="${authorList}">
                                        <option value="${b.authorId}" 
                                                <c:if test="${b.authorId == book.author.authorId || b.authorId == authorId}">
                                                    selected
                                                </c:if>
                                                >
                                            ${b.authorName}
                                        </option>
                                    </c:forEach>                                       
                                </select>
                            </td>
                        </tr>

                    </table>                        
                    <p>
                        <button name="button_action" type="submit" value="Save">Save</button>
                        <button name="button_action" type="submit" value="Cancel">Cancel</button>
                        <input type="button" class="btn-warning" value="Add New Author" onclick="location.href = 'ac?action=Add'">
                    </p>
                </div>
            </form>
        </div>
    </body>
</html>
