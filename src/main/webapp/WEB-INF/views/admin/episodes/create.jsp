<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.45/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Add Episode</h1>
    <hr>
    <form method="POST" action="${contextPath}/admin/addEpisode">
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title"/>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea style="resize: none" class="form-control" id="description" name="description"></textarea>
        </div>
        <div class="form-group">
            <label for="release">Release Date</label>
            <div class='input-group date' id='datetimepicker4'>
                <input type="text" id="release" name="release_date" class="form-control"/>
                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                </span>
            </div>
        </div>
        <div class="form-group">
            <label for="season">Season name</label>
            <input type="text" name="seasonName" class="form-control" id="season"/>
        </div>
        <c:if test="${msgError != null}">
            <div class="alert alert-danger">
                <c:out value="${msgError}"/>
            </div>
        </c:if>
        <c:if test="${msgSuccess != null}">
            <div class="alert alert-success">
                <c:out value="${msgSuccess}"/>
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        $('#datetimepicker4').datetimepicker({
            format: 'YYYY-MM-DD'
        });
    });
</script>
</body>
</html>
