<%-- 
    Document   : createUser
    Created on : Mar 29, 2022, 9:28:45 PM
    Author     : emilylau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Create Page</title>
    </head>
    <body>
        <h1>Create a user</h1>
        <form:form method="POST" modelAttribute="user">
            <form:label path="username">Username: </form:label>
            <form:input path="username"/><br/>
            <form:label path="password">Password: </form:label>
            <form:input path="password"/><br/>
            <form:label path="fullname">Full name: </form:label>
            <form:input path="fullname"/><br/>
            <form:label path="phone">Phone: </form:label>
            <form:input path="phone"/><br/>
            <form:label path="address">Address: </form:label>
            <form:input path="address"/><br/>
            <label id="role">Role: </label>
            <input type="radio" id="role" name="role" value="ROLE_LECTURER">Lecturer</input>
            <input type="radio" id="role" name="role" value="ROLE_STUDENT">Student</input>
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
