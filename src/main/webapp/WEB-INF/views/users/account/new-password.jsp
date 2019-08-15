<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - New Password</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>Forgot password?</h2>
    <form method="POST" action="${contextPath}/forgot-password/new-password">
        <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" disabled="disabled"
                   value="<c:out value="${user.username}"/>" class="form-control"
                   id="username"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" id="password"/>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" name="confirmPassword" class="form-control"
                   id="confirmPassword"/>
        </div>
        <button type="submit" class="btn btn-success">Save</button>
    </form>
    <c:if test="${msgError != null}">
        <div class="alert alert-danger">
            <c:out value="${msgError}"></c:out>
        </div>
    </c:if>
</div>
</body>
</html>
