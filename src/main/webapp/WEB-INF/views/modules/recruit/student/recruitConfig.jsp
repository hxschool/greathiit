<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统招数据管理</title>
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
	<br/>
	<form  id="inputForm" action="${ctx}/recruit/student/recruitStudent/assign/operation" method="post" class="form-horizontal">
	
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">设置专业：</label>
			<div class="controls">
				<select name="majorId" class="input-xlarge required">
				<option>请选择</option>
				<c:forEach var="rtm" items="${list}" varStatus="status">
				<c:if test="${rtm.flagShow=='0'}">
					<option value="${rtm.majorId}">${rtm.majorName}</option>
				</c:if>
				</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">分班模式：</label>
			<div class="controls">

				<select name="modeSwitch" class="input-xlarge required">
				<option>请选择</option>
				<option value="1">自动模式</option>
				<option value="2">手动模式</option>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
</body>
</html>