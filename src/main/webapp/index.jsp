<%-- 
    Document   : index
    Created on : Sep 19, 2017, 8:01:39 PM
    Author     : jlombardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <title>Book Web Application</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <h3>Pick a Task</h3>
            <div class="form-group" class="col-xs-12 col-sm-4 col-md-2">
                <ul>
                    <li><a href="ac?action=displayList">View all Authors</a></li>
                    <li><a href="bc?action=displayList">View all Books</a></li>
                </ul>
            </div>

            <sec:authorize access="hasAnyRole('ROLE_MGR')">
                <h3>For managers only</h3>
            </sec:authorize>  

        </div> 
    </body>
</html>
