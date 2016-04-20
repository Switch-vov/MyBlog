<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>博客首页</title>
<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resource/css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<%-- 引入页头  --%>
		<jsp:include page="/WEB-INF/public/head.jsp" />
		<!-- 主体 -->
		<div class="row" style="background:url(${pageContext.request.contextPath }/resource/images/body_bg.jpg);">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-md-12">
						<div class="page-header">
							<br/>
							<h1 style="color:white;">
								Switch的博客
							</h1>
						</div>
						<p class="text-left text-muted" style="font-size:22px;color:#00ff7f">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							这里是个性签名。
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12" style="text-align: right;">
						<div class="btn-group" >
							<button class="btn btn-default" type="button">
								<em class="glyphicon glyphicon-align-left">
									<a style="text-decoration:none;" href="index.html"><span style="color:black;">目录视图</span></a>
								</em> 
							</button>
							<button class="btn btn-default" type="button">
								<em class="glyphicon glyphicon-align-center">
									<a style="text-decoration:none;" href="index2.html"><span style="color:black;">摘要视图</span></a>
								</em>
							</button>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">
						<div style="background-color:white; text-align: center; margin-bottom: 20px;">
							<div style="text-align: left; background-color:#F2F2F2; border-bottom:1px solid #D7D7D7; line-height: 25px;">个人资料</div>
							<br />
							<div style="border-bottom:1px dashed #D7D7D7;">
							<img alt="Bootstrap Image Preview"
								src="http://lorempixel.com/140/140/" class="img-thumbnail">
								<br/>
								<span>
									<strong>用户名</strong>
								</span>
							</div>
							<br/>
							<div style="width: 100%;">
								<div style="text-align: left;line-height:20px; position:relative;left:30px; width: 70%;">
									<p>访问：10000次</p>
									<p>博客：10000篇</p>
									<p>评论：10000条</p>
								</div>
							</div>
							<br/>
						</div>
					</div>
					<div class="col-md-10">
						<div style="background-color: white;">
							<span style="font-size:20px;"><a class="one" href="#">这里是标题栏</a></span>
							<br/>
							<br/>
							<span class="label label-default">这里是博客标签</span>
							<div style="text-align: right;">
								<span> 发布博客的时间 </span>&nbsp;&nbsp;&nbsp;
								<span> n人阅读 </span>&nbsp;&nbsp;&nbsp;
								<span>评论（n）</span>
							</div>
							<hr/>
							<div>
								<p>这里放博客？</p>
							</div>
							
							
							<%-- 评论栏  --%>

						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>		
				
		<%-- 引入页尾 --%>
		<jsp:include page="/WEB-INF/public/foot.jsp" />
	</div>
	
	
	<script src="${pageContext.request.contextPath }/resource/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/scripts.js"></script>
</body>
</html>