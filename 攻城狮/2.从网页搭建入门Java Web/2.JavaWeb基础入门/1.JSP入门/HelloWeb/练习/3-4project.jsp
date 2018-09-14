<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>求1-100数字的和</title>
</head>
<body>
	<%! int sum=0;%>
	<%
		for(int i=1;i<=100;i++){
		sum+=i;
	}
	%>
	1-100的和是：<% out.println(sum); %>
</body>
</html>