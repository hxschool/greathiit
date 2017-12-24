<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学期初始化管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				
			if ($("#yearTerm").val() != '') {
					//设置年份
					$("#year").val($("#yearTerm").val().substring(0, 4));
					//设置学期
					$("#term option").each(function() {
						var txt = $(this).val();
						if (txt == $("#yearTerm").val().substring(4)) {
							$(this).attr("selected", 'selected');
						}
					})
				}
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										$("#yearTerm").val(
												$("#year").val()
														+ $("#term").val());
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
		<li><a href="${ctx}/course/courseYearTerm/">学期初始化列表</a></li>
		<li class="active"><a
			href="${ctx}/course/courseYearTerm/form?id=${courseYearTerm.id}">学期初始化<shiro:hasPermission
					name="course:courseYearTerm:edit">${not empty courseYearTerm.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="course:courseYearTerm:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="courseYearTerm"
		action="${ctx}/course/courseYearTerm/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="yearTerm" id="yearTerm" />
		<sys:message content="${message}" />

		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="year" id="year" class="input-medium Wdate required" required="required" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});" readonly="readonly"/> <span
					class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<select name="term" id="term" class="input-medium required" >
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	
	
</body>
</html>