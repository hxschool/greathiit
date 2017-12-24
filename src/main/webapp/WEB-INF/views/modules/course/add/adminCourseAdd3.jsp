<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程内容管理</title>
	<meta name="decorator" content="default"/>

<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/admin.css" />
<script type="text/javascript">
	var msg = "${essage}";
	if (msg != "") {
		alert(msg);
	}
	
	var i = 1;
	function addContent() {
		i++;
		var h5=document.createElement("h5");
		var title = document.createTextNode("第" + i + "章");
		h5.appendChild(title);
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>章节名称：</label><div class='controls'><input type='text' name='csc["+(i-1)+"].cscChapter' class='input-long'></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' name='csc["+(i-1)+"].cscClassHour' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>支撑目标：</label><div class='controls'><input type='text' name='csc["+(i-1)+"].cscTarget' class='input-long'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>学习目的：</label><div class='controls'><input type='text' name='csc["+(i-1)+"].cscGoal' class='input-long'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "control-group");
		div5.innerHTML += "<label class='control-label'>基本要求：</label><div class='controls'><input type='text' name='csc["+(i-1)+"].cscBasRequ' class='input-long'></div>";
		var div6 = document.createElement("div");
		div6.setAttribute("class", "control-group");
		div6.innerHTML += "<label class='control-label'>学习重点：</label><div class='controls'><input type='text' name='csc["+(i-1)+"].cscStudyEmpha' class='input-long'></div>";
		var div7 = document.createElement("div");
		div7.setAttribute("class", "control-group");
		div7.innerHTML += "<label class='control-label'>学习难点：</label><div class='controls'><input type='text' name='csc["+(i-1)+"].cscStudyDiffi' class='input-long'></div>";
		var div8 = document.createElement("div");
		div8.setAttribute("class", "control-group");
		div8.innerHTML += "<label class='control-label'>课外作业及要求：</label><div class='controls'><input type='text' name='csc["+(i-1)+"].cscHomework' class='input-long'></div>";
		var hr = document.createElement("hr");
		document.getElementById("div-content").appendChild(h5);
		document.getElementById("div-content").appendChild(div1);
		document.getElementById("div-content").appendChild(div2);
		document.getElementById("div-content").appendChild(div3);
		document.getElementById("div-content").appendChild(div4);
		document.getElementById("div-content").appendChild(div5);
		document.getElementById("div-content").appendChild(div6);
		document.getElementById("div-content").appendChild(div7);
		document.getElementById("div-content").appendChild(div8);
		document.getElementById("div-content").appendChild(hr);
	}
</script>
</head>

<body>
	
	<div class="container">
		<div class="row">
				<div class="span12">
				<div class="div-module-add-curs">
					<h6><img class="image-path-1" src="${ctxStatic}/modules/img/circle.jpg"/>
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>添加课程具体内容
					</h6>
				</div>
				<form action="adminCourseAdd4" method="post" class="form-horizontal">
					<input type="text" name="courseId" value="${courseId}"/>
					
					<div class="div-inf">
						<!-- <p>课程具体内容及基本要求</p> -->
						<div id="div-content">
							<h5>第1章</h5>
							<div class="control-group">
								<label class="control-label">章节名称：</label>
								<div class="controls">
									<input type="text" name="csc[0].cscChapter" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学时：</label>
								<div class="controls">
									<input type="text" name="csc[0].cscClassHour" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">支撑目标：</label>
								<div class="controls">
									<input type="text" name="csc[0].cscTarget" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学习目的：</label>
								<div class="controls">
									<input type="text" name="csc[0].cscGoal" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">基本要求：</label>
								<div class="controls">
									<input type="text" name="csc[0].cscBasRequ" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学习重点：</label>
								<div class="controls">
									<input type="text" name="csc[0].cscStudyEmpha" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">学习难点：</label>
								<div class="controls">
									<input type="text" name="csc[0].cscStudyDiffi" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">课外作业及要求：</label>
								<div class="controls">
									<input type="text" name="csc[0].cscHomework" class="input-long">
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
	
	<script src="js/jquery1.12.1.js"></script>
	<script src="js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
