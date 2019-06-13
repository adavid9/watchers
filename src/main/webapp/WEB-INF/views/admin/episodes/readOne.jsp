<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Episode More Info</title>
</head>
<body>
<div class="container">
    <h1>Episode more info:</h1>
    <table border="0" width="20%">
        <tr><b>Episode</b><br></tr>
        <tr><c:out value="id: ${episode.id}"></c:out><br></tr>
        <tr><c:out value="title: ${episode.title}"></c:out><br></tr>
        <tr><c:out value="description: ${episode.description}"></c:out><br></tr>
        <tr><c:out value="release: ${episode.release_date}"></c:out><br></tr>
        <tr><b>Season</b><br></tr>
        <tr><c:out value="name: ${season.name}"></c:out><br></tr>
        <tr><b>Series</b><br></tr>
        <tr><c:out value="title: ${series.title}"></c:out><br></tr>
    </table>
</div>
</body>
</html>
