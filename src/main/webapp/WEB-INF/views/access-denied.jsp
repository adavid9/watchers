<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-20
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Access Denied</title>
</head>
<body>
    <div class="container">
        <h1>Access Denied for <c:out value="${user}"/></h1>
        <p>Click the button to back on welcome page.</p>
        <a href="${contextPath}/welcome">
            <button type="button">Back</button>
        </a>
    </div>
</body>
</html>
