<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${visitUserInfo.userName }的博客——${visitSingleArticleInfo.title }</title>
<link href="${pageContext.request.contextPath }/resource/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/resource/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath }/resource/summernote/summernote.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/resource/summernote/summernote.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/summernote/summernote-zh-CN.js"></script>
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
								<a style="text-decoration:none;" href="${pageContext.request.contextPath }/userArticle.do?type=gotoUpdateBlogUI&userId=${loginUserInfo.userId }&articleId=${visitSingleArticleInfo.articleId }">
									<button class="btn btn-default" type="button">
										<em class="glyphicon glyphicon-align-justify">
										<span style="color:black;">修改博客</span>
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
						<div style="background-color: white;padding: 20px;">
							<span style="font-size:20px;"><a class="one" href="${pageContext.request.contextPath }/userArticle.do?type=gotoArticleContentPage&articleId=${visitSingleArticleInfo.articleId }">${visitSingleArticleInfo.title }</a></span>
							<br/>
							<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="label label-default">博客标签1</span>
							<span class="label label-default">博客标签2</span>
							<span class="label label-default">博客标签3</span>
							<span class="label label-default">博客标签4</span>
							<span class="label label-default">博客标签5</span>
							<div style="text-align: right;">
								<span>${visitSingleArticleInfo.date }</span>&nbsp;&nbsp;&nbsp;
								<span>阅读(${fn:length(visitSingleArticleInfo.clicks) })</span>&nbsp;&nbsp;&nbsp;
								<span>评论(${fn:length(visitSingleArticleInfo.critiques) })</span>
							</div>
							<hr/>
							<div>
								<p>
									${visitSingleArticleInfo.content }
								</p>
							</div>
							
							
							<%-- 评论显示区 --%>
							<hr/>
							<c:forEach var="critique" items="${visitCritiquesInfo }">
								<div class="media well">
					 				<a href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${critique.user.userName }" class="pull-left">
					 					<img alt="Bootstrap Media Preview" src="http://lorempixel.com/64/64/" class="media-object" />
					 				</a>
									<div class="media-body">
										<h4 class="media-heading">
											<a href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${critique.user.userName }">${critique.user.nickName }</a>
										</h4> 
										${critique.content }
									</div>
								</div>
							</c:forEach>
							<%-- 评论栏  --%>
							<hr />
							<label for="title" style="color: black; font-size: 20px;">
								&nbsp;&nbsp;&nbsp;&nbsp;评论
							</label>
							<c:if test="${empty loginUserInfo}">
								<div class="alert alert-dismissable alert-info">
									<button type="button" class="close" data-dismiss="alert" aria-hidden="true">
										×
									</button>
									<h4>
										如果您想要评论
									</h4> 
										请<a href="${pageContext.request.contextPath }/login.do?type=gotoLogin" class="alert-link"><strong>登录</strong></a>/
										<a href="${pageContext.request.contextPath }/login.do?type=gotoRegister" class="alert-link"><strong>注册</strong></a>
								</div>
							</c:if>
							<c:if test="${!empty loginUserInfo}">
								<form action="${pageContext.request.contextPath }/userArticle.do?type=comment" method="post">
									<div class="editor"></div>
									<br />
									<textarea style="display: none;" id="content" name="content">${content }</textarea>
									<input type="hidden" name="userId" value="${loginUserInfo.userId }"/>
									<input type="hidden" name="articleId" value="${visitSingleArticleInfo.articleId }"/>
									<button type="submit" class="btn btn-default" onclick="putContent()">提交</button>
								</form>
							</c:if>
						</div>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
				
		<%-- 引入页尾 --%>
		<jsp:include page="/WEB-INF/public/foot.jsp" />
	</div>

	
	<script>
	   $(document).ready(function() {
		  $('.editor').summernote({
		  	toolbar: [
			    // [groupName, [list of button]]
			    ['style1', ['style']],
			    ['urdo', ['undo', 'redo']],
			    ['style2', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
			    ['fontns', ['fontname','fontsize']],
			    ['color', ['color']],
			    ['font', ['superscript', 'subscript']],
			    ['para', ['ul', 'ol', 'paragraph', 'height']],
			    ['misc', ['fullscreen', 'codeview']],
			    ['help',['help']]
			 ],
			lang: 'zh-CN',
		  	height: 300,
		  	minHeight: 200,
		  	maxHeight: null,
		  	focus: false });
	   });
 
 		var putContent = function() {
 			var makrup = $('.editor').summernote('code');
			/*window.alert(makrup);*/
 			document.getElementById("content").value = makrup;
 			$('.editor').summernote('destroy');
 		};
	</script>
	<script src="${pageContext.request.contextPath }/resource/js/scripts.js"></script>
</body>
</html>