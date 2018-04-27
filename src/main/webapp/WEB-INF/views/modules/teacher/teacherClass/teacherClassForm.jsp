<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师班级信息表管理</title>
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
			
			$('#element_id').cxSelect({ 
				  url: '${ctx}/sys/office/treeClassLink',
				  selects: ['school', 'major', 'grade'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
			
$('#grade').change(function(){
				
				var parnetId = $("#major").children('option:selected').val();
				var grade = $("#grade").children('option:selected').val();
				 console.log(parnetId);
				 console.log(grade);
				 $.ajax({
					  url: '${ctx}/sys/office/ajaxClass',
			          async: false,
			          data: {
			        	  parnetId: parnetId,
			        	  grade: grade
			          },
			          success: function( data ) {
			        	  $("#w_test").empty();
			        	  if(data.length==0){
			        		//  $("#dormMessage").css("color","red");
		                	//  $("#dormMessage").html("当前寝室没有入住任何学员");
			        	  }
			        	  
			        	  for(var i=0 ;i<data.length;i++){
			        		  $("#w_test").append("<div style='width:120px;float:left;'> <input type='checkbox' class='classNumber' value='"+data[i].id+"' name='classNumber'/>"+data[i].name +"</div>");
			           	  }
			          }
			        });
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/teacher/teacherClass/">教师班级信息表列表</a></li>
		<li class="active"><a href="${ctx}/teacher/teacherClass/form?id=${teacherClass.id}">教师班级信息表<shiro:hasPermission name="teacher:teacherClass:edit">${not empty teacherClass.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="teacher:teacherClass:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="teacherClass" action="${ctx}/teacher/teacherClass/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div id="element_id">
		<div class="control-group">
			<label class="control-label">学院：</label>
			<div class="controls">
				<select name="school" id="school"
								class="school" style="width:200px;">
								<option value="" selected="selected">==请选择学院==</option>
							</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">专业：</label>
			<div class="controls">
				<select name="major" id="major"
									class="major" style="width:200px;">
									<option value="" selected="selected">==请选择专业==</option>
								</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">年级：</label>
			<div class="controls">
				<select name="grade"  id="grade" class="grade" style="width:200px;" >
	                          <option value="" selected="selected">==请选择年级==</option>
	                        </select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">班级：</label>
			<div class="controls">
				<div id="w_test"></div>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="teacher:teacherClass:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>