<%-- 
    Document   : book-category
    Created on : Apr 27, 2022, 10:56:57 PM
    Author     : casto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table border="0" width="3">
            <thead>
                <tr>
                    <th>BookTitle</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${forbooks}" var="book">
                <tr>
                    <td>
                        <c:out value="${book.title}" />
                    </td>
                    <td> <c:out value="${book.Athour}" /></td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>
