<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - All Series</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>All Series</h2>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Country</th>
            <th scope="col">Director</th>
            <th scope="col">Release Date</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${series}" var="series">
            <tr>
                <td><c:out value="${series.id}"/></td>
                <td><c:out value="${series.title}"/></td>
                <td><c:out value="${series.description}"/></td>
                <td><c:out value="${series.country}"/></td>
                <td><c:out value="${series.director}"/></td>
                <td><c:out value="${series.release_date}"/></td>
                <td>
                    <form action="${contextPath}/admin/readSeries/${series.id}" method="POST">
                        <button type="submit" class="btn btn-default">More info</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
