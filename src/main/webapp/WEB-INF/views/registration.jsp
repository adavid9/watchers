<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-11
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Registration</title>
</head>
<body>
    <div class="container">
        <form method="POST" action="${contextPath}/registration" class="sign-form">
            <h2 class="sign-form-heading">Create an new account</h2>
            <p>Username:</br>
                <input type="text" name="username" />
            </p>
            <p>Password:</br>
                <input type="password" name="password" />
            </p>
            <p>Confirm Password:</br>
                <input type="password" name="confirmPassword" />
            </p>
            <p>User Role:</br>
                <input type="checkbox" name="roleName" value="ROLE_USER" checked="checked" />
            </p>
            <input type="submit" value="Submit" />
        </form>
        <c:if test="${msgError != null}">
            <span>
                <c:out value="${msgError}" />
            </span>
        </c:if>
    </div>
</body>
</html>
