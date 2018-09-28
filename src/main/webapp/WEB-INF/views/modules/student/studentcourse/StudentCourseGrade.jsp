<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生参与项目页面</title>
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
	<link rel="stylesheet" href="${ctxStatic}/modules/teacher/teacher_information.css" />
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/teaching_management.css" />


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
						<div class="span12 div-content-white-bgr" style="min-height: 440px">
							<div class="div-inf-bar">
								<label>学生成绩</label>
							</div>
							<div class="div-inf-tbl">
								<div>
									<span class="text-size left-distance">查询学年：</span><input
										type="text" name="startSchoolYear" id="startSchoolYear"
										style="width: 40px" onchange="changeEndYear()"
										onFocus="WdatePicker(WdatePicker({lang:'zh-cn',dateFmt:'yyyy',readOnly:'true'})) ">
									<span class="text-size ">至</span>&nbsp;&nbsp;<input type="text"
										name="endSchoolYear" id="endSchoolYear" style="width: 40px"
										readOnly>&nbsp;&nbsp;<input type="button"
										id="evaSummary" class="btn left-distance btn-bottom"
										value="查询成绩" onclick="selectCourseGrades(1)">
								</div>
								<div class="div-tchr-basic-inf">

									<table class="table table-bordered " id="stuCourseGradeList"
										style="width: 100%">
										<thead>
											<tr>
												<th style="width: 60px">课程编号</th>
												<th>课程名称</th>
												<th>学分</th>
												<th>学时</th>
												<th>学科性质</th>
												<th>授课教师</th>
												<th>成绩</th>
											</tr>
										</thead>
										<tbody>
										<tr><td colspan="7">系统升级中</td></tr>
											
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
		//显示后将request里的Message清空，防止回退时重复显示。

		//当选择学年开始时间之后给定结束时间
		function changeEndYear() {
			var startSchoolYear = $("#startSchoolYear").val();
			document.getElementById("endSchoolYear").value = parseInt(startSchoolYear) + 1;
		}
		function selectCourseGrades(page) {
			var startSchoolYear = $("#startSchoolYear").val();
			var endSchoolYear = $("#endSchoolYear").val();
			var schoolYear = startSchoolYear + "-" + endSchoolYear;
			$("#stuCourseGradeList tbody").html("");
			$.getJSON("Json_selectStuCourseGrades", {
				schoolYear : schoolYear,
				page : page
			}, function(data) {
				$("#page").html(data.pbStuCours.page);
				$("#totalPage").html(data.pbStuCours.totalPage);
				if (data.pbStuCours.list.length == 0) {
					alert("未找到相关数据！");
				} else {
					$.each(data.pbStuCours.list, function(i, value) {

						$("#stuCourseGradeList").append(
								"<tr><td>" + value.course.cursNum + "</td><td>"
										+ value.course.cursName + "</td><td>"
										+ value.course.cursCredit + "</td><td>"
										+ value.course.cursClassHour
										+ "</td><td>"
										+ value.course.cursProperty
										+ "</td><td>"
										+ value.course.teacher.tchrName
										+ "</td><td>" + value.evaValue
										+ "</td></tr>");
					});
				}

			});
		}
		function upPage() {
			var page = parseInt($("#page").html());
			if (page == 1) {
				alert("这已经是第一页！");
			} else {
				selectCourseGrades(page - 1);
			}
		}
		function downPage() {
			var totalPage = parseInt($("#totalPage").html());
			var page = parseInt($("#page").html());
			if (page == totalPage) {
				alert("这已经是最后一页！");
			} else {
				selectCourseGrades(page + 1);
			}
		}
	</script>

</body>
</html>
