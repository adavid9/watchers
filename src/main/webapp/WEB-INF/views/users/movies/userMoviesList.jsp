<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-21
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - User Movies List</title>
</head>
<body>
<h1><%=SecurityContextHolder.getContext().getAuthentication().getName()%> movie list</h1>
<p>Hit the remove button if you would like to remove a movie from the list</p>
<div class="container">
    <c:forEach items="${movies}" var="movie">
        <table border="0" width="20%">
            <tr>Title<br></tr>
            <tr><c:out value="${movie.title}"></c:out><br></tr>
            <tr>Description<br></tr>
            <tr><c:out value="${movie.description}"></c:out><br></tr>
            <tr>Release<br></tr>
            <tr><c:out value="${movie.release_date}"></c:out><br></tr>
            <tr>Country<br></tr>
            <tr><c:out value="${movie.country}"></c:out><br></tr>
            <tr>Category<br></tr>
            <tr><c:out value="${movie.category}"></c:out><br></tr>
            <tr>Rate<br></tr>
            <tr><c:out value="${movie.rate}"></c:out><br></tr>
        </table>
        <form method="POST" action="${contextPath}/myMoviesList/${movie.id}">
            <button type="submit">Remove</button>
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
