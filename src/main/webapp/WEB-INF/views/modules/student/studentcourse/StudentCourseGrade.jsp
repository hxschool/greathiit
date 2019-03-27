<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>我的成绩</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
</script>
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/teacher/teacher_information.css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/teacher/teaching_management.css" />


<script type="text/javascript">
	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}

	//显示后将request里的Message清空，防止回退时重复显示。
</script>
</head>
<body>

	<div class="content">
		<div class="container">
			
				<div class="row">
					<div class="span12">
						<div class="row">
							<div class="span12 div-content-white-bgr"
								style="min-height: 440px">
								<div class="div-inf-bar">
									<label>学生成绩</label>
								</div>
								<div class="div-inf-tbl">
									<form action="?" method="post">
									<div>
											<select name="termYear" class="input-medium">
											<option value="">请选择</option>
											<c:forEach items="${fns:termYear()}" var="term">
												<option value="${term.key}">${term.value}</option>
											</c:forEach>
										</select>
										
										&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" style="margin-top: 0px;" type="submit" value="查询"/>
										&nbsp;&nbsp;<button id="printSubmit" class="btn btn-primary" style="margin-top: 0px;" type="button">打印成绩单</button>
									</div>
									</form>
									<div class="div-tchr-basic-inf">

										<table class="table table-bordered " id="stuCourseGradeList"
											style="width: 100%">
											<thead>
												<tr>
													<th style="width: 60px">课程编号</th>
													<th>课程名称</th>
													<th>学分</th>
													<th>绩点</th>
													<th>成绩</th>
													<th>授课教师</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${studentCourses}" var="studentCourse" >
												<tr>
													<td style="width: 60px">${studentCourse.course.id}</td>
													<td>${studentCourse.course.cursName}</td>
													<td>${studentCourse.credit}</td>
													<td>${studentCourse.point}</td>
													<td>${studentCourse.evaValue}</td>
													<td>${studentCourse.course.teacher.tchrName}</td>
												</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			
		</div>
	</div>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});

		var msg = "${message}";
		if (msg != "") {
			alert(msg);
		}
		
	</script>

</body>
</html>
