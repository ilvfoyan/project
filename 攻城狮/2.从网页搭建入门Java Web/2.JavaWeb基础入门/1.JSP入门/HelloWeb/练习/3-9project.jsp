<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>由x求y值</title>
</head>
<body>
	<%! 
		int x=-5;
		int y=0;
	 %>
	<% if(x<0) {%>
	<div style="text-align: center;">
		<div>当x<0时，输出</div>
		<div>x=<%=x%></div>
		<div>y=<%=-1%></div>
	</div>
	<%}%>
	<% if(x>0) {%>
	<div style="text-align: center;">
		<div>当x>0时，输出</div>
		<div>x=<%=x%></div>
		<div>y=<%=-1%></div>
	</div>
	<%}%>
	<% if(x==0){%>
	<div style="text-align: center;">
		<div>当x=0时，输出</div>
		<div>x=<%=x%></div>
		<div>y=<%=0%></div>
	</div>
	<%}%>
</body>
</html>