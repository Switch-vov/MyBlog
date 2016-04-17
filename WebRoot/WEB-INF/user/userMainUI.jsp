<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Bootstrap 3, from LayoutIt!</title>
<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resource/css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<%-- 引入页头  --%>
		<jsp:include page="/WEB-INF/public/head.jsp" />
		
		<%-- 主体 --%>
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
								<em class="glyphicon glyphicon-align-left"></em> 目录视图
							</button>
							<button class="btn btn-default" type="button">
								<em class="glyphicon glyphicon-align-center"></em> 摘要视图
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
							<div>
								<table class="table table-hover table-condensed">
									<thead>
										<tr>
											<th colspan="2">博客列表</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>TB - Monthly</td>
											<td style="text-align: right;">Default</td>
										</tr>
										<tr class="active">
											<td>TB - Monthly</td>
											<td style="text-align: right;">Approved</td>
										</tr>
										<tr class="success">
											<td>TB - Monthly</td>
											<td style="text-align: right;">Declined</td>
										</tr>
										<tr class="warning">
											<td>TB - Monthly</td>
											<td style="text-align: right;">Pending</td>
										</tr>
										<tr class="danger">
											<td>TB - Monthly</td>
											<td style="text-align: right;">Call in to confirm</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div  style="text-align: center;">
								<ul class="pagination pagination-sm">
									<li><a href="#">Prev</a></li>
									<li><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">Next</a></li>
								</ul>
							</div>
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