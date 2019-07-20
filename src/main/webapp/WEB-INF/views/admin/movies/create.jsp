<%@ page import="com.dawidantecki.watchers.data.entity.enums.Category" %>
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
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.45/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Add movie</h2>
    <hr>
    <form action="${contextPath}/admin/addMovie" method="POST">
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" name="title" class="form-control" id="title"/>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea name="description" class="form-control" id="description" style="resize: none"></textarea>
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
            <label for="rate">Rate</label>
            <input type="number" step="any" name="rate" class="form-control" id="rate"/>
        </div>
        <button type="submit" class="btn btn-success">Save</button>
    </form>
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
