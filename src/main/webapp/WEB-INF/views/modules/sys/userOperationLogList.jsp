<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户操作日志管理</title>
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
		<li class="active"><a href="${ctx}/sys/userOperationLog/">用户操作日志列表</a></li>
		<shiro:hasPermission name="sys:userOperationLog:edit"><li><a href="${ctx}/sys/userOperationLog/form">用户操作日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="userOperationLog" action="${ctx}/sys/userOperationLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="sys:userOperationLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userOperationLog">
			<tr>
				<td><a href="${ctx}/sys/userOperationLog/form?id=${userOperationLog.id}">
					<fmt:formatDate value="${userOperationLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${userOperationLog.remarks}
				</td>
				<shiro:hasPermission name="sys:userOperationLog:edit"><td>
    				<a href="${ctx}/sys/userOperationLog/form?id=${userOperationLog.id}">修改</a>
					<a href="${ctx}/sys/userOperationLog/delete?id=${userOperationLog.id}" onclick="return confirmx('确认要删除该用户操作日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>