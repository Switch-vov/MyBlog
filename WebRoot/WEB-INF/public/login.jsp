<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html dir="ltr" lang="en-US" class="htmlcolor">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>博客登录</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/botton.css" type="text/css">
</head>
<body>
	<div>
		<form action="${pageContext.request.contextPath }/login.do?type=login" method="post" class="formborder">
			<div class="loginshow">登录框</div>
			<div class="user">用户名:</div>
			<div class="pwd">密码:</div>
			<div class="userin">
				<input type="text" name="userName"/>
			</div>
			<div class="pwdin">
				<input type="password" name="password"  />
			</div>
			<div class="remember">
				<input type="checkbox" name="remember-me" />记住我
			</div>
			<div class="forget">
				忘记 <a href="#">用户名</a> 或 <a href="#">密码</a>？
			</div>
			<div class="divbot">
				<input type="submit" class="bot" name="submit" value="" />
			</div>
		</form>
	</div>
	<div class="foot">
		<hr />
		<p align="center">博客系统@“和谐堂HXT版权所有”</p>
	</div>
	<div class="returnHome"><a href="${pageContext.request.contextPath }/login.do?type=gotoMainUI">返回博客首页</a></div>
</body>
</html>
