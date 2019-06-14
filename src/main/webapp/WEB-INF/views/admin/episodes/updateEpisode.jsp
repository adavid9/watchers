<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-14
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Update Selected Episode</title>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/updateEpisode">
        <table border="0" width="20%">
            <input type="hidden" name="id" value="<c:out value="${episode.id}"/>"/>
            <tr>Title:<br></tr>
            <tr><input type="text" name="title" value="<c:out value="${episode.title}"/>"/><br></tr>
            <tr>Description:<br></tr>
            <tr>
                <textarea name="description" cols="50" rows="5" style="resize: none"><c:out
                        value="${episode.description}"/></textarea><br>
            </tr>
            <tr>Release:<br></tr>
            <tr><input type="date" name="release_date" value="<c:out value="${episode.release_date}"/>"/><br></tr>
        </table>
        <button type="submit">Update</button>
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
    </form>
</div>
</body>
</html>
