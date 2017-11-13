<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
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
	
	<form:form id="inputForm" modelAttribute="dict" action="${ctx}/out/system/dict/sv" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">开关设置:</label>
			<div class="controls">
				<select name="value">
					<option value="yes" <c:if test="${dict.value=='yes'}">selected="selected"</c:if>  >开</option>
					<option value="no" <c:if test="${dict.value=='no'}">selected="selected"</c:if> >关</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关闭提示语:</label>
			<div class="controls">
				<form:textarea id="description" htmlEscape="false" path="description" rows="4" class="input-xxlarge"/>
				
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>