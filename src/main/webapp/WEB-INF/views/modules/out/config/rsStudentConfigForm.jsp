<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统配置管理</title>
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
		
		<li class="active"><a href="${ctx}/out/config/rsStudentConfig/form?id=${rsStudentConfig.id}">系统配置<shiro:hasPermission name="out:config:rsStudentConfig:edit">${not empty rsStudentConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="out:config:rsStudentConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rsStudentConfig" action="${ctx}/out/config/rsStudentConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">学期：</label>
			<div class="controls">
				<form:select path="yearTerm" items="${fns:termYear()}" class="input-medium"/>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招生系统(开启/关闭)：</label>
			<div class="controls">
			
				
				<form:select path="sw" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('switch_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">分数线(开启/关闭)：</label>
			<div class="controls">
			
				
				<form:select path="scoreSw" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('switch_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> 默认设置关闭状态,如设置开启状态那么就按照招生计划录取分数线录取
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关闭提示：</label>
			<div class="controls">
				<form:textarea path="tip" htmlEscape="true" rows="4" class="input-xxlarge "/>
				<sys:ckeditor replace="tip" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">特别提示：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" class="input-xxlarge "/>
				<sys:ckeditor replace="description" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
				<sys:ckeditor replace="remarks" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="out:config:rsStudentConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>