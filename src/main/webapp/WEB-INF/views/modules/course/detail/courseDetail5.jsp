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
					<div class="div-inf-title">本课程对培养学生能力和素质的贡献点</div>
					<div class="div-inner-text">在课堂教学中，通过讲授、提问、讨论、演示等教学方法和手段，提高学生逻辑思维能力和表述能力，锻炼学生对知识和观点的表达，同时也能提高学习兴趣。
						教学中设置相应的案例分析和教学录像，设计合理的课堂任务，组织学生进行分组讨论，借此激发学生的潜能，同时向学生讲述分析问题的方法，提高学生对知识
						的应用能力。结合企业发展动态，对制造技术新的应用方向和应用内容进行扩充讲解，并可以发动学生进行有关调研，以提高学生学习兴趣。课后鼓励学生充分利
						用网络信息资源，搜集相关案例和学习资源，扩充课堂教学内容涉及的知识和应用。</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>