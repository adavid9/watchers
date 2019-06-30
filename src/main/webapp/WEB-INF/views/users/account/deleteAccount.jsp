<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-22
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Delete Account</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h1>Delete your account</h1>
    <p>To delete account you have to provide matching password to your account password.</p>
    <hr>
    <form method="POST" action="${contextPath}/deleteAccount" id="deleteAccount">
        <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" class="form-control" disabled="disabled" name="username"
                   value="<c:out value="${user.username}"/>">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" id="password" class="form-control"/>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="isSure"/>Are you sure?
            </label>
        </div>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
    <c:if test="${msgError != null}">
        <div class="alert alert-danger">
            <c:out value="${msgError}"></c:out>
        </div>
    </c:if>
</div>
</body>
</html>
