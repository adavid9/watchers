<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-10
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Home</title>
</head>
<body>
    <c:if test="${msg != null}">
        <h1><c:out value="${msg}"></c:out></h1>
        <c:if test="${description != null}">
            <p><c:out value="${description}"></c:out></p>
        </c:if>
    </c:if>
</body>
</html>
