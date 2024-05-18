<%-- 
    Document   : userList
    Created on : May 18, 2024, 2:55:48 PM
    Author     : simon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>User List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Full Name</th>
            <th>Phone</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Status</th>
            <th>Role ID</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.user_id}</td>
                <td>${user.user_name}</td>
                <td>${user.email}</td>
                <td>${user.full_name}</td>
                <td>${user.phone}</td>
                <td><c:choose>
                        <c:when test="${user.gender}">Male</c:when>
                        <c:otherwise>Female</c:otherwise>
                    </c:choose>
                </td>
                <td><c:choose>
                        <c:when test="${user.age}">Adult</c:when>
                        <c:otherwise>Minor</c:otherwise>
                    </c:choose>
                </td>
                <td><c:choose>
                        <c:when test="${user.status}">Active</c:when>
                        <c:otherwise>Inactive</c:otherwise>
                    </c:choose>
                </td>
                <td>${user.role_id}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
