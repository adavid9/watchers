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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>Update Movie</h2>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Country</th>
            <th scope="col">Category</th>
            <th scope="col">Rate</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${movies}" var="movie">
            <tr>
                <td><c:out value="${movie.id}"/></td>
                <td><c:out value="${movie.title}"/></td>
                <td><c:out value="${movie.description}"/></td>
                <td><c:out value="${movie.country}"/></td>
                <td><c:out value="${movie.category}"/></td>
                <td><c:out value="${movie.rate}"/></td>
                <td>
                    <form action="${contextPath}/admin/updateMovie/${movie.id}" method="POST">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${msgError != null}">
        <div class="alert alert-danger">
            <c:out value="${msgError}"/>
        </div>
    </c:if>
    <c:if test="${msgSuccess != null}">
        <div class="alert alert-success">
            <c:out value="${msgSuccess}"/>
        </div>
    </c:if>
</div>
</body>
</html>
