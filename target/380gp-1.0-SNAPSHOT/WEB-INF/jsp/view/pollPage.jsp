<%-- 
    Document   : poll
    Created on : Mar 22, 2022, 5:10:56 PM
    Author     : emilylau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Poll Page</title>
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
        <a href="<c:url value="/">
                </c:url>"><c:out value="to Index" /></a><br />
        <a href="<c:url value="/poll/${poll.getPollId()}/delete">
                </c:url>"><c:out value="Delete this Poll" /></a><br />
        <h1>Poll ${poll.getPollId()} question: ${poll.getQuestion()}</h1>
        <c:url var="vote_url"  value="/poll/${poll.getPollId()}/vote" />
        <form:form mehtod="POST" action="${vote_url}" modelAttribute="vote" >
            <input type="hidden" name="username" value="<security:authentication property="principal.username" />"/>
            <form:radiobutton  path="voteOption" value="1" checked="${voteOption eq 1 ? 'checked' : ''}" />${poll.getOption1()}<br>
            <form:radiobutton  path="voteOption" value="2" checked="${voteOption eq 2 ? 'checked' : ''}" />${poll.getOption2()}<br>
            <form:radiobutton  path="voteOption" value="3" checked="${voteOption eq 3 ? 'checked' : ''}" />${poll.getOption3()}<br>
            <form:radiobutton  path="voteOption" value="4" checked="${voteOption eq 4 ? 'checked' : ''}" />${poll.getOption4()}<br>
            <input type="submit" value="Vote"></input><br>
        </form:form>
        <h2>Comments</h2>
        <a href="<c:url value="/poll/${poll.getPollId()}/comment/create">            
        </c:url>">
            Create comment
        </a>
        <p>${comments}</p>
        <c:choose>
            <c:when test="${empty comments}">
               <p>No comments</p> 
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Users</th>
                        <th>Comments</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="entry" items="${comments}">
                        <tr>
                            <td>${entry.getUsername()} </td>
                            <td>${entry.getContent()} </td>
                            <td><a href="<c:url value="/poll/${poll.getPollId()}/comment/${entry.getCommentId()}/delete"></c:url>">
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
