<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

	<title>统招数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputSubmit").click(function(){
				layer.msg('持续更新中.', function(){
					//关闭后的操作
					});
			})
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


		<sys:message content="${message}" />

		<div class="form-actions">
			<a href="${ctx}/payment"
				class="button button-block button-rounded button-glow  button-caution  button-primary  button-large ">在线缴费</a>
				  <br>
				<a id="inputSubmit"
				class="button button-block button-rounded button-glow  button-raised  button-primary  button-large ">完善个人信息</a>
		</div>



	

</body>
</html>