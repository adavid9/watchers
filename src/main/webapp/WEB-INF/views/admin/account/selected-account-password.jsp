<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-29
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Change Selected Account Password</title>
</head>
<body>
<div class="container">
    <form action="${contextPath}/admin/changeUserPassword" method="POST">
        <table border="0" width="20%">
            <input type="hidden" name="id" value="<c:out value="${account.id}"/>"/>
            <tr>Username:<br></tr>
            <tr><input type="text" name="username" disabled="disabled"
                       value="<c:out value="${account.username}"/>"/><br>
            </tr>
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
</div>
</body>
</html>
