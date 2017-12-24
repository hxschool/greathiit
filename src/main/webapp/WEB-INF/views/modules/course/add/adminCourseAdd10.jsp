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
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/admin.css" />

<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
	<% request.removeAttribute("Message");%>//显示后将request里的Message清空，防止回退时重复显示。
	window.onload=function(){ 
		//文档加载完成后为表格中的每一个input添加name属性
		var tbody = document.getElementById("tbody");
		var inputs =  tbody.getElementsByTagName("input");
		for(var i = 0;i<inputs.length/3;i++){
			inputs[3*i+0].setAttribute("name","contributeTargets[" + i + "].conTarValue");
			inputs[3*i+1].setAttribute("name", "contributeTargets[" + i
					+ "].teachingTarget.tchTargetId");
			inputs[3*i+2].setAttribute("name", "contributeTargets[" + i
					+ "].indicatorPoint.indPointId");
		}
	};
	
	/* 验证输入数据是否符合要求 */
	function validate(){
		//input若为空则置零
		var input = document.getElementsByClassName("valueInput");
		for(var i=0;i<input.length;i++){
			if(input[i].value.toString().trim().length==0) input[i].value=0;
		}
		
		var n = document.getElementsByClassName("countTr").length;//获取表格行数
		var count = input.length/n;//获取每一行的input数
		
		for(var i=0;i<n;i++){
			var total = 0.0;
			for(var j=0;j<count;j++){
				total += parseFloat(input[count*i+j].value);
			}
			if(total != 1.0){
				alert("第"+(i+1)+"行加和不为1，请检查。");
				return false;
			}
		}
		return true;
	}
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
						<form action="adminCourseAddOk"
							method="post" onsubmit="javascript:return validate()">
							<div class="div-tbl-title">表-2：教学目标与毕业要求指标点支撑权重设置表</div>
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th class="width48"></th>
										<s:iterator value="indicatorPoint" var="i">
											<th>指标点<s:property value="#i.indPointNum" /></th>
										</s:iterator>
									</tr>
								</thead>
								<tbody id="tbody">
									<s:iterator value="targets" var="t" status="s1">
										<tr class="countTr">
											<td>目标<s:property value="#s1.index+1" /></td>
											<s:iterator value="indicatorPoint" var="i" status="s2">
												<td id="innerTd"><input class="border0 valueInput" /> <input
													value="<s:property value="#t.tchTargetId"/>" class="hidden" /> <input
													value="<s:property value="#i.indPointId"/>" class="hidden" /></td>
											</s:iterator>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							<div class="div-btn">
								<input type="submit" class="btn" value="下一步">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
