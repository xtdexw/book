<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>图书后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="static/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="static/css/misc-pages.css">
</head>
<body class="simple-page">
<div class="simple-page-wrap">
    <div class="simple-page-logo ">
        <a href="">
            <span></span>
            <span>图书后台管理系统</span>
        </a>
    </div><!-- logo -->

    <div class="simple-page-form" id="login-form">
        <h4 class="form-title m-b-xl text-center">
            <c:out value="${requestScope.msgtop}" default="请输入用户名和密码"></c:out>
        </h4>
        <form action="AdminServlet?action=login" method="post">
<%--            <input type="hidden" value="login" name="action">--%>
            <div class="form-group">
                <input type="text" name="username" class="form-control" placeholder="用户名" value="${requestScope.username}">
            </div>

            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="密码" value="${requestScope.password}">
            </div>

            <input type="submit" class="btn btn-primary" value="登录">
        </form>
    </div>
</div>

</body>
</html>