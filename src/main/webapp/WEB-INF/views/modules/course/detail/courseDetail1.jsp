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
					<div class="div-inf-title">基本信息</div>
					<div class="div-detail">
						<label>课程编号：</label> <label>${course.cursNum }</label>
					</div>
					<div class="div-detail">
						<label>课程名称：</label> <label>${course.cursName}</label>
					</div>
					<div class="div-detail">
						<label>英文名称：</label> <label>${course.cursEngName}</label>
					</div>
					<div class="div-detail">
						<label>学分/学时：</label> <label>${course.cursCredit}/${course.cursClassHour}</label>
					</div>
					<div class="div-detail">
						<label>课程性质：</label> <label>${course.cursProperty}</label>
					</div>
					<div class="div-detail">
						<label>适用专业：</label> <label>试用单位</label>
					</div>
					<div class="div-detail">
						<label>开设学期：</label> <label>${course.cursYearTerm}</label>
					</div>
					<div class="div-detail">
						<label>先修课程：</label> <label>${course.cursPreCourses}</label>
					</div>
					
					<div class="div-detail">
					<label>课程类型：</label> <label>
					
					
					<c:choose>
   <c:when test="${course.cursType=='normal'}"> 
        考试课程
   </c:when>
  <c:when test="${course.cursType=='experiment'}"> 
        实验课程
   </c:when>
   <c:when test="${course.cursType=='graduation-project'}"> 
        毕业设计
   </c:when>
</c:choose>
					</label>	
					</div>
					
					<div class="div-detail">
						<label>开课单位：</label> <label>单位</label>
					</div>
				</div>

				<div class="div-inf">
					<div class="div-inf-title">课程简介</div>
					<div class="div-inner-text">
						${course.cursIntro}
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>