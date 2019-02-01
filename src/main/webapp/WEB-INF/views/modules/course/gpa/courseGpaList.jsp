<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>gpa管理</title>
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
		<li class="active"><a href="${ctx}/course/gpa/courseGpa/">gpa列表</a></li>
		<shiro:hasPermission name="course:gpa:courseGpa:edit"><li><a href="${ctx}/course/gpa/courseGpa/form">gpa添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseGpa" action="${ctx}/course/gpa/courseGpa/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分组标识：</label>
				<form:input path="groupid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>分组：</label>
				<form:input path="groupname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分组标识</th>
				<th>分组</th>
				<th>成绩</th>
				<th>学分</th>
				<shiro:hasPermission name="course:gpa:courseGpa:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseGpa">
			<tr>
				<td><a href="${ctx}/course/gpa/courseGpa/form?id=${courseGpa.id}">
					${courseGpa.groupid}
				</a></td>
				<td>
					${courseGpa.groupname}
				</td>
				<td>
					${courseGpa.score}
				</td>
				<td>
					${courseGpa.credit}
				</td>
				<shiro:hasPermission name="course:gpa:courseGpa:edit"><td>
    				<a href="${ctx}/course/gpa/courseGpa/form?id=${courseGpa.id}">修改</a>
					<a href="${ctx}/course/gpa/courseGpa/delete?id=${courseGpa.id}" onclick="return confirmx('确认要删除该gpa吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>