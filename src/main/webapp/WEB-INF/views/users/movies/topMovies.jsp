<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h1>List of top 10 movies by rate.</h1>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Rate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${movies}" var="movie" varStatus="i">
            <tr>
                <td><c:out value="${i.count}"></c:out></td>
                <td><c:out value="${movie.title}"></c:out></td>
                <td><c:out value="${movie.rate}"></c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
