<%-- 
    Document   : sidenavigation
    Created on : 10 15, 15, 10:48:02 PM
    Author     : jdomugho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<div id="sideNavigation">
    <div>

    </div>
    <div id="menu">
        <ul class="nav">
            <li role="presentation"><a href="#">Downloads</a></li>
<!--            <li role="presentation"><a href="#">Naruto Series</a>
                <ul class="">
                    <li><a href="${cp}/anime/episodes?series=naruto">Naruto</a></li>
                    <li><a href="${cp}/anime/episodes?series=narutoshippuden">Naruto Shippuden</a></li>
                </ul>
            </li>            -->
        </ul>
    </div>
</div>
