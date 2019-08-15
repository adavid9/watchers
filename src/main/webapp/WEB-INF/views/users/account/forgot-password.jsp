<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-08-15
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <title>Watchers - Forgot Password</title>
        <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
        <link rel="stylesheet"
              href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
    </head>
</head>
<body>
    <div class="container">
        <h2>Forgot Password?</h2>
        <hr>
        <c:if test="${msgError != null}">
            <div class="alert alert-danger">
                <c:out value="${msgError}"></c:out>
            </div>
        </c:if>
        <form method="POST" action="${contextPath}/forgot-password">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" name="username" class="form-control" id="username"/>
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
                <label for="answer">Answer</label>
                <input type="text" name="answer" class="form-control" id="answer"/>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>
