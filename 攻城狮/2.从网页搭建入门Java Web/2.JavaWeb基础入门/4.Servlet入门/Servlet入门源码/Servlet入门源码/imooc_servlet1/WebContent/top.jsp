<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <%-- 在IE运行最新的渲染模式 --%> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <%-- 初始化移动浏览显示 --%> -->
<meta name="Author" content="Dreamer-1.">

<!-- 引入各种CSS样式表 -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/index.css">
<!-- 修改自Bootstrap官方Demon，你可以按自己的喜好制定CSS样式 -->
<link rel="stylesheet" href="css/font-change.css">
<!-- 将默认字体从宋体换成微软雅黑（个人比较喜欢微软雅黑，移动端和桌面端显示效果比较接近） -->

<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="css/homepage.css">
<link type="text/javascript" rel="stylesheet" href="js/homepage.js">

</head>
<body>
	<header>
	<div class="head-left">
		<a href="http://www.hzepower.com/"><img src="images/logo.jpg" /></a>
	</div>
	<div class="head-right"></div>
	</header>

	<div id="div1">
		<!-- 显示用户名及登录时间 -->
		<%
    Object username = request.getAttribute("username");
   if(username!=null && !"".equals(username)){

  %>
		username;
		<%} %>
		<button onclick="location='index.jsp'">退出</button>
	</div>
	<div class="clear"></div>

</body>
</html>