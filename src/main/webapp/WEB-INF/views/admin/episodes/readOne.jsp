<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>Episode more info</h2>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Release Date</th>
            <th scope="col">Season</th>
            <th scope="col">Series</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${episode.id}"/></td>
            <td><c:out value="${episode.title}"/></td>
            <td><c:out value="${episode.description}"/></td>
            <td><c:out value="${episode.release_date}"/></td>
            <td><c:out value="${season.name}"/></td>
            <td><c:out value="${series.title}"/></td>
        </tr>
        </tbody>
        <a href="${contextPath}/admin/readEpisode">
            <button type="button" class="btn btn-default">Back</button>
        </a>
    </table>
    <c:if test="${msgError}">
        <div class="alert alert-danger">
            <c:out value="${msgError}"/>
        </div>
    </c:if>
</div>
</body>
</html>
