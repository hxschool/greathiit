<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全局系统配置管理</title>
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
		<shiro:hasPermission name="sys:sysConfig:manager"><li class="active"><a href="${ctx}/sys/sysConfig/">全局系统配置列表</a></li></shiro:hasPermission>
		<shiro:hasPermission name="sys:sysConfig:edit"><li><a href="${ctx}/sys/sysConfig/form">全局系统配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysConfig" action="${ctx}/sys/sysConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模块：</label>
				<form:input path="module" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模块</th>
				<th>标题</th>
				<th>学期</th>
				<th>值(录取成绩其它类型值)</th>
				<th>时间范围</th>
				<th>是否关闭</th>
				<th>备注</th>
				<shiro:hasPermission name="sys:sysConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysConfig">
			<tr>
				<td><a href="${ctx}/sys/sysConfig/form?id=${sysConfig.id}">
					${sysConfig.module}
				</a></td>
				<td>
					${sysConfig.title}
				</td>
				<td>
					${sysConfig.termYear}
				</td>
				<td>
					${sysConfig.value}
				</td>
				<td>
					<fmt:formatDate value="${sysConfig.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>-<fmt:formatDate value="${sysConfig.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(sysConfig.status,'yes_no','')}
				</td>
				<td>
					${sysConfig.remarks}
				</td>
				<shiro:hasPermission name="sys:sysConfig:edit"><td>
    				<a href="${ctx}/sys/sysConfig/form?id=${sysConfig.id}">修改</a>
					<a href="${ctx}/sys/sysConfig/delete?id=${sysConfig.id}" onclick="return confirmx('确认要删除该全局系统配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>