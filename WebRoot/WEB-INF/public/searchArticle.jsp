<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${searchKey }——搜索文章</title>
<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resource/css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<%-- 引入页头  --%>
		<jsp:include page="/WEB-INF/public/head.jsp" />
		<input type="hidden" id="hiddenSearchKey" value="${searchKey }" />
		<!-- 主体 -->
		<div class="row" style="background:url(${pageContext.request.contextPath }/resource/images/body_bg.jpg);">
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<c:if test="${fn:length(searchArticles) == 0 }">
					<div class="alert alert-dismissable alert-info">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">
							×
						</button>
						<h4>
							对不起，您输入的关键字并未找到相关文章。
						</h4> 
							您可以更换关键字，再次搜索。
					</div>
				</c:if>
				<c:forEach var="article" items="${searchArticles }">
					<div style="background-color: white;">
						<h3 class="media-heading">
							<a href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${article.user.userName }" class="one">
								${article.user.nickName }
							</a>
						</h3>
						<div class="media">
						 	<a class="pull-left" href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${article.user.userName }">
						 		<img alt="Bootstrap Media Preview" src="http://lorempixel.com/64/64/" class="media-object">
						 	</a>
							<div class="media-body">
							 	<a href="${pageContext.request.contextPath }/userArticle.do?type=gotoArticleContentPage&articleId=${article.articleId }" class="one">
							 		<h4 class="media-heading">
							 			${article.title }
									</h4> 
								</a>
								<br />
								<p style="background-color: #77ffee;">
									发表时间：${article.date }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									阅读次数：${fn:length(article.clicks) }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									评论次数：${fn:length(article.critiques) }
								</p>
							</div>
						</div>
					</div>
					<br />
				</c:forEach>
				
				<div  style="text-align: center;">
					<ul class="pagination pagination-sm">
						<c:if test="${pageNow != 1 }">
							<li><a href="${pageContext.request.contextPath }/search.do?type=pageArticle&searchKey=${searchKey }&pageNow=1">首页</a></li>
							<li><a href="${pageContext.request.contextPath }/search.do?type=pageArticle&searchKey=${searchKey }&pageNow=${pageNow - 1 }">上一页</a></li>
						</c:if>
						<c:forEach var="i" begin="1" end="${pageCount }" step="1">
							<li><a href="${pageContext.request.contextPath }/search.do?type=pageArticle&searchKey=${searchKey }&pageNow=${i }">${i }</a></li>
						</c:forEach>
						<c:if test="${pageNow < pageCount }">
							<li><a href="${pageContext.request.contextPath }/search.do?type=pageArticle&searchKey=${searchKey }&pageNow=${pageNow + 1 }">下一页</a></li>
							<li><a href="${pageContext.request.contextPath }/search.do?type=pageArticle&searchKey=${searchKey }&pageNow=${pageCount }">尾页</a></li>
						</c:if>
						<li><a href="#" onclick="return false">当前第${pageNow }页</a></li>
						<li><a href="#" onclick="return false">当前页有${fn:length(searchArticles) }篇博客</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-1"></div>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
		</div>
		
				
		<%-- 引入页尾 --%>
		<jsp:include page="/WEB-INF/public/foot.jsp" />
	</div>
	
	
	<script src="${pageContext.request.contextPath }/resource/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/js/scripts.js"></script>
</body>
</html>