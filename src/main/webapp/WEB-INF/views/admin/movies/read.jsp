<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - All Movies</title>
</head>
<body>
<h1>All Movies:</h1>
<c:forEach items="${movies}" var="movie">
    <table border="0" width="20%">
        <tr><c:out value="Title: ${movie.title}"/><br></tr>
        <tr><c:out value="Description: ${movie.description}"/><br></tr>
        <tr><c:out value="Release: ${movie.release_date}"/><br></tr>
        <tr><c:out value="Country: ${movie.country}"/><br></tr>
        <tr><c:out value="Category: ${movie.category}"/><br></tr>
        <tr><c:out value="Rate: ${movie.rate}"/><br></tr>
    </table>
</c:forEach>
</body>
</html>
