<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Delete Movie</title>
</head>
<body>
<h1>Delete Movie:</h1>
<h5>Delete by title:</h5>
<form method="POST" action="${contextPath}/deleteMovie">
    <input type="text" name="title"/>
    <button type="submit">Delete</button>
</form>

<c:forEach items="${movies}" var="movie">
    <table border="0" width="20%">
        <tr><c:out value="Title: ${movie.title}"/><br></tr>
        <tr><c:out value="Description: ${movie.description}"/><br></tr>
        <tr><c:out value="Release: ${movie.release_date}"/><br></tr>
        <tr><c:out value="Country: ${movie.country}"/><br></tr>
        <tr><c:out value="Category: ${movie.category}"/><br></tr>
    </table>
    <form method="POST" action="${contextPath}/deleteMovie/${movie.id}">
        <button type="submit">Delete</button>
    </form>
</c:forEach>
<c:if test="${msgError != null}">
            <span>
                <c:out value="${msgError}"></c:out>
            </span>
</c:if>
<c:if test="${msgSuccess != null}">
            <span>
                <c:out value="${msgSuccess}"></c:out>
            </span>
</c:if>
</body>
</html>
