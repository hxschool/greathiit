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
					<div class="div-inf-title">教学安排及方式</div>
					<div class="div-curs-plan">
						<label></label>
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th width="10%">序号</th>
									<th width="60%">课程内容</th>
									<th width="10%">学时</th>
									<th width="20%">教学方式</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="c" items="${ctm}" varStatus="s">
								<tr>
									<td>${s.index+1}</td>
									<td class="left">${c.cursContent}</td>
									<td>${c.period}</td>
									<td>${c.teacMethod}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>