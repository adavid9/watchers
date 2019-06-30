<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-11
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Watchers - Login</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <form method="POST" action="${pageContext.request.contextPath}/login">
        <h2>Log In</h2>
        <div class="form-group ${msgError != null ? 'has-error' : ''}">
            <div class="form-group">
                <label for="username">Username</label>
                <input name="username" type="text" class="form-control" placeholder="Username"
                       autofocus="true" id="username"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input name="password" type="password" class="form-control" placeholder="Password" id="password"/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-primary" type="submit">Log in</button>
            <button class="btn btn-default"><a href="${pageContext.request.contextPath}/registration">Register</a>
            </button>
        </div>
        <c:if test="${msgError != null}">
            <div class="alert alert-info">
                <c:out value="${msgError}" />
            </div>
        </c:if>
    </form>
</div>
</body>
</html>
