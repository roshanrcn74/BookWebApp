<%-- 
    Document   : error
    Created on : Oct 15, 2017, 11:02:37 PM
    Author     : Roshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
        <title>ErrorPage</title>
    </head>
    <body>
        <h3>Error Message</h3>
        <p> Error Message : ${errMessage}</p>
    </body>
</html>
