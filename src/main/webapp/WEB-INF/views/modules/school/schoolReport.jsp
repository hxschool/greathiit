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
		<li><a href="#">成绩单</a></li>
	</ul><br/>
	<form id="inputForm" action="${ctx}/school/schoolReport/export" method="post" class="form-horizontal">
		
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">时间范围:</label>
			<div class="controls">
                <input id="startYear" name="startYear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
      onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endYear\')}'},dateFmt:'yyyy')"/> - 
      <input id="endYear" name="endYear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
      onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startYear\')}'},dateFmt:'yyyy')"/>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学期:</label>
			<div class="controls">
                <select name="n" class="input-medium">
                	<option value="1">1</option>
                	<option value="2">2</option>
                	<option value="3">3</option>
                	<option value="4">4</option>
                </select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">所属学院:</label>
			<div class="controls">
                <sys:treeselect id="company" name="department" value="" labelName="company.name" labelValue=""
					title="学院" url="/sys/office/treeData?grade=2" cssClass="required" notAllowSelectParent="true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">所属专业:</label>
			<div class="controls">
                <sys:treeselect id="office" name="specialty" value="" labelName="office.name" labelValue=""
					title="专业" url="/sys/office/treeData?grade=3" cssClass="required" notAllowSelectParent="true"  />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">所属班级:</label>
			<div class="controls">
                <sys:treeselect id="clazz" name="clazz" value="" labelName="clazz.name" labelValue=""
					title="班级" url="/sys/office/treeData?type=4" cssClass="required" notAllowSelectParent="true"  />
			</div>
		</div>
		
		
		
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="生成成绩单"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	
	
	
</body>
</html>