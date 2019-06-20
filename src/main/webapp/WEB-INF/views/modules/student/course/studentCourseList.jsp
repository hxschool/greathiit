<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生成绩管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/student/course/studentCourse/">学生成绩列表</a></li>
		<shiro:hasPermission name="student:course:studentCourse:edit"><li><a href="${ctx}/student/course/studentCourse/form">学生成绩添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="studentCourse" action="${ctx}/student/course/studentCourse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>平时成绩：</label>
				<form:input path="classEvaValue" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>综合成绩：</label>
				<form:input path="evaValue" htmlEscape="false" class="input-medium"/>
			</li>

			<li><label>期末成绩：</label>
				<form:input path="finEvaValue" htmlEscape="false" class="input-medium"/>
			</li>
			
			<li><label>学期：</label>
				<form:input path="termYear" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>课程号：</label>
				<form:input path="courseId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态标记：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>平时成绩</th>
				<th>期末成绩</th>
				<th>综合成绩</th>
				<th>学期</th>
				<th>课程号</th>
				<th>学号</th>
				<th>状态标记</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="student:course:studentCourse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="studentCourse">
			<tr>
				<td><a href="${ctx}/student/course/studentCourse/form?id=${studentCourse.id}">
					${studentCourse.classEvaValue}
				</a></td>
				

				<td>
					${studentCourse.finEvaValue}
				</td>
				
				<td>
					${studentCourse.evaValue}
				</td>

				<td>
					${studentCourse.termYear}
				</td>
				<td>
					${studentCourse.courseId}
				</td>
				<td>
					${studentCourse.studentNumber}
				</td>
				<td>
					${studentCourse.status}
				</td>
				<td>
					<fmt:formatDate value="${studentCourse.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${studentCourse.remarks}
				</td>
				<shiro:hasPermission name="student:course:studentCourse:edit"><td>
    				<a href="${ctx}/student/course/studentCourse/form?id=${studentCourse.id}">修改</a>
					<a href="${ctx}/student/course/studentCourse/delete?id=${studentCourse.id}" onclick="return confirmx('确认要删除该学生成绩吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>