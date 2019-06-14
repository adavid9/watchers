<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-14
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Update Season</title>
</head>
<body>
<div class="container">
    <h1>Update Season</h1>
    <br>
    <c:forEach items="${seasons}" var="season">
        <table border="0" width="20%">
            <tr><c:out value="Season id: ${season.id}"></c:out><br></tr>
            <tr><c:out value="Season title: ${season.name}"></c:out><br></tr>
            <tr><c:out value="Season release: ${season.release_date}"></c:out><br></tr>
        </table>
        <form action="${contextPath}/updateSeason/${season.id}" method="POST">
            <button type="submit">Update Season</button>
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
