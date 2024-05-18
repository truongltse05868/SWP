<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>User List</h1>
    <c:if test="${not empty users}">
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
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.status}">
                                Active
                            </c:when>
                            <c:otherwise>
                                Inactive
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${user.role_id}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty users}">
        <p>No users found.</p>
    </c:if>
</body>
</html>
