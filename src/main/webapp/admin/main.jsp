<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js/"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<title>main</title>
</head>
<body>
<!--整体部分-->
<div id="all">
	<!--上部分-->
	<div id="top">
		<div id="top1">
			<span>商品管理系统</span>
		</div>
		<div id="top2"></div>
		<div id="top3">
			<span>欢迎您，${username}</span>
		</div>
	</div>
	<!--下部分-->
	<div id="bottom">
		<!--下部分左边-->
		<div id="bleft">
			<div id="ltop">
				<div id="lts">
					<img src="${pageContext.request.contextPath}/images/logo.jpg" /><br>
				</div>
			</div>
			<div id="lbottom">
				<ul>
					<a href="${pageContext.request.contextPath}/product/split" target="myright" >
						<li class="two"><span class="glyphicon glyphicon-book" style="color: white;"/> 商品管理 <span class="glyphicon glyphicon-play" style="color: white;"/> </li>
					</a>
					<a href="${pageContext.request.contextPath}/admin/err.jsp" target="myright">
						<li class="one"><span class="glyphicon glyphicon-sort" style="color: white;"/> 订单管理 <span class="glyphicon glyphicon-play" style="color: white;"/> </li>
					</a>
					<a href="${pageContext.request.contextPath}/admin/err.jsp" target="myright">
						<li class="one"><span class="glyphicon glyphicon-user" style="color: white;"/> 用户管理 <span class="glyphicon glyphicon-play" style="color: white;"/> </li>
					</a>
					<a href="${pageContext.request.contextPath}/admin/err.jsp" target="myright">
						<li class="one"><span class="glyphicon glyphicon-bullhorn" style="color: white"/> 通知公告 <span class="glyphicon glyphicon-play" style="color: white;"/> </li>
					</a>
				</ul>
			</div>
		</div>
		<!--下部分右边-->
		<div id="bright">
			<iframe frameborder="0" scrolling="yes" name="myright" width="1235px" height="700px" />
		</div>
	</div>
</div>
</body>
</html>