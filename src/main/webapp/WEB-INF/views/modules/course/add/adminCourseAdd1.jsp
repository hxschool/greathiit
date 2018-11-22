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

	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}
	
	function isEmpty(){
		var inputs = document.getElementsByTagName("input");
		for(var i=0;i<inputs.length;i++){
			var value=inputs[i].value;
			if(inputs[i].name!=''&&value==""){
				alert("输入框不能为空");
				inputs[i].focus();
				return false;
			}
		}
		var hour = document.getElementById("input-hour").value;
		var credit = document.getElementById("input-credit").value;
		var errorStr = "";
		if(isNaN(hour)){
			errorStr += "学时，";
		}
		if(isNaN(credit)){
			errorStr += "学分，";
		}
		
		if(errorStr != ""){
			alert(errorStr+"必须为数字");
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
						<a href="#">课程列表</a><img class="image-path-2" src="${ctxStatic}/modules/img/zhexian.jpg"/>添加课程基本信息
					</h6>
				</div>
				
				<form action="adminCourseAdd2" method="post"
					enctype="multipart/form-data" class="form-horizontal" onsubmit="javascript:return isEmpty()">
					
					<div class="div-inf">

						<div class="div-inf-title">基本信息</div>
						<div id="element_id">
						<div class="control-group control-group-left">
							<label class="control-label">学院：</label>
							<div class="controls">
								<select class="province input-medium"><option>请选择</option></select>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">专业：</label>
							<div class="controls">
								<select id="city" name="cursMajor" class="city input-medium"><option>请选择</option></select>
							</div>
						</div>
						</div>
						<div class="control-group control-group-left" id="element_course_educational">
							<label class="control-label">课程名称：</label>
							<div class="controls">
								<select id="cursNum" name="cursNum" class="cursNum input-medium" style="width:200px;"><option>请选择</option></select>
								<input type="hidden" id="cursName" name="cursName"  readonly="readonly">
							</div>
						</div>
						
						<div class="control-group control-group-left">
							<label class="control-label">学时：</label>
							<div class="controls">
								<input type="text" name="cursClassHour" id="input-hour">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">学分：</label>
							<div class="controls">
								<input type="text" name="cursCredit" id="input-credit">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">课程性质：</label>
							<div class="controls">
								
								<select name="cursProperty" style="width:200px;">
									<option value="" label=""/>
									
									<c:forEach items="${fns:getDictList('course_property')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
					
						
						<div class="control-group control-group-left">
							<label class="control-label">开设学期：</label>
							<div class="controls">
								<input type="text" name="cursCurrTerm">
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">先修课程：</label>
							<div class="controls">
								<input type="text" name="cursPreCourses">
							</div>
						</div>
						
						<div class="control-group control-group-left">
							<label class="control-label">课程类型：</label>
							<div class="controls">
						
								
								<select name="cursType" style="width:200px;">
									<option value="" label=""/>
									
									<c:forEach items="${fns:getDictList('course_curs_type')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
							<div class="control-group control-group-left">
							<label class="control-label">考核形式：</label>
							<div class="controls">
								
								<select name="cursForm" style="width:200px;">
									<option value="" label=""/>
									
									<c:forEach items="${fns:getDictList('course_curs_form')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						
						<div class="control-group control-group-left">
							<label class="control-label">课程负责人：</label>
							<div class="controls">
								${fns:getUser().name}
							</div>
						</div>
					</div>
					<div class="div-inf">
						<div class="div-inf-title">课程简介</div>
						<div class="div-inner-text">
							<textarea name="cursIntro"></textarea>
						</div>
					</div>
					<div class="div-btn">
						<input type="submit" class="btn" value="添加">
					</div>
				</form>
			</div>
		</div>
	</div>
		<script type="text/javascript">
		$(document).ready(function() {
			
			$('#element_id').cxSelect({ 
			  url: '${ctx}/sys/office/treeLink',
			  selects: ['province', 'city', 'area'], 
			  jsonName: 'name',
			  jsonValue: 'value',
			  jsonSub: 'sub'
			}); 
			$('#element_course_educational').cxSelect({ 
				  url: '${ctx}/course/courseEducational/ajaxCourseEducational',
				  selects: ['cursNum'], 
				  jsonName: 'cursName',
				  jsonValue: 'cursNum'
			}); 
			$('#cursNum').change(function(){  
			　	$('#cursName').val($(this).children('option:selected').text()); 
			});
		});
		</script>
</body>
</html>
