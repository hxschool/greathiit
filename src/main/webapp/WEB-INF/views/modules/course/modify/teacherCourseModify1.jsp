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
				<form action="TeacherCourse_Modify_1_updateCursBasicInf" method="post"
					enctype="multipart/form-data" class="form-horizontal" onsubmit="javascript:return isEmpty()">
					<div class="div-inf">
						<div class="div-inf-title">基本信息</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程编号：</label>
							<div class="controls">
								<input type="text" name="course.cursNum" value="<s:property value="course.cursNum"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程名称：</label>
							<div class="controls">
								<input type="text" name="course.cursName" value="<s:property value="course.cursName"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">英文名称：</label>
							<div class="controls">
								<input type="text" name="course.cursEngName" value="<s:property value="course.cursEngName"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">学时：</label>
							<div class="controls">
								<input type="text" name="course.cursClassHour" value="<s:property value="course.cursClassHour"/>" id="input-hour" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">学分：</label>
							<div class="controls">
								<input type="text" name="course.cursCredit" value="<s:property value="course.cursCredit"/>" id="input-credit" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程性质：</label>
							<div class="controls">
							<input type="text" name="course.cursProperty" value="<s:property value="course.cursProperty"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">适用专业：</label>
							<div class="controls">
								<input type="text" name="course.cursApplMajor" value="<s:property value="course.cursApplMajor"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">开设学期：</label>
							<div class="controls">
								<input type="text" name="course.cursTerm" value="<s:property value="course.cursTerm"/>" id="input-term" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">先修课程：</label>
							<div class="controls">
								<input type="text" name="course.cursPreCourses" value="<s:property value="course.cursPreCourses"/>" class="input-isEmpty">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">开课院系：</label>
							<div class="controls">
							<input id="department" style="display:none" value="<s:property value="course.dept.deptId"/>">
							<select name="course.dept.deptId">
									<s:iterator value="departments" var="d">
										<option id="<s:property value="#d.deptId"/>" value="<s:property value="#d.deptId" />"><s:property value="#d.deptName" /></option>
									</s:iterator>
								</select>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程类型：</label>
							<div class="controls">
							<input id="cursType" style="display:none" value="<s:property value="course.type"/>">
							<select name="course.type">
										<option id="normal" value="normal">考试课程</option>
										<option id="experiment" value="experiment">实验课程</option>
										<option id="graduation-project" value="graduation-project">毕业设计</option>
								</select>
							</div>
						</div>
					</div>
					<div class="div-inf">
						<div class="div-inf-title">课程简介</div>
						<div class="div-inner-text">
							<textarea name="course.cursIntro"><s:property value="course.cursIntro"/></textarea>
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
