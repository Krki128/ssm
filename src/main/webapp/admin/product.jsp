<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>product</title>
    <script type="text/javascript">
    if ("${msg}" != "") {
        alert("${msg}");
    }
    </script>
    <c:remove var="msg"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bright.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

    <script type="text/javascript">
        //全选复选框功能实现
        function allClick() {
            //获得当前点击后全选按钮的状态
            var flag = $("#all").prop("checked");
            //将此状态赋值给下面五个复选框
            $("input[name='ck']").each(function () {
                this.checked = flag;
            });
        }

        //单个复选框点击改变全选复选框功能实现
        function ckClick() {
            //得到下面五个复选框的个数
            var fiveLength = $("input[name='ck']").length;
            //得到下面五个复选框被选中的个数
            var checkedLength = $("input[name='ck']:checked").length;
            //进行对比,改变全选复选框的状态
            if(fiveLength == checkedLength){
                $("#all").prop("checked",true);
            }else{
                $("#all").prop("checked",false);
            }
        }

        //批量删除
        function deleteBatch() {
            //得到所有选中复选框的对象,根据其长度判断是否有选中商品
            var cks = $("input[name='ck']:checked");  //1,4,5
            //如果没有选中的商品
            if(cks.length == 0){
                alert("请先选择将要删除的商品!");
            }else{
                var str = "";
                var pid = "";
                if(confirm("您确定要删除"+cks.length+"条商品吗?")){
                    //获取其value的值,进行字符串拼接
                    $.each(cks,function () {
                        pid = $(this).val();
                        //进行非空判断,避免出错
                        if(pid != null){
                            str += pid+",";  //145   ===>1,4,5,
                        }
                    });
                    //发送ajax请求,进行批量删除的提交
                    $.ajax({
                        url:"${pageContext.request.contextPath}/prod/deleteBatch.action",
                        data:{"pids":str},
                        type:"post",
                        dataType:"text",
                        success:function (msg) {
                            alert(msg);//批量删除成功!失败!不可删除!
                            //将页面上显示商品数据的容器重新加载
                            $("#table").load("http://localhost:8080/admin/product.jsp #table");
                        }
                    });
                }
            }
        }

        //单个删除
        function del(pId,pageNum) {
            //弹框提示
            if (confirm("您确定删除吗?")) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/product/deleteProduct",
                    //data: {"pid": pid,"pageNum": pageNum,"pname":pname,"typeid":typeid,"lprice":lprice,"hprice":hprice},
                    data: {"pId": pId,"pageNum": pageNum},
                    type: "post",
                    dataType: "text",
                    success: function (msg) {
                        alert(msg);
                        $("#table").load("http://localhost:8080/admin/product.jsp #table");
                    }
                });
            }
        }

        //跳转更新jsp
        function update(pId) {
            /*取出查询条件
        var pname = $("#pname").val();
        var typeid = $("#typeid").val();
        var lprice = $("#lprice").val();
        var hprice = $("#hprice").val();
         */
            //向服务器提交请求,传递商品id
            let string="pId="+pId+"&pageNum="+${info.pageNum};
            location.href = "${pageContext.request.contextPath}/product/updateProduct?"+string;
        }

        //分页的AJAX实现
        function ajaxsplit(pageNum) {
            //向服务发出ajax请求,请示page页中的所有数据,在当前页面上局部刷新显示
            $.ajax({
                url: "${pageContext.request.contextPath}/product/ajaxSplitPaging",
                data: {"pageNum": pageNum},
                type: "post",
                success: function () {
                    //重新加载显示分页数据的容器
                    $("#table").load("http://localhost:8080/admin/product.jsp #table");
                }
            });
        }

        //条件查询
        function condition() {
            //取出查询条件
            var pname = $("#pname").val();
            var typeid = $("#typeid").val();
            var lprice = $("#lprice").val();
            var hprice = $("#hprice").val();
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/product/ajaxCondition",
                data:{"pname":pname,"typeid":typeid,"lprice":lprice,"hprice":hprice},
                success:function () {
                    //刷新显示数据的容器
                    $("#table").load("http://localhost:8080/admin/product.jsp #table");
                }
            });
        }
    </script>
