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
					<form action="Student_Performance_1_selectStuPer" method="post"
						enctype="multipart/form-data">
						<h5>
							<s:property value="currentTerm" />
						</h5>
						<div class="row">
							<div class="span2">
								年份&nbsp;&nbsp;<select id="year" name="year" class="form-control"
									style="width: 80px">
									<option value="" selected="selected">全部</option>
								</select>
							</div>
							<div class="span2">
								学期&nbsp;&nbsp;<select id="term" name="term">
									<option value="" selected="selected">全部</option>
									<option value="1">秋</option>
									<option value="2">春</option>
								</select>
							</div>
							<div class="span4">
								课程&nbsp;&nbsp;<select id="course" name="stuCursLimit.cursName" style="width: 200px">

								</select>

							</div>
							<div >
								<input class="btn" type="submit" style="margin-top:0px" value="查询成绩">
							</div>
						</div>

					</form>
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th>课程名称</th>
								<th>课程性质</th>
								<th>学分</th>
								<th>成绩</th>
								<th>开课学院</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="6">系统升级中</td>
							</tr>

							<!-- <s:iterator value="stuCurs" var="p">
											<tr>
												<td><s:property value="#p.course.cursNum" /></td>
												<td><s:property value="#p.course.cursName" /></td>
												<td><s:property value="#p.course.cursProperty" /></td>
												<td><s:property value="#p.course.cursCredit" /></td>
												<td><s:property value="#p.EvaValue" /></td>
												<td><s:property value="#p.course.dept.deptName" /></td>
											</tr>
										</s:iterator> -->
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
