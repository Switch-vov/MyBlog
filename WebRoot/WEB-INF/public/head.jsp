<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 搜索框控制不能小于两个字符 --%>
<script type="text/javascript">
<!--
	function checkSearchLength() {
		var key = document.getElementById("searchKey").value;
		var keyReg1 = /.{2,}/gi;
		if (!keyReg1.test(key)) {
			window.alert("请输入2-20个字符");
			return false;
		}
		var keyReg2 = /.{21,}/gi;
		if (keyReg2.test(key)) {
			window.alert("请输入2-20个字符");
			return false;
		}
	}
//-->
</script>

<%-- 页首 --%>
<div class="row">
	<div class="col-md-12">
		<nav class="navbar navbar-default navbar-inverse navbar-fixed-top"
			role="navigation">
			<div class="navbar-header">

				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span><span
						class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath }/gotoMainUI.do">MyBlog</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${pageContext.request.contextPath }/gotoMainUI.do">首页</a></li>
					<li><a href="${pageContext.request.contextPath }/userBlog.do?type=gotoAllBlogUI">博客列表</a></li>
					<li class="dropdown" id="drop"><a href="#"
						class="dropdown-toggle" onclick="dropclick()">友情链接<strong
							class="caret"></strong>
					</a>
						<ul class="dropdown-menu">
							<li><a href="https://www.baidu.com/" target="_blank">百度</a></li>
							<li><a href="https://www.sogou.com/" target="_blank">搜狗</a></li>
							<li><a href="http://weibo.com/" target="_blank">新浪微博</a></li>
							<li class="divider"></li>
							<li><a href="http://blog.csdn.net/q547550831"
								target="_blank">Switch的博客</a></li>
							<li class="divider"></li>
							<li><a href="http://blog.csdn.net/u011391361"
								target="_blank">TSW的博客</a></li>
						</ul></li>
				</ul>
				<form action="${pageContext.request.contextPath }/search.do?type=gotoSearchArticleUI" class="navbar-form navbar-left" role="search" method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="searchKey" name="searchKey">
					</div>
					<button type="submit" class="btn btn-default" onclick="return checkSearchLength()">搜索</button>
				</form>
				<ul class="nav navbar-nav navbar-right" style="margin-right: 10px;">
					<%-- if user not login --%>
					<c:if test="${empty loginUserInfo}">
						<li>
							<a href="${pageContext.request.contextPath }/login.do?type=gotoLogin">登录</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath }/login.do?type=gotoRegister">注册</a>
						</li>
					</c:if>
					<%-- if user login --%>
					<c:if test="${!empty loginUserInfo}">
						<li>
							<a href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${loginUserInfo.userName }">${loginUserInfo.nickName }</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath }/login.do?type=logout">注销？</a>
						</li>
						<li class="dropdown" id="drop2">
							<a href="#" class="dropdown-toggle" onclick="dropclick2()">我的博客
								<strong class="caret"></strong>
							</a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath }/userBlog.do?type=gotoUserUI&userName=${loginUserInfo.userName }">我的主页</a></li>
								<li><a href="${pageContext.request.contextPath }/modifyBlogInfo.do?type=gotoModifyBlogInfo">修改信息</a></li>
								<li><a href="${pageContext.request.contextPath }/forgetPassword.do?type=gotoFindMyPassword">修改密码</a></li>
								<li class="divider"></li>
								<li><a href="${pageContext.request.contextPath }/userArticle.do?type=gotoWriteBlogUI&userId=${loginUserInfo.userId }">写博客</a></li>
							</ul>
						</li>
					</c:if>
				</ul>
			</div>

		</nav>
	</div>
</div>