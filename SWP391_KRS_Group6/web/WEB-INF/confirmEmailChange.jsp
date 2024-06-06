
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm Email Change</title>
</head>
<body>
    <h3>Confirm Your Email Change</h3>
    <form action="${pageContext.request.contextPath}/userController" method="post">
        <input type="hidden" name="action" value="confirmEmailChange">
        <input type="hidden" name="userId" value="${sessionScope.userId}">
        <input type="hidden" name="newEmail" value="${sessionScope.newEmail}">
        <div>
            <label>Enter OTP sent to your new email:</label>
            <input type="text" name="otp" required>
        </div>
        <button type="submit">Confirm</button>
        <c:if test="${param.error == 'invalidOrExpiredOtp'}">
            <div style="color: red;">Invalid or expired OTP. Please try again.</div>
        </c:if>
    </form>
</body>
</html>
