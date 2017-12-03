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
	<form action="${ctx}/dorm/ucDorm/save" method="post" class="form-horizontal">
		
		<sys:message content="${message}"/>
		


			
			<div class="control-group">
				<label class="control-label">学号：</label>
				<div class="controls">
					<input id="studentNumber"
					name="studentNumber" type="text" maxlength="50" class="input-small"
					 /> &nbsp;&nbsp;&nbsp;<span id="studentNumberMessage" style="color:red"></span>
				</div>
				
				
			</div>

			

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	
	<script>
		
		$(function() {
			$("#studentNumber").blur(function() {
				$.post("${ctx}/dorm/ucDorm/ajaxStudentnumber", {
					studentNumber : $("#studentNumber").val()
				}, function(data) {
						$("#studentNumberMessage").html(data.responseMessage);
						if(data.responseCode=="9998"){
							var btn ='<input id="btnStudentNumber" class="btn btn-danger " type="button" value="{studentNumber}"/></span>';
							
							var val = "腾出学员:<:"+$("#studentNumber").val()+":>";
							btn = btn.replace("{studentNumber}",val);
							
							$("#studentNumberMessage").html($("#studentNumberMessage").html() + btn);
						}
					
				});
			});

		});
	</script>
	
</body>
</html>