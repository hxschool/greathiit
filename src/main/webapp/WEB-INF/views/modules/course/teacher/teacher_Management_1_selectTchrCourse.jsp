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
	href="${ctxStatic}/modules/teacher/teaching_management.css" />
</head>
<body>
	<div class="content">
		<div class="container">
			<div class="row">
				<div class="span9">
					<div class="row">
						<div class="span9 div-content-white-bgr">
							<!-- 研究成果 -->
							<div class="div-tchr-curs">
								<div class="div-inf-bar">
									<label>我的课程列表</label>
								</div>

								<div class="div-tchr-course">

									<h5>讲授课程</h5>
									
									<c:forEach var="item" items="${teachCourses}" varStatus="status">
										<section class="div-curs">
											<h6>课程名称：</h6>
											<label>${item.cursName}</label><br />
											<h6>课程编号：</h6>
											<label>${item.cursNum}</label><br />
											<h6>学分/学时：</h6>
											<label>${item.cursCredit}/${item.cursClassHour}</label><br />
											<h6>课程性质：</h6>
											<label>${item.cursProperty}</label><br />
											<h6>开设学期：</h6>
											<label>${item.cursTerm}</label>
											<div class="div-curs-detail-inf a">
												<a href="Course_Detail_1_selectByCursId?cursId=${item.id}">详细</a>&nbsp;&nbsp;
											</div>
										</section>
									</c:forEach>
								</div>
								<!-- <div class="div-tchr-course">
									<h5>负责课程</h5>
									<s:iterator value="chargeCourses" var="c">
										<div class="div-curs">
											<h6>课程名称：</h6>
											<label><s:property value="#c.cursName" /></label><br />
											<h6>课程编号：</h6>
											<label><s:property value="#c.cursNum" /></label><br />
											<h6>学分/学时：</h6>
											<label><s:property value="#c.cursCredit" />/<s:property
													value="#c.cursClassHour" /></label><br />
											<h6>课程性质：</h6>
											<label><s:property value="#c.cursProperty" /></label><br />
											<h6>开设学期：</h6>
											<label><s:property value="#c.cursTerm" /></label>
											<div class="div-curs-detail-inf a">
												<a
													href="Course_Detail_1_selectByCursId?cursId=<s:property value="#c.cursId"/>">详细</a>&nbsp;&nbsp;
												<a
													href="Teacher_Course_Evaluate_selectTargetEvaByCursId?cursId=<s:property value="#c.cursId"/>">课程达成评价</a>&nbsp;&nbsp;
											</div>
										</div>
									</s:iterator>
								</div> -->
							</div>
							<!-- 研究成果完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>