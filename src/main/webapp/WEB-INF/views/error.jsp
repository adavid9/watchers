<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<%--
  Created by IntelliJ IDEA.
  User: adavid
  Date: 2019-07-14
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Watchers - Error Page</title>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
</head>
<body>
    <div class="container">
        <h3 class="alert alert-danger">
            Something went wrong! Error Code = <c:out value="${errorCode}" />
        </h3>
        <pre>
            <c:out value="${errorMsg}" />
        </pre>
        <br>
        <a href="${contextPath}/welcome">
            <button type="button" class="btn btn-default">Back</button>
        </a>
    </div>
</body>
</html>
