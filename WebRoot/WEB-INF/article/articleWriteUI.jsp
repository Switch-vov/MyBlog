<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${loginUserInfo.userName }——写博客</title>
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
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="form-group">
							<label for="title" style="color: white; font-size: 20px;">
								&nbsp;&nbsp;&nbsp;&nbsp;文章标题(标题不能多于45个字符)
								&nbsp;&nbsp;&nbsp;&nbsp;<lable style="color:red;">${errInfo }</lable>
							</label>
							<input type="text" class="form-control" id="virtualtitle" />
						</div>
						<label for="title" style="color: white; font-size: 20px;">
								&nbsp;&nbsp;&nbsp;&nbsp;文章内容(内容不能少于50个字符)
						</label>
						<br/>
						<button id="edit" class="btn btn-default" onclick="edit()" type="button">编辑</button>
						<button id="save" class="btn btn-default" onclick="save()" type="button">预览</button>
						<div class="editor" style="background-color: white;"></div>
						<br />
						<form action="${pageContext.request.contextPath }/userArticle.do?type=saveArticle" method="post">
							<input type="hidden" id="title" name="title"/>
							<input type="hidden" id="content" name="content"/>
							<input type="hidden" name="userId" value="${loginUserInfo.userId }"/>
							<button type="submit" class="btn btn-default" onclick="putContent()">保存</button>
						</form>
					</div>
				</div>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
			</div>
			<div class="col-md-2"></div>
		<%-- 引入页尾 --%>
		<jsp:include page="/WEB-INF/public/foot.jsp" />
		</div>
	<script>
	
	   var edit = function() {
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
			    ['insert', ['picture' , 'link','video','table','hr']],
			    ['misc', ['fullscreen', 'codeview']],
			    ['help',['help']]
			 ],
			lang: 'zh-CN',
		  	height: 300,
		  	minHeight: 200,
		  	maxHeight: null,
		  	focus: true });
		};

		var save = function() {
		  var makrup = $('.editor').summernote('code');
		  $('.editor').summernote('destroy');
		};
 
 		var putContent = function() {
 			var makrup = $('.editor').summernote('code');
			/*window.alert(makrup);*/
 			document.getElementById("content").value = makrup;
 			$('.editor').summernote('destroy');
 			var virtualtitle = document.getElementById("virtualtitle").value;
 			document.getElementById("title").value = virtualtitle;
 		};
	</script>
	
	
	<script src="${pageContext.request.contextPath }/resource/js/scripts.js"></script>
</body>
</html>