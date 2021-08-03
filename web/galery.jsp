<%-- 
    Document   : galery
    Created on : Jul 7, 2021, 3:59:39 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <link href="css/galery.css" rel="stylesheet" type="text/css"/>
        <script src="js/paging.js" type="text/javascript"></script>
        <title>Galery</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="left">
                <c:choose>
                    <c:when test="${requestScope.result1 != null}">
                        <p class="result">${requestScope.result1}</p>
                    </c:when>
                    <c:otherwise>
                        <div class="about">
                            ${requestScope.galery.name}
                        </div>
                        <c:choose>
                            <c:when test="${requestScope.result2 != null}">
                                <p class="result">${requestScope.result2}</p>
                            </c:when>
                            <c:otherwise>
                                <div class="image">
                                    <img src="${requestScope.imageURL}" alt="image">
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${requestScope.result3 != null}">
                                <p class="result">${requestScope.result3}</p>
                            </c:when>
                            <c:otherwise>
                                <div class="image-set">
                                    <ul>
                                        <c:forEach items="${requestScope.images}" var="i">
                                            <div class="shadow">
                                            <li>
                                                <a href="galery?pageindex=${pageindex}&galeryID=${galery.id}&imageID=${i.id}">
                                                    <img src="${i.url}">
                                                </a>
                                            </li>
                                            </div>
                                        </c:forEach>
                                    </ul>
                                    <div id="paging" class="bottom-paging"></div>
                                    <script>
                                        renderImagePager("paging",${pageindex},${galery.id},${totalpage},2)
                                    </script>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="right">
                <%@include file="right.jsp" %>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
