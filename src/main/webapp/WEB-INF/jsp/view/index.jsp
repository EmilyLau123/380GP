<%-- 
    Document   : Index
    Created on : Mar 21, 2022, 7:15:27 PM
    Author     : emilylau
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Page</title>
    </head>
    <body>
        <security:authorize access="hasRole('ROLE_LECTURER')">
            <a href="<c:url value="/user">
                </c:url>"><c:out value="users" /></a><br />
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <a href="<c:url value="/logout">
                </c:url>"><c:out value="logout" /></a><br />
            <p>Hi! <security:authentication property="principal.username" /></p>
        </security:authorize>
        
        <security:authorize access="!isAuthenticated()">
            <p>You are logged out!</p> 
            <a href="<c:url value="/sign_up"></c:url>">
                <c:out value="Sign Up" />
            </a><br />
            <a href="<c:url value="/login">
                </c:url>"><c:out value="login" /></a><br />
        </security:authorize>
                
        <h1>Polls</h1>
        <a href="<c:url value="/poll/create">
        </c:url>">
            Create poll
        </a><br>
        
        
        

        <c:choose>
            <c:when test="${fn:length(pollList) == 0}">
                <i>There are no poll in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach var="entry" items="${pollList}">
                    <a href="<c:url value="/poll/${entry.getPollId()}">
                    </c:url>"><c:out value="${entry.getQuestion()}" /></a><br />
                </c:forEach>
            </c:otherwise>
        </c:choose>
        
        
        
     </body>
</html>
