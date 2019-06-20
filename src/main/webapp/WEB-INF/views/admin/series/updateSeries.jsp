<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Update Selected Series</title>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/admin/updateSeries">
        <table border="0" width="20%">
            <input type="hidden" name="id" value="<c:out value="${series.id}"/>"/>
            <tr>Title:<br></tr>
            <tr><input type="text" name="title" value="<c:out value="${series.title}"/>"/><br></tr>
            <tr>Description:<br></tr>
            <tr>
                <textarea name="description" cols="50" rows="5" style="resize: none"><c:out
                        value="${series.description}"/></textarea><br>
            </tr>
            <tr>Release:<br></tr>
            <tr><input type="date" name="release_date" value="<c:out value="${series.release_date}"/>"/><br></tr>
            <tr>Country:<br></tr>
            <tr><input type="text" name="country" value="<c:out value="${series.country}"/>"/><br></tr>
            <tr>Director:<br></tr>
            <tr><input type="text" name="director" value="<c:out value="${series.director}"/>"/></tr>
            <br>
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
