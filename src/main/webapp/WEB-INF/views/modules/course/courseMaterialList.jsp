<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程基本信息管理</title>
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
		<li class="active"><a href="${ctx}/course/courseMaterial/">课程基本信息列表</a></li>
		<shiro:hasPermission name="course:courseMaterial:edit"><li><a href="${ctx}/course/courseMaterial/form">课程基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseMaterial" action="${ctx}/course/courseMaterial/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>作者：</label>
				<form:input path="cmAuthor" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>书名：</label>
				<form:input path="cmName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>出版年份：</label>
				<form:input path="cmPubYear" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>出版社：</label>
				<form:input path="cmPublisher" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>作者</th>
				<th>书名</th>
				<th>出版年份</th>
				<th>出版社</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="course:courseMaterial:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseMaterial">
			<tr>
				<td><a href="${ctx}/course/courseMaterial/form?id=${courseMaterial.id}">
					${courseMaterial.cmAuthor}
				</a></td>
				<td>
					${courseMaterial.cmName}
				</td>
				<td>
					${courseMaterial.cmPubYear}
				</td>
				<td>
					${courseMaterial.cmPublisher}
				</td>
				<td>
					<fmt:formatDate value="${courseMaterial.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseMaterial.remarks}
				</td>
				<shiro:hasPermission name="course:courseMaterial:edit"><td>
    				<a href="${ctx}/course/courseMaterial/form?id=${courseMaterial.id}">修改</a>
					<a href="${ctx}/course/courseMaterial/delete?id=${courseMaterial.id}" onclick="return confirmx('确认要删除该课程基本信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>