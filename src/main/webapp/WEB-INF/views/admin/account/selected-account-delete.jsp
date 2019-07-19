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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h1>Delete previously selected account</h1>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Roles</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${account.username}"/></td>
            <td>
                <c:forEach var="role" items="${account.roles}">
                    <span><c:out value="${role.roleName.name()}"/></span>
                </c:forEach>
            </td>
            <td>
                <form action="${contextPath}/admin/deleteAccount/${account.id}" method="POST">
                    <button type="submit">Delete Account</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
