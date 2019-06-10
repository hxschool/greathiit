<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>答辩抽签管理-导出学生信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#element_id').cxSelect({ 
				  url: '${ctx}/sys/office/treeClassLink',
				  selects: ['province', 'city','clazz', 'area'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
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
		<li class="active"><a href="#">导出学生信息</a></li>
		
	</ul><br/>
	<form id="inputForm" action="${ctx}/school/schoolReport/export"
		method="post" class="form-horizontal">

		<sys:message content="${message}" />
		


		<div id="element_id">

			<div class="control-group">
				<label class="control-label">所属学院:</label>
				<div class="controls">

					<select class="province input-medium" name="department"><option>请选择</option></select>

				</div>

			</div>

			<div class="control-group">
				<label class="control-label">所属专业:</label>
				<div class="controls">
					<select id="city" class="city input-medium" name="specialty"><option>请选择</option></select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">年级:</label>
				<div class="controls">
					<select id="clazz" class="clazz input-medium"><option>请选择</option></select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">所属班级:</label>
				<div class="controls">
					<select id="area" class="area input-medium" name="clazz"><option>请选择</option></select>
				</div>
			</div>
		</div>






		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="生成成绩单" /> <input id="btnCancel" class="btn" type="button"
				value="返 回" onclick="history.go(-1)" />
		</div>
	</form>
</body>
</html>