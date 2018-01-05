<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>招生计划管理</title>
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
		<li class="active"><a href="${ctx}/out/jcd/rsZsjh/">招生计划列表</a></li>
		<shiro:hasPermission name="out:jcd:rsZsjh:edit"><li><a href="${ctx}/out/jcd/rsZsjh/form">招生计划添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rsZsjh" action="${ctx}/out/jcd/rsZsjh/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>专业类型：</label>
				<form:select path="majorType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('greathiit_zhaosheng_major')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>专业人数：</label>
				<form:input path="majorCount" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>专业编码</th>
				<th>专业名称</th>
				<th>专业人数</th>
				
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="out:jcd:rsZsjh:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rsZsjh">
			<tr>
				<td>
					<a href="${ctx}/out/jcd/rsZsjh/form?id=${rsZsjh.id}">${rsZsjh.id}</a>
				</td>
				<td>
					${rsZsjh.majorType}
				</td>
				<td>
					${fns:getDictLabel(rsZsjh.majorType, 'greathiit_zhaosheng_major', '')}
				</td>
				<td>
					${rsZsjh.majorCount}
				</td>
				
				<td>
					<fmt:formatDate value="${rsZsjh.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rsZsjh.remarks}
				</td>
				<shiro:hasPermission name="out:jcd:rsZsjh:edit"><td>
    				<a href="${ctx}/out/jcd/rsZsjh/form?id=${rsZsjh.id}">修改</a>
					<a href="${ctx}/out/jcd/rsZsjh/delete?id=${rsZsjh.id}" onclick="return confirmx('确认要删除该招生计划吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>