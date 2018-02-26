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
	function deleteContent(label) {
		var section = label.parentNode.parentNode;
		section.remove();
		//重新编号
		var labels = document.getElementsByClassName("label-index");
		for (var i = 0; i < labels.length; i++) {
			labels[i].innerHTML = (i + 1) + "";
		}
	}
	function addFirstContent() {
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>序号：</label><div class='controls'><label class='label-index'></label></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>课程内容：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>教学方式：</label><div class='controls'><input type='text' class='input-long'><input type='hidden' class='input-long'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "div-btn");
		var label1 = document.createElement("label");
		label1.innerHTML = "添加";
		label1.setAttribute("class", "btn");
		label1.setAttribute("onclick", "addContent(this)");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div5.appendChild(label1);
		div5.appendChild(label2);
		var hr = document.createElement("hr");
		
		var section = document.createElement("section");
		section.appendChild(div1);
		section.appendChild(div2);
		section.appendChild(div3);
		section.appendChild(div4);
		section.appendChild(div5);
		section.appendChild(hr);
		
		var div = document.getElementById("div-method");
		div.appendChild(section);
		//重新编号
		var labels = document.getElementsByClassName("label-index");
		for (var i = 0; i < labels.length; i++) {
			labels[i].innerHTML = (i + 1) + "";
		}
	}
	function addContent(label) {
		var div1 = document.createElement("div");
		div1.setAttribute("class", "control-group");
		div1.innerHTML += "<label class='control-label'>序号：</label><div class='controls'><label class='label-index'></label></div>";
		var div2 = document.createElement("div");
		div2.setAttribute("class", "control-group");
		div2.innerHTML += "<label class='control-label'>课程内容：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div3 = document.createElement("div");
		div3.setAttribute("class", "control-group");
		div3.innerHTML += "<label class='control-label'>学时：</label><div class='controls'><input type='text' class='input-long'></div>";
		var div4 = document.createElement("div");
		div4.setAttribute("class", "control-group");
		div4.innerHTML += "<label class='control-label'>教学方式：</label><div class='controls'><input type='text' class='input-long'><input type='hidden' class='input-long'></div>";
		var div5 = document.createElement("div");
		div5.setAttribute("class", "div-btn");
		var label1 = document.createElement("label");
		label1.innerHTML = "添加";
		label1.setAttribute("class", "btn");
		label1.setAttribute("onclick", "addContent(this)");
		var label2 = document.createElement("label");
		label2.innerHTML = "删除";
		label2.setAttribute("class", "btn");
		label2.setAttribute("onclick", "deleteContent(this)");
		div5.appendChild(label1);
		div5.appendChild(label2);
		var hr = document.createElement("hr");
		
		var nextSection = document.createElement("section");
		nextSection.appendChild(div1);
		nextSection.appendChild(div2);
		nextSection.appendChild(div3);
		nextSection.appendChild(div4);
		nextSection.appendChild(div5);
		nextSection.appendChild(hr);
		
		var section = label.parentNode.parentNode;//获取作为参数传进来的label的父section
		if(section.nextSibling){//如果父section后面还有section，则在后面那个section之前插入新建的section
			section.parentNode.insertBefore(nextSection,section.nextSibling);
			}else{//否则直接把新建的section插在最后面
				section.parentNode.appendChild(nextSection);
			}
		//重新编号
		var labels = document.getElementsByClassName("label-index");
		for (var i = 0; i < labels.length; i++) {
			labels[i].innerHTML = (i + 1) + "";
		}
	}
	
	function addName(){//提交表单之前为每一个input添加name属性
		var input = document.getElementsByClassName("input-long");
		for(var j=0;j<(input.length/4);j++){
			input[j*4+0].setAttribute("name","ctm["+j+"].cursContent");
			input[j*4+1].setAttribute("name","ctm["+j+"].period");
			input[j*4+2].setAttribute("name","ctm["+j+"].teacMethod");
			input[j*4+3].setAttribute("name","ctm["+j+"].id");
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
				<form action="teacherCourse_Modify_4_modifyTargetByCursId" method="post"
					class="form-horizontal">
<input type="hidden" name="courseId" value="${courseId}">

					<div class="div-inf">
						<!-- <p>课程具体内容及基本要求</p> -->
						<div id="div-method">
							<c:forEach items="${ctm}" var="c" varStatus="s">
								
								<section>
									<div class="control-group">
										<label class="control-label">序号：</label>
										<div class="controls">
											<label class="label-index">${s.index+1 }</label>
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">课程内容：</label>
										<div class="controls">
											<input type="text" value="${c.cursContent }" class="input-long">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">学时：</label>
										<div class="controls">
											<input type="text" value="${c.period }" class="input-long">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">教学方式：</label>
										<div class="controls">
											<input type="text" value="${c.teacMethod }" class="input-long"><input type="hidden" value="${c.id }" class="input-long">
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
						<div class="div-btn"><label class="btn" onclick="addFirstContent(this)">添加</label>
							<input onclick="addName()" type="submit" value="提交" class="btn">
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
	
</body>
</html>
