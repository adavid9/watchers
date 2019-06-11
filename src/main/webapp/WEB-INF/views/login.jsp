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
</head>
<body>
    <div class="container">
        <form method="POST" action="${pageContext.request.contextPath}/login" class="form-signin">
            <h2 class="form-heading">Log in</h2>
            <div class="form-group ${msgError != null ? 'has-error' : ''}">
                <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true" />
                <input name="password" type="password" class="form-control" placeholder="Password" />
                <span>${msgError}</span>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
                <h4 class="text-center"><a href="${pageContext.request.contextPath}/registration">Register</a></h4>
            </div>
        </form>
    </div>
</body>
</html>
