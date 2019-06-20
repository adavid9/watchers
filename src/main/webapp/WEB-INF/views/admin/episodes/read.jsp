<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - All Episodes</title>
</head>
<body>
<div class="container">
    <h2>All Episodes:</h2>
    <c:forEach items="${episodes}" var="episode">
        <table border="0" width="20%">
            <tr><c:out value="id: ${episode.id}"></c:out><br></tr>
            <tr><c:out value="title: ${episode.title}"></c:out><br></tr>
            <tr><c:out value="description: ${episode.description}"></c:out><br></tr>
            <tr><c:out value="release: ${episode.release_date}"></c:out><br></tr>
            <form method="POST" action="${contextPath}/admin/readEpisode/${episode.id}">
                <button type="submit">More Info</button>
            </form>
        </table>
    </c:forEach>
</div>
</body>
</html>
