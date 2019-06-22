<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-22
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - (Admin) All Users</title>
</head>
<body>
<div class="container">
    <h1>List of all available users.</h1>
    <c:forEach items="${users}" var="user">
        <table border="0" width="20%">
            <tr>Username: <c:out value="${user.username}"/><br></tr>
            <tr>Role:<c:forEach items="${user.roles}" var="role">
                <c:out value="${role.name}"></c:out>
            </c:forEach><br></tr>
        </table>
        <form action="${contextPath}/admin/deleteAccount/${user.id}" method="GET">
            <button type="submit">Delete Account</button>
        </form>
    </c:forEach>
</div>
</body>
</html>
