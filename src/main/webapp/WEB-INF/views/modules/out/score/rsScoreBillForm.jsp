<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试成绩单管理</title>
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
		<li><a href="${ctx}/out/score/rsScoreBill/">考试成绩单列表</a></li>
		<li class="active"><a href="${ctx}/out/score/rsScoreBill/form?id=${rsScoreBill.id}">考试成绩单<shiro:hasPermission name="out:jcd:rsScoreBill:edit">${not empty rsScoreBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="out:jcd:rsScoreBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rsScoreBill" action="${ctx}/out/score/rsScoreBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">考生号：</label>
			<div class="controls">
				<form:input path="ksh" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="xm" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="sfzh" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总分：</label>
			<div class="controls">
				<form:input path="zf" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投档成绩：</label>
			<div class="controls">
				<form:input path="cj" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目1：</label>
			<div class="controls">
				<form:input path="km1" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目2：</label>
			<div class="controls">
				<form:input path="km2" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目3：</label>
			<div class="controls">
				<form:input path="km3" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目4：</label>
			<div class="controls">
				<form:input path="km4" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意向专业1：</label>
			<div class="controls">
				<form:input path="zy1" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意向专业2：</label>
			<div class="controls">
				<form:input path="zy2" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意向专业3：</label>
			<div class="controls">
				<form:input path="zy3" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意向专业4：</label>
			<div class="controls">
				<form:input path="zy4" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意向专业5：</label>
			<div class="controls">
				<form:input path="zy5" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">意向专业6：</label>
			<div class="controls">
				<form:input path="zy6" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> -->
		<div class="control-group">
			<label class="control-label">是否服从专业调剂：</label>
			<div class="controls">
				<form:input path="zytj" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<!-- <shiro:hasPermission name="out:jcd:rsScoreBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> -->
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>