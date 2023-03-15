<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>订单详情</title>
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px">
    <span class="wel_word">订单详情</span>
    <div>
        <span>欢迎<span class="um_span">用户名:${user.username}</span>光临网上书城</span>
        <a href="OrderServlet?action=listOrder">我的订单</a>
        <a href="UserServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </div>
</div>

<div id="main">

    <table>
        <tr>
            <td>订单号</td>
            <td>商品名</td>
            <td>商品数量</td>
            <td>商品单价</td>
            <td>商品总价</td>
        </tr>

        <c:forEach items="${requestScope.orderItem}" var="orderItem">
            <tr>
                <td>${orderItem.orderId}</td>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>

</div>

<%@include file="/pages/common/bottom.jsp" %>

</body>
</html>