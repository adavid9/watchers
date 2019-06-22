<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-22
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Change Password</title>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/changePassword">
        <table border="0" width="20%">
            <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
            <tr>Username<br></tr>
            <tr><input type="text" disabled="disabled" value="<c:out value="${user.username}"/>"/><br></tr>
            <tr>Password<br></tr>
            <tr><input type="password" name="password"/><br></tr>
            <tr>Confirm Password<br></tr>
            <tr><input type="password" name="confirmPassword"/><br></tr>
        </table>
        <button type="submit">Save</button>
    </form>
    <c:if test="${msgError != null}">
        <c:out value="${msgError}"></c:out>
    </c:if>
    <c:if test="${msgSuccess != null}">
        <c:out value="${msgSuccess}"></c:out>
    </c:if>
</div>
</body>
</html>
