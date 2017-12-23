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
					<div class="div-inf-title">目标与任务</div>

					<div class="div-inner-text">
						<h6>通过本课程的教学，使学生具备以下知识和能力：</h6>
						<c:forEach var="t" items="${targets}" varStatus="s">
							<p>
								${s.index+1}
								.
								${t.tchtargetContent}
							</p>

						</c:forEach>
					</div>
				</div>

				<div class="div-inf">
					<div class="div-inf-title">课程教学目标与毕业要求对应关系</div>
					<div class="div-curs-plan">
							<div class="div-tbl-title">表-1：课程考试与教学目标支撑分值设置表</div>
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th class="width48">项目</th>
										<c:if test="${course.cursType=='normal' }">
								 			<th>课堂表现</th>
											<th>平时作业</th>
											<th>实验成绩</th>
											<th>期中成绩</th>
											<th>期末成绩</th>
										</c:if>
										<c:if test="${course.cursType=='experiment' }">
							 				<th>协作答辩</th>
											<th>技术方案</th>
											<th style="display:none"></th>
											<th>设计报告</th>
											<th>查阅文献</th>
										</c:if>
										<c:if test="${course.cursType=='graduation-project'}">
							 				<th>中期</th>
											<th>软硬件验收</th>
											<th>翻译</th>
											<th>论文</th>
											<th>答辩</th>
										</c:if>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="t" items="${targets}" varStatus="s">
									<c:if test="${course.cursType=='experiment' }">
									<tr>
										<td>目标${s.index+1}</td>
										<td>${t.tchtargetClassTargetValue}</td>
					 					<td>${t.tchtargetHomeworkTargetValue}</td>
										<td style="display:none">${t.tchtargetExpTargetValue}</td>
										<td>${t.tchtargetMidTargetValue}</td>
										<td>${t.tchtargetFinTargetValue}</td>
									</tr>
									</c:if>
									<c:if test="${course.cursType!='experiment' }">
									<tr>
										<td>目标${s.index+1}</td>
										<td>${t.tchtargetClassTargetValue}</td>
					 					<td>${t.tchtargetHomeworkTargetValue}</td>
										<td>${t.tchtargetExpTargetValue}</td>
										<td>${t.tchtargetMidTargetValue}</td>
										<td>${t.tchtargetFinTargetValue}</td>
									</tr>
									</c:if>
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