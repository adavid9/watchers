<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-06-11
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Create Series</title>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/admin/addSeries">
        <h2>Add a series</h2>
        <p>
            Title:
            <input type="text" name="title"/>
        </p>
        <p>
            Description:</br>
            <textarea name="description" style="resize:none" rows="5" cols="50" placeholder="Description..."></textarea>
        </p>
        <p>
            Country:
            <input type="text" name="country"/>
        </p>
        <p>
            Director:
            <input type="text" name="director"/>
        </p>
        <p>
            Release:
            <input type="date" name="release_date"/>
        </p>

        <c:if test="${msgError != null}">
            <p><c:out value="${msgError}"/></p>
        </c:if>

        <c:if test="${msgSuccess != null}">
            <p><c:out value="${msgSuccess}"/></p>
        </c:if>

        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
