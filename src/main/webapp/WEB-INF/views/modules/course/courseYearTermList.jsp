<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学期初始化管理</title>
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
		<li class="active"><a href="${ctx}/course/courseYearTerm/">学期初始化列表</a></li>
		<shiro:hasPermission name="course:courseYearTerm:edit"><li><a href="${ctx}/course/courseYearTerm/form">学期初始化添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseYearTerm" action="${ctx}/course/courseYearTerm/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="course:courseYearTerm:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseYearTerm">
			<tr>
				<td><a href="${ctx}/course/courseYearTerm/form?id=${courseYearTerm.id}">
					<fmt:formatDate value="${courseYearTerm.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${courseYearTerm.remarks}
				</td>
				<shiro:hasPermission name="course:courseYearTerm:edit"><td>
    				<a href="${ctx}/course/courseYearTerm/form?id=${courseYearTerm.id}">修改</a>
					<a href="${ctx}/course/courseYearTerm/delete?id=${courseYearTerm.id}" onclick="return confirmx('确认要删除该学期初始化吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>