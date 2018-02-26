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
					<div class="div-inf-title">考核及成绩评定方式</div>
					<div class="div-inner-text">
						该课程各部分所占比例为：<c:if test="${course.cursType=='normal'}">
							期中成绩
						</c:if>
							<c:if test="${course.cursType=='experiment'}">
							查阅文献
						</c:if>
							<c:if test="${course.cursType=='graduation-project'}">
							论文
						</c:if>
						${rules.midTermPer }
						%， <c:if test="${course.cursType=='normal'}">
							期末成绩
						</c:if>
							<c:if test="${course.cursType=='experiment'}">
							查阅文献
						</c:if>
							<c:if test="${course.cursType=='graduation-project' }">
							答辩
						</c:if>
						
						${rules.finalExamper}%，
						 <c:if test="${course.cursType=='normal'}">
							课堂表现
						</c:if>
							<c:if test="${course.cursType=='experiment'}">
							协作答辩
						</c:if>
							<c:if test="${course.cursType=='graduation-project'}">
							中期
						</c:if>：
						${rules.clazzPer}
						%， <c:if test="${course.cursType=='normal'}">
							平时作业
						</c:if>
							<c:if test="${course.cursType=='experiment'}">
							技术方案
						</c:if>
							<c:if test="${course.cursType=='graduation-project'}">
							软硬件验收
						</c:if>：
						${rules.homeworkResultPer}
						%， <c:if test="${course.cursType=='normal'}">
							实验成绩${rules.expResultPer }
						%。
						</c:if>
							<c:if test="${course.cursType=='experiment'}">
						</c:if>
							<c:if test="${course.cursType=='graduation-project'}">
							翻译${rules.expResultPer}
						%。
						</c:if>
						
					</div>
				</div>
				<!-- <div class="div-curs-plan">
					<div class="div-tbl-title">表-2：教学目标与毕业要求指标点支撑权重设置表</div>
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th class="width48"></th>
								<s:iterator value="indicatorPoints" var="i">
									<th>指标点<s:property value="#i.indPointNum" /></th>
								</s:iterator>
							</tr>
						</thead>
						<tbody id="tbody">
							<s:iterator value="targets" var="t" status="s1">
								<tr class="countTr">
									<td>目标<s:property value="#s1.index+1" /></td>
									<s:iterator value="indicatorPoints" var="i" status="s2">
										<td><s:iterator
												value="contributeTargets.{?#this.teachingTarget.tchTargetId==#t.tchTargetId&&#this.indicatorPoint.indPointId==#i.indPointId}"
												var="c">
												<s:property value="#c.conTarValue" />
											</s:iterator></td>
									</s:iterator>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div> -->
			</div>
		</div>
	</div>
</body>
</html>