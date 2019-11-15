<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

<meta charset="UTF-8">
<meta charset="UTF-8">
<meta name="decorator" content="default"/>
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />

<link rel="stylesheet" href="${ctxStatic}/modules/teacher/student_goal.css" />


<script type="text/javascript">
	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}

	//显示后将request里的Message清空，防止回退时重复显示。

	function isEmpty() {
		var time = document.getElementById("input-time");
		var name = document.getElementById("input-name");
		var unit = document.getElementById("input-unit");
		var duty = document.getElementById("input-duty");

		if (time.value.toString().trim().length != 0
				&& name.value.toString().trim().length != 0
				&& unit.value.toString().trim().length != 0
				&& duty.value.toString().trim().length != 0) {
			return true;
		} else {
			if (time.value.toString().trim().length == 0) {
				time.focus();
			}
			if (name.value.toString().trim().length == 0) {
				name.focus();
			}
			if (unit.value.toString().trim().length == 0) {
				unit.focus();
			}
			if (duty.value.toString().trim().length == 0) {
				duty.focus();
			}
			alert("输入框不能为空！");
			return false;
		}
	}
</script>
</head>

<body>
	
	<div class="content">
		<div class="container">
			<div class="row">
				<div class="span12 div-content-white-bgr">
					<div class="row">
							<!-- 学生实践活动 -->
							<div class="div-inf-bar">
								<label>实践活动</label>
							</div>
							<!-- 组织活动 -->
							<div class="div-inf-tbl">
								<h5>组织活动</h5>
								<table class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th>时间</th>
											<th>活动名称</th>
											<th>主办单位</th>
											<th>职责描述</th>
											<th>状态</th>
											<shiro:hasPermission name="student:student:edit">
											<th>操作</th>
											</shiro:hasPermission>
										</tr>
									</thead>
									<tbody>
									
										<c:forEach var="item" items="${orgActivities}" varStatus="status">
											<tr>
												<td> <fmt:formatDate value="${item.actTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${item.actName}</td>
												<td>${item.actUnit}</td>
												<td>${item.actDuty}</td>
												<td>
												${item.actState==1?'已审核':'未审核'}
												</td>
												<shiro:hasPermission name="student:student:edit">
												<td><a onclick="return confirm('确认删除？')"
													href="Student_Portfolio_Activity_deleteActivity?actId=${item.id}">删除</a></td>
													</shiro:hasPermission>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<label class="lable-add" data-toggle="modal"
									data-target="#myModal">添加</label>
							</div>
							<!-- 参与活动 -->
							<div class="div-inf-tbl">
								<h5>参与活动</h5>
								<table class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th>时间</th>
											<th>活动名称</th>
											<th>主办单位</th>
											<th>职责描述</th>
											<th>状态</th>
											<shiro:hasPermission name="student:student:edit">
											<th>操作</th>
											</shiro:hasPermission>
										</tr>
									</thead>
									<tbody>
										
										<c:forEach var="item" items="${attendActivities}" varStatus="status">
											<tr>
												<td> <fmt:formatDate value="${item.actTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${item.actName}</td>
												<td>${item.actUnit}</td>
												<td>${item.actDuty}</td>
												<td>
												${item.actState==1?'已审核':'未审核'}
												</td>
												<shiro:hasPermission name="student:student:edit">
												<td><a onclick="return confirm('确认删除？')"
													href="Student_Portfolio_Activity_deleteActivity?actId=${item.id}">删除</a></td>
													</shiro:hasPermission>
											</tr>
										</c:forEach>
									</tbody>

								</table>
								<label class="lable-add" data-toggle="modal"
									data-target="#myModal">添加</label>
							</div>
							<!-- 社会实践 -->
							<div class="div-inf-tbl">
								<h5>社会实践</h5>
								<table class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th>时间</th>
											<th>活动名称</th>
											<th>主办单位</th>
											<th>职责描述</th>
											<th>状态</th>
											<shiro:hasPermission name="student:student:edit">
											<th>操作</th>
											</shiro:hasPermission>
										</tr>
									</thead>
									<tbody>
									
										<c:forEach var="item" items="${socialActivities}" varStatus="status">
											<tr>
												<td> <fmt:formatDate value="${item.actTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${item.actName}</td>
												<td>${item.actUnit}</td>
												<td>${item.actDuty}</td>
												<td>
												${item.actState==1?'已审核':'未审核'}
												</td>
												<shiro:hasPermission name="student:student:edit">
												<td><a onclick="return confirm('确认删除？')"
													href="Student_Portfolio_Activity_deleteActivity?actId=${item.id}">删除</a></td>
													</shiro:hasPermission>
											</tr>
										</c:forEach>
										


									</tbody>
								</table>
								<label class="lable-add" data-toggle="modal"
									data-target="#myModal">添加</label>
							</div>
							<!-- 学生实践活动完 -->

						</div>
					</div>
				</div>
			</div>
		</div>
	
	
	
	<!-- 模态框，用于添加参与活动信息 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="display:none">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h5 class="modal-title">添加活动信息</h5>
				</div>
				<div class="modal-body">
					<form action="Student_Portfolio_Activity_addActivity" method="post"
						class="form-horizontal form-add" enctype="multipart/form-data"
						onsubmit="javascript:return isEmpty(1)">
						<div class="control-group">
							<label class="control-label">时间：</label>
							<div class="controls">
								<input id="input-time" type="date" name="actTime">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">活动名称：</label>
							<div class="controls">
								<input id="input-name" type="text" name="actName">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">活动类型：</label>
							<div class="controls">
								<select id="input-type" name="actType">
									<option value="组织活动">组织活动</option>
									<option value="参与活动">参与活动</option>
									<option value="社会实践">社会实践</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">主办单位：</label>
							<div class="controls">
								<input id="input-unit" type="text" name="actUnit">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">职责描述：</label>
							<div class="controls">
								<input id="input-duty" type="text" name="actDuty">
							</div>
							<input type="hidden" name="actState" value="0"
								class="hidden">
						</div>
						<div class="div-btn">
							<shiro:hasPermission name="student:student:edit">
							<input type="submit" value="提交" class="btn">
							</shiro:hasPermission>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- 模态框，用于添加参与活动信息完 -->
	
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
