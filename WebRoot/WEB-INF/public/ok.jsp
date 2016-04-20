<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>My JSP 'ok.jsp' starting page</title>
  </head>

<body>
	<h1>操作成功</h1>
	<a href="${pageContext.servletContext.contextPath }/login.do?type=goMainFrame">返回主界面</a>
</body>
</html>