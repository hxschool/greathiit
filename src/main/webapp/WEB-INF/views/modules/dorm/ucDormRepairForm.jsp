<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报修管理管理</title>
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
		<li><a href="${ctx}/dorm/ucDormRepair/">报修管理列表</a></li>
		<li class="active"><a href="${ctx}/dorm/ucDormRepair/form?id=${ucDormRepair.id}">报修管理<shiro:hasPermission name="dorm:ucDormRepair:edit">${not empty ucDormRepair.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dorm:ucDormRepair:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ucDormRepair" action="${ctx}/dorm/ucDormRepair/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<c:if test="${ucDormRepair.id !=null }">
		<div class="control-group">
			<label class="control-label">维修信息：</label>
			<div class="controls">
				报修人:${ucDormRepair.user.name}<br>
				报修时间:<fmt:formatDate value="${ucDormRepair.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
				 位置坐标:${ucDormRepair.dorm.ucDormBuild.id}栋${ucDormRepair.dorm.dormFloor}层${ucDormRepair.dorm.dormNumber}
			</div>
		</div>
		
		
		
		
			<div class="control-group">
				<label class="control-label">操作类型：</label>
				<div class="controls">
					<form:select path="repairState" class="input-xlarge">
						<form:option value="" label="默认"/>
						<form:options items="${fns:getDictList('repair_state')}" itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
		</c:if>
		
		
		<div class="control-group">
			<label class="control-label">报修类型：</label>
			<div class="controls">
				<form:select path="repairType" class="input-xlarge">
					<form:option value="" label="默认"/>
					<form:options items="${fns:getDictList('repair_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">报修主题：</label>
			<div class="controls">
				<form:input path="repairTitle" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修描述：</label>
			<div class="controls">
				<form:input path="repairContent" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="repairPhone" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<c:if test="${ucDormRepair.id !=null }">
			<div class="control-group">
				<label class="control-label">修复回复信息：</label>
				<div class="controls">
					<form:textarea path="repairReplace" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
				</div>
			</div>
		</c:if>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>