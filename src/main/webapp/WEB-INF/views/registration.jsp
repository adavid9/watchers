<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/registration">
        <h2 class="sign-form-heading">Create an new account</h2>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" placeholder="Username" name="username" id="username"/>
        </div>
        <div class="form-group">
            <label for="email">Email Address</label>
            <input type="text" class="form-control" placeholder="Email Address" name="email" id="email"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Password" name="password" id="password"/>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" class="form-control" placeholder="Confirm Password" name="confirmPassword"
                   id="confirmPassword"/>
        </div>
        <div class="form-group">
            <label for="question">Question</label>
            <select name="question" id="question" class="form-control">
                <option>Your best friends name?</option>
                <option>City where do you born?</option>
                <option>Name of your ex girlfriend?</option>
                <option>Your favorite food type?</option>
                <option>Where have you been when you were 13?</option>
            </select>
        </div>
        <div class="form-group">
            <label for="answer">
                <input type="text" class="form-control" placeholder="Answer" name="answer"
                id="answer" />
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="roleName" value="ROLE_USER" checked="checked"
                       onclick="return false; /* prevents possibility to uncheck the checkbox*/"/> User Role
            </label>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <c:if test="${msgError != null}">
        <div class="alert alert-danger">
            <c:out value="${msgError}"/>
        </div>
    </c:if>
</div>
</body>
</html>
