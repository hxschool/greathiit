<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统秘钥管理</title>
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
		<li class="active"><a href="${ctx}/sys/sysAppconfig/">系统秘钥列表</a></li>
		<shiro:hasPermission name="sys:sysAppconfig:edit"><li><a href="${ctx}/sys/sysAppconfig/form">系统秘钥添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysAppconfig" action="${ctx}/sys/sysAppconfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>应用编号：</label>
				<form:input path="appid" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>应用名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>应用编号</th>
				<th>应用名称</th>
				<th>URL</th>
				<th>联系人</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:sysAppconfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysAppconfig">
			<tr>
				<td><a href="${ctx}/sys/sysAppconfig/form?id=${sysAppconfig.id}">
					${sysAppconfig.appid}
				</a></td>
				<td>
					${sysAppconfig.name}
				</td>
				<td>
					${sysAppconfig.url}
				</td>

				<td>
					${sysAppconfig.contactperson}
				</td>
				<td>
					<fmt:formatDate value="${sysAppconfig.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysAppconfig.remarks}
				</td>
				<shiro:hasPermission name="sys:sysAppconfig:edit"><td>
    				<a href="${ctx}/sys/sysAppconfig/form?id=${sysAppconfig.id}">修改</a>
					<a href="${ctx}/sys/sysAppconfig/delete?id=${sysAppconfig.id}" onclick="return confirmx('确认要删除该系统秘钥吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>