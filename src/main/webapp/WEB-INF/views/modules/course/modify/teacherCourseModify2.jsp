<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程内容管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
	<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/teacher/admin.css" />
</head>

<body>
	
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="${ctxStatic}/modules/img/circle.jpg"/>
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>${course.cursName}
					</h6>
				</div>
				<form action="TeacherCourse_Modify_2_modifyTargetByCursId" method="post"
					enctype="multipart/form-data" class="form-horizontal" onsubmit="javascript:return isEmpty()">
					<div class="div-inf" id="div-targets">
					<c:forEach items="${targets }" var="t" varStatus="s">
						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<input type="text" class="input-long target"
								value="${t.tchTarContent }" /><label class="label-delete" onclick="deleteDiv(this)">删除</label>
							</div>
						</div>
						</c:forEach>
					</div>
					<div class="div-btn">
						<label class="btn" onclick="addTarget()">添加</label>
						<input type="submit" class="btn" onclick="addName()" value="确定">
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>
