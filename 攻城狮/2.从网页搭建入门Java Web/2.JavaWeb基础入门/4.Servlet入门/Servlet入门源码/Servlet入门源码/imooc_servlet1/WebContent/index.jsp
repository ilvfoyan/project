<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String basePath = request.getScheme()+":"+"//"+request.getServerName()+":"+request.getServerPort()+"/"
	+request.getServletContext().getContextPath()+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>欢迎登录</title>
<link href="css/login.css" rel="stylesheet">
</head>

<!--  <script>
 function login(){
 	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	if(username == "q" && password == "123"){
		self.location = "index.html";
	}else{
		alert("用户名或密码错误！");
	}
 }
 
 </script> -->
<body>
	<!--面板-->
	<div id="panel">
		<!--面板的头部-->
		<div class="panel-top">
			<h2 align="center">欢迎登录系统</h2>
		</div>
		<!--面板的主要内容-->
		<div class="panel-content" align="center">
			<%
    Object message = request.getAttribute("message");
   if(message!=null && !"".equals(message)){

  %>
			<script type="text/javascript">
          alert("<%=message%>");
     </script>
			<%}%>
			<%-- <form action="<%=basePath %>LoginServlet" method="post">  --%>
			<form action="http://localhost:8080/imooc_servlet1/LoginServlet"
				method="post">
				<div class="int">
					<label><img src="images/user.png" width="26" height="26"></label>
					<input align="absmiddle" type="text" id="username" name="username"
						placeholder="请输入用户名">
				</div>
				<div class="int">
					<label><img src="images/password.png" width="28"
						height="28"></label> <input type="password" id="password"
						name="password" placeholder="请输入密码">
				</div>

				<!--配置信息-->
				<!--<div class="setting">            
<a href="#" class="auto-login">
<input type="checkbox">下次自动登录
</a>           
 <a href="#" class="forget-pwd">忘记密码</a>        
</div>     -->
				<div class="login">
					<button>登录</button>
				</div>
				<div class="reg">
					还没账号?<a href="register.jsp">马上注册</a>
				</div>
		</div>
		</form>
		<!--面板的底部-->
		<!--  <div class="panel-footer">        
<span>社交账号登录:</span>        
<a href="#"><img src="images/sina.png"> </a>       
<a href="#"><img src="images/qq.png">   </a>    
 <a href="#"><img src="images/wechat.png">  </a>  
</div> -->
	</div>
</body>
</html>