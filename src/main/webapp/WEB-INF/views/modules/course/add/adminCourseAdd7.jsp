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
		div1.innerHTML += "<label class='control-label'>作者：</label><div class='controls'><input type='text' name='crb["+(i-1)+"].crbAuthor' class='input-long'></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>书名：</label><div class='controls'><input type='text' name='crb["+(i-1)+"].crbName' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>出版社：</label><div class='controls'><input type='text' name='crb["+(i-1)+"].crbPublisher' class='input-long'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>出版年份：</label><div class='controls'><input type='text' name='crb["+(i-1)+"].crbPubYear' class='input-long'></div>";
		var hr = document.createElement("hr");
		document.getElementById("div-content").appendChild(div1);
		document.getElementById("div-content").appendChild(div2);
		document.getElementById("div-content").appendChild(div3);
		document.getElementById("div-content").appendChild(div4);
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
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>添加课程教材及参考书目
					</h6>
				</div>
				<form action="adminCourseAdd8" method="post" class="form-horizontal">
				<input type="hidden" name="courseId" value="${courseId}"/>
					<div class="div-inf">
						<div id="div-content">
							<div class="control-group">
								<label class="control-label">教材：</label>
								<div class="controls">
									<input type="text" name="course.cursMaterial" class="input-long">
								</div>
							</div>
							
							<hr>
						<!-- 课程参考书目 -->
						<div class="div-inf-title">参考书目</div>
							<div class="control-group">
								<label class="control-label">作者：</label>
								<div class="controls">
									<input type="text" name="crb[0].crbAuthor" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">书名：</label>
								<div class="controls">
									<input type="text" name="crb[0].crbName" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">出版社：</label>
								<div class="controls">
									<input type="text" name="crb[0].crbPublisher" class="input-long">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">出版年份：</label>
								<div class="controls">
									<input type="text" name="crb[0].crbPubYear" class="input-long">
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
