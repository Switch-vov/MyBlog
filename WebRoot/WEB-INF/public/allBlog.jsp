<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>博客列表</title>
<link href="${pageContext.request.contextPath }/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resource/css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<%-- 引入页头  --%>
		<jsp:include page="/WEB-INF/public/head.jsp" />
		
		<!-- 主体 -->
		<div class="row" style="background:url(${pageContext.request.contextPath }/resource/images/body_bg.jpg);">
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<c:forEach var="user" items="${allUser }" varStatus="status">
					<div style="background-color: white;">
						<h3 class="media-heading">
								<a class="one" href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${user.userName }">
									${user.nickName }的博客
								</a>
						</h3>
						<div class="media">
						 	<a class="pull-left" href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${user.userName }">
						 		<img alt="Bootstrap Media Preview" src="http://lorempixel.com/64/64/" class="media-object">
						 	</a>
							<div class="media-body">
							 	<h4 class="media-heading">
							 		<c:forEach var="bloginfo" items="${user.bloginfos }">
							 			${bloginfo.idiograph }
							 		</c:forEach>
								</h4>
							</div>
						</div>
						<div>
							<p style="background-color: #77ffee;">
								博客主人的Id：${user.userName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								最近更新时间：
								<c:set var="updateTime" value="${'lastUpdateTime' }${user.userName }" scope="request"></c:set>
								<%= request.getAttribute("" + request.getAttribute("updateTime")) %>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								总阅读次数：
								<c:set var="click" value="${'userClick' }${user.userName }" scope="request"></c:set>
								<%= request.getAttribute("" + request.getAttribute("click")) %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								总评论次数：
								<c:set var="critique" value="${'userCritique' }${user.userName }" scope="request"></c:set>
								<%= request.getAttribute("" + request.getAttribute("critique")) %>
							</p>
						</div>
					</div>
				</c:forEach>
				
				<div  style="text-align: center;">
					<ul class="pagination pagination-sm">
						<c:if test="${pageNow != 1 }">
							<li><a href="${pageContext.request.contextPath }/userBlog.do?type=pageBlog&pageNow=1">首页</a></li>
							<li><a href="${pageContext.request.contextPath }/userBlog.do?type=pageBlog&pageNow=${pageNow - 1 }">上一页</a></li>
						</c:if>
						<c:forEach var="i" begin="1" end="${pageCount }" step="1">
							<li><a href="${pageContext.request.contextPath }/userBlog.do?type=pageBlog&pageNow=${i }">${i }</a></li>
						</c:forEach>
						<c:if test="${pageNow < pageCount }">
							<li><a href="${pageContext.request.contextPath }/userBlog.do?type=pageBlog&pageNow=${pageNow + 1 }">下一页</a></li>
							<li><a href="${pageContext.request.contextPath }/userBlog.do?type=pageBlog&pageNow=${pageCount }">尾页</a></li>
						</c:if>
						<li><a href="#" onclick="return false">当前第${pageNow }页</a></li>
						<li><a href="#" onclick="return false">当前页有${fn:length(allUser) }位博主</a></li>
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