<%-- 
    Document   : header
    Created on : Jul 6, 2021, 11:08:32 AM
    Author     : Dell Inc
--%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="nav">
            <div class="pre-header">
                <a class="${requestScope.active=="activeHome"?"active":""}"
                   href="home">My front page</a>
                <c:forEach items="${requestScope.top3Galery}" var="g">
                    <a class="${requestScope.active==g.name ? "active":""}"
                       href="galery?galeryID=${g.id}">${g.name}</a> 
                </c:forEach>
                <a class="${requestScope.active=="activeContact"?"active":""}"
                   href="contact">Contact</a> 
            </div>
        </div>
        <div class="header">                  
            <div class="header-inner">
                <div class="img"></div>
                <div>
                    <div class="big-title">PHOTOGRAPHER</div>
                    <div class="small-title">Welcome to this website</div>
                </div>
            </div>
        </div>  
    </body>
</html>
