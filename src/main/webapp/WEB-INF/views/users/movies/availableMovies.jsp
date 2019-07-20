<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-21
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - All Available Movies</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h1>List of available movies</h1>
    <p>Hit the add movie button to add a movie to your movie list.</p>
    <hr>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Release Date</th>
                <th scope="col">Country</th>
                <th scope="col">Category</th>
                <th scope="col">Rate</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${movies}" var="movie">
                <tr>
                    <td><c:out value="${movie.title}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${movie.description.equals('')}">
                                -
                            </c:when>
                            <c:otherwise>
                                <c:out value="${movie.description}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${movie.release_date.equals('')}">
                                -
                            </c:when>
                            <c:otherwise>
                                <c:out value="${movie.release_date}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${movie.country.equals('')}">
                                -
                            </c:when>
                            <c:otherwise>
                                <c:out value="${movie.country}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${movie.category.type.equals('')}">
                                -
                            </c:when>
                            <c:otherwise>
                                <c:out value="${movie.category.type}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${movie.rate}"/></td>
                    <td>
                        <form action="${contextPath}/availableMovies/${movie.id}" method="POST" id="add">
                            <button type="submit" class="btn btn-default">Add Movie</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${msgError != null}">
            <div class="alert alert-danger">
                <c:out value="${msgError}"></c:out>
            </div>
        </c:if>
        <c:if test="${msgSuccess != null}">
            <div class="alert alert-success">
                <c:out value="${msgSuccess}"></c:out>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
