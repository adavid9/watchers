<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Add Movie</title>
</head>
<body>
<div class="container">
    <h1>Add movie:</h1>
    <form action="${contextPath}/addMovie" method="POST">
        <table border="0" width="20%">
            <tr>Title:</tr>
            <br>
            <tr><input type="text" name="title"/></tr>
            <br>
            <tr>Description:</tr>
            <br>
            <textarea name="description" cols="50" rows="5" style="resize: none"></textarea><br>
            <tr>Release Date:</tr>
            <br>
            <tr><input type="date" name="release_date"/></tr>
            <br>
            <tr>Country:</tr>
            <br>
            <tr><input type="text" name="country"/></tr>
            <br>
            <tr>Category:</tr>
            <br>
            <tr><input type="text" name="category"/></tr>
            <br>
            <tr>
                <button type="submit">Submit</button>
            </tr>
        </table>
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
