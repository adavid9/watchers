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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h1>List of all available users.</h1>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Role</th>
            <th scope="col">-</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.username}"/></td>
                <td>
                    <c:forEach items="${user.roles}" var="role">
                        <span><c:out value="${role.name}"/></span>
                    </c:forEach>
                </td>
                <td>
                    <form action="${contextPath}/admin/deleteAccount/${user.id}" method="GET">
                        <button type="submit" class="btn btn-danger">Delete Account</button>
                    </form>
                </td>
                <td>
                    <form action="${contextPath}/admin/changeUserPassword/${user.id}" method="GET">
                        <button type="submit" class="btn btn-default">Change Password</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
