<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程基本信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出课程数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/course/course/export");
						$("#searchForm").submit();
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
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
		<li class="active"><a href="${ctx}/course/course/">课程基本信息列表</a></li>
		<shiro:hasPermission name="course:course:edit"><li><a href="${ctx}/course/course/form">课程基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/course/course/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
		<li><label>课程名称：</label>
					<form:input path="cursName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
		
		<li><label>教师姓名：</label>
					<form:input path="teacher.tchrName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
					<li><label>学期：</label>
					<select name="cursYearTerm" class="input-medium">
						<option value="">请选择</option>
						<c:forEach items="${fns:termYear()}" var="term">
							<option value="${term.key}">${term.value}</option>
						</c:forEach>
					</select>
			</li>
		
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" />
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
				<th>专业</th>
				<th>学时</th>
				<th>学分</th>
				<th>开设学期</th>
				
				<!--<th>与相关课程的分工衔接</th>
				<th>其他说明</th>  -->
				<th>先修课程</th>
				<th>课程性质</th>
				<th>课程类型</th>
				<th>考核</th>

				<th>课程简介</th>
				<shiro:hasPermission name="course:course:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="course">
			<tr>
				<td><a href="${ctx}/course/course/form?id=${course.id}">
					${course.cursNum}
				</a></td>
				<td>
					${course.cursName}
				</td>
				<td>
					${course.teacher.tchrName}
				</td>
				<td>
					${course.cursMajor}
				</td>
				<td>
					${course.cursClassHour}
				</td>
				<td>
					${course.cursCredit}
				</td>
				<td>
					${course.cursYearTerm}
				</td>
				
				<!-- <td>
					${course.cursNote1}
				</td>
				<td>
					${course.cursNote2}
				</td> -->
				<td>
					${course.cursPreCourses}
				</td>
				<td>
		
					${fns:getDictLabel(course.cursType, 'course_property', '')}
				</td>

				<td>
					
					${fns:getDictLabel(course.cursType, 'course_curs_type', '')}
				</td>
				<td>
					
					${fns:getDictLabel(course.cursForm, 'course_curs_form', '')}
				</td>

				<td>
					
					<a href ="javascript:return false;" onclick="return false;" style="cursor: default;" title="${course.cursIntro}">${fns:abbr(course.cursIntro,10)}</a>
				</td>
				<shiro:hasPermission name="course:course:edit"><td>
    				<a class="btn btn-primary"  href="${ctx}/course/course/form?id=${course.id}">修改</a>
					<a class="btn btn-primary"  href="${ctx}/course/course/delete?id=${course.id}" onclick="return confirmx('确认要删除该课程基本信息吗？', this.href)">删除</a>
					<!-- <a class="btn btn-success"  href="${ctx}/course/course/import/studentCourse?id=${course.id}">导出成绩模板</a> -->
					<shiro:hasPermission name="student:studentCourse:export">
					<a class="btn btn-success"  href="${ctx}/student/studentCourse/export/student?id=${course.id}">导出学生信息</a>
					</shiro:hasPermission>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>