<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - All Series</title>
</head>
<body>
    <div class="container">
        <c:forEach items="${series}" var="series">
            <table border="0" width="20%">
                <tr><c:out value="Series id: ${series.id}"></c:out><br></tr>
                <tr><c:out value="Series title: ${series.title}"></c:out><br></tr>
                <tr><c:out value="Series description: ${series.description}"></c:out><br></tr>
                <tr><c:out value="Series country: ${series.country}"></c:out><br></tr>
                <tr><c:out value="Series director: ${series.director}"></c:out><br></tr>
                <tr><c:out value="Series release: ${series.release_date}"></c:out><br></tr>
            </table>
            <form action="${contextPath}/admin/readSeries/${series.id}" method="POST">
                <button type="submit">More info</button>
            </form>
        </c:forEach>
    </div>
</body>
</html>
