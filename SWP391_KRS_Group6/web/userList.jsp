<%-- 
    Document   : userList
    Created on : May 18, 2024, 2:55:48 PM
    Author     : simon
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <table border="1">
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
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.status}</td>
                <td>${user.role_id}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
