<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程具体内容管理</title>
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
		<li class="active"><a href="${ctx}/course/courseSpecificContent/">课程具体内容列表</a></li>
		<shiro:hasPermission name="course:courseSpecificContent:edit"><li><a href="${ctx}/course/courseSpecificContent/form">课程具体内容添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseSpecificContent" action="${ctx}/course/courseSpecificContent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>基本要求：</label>
				<form:input path="cscBasRequ" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>章节名称：</label>
				<form:input path="cscChapter" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>学时：</label>
				<form:input path="cscClassHour" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>学习目的：</label>
				<form:input path="cscGoal" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>课外作业及要求：</label>
				<form:input path="cscHomework" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>学习难点：</label>
				<form:input path="cscStudyDiffi" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>学习重点：</label>
				<form:input path="cscStudyEmpha" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>教学目标：</label>
				<form:input path="cscTarget" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>成绩编号：</label>
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
				<th>基本要求</th>
				<th>章节名称</th>
				<th>学时</th>
				<th>学习目的</th>
				<th>课外作业及要求</th>
				<th>学习难点</th>
				<th>学习重点</th>
				<th>教学目标</th>
				<th>成绩编号</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="course:courseSpecificContent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseSpecificContent">
			<tr>
				<td><a href="${ctx}/course/courseSpecificContent/form?id=${courseSpecificContent.id}">
					${courseSpecificContent.cscBasRequ}
				</a></td>
				<td>
					${courseSpecificContent.cscChapter}
				</td>
				<td>
					${courseSpecificContent.cscClassHour}
				</td>
				<td>
					${courseSpecificContent.cscGoal}
				</td>
				<td>
					${courseSpecificContent.cscHomework}
				</td>
				<td>
					${courseSpecificContent.cscStudyDiffi}
				</td>
				<td>
					${courseSpecificContent.cscStudyEmpha}
				</td>
				<td>
					${courseSpecificContent.cscTarget}
				</td>
				<td>
					${courseSpecificContent.courseId}
				</td>
				<td>
					<fmt:formatDate value="${courseSpecificContent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseSpecificContent.remarks}
				</td>
				<shiro:hasPermission name="course:courseSpecificContent:edit"><td>
    				<a href="${ctx}/course/courseSpecificContent/form?id=${courseSpecificContent.id}">修改</a>
					<a href="${ctx}/course/courseSpecificContent/delete?id=${courseSpecificContent.id}" onclick="return confirmx('确认要删除该课程具体内容吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>