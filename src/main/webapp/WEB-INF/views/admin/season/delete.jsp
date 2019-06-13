<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-12
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Delete Season</title>
</head>
<body>
    <div class="container">
        <form action="${contextPath}/deleteSeason" method="POST">
            <h1>Delete season:</h1>
            <p>Name:<br>
                <input type="text" name="name" />
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
        <c:forEach items="${seasons}" var="season">
            <table border="0" width="20%">
                <tr>
                    <c:out value="Season id: ${season.id}" /><br>
                </tr>
                <tr>
                    <c:out value="Season name: ${season.name}" /><br>
                </tr>
                <tr>
                    <c:out value="Season espisodes: ${season.episodesNo}" /><br>
                </tr>
                <tr>
                    <c:out value="Season release: ${season.release_date}" /><br>
                </tr>
                <form method="POST" action="${contextPath}/deleteSeason/${season.id}">
                    <button type="submit">Delete</button>
                </form>
            </table>
        </c:forEach>
    </div>
</body>
</html>
