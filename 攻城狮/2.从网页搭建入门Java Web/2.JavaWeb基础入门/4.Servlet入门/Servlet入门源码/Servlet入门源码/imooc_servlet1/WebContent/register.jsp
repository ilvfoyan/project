<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String basePath = request.getScheme()+":"+"//"+request.getServerName()+":"+request.getServerPort()+"/"
	+request.getServletContext().getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
<title>注册页面</title>

<link rel="stylesheet" href="css/register.css">

</head>

<body>
	<div align="left">
		<button onclick="location='index.jsp'">返回</button>
	</div>
	<div id="panel">
		<%
    Object message = request.getAttribute("message");
   if(message!=null && !"".equals(message)){

  %>
	 <script type="text/javascript">
          alert("<%=message%>");
     </script>
		<%} %>
		<form action="<%=basePath %>RegisterServlet" method="post">
			<table align="left" width="280px">
				<h2>欢迎来到注册页面</h2>
				<tr>
					<td align="right">用户名:</td>
					<td><input type="text" id="username"
						name="username" style="width: 160px;"></td>
				</tr>

				<tr>
					<td align="right">密码:</td>
					<td><input type="password" id="password1" name="password1"
						style="width: 160px;"></td>
				</tr>
				<tr>
					<td align="right">确认密码:</td>
					<td><input type="password" id="password2" name="password2"
						style="width: 160px;"></td>

				</tr>
			</table>

			<div align="center">
				<tr>
					<td colspan="2" bordercolor="#FF00FF"><button
							onClick="register()">注册</button></td>
					<td colspan="2"><button name="reset" type="reset">重置</button></td>

				</tr>
			</div>
			</table>
		</form>
	</div>
</body>
</html>
