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
		<li class="active"><a href="#">按学号腾出</a></li>
	</ul><br/>
	<form id="form" action="${ctx}/dorm/ucDorm/flightDormByStudentNumber" method="post" class="form-horizontal">
		<input type="hidden" name="studentDormType" value="unUcDormStudentNumberForm">
		<sys:message content="${message}"/>
		
			<div class="control-group">
				<label class="control-label">操作学号：</label>
				<div class="controls">
					<input id="studentNumber"
					name="studentNumber" type="text" maxlength="50" class="form-control"
					 placeholder="请输入学号" required="required"/> &nbsp;&nbsp;&nbsp;<span id="studentNumberMessage" style="color:green"></span>
				</div>
			</div>

		<div class="form-actions">
			<input type="hidden" id="ajax_total"> 
			<input type="hidden" id="ajax_studentNumber" value="1"> 
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="腾出"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	
	
	
	<script type="text/javascript">

		
		$(function() {
		
			$("#studentNumber").blur(function() {
				var studentNumber = $("#studentNumber").val();
				if(studentNumber!=''){
					$.post("${ctx}/dorm/ucDorm/ajaxStudentnumber", {
						studentNumber : studentNumber
					}, function(data) {
							$("#studentNumberMessage").html("");
							if(data.responseCode!='9998'){
								$("#studentNumberMessage").html(data.responseMessage);
								$("#ajax_studentNumber").val("0");
							}
							
							
							if(data.responseCode=='9998'){
								var user=data.result;
								
								$("#studentNumberMessage").html(data.responseMessage.replace("不可以分配寝室,当前学生","学员"+user.name));
								$("#ajax_studentNumber").val("1");
							}
							
						
					});
				}
				
			});
			

			var validate = $("#form").validate({
                submitHandler: function(form){
                	if($("#ajax_studentNumber").val()!='0'){
                		if(confirm("确定要当前操作吗?")){
                			form.submit();
                		}
                		
                	}
                	
                    return false;
                }          
            });    
		});
	</script>
	
</body>
</html>