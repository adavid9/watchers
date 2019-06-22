<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-23
  Time: 00:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Selected Account</title>
</head>
<body>
<div class="container">
    <table border="0" width="20%">
        <tr>Username: <c:out value="${account.username}"/><br></tr>
        <tr>Role:<c:forEach items="${account.roles}" var="role">
            <c:out value="${role.name}"></c:out>
        </c:forEach><br></tr>
    </table>
    <form action="${contextPath}/admin/deleteAccount/${account.id}" method="POST">
        <button type="submit">Delete Account</button>
    </form>
</div>
</body>
</html>
