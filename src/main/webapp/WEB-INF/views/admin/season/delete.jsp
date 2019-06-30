<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-12
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Delete Season</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>Delete season</h2>
    <hr>
    <form action="${contextPath}/admin/deleteSeason" method="POST">
        <h3>Delete season by title</h3>
        <hr>
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" name="name" id="name" class="form-control"/>
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
            <th scope="col">Name</th>
            <th scope="col">Release Date</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${seasons}" var="season">
            <tr>
                <td><c:out value="${season.id}"/></td>
                <td><c:out value="${season.name}"/></td>
                <td><c:out value="${season.release_date}"/></td>
                <td>
                    <form method="POST" action="${contextPath}/admin/deleteSeason/${season.id}">
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
