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
<script type="text/javascript">
    function shenpi(){
      confirm("确认审批？");
    }
  </script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jqGrid组件基础样式包-必要 -->
<link rel="stylesheet" href="jqgrid/css/ui.jqgrid.css" />

<!-- jqGrid主题包-非必要 -->
<!-- 在jqgrid/css/css这个目录下还有其他的主题包，可以尝试更换看效果 -->
<link rel="stylesheet"
	href="jqgrid/css/css/redmond/jquery-ui-1.8.16.custom.css" />

<!-- jquery插件包-必要 -->
<!-- 这个是所有jquery插件的基础，首先第一个引入 -->
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>

<!-- jqGrid插件包-必要 -->
<script type="text/javascript" src="jqgrid/js/jquery.jqGrid.src.js"></script>

<!-- jqGrid插件的多语言包-非必要 -->
<!-- 在jqgrid/js/i18n下还有其他的多语言包，可以尝试更换看效果 -->
<script type="text/javascript" src="jqgrid/js/i18n/grid.locale-cn.js"></script>
<title>http://blog.mn886.net/jqGrid</title>

<!-- 本页面初始化用到的js包，创建jqGrid的代码就在里面 -->
<script type="text/javascript" src="js/index.js"></script>
</head>
<body style="background-color: #CAE1FF;">
	<aside>
	<div>
		<table class="table2">
			<tr>
				<td class="tr-td1" rowspan="4">查询</td>
				<td class="td1">采购单号：<input type="text" /></td>
			</tr>
			<tr>

				<td class="td1">物料编码：<input type="text" /></td>
			</tr>
			<tr>

				<td class="date">订单日期：<input type="date" /></td>
			</tr>
			<tr>

				<td>
					<div class="choose">可供选项：</div>
					<form action="">
						<input type="text" list="url_list" name="text" />
					</form> <datalist id="url_list">
					<option label="已审批" value="已审批" />
					<option label="未审批" value="未审批" />
					<option label="全部" value="全部" />
					</datalist>
			</tr>
		</table>
	</div>

	<table id="list2"></table>
	<div id="pager2"></div>

	<div class="shenpi">
		<!-- <input type="submit" value="审批" onclick="shenpi()"/> -->
		<button onclick="shenpi()">审批</button>
	</div>
	</aside>
</body>
</html>