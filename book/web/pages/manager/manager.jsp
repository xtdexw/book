<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>后台管理</title>
    <link type="text/css" rel="stylesheet" href="static/css/style.css">

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>

</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>
<jsp:include page="header.jsp">
    <jsp:param name="msg" value="后台管理系统"/>
</jsp:include>

<div id="main">
    <h1>欢迎管理员进入后台管理系统</h1>
</div>

<%@include file="/pages/common/bottom.jsp" %>

</body>
</html>