<%-- 
    Document   : header
    Created on : 10 15, 15, 10:24:21 PM
    Author     : jdomugho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<script src="${cp}/resources/js/angular.min.js"></script>
<script src="${cp}/resources/js/jquery.min.js"></script>
<script src="${cp}/resources/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!--css-->
<link rel="stylesheet" href="${cp}/resources/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${cp}/resources/theme/css/animedownloader.css">