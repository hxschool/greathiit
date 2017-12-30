<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>教师信息管理</title>
<meta name="decorator" content="default" />
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
	href="${ctxStatic}/modules/teacher/teacher_information.css" />
</head>
<body>
	<div class="content">
		<div class="container">


			<div class="row">
				<div class="span12 div-content-white-bgr">
					<!-- 教师基本信息 -->
					<div class="div-tchr-basic-inf">
						<div class="div-inf-bar">
							<label>基本资料</label>
						</div>
						<div class="div-tchr-basic-inf-left">
							<h5>个人信息</h5>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</div>
								<div class="div-tchr-basic-inf-1-2">${teacher.tchrName }</div>
							</div>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">出生年月</div>
								<div class="div-tchr-basic-inf-1-2">
									${teacher.tchrBirthday }</div>
							</div>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">最高学位</div>
								<div class="div-tchr-basic-inf-1-2">${teacher.tchrDegree }
								</div>
							</div>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</div>
								<div class="div-tchr-basic-inf-1-2">${teacher.tchrTitle }
								</div>
							</div>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">所学专业</div>
								<div class="div-tchr-basic-inf-1-2">${teacher.tchrMajor }
								</div>
							</div>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">毕业院校</div>
								<div class="div-tchr-basic-inf-1-2">
									${teacher.tchrGraduateSchool }</div>
							</div>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">任职时间</div>
								<div class="div-tchr-basic-inf-1-2">
									${teacher.tchrAttendDate }</div>
							</div>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</div>
								<div class="div-tchr-basic-inf-1-2">${teacher.tchrPhone }
								</div>
							</div>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真</div>
								<div class="div-tchr-basic-inf-1-2">${teacher.tchrFax}</div>
							</div>

							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">电子邮件</div>
								<div class="div-tchr-basic-inf-1-2">${teacher.tchrEmail}</div>
							</div>
							<div class="div-tchr-basic-inf-1">
								<div class="div-tchr-basic-inf-1-1">办&nbsp;&nbsp;公&nbsp;&nbsp;室</div>
								<div class="div-tchr-basic-inf-1-2">

									${teacher.tchrOfficeAddr}</div>
							</div>
						</div>
						<div class="div-tchr-basic-inf-right">
							<img src="tchrImg/${teacher.tchrSchNum}"
								class="img-polaroid img-head">
						</div>
					</div>
					<div class="div-tchr-self-intr">
						<h5>个人简介：</h5>
						<div class="div-tchr-self-intr-content">
							<label>${teacher.tchrSelfIntroduce}</label>
						</div>
					</div>
					<!-- 教师基本信息完 -->
					<!-- 教师学历履历 -->
					<div class="div-inf-tbl">
						<h5>学历&履历</h5>
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th>学校/单位</th>
									<th>学位/职位</th>
									<th>时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="e" items="${teacherExperiments}"
									varStatus="status">
									<tr>
										<td>${e.tchrSchool }</td>
										<td>${e.tchrPosition }</td>
										<td>${e.tchrTime }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- 教师学历履历完 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>