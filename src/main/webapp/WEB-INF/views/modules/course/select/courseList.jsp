<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选课信息维护</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function() {
				$.jBox($("#importBox").html(), {
					title : "导入数据",
					buttons : {
						"关闭" : true
					},
					bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
				});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/course/course/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/course/course/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/course/select/">信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/course/select/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课程名称：</label>
			 <form:input path="cursName"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>


			<li><label>任课教师：</label> <form:input path="teacher.tchrName"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>
			
		
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			
			<a class="btn btn-primary"
				href="${ctx}/course/course/import/template">下载模板</a> <input
				id="btnImport" class="btn btn-primary" type="button" value="成绩导入" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程编号</th>
				<th>课程名称</th>
				<th>任课教师</th>
				<th>开设学期</th>
				<th>学时</th>
				<th>学分</th>
				
				<th>课程性质</th>
				
				<th>课程类型</th>
				<th>课程简介</th>
				<th>备注</th>
				<th>查看</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="course">
			<tr>
				<td><a href="${ctx}/course/select/student?id=${course.id}">
					${course.cursNum}
				</a></td>
				<td>
					${course.cursName}
				</td>
				<td>
					${course.teacher.tchrName}
				</td>
		
			<td>
					${course.cursYearTerm}
				</td>
				<td>
					${course.cursClassHour}
				</td>
				<td>
					${course.cursCredit}
				</td>
				
				<td>
					
					${fns:getDictLabel(course.cursProperty, 'course_property', '暂无信息')}
				</td>

				<td>
					
					${fns:getDictLabel(course.cursType, 'course_curs_type', '暂无信息')}
				</td>
	
				<td>
					<a href="javacript:void(0);" title="${course.cursIntro }">${fns:abbr(course.cursIntro,10)}</a>
				</td>
				<td>
					${fns:abbr(course.remarks,10)}
				</td>
				<td>
				
					
    				<a  class="btn btn-primary" href="${ctx}/course/select/student?id=${course.id}" style="width:68px;">查看(${fnc:countStudents(course.id)})</a>
					<a class="btn btn-primary" href="${ctx}/course/course/form?id=${course.id}">修改</a>
    				<a  class="btn btn-primary" href="${ctx}/course/course/teacherCourseModify?cursId=${course.id}" >教学大纲</a>
    				<a  class="btn btn-success" href="${ctx}/course/select/export?course.id=${course.id}">导出</a>
    				<a  class="btn btn-success" href="${ctx}/student/studentCourse/export/student?id=${course.id}">导出学生信息</a>
    				
    				<a  class="btn btn-success" href="${ctx}/course/select/studentCourse?id=${course.id}">导出成绩单</a>
    				
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>