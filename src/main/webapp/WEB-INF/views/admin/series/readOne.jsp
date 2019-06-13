<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
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
</head>
<body>
<div class="container">
        <table border="0" width="20%">
            <tr><c:out value="Series id: ${series.id}"></c:out><br></tr>
            <tr><c:out value="Series title: ${series.title}"></c:out><br></tr>
            <tr><c:out value="Series description: ${series.description}"></c:out><br></tr>
            <tr><c:out value="Series country: ${series.country}"></c:out><br></tr>
            <tr><c:out value="Series director: ${series.director}"></c:out><br></tr>
            <tr><c:out value="Series release: ${series.release_date}"></c:out><br></tr>
            <c:forEach items="${seasons}" var="season">
                <c:if test="${season.series.id == series.id}">
                    <tr><c:out value="Season name: ${season.name}"></c:out><br></tr>
                    <tr><c:out value="Season episodes number: ${season.episodesNo}"></c:out><br></tr>
                    <tr><c:out value="Season relase: ${season.release_date}"></c:out><br></tr>
                </c:if>
                <c:forEach items="${episodes}" var="episode">
                    <c:if test="${episode.season.id == season.id}">
                        <tr><c:out value="Episode title: ${episode.title}"></c:out></tr>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </table>
        <c:if test="${msgError != null}">
            <span><c:out value="${msgError}"></c:out></span>
        </c:if>
</div>
</body>
</html>