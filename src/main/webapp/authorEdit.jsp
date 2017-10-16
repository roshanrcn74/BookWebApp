<%-- 
    Document   : authorEditAdd
    Created on : Oct 12, 2017, 7:47:59 PM
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
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <title>Edit Author</title>
    </head>
    <body>
        <div class="container">
            <h3>Update Author Name</h3>
            <form name="author" method="POST" action="ac?action=SaveCancel&rec=update">
                <div style="border:5px;">
                    <input type="hidden" name="id" value="${author.authorId}">
                    <table class="tabe table-striped table-bordered table-hover table-condensed">
                        <tr>
                            <td>Author Name : </td>
                            <td><input type="text" class="form-control" placeholder="Author Name" name="aName" value="${author.authorName}"></td>
                        </tr>
                        <tr>
                            <td>Date Added  : </td>
                            <td><input type="text" class="form-control" placeholder="Date Added" name="aDateAdded" value="${author.dateAdded}" readonly></td>
                        </tr>
                    </table>                        
                    <p><button name="button_action" type="submit" value="Save">Save</button>
                       <button name="button_action" type="submit" value="Cancel">Cancel</button></p>
                </div>
            </form>
        </div>
    </body>
</html>
