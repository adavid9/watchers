<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
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
</head>
<body>
    <div class="container">
        <h1>All Seasons</h1>
        <c:forEach items="${seasons}" var="season">
            <table border="0" width="20%">
                <tr><c:out value="id: ${season.id}"></c:out><br></tr>
                <tr><c:out value="name: ${season.name}"></c:out><br></tr>
                <tr><c:out value="episode number: ${season.episodesNo}"></c:out><br></tr>
                <tr><c:out value="release: ${season.release_date}"></c:out><br></tr>
            </table>
            <form action="${contextPath}/readSeason/${season.id}" method="POST">
                <button type="submit">More Info</button>
            </form>
        </c:forEach>
    </div>
</body>
</html>
