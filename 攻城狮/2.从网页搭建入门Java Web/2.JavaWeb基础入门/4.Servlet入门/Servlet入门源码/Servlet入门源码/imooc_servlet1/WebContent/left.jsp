<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	<nav>
	<div>
		<div>
			<ul class="ull">
				<li class="li1">功能菜单</li>
				<!-- 一级菜单 -->
				<li class="lii"><a data-toggle="collapse" href="price.jsp"
					target="main"> <i></i>&nbsp;1.采购报价审批<span class="sr-only">(current)</span></a>
				</li>
				<!-- 二级菜单 -->
				<!-- 注意一级菜单中<a>标签内的href="#……"里面的内容要与二级菜单中<ul>标签内的id="……"里面的内容一致 -->
				<ul id="userMeun" class="nav-list collapse">
					<li><a href="###" onclick="showAtRight('userList.jsp')"><i></i>
							采购报价</a></li>
				</ul>

				<li class="lii"><a data-toggle="collapse" href="apply.jsp"
					target="main"> <i></i>&nbsp;2.采购申请审批 <span class="sr-only">(current)</span></a>
				</li>
				<ul id="productMeun" class="nav-list collapse">
					<li><a href="###" onclick="showAtRight('productList.jsp')"><i></i>
							采购申请</a></li>
				</ul>

				<li class="lii"><a data-toggle="collapse" href="main.jsp"
					target="main"> <i></i>&nbsp;3.采购订单审批 <span class="sr-only">(current)</span></a>
				</li>
				<ul id="recordMeun" class="nav-list collapse">
					<li><a href="###" onclick="showAtRight('recordList.jsp')"><i></i>
							采购订单</a></li>
				</ul>

			</ul>

		</div>
	</div>
	</nav>
</body>
</html>