</head>
<body>
<div id="brall">
    <!--商品管理>商品列表-->
    <div id="nav">
        <p>商品管理>商品列表</p>
    </div>
    <!--筛选条件-->
    <div id="condition" style="text-align: center">
        <form id="myform">
            商品名称：<input name="pname" id="pname">
            商品类型：<select name="typeid" id="typeid">
                <option value="-1">请选择</option>
                <c:forEach items="${typeList}" var="pt">
                    <option value="${pt.typeId}">${pt.typeName}</option>
                </c:forEach>
            </select>
            价格：<input name="lprice" id="lprice">-<input name="hprice" id="hprice">
            <input type="button" value="查询" onclick="condition()">
        </form>
    </div><br>
    <!--显示商品-->
    <div id="table">
        <c:choose>
            <c:when test="${info.list.size()!=0}">
                <div id="top">
                    <!--全选按钮-->
                    <input type="checkbox" id="all" onclick="allClick()" style="margin-left: 50px">&nbsp;&nbsp;全选
                    <!--新增和删除-->
                    <a href="${pageContext.request.contextPath}/product/addProduct?pageNum=${info.pageNum}">
                        <input type="button" class="btn btn-warning" id="btn1" value="新增商品">
                    </a>
                    <input type="button" class="btn btn-warning" id="btn2" value="批量删除" onclick="deleteBatch()">
                </div>
                <div id="middle">
                    <!--显示分页后的商品-->
                    <table class="table table-bordered table-striped">
                        <tr>
                            <th></th>
                            <th>商品名</th>
                            <th>商品介绍</th>
                            <th>定价（元）</th>
                            <th>商品图片</th>
                            <th>商品数量</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${info.list}" var="p">
                            <tr>
                                <td valign="center" align="center">
                                    <input type="checkbox" name="ck" id="ck" value="${p.pId}" onclick="ckClick()"></td>
                                <td>${p.pName}</td>
                                <td>${p.pContent}</td>
                                <td>${p.pPrice}</td>
                                <td><img width="55px" height="45px"
                                         src="${pageContext.request.contextPath}/image_big/${p.pImage}"></td>
                                <td>${p.pNumber}</td>
                                    <%--<td><a href="${pageContext.request.contextPath}/admin/product?flag=delete&pid=${p.pId}" onclick="return confirm('确定删除吗？')">删除</a>--%>
                                    <%--&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/product?flag=one&pid=${p.pId}">修改</a></td>--%>
                                <td>
                                    <button type="button" class="btn btn-info "
                                            onclick="update(${p.pId})">编辑
                                    </button>
                                    <button type="button" class="btn btn-warning" id="mydel"
                                            onclick="del(${p.pId},${info.pageNum})">删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <!--分页栏-->
                    <div id="bottom">
                        <div>
                            <nav aria-label="..." style="text-align:center;">
                                <ul class="pagination">
                                    <!--后退按钮-->
                                    <li><%--<a href="${pageContext.request.contextPath}/prod/split.action?page=${.prePage}" aria-label="Previous">--%>
                                        <a href="javascript:ajaxsplit(${info.prePage})" aria-label="Previous">
                                            <span aria-hidden="true">«</span></a>
                                    </li>
                                    <!--循环显示所以的页面数字链接-->
                                    <c:forEach begin="1" end="${info.pages}" var="i">
                                        <c:if test="${info.pageNum==i}">
                                            <li><%--<a href="${pageContext.request.contextPath}/prod/split.action?page=${i}" style="background-color: grey">${i}</a>--%>
                                                <a href="javascript:ajaxsplit(${i})"
                                                   style="background-color: grey">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${info.pageNum!=i}">
                                            <li><%--<a href="${pageContext.request.contextPath}/prod/split.action?page=${i}">${i}</a>--%>
                                                <a href="javascript:ajaxsplit(${i})">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <!--前进按钮-->
                                    <li><%--<a href="${pageContext.request.contextPath}/prod/split.action?page=1" aria-label="Next">--%>
                                        <a href="javascript:ajaxsplit(${info.nextPage})" aria-label="Next">
                                            <span aria-hidden="true">»</span></a>
                                    </li>
                                    <li style=" margin-left:150px;color: #0e90d2;height: 35px; line-height: 35px;">总共&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pages}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${info.pageNum!=0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pageNum}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                        <c:if test="${info.pageNum==0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">1</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </c:when>

            <c:otherwise>
                <div>
                    <h2 style="width:1200px; text-align: center;color: orangered;margin-top: 100px">
                        暂时没有符合条件的商品！</h2>
                </div>
            </c:otherwise>

        </c:choose>
    </div>
</div>
</body>
</html>
