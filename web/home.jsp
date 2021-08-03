<%-- 
    Document   : home.jsp
    Created on : Jun 30, 2021, 10:12:57 AM
    Author     : Dell Inc
--%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <script src="js/paging.js" type="text/javascript"></script>
        <title>Home</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="container">
            <div class="left">
                <div class="image">
                    <img src="${requestScope.contact.image}">
                </div>
                <div class="summmary">
                    ${requestScope.contact.sumary}
                </div>
                <div class="galeries">
                    <c:choose>
                        <c:when test="${requestScope.result != null}">
                            <p class="result">${requestScope.result}</p>
                        </c:when>
                        <c:otherwise>
                            <ul>
                                <c:forEach items="${requestScope.galeries}" var="g">
                                    <li>
                                        <div class="image">
                                            <img src="${g.image_url}"
                                        </div>
                                        <p class="galery-title">
                                            <a href="galery?galeryID=${g.id}">${g.title}</a>
                                        </p>
                                        <p class="description-font">${g.description}</p>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div id="paging" class="bottom-paging"></div>
                            <script>
                                renderHomePager("paging",${pageindex},${totalpage},2)
                            </script>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="about">
                    About me
                </div>
                <div class="description-font">
                    ${requestScope.contact.about}
                </div>
            </div>
            <div class="right">
                <%@include file="right.jsp" %>
            </div>
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>
