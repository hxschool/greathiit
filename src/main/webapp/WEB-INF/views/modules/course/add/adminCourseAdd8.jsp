<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程内容管理</title>
	<meta name="decorator" content="default"/>

<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/admin.css" />
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
</script>
</head>

<body>
	
	<div class="container">
		<div class="row">
			
				<div class="span12">
				<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="${ctxStatic}/modules/img/circle.jpg"/>
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>添加课程说明
					</h6>
				</div>
				<form action="adminCourseAdd9"  method="post"
					enctype="multipart/form-data" class="form-horizontal">
					<input type="hidden" name="courseId" value="${courseId}"/>
					<div class="div-inf">
						<div class="div-inf-title">（一）与相关课程的分工衔接</div>
						<div class="div-inner-text">
							<textarea name="cursNote1"></textarea>
						</div>
					</div>
					<div class="div-inf">
						<div class="div-inf-title">（二）其他说明</div>
						<div class="div-inner-text">
							<textarea name="cursNote2"></textarea>
						</div>
					</div>
					<div class="div-btn">
							<input type="submit" value="提交" class="btn">
						</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>
