<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Watchers - Delete Episode</title>
</head>
<body>
    <div class="container">
        <form method="POST" action="${contextPath}/deleteEpisode">
            <h1>Delete episode by title:</h1>
            <input type="text" name="title" />
            <button type="submit">Delete</button>
        </form>
        <br><br>
        <h1>Delete episode by button:</h1>
        <c:forEach items="${episodes}" var="episode">
            <table border="0" width="20%">
                <tr><c:out value="Episode id: ${episode.id}"></c:out><br></tr>
                <tr><c:out value="Episode title: ${episode.title}"></c:out><br></tr>
                <tr><c:out value="Episode description: ${episode.description}"></c:out><br></tr>
                <tr><c:out value="Episode release: ${episode.release_date}"></c:out><br></tr>
                <form method="POST" action="${contextPath}/deleteEpisode/${episode.id}">
                    <button type="submit">Delete</button>
                </form>
            </table>
        </c:forEach>
    </div>
</body>
</html>
