<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>博客注册</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/main.css" media="all" />

</head>
<body>
	<div
		style="border:solid;width:275px;position:relative;left:533px;top:0px;background-color:yellow">
		<div style="width:275px">
			<div>
				<h1>博客网站系统</h1>
			</div>
			<div>
				<hr />
				<h2>新博客注册</h2>
				<form action="${pageContext.request.contextPath }/login.do?type=register" method="post">
					<div>
						帐号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
							name="userName" />
					</div>
					<div>
						密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password"
							name="password" />
					</div>
					<div>
						确认密码:&nbsp;&nbsp;&nbsp;<input type="password" name="repassword" />
					</div>
					<div>
						昵称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
							name="nickName" />
					</div>
					<div>
						密码保护问题:&nbsp;<input type="text" name="question" />
					</div>
					<div>
						密码保护的答案:<input type="text" name="answer" />
					</div>
					<div style="width:275px;position:relative;left:110px;top:0px">
						<input name="提交" type="submit" class="button" value="注册" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<div>
		<hr />
		<p align="center">博客网站系统@“河蟹塘HXT”版权所有</p>
	</div>
</body>
</html>
