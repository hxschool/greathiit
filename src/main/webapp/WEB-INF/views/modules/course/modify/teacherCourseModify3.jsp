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
	function deleteContent(label) {
		var div = label.parentNode.parentNode;
		div.remove();
		//重新编号
		var h5 = document.getElementsByTagName("h5");
		for (var i = 0; i < h5.length; i++) {
			h5[i].innerHTML = "第" + (i + 1) + "章";
		}
	}
	function addFirstContent() {
		//拼出一个章节添加的section
		var h5 = document.createElement("h5");
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>章节名称：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>支撑目标：</label><div class='controls'><c:forEach items='${targets}' var='t' varStatus='s'><label class='label-checkbox'><input class='checkbox' type='checkbox' value='教学目标${s.index+1}'>教学目标${s.index+1}</label></c:forEach><input type='hidden' class='hidden' value='${c.cscTarget}'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>学习目的：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "control-group");
		div5.innerHTML += "<label class='control-label'>基本要求：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div6 = document.createElement("div");
		div6.setAttribute("class", "control-group");
		div6.innerHTML += "<label class='control-label'>学习重点：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div7 = document.createElement("div");
		div7.setAttribute("class", "control-group");
		div7.innerHTML += "<label class='control-label'>学习难点：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div8 = document.createElement("div");
		div8.setAttribute("class", "control-group");
		div8.innerHTML += "<label class='control-label'>课外作业及要求：</label><div class='controls'><input type='text' class='input-long'><input type='hidden' class='input-long'></div>";
		var div9 = document.createElement("div");
		div9.setAttribute("class", "div-btn");
		var label1 = document.createElement("label");
		label1.innerHTML = "添加";
		label1.setAttribute("class", "btn");
		label1.setAttribute("onclick", "addContent(this)");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div9.appendChild(label1);
		div9.appendChild(label2);
		var hr = document.createElement("hr");

		var section = document.createElement("section");
		section.appendChild(h5);
		section.appendChild(div1);
		section.appendChild(div2);
		section.appendChild(div3);
		section.appendChild(div4);
		section.appendChild(div5);
		section.appendChild(div6);
		section.appendChild(div7);
		section.appendChild(div8);
		section.appendChild(div9);
		section.appendChild(hr);

		var div = document.getElementById("div-content");
		div.appendChild(section);
		//重新编号
		var h5 = document.getElementsByTagName("h5");
		for (var i = 0; i < h5.length; i++) {
			h5[i].innerHTML = "第" + (i + 1) + "章";
		}
	}
	function addContent(label) {
		//拼出一个章节添加的section
		var h5 = document.createElement("h5");
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>章节名称：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>支撑目标：</label><div class='controls'><c:forEach items='${targets}' var='t' varStatus='s'><label class='label-checkbox'><input class='checkbox' type='checkbox' value='教学目标${s.index+1}'>教学目标${s.index+1}</label></c:forEach><input type='hidden' class='hidden' value='${c.cscTarget}'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>学习目的：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "control-group");
		div5.innerHTML += "<label class='control-label'>基本要求：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div6 = document.createElement("div");
		div6.setAttribute("class", "control-group");
		div6.innerHTML += "<label class='control-label'>学习重点：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div7 = document.createElement("div");
		div7.setAttribute("class", "control-group");
		div7.innerHTML += "<label class='control-label'>学习难点：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div8 = document.createElement("div");
		div8.setAttribute("class", "control-group");
		div8.innerHTML += "<label class='control-label'>课外作业及要求：</label><div class='controls'><input type='text' class='input-long'></div><input type='hidden' class='input-long'>";
		var div9 = document.createElement("div");
		div9.setAttribute("class", "div-btn");
		var label1 = document.createElement("label");
		label1.innerHTML = "添加";
		label1.setAttribute("class", "btn");
		label1.setAttribute("onclick", "addContent(this)");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div9.appendChild(label1);
		div9.appendChild(label2);
		var hr = document.createElement("hr");

		var nextSection = document.createElement("section");
		nextSection.appendChild(h5);
		nextSection.appendChild(div1);
		nextSection.appendChild(div2);
		nextSection.appendChild(div3);
		nextSection.appendChild(div4);
		nextSection.appendChild(div5);
		nextSection.appendChild(div6);
		nextSection.appendChild(div7);
		nextSection.appendChild(div8);
		nextSection.appendChild(div9);
		nextSection.appendChild(hr);

		var section = label.parentNode.parentNode;//获取作为参数传进来的label的父section
		if (section.nextSibling) {//如果父section后面还有section，则在后面那个section之前插入新建的section
			section.parentNode.insertBefore(nextSection, section.nextSibling);

		} else {//否则直接把新建的section插在最后面
			section.parentNode.appendChild(nextSection);
		}
		//重新编号
		var h5 = document.getElementsByTagName("h5");
		for (var i = 0; i < h5.length; i++) {
			h5[i].innerHTML = "第" + (i + 1) + "章";
		}
	}

	function addName() {//提交表单之前为每一个input添加name属性
		var input = document.getElementsByClassName("input-long");
		var checkbox = document.getElementsByClassName("checkbox");
		var n = checkbox.length/(input.length / 8);//该课程有几个目标
		for (var j = 0; j < (input.length / 8); j++) {
			input[j * 8 + 0].setAttribute("name", "csc[" + j + "].cscChapter");
			input[j * 8 + 1]
					.setAttribute("name", "csc[" + j + "].cscClassHour");
			for(var i=0;i<n;i++){
					checkbox[j * n + i].setAttribute("name", "csc[" + j + "].cscTarget");
			}
			
			input[j * 8 + 2].setAttribute("name", "csc[" + j + "].cscGoal");
			input[j * 8 + 3].setAttribute("name", "csc[" + j + "].cscBasRequ");
			input[j * 8 + 4].setAttribute("name", "csc[" + j + "].cscStudyEmpha");
			input[j * 8 + 5].setAttribute("name", "csc[" + j+ "].cscStudyDiffi");
			input[j * 8 + 6].setAttribute("name", "csc[" + j + "].cscHomework");
			input[j * 8 + 7].setAttribute("name", "csc[" + j + "].id");
		}
	}
	
	$(function(){
		var input = document.getElementsByClassName("autoSelect");
		for(var i=0;i<input.length;i++){
			var value=input[i].value;
			var checkbox = input[i].parentNode.getElementsByClassName("checkbox");
			var arg = [];
			arg = value.split(",");
			for(var j=0;j<checkbox.length;j++){
				for(var k=0;k<arg.length;k++){
					console.log(checkbox[j].value.trim()+"  "+arg[k].trim());
					if(checkbox[j].value.trim() == arg[k].trim()){
						checkbox[j].setAttribute("checked","true");
					}
				}
			}
		}
	})
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
				<form action="teacherCourse_Modify_3_modifyTargetByCursId"
					method="post" class="form-horizontal">
					<input type="hidden" name="courseId" value="${courseId}">
					<div class="div-inf">
						<!-- <p>课程具体内容及基本要求</p> -->
						<div id="div-content">
							<c:forEach items="${csc }" var="c" varStatus="s">
							
								
								<section>
									<h5>
										第
										${s.index+1 }
										章
									</h5>
									<div class="control-group">
										<label class="control-label">章节名称：</label>
										<div class="controls">
											<input type="text"  class="input-long"
												value="${c.cscChapter }">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学时：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="${c.cscClassHour }">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">支撑目标：</label>
										<div class="controls">
												<c:forEach items="${targets }" var="t" varStatus="ss">
													<label class='label-checkbox'><input class="checkbox" type="checkbox" value="教学目标${ss.index+1 }">教学目标
														${ss.index+1 }</label>
												</c:forEach>
											<input type="hidden" class="hidden autoSelect"
												value="${c.cscTarget }">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学习目的：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="${c.cscGoal }">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">基本要求：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="${c.cscBasRequ }">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学习重点：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="${c.cscStudyEmpha }">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学习难点：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="${c.cscStudyDiffi }">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">课外作业及要求：</label>
										<div class="controls">
											<input type="text" class="input-long"
												value="${c.cscHomework }">
												
												<input type="hidden" class="input-long"
												value="${c.id }">
										</div>
									</div>
									<div class="div-btn">
										<label class="btn" onclick="addContent(this)">添加</label> <label
											class="btn" onclick="deleteContent(this)">删除</label>
									</div>
									<hr>
								</section>
								
							</c:forEach>
						</div>
						<div class="div-btn">
							<label class="btn" onclick="addFirstContent()">添加</label> <input
								onclick="addName()" type="submit" value="提交" class="btn">
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
	
</body>
</html>
