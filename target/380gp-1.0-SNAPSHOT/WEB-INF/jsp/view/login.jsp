<%-- 
    Document   : login
    Created on : Mar 26, 2022, 2:07:29 PM
    Author     : emilylau
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <a href="<c:url value="/">
                </c:url>"><c:out value="to Index" /></a><br />
        <a href="<c:url value="/sign_up"></c:url>">
            <c:out value="Sign Up" />
        </a><br />
        <form:form method="POST" modelAttribute="user">
            <form:label path="username">Username: </form:label>
            <form:input path="username"/><br/>
            <form:label path="password">Password </form:label>
            <form:input path="password"/><br/>
            <input type="checkbox" id="remember-me" name="remember-me" />
            <label for="remember-me">Remember me</label><br/><br/>
            <input type="submit" value="Submit"/>
        </form:form>
        <c:if test="${loginFailed eq true}">
            Sign up Fail!
        </c:if>
    </body>
</html>
