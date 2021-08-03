<%-- 
    Document   : contact.jsp
    Created on : Jul 7, 2021, 11:20:33 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <link href="css/contact.css" rel="stylesheet" type="text/css"/>
        <title>Contact</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="left">
                <div class="about">Contact</div>
                <div class="contact-content">
                    <p class="title-font">PHOTOGRAPHER</p>
                    <div class="address">
                        <p>Address: ${requestScope.contact.address}</p>
                        <p>City: ${requestScope.contact.city}</p>
                        <p>Country: ${requestScope.contact.country}</p>
                    </div>
                    <table class="info">
                        <tr>
                            <td>Tel:</td>
                            <td>${requestScope.contact.telephone}</td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td>${requestScope.contact.email}</td>
                        </tr>
                    </table>
                    <div class="map"></div>
                </div>
            </div>
            <div class="right">
                <%@include file="right.jsp" %>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
