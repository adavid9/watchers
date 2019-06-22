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
</head>
<body>
<div class="container">
    <h1>Delete your account</h1>
    <p>To delete account you have to provide matching password to your account password.</p>
    <form method="POST" action="${contextPath}/deleteAccount" id="deleteAccount">
        <table border="0" width="20%">
            <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
            <tr>Username:<br></tr>
            <tr><input type="text" disabled="disabled" name="username" value="<c:out value="${user.username}"/>"><br>
            </tr>
            <tr>Password:<br></tr>
            <tr><input type="password" name="password"><br></tr>
            <tr>Are you sure?</tr>
            <tr><input type="checkbox" name="isSure" /></tr>
        </table>
        <button type="submit">Delete</button>
    </form>
    <c:if test="${msgError != null}">
        <c:out value="${msgError}"></c:out>
    </c:if>
</div>
</body>
</html>
