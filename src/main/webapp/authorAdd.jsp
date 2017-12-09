<%-- 
    Document   : authorAdd
    Created on : Oct 12, 2017, 8:32:35 PM
    Author     : Roshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Author"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <title>Add Author</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <h3>Add Author Name</h3>
            <form name="author" method="POST" action="ac?action=SaveCancel&rec=new">
                <div style="border:5px;">                       
                    <table class="tabe table-striped table-bordered table-hover table-condensed">
                        <tr>
                            <td>Author Name : </td>
                            <td class="col-xs-12 col-sm-6 col-md-8"><input type="text" maxlength=46 class="form-control" placeholder="Author Name" name="aName" value="${author.authorName}"></td>
                        </tr>
                        <tr>
                            <td>Date Added  : </td>
                            <td><input type="text" class="form-control" placeholder="Date Added" name="aDateAdded" value="${date}" readonly></td>
                        </tr>
                    </table>                        
                    <p><button name="button_action" type="submit" value="Save">Save</button>
                       <button name="button_action" type="submit" value="Cancel">Cancel</button></p>
                </div>
            </form>
        </div>
    </body>
</html>
