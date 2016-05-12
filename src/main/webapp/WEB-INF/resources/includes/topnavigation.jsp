<%-- 
    Document   : topnavigation
    Created on : 10 16, 15, 5:54:27 PM
    Author     : jdomugho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<div id="topNavigation">
    <div>
        <span id="top-nav-toggle"><i class="glyphicon glyphicon-menu-hamburger"></i></span>        
        <span id="top-nav-menu" class="pull-right">Welcome <span class="badge warning">TBD</span></span>
    </div>
</div>
