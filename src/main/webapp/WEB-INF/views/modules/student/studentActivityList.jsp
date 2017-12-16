<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参与活动管理</title>
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
		<li class="active"><a href="${ctx}/student/studentActivity/">参与活动列表</a></li>
		<shiro:hasPermission name="student:studentActivity:edit"><li><a href="${ctx}/student/studentActivity/form">参与活动添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="studentActivity" action="${ctx}/student/studentActivity/" method="post" class="breadcrumb form-search">
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
				<th>活动名称</th>
				<th>活动日期</th>
				<th>职责描述</th>
				<th>状态</th>
				<th>活动类型</th>
				<th>主办单位</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="student:studentActivity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="studentActivity">
			<tr>
				<td><a href="${ctx}/student/studentActivity/form?id=${studentActivity.id}">
					${studentActivity.actName}
				</a></td>
				<td>
					<fmt:formatDate value="${studentActivity.actTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${studentActivity.actDuty}
				</td>
				<td>
					${studentActivity.actState}
				</td>
				<td>
					${studentActivity.actType}
				</td>
				<td>
					${studentActivity.unit}
				</td>
				<td>
					<fmt:formatDate value="${studentActivity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${studentActivity.remarks}
				</td>
				<shiro:hasPermission name="student:studentActivity:edit"><td>
    				<a href="${ctx}/student/studentActivity/form?id=${studentActivity.id}">修改</a>
					<a href="${ctx}/student/studentActivity/delete?id=${studentActivity.id}" onclick="return confirmx('确认要删除该参与活动吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>