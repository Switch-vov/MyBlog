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
		
		<%-- 主体 --%>
		<div class="row" style="background:url(images/body_bg.jpg);">
			<br/>
			<br/>
			<br/>
			<div class="col-md-12">
				<div class="carousel slide" id="carousel-996665">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0" data-target="#carousel-996665">
						</li>
						<li data-slide-to="1" data-target="#carousel-996665">
						</li>
						<li data-slide-to="2" data-target="#carousel-996665">
						</li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img alt="internet" src="${pageContext.request.contextPath }/resource/images/Ho1.jpg">
							<div class="carousel-caption">
								<h4 style="color:yellow;">
									热词：互联网、博客
								</h4>
								<p style="color:black;">
									科技发展让我们进入互联网时代，而博客则是人们在互联网上交流的一大突破和亮点。
								</p>
							</div>
						</div>
						<div class="item">
							<img alt="world" src="${pageContext.request.contextPath }/resource/images/Ho2.jpg">
							<div class="carousel-caption">
								<h4  style="color:yellow;">
									热词：了解世界
								</h4>
								<p style="color:white;">
									博客让我们足不出户，就能看见外面的世界，了解地球的荒寂或美妙。
								</p>
							</div>
						</div>
						<div class="item">
							<img alt="figure" src="${pageContext.request.contextPath }/resource/images/Ho3.jpg">
							<div class="carousel-caption">
								<h4 style="color:#00ffff;">
									热词：了解人物
								</h4>
								<p style="color:white;">
									博客还能让我们去了解各个领悟的人物，崇拜他们，或者单为满足自己的欣赏欲。
								</p>
							</div>
						</div>
					</div> <a class="left carousel-control" href="#carousel-996665" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-996665" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
				<br/>
				<br/>
				<br/>
				<br/>
				<div class="row">
					<div style="text-align: center;color:white"><Strong>V百科图片新闻每日专栏</Strong></div>
					<hr/>
					<div class="col-md-4">
						<div class="thumbnail">
							<img alt=huojin" src="${pageContext.request.contextPath }/resource/images/Huojin.png">
							<div class="caption">
								<h3>
									霍金教授
								</h3>
								<p>
									大牌“网红”霍金教授强势来袭！
								</p>
								<p>
									<a class="btn btn-primary" target="_blank" href="${pageContext.request.contextPath }/resource/images/newspic1.jpg">查看图片</a>
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="thumbnail">
							<img alt="kebi" src="${pageContext.request.contextPath }/resource/images/kebi.png">
							<div class="caption">
								<h3>
									科比
								</h3>
								<p>
									2014年-4月-14号，科比正式告别篮坛。
								</p>
								<p>
									<a class="btn btn-primary" target="_blank" href="${pageContext.request.contextPath }/resource/images/newspic2.jpg">查看图片</a>
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="thumbnail">
							<img alt="zhangguorong" src="${pageContext.request.contextPath }/resource/images/zhangguorong.png">
							<div class="caption">
								<h3>
									张国荣
								</h3>
								<p>
									张国荣——永远的哥哥
								</p>
								<p>
									<a class="btn btn-primary"  target="_blank" href="${pageContext.request.contextPath }/resource/images/newspic3.jpg">查看图片</a>
								</p>
							</div>
						</div>
					</div>
				</div>
				<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			</div>
		</div>
		
		<%-- 引入页尾 --%>
		<jsp:include page="/WEB-INF/public/foot.jsp" />
	</div>
	
	
	<script src="${pageContext.request.contextPath }/resource/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/scripts.js"></script>
</body>
</html>