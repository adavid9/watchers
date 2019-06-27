<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${pageContext.request.userPrincipal.name}" var="user" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-26
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Admin Start Page</title>
</head>
<body>
    <h1>Admin Page, you're logged as <c:out value="${user}"></c:out></h1>
</body>
</html>
