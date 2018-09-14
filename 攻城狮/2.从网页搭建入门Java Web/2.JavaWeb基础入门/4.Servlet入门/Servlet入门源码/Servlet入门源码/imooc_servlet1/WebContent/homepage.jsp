<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jstl/core rt" prefix="c" %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>审批系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <%-- 在IE运行最新的渲染模式 --%> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <%-- 初始化移动浏览显示 --%> -->
<meta name="Author" content="Dreamer-1.">


<title>采购订单审批系统</title>


<!-- 引入各种CSS样式表 -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/index.css">
<!-- 修改自Bootstrap官方Demon，你可以按自己的喜好制定CSS样式 -->
<link rel="stylesheet" href="css/font-change.css">
<!-- 将默认字体从宋体换成微软雅黑（个人比较喜欢微软雅黑，移动端和桌面端显示效果比较接近） -->

<script type="text/javascript" src="js/jquery.sortable.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- <script type="text/javascript" src="js/bootstrap.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script> -->
<link type="text/css" rel="stylesheet" href="css/homepage.css">


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


<script type="text/javascript" src="js/jquery.jqGrid.js"></script>

<!-- 本页面初始化用到的js包，创建jqGrid的代码就在里面 -->
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/bootstrap3-typeahead.js"></script>
<link rel="stylesheet" href="css/bootstrap-datepicker.css" />
<script type="text/javascript" src="js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/homepage.js"></script>






</head>
<body onload="getTime()" style="height:100%;">
	<header>
	<div class="head-left">
		<a href="http://www.hzepower.com/"><img src="images/logo.jpg" /></a>
	</div>
	<div class="head-right"></div>
	</header>
	
	
	
	
	
	

	<div id="div1" style="padding-bottom: 12px;">
		<!-- 显示用户名及登录时间 -->
		<a href="index.jsp" class="out"
			style="text-decoration: none; margin: 0 24px; float: right;">退出</a>
		<div class="time" id="time"
			style="float: right; margin-left: 12px; font-size: 16px; font-family:'微软雅黑';font-weight:bold;"></div>
		<input type="hidden" id="username" value = <%=request.getAttribute("username") %>>
		<a href="#" class="user"
			style="text-decoration: none; float: right; margin-left: 12px; font-size: 16px; font-size: 16px; color: #6495ED; font-family:'
			微软雅黑';font-weight:bold;"> 
			<%
		   Object username = request.getAttribute("username");
		   if(username!=null && !"".equals(username)){
			%> 
  				<%out.println(username);%> 
  			<%} %>
		</a>
	</div>
	<div class="clear"></div>

	<div id="div2">
		<nav>
		<div>
			<div>
				<ul class="ull">
					<li class="li1">功能菜单</li>
					<!-- 一级菜单 -->
					<li class="lii"><a href="#userMeun" data-toggle="collapse">
							<i></i>&nbsp;1.采购报价审批<span class="sr-only">(current)</span>
					</a></li>
					<!-- 二级菜单 -->
					<!-- 注意一级菜单中<a>标签内的href="#……"里面的内容要与二级菜单中<ul>标签内的id="……"里面的内容一致 -->
					<ul id="userMeun" class="nav-list collapse">
						<li><a href="###" onclick="showAtRight('userList.jsp')"><i></i>
								采购报价</a></li>
					</ul>

					<li class="lii"><a href="#productMeun" data-toggle="collapse">
							<i></i>&nbsp;2.采购申请审批 <span class="sr-only">(current)</span>
					</a></li>
					<ul id="productMeun" class="nav-list collapse">
						<li><a href="###" onclick="showAtRight('productList.jsp')"><i></i>
								采购申请</a></li>
					</ul>

					<li class="lii"><a href="#recordMeun" data-toggle="collapse">
							<i></i>&nbsp;3.采购订单审批 <span class="sr-only">(current)</span>
					</a></li>
					<ul id="recordMeun" class="nav-list collapse">
						<li><a href="###" onclick="showAtRight('recordList.jsp')"><i></i>
								采购订单</a></li>
					</ul>

				</ul>

			</div>
		</div>
		</nav>

		<!-- <nav>
          <h3>功能清单</h3>
          <ul>
            <li>1.采购报价审批</li>
            <li>2.采购申请审批</li>
            <li>3.采购订单审批</li>
          </ul>
          
          <table>
              <tr>
                  <td>1.采购报价审批</td>
              </tr>
              <tr>
                  <td>2.采购申请审批</td>
              </tr>
              <tr>
                  <td class="td3">3.采购订单审批</td>
              </tr>
          </table>
      </nav>  -->

		<aside>
		    <!-- <div title="查询条件">
		        <form action="" id="searchForm" name="searchForm" method="post">
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
								<option id="checked" label="已审批" value="已审批" />
								<option id="checking" label="未审批" value="未审批" />
								<option id="all" label="全部" value="全部" />
								</datalist>
							</td>
						</tr>
		                <tr>
		                    <td width="10%">
		                        <a href="#" onclick="query();" id="querylink" class="easyui-linkbutton"
		                           iconCls="icon-search">查询</a>
		                        <a href="#" onclick="setNull();" class="easyui-linkbutton" iconCls="icon-redo">重置</a>
		                    </td>
		
		                </tr>
		
		            </table>
		        </form>
		    </div> -->
    		
		
		
		<div>
			<form action="" id="searchForm" name="searchForm" method="post" onsubmit="return false;">
				<table class="table2">
					<tr>
						<td class="tr-td1" rowspan="4">查询条件</td>
						<td class="td1">采购单号：<input type="text" id="id"/></td>
					</tr>
					<tr>
	
						<td class="td1">物料编码：<input type="text" id="num"/></td>
					</tr>
					<tr>
	
						<td class="date">订单日期：<input type="date" id="date"/></td>
					</tr>
					<tr>
	
						<td>
							<div class="choose">可供选项：</div>
							<form action="" class="checkBox">
								<input type="text" list="url_list" name="text" id="choose"/>
							</form> 
							<datalist id="url_list">
								<option id="checked" label="已审批" value="已审批" />
								<option id="checking" label="未审批" value="未审批" />
								<option id="all" label="全部" value="全部" />
							</datalist>
						</td>
						<td class="search_button">
		                        <a href="#" onclick="" id="querylink" class="linkbutton"
		                           iconCls="icon-search">查询</a>
		                        <a href="#" onclick="" id="reSet" class="linkbutton" iconCls="icon-redo">重置</a>
		                </td>
					</tr>
				</table>
			</form>
		</div>
		
		<!-----用来计算单元格内容实际长度的--------->  
