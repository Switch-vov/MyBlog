<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
2	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
3	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
4	<html>
5	<head>
6	<title>My JSP 'err.jsp' starting page</title>
7	</head>
8	
9	<body>
10		<h1>操作失败</h1>
11		<a href="${pageContext.servletContext.contextPath }/login.do?type=goMainFrame">返回主界面</a>
12	</body>
13</html>