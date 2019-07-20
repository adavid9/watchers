<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-12
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Delete Series</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>Delete series</h2>
    <p>If you delete a series, all related seasons and episodes will be also removed.</p>
    <hr>
    <form method="POST" action="${contextPath}/admin/deleteSeries">
        <h3>Delete series by title</h3>
        <hr>
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" name="title" id="title" class="form-control"/>
        </div>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
    <br>
    <h3>Delete series by button</h3>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Category</th>
            <th scope="col">Country</th>
            <th scope="col">Director</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${series}" var="series">
            <tr>
                <td><c:out value="${series.id}"/></td>
                <td><c:out value="${series.title}"/></td>
                <td><c:out value="${series.description}"/></td>
                <td><c:out value="${series.category.type}"/></td>
                <td><c:out value="${series.country}"/></td>
                <td><c:out value="${series.director}"/></td>
                <td>
                    <form action="${contextPath}/admin/deleteSeries/${series.id}" method="POST">
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
