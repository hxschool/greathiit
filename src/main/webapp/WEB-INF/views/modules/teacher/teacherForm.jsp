<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>教师信息管理</title>
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
		<li><a href="${ctx}/teacher/teacher/">教师信息列表</a></li>
		<li class="active"><a
			href="${ctx}/teacher/teacher/form?id=${teacher.id}">教师信息<shiro:hasPermission
					name="teacher:teacher:edit">${not empty teacher.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="teacher:teacher:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="teacher"
		action="${ctx}/teacher/teacher/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<!-- 选项卡菜单-->
		<ul id="myTab" class="nav nav-pills" role="tablist">
			<li class="active"><a href="#bulletin" role="tab"
				data-toggle="pill">基础信息</a></li>
			<li><a href="#rule" role="tab" data-toggle="pill">联系信息</a></li>
			<li><a href="#forum" role="tab" data-toggle="pill">学历信息</a></li>
			<li><a href="#security" role="tab" data-toggle="pill">自我介绍</a></li>
		</ul>
		<!-- 选项卡面板 -->
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="bulletin">
				<div class="control-group">
					<label class="control-label">教师号：</label>
					<div class="controls">
						<form:input path="user.no" htmlEscape="false" maxlength="255"
							class="input-xlarge " readonly="readonly"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">姓名：</label>
					<div class="controls">
						<form:input path="tchrName" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">身份证号：</label>
					<div class="controls">
						<form:input path="tchrIdcard" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">性别：</label>
					<div class="controls">
						<form:select path="tchrGender" class="input-xlarge ">
							<form:options items="${fns:getDictList('sex')}" itemLabel="label"
								itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">生日：</label>
					<div class="controls">
						<input name="tchrBirthday" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${teacher.tchrBirthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">民族：</label>
					<div class="controls">

						<form:select path="tchrNation" class="input-xlarge ">
							<form:options items="${fns:getDictList('nation')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">政治面貌：</label>
					<div class="controls">

						<form:select path="tchrPolitical" class="input-xlarge ">
							<form:options items="${fns:getDictList('political')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="rule">

				<div class="control-group">
					<label class="control-label">联系电话：</label>
					<div class="controls">
						<form:input path="tchrPhone" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">办公地点：</label>
					<div class="controls">
						<form:input path="tchrOfficeAddr" htmlEscape="false"
							maxlength="255" class="input-xlarge " />
					</div>
				</div>


				<div class="control-group">
					<label class="control-label">email：</label>
					<div class="controls">
						<form:input path="tchrEmail" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">邮政编码：</label>
					<div class="controls">
						<form:input path="tchrFax" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="forum">

				<div class="control-group">
					<label class="control-label">毕业院校：</label>
					<div class="controls">
						<form:input path="tchrGraduateSchool" htmlEscape="false"
							maxlength="255" class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">毕业专业：</label>
					<div class="controls">
						<form:input path="tchrMajor" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">学位：</label>
					<div class="controls">
						<form:select path="tchrDegree" class="input-xlarge ">
							<form:options items="${fns:getDictList('sys_edu')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">职称：</label>
					<div class="controls">
						<form:input path="tchrTitle" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">任职日期：</label>
					<div class="controls">
						<input name="tchrAttendDate" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${teacher.tchrAttendDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">曾供职院校：</label>
					<div class="controls">
						<form:input path="tchrSchool" htmlEscape="false" maxlength="10"
							class="input-xlarge " />
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="security">
				<div class="control-group">
					<label class="control-label">自我介绍：</label>
					<div class="controls">
						<form:input path="tchrSelfIntroduce" id="tchrSelfIntroduce"
							htmlEscape="false" class="input-xlarge " />
						<sys:ckeditor replace="tchrSelfIntroduce" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">英文自我介绍：</label>
					<div class="controls">
						<form:input path="tchrEngSelfIntroduce" id="tchrEngSelfIntroduce"
							htmlEscape="false" class="input-xlarge " />
						<sys:ckeditor replace="tchrEngSelfIntroduce" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">remarks：</label>
					<div class="controls">
						<form:textarea path="remarks" id="remarks" htmlEscape="false"
							rows="4" maxlength="200" class="input-xxlarge " />
						<sys:ckeditor replace="remarks" />
					</div>
				</div>
			</div>
		</div>









		<div class="form-actions">
			<shiro:hasPermission name="teacher:teacher:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>



</body>
</html>