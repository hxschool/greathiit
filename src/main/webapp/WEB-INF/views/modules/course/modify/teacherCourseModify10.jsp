<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>课程内容管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		//$("#name").focus();
		$("#inputForm").validate({
			submitHandler : function(form) {
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
</script>
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/admin.css" />
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="div-module-add-curs">
					<h6>
						<img class="image-path-1"
							src="${ctxStatic}/modules/img/circle.jpg" /> <a href="#">课程列表</a><img
							class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg" />${course.cursName}
					</h6>
				</div>
				<div class="div-inf">
					<div class="div-inf-title">本课程对毕业要求指标点的支撑</div>
					<div class="div-curs-plan">
						<form action="TeacherCourse_Modify_10_modifyPointByCursId"
							method="post" onsubmit="javascript:return validate()">
							<div class="div-tbl-title">表-2：教学目标与毕业要求指标点支撑权重设置表</div>
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th class="width48"></th>
										<c:forEach items="${indicatorPoint }" var="i">
											<th>指标点${i.indPointNum}</th>
										</c:forEach>
									</tr>
								</thead>
								<tbody id="tbody">
									<c:forEach items="${targets}" var="t" varStatus="s1">
										<tr class="countTr">
											<td>目标${s1.index+1}</td>
											<c:forEach items="${indicatorPoint }" var="i" status="s2">
												<td id="innerTd"><c:choose>
														<c:when
															test="${contributeTargets!=null&&contributeTargets.size()>0 }">
															<!-- <c:forEach
																items="contributeTargets.{?#this.teachingTarget.tchTargetId==#t.tchTargetId&&#this.indicatorPoint.indPointId==#i.indPointId}"
																var="c">
																<input class="border0 valueInput"
																	value="${c.conTarValue" />" />
															</c:forEach> -->
														</c:when>
														<c:otherwise>
															<input class="border0 valueInput" />
														</c:otherwise>
													</c:choose> <input value="${t.tchTargetId}" class="hidden" /> <input
													value="${i.indPointId}" class="hidden" /></td>
											</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="div-btn">
								<input type="submit" class="btn" value="保存">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