<!-- <div class="ui-jqgrid ui-widget ui-widget-content ui-corner-all" style="position:absolute;top:-9999px">
	<div class="ui-jqgrid-view">  
		<div class="ui-jqgrid-bdiv">
			<div style="position: relative;">
				<table cellspacing="0" cellpadding="0" border="0">
					<tr class="ui-widget-content jqgrow ui-row-ltr" style="table-layout:table">
						<td id="tdCompute" style="background:#eee;width:auto"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>   -->
<!-----用来计算单元格内容实际长度的--------->  
		<div class="table">
			<table id="list2"></table>
			<div id="pager2"></div>
		</div>

		

		<!-- <table id="gridTable"></table>
        <div id="gridPager"></div> -->

		<div class="shenpi">
			<!-- <input type="submit" value="审批" onclick="shenpi()"/> -->
			<button id="bt">审批</button>
		</div>


		<%-- <table id="table1">
              <tr class="tr1">
                  <th></th>
                  <th>采购单号</th>
                  <th>项目名</th>
                  <th>物料编码</th>
                  <th>审批</th>
        	  </tr>
        	  <%
          		CheckDao dao=new CheckDao();
            	List<Check> list =dao.getAllData(); 
            	int i=0;
           		for(Check d:list)
            	{
            	%>
              <tr class="tr1">
              		<td><input type="checkbox" id="ckx" name="ckx" value=t></td>
                  <td><%=d.getId() %></td>
                  <td><%=d.getPname() %></td>
                  <td><%=d.getNum() %></td>
                  <td><%=d.getTf() %></td>
        	</tr>
        	<%i++;}%>
         <%-- </table> --%> <!-- <script>
        	
        		ckx.setAttribute("value","tt");
        	
        </script>  -->
        <!-- <div class="d">
          <div>采购单号</div> --%>
          <div>项目名</div>
          <div>物料编码</div>
          <div>品名规格</div>
          <div>采购数量</div>
          <div>含税单价</div> --> <!-- <div>税率</div>
          <div>付款方式</div>
          <div>供应商名称</div>
          <div>备注</div>
          <div>审批状态</div>
            <div>订单日期</div>
            <div>审批人</div>
            <div>采购人</div> -->
	</div>




	<!-- <table id="table1">
              <tr class="tr1">
                  <th></th>
                  <th>采购单号</th>
                  <th>项目名</th>
                  <th>物料编码</th>
                  <th>品名规格</th>
                  <th>采购数量</th>
                  <th>含税单价</th>
                  <th>税率</th>
                  <th>付款方式</th>
                  <th>供应商名称</th>
                  <th>备注</th>
                  <th>审批状态</th>
                  <th>订单日期</th>
                  <th>审批人</th>
                  <th>采购人</th>
              </tr>
              <tr class="tr1">
                  <td><input type="checkbox" /></td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
              </tr>
              <tr class="tr1">
                  <td><input type="checkbox" /></td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
                  <td>a</td>
              </tr>
          </table> -->
	</aside>
	</div>
	<footer> </footer>
</body>
</html>