<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

<meta charset="UTF-8">
<meta charset="UTF-8">
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/teacher/student_information.css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/teacher/teaching_management.css" />
<script type="text/javascript">
	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}
	$(function() {
		var $termSelect = $("#year");
		var d = new Date();
		var year = d.getFullYear();//获取当前年份
		for(var i=year-5;i<=year+5;i++){
			var $Option1 = $("<option value='" + (i) + "'>" + (i)
					+ "</option>");
			$termSelect.append($Option1);
		}
		
	});
</script>
</head>

<body>
	<div class="container">

		<div class="row">
			<div class="span12 div-content-white-bgr">
				<div class="div-inf-bar">
					<label>学生成绩查询</label>
				</div>
				<div class="div-inf-tbl">

						<form:form id="searchForm" modelAttribute="studentCourse"
		action="${ctx}/student/student/Student_Performance" method="post"
		class="breadcrumb form-search">
						<h5>
							
						</h5>
						<div class="row">
							<div class="span3">
								<form:select path="termYear" class="input-medium" style="width: 235px">
									<form:option value="" label="--学期--" />
									<form:options items="${fns:termYear()}" htmlEscape="false" />
								</form:select>
							</div>

							<div class="span4">
								<select id="course" name="course.id" style="width: 315px">
										<option value="">--课程--</option>
									<c:forEach items="${courses}" var="course">
										<option value="${course.id}">${course.cursName}</option>
									</c:forEach>
								</select>

							</div>
							<div class="span1">
								<input class="btn" type="submit" style="margin-top:0px" value="查询成绩">
							</div>
						</div>

					</form:form>
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th>课程名称</th>
								<th>平时成绩</th>
								<th>期末成绩</th>
								<th>综合成绩</th>
								<th>学分</th>
								<th>绩点</th>
								<th>开课年份</th>
								<th>授课教师</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${studentCourses}" var="sc">
								<tr>
									<td>${sc.course.cursName}</td>
									<td>${sc.classEvaValue}</td>
									<td>${sc.finEvaValue}</td>
									<td>${sc.evaValue}</td>
									<td>${sc.credit}</td>
									<td>${sc.point}</td>
									<td>${sc.termYear}</td>
									<td>${sc.course.teacher.tchrName}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>



	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
