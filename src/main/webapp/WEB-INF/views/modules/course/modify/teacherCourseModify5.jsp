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
	href="${ctxStatic}/modules/teacher/admin.css" />
</script>
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
					<div class="div-inf-title">本课程对毕业要求指标点的支撑</div>
					<div class="div-curs-plan">
						<form action="TeacherCourse_Modify_10_selectTargetAndPointById"
							method="post">
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th class="width24">毕业要求</th>
										<th>指标点</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${indicatorPoints}" varStatus="s">
										<tr>
											<td><label
												title="${requirement[s.index].graReqContent}"> ${requirement[s.index].graReqTitle} </label></td>
											<td class="indPointList"><c:forEach items="${top }" var="inner">
													<label
														title="${inner.indPointContent }"><input
														type="checkbox" name="indicatorPoint.indPointId"
														value="${inner.indPointId }"> ${inner.indPointNum }</label>
												</c:forEach></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="div-btn">
								<input type="submit" value="设置毕业要求评价值" class="btn">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
