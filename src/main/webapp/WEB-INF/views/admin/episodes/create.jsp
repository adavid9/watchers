<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-13
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Watchers - Create Episode</title>
</head>
<body>
    <div class="container">
        <form method="POST" action="${contextPath}/admin/addEpisode">
            <h1>Add Episode:</h1>
            <p>Title:<br>
                <input type="text" name="title"/>
            </p>
            <p>Description<br>
                <input type="text" name="description"/>
            </p>
            <p>Release:<br>
                <input type="date" name="release_date"/>
            </p>
            <p>Season name:
                <input type="text" name="seasonName"/>
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
