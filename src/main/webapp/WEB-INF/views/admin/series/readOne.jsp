<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Series More Info</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>Series more info</h2>
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
            <th scope="col">Release Date</th>
            <th scope="col">Seasons</th>
            <th scope="col">Episodes</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${series.id}"/></td>
            <td><c:out value="${series.title}"/></td>
            <td><c:out value="${series.description}"/></td>
            <td><c:out value="${series.category.type}"/></td>
            <td><c:out value="${series.country}"/></td>
            <td><c:out value="${series.director}"/></td>
            <td><c:out value="${series.release_date}"/></td>
            <td>
                <c:forEach items="${seasons}" var="season">
                    <c:if test="${season.series.id == series.id}">
                        <li><c:out value="${season.name}"/></li>
                    </c:if>
                </c:forEach>
            </td>
            <td>
                <c:forEach items="${episodes}" var="episode">
                    <c:if test="${episode.season.id == season.id}">
                        <li><c:out value="${episode.title}"/></li>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        </tbody>
    </table>
    <c:if test="${msgError != null}">
        <div class="alert alert-danger">
            <c:out value="${msgError}"/>
        </div>
    </c:if>
</div>
</body>
</html>