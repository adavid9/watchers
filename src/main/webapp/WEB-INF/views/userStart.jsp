<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${pageContext.request.userPrincipal.name}" var="user"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-28
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - User Start Page</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h1>User Page, you're logged in as <c:out value="${user}"></c:out></h1>
    <h3>Available menu</h3>
    <ul>
        <h4>Account:</h4>
        <ul>
            <li><a href="${contextPath}/changePassword">Change Password</a></li>
            <li><a href="${contextPath}/deleteAccount">Delete Account</a></li>
        </ul>
        <h4>Movies:</h4>
        <ul>
            <li><a href="${contextPath}/myMoviesList">My Movies</a></li>
            <li><a href="${contextPath}/availableMovies">Available Movies</a></li>
            <li><a href="${contextPath}/topMovies">Top 10 Movies</a></li>
        </ul>
        <h4>Movies:</h4>
        <ul>
            <li><a href="${contextPath}/user/series-list">Available Series</a></li>
            <li><a href="${contextPath}/user/seasons-list">Available Seasons</a></li>
            <li><a href="${contextPath}/user/episodes-list">Available Episodes</a></li>
            <li><a href="${contextPath}/user/seasons">User Seasons</a></li>
            <li><a href="${contextPath}/user/series">User Series</a></li>
            <li><a href="${contextPath}/user/episodes">User Episodes</a></li>
        </ul>
        <br><br>
        <a href="${contextPath}/welcome">
            <button type="button" class="btn btn-default">
                Home
            </button>
        </a>
    </ul>
</div>
</body>
</html>
