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
		<script type="text/javascript">
	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}
	</script>
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
				<form action="teacherCourse_Modify_1_updateCursBasicInf" method="post"
					enctype="multipart/form-data" class="form-horizontal" onsubmit="javascript:return isEmpty()">
					<input type="hidden" name="id" value="${course.id}" class="input-isEmpty">
					<div class="div-inf">
						<div class="div-inf-title">基本信息</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程编号：</label>
							<div class="controls">
								<input type="text" name="cursNum" value="${course.cursNum}" class="input-isEmpty" readonly="readonly">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程名称：</label>
							<div class="controls">
								<input type="text" name="cursName" value="${course.cursName}" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">英文名称：</label>
							<div class="controls">
								<input type="text" name="cursEngName" value="${course.cursEngName}" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">学时：</label>
							<div class="controls">
								<input type="text" name="cursClassHour" value="${course.cursClassHour}" id="input-hour" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">学分：</label>
							<div class="controls">
								<input type="text" name="cursCredit" value="${course.cursCredit}" id="input-credit" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程性质：</label>
							<div class="controls">
							<input type="text" name="cursProperty" value="${course.cursProperty}" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">适用专业：</label>
							<div class="controls">
								<input type="text" name="cursMajor" value="${course.cursMajor}" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">开设学期：</label>
							<div class="controls">
								<input type="text" name="cursTerm" value="${course.cursTerm}" id="input-term" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">先修课程：</label>
							<div class="controls">
								<input type="text" name="cursPreCourses" value="${course.cursPreCourses}" class="input-isEmpty">
							</div>
						</div>
						
						<div class="control-group control-group-left">
							<label class="control-label">课程类型：</label>
							<div class="controls">
							<select name="cursType">
										<option id="normal" value="normal" <c:if test="${course.cursType == 'normal' }"> selected </c:if> >考试课程</option>
										<option id="experiment" value="experiment" <c:if test="${course.cursType == 'experiment' }"> selected </c:if> >实验课程</option>
										<option id="graduation-project" value="graduation-project" <c:if test="${course.cursType == 'graduation-project' }"> selected </c:if> >毕业设计</option>
								</select>
							</div>
						</div>
					</div>
					<div class="div-inf">
						<div class="div-inf-title">课程简介</div>
						<div class="div-inner-text">
							<textarea name="cursIntro">${course.cursIntro}</textarea>
						</div>
					</div>
					<div class="div-btn">
						<input type="submit" class="btn" value="提交">
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
