<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-11
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Watchers - Registration</title>
</head>
<body>
    <div class="container">
        <form method="POST" action="${pageContext.request.contextPath}/registration" class="sign-form">
            <h2 class="sign-form-heading">Create an new account</h2>
            <p><input type="text" name="username" /></p>
            <p><input type="password" name="password" /></p>
            <p><input type="password" name="confirmPassword" /></p>
            <input type="submit" value="Submit" />
        </form>
    </div>
</body>
</html>
