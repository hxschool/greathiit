<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>课程基本信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			
			 $('#adminCourseAddOk').click(function(){
			    	$("#courseFrom").attr("action","adminCourseAdd2?op=ok");
			    	$("#courseFrom").submit();
				});
			    <c:if test="${param.cursProperty!=50 }">
			    $('#element_id').cxSelect({ 
					  url: '${ctx}/sys/office/treeClassLink',
					  selects: ['province', 'city','clazz'], 
					  jsonName: 'name',
					  jsonValue: 'value',
					  jsonSub: 'sub'
					});
			    $('#clazz').change(function(){
					var parnetId = $("#city").children('option:selected').val();
					var grade = $(this).children('option:selected').val();
					
					$.ajax({
						url : '${ctx}/sys/office/ajaxClass',
						data: {"parnetId":parnetId,"grade":grade},
						async : false,
						success : function(data) {
							for(var i=0;i<data.length;i++){
								var cla = data[i];
								$("#area").append("<option value='"+cla.id+"'>"+cla.name+"</option>"); 
							}
						}
					});
					
					
				});
				$('#element_course_educational').cxSelect({ 
					  url: '${ctx}/course/courseEducational/ajaxCourseEducational',
					  selects: ['cursNum'], 
					  jsonName: 'cursName',
					  jsonValue: 'cursNum'
				}); 
				
				$('#eduCourseNum').change(function(){  
					$('#cursNum').val($(this).children('option:selected').val()); 
					$('#cursName').val($(this).children('option:selected').text()); 
				});
				</c:if>
			$.ajax({
				url : '${ctx}/teacher/teacher/ajaxTeacher',
				async : false,
				success : function(data) {
					
					var teacherNumber="";
					<c:if test="${course!=null&&course.teacher!=null&&course.teacher.teacherNumber!=null}">
						teacherNumber = "${course.teacher.teacherNumber}";
					</c:if>
					
					$.each(data, function(index, item) {						
						$("#teacherNumber").append( "<option value="+item.teacherNumber+">" + item.tchrName+ "</option>"); 
					});		
					var ddd = $("#teacherNumber").select2();
					ddd.val(teacherNumber).trigger("change");
				}
			});
			
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

		<li><a
			href="${ctx}/course/course/?cursProperty=${param.cursProperty} ">课程维护</a></li>
		<li class="active"><a
			href="${ctx}/course/course/form?id=${course.id}"> <c:if
					test="${param.cursProperty==50}">
			选课
			</c:if> <c:if test="${param.cursProperty!=50}">
			基础课程
			</c:if> <shiro:hasPermission name="course:course:edit">${not empty course.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="course:course:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<sys:message content="${message}" />
	<form:form id="inputForm" modelAttribute="course"
		action="${ctx}/course/course/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />


		<div class="control-group">
			<label class="control-label">开设学期：</label>
			<div class="controls">
				<select name="cursYearTerm" class="input-large required">
					<c:forEach items="${fns:termYear()}" var="termYear">
						<option value="${termYear.key}"
							<c:choose>
							<c:when test="${course!=null&& course.cursYearTerm==termYear.key }">
							selected
							</c:when>
							<c:otherwise>
								<c:if test="${termYear.key==config.termYear}">selected</c:if>
							</c:otherwise>
							</c:choose>>${termYear.key}</option>
					</c:forEach>
				</select>

			</div>
		</div>

		<c:choose>
			<c:when test="${param.cursProperty==50}">
				<div class="control-group">
					<label class="control-label">课程性质：</label>
					<div class="controls">
						<form:select path="cursProperty" id="cursProperty"
							class="input-large required">
							<option value="50">公共选课</option>
						</form:select>
						<span class="help-inline"><font color="red">如果是公共选课请设置课程性质公共选课</font>
						</span>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="control-group">
					<label class="control-label">课程性质：</label>
					<div class="controls">
						<form:select path="cursProperty" id="cursProperty"
							class="input-large required">
							<option value="10">公共基础课</option>
						</form:select>
						<span class="help-inline"><font color="red">如果是公共选课请设置课程性质公共选课</font>
						</span>
					</div>
				</div>
			</c:otherwise>

		</c:choose>
		<c:if test="${param.cursProperty!=50}">
		<div class="control-group" id="element_course_educational">
			<label class="control-label">教务课程：</label>
			<div class="controls">

				<select id="eduCourseNum" name="eduCourseNum"
					class="cursNum input-xlarge required">
					<option>请选择</option>
					<c:if test="${course.id!=null and course.id!=''}">
						<option value="${course.cursNum}" selected>${course.cursName}</option>
					</c:if>
				</select> <span class="help-inline"><font color="red">如果是教务处课程可以通过选择课程自动填写课程信息.如果是选课课程需要填写课程编号和课程名称</font>
				</span>
			</div>
		</div>
		</c:if>
		<c:if test="${param.cursProperty!=50}">
		<form:hidden path="cursNum" htmlEscape="false" maxlength="255"
						class="input-large required " />
		<form:hidden path="cursName" htmlEscape="false" maxlength="255"
						class="input-xlarge required" />
		</c:if>
		<c:if test="${param.cursProperty==50}">
			<div class="control-group">
				<label class="control-label">课程编号：</label>
				<div class="controls">
					<form:input path="cursNum" htmlEscape="false" maxlength="255"
						class="input-large required "  />
	
					<span class="help-inline"><font color="red">*B:本科课程,G:高职课程</font>
					</span>
				</div>
			</div>
	
	
			<div class="control-group">
				<label class="control-label">课程名称：</label>
				<div class="controls">
	
					<form:input path="cursName" htmlEscape="false" maxlength="255"
						class="input-xlarge required" />
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${param.cursProperty==50 }">
				<div class="control-group">
					<label class="control-label">分类：</label>
					<div class="controls">

						<select name="cursSelectCourseType" class="input-large required">
							<option value="">请选择</option>
							<c:forEach items="${groupSelect}" var="groupSelect">
								<optgroup label="${groupSelect.key}">
									<c:forEach items="${groupSelect.value}" var="entry">
										<option value="${entry.value}">${entry.label}</option>
									</c:forEach>
								</optgroup>
							</c:forEach>
						</select>

					</div>
				</div>
			</c:when>
			<c:otherwise>

			</c:otherwise>

		</c:choose>


		<div class="control-group">
			<label class="control-label">学时：</label>
			<div class="controls">
				<form:input path="cursClassHour" htmlEscape="false" maxlength="255"
					class="input-large required" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">学分：</label>
			<div class="controls">
				<form:input path="cursCredit" htmlEscape="false" maxlength="255"
					class="input-large required" />
			</div>
		</div>




		<div class="control-group">
			<label class="control-label">课程类型：</label>
			<div class="controls">

				<form:select path="cursType" class="input-large required">
					<form:options items="${fns:getDictList('course_curs_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考核形式：</label>
			<div class="controls">

				<form:select path="cursForm" class="input-large required">
					<form:options items="${fns:getDictList('course_curs_form')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>



		<shiro:hasPermission name="course:course:edit">
			<div class="control-group">
				<label class="control-label">任课教师：</label>
				<div class="controls">

					<select name="teacher.teacherNumber" id="teacherNumber"
						class="input-large required">

					</select>
				</div>
			</div>
		</shiro:hasPermission>

		<div class="control-group">
			<label class="control-label">教学模式：</label>
			<div class="controls">
				<select name="courseTeachingMode.teacMethod"
					class="input-large required">
					<option value="" label="" />
					<c:forEach items="${fns:getDictList('teac_method')}" var="dict">
						<option value="${dict.value}" <c:if test="${course!=null&&course.courseTeachingMode!=null&&course.courseTeachingMode.teacMethod==dict.value}"> selected </c:if> >${dict.label}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<c:choose>
			<c:when test="${param.cursProperty==50 }">

			</c:when>
			<c:otherwise>


				<div id="element_id">

					<div class="control-group">
						<label class="control-label">所属学院:</label>
						<div class="controls">

							<select class="province input-large" name="department"><option>请选择</option></select>

						</div>

					</div>

					<div class="control-group">
						<label class="control-label">所属专业:</label>
						<div class="controls">
							<select id="city" class="city input-large" name="specialty"><option>请选择</option></select>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">年级:</label>
						<div class="controls">
							<select id="clazz" class="clazz input-large"><option>请选择</option></select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">选择班级:</label>
						<div class="controls">
							<select id="area" class="area input-xlarge" name="classIds"
								multiple="multiple"><option>请选择</option></select>
						</div>
					</div>
					<c:if test="${course!=null&&param.cursProperty!=50}">
					<div class="control-group">
						<label class="control-label">选择班级:</label>
						<div class="controls">
							 
							 <c:forEach items="${ccs}" var="cs">
        							${cs.cls.name }
   							</c:forEach>
						</div>
					</div>
					</c:if>
				</div>
			</c:otherwise>

		</c:choose>
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


</body>
</html>