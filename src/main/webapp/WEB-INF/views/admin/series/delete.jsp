<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-12
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Delete Series</title>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/admin/deleteSeries">
        <p>Title:<br>
            <input type="text" name="title"/>
        </p>
        <button type="submit">Delete</button>
    </form>
    <c:if test="${msgError != null}">
        <span><c:out value="${msgError}"/></span>
    </c:if>
    <c:if test="${msgSuccess != null}">
            <span>
                <c:out value="${msgSuccess}"/>
            </span>
    </c:if>
    <br><br>
    <c:forEach items="${series}" var="s">
        <table border="0" width="20%">
            <tr>
                <c:out value="Series id: ${s.id}"/><br>
            </tr>
            <tr>
                <c:out value="Series title: ${s.title}"/><br>
            </tr>
            <tr>
                <c:out value="Series description: ${s.description}" /><br>
            </tr>
            <tr>
                <c:out value="Series country: ${s.country}" /><br>
            </tr>
            <tr>
                <c:out value="Series director: ${s.director}" /><br>
            </tr>
            <c:forEach items="${s.seasons}" var="seasons">
                <tr>
                    <c:out value="Season title: ${seasons.name}" /><br>
                </tr>
                <tr>
                    <c:out value="Season espidoesNo: ${seasons.episodesNo}" /><br>
                </tr>
                <tr>
                    <c:out value="Season release: ${seasons.release_date}" /><br>
                </tr>
            </c:forEach>
            <form action="${contextPath}/admin/deleteSeries/${s.id}" method="POST">
                <button type="submit">Delete</button>
            </form>
            <c:if test="${msg} != null">
                <span>
                    <c:out value="${msg}" />
                </span>
            </c:if>
        </table>
    </c:forEach>
</div>

</body>
</html>
