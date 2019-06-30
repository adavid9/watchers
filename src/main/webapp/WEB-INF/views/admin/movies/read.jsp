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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>All Movies</h2>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Release Date</th>
            <th scope="col">Country</th>
            <th scope="col">Category</th>
            <th scope="col">Rate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${movies}" var="movie">
            <tr>
                <td><c:out value="${movie.title}"/></td>
                <td><c:out value="${movie.description}"/></td>
                <td><c:out value="${movie.release_date}"/></td>
                <td><c:out value="${movie.country}"/></td>
                <td><c:out value="${movie.category}"/></td>
                <td><c:out value="${movie.rate}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
