<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单招报名申请表管理</title>
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
		<li><a href="${ctx}/out/system/systemStudent/">单招报名申请表列表</a></li>
		<li class="active"><a href="${ctx}/out/system/systemStudent/form?id=${systemStudent.id}">单招报名申请表<shiro:hasPermission name="out:system:systemStudent:edit">${not empty systemStudent.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="out:system:systemStudent:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="systemStudent" action="${ctx}/out/system/systemStudent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">顺序号：</label>
			<div class="controls">
				<input name="hcFormArea" type="text" readonly="readonly" maxlength="64" class="input-xlarge "
					value="${systemStudent.hcFormArea}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在省份：</label>
			<div class="controls">
				<form:input path="hcFormProvince" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在城市：</label>
			<div class="controls">
				<form:input path="hcFormCity" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科类：</label>
			<div class="controls">
				<form:input path="hcFormKl" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报考号：</label>
			<div class="controls">
				<form:input path="hcFormBkh" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="hcFormXm" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">出生日期：</label>
			<div class="controls">
				<input name="hcFormBirth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${systemStudent.hcFormBirth}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:input path="hcFormXb" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="hcFormSfzh" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">毕业类别：</label>
			<div class="controls">
				<form:input path="hcFormBylb" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	
		
		<div class="control-group">
			<label class="control-label">毕业学校：</label>
			<div class="controls">
				<form:input path="hcFormByxx" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">联系方式：</label>
			<div class="controls">
				<form:input path="hcFormSj" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">意向专业1：</label>
			<div class="controls">
				<form:input path="hcFormZy1" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意向专业2：</label>
			<div class="controls">
				<form:input path="hcFormZy2" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意向专业3：</label>
			<div class="controls">
				<form:input path="hcFormZy3" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意向专业4：</label>
			<div class="controls">
				<form:input path="hcFormZy4" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">意向专业5：</label>
			<div class="controls">
				<form:input path="hcFormZy5" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否服从专业调剂：</label>
			<div class="controls">
				<form:input path="hcFormZytj" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">与本人关系：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrFaGx" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人姓名：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrFaName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrFaTel" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作单位：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrFaWork" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任何职务：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrFaZw" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">与本人关系：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrMaGx" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人姓名：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrMaName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrMaTel" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作单位：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrMaWork" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任何职务：</label>
			<div class="controls">
				<form:input path="hcFormJjlxrMaZw" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备用联系方式：</label>
			<div class="controls">
				<form:input path="hcFormBysj" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">qq：</label>
			<div class="controls">
				<form:input path="hcFormQq" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="hcFormBz" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="hcFormZhuangtai" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="out:system:systemStudent:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>