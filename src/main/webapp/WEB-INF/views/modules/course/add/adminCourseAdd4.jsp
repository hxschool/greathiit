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
	var i = 1;
	function addContent() {
		i++;
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>序号：</label><div class='controls'><label>"
				+ i + "</label></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>课程内容：</label><div class='controls'><input type='text' name='ctm["
			+ (i - 1) + "].cursContent' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' name='ctm["
			+ (i - 1) + "].period' class='input-long'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>教学方式：</label><div class='controls'><input type='checkbox' value='01' name='ctm["
			+ (i - 1)
			+ "].teacMethod'>课堂讲授&nbsp;&nbsp;<input type='checkbox' value='02' name='ctm["
			+ (i - 1)
			+ "].teacMethod'>混合模式&nbsp;&nbsp;<input type='checkbox' value='03' name='ctm["
			+ (i - 1) + "].teacMethod'>在线模式</div>";
		var hr = document.createElement("hr");
		document.getElementById("div-method").appendChild(div1);
		document.getElementById("div-method").appendChild(div2);
		document.getElementById("div-method").appendChild(div3);
		document.getElementById("div-method").appendChild(div4);
		document.getElementById("div-method").appendChild(hr);
	}
</script>
</head>

<body>
	
	<div class="container">
		<div class="row">

				<div class="span12">
				<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="${ctxStatic}/modules/img/circle.jpg"/>
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>添加课程教学方式
					</h6>
				</div>
				<form action="adminCourseAdd5" method="post"
					class="form-horizontal">
									<input type="hidden" name="courseId" value="${courseId}"/>
					<div class="div-inf">
						<!-- <p>课程具体内容及基本要求</p> -->
						<div id="div-method">
							<div class="control-group">
								<label class="control-label">序号：</label>
								<div class="controls">
									<label>1</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">课程内容：</label>
								<div class="controls">
									<input type="text" name="ctm[0].cursContent" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学时：</label>
								<div class="controls">
									<input type="text" name="ctm[0].period" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">教学方式：</label>
								<div class="controls">
									<input type="checkbox" value="01" name="ctm[0].teacMethod">课堂讲授&nbsp;&nbsp;
									<input type="checkbox" value="02" name="ctm[0].teacMethod">混合模式&nbsp;&nbsp;
									<input type="checkbox" value="03" name="ctm[0].teacMethod">在线模式&nbsp;&nbsp;
								</div>
							</div>
							<hr>
						</div>
						<div class="div-btn">
							<label class="btn" onclick="addContent()">添加</label> <input
								type="submit" value="下一步" class="btn">
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
	
</body>
</html>
