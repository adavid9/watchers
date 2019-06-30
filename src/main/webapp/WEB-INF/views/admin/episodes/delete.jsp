<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Delete Episode</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>Delete episode</h2>
    <hr>
    <form method="POST" action="${contextPath}/admin/deleteEpisode">
        <h3>Delete episode by title</h3>
        <hr>
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" name="title" class="form-control" id="title"/>
        </div>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
    <br>
    <h3>Delete episode by button</h3>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Release Date</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${episodes}" var="episode">
            <tr>
                <td><c:out value="${episode.id}"/></td>
                <td><c:out value="${episode.title}"/></td>
                <td><c:out value="${episode.description}"/></td>
                <td><c:out value="${episode.release_date}"/></td>
                <td>
                    <form method="POST" action="${contextPath}/admin/deleteEpisode/${episode.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
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
