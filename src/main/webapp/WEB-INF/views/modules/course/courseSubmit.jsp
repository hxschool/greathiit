<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>课程成绩参数设置</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			
			
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

		<li><a
			href="${ctx}/course/course/?cursProperty=${param.cursProperty} ">课程维护</a></li>
		<li class="active"><a
			href="${ctx}/course/course/form?id=${course.id}"> <c:if
					test="${param.cursProperty==50}">
			课程成绩参数设置
			</c:if> <c:if test="${param.cursProperty!=50}">
			课程成绩参数设置
			</c:if> <shiro:hasPermission name="course:course:edit">${not empty course.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="course:course:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<sys:message content="${message}" />
	<form:form id="inputForm" modelAttribute="course"
		action="${ctx}/course/course/submit" method="post"
		class="form-horizontal">
		<form:hidden path="id" />


		<div class="control-group">
			<label class="control-label">开设学期：</label>
			<div class="controls">
				<select name="cursYearTerm" class="input-large required">
					<c:forEach items="${fns:termYear()}" var="termYear">
						<option value="${termYear.key}"
							<c:choose>
							<c:when test="${course!=null&& course.cursYearTerm==termYear.key }">
							selected
							</c:when>
							<c:otherwise>
								<c:if test="${course==null&&termYear.key==config.termYear}">selected</c:if>
							</c:otherwise>
							</c:choose> >${termYear.key}</option>
					</c:forEach>
				</select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程编号：</label>
			<div class="controls">
				<form:input path="cursNum" htmlEscape="false" maxlength="255"
					class="input-large  " readonly="true" />

				<span class="help-inline"><font color="red">*B:本科课程,G:高职课程</font>
				</span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">课程名称：</label>
			<div class="controls">

				<form:input path="cursName" htmlEscape="false" maxlength="255"
					class="input-xlarge " readonly="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">学分：</label>
			<div class="controls">
				<form:input path="cursCredit" htmlEscape="false" maxlength="255"
					class="input-large required" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">考试时间：</label>
			<div class="controls">

				<input name="examTime" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="${course.examTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">命题人：</label>
			<div class="controls">
				<form:input path="propositioner" htmlEscape="false" maxlength="255"
					class="input-xxlarge required" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">评分人：</label>
			<div class="controls">
				<form:input path="rater" htmlEscape="false" maxlength="255"
					class="input-xxlarge required" />
			</div>
		</div>
				<div class="control-group">
			<label class="control-label">课程类型：</label>
			<div class="controls">

				<form:select path="cursType" class="input-large required">
					<form:options items="${fns:getDictList('course_curs_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">成绩类别：</label>
			<div class="controls">
				<form:select path="category" class="input-medium rater">
					<form:options items="${fns:getDictList('student_course_category')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>

			</div>
		</div>



		<div class="control-group">
			<label class="control-label">课程简介：</label>
			<div class="controls">
				<form:textarea path="cursIntro" htmlEscape="false" rows="4"
					maxlength="2000" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="2000" class="input-xxlarge " />

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="course:course:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>


</body>
</html>