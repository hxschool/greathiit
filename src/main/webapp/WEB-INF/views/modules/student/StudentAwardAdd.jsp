

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生参与项目页面</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />

<link rel="stylesheet" href="${ctxStatic}/modules/student_goal.css" />
<link rel="stylesheet" href="${ctxStatic}/modules/student_item_file.css" />
</head>

<body>
	
	<div class="content">
		<div class="container">
			<div class="row">
				
				<div class="span12">
					<div class="row">
						<div class="span12 div-content-white-bgr">
							<div class="div-inf-bar">
								<label>添加项目信息</label>
							</div>
							<div class="div-inf-tbl">
								<form action="Student_Award_Add_Save" method="post"
									class="form-horizontal form-add" enctype="multipart/form-data"
									onsubmit="javascript:return isEmpty(1)">
									
									<div class="control-group">
										<label class="control-label">项目编号：</label>
										<div class="controls">
											<input id="input-time" type="text" name="itemNum">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">项目名称：</label>
										<div class="controls">
											<input id="input-name" type="text" name="itemName">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">项目类别：</label>
										<div class="controls">
											<select id="input-type" name="itemEvaType"
												onchange="typeChange()">
													<c:forEach var="item" items="${fns:getDictList('project_type')}" varStatus="status">
														<option value="${item.value}">
															${item.label}
														</option>
													</c:forEach>
												
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">项目指标点：</label>
										<div class="controls">
											<select id="input-point" name="itemEvaPoint"
												onchange="pointChange()">
													<c:forEach var="item" items="${fns:getDictList('project_point')}" varStatus="status">
														<option value="${item.value}">
															${item.label}
														</option>
													</c:forEach>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">项目等级：</label>
										<div class="controls">
											<select id="input-grade" name="itemEvaScore"
												onchange="gradeChange()">
												<c:forEach var="item" items="${fns:getDictList('project_score')}" varStatus="status">
														<option value="${item.value}">
															${item.label}
														</option>
													</c:forEach>
											</select>
										</div>
									</div>
									<div class="control-group" id="itemobject">
										<label class="control-label">项目表彰对象：</label>
										<div class="controls">
											<select id="input-object" name="itemPrizeObject"
												onchange="objectChange()">
												<option value="个人">个人</option>
												<option value="团队">团队</option>
											</select>
										</div>
									</div>
									<div class="control-group" id="itemRoles">
										<label class="control-label">项目参与角色：</label>
										<div class="controls">
											<select id="input-role" name="itemRole">
												<option value="第一负责人">第一负责人</option>
												<option value="其他成员">其他成员</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">主办单位：</label>
										<div class="controls">
											<input id="input-unit" type="text" name="itemUnit">
										</div>
									</div>
									<!-- <div class="control-group">
										<label class="control-label">获奖情况：</label>
										<div class="controls">
											<input id="input-duty" type="text" name="item.itemAward">
										</div>
										<input type="text" name="item.itemScore" value="0"
											style="display: none">
									</div> -->
									<div class="control-group">
										<label class="control-label">上传附件：</label>
										<div class="controls">
											<input type="button" name="upload" id="upload" class="btn"
												onclick="next()" value="上传附件">
										</div>
									</div>
									<div class="control-group" id="div-uploads"></div>
									<div class="div-btn">
										<input type="submit" value="提交" class="btn">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
	<script>
		
		//显示后将request里的Message清空，防止回退时重复显示。
		function isEmpty() {

			var time = document.getElementById("input-time");
			var name = document.getElementById("input-name");
			var unit = document.getElementById("input-unit");
			/* var duty = document.getElementById("input-duty"); */
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
				alert("输入框不能为空！");
				return false;
			}
		}

		/**
		 **下面用于图片上传预览功能
		 **/

		function next() {
			var div = document.createElement("div");
			//div.setAttribute("class", "controls");
			div.innerHTML += "<div class='addFile'><input type='file' name='file' class='file-input' value='选择文件'/><a class='a-link' style='cursor:hand'  onclick='next()'>继续添加</a><a class='a-link' onclick='deleteDiv(this)'>删除</a></div>";
			document.getElementById("div-uploads").appendChild(div);
		}
		function deleteDiv(label) {
			var div = label.parentNode.parentNode;
			div.remove();
		}
		
		function typeChange() {
			var itemTypeId = document.getElementById("input-type").value;
			$.getJSON("Json_selectItemEvaPoint", {
				itemTypeId : itemTypeId
			}, function(data) {
				//清空显示层中的数据   
				$("#input-point").html("");
				//使用jQuery中的each(data,function(){});
				$.each(data.itemEvaluatePoints, function(i, value) {
					if (i == 0) {
						var pointId = value.itemEvaPointId;
						$.getJSON("Json_selectItemEvaScores", {
							pointId : pointId
						}, function(data) {
							//清空显示层中的数据   
							$("#input-grade").html("");
							//使用jQuery中的each(data,function(){});函数   
							$.each(data.itemEvaluateScores, function(i, value) {
								$("#input-grade").append(
										"<option value="+value.itemEvaScoreId+">"
												+ value.itemEvaScoreName
												+ "</option>");
							});
							gradeChange();
						});	
					}
					$("#input-point").append(
							"<option value="+value.itemEvaPointId+">"
									+ value.itemEvaPointName + "</option>");
				});

			});

		}

		function pointChange() {
			var pointId = document.getElementById("input-point").value;
			$.getJSON("Json_selectItemEvaScores", {
				pointId : pointId
			}, function(data) {
				//清空显示层中的数据   
				$("#input-grade").html("");
				//使用jQuery中的each(data,function(){});函数 
				$.each(data.itemEvaluateScores, function(i, value) {
					$("#input-grade").append(
							"<option value="+value.itemEvaScoreId+">"
									+ value.itemEvaScoreName + "</option>");
				});
				gradeChange();
			});
		}
		function gradeChange() {
			var gradeId = document.getElementById("input-grade").value;
			$
					.getJSON(
							"Json_selectItemEvaScore",
							{
								gradeId : gradeId
							},
							function(data) {
								if (data.itemEvaluateScore.isTeam == "1") {
									document.getElementById("itemobject").style.display = "";
								} else {
									document.getElementById("itemobject").style.display = "none";
									document.getElementById("input-object").value="个人";
									document.getElementById("itemRoles").style.display = "none";
									document.getElementById("input-role").value="第一负责人";
									
								}
							});
		}

		function objectChange() {
			var object = document.getElementById("input-object").value;
			if (object == "团队") {
				document.getElementById("itemRoles").style.display = "";
			
			} else if (object == "个人") {
				document.getElementById("itemRoles").style.display = "none";
				$("#input-role").val("第一负责人");
			}
		}
	</script>
</body>
</html>
