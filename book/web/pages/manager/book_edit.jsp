<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>编辑图书</title>
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>
<jsp:include page="header.jsp">
    <jsp:param name="msg" value="编辑图书"/>
</jsp:include>

<div id="main">
    <form action="BookServlet?action=updateBookById&id=${book.id}" method="post" enctype="multipart/form-data">
                   <input name="pageNo" type="hidden" value="${pageNo}">
        <table>
            <tr>
                <td>名称</td>
                <td><input name="name" type="text" value="${book.name}"/></td>
            </tr>
            <tr>
                <td>价格</td>
                <td><input name="price" type="text" value="${book.price}"/></td>
            </tr>
            <tr>
                <td>作者</td>
                <td><input name="author" type="text" value="${book.author}"/></td>
            </tr>
            <tr>
                <td>销量</td>
                <td><input name="sales" type="text" value="${book.sales}"/></td>
            </tr>
            <tr>
                <td>库存</td>
                <td><input name="stock" type="text" value="${book.stock}"/></td>
            </tr>
            <tr>
                <td>封面</td>
                <td>
                    <input name="imgPath" type="file" id="file_input" onchange="show_image()"/>
                </td>
                <td>
                    <img  src="${book.imgPath}" alt="" id="show_img" width="100px" height="100px"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>

<%@include file="/pages/common/bottom.jsp" %>

</body>
</html>
<script>
    function show_image() {
        //抓取到上传图片的input标签的信息
        file_input = document.getElementById("file_input");
        //抓取到需要展示预览图的img标签信息
        show_img = document.getElementById("show_img");
        //回去预览图的src属性信息
        show_img.src = window.URL.createObjectURL(file_input.files[0]);
        //改变style属性中block的值
        show_img.style.display = 'block';
    }
</script>