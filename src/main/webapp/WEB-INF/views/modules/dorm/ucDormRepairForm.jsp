<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>���޹������</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('�����ύ�����Ե�...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("�����������ȸ�����");
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
		<li><a href="${ctx}/dorm/ucDormRepair/">���޹����б�</a></li>
		<li class="active"><a href="${ctx}/dorm/ucDormRepair/form?id=${ucDormRepair.id}">���޹���<shiro:hasPermission name="dorm:ucDormRepair:edit">${not empty ucDormRepair.id?'�޸�':'���'}</shiro:hasPermission><shiro:lacksPermission name="dorm:ucDormRepair:edit">�鿴</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ucDormRepair" action="${ctx}/dorm/ucDormRepair/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<c:if test="${ucDormRepair.id !=null }">
		<div class="control-group">
			<label class="control-label">ά����Ϣ��</label>
			<div class="controls">
				������:${ucDormRepair.user.name}<br>
				����ʱ��:<fmt:formatDate value="${ucDormRepair.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
				 λ������:${ucDormRepair.dorm.ucDormBuild.id}��${ucDormRepair.dorm.dormFloor}��${ucDormRepair.dorm.dormNumber}
			</div>
		</div>
		
		
		
		
			<div class="control-group">
				<label class="control-label">�������ͣ�</label>
				<div class="controls">
					<form:select path="repairState" class="input-xlarge">
						<form:option value="" label="Ĭ��"/>
						<form:options items="${fns:getDictList('repair_state')}" itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
		</c:if>
		
		
		<div class="control-group">
			<label class="control-label">�������ͣ�</label>
			<div class="controls">
				<form:select path="repairType" class="input-xlarge">
					<form:option value="" label="Ĭ��"/>
					<form:options items="${fns:getDictList('repair_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">�������⣺</label>
			<div class="controls">
				<form:input path="repairTitle" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">����������</label>
			<div class="controls">
				<form:input path="repairContent" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">��ϵ�绰��</label>
			<div class="controls">
				<form:input path="repairPhone" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">��ע��Ϣ��</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<c:if test="${ucDormRepair.id !=null }">
			<div class="control-group">
				<label class="control-label">�޸��ظ���Ϣ��</label>
				<div class="controls">
					<form:textarea path="repairReplace" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
				</div>
			</div>
		</c:if>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="�� ��"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="�� ��" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>