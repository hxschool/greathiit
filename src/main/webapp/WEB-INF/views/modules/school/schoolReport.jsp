<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>成绩单管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			
			$('#element_id').cxSelect({ 
				  url: '${ctx}/sys/office/treeClassLink',
				  selects: ['province', 'city','clazz', 'area'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
			
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
	</ul>
	<br />
	<form id="inputForm" action="${ctx}/school/schoolReport/export"
		method="post" class="form-horizontal">

		<sys:message content="${message}" />
		
		<div class="control-group">
			<label class="control-label">学期:</label>
			<div class="controls">
				<select name="termYear" class="input-medium">
				<c:forEach items="${fns:termYear()}" var="termYear">
						<option value="${termYear.key}"
							<c:if test="${termYear.key==course.cursYearTerm}">selected</c:if>>${termYear.key}</option>
					</c:forEach>
				</select>
			</div>
		</div>


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

<fieldset>
			<legend>使用说明</legend>
			${config.tip }
		</fieldset>
	<sys:message content="${message}" />

</body>
</html>