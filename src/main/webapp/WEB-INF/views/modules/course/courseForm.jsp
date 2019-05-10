<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>课程基本信息管理</title>
<meta name="decorator" content="default" />
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/course/course/">课程基本信息列表</a></li>
		<li class="active"><a
			href="${ctx}/course/course/form?id=${course.id}">课程基本信息<shiro:hasPermission
					name="course:course:edit">${not empty course.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="course:course:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="course"
		action="${ctx}/course/course/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />

		<div class="control-group"  id="element_course_educational">
			<label class="control-label">课程名称：</label>
			<div class="controls">

				<select id="cursNum" name="cursNum" class="cursNum input-medium"
					style="width: 200px;">
					<option>请选择</option>
					<c:if test="${course.id!=null and course.id!=''}" >
					<option value="${course.cursNum}" selected>${course.cursName}</option>
					</c:if>
					</select> <input
					type="hidden" id="cursName" name="cursName" value="${course.cursName}" readonly="readonly">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">学时：</label>
			<div class="controls">
				<form:input path="cursClassHour" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学分：</label>
			<div class="controls">
				<form:input path="cursCredit" htmlEscape="false"
					class="input-xlarge " />
			</div>
		</div>



		<div class="control-group">
			<label class="control-label">课程性质：</label>
			<div class="controls">
				
				<form:select path="cursProperty" style="width: 200px;">
					<form:options items="${fns:getDictList('course_property')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开设学期：</label>
			<div class="controls">
				<select name="cursYearTerm" style="width: 200px;">
					<c:forEach items="${fns:termYear()}" var="termYear">
						<option value="${termYear.key}" <c:if test="${termYear.key==course.cursYearTerm}">selected</c:if>>${termYear.key}</option>
					</c:forEach>
				</select>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程类型：</label>
			<div class="controls">
				
				<form:select path="cursType" style="width: 200px;">
					<form:options items="${fns:getDictList('course_curs_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考核形式：</label>
			<div class="controls">

				<form:select path="cursForm" style="width: 200px;">
					<form:options items="${fns:getDictList('course_curs_form')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任课教师：</label>
			<div class="controls">
				${course.teacher.tchrName }
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">教学模式：</label>
			<div class="controls">
				<select name="teacMethod" style="width:200px;">
									<option value="" label=""/>
									<c:forEach items="${fns:getDictList('course_learning_model')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">课程简介：</label>
			<div class="controls">
				<form:textarea path="cursIntro" htmlEscape="false" rows="4"
					maxlength="2000" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="2000" class="input-xxlarge " />

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="course:course:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#adminCourseAddOk').click(function(){
		    	$("#courseFrom").attr("action","adminCourseAdd2?op=ok");
		    	$("#courseFrom").submit();
			});
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