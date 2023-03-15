<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>书城首页</title>
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <style>
        ::-webkit-scrollbar {
            display: none;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo1.gif" width="230px" height="80px">
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
            <a href="javascript:void(0)" onclick="myshopcart()">购物车</a>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">用户:${user.username}</span>光临书城</span>
            <a href="OrderServlet?action=listOrder">我的订单</a>
            <a href="UserServlet?action=logout">注销</a>
            <a href="pages/cart/cart.jsp">购物车</a>
        </c:if>

        <a href="login.jsp">后台管理</a>

    </div>
</div>
<div id="main">
    <div id="book">

        <div class="book_cond">
            <form action="BookServlet?action=searchPage&pageNo=${requestScope.page.pageNo}" method="post">
                书名：<input type="text" name="name" value="${name}">
                作者：<input type="text" name="author" value="${author}">
                价格：<input id="min" type="text" name="min" value="${min}"> 元 -
                <input id="max" type="text" name="max" value="${max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>

        <div style="text-align: center">
            <c:if test="${empty sessionScope.cart.items}">
                <div>
                    <span style="color: red;">当前购物车为空</span>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.cart.items}">
                <span>您的购物车中有<span style="color: red">${sessionScope.cart.totalCount}</span>件商品</span>
                <div>
                您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
<%--                <c:remove var="lastName" scope="session"></c:remove>--%>
                </div>
            </c:if>
        </div>

        <c:choose>
            <c:when test="${empty page.items}">
                <h1 style="text-align: center;color: red">没有找到数据</h1>
            </c:when>
            <c:otherwise>
                <c:forEach items="${page.items}" var="book">
                    <div class="b_list">
                        <div class="img_div">
                            <img class="book_img" alt="" src="${book.imgPath}"/>
                        </div>
                        <div class="book_info">
                            <div class="book_name">
                                <span class="sp1">书名:</span>
                                <span class="sp2">${book.name}</span>
                            </div>
                            <div class="book_author">
                                <span class="sp1">作者:</span>
                                <span class="sp2">${book.author}</span>
                            </div>
                            <div class="book_price">
                                <span class="sp1">价格:</span>
                                <span class="sp2">￥${book.price}</span>
                            </div>
                            <div class="book_sales">
                                <span class="sp1">销量:</span>
                                <span class="sp2">${book.sales}</span>
                            </div>
                            <div class="book_amount">
                                <span class="sp1">库存:</span>
                                <span class="sp2">${book.stock}</span>
                            </div>
                            <div class="book_add">
                                <input onclick="addToCart('${book.id}')" type="button" value="加入购物车">
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <jsp:include page="/pages/common/page.jsp"></jsp:include>

</div>

<%@include file="/pages/common/bottom.jsp" %>

</body>
</html>
<script>
    function addToCart(bookId) {
        if('${sessionScope.user}' != ''){
            window.location.href = "CartServlet?action=addItem&id=" + bookId;
        }else {
            window.alert("请登入再加入购物车!");
            window.location.href = "pages/user/login.jsp";
        }
    }

    function myshopcart() {
        window.alert("请登入再查看购物车!");
        window.location.href = "pages/user/login.jsp";
    }


</script>