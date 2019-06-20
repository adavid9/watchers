<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-14
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Update Episodes</title>
</head>
<body>
<div class="container">
    <h1>Update Series</h1>
    <br>
    <c:forEach items="${episodes}" var="episode">
        <table border="0" width="20%">
            <tr><c:out value="Episode id: ${episode.id}"></c:out><br></tr>
            <tr><c:out value="Episode title: ${episode.title}"></c:out><br></tr>
            <tr><c:out value="Episode description: ${episode.description}"></c:out><br></tr>
            <tr><c:out value="Episode release: ${episode.release_date}"></c:out><br></tr>
        </table>
        <form action="${contextPath}/admin/updateEpisode/${episode.id}" method="POST">
            <button type="submit">Update Episode</button>
        </form>
    </c:forEach>
    <c:if test="${msgError != null}">
        <span>
            <c:out value="${msgError}"></c:out>
        </span>
    </c:if>

    <c:if test="${msgSuccess != null}">
        <span>
            <c:out value="${msgSuccess}"></c:out>
        </span>
    </c:if>
</div>
</body>
</html>
