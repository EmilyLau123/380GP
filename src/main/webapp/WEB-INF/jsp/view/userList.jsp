<%-- 
    Document   : userList
    Created on : Mar 29, 2022, 6:34:49 PM
    Author     : emilylau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List Page</title>
    </head>
    <style>
        table, th, td {
          border: 1px solid black;
          border-collapse: collapse;
        }
        th, td {
          padding: 5px;
        }
    </style>
    <body>
        <h1>User List</h1>
        <a href="<c:url value="/user/create">            
        </c:url>">
            Create user
        </a>
        <p>${users}</p>
        <c:choose>
            <c:when test="${empty users}">
               <p>No users</p> 
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td><a href="<c:url value="/user/${user.getUsername()}"></c:url>">
                                    <c:out value="${user.getUsername()}"/>
                                </a>
                            </td>
                            <td><a href="<c:url value="/user/${user.getUsername()}/delete"></c:url>">
                                    <c:out value="Delete"/>
                                </a> 
                            </td>
                        </tr>
                    </c:forEach>
                 </table>  
            </c:otherwise>
        </c:choose>
    </body>
</html>
