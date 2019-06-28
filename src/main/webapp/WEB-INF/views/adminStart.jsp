<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${pageContext.request.userPrincipal.name}" var="user"/>
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
<h3>Available menu</h3>
<ul>
    <h4>TV Series:</h4>
    <ul>
        <h4>Series:</h4>
        <ul>
            <li><a href="${contextPath}/admin/addSeries">Add Series</a></li>
            <li><a href="${contextPath}/admin/deleteSeries">Delete Series</a></li>
            <li><a href="${contextPath}/admin/updateSeries">Edit Series</a></li>
            <li><a href="${contextPath}/admin/readSeries">Find Series</a></li>
        </ul>
        <h4>Seasons:</h4>
        <ul>
            <li><a href="${contextPath}/admin/addSeason">Add Season</a></li>
            <li><a href="${contextPath}/admin/deleteSeason">Delete Season</a></li>
            <li><a href="${contextPath}/admin/updateSeason">Edit Season</a></li>
            <li><a href="${contextPath}/admin/readSeason">Find Season</a></li>
        </ul>
        <h4>Episodes</h4>
        <ul>
            <li><a href="${contextPath}/admin/addEpisode">Add Episode</a></li>
            <li><a href="${contextPath}/admin/deleteEpisode">Delete Episode</a></li>
            <li><a href="${contextPath}/admin/updateEpisode">Update Episode</a></li>
            <li><a href="${contextPath}/admin/readEpisode">Find Episode</a></li>
        </ul>
    </ul>
    <h4>Movies</h4>
    <ul>
        <li><a href="${contextPath}/admin/addMovie">Add Movie</a></li>
        <li><a href="${contextPath}/admin/deleteMovie">Delete Movie</a></li>
        <li><a href="${contextPath}/admin/updateMovie">Edit Movie</a></li>
        <li><a href="${contextPath}/admin/readMovie">Find Movie</a></li>
    </ul>
    <h4>Accounts</h4>
    <ul>
        <li><a href="${contextPath}/admin/accounts">All Accounts</a></li>
    </ul>
    <h4>Manage Account</h4>
    <ul>
        <li><a href="${contextPath}/changePassword">Change Password</a></li>
        <li><a href="${contextPath}/deleteAccount">Delete Account</a></li>
    </ul>
    <br>
    <button type="button">
        <a href="${contextPath}/welcome">
            Home
        </a>
    </button>
</ul>
</body>
</html>
