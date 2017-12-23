<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设置课程考试与教学目标支撑分值管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/course/courseTeachingtarget/">设置课程考试与教学目标支撑分值列表</a></li>
		<li class="active"><a href="${ctx}/course/courseTeachingtarget/form?id=${courseTeachingtarget.id}">设置课程考试与教学目标支撑分值<shiro:hasPermission name="course:courseTeachingtarget:edit">${not empty courseTeachingtarget.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="course:courseTeachingtarget:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="courseTeachingtarget" action="${ctx}/course/courseTeachingtarget/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">教学目标：</label>
			<div class="controls">
				<form:input path="tchtargetContent" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课堂表现：</label>
			<div class="controls">
				<form:input path="tchtargetClassTargetValue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平时作业：</label>
			<div class="controls">
				<form:input path="tchtargetHomeworkTargetValue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验成绩：</label>
			<div class="controls">
				<form:input path="tchtargetExpTargetValue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">期中成绩：</label>
			<div class="controls">
				<form:input path="tchtargetMidTargetValue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">期末成绩：</label>
			<div class="controls">
				<form:input path="tchtargetFinTargetValue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">course_id：</label>
			<div class="controls">
				<form:input path="courseId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="course:courseTeachingtarget:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>