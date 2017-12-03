<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>宿舍管理管理</title>
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
		<li><a href="${ctx}/dorm/ucDorm/">宿舍管理列表</a></li>
		<li class="active"><a href="${ctx}/dorm/ucDorm/form?id=${ucDorm.id}">宿舍管理<shiro:hasPermission name="dorm:ucDorm:edit">${not empty ucDorm.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dorm:ucDorm:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ucDorm" action="${ctx}/dorm/ucDorm/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">公寓号：</label>
			<div class="controls">
				<form:input path="dormbuildId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宿舍门牌号：</label>
			<div class="controls">
				<form:input path="dormNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">楼层：</label>
			<div class="controls">
				<form:input path="dormFloor" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已入住人数：</label>
			<div class="controls">
				
				${ucDorm.cnt }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可入住人数：</label>
			<div class="controls">
				${ucDorm.total-ucDorm.cnt }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总人数：</label>
			<div class="controls">
				${ucDorm.total }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">寝室长：</label>
			<div class="controls">
				<form:input path="master" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dorm:ucDorm:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>