<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>使用List存储数据，并取出显示</title>
</head>
<body>
	<% 
		List<String> list = new ArrayList();
		list.add("JSP基础入门");
		list.add("Servlet视频详解");
		list.add("EL表达式初识");
		list.add("JSTL标签库初识");
	%>
	<%
		for(int i=0;i<list.size();i++){
	%>
			<div>第<%=(i+1)%>条： <%=list.get(i)%></div>
	<%}%>
</body>
</html>