<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程内容管理</title>
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
	<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/teacher/course-detail.css" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="${ctxStatic}/modules/img/circle.jpg"/>
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>${course.cursName}
					</h6>
				</div>
				<div class="div-inf">
					<div class="div-inf-title">教材及参考书目</div>
					<div class="div-inner-text">
						<h5 class="gray">教材：</h5>
						<ol type="1">
							
							<c:forEach var="m" items="${cm}" varStatus="status">
       						 <li>${m.cmAuthor}，
							《%${m.cmName}》，
							<${m.cmPublisher}，
							${m.cmPubYear}</li>
							</c:forEach> 
						</ol>
						<h5 class="gray">参考书目：</h5>
						<ol type="1">
							<c:forEach var="m" items="${crb}" varStatus="status">
       						 <li>${m.cmAuthor}，
							《%${m.cmName}》，
							<${m.cmPublisher}，
							${m.cmPubYear}</li>
							</c:forEach> 
						</ol>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>