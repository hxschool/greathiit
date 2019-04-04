<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程内容管理</title>
	<meta name="decorator" content="default"/>

<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/admin.css" />
<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
</script>
</head>

<body>
	
	<div class="container">
		<div class="row">
			
				<div class="span12">
				<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="${ctxStatic}/modules/img/circle.jpg"/>
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>添加课程贡献点
					</h6>
				</div>
				<div class="div-inf">
					<div class="div-inf-title">本课程对毕业要求指标点的支撑</div>
					<div class="div-curs-plan">
					<form action="adminCourseAdd7" method="post">
						<input type="hidden" name="courseId" value="${courseId}"/>
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th class="width24">毕业要求</th>
									<th>指标点</th>
								</tr>
							</thead>
							<tbody>
									<!-- 迭代毕业要求 -->
									<tr>
										<td>毕业要求<s:property value="#s.index+1" /></td>
										<td class="indPointList">
										待开发
										<!-- 迭代毕业要求 -->
										<s:iterator value="top" id="inner">
												<label title="标签说明"><input type="checkbox"
												 name="indicatorPoint.indPointId"	value="">
												测试</label>
											</s:iterator>
											<!-- 迭代毕业要求 -->
											</td>
									</tr>
									<!-- 迭代毕业要求 -->
							</tbody>
						</table>
						<div class="div-btn">
							<input type="submit" value="下一步" class="btn">
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
