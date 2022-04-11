<%-- 
    Document   : signUp
    Created on : Mar 28, 2022, 5:32:47 PM
    Author     : emilylau
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
        <h1>Sign Up Page</h1>
        <form:form method="POST" modelAttribute="user">
            <form:label path="userId">userId: </form:label>
            <form:input path="userId"/><br/>
            <form:label path="username">Username: </form:label>
            <form:input path="username"/><br/>
            <form:label path="password">Password: </form:label>
            <form:input path="password"/><br/>
            <form:label path="fullname">Full name: </form:label>
            <form:input path="fullname"/><br/>
            <form:label path="phone">Phone number: </form:label>
            <form:input path="phone"/><br/>
            <form:label path="address">Address: </form:label>
            <form:input path="address"/><br/>
            
            <input type="submit" value="Submit"/>
        </form:form>
        <c:if test="${signUpFailed eq true}">
            Sign up Fail!
        </c:if>
    </body>
</html>
