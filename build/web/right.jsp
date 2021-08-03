<%-- 
    Document   : right
    Created on : Jul 6, 2021, 3:17:30 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/right.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="header-right">
            <p>Share this page</p>
        </div>
        <div class="inner-right">
            <div class="icon">
                <img src="${requestScope.contact.icon_face}">
                <br>
                <img src="${requestScope.contact.icon_twitter}">
                <br>
                <img src="${requestScope.contact.icon_google}">
            </div>
            <div class="linkShare">
                <a href="${contact.fb_url}">Share on Facebook</a>
                <a href="${contact.twitter_url}">Share on Twitter</a>
                <a href="${contact.google_url}">Share on Google+</a>
            </div>
        </div>
    </body>
</html>
