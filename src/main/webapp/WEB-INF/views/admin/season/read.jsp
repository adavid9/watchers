<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - All Seasons</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2>All Seasons</h2>
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
                    <form action="${contextPath}/admin/readSeason/${season.id}" method="POST">
                        <button type="submit" class="btn btn-default">More Info</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
