<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>访客信息管理</title>
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
		<li class="active"><a href="${ctx}/visitor/tmVisitor/">访客信息列表</a></li>
		<shiro:hasPermission name="visitor:tmVisitor:edit"><li><a href="${ctx}/visitor/tmVisitor/form">访客信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tmVisitor" action="${ctx}/visitor/tmVisitor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>访客姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>访客性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>联系电话：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>拜访寝室：</label>
				<form:input path="dromRoom" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>访客姓名</th>
				<th>访客性别</th>
				<th>联系电话</th>
				<th>拜访寝室</th>
				<th>访问事由</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="visitor:tmVisitor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmVisitor">
			<tr>
				<td><a href="${ctx}/visitor/tmVisitor/form?id=${tmVisitor.id}">
					${tmVisitor.name}
				</a></td>
				<td>
					${fns:getDictLabel(tmVisitor.sex, 'sex', '')}
				</td>
				<td>
					${tmVisitor.mobile}
				</td>
				<td>
					${tmVisitor.dromRoom}
				</td>
				<td>
					${tmVisitor.reason}
				</td>
				<td>
					<fmt:formatDate value="${tmVisitor.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tmVisitor.remarks}
				</td>
				<shiro:hasPermission name="visitor:tmVisitor:edit"><td>
    				<a href="${ctx}/visitor/tmVisitor/form?id=${tmVisitor.id}">修改</a>
					<a href="${ctx}/visitor/tmVisitor/delete?id=${tmVisitor.id}" onclick="return confirmx('确认要删除该访客信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>