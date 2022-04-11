<%-- 
    Document   : editUser
    Created on : Mar 30, 2022, 7:15:27 PM
    Author     : emilylau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User Page</title>
    </head>
    <body>
        <h1>Edit a user</h1>
        ${role}
        <form:form method="POST" modelAttribute="user">
            <form:label path="username">Username: </form:label>
            <form:input path="username" value="${username}"/><br/>
            <form:label path="password">Password: </form:label>
            <form:input path="password" value="${password}"/><br/>
            <form:label path="fullname">Full name: </form:label>
            <form:input path="fullname" value="${fullname}"/><br/>
            <form:label path="phone">Phone: </form:label>
            <form:input path="phone" value="${phone}"/><br/>
            <form:label path="address">Address: </form:label>
            <form:input path="address" value="${address}"/><br/>
            <label id="role">Role: </label>
            <form:radiobutton  path="role" value="ROLE_LECTURER" checked="${role eq 'ROLE_LECTURER' ? 'checked' : ''}" />Lecturer<br>
            <form:radiobutton  path="role" value="ROLE_STUDENT" checked="${role eq 'ROLE_STUDENT' ? 'checked' : ''}" />Student<br>

            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>
