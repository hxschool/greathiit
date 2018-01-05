<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>招生计划管理</title>
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
		<li><a href="${ctx}/out/jcd/rsZsjh/">招生计划列表</a></li>
		<li class="active"><a href="${ctx}/out/jcd/rsZsjh/form?id=${rsZsjh.id}">招生计划<shiro:hasPermission name="out:jcd:rsZsjh:edit">${not empty rsZsjh.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="out:jcd:rsZsjh:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rsZsjh" action="${ctx}/out/jcd/rsZsjh/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="status" value="0"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">专业名称：</label>
			<div class="controls">
				<form:select path="majorType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('greathiit_zhaosheng_major')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业人数：</label>
			<div class="controls">
				<form:input path="majorCount" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">扩展1：</label>
			<div class="controls">
				<form:input path="zy1" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		 -->
		
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="out:jcd:rsZsjh:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>