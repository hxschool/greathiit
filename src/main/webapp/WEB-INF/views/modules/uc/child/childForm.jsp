<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="label" scope="page" value='${fns:getDictLabel(office.type, "sys_office_type", "")}'/>
<html>
<head>
<title>${label}管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#name").focus();
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
		<li><a
			href="${ctx}/uc/child/list?grade=${office.grade}&id=${office.parent.id}&parentIds=${office.parentIds}">列表</a></li>
		<li class="active"><a
			href="${ctx}/uc/child/form?grade=${office.grade}&id=${office.id}&parent.id=${office.parent.id}"><shiro:hasPermission
					name="sys:office:edit">${not empty office.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="office"
		action="${ctx}/uc/child/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="area.id" value="230100"/>
		
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">所属${fns:getDictLabel(office.type-1, "sys_office_type", "")}:</label>
			<div class="controls">
			
				<sys:treeselect id="office" name="parent.id"
					value="${office.parent.id}" labelName="parent.name"
					labelValue="${office.parent.name}" title=''
					url="/sys/office/treeData?grade=${office.grade-1}" extId="${office.id}" cssClass=""
					 allowClear="${office.currentUser.admin}"/>
					
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">${label}名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">${label}编码:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group hide">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:select path="type" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group hide">
			<label class="control-label">级别:</label>
			<div class="controls">
				<form:select path="grade" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_grade')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group gender">
			<label class="control-label">班级人数(男):</label>
			<div class="controls">
				<form:input path="male" htmlEscape="false" maxlength="50" />
			</div>
		</div>

		<div class="control-group gender">
			<label class="control-label">班级人数(女):</label>
			<div class="controls">
				<form:input path="female" htmlEscape="false" maxlength="50" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">是否可用:</label>
			<div class="controls">
				<form:select path="useable">
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline">“是”代表此账号允许登陆，“否”则表示此账号不允许登陆</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">班主任:</label>
			<div class="controls">
				<sys:treeselect id="primaryPerson" name="primaryPerson.id"
					value="${office.primaryPerson.id}"
					labelName="office.primaryPerson.name"
					labelValue="${office.primaryPerson.name}" title="用户"
					url="/sys/office/treeData?type=3" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">导员:</label>
			<div class="controls">
				<sys:treeselect id="deputyPerson" name="deputyPerson.id"
					value="${office.deputyPerson.id}"
					labelName="office.deputyPerson.name"
					labelValue="${office.deputyPerson.name}" title="用户"
					url="/sys/office/treeData?type=3" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3"
					maxlength="200" class="input-xlarge" />
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="sys:office:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("#officeId").val().length == 2 && ${office.grade==4}) {
				$(".gender").show();
			} else {
				$(".gender").hide();
			}
			$("#name").blur(function() {
				if ($(this).val() != null && $(this).val() != "") {
					$.post("${ctx}/uc/child/generateSequenceNumber", {
						"parent.id" : $("#officeId").val(),
						"name" : $("#name").val()
					}, function(result) {
						var data = result;
						if (data.code != '20000000') {
							if($("#id").val()==""){
								$("#code").val("");
								layer.msg(data.msg);
							}
						} else {
							$("#code").val(data.msg);
						}
					});
				}

			});
		});
		function officeTreeselectCallBack(v, h, f) {//回调sys:treeselect 方法用法
			if ("ok" == v) {//点击确认
				if ($("#officeId").val().length == 2) {
					$(".gender").show();
				} else {
					$(".gender").hide();
				}
			}
		}
	</script>
</body>
</html>