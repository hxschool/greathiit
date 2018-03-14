<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学院教室管理管理</title>
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
		<li><a href="${ctx}/school/schoolCourse/">学院教室管理列表</a></li>
		<li class="active"><a href="${ctx}/school/schoolCourse/form?id=${schoolCourse.id}">学院教室管理<shiro:hasPermission name="school:schoolCourse:edit">${not empty schoolCourse.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="school:schoolCourse:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="schoolCourse" action="${ctx}/school/schoolCourse/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">选择学院：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${schoolCourse.office.id}" labelName="office.name" labelValue="${schoolCourse.office.name}"
					title="部门" url="/sys/office/treeData?grade=2" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div id="top">
		
		<div class="control-group">
			<label class="control-label">选择教学楼：</label>
			<div class="controls">
			<select class="h_school" style="width: 100px;"></select><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">选择教室：</label>
			<div class="controls">
				<select id="address" class="address" name="schoolRoots"  style="width: 200px;" multiple="true"> </select>
				<span class="help-inline"><font color="red">只能针对一个教学楼添加教室,如跨教学楼需要再次操作</font> </span>
			</div>
		</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;<shiro:hasPermission name="school:schoolCourse:edit"></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	<script type="text/javascript">
	
	$('#top').cxSelect({ 
		  url: '${ctx}/school/schoolRoot/treeLinkId',
		  selects: ['h_school', 'address'], 
		  jsonName: 'name',
		  jsonValue: 'value',
		  jsonSub: 'sub'
		}); 
	</script>
</body>
</html>