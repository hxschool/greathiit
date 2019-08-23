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
					var tmpSelect = "";
			        debugger;
					$("#area option:selected").each(function(){
			            tmpSelect = tmpSelect + "<option value='"+$(this).val()+"' selected>"+$(this).text()+"</option>"; 
			        });
					
			   
  					$("#area").empty();
  					$("#area").append(tmpSelect);
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

		<li class="active"><a
			href="">公共选课</a></li>
		
	</ul>
	<br />
	<sys:message content="${message}" />
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/course/course/selectCourse" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
		
		<li><label>课程名称：</label>
					<input name="course.cursName"  maxlength="64" class="input-medium"/>
			</li>

					<li><label>学期：</label>
					<select name="course.cursYearTerm" class="input-medium">
						<option value="">请选择</option>
						<c:forEach items="${fns:termYear()}" var="termYear">
							<option value="${termYear.key}"
																	<c:if test="${config.termYear==termYear.key}"> selected="selected" </c:if>>${termYear.key}</option>
						</c:forEach>
					</select>
			</li>
		
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="点击导出"/>
			
				
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文件名</th>
				<th>点击下载</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="f">
				<tr>
					<td>${f.name }</td>
					<td><a  class="btn btn-primary" href="/download/selectcourse/${f.name}">点击下载</a>
					<a  class="btn btn-danger" href="/course/course/delete_select_course_export_file?filename=${f.name}">点击删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>