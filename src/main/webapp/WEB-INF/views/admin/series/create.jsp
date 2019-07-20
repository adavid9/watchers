<%@ page import="com.dawidantecki.watchers.data.entity.enums.Category" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.45/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Add Series</h2>
    <hr>
    <form method="POST" action="${contextPath}/admin/addSeries">
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" name="title" class="form-control" id="title"/>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea name="description" style="resize:none" id="description" class="form-control"></textarea>
        </div>
        <div class="form-group">
            <label for="category">Category</label>
            <select name="category" id="category" class="form-control">
                <c:forEach items="${Category.values()}" var="category">
                    <option><c:out value="${category.type}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="country">Country</label>
            <input type="text" name="country" id="country" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="director">Director</label>
            <input type="text" id="director" class="form-control" name="director"/>
        </div>
        <div class="form-group">
            <label for="release">Release Date</label>
            <div class='input-group date' id='datetimepicker4'>
                <input type="text" id="release" name="release_date" class="form-control"/>
                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                </span>
            </div>
        </div>

        <c:if test="${msgError != null}">
            <div class="alert alert-danger">
                <c:out value="${msgError}"/><
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
