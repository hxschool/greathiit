<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设置课程考试与教学目标支撑分值管理</title>
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
		<li class="active"><a href="${ctx}/course/courseTeachingtarget/">设置课程考试与教学目标支撑分值列表</a></li>
		<shiro:hasPermission name="course:courseTeachingtarget:edit"><li><a href="${ctx}/course/courseTeachingtarget/form">设置课程考试与教学目标支撑分值添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseTeachingtarget" action="${ctx}/course/courseTeachingtarget/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>教学目标：</label>
				<form:input path="tchtargetContent" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>课堂表现：</label>
				<form:input path="tchtargetClassTargetValue" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>平时作业：</label>
				<form:input path="tchtargetHomeworkTargetValue" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>实验成绩：</label>
				<form:input path="tchtargetExpTargetValue" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>期中成绩：</label>
				<form:input path="tchtargetMidTargetValue" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>期末成绩：</label>
				<form:input path="tchtargetFinTargetValue" htmlEscape="false" class="input-medium"/>
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
				<th>教学目标</th>
				<th>课堂表现</th>
				<th>平时作业</th>
				<th>实验成绩</th>
				<th>期中成绩</th>
				<th>期末成绩</th>
				<th>course_id</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="course:courseTeachingtarget:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseTeachingtarget">
			<tr>
				<td><a href="${ctx}/course/courseTeachingtarget/form?id=${courseTeachingtarget.id}">
					${courseTeachingtarget.tchtargetContent}
				</a></td>
				<td>
					${courseTeachingtarget.tchtargetClassTargetValue}
				</td>
				<td>
					${courseTeachingtarget.tchtargetHomeworkTargetValue}
				</td>
				<td>
					${courseTeachingtarget.tchtargetExpTargetValue}
				</td>
				<td>
					${courseTeachingtarget.tchtargetMidTargetValue}
				</td>
				<td>
					${courseTeachingtarget.tchtargetFinTargetValue}
				</td>
				<td>
					${courseTeachingtarget.courseId}
				</td>
				<td>
					<fmt:formatDate value="${courseTeachingtarget.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseTeachingtarget.remarks}
				</td>
				<shiro:hasPermission name="course:courseTeachingtarget:edit"><td>
    				<a href="${ctx}/course/courseTeachingtarget/form?id=${courseTeachingtarget.id}">修改</a>
					<a href="${ctx}/course/courseTeachingtarget/delete?id=${courseTeachingtarget.id}" onclick="return confirmx('确认要删除该设置课程考试与教学目标支撑分值吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>