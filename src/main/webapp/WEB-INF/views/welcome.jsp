<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <h1>!!!Welcome in Watchers!!!</h1>
        <button type="button" class="btn btn-default">
            <a href="${contextPath}/registration">
                Register
            </a>
        </button>
        <button type="button" class="btn btn-default">
            <a href="${contextPath}/login">
                Login
            </a>
        </button>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a
                onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>
</div>
</body>
</html>
