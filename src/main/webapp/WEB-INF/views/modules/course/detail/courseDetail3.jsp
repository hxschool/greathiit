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
					<div class="div-inf-title">课程具体内容及基本要求</div>
				</div>
				
				<c:forEach var="c" items="${csc}" varStatus="s">
					<div class="div-curs-detail">
						<h5 class="gray">（${s.index+1}）${c.cscChapter}（${c.cscClassHour}学时）（支撑${c.cscTarget}）</h5>
						<label>${c.cscGoal}</label><br /> <label>1.基本要求</label><br />
						<label>${c.cscBasRequ}</label><br /> <label>2.重点、难点</label>
						<br /> <label>重点：${c.cscStudyEmpha}</label><br /> <label>难点：${c.cscStudyDiffi}</label><br /> <label>3.作业及课外学习要求：</label><br />
						<label>${c.cscHomework}</label><br />
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>