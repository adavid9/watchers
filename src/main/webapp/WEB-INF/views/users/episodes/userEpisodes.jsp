<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-08-17
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Watchers - User Episodes</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <h2><%=SecurityContextHolder.getContext().getAuthentication().getName()%> series
        list.</h2>
    <hr>
    <c:if test="${msgSuccess != null}">
        <div class="alert alert-success"><c:out value="${msgSuccess}"/></div>
    </c:if>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Release Date</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${episodes}" var="episode">
            <tr>
                <td><c:out value="${episode.title}"/></td>
                <td><c:out value="${episode.description}"/></td>
                <td><c:out value="${episode.release_date}"/></td>
                <td>
                    <form method="POST" action="${contextPath}/user/episodes/${episode.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
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
