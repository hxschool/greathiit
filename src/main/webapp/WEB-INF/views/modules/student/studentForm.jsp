<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学生信息管理</title>
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
		<li><a href="${ctx}/student/student/">学生信息列表</a></li>
		<li class="active"><a
			href="${ctx}/student/student/form?id=${student.id}">学生信息<shiro:hasPermission
					name="student:student:edit">${not empty student.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="student:student:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="student"
		action="${ctx}/student/student/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />


		<ul id="myTab" class="nav nav-pills" role="tablist">
			<li class="active"><a href="#bulletin" role="tab"
				data-toggle="pill">基础信息</a></li>
			<li><a href="#rule" role="tab" data-toggle="pill">学习目标</a></li>
			<li><a href="#forum" role="tab" data-toggle="pill">家庭信息</a></li>
			<li><a href="#security" role="tab" data-toggle="pill">简介</a></li>
			<li><a href="#face" role="tab" data-toggle="pill">相片</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="bulletin">
				<div class="control-group">
					<label class="control-label">考试号：</label>
					<div class="controls">
						<form:input path="exaNumber" htmlEscape="false" maxlength="64"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学号：</label>
					<div class="controls">
						<form:input path="studentNumber" htmlEscape="false" maxlength="64"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">姓名：</label>
					<div class="controls">
						<form:input path="name" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">身份证号：</label>
					<div class="controls">
						<form:input path="idCard" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">生日：</label>
					<div class="controls">
						<input name="birthday" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">性别：</label>
					<div class="controls">


						<form:select path="gender" class="input-xlarge ">
							<form:options items="${fns:getDictList('sex')}" itemLabel="label"
								itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">民族：</label>
					<div class="controls">


						<form:select path="nation" class="input-xlarge ">
							<form:options items="${fns:getDictList('nation')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">政治面貌：</label>
					<div class="controls">


						<form:select path="political" class="input-xlarge ">
							<form:options items="${fns:getDictList('political')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学历：</label>
					<div class="controls">
						<form:select path="edu" class="input-xlarge ">
							<form:options items="${fns:getDictList('student_edu')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">学制：</label>
					<div class="controls">

						<form:select path="studentLength" class="input-xlarge ">
							<form:options items="${fns:getDictList('student_school_system')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">户口所在地：</label>
					<div class="controls">
						<form:input path="nativePlace" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">联系电话：</label>
					<div class="controls">
						<form:input path="phone" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">联系地址：</label>
					<div class="controls">
						<form:input path="address" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">email：</label>
					<div class="controls">
						<form:input path="mail" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">QQ：</label>
					<div class="controls">
						<form:input path="qq" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">微信：</label>
					<div class="controls">
						<form:input path="wechat" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>

			</div>
			<div class="tab-pane fade" id="rule">
				
				<div class="control-group">
					<label class="control-label">短期目标：</label>
					<div class="controls">
							<form:textarea path="shortGoal" htmlEscape="false" rows="4"
							maxlength="2000" class="input-xxlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">中期目标：</label>
					<div class="controls">
							<form:textarea path="midGoal" htmlEscape="false" rows="4"
							maxlength="2000" class="input-xxlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">长期目标：</label>
					<div class="controls">
							<form:textarea path="longGoal" htmlEscape="false" rows="4"
							maxlength="2000" class="input-xxlarge " />
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="forum">

				<div class="control-group">
					<label class="control-label">父亲职业：</label>
					<div class="controls">
						<form:input path="fatherWorks" htmlEscape="false"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">父亲联系方式：</label>
					<div class="controls">
						<form:input path="fatherPhone" htmlEscape="false"
							class="input-xlarge " />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">母亲职业：</label>
					<div class="controls">
						<form:input path="motherWorks" htmlEscape="false"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">母亲联系方式：</label>
					<div class="controls">
						<form:input path="motherPhone" htmlEscape="false"
							class="input-xlarge " />
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="security">
				<div class="control-group">
					<label class="control-label">英文简介：</label>
					<div class="controls">
							<form:textarea path="selfEngIntroduce" htmlEscape="false" rows="4"
							maxlength="2000" class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">中文简介：</label>
					<div class="controls">
							<form:textarea path="selfIntroduce" htmlEscape="false" rows="4"
							maxlength="2000" class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">remarks：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="4"
							maxlength="2000" class="input-xxlarge " />
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="face">
				<img src="${student.face}" class="img-rounded">
		
			</div>


			<div class="form-actions">
				<shiro:hasPermission name="student:student:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="保 存" />&nbsp;</shiro:hasPermission>
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
			</div>
	</form:form>
</body>
</html>