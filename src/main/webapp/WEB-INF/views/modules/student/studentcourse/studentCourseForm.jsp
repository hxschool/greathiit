<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学生成绩管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
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
		<li><a href="${ctx}/student/studentCourse/">学生成绩列表</a></li>
		<li class="active"><a
			href="${ctx}/student/studentCourse/form?id=${studentCourse.id}">学生成绩<shiro:hasPermission
					name="student:studentCourse:edit">${not empty studentCourse.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="student:studentCourse:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="studentCourse"
		action="${ctx}/student/studentCourse/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />

		<div class="control-group">
			<label class="control-label">课程编号：</label>
			<div class="controls">
				<p class="text-left" style="padding-top: 3px;">${studentCourse.course.id}</p>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学号：</label>
			<div class="controls">
				<p class="text-left" style="padding-top: 3px;">${studentCourse.student.studentNumber}</p>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<p class="text-left" style="padding-top: 3px;">${studentCourse.student.name}</p>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平时成绩：</label>
			<div class="controls">
				<form:input path="classEvaValue" htmlEscape="false"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">期末成绩：</label>
			<div class="controls">
				<form:input path="finEvaValue" htmlEscape="false"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">综合成绩：</label>
			<div class="controls">
				<form:input path="evaValue" htmlEscape="false" class="input-xlarge " />
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">学分：</label>
			<div class="controls">
				<form:input path="credit" htmlEscape="false" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绩点：</label>
			<div class="controls">
				<form:input path="point" htmlEscape="false" class="input-xlarge " />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">学期：</label>
			<div class="controls">
				<select name="termYear" class="input-xlarge ">
					<c:forEach items="${fns:termYear()}" var="termYear">
						<option value="${termYear.key}"
							<c:if test="${studentCourse.termYear==termYear.key}"> selected="selected" </c:if>>${termYear.key}</option>
					</c:forEach>
				</select>
			</div>
		</div>



		<div class="control-group">
			<label class="control-label">状态标记：</label>
			<div class="controls">
				<form:select path="status" class="input-large required">
					<form:options items="${fns:getDictList('student_course_result')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="student:studentCourse:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>