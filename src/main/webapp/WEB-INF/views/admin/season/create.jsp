<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-12
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Create Season</title>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/addSeason">
        <h1>Add Season:</h1>
        <p>Name:<br>
            <input type="text" name="name"/>
        </p>
        <p>Episodes number:<br>
            <input type="text" name="episodes_no"/>
        </p>
        <p>Release:<br>
            <input type="date" name="release_date"/>
        </p>
        <p>Series title:
            <input type="text" name="seriesTitle"/>
        </p>
        <c:if test="${msgError != null}">
                <span>
                    <c:out value="${msgError}"/>
                </span>
        </c:if>
        <c:if test="${msgSuccess != null}">
                <span>
                    <c:out value="${msgSuccess}"/>
                </span>
        </c:if>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
