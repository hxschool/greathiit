<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教务课程信息管理</title>
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
		<li class="active"><a href="${ctx}/course/courseEducational/">教务课程信息列表</a></li>
		<shiro:hasPermission name="course:courseEducational:edit"><li><a href="${ctx}/course/courseEducational/form">教务课程信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseEducational" action="${ctx}/course/courseEducational/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课程编码：</label>
				<form:input path="cursNum" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>课程名称：</label>
				<form:input path="cursName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程编码</th>
				<th>课程名称</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="course:courseEducational:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseEducational">
			<tr>
				<td><a href="${ctx}/course/courseEducational/form?id=${courseEducational.id}">
					${courseEducational.cursNum}
				</a></td>
				<td>
					${courseEducational.cursName}
				</td>
				<td>
					<fmt:formatDate value="${courseEducational.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseEducational.remarks}
				</td>
				<shiro:hasPermission name="course:courseEducational:edit"><td>
    				<a href="${ctx}/course/courseEducational/form?id=${courseEducational.id}">修改</a>
					<a href="${ctx}/course/courseEducational/delete?id=${courseEducational.id}" onclick="return confirmx('确认要删除该教务课程信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>