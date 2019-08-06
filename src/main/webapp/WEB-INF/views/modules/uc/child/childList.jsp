<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${label}管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/uc/child/detail?grade=${param.grade}">列表</a></li>
		<shiro:hasPermission name="sys:office:edit">
			<li><a href="${ctx}/uc/child/form?grade=${param.grade}">添加</a></li>
		</shiro:hasPermission>
	</ul>
	<sys:message content="${message}" />
	
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>${label}名称</th>
				<th>${label}编码</th>
				<th>类型</th>
				<c:if test="param.grade==4">
					<th>班级人数(男)</th>
					<th>班级人数(女)</th>
				</c:if>
				<th>备注</th>
				<shiro:hasPermission name="sys:office:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${list}" var="office">
				<tr>
					<td>${office.name}</td>
					<td>${office.code}</td>
					<c:if test="param.grade==4">
						<td>${office.male}</td>
						<td>${office.female}</td>
					</c:if>
					<td>${fns:getDictLabel(office.type, "sys_office_type", "")}</td>
					<td>${office.remarks}</td>
					<shiro:hasPermission name="sys:office:edit">


						<td><a href="${ctx}/uc/child/form?id=${office.id}">修改</a> <a
							href="${ctx}/uc/child/remove?id=${office.id}"
							onclick="return confirmx('要删除该组织及所有子机构项吗？', this.href)">删除</a> <a
							href="${ctx}/uc/child/delete?id=${office.id}"
							onclick="return confirmx('要停用该组织及所有子机构项吗？', this.href)">停用</a>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>

	</table>

</body>
</html>