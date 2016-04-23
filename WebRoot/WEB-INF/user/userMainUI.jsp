<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${visitUserInfo.nickName }的博客</title>
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
								<a class="two" href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${visitUserInfo.userName }">${visitUserInfo.nickName }的博客</a>
							</h1>
						</div>
						<p class="text-left text-muted" style="font-size:22px;color:#00ff7f">
							&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;
							${visitBlogInfo.idiograph }
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12" style="text-align: right;">
						<div class="btn-group" >
							<a style="text-decoration:none;" href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${visitUserInfo.userName }">
								<button class="btn btn-default" type="button">
										<em class="glyphicon glyphicon-align-left">
											<span style="color:black;">目录视图</span>
										</em> 
								</button>
							</a>
							<a style="text-decoration:none;" href="#">
								<button class="btn btn-default" type="button">
									<em class="glyphicon glyphicon-align-center">
										<span style="color:black;">摘要视图</span>
									</em>
								</button>
							</a>
							<c:if test="${loginUserInfo.userName == visitUserInfo.userName}">
								<a style="text-decoration:none;" href="${pageContext.request.contextPath }/userArticle.do?type=gotoWriteBlogUI&userId=${loginUserInfo.userId }">
									<button class="btn btn-default" type="button">
										<em class="glyphicon glyphicon-align-right">
											<span style="color:black;">写博客&nbsp;</span>
										</em>
									</button>
								</a>
							</c:if>
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
									<strong>${visitUserInfo.userName }</strong>
								</span>
							</div>
							<br/>
							<div style="width: 100%;">
								<div style="text-align: left;line-height:20px; position:relative;left:30px; width: 70%;">
									<p>访问：${visitClickCount }次</p>
									<p>博客：${visitArticleCount }篇</p>
									<p>评论：${visitCritiqueCount }条</p>
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
										<c:forEach var="article" items="${visitArticleInfo }" varStatus="status">
											<c:choose>
												<c:when test="${(status.index % 5) == 0}">
													<tr>
														<td><a class="one" href="${pageContext.request.contextPath }/userArticle.do?type=gotoArticleContentPage&articleId=${article.articleId }">${article.title }</a></td>
														<td style="text-align: right;">${article.date } 阅读(${fn:length(article.clicks) }) 评论(${fn:length(article.critiques) })</td>
													</tr>
												</c:when>
												<c:when test="${(status.index % 5) == 1}">
													<tr class="active">
														<td><a class="one" href="${pageContext.request.contextPath }/userArticle.do?type=gotoArticleContentPage&articleId=${article.articleId }">${article.title }</a></td>
														<td style="text-align: right;">${article.date } 阅读(${fn:length(article.clicks) }) 评论(${fn:length(article.critiques) })</td>
													</tr>
												</c:when>
												<c:when test="${(status.index % 5) == 2}">
													<tr class="success">
														<td><a class="one" href="${pageContext.request.contextPath }/userArticle.do?type=gotoArticleContentPage&articleId=${article.articleId }">${article.title }</a></td>
														<td style="text-align: right;">${article.date } 阅读(${fn:length(article.clicks) }) 评论(${fn:length(article.critiques) })</td>
													</tr>
												</c:when>
												<c:when test="${(status.index % 5) == 3}">
													<tr class="warning">
														<td><a class="one" href="${pageContext.request.contextPath }/userArticle.do?type=gotoArticleContentPage&articleId=${article.articleId }">${article.title }</a></td>
														<td style="text-align: right;">${article.date } 阅读(${fn:length(article.clicks) }) 评论(${fn:length(article.critiques) })</td>
													</tr>
												</c:when>
												<c:when test="${(status.index % 5) == 4}">
													<tr class="danger">
														<td><a class="one" href="${pageContext.request.contextPath }/userArticle.do?type=gotoArticleContentPage&articleId=${article.articleId }">${article.title }</a></td>
														<td style="text-align: right;">${article.date } 阅读(${fn:length(article.clicks) }) 评论(${fn:length(article.critiques) })</td>
													</tr>
												</c:when>
											</c:choose>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div  style="text-align: center;">
								<ul class="pagination pagination-sm">
									<c:if test="${pageNow != 1 }">
										<li><a href="${pageContext.request.contextPath }/userBlog.do?type=page&userName=${visitUserInfo.userName }&pageNow=1">首页</a></li>
										<li><a href="${pageContext.request.contextPath }/userBlog.do?type=page&userName=${visitUserInfo.userName }&pageNow=${pageNow - 1 }">上一页</a></li>
									</c:if>
									<c:forEach var="i" begin="1" end="${pageCount }" step="1">
										<li><a href="${pageContext.request.contextPath }/userBlog.do?type=page&userName=${visitUserInfo.userName }&pageNow=${i }">${i }</a></li>
									</c:forEach>
									<c:if test="${pageNow < pageCount }">
										<li><a href="${pageContext.request.contextPath }/userBlog.do?type=page&userName=${visitUserInfo.userName }&pageNow=${pageNow + 1 }">下一页</a></li>
										<li><a href="${pageContext.request.contextPath }/userBlog.do?type=page&userName=${visitUserInfo.userName }&pageNow=${pageCount }">尾页</a></li>
									</c:if>
									<li><a href="#" onclick="return false">当前第${pageNow }页</a></li>
									<li><a href="#" onclick="return false">当前页有${fn:length(visitArticleInfo) }篇文章</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			<div class="col-md-1"></div>
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