<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>找回密码</title>
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
			
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<form action="${pageContext.request.contextPath }/forgetPassword.do?type=gotoModifyPassword" class="form-horizontal" role="form" method="post">
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label" style="color:white;">
							您设置的密保问题：
						</label>
						<div class="col-sm-10" style="width:250px">
							<c:if test="${empty loginUserInfo}">
								<input type="text" disabled="disabled" value="${forgetUser.question }" class="form-control" name="question">
							</c:if>
							<c:if test="${!empty  loginUserInfo}">
								<input type="text" disabled="disabled" value="${loginUserInfo.question }" class="form-control" name="question">
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label" style="color:white;">
							请输入您设置的密保答案：
						</label>
						<div class="col-sm-10" style="width:250px">
							<input type="text" class="form-control" name="answer">
						</div>
						<label style="color: red;">${errinfo }</label>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label" style="color:white;">
							请输入您的重置密码：
						</label>
						<div class="col-sm-10" style="width:250px">
							<input type="password" class="form-control" name="password">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label" style="color:white;">
							请确认您的重置密码：
						</label>
						<div class="col-sm-10" style="width:250px">
							<input type="password" class="form-control" name="repassword">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">
								<a style="color:black;text-decoration: none;" href="BlogLogin.html">重置密码</a>
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
			<div class="col-md-2"></div>
		</div>		
		
		
		<%-- 引入页尾 --%>
		<jsp:include page="/WEB-INF/public/foot.jsp" />
	</div>
	
	
	<script src="${pageContext.request.contextPath }/resource/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/scripts.js"></script>
</body>
</html>