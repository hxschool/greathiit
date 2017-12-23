<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程考试与教学目标支撑分值设置管理</title>
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
		<li class="active"><a href="${ctx}/course/courseCompositionRules/">课程考试与教学目标支撑分值设置列表</a></li>
		<shiro:hasPermission name="course:courseCompositionRules:edit"><li><a href="${ctx}/course/courseCompositionRules/form">课程考试与教学目标支撑分值设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseCompositionRules" action="${ctx}/course/courseCompositionRules/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课堂表现：</label>
				<form:input path="clazzPer" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>实验成绩：</label>
				<form:input path="expResultPer" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>期末成绩：</label>
				<form:input path="finalExamper" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>平时作业：</label>
				<form:input path="homeworkResultPer" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>期中成绩：</label>
				<form:input path="midTermPer" htmlEscape="false" class="input-medium"/>
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
				<th>课堂表现</th>
				<th>实验成绩</th>
				<th>期末成绩</th>
				<th>平时作业</th>
				<th>期中成绩</th>
				<th>course_id</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="course:courseCompositionRules:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseCompositionRules">
			<tr>
				<td><a href="${ctx}/course/courseCompositionRules/form?id=${courseCompositionRules.id}">
					${courseCompositionRules.clazzPer}
				</a></td>
				<td>
					${courseCompositionRules.expResultPer}
				</td>
				<td>
					${courseCompositionRules.finalExamper}
				</td>
				<td>
					${courseCompositionRules.homeworkResultPer}
				</td>
				<td>
					${courseCompositionRules.midTermPer}
				</td>
				<td>
					${courseCompositionRules.courseId}
				</td>
				<td>
					<fmt:formatDate value="${courseCompositionRules.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseCompositionRules.remarks}
				</td>
				<shiro:hasPermission name="course:courseCompositionRules:edit"><td>
    				<a href="${ctx}/course/courseCompositionRules/form?id=${courseCompositionRules.id}">修改</a>
					<a href="${ctx}/course/courseCompositionRules/delete?id=${courseCompositionRules.id}" onclick="return confirmx('确认要删除该课程考试与教学目标支撑分值设置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>