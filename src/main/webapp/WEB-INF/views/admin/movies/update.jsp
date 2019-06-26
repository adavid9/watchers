<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Update Movie</title>
</head>
<body>
<div class="container">
    <h1>Update Movie</h1>
    <c:forEach items="${movies}" var="movie">
        <table border="0" width="20%">
            <tr><c:out value="Movie id: ${movie.id}"></c:out><br></tr>
            <tr><c:out value="Movie title: ${movie.title}"></c:out><br></tr>
            <tr><c:out value="Movie description: ${movie.description}"></c:out><br></tr>
            <tr><c:out value="Movie country: ${movie.country}"></c:out><br></tr>
            <tr><c:out value="Movie category: ${movie.category}"></c:out><br></tr>
            <tr><c:out value="Movie rate: ${movie.rate}"></c:out><br></tr>
        </table>
        <form action="${contextPath}/admin/updateMovie/${movie.id}" method="POST">
            <button type="submit">Update</button>
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
</div>
</body>
</html>
