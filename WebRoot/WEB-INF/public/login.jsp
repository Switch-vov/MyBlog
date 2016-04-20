<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>博客登录</title>
<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resource/css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<%-- 引入页头  --%>
		<jsp:include page="/WEB-INF/public/head.jsp" />
		
		<%-- 主体 --%>
		<div class="row" style="background:url(${pageContext.request.contextPath }/resource/images/body_bg.jpg);">
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<form action="${pageContext.request.contextPath }/login.do?type=login" class="form-horizontal" role="form" method="post">
					<div class="form-group">
						<label class="col-sm-2 control-label" style="color:white;">
							用户名：
						</label>
						<div class="col-sm-10" style="width:250px">
							<input type="text" class="form-control" name="userName">
						</div>
						<label style="color: red;">${errinfo }</label>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label" style="color:white;">
							密&nbsp;&nbsp;&nbsp;&nbsp;码：
						</label>
						<div class="col-sm-10" style="width:250px">
							<input type="password" class="form-control" name="password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label style="color:white;">
									<input type="checkbox"> 记住我&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</label>
								<a style="color:white" href="${pageContext.request.contextPath }/forgetPassword.do?type=gotoForgetPassword">忘记密码？</a>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">
								登录
							</button>
						</div>
					</div>
				</form>
				<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			</div>
			<div class="col-md-3"></div>
		</div>		
		
		
		<%-- 引入页尾 --%>
		<jsp:include page="/WEB-INF/public/foot.jsp" />
	</div>
	
	
	<script src="${pageContext.request.contextPath }/resource/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/scripts.js"></script>
</body>
</html>