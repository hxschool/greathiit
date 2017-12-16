<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计划教室管理</title>
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
		<li class="active"><a href="${ctx}/course/courseSchedule/">计划教室列表</a></li>
		<shiro:hasPermission name="course:courseSchedule:edit"><li><a href="${ctx}/course/courseSchedule/form">计划教室添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseSchedule" action="${ctx}/course/courseSchedule/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>前四位是年份,接着一位是学期,接着两位是学院号,接着三位是教室号,接着两位是周次,接着一位是次,接着一位是星期几：</label>
				<form:input path="timeAdd" htmlEscape="false" maxlength="14" class="input-medium"/>
			</li>
			<li><label>course_id：</label>
				<form:input path="courseId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>前四位是年份,接着一位是学期,接着两位是学院号,接着三位是教室号,接着两位是周次,接着一位是次,接着一位是星期几</th>
				<th>course_id</th>
				<th>7个解析一个班级</th>
				<th>0表示管理员加的课,1表示可排课,2表示已排课</th>
				<th>tips</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="course:courseSchedule:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseSchedule">
			<tr>
				<td><a href="${ctx}/course/courseSchedule/form?id=${courseSchedule.id}">
					${courseSchedule.timeAdd}
				</a></td>
				<td>
					${courseSchedule.courseId}
				</td>
				<td>
					${courseSchedule.courseClass}
				</td>
				<td>
					${courseSchedule.lock}
				</td>
				<td>
					${courseSchedule.tips}
				</td>
				<td>
					<fmt:formatDate value="${courseSchedule.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseSchedule.remarks}
				</td>
				<shiro:hasPermission name="course:courseSchedule:edit"><td>
    				<a href="${ctx}/course/courseSchedule/form?id=${courseSchedule.id}">修改</a>
					<a href="${ctx}/course/courseSchedule/delete?id=${courseSchedule.id}" onclick="return confirmx('确认要删除该计划教室吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>