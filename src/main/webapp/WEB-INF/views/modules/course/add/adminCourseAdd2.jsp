<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程内容管理</title>
	<meta name="decorator" content="default"/>

<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/admin.css" />
<script type="text/javascript">
var msg = "${message}";
if (msg != "") {
	alert(msg);
}
var i=1;
function addTarget() {
	i++;
	var div = document.createElement("div");
	div.setAttribute("class", "control-group");
	div.innerHTML += "<label class='control-label'>教学目标"
		+ i + "：</label><div class='controls'><input type='text' name='targets["+(i-1)+"].tchtargetContent'  class='input-long'></div>";
	document.getElementById("div-targets").appendChild(div);
}
</script>
</head>

<body>
	<div class="container">
		<div class="row">
			

				
				<div class="span12">
				<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="${ctxStatic}/modules/img/circle.jpg"/>
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>添加课程教学目标
					</h6>
				</div>
				<form action="adminCourseAdd3" method="post" class="form-horizontal">
				<input type="hidden" name="courseId" value="${courseId}"/>
					<div class="div-inf">
					<p>通过本课程的理论教学，使学生具备以下能力：</p>
					<div id="div-targets">
					<div class="control-group">
							<label class="control-label">教学目标1：</label>
							<div class="controls">
								<input type="text" name="targets[0].tchtargetContent" class="input-long">
							</div>
							
						</div>
					</div>
					<div class="div-btn">
				<label class="btn" onclick="addTarget()">添加</label>
				<input type="submit" value="下一步" class="btn">
			</div>
			</div>
			</form>
					</div>
						
			</div>
		</div>

</body>
</html>
