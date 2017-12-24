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
	
	function validate(){
		//若input为空，则将其设为0
		var input = document.getElementsByTagName("input");
		for(var i=0;i<input.length;i++){
			if(input[i].value.toString().trim().length==0) input[i].value=0;
		}
		
		var midValue = document.getElementById("input-mid").value;
		var finValue = document.getElementById("input-fin").value;
		var workValue = document.getElementById("input-work").value;
		var clasValue = document.getElementById("input-cla").value;
		var expValue = document.getElementById("input-exp").value;
		
		if(parseFloat(midValue)+parseFloat(finValue)+parseFloat(workValue)+parseFloat(clasValue)+parseFloat(expValue)!=100){
			alert("各项百分比加和应该为100");
			return false;
		}
		
		var colClass = document.getElementsByClassName("colClass");
		var colWork = document.getElementsByClassName("colWork");
		var colExp = document.getElementsByClassName("colExp");
		var colMid = document.getElementsByClassName("colMid");
		var colFin = document.getElementsByClassName("colFin");
		var colClassTotal = 0.0;
		var colWorkTotal = 0.0;
		var colExpTotal = 0.0;
		var colMidTotal = 0.0;
		var colFinTotal = 0.0;
		
		for(var i=0;i<colClass.length;i++){
			colClassTotal += parseFloat(colClass[i].value);
			colWorkTotal += parseFloat(colWork[i].value);
			colExpTotal += parseFloat(colExp[i].value);
			colMidTotal += parseFloat(colMid[i].value);
			colFinTotal += parseFloat(colFin[i].value);
		}
		
		if(colClassTotal==parseFloat(clasValue)&&
				colWorkTotal==parseFloat(workValue)&&
				colExpTotal==parseFloat(expValue)&&
				colMidTotal==parseFloat(midValue)&&
				colFinTotal==parseFloat(finValue)
				){
			return true;
		}
		else{
			alert("课程考试与教学目标支撑分值设置异常，请检查！");
			return false;
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
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>课程考试与教学目标支撑分值设置
					</h6>
				</div>
				<form action="adminCourseAdd10"  method="post" enctype="multipart/form-data"
					class="form-horizontal" onsubmit="javascript:return validate()">
					<div class="div-inf">
						<div class="div-inf-title">设置评分标准</div>
						<label class="title-per-rule">评分标准：</label>
						<div class="div-per-rule">
							课堂表现&nbsp;<input name="clazzPer" type="text"
								id="input-cla"/>&nbsp;%
						</div>
						<div class="div-per-rule">
							平时作业&nbsp;<input name="homeworkResultPer"
								type="text" id="input-work"/>&nbsp;%
						</div>
						<div class="div-per-rule">
							实验成绩&nbsp;<input name="expResultPer" type="text"
								id="input-exp"/>&nbsp;%
						</div>
						<div class="div-per-rule">
							期中成绩&nbsp;<input name="midTermPer" type="text"
								id="input-mid" />&nbsp;%
						</div>
						<div class="div-per-rule">
							期末成绩&nbsp;<input name="finalExamPer" type="text"
								id="input-fin"/>&nbsp;%
						</div>
						</div>
						<div class="div-inf">
						<div class="div-inf-title">设置课程考试与教学目标支撑分值</div>
						<div class="div-curs-plan">
							<div class="div-tbl-title">表-1：课程考试与教学目标支撑分值设置表</div>
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th class="hidden"></th>
										<th class="width48">项目</th>
										<th>课堂表现</th>
										<th>平时作业</th>
										<th>实验成绩</th>
										<th>期中成绩</th>
										<th>期末成绩</th>
									</tr>
								</thead>
								<tbody>
								<s:iterator value="targets" var="t" status="s">
									<tr>
										<td class="hidden"><input name="targets[<s:property value="#s.index"/>].tchTargetId" value="<s:property value="#t.tchTargetId"/>" /></td>
										<td>目标<s:property value="#s.index+1"/></td>
										<td><input name="targets[<s:property value="#s.index"/>].tchtargetClassTargetValue" class="border0 colClass" /></td>
					 					<td><input name="targets[<s:property value="#s.index"/>].tchtargetHomeworkTargetValue" class="border0 colWork" /></td>
										<td><input name="targets[<s:property value="#s.index"/>].tchtargetExpTargetValue" class="border0 colExp" /></td>
										<td><input name="targets[<s:property value="#s.index"/>].tchtargetMidTargetValue" class="border0 colMid" /></td>
										<td><input name="targets[<s:property value="#s.index"/>].tchtargetFinTargetValue" class="border0 colFin" /></td>
									</tr>
								</s:iterator>
								</tbody>
							</table>
						</div>						
					</div>
					<div class="div-btn">
						<input type="submit" class="btn" value="下一步">
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>
