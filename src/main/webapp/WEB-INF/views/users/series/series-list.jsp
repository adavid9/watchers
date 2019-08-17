<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"  />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-08-17
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Watchers - Available Series</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>List of available series in Watchers</h2>
    <p>If you want add series hit the "add" button</p>
    <hr>
    <c:if test="${msgSuccess != null}">
        <div class="alert alert-success"><c:out value="${msgSuccess}"/></div>
    </c:if>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Category</th>
                <th scope="col">Country</th>
                <th scope="col">Director</th>
                <th scope="col">Release Date</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${series}" var="series">
                <tr>
                    <td><c:out value="${series.title}"/></td>
                    <td><c:out value="${series.description}"/></td>
                    <td><c:out value="${series.category.type}"/></td>
                    <td><c:out value="${series.country}"/></td>
                    <td><c:out value="${series.director}"/></td>
                    <td><c:out value="${series.release_date}"/></td>
                    <td>
                        <form action="${contextPath}/user/series-list/${series.id}" method="POST">
                            <button type="submit" class="btn btn-primary">Add</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button type="button" class="btn btn-default">
        <a href="${contextPath}/userStart">Back</a>
    </button>
</div>
</body>
</html>
