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
<script type="text/javascript">
	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}

	//显示后将request里的Message清空，防止回退时重复显示。

	var input = document.getElementsByTagName("input");
	function validate() {
		//若input为空，则将其设为0
		for (var i = 0; i < input.length; i++) {
			if (input[i].value.toString().trim().length == 0)
				input[i].value = 0;
		}

		var midValue = document.getElementById("input-mid").value;
		var finValue = document.getElementById("input-fin").value;
		var workValue = document.getElementById("input-work").value;
		var clasValue = document.getElementById("input-cla").value;
		var expValue = document.getElementById("input-exp").value;

		if (parseFloat(midValue) + parseFloat(finValue) + parseFloat(workValue)
				+ parseFloat(clasValue) + parseFloat(expValue) != 100) {
			alert("各项百分比加和应该为100");
			return false;
		}

		var colClass = document.getElementsByClassName("colClass");
		var colWork = document.getElementsByClassName("colWork");
		var colExp = document.getElementsByClassName("colExp");
		var colMid = document.getElementsByClassName("colMid");
		var colFin = document.getElementsByClassName("colFin");
		var colClassTotal = 0.0;
		var colWorkTotal = 0.0;
		var colExpTotal = 0.0;
		var colMidTotal = 0.0;
		var colFinTotal = 0.0;

		for (var i = 0; i < colClass.length; i++) {
			colClassTotal += parseFloat(colClass[i].value);
			colWorkTotal += parseFloat(colWork[i].value);
			/* if(colExp[i].value==null) colExp[i].value=0.0; */
			colExpTotal += parseFloat(colExp[i].value);
			colMidTotal += parseFloat(colMid[i].value);
			colFinTotal += parseFloat(colFin[i].value);
		}

		if (colClassTotal == parseFloat(clasValue)
				&& colWorkTotal == parseFloat(workValue)
				&& colExpTotal == parseFloat(expValue)
				&& colMidTotal == parseFloat(midValue)
				&& colFinTotal == parseFloat(finValue)) {
			return true;
		} else {
			alert("课程考试与教学目标支撑分值设置异常，请检查！");
			return false;
		}
	}
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
				<form action="teacherCourse_Modify_9_modifyTargetValueByCursId"
					method="post" enctype="multipart/form-data" class="form-horizontal"
					onsubmit="javascript:return validate()">
					<input type="hidden" name="courseId" value="${course.id}">
					<div class="div-inf">
						<div class="div-inf-title">设置评分标准</div>
						
						<label class="title-per-rule">评分标准：</label>
						<div class="div-per-rule">
							<c:if test="${course.cursType=='normal' }">
							课堂表现
						</c:if>
							<c:if test="course.cursType=='experiment'">
							协作答辩
						</c:if>
							<c:if test="course.cursType=='graduation-project'">
							中期
						</c:if>
							&nbsp;<input name="clazzPer" type="text"
								id="input-cla"
								value="${courseCompositionRules.clazzPer}" />&nbsp;%
						</div>
						<div class="div-per-rule">
							<c:if test="course.cursType=='normal'">
							平时作业
						</c:if>
							<c:if test="course.cursType=='experiment'">
							技术方案
						</c:if>
							<c:if test="course.cursType=='graduation-project'">
							软硬件验收
						</c:if>
							&nbsp;<input name="homeworkResultPer"
								type="text" id="input-work"
								value="${courseCompositionRules.homeworkResultPer}" />&nbsp;%
						</div>
						<div class="div-per-rule">
							<c:if test="course.cursType=='normal'">
							实验成绩&nbsp;<input name="expResultPer" type="text"
									id="input-exp"
									value="${courseCompositionRules.expResultPer}" />&nbsp;%
						</c:if>
							<c:if test="course.cursType=='experiment'">
								<input name="expResultPer" type="text"
									id="input-exp" style="display:none"
									value="0" />
							</c:if>
							<c:if test="course.cursType=='graduation-project'">
							翻译&nbsp;<input name="expResultPer" type="text"
									id="input-exp"
									value="${courseCompositionRules.expResultPer}" />&nbsp;%
						</c:if>

						</div>
						<div class="div-per-rule">
							<c:if test="course.cursType=='normal'">
							期中成绩
						</c:if>
							<c:if test="course.cursType=='experiment'">
							设计报告
						</c:if>
							<c:if test="course.cursType=='graduation-project'">
							论文
						</c:if>
							&nbsp;<input name="midTermPer" type="text"
								id="input-mid"
								value="${courseCompositionRules.midTermPer}" />&nbsp;%
						</div>
						<div class="div-per-rule">
							<c:if test="course.cursType=='normal'">
							期末成绩
						</c:if>
							<c:if test="course.cursType=='experiment'">
								查阅文献
							</c:if>
							<c:if test="course.cursType=='graduation-project'">
							答辩
						</c:if>
							&nbsp;<input name="finalExamper" type="text"
								id="input-fin"
								value="${courseCompositionRules.finalExamper}" />&nbsp;%
						</div>
					</div>
					<div class="div-inf">
						<div class="div-inf-title">设置课程考试与教学目标支撑分值</div>
						<div class="div-curs-plan">
							<div class="div-tbl-title">表-1：课程考试与教学目标支撑分值设置表</div>
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th class="hidden"></th>
										<th class="width48">项目</th>
										<c:if test="course.cursType=='normal'">
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
										<c:if test="${course.cursType=='graduation-project' }">
											<th>中期</th>
											<th>软硬件验收</th>
											<th>翻译</th>
											<th>论文</th>
											<th>答辩</th>
										</c:if>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${targets}" var="t" varStatus="s">
										<tr>
											<td class="hidden"><input
												name="targets[${s.index}].id"
												value="${t.id}" />
												
												<input
												name="targets[${s.index}].tchtargetContent"
												value="${t.tchtargetContent}" />
												</td>
											<td>目标${s.index+1 }</td>
											<td><input
												name="targets[${s.index }].tchtargetClassTargetValue"
												class="border0 colClass"
												value="${t.tchtargetClassTargetValue}" /></td>
											<td><input
												name="targets[${s.index }].tchtargetHomeworkTargetValue"
												class="border0 colWork"
												value="${t.tchtargetHomeworkTargetValue}" /></td>
												<td class="td-exp"><input
													name="targets[${s.index }].tchtargetExpTargetValue"
													class="border0 colExp"
													value="${t.tchtargetExpTargetValue}" /></td>
											<td><input
												name="targets[${s.index }].tchtargetMidTargetValue"
												class="border0 colMid"
												value="${t.tchtargetMidTargetValue}" /></td>
												<c:if test="${course.cursType=='experiment' }">
													<td style="display:none"><input class="border0 colFin"
														name="targets[${s.index }].tchtargetFinTargetValue"
														value="${t.tchtargetFinTargetValue}" /></td>
												</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="div-btn">
						<input type="submit" class="btn" value="提交">
					</div>
				</form>
			</div>

		</div>
	</div>
	
	<label id="cursType" class="hidden">${course.cursType }</label>
</body>
</html>
