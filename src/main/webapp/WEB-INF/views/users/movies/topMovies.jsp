<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-26
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Top 10 Movies</title>
</head>
<body>
    <div class="container">
        <h1>List of top 10 movies by rate.</h1>
        <c:forEach items="${movies}" var="movie" varStatus="i">
            <table border="0" width="20%">
                <tr><c:out value="${i.count}"></c:out><br></tr>
                <tr>Title: <c:out value="${movie.title}"></c:out><br></tr>
                <tr>Rate: <c:out value="${movie.rate}"></c:out><br></tr>
            </table>
        </c:forEach>
    </div>
</body>
</html>
