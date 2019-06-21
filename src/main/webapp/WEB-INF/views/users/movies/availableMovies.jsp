<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-21
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - All Available Movies</title>
</head>
<body>
<h1>List of available movies</h1>
<p>Hit the add movie button to add a movie to your movie list.</p>
<div class="container">
    <c:forEach items="${movies}" var="movie">
        <table border="0" width="20%">
            <tr>Title<br></tr>
            <tr><c:out value="${movie.title}"/><br></tr>
            <tr>Description<br></tr>
            <tr><c:out value="${movie.description}"/><br></tr>
            <tr>Release<br></tr>
            <tr><c:out value="${movie.release_date}"/><br></tr>
            <tr>Country<br></tr>
            <tr><c:out value="${movie.country}"/><br></tr>
            <tr>Category<br></tr>
            <tr><c:out value="${movie.category}"/><br></tr>
        </table>
        <form action="${contextPath}/availableMovies/${movie.id}" method="POST" id="add">
            <button type="submit">Add Movie</button>
        </form>
    </c:forEach>
    <c:if test="${msgError != null}">
        <c:out value="${msgError}"></c:out>
    </c:if>
    <c:if test="${msgSuccess != null}">
        <c:out value="${msgSuccess}"></c:out>
    </c:if>
</div>
</body>
</html>
