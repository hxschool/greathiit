<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学籍异动</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/student/studentStatusLog/">变动进度表列表</a></li>
		<shiro:hasPermission name="student:studentStatusLog:edit">
			<li><a href="${ctx}/student/studentStatusLog/form">变动进度表添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="studentStatusLog"
		action="${ctx}/student/studentStatusLog/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>操作类型：</label> <form:input path="status"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td>操作模型</td>
				<td>操作人</td>
				<td>操作类型</td>
				<td>创建时间</td>
				<td>过程描述</td>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="student:studentStatusLog:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="studentStatusLog">
				<tr>
					<td>${studentStatusLog.logType=="1"?"学籍信息":"学生信息"}</td>
					<td>${studentStatusLog.createBy}</td>
					<td>
					<c:if test="${studentStatusLog.logType=='1'}">
					${fns:getDictLabel(studentStatusLog.status,'student_uc_status','')}
					</c:if>
					<c:if test="${studentStatusLog.logType=='2'}">
					${fns:getDictLabel(studentStatusLog.status,'student_status','')}
					</c:if>
					
					
					</td>
					<td><fmt:formatDate value="${studentStatusLog.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${studentStatusLog.description }</td>
					<td><fmt:formatDate value="${studentStatusLog.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${studentStatusLog.remarks}</td>
					<shiro:hasPermission name="student:studentStatusLog:edit">
						<td><a
							href="${ctx}/student/studentStatusLog/form?id=${studentStatusLog.id}">修改</a>
							<a
							href="${ctx}/student/studentStatusLog/delete?id=${studentStatusLog.id}"
							onclick="return confirmx('确认要删除该变动进度表吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>