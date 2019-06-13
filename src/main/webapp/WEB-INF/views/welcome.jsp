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
</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <c:if test="${msg != null}">
            <h1><c:out value="${msg}"></c:out></h1>
            <c:if test="${description != null}">
                <p><c:out value="${description}"></c:out></p>
            </c:if>
        </c:if>
        <button type="button">
            <a href="${contextPath}/registration">
                Register
            </a>
        </button>
        <button type="button">
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
                onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
        <div>
            <button type="button">
                <a href="${contextPath}/addSeries">
                    Add Series
                </a>
            </button>
            <button type="button">
                <a href="${contextPath}/addSeason">
                    Add Season
                </a>
            </button>
            <button type="button">
                <a href="${contextPath}/addEpisode">
                    Add Episode
                </a>
            </button>
        </div>
    </c:if>
</div>
</body>
</html>
