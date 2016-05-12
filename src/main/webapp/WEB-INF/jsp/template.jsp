<%-- 
    Document   : index
    Created on : 10 14, 15, 8:06:04 PM
    Author     : jdomugho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Naruto Episodes</title>
        <%@include file="../resources/includes/header.jsp" %>
    </head>
    <body>
        <%@include file="../resources/includes/sidenavigation.jsp" %>
        <div id="page-content">
            <%@include file="../resources/includes/topnavigation.jsp" %>
            
        </div>
    </body>
</html>