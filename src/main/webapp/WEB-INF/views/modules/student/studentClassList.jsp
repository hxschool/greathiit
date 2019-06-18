<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生班级管理</title>
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
		<li class="active"><a href="${ctx}/student/studentClass/">学生班级列表</a></li>
		<shiro:hasPermission name="student:studentClass:edit"><li><a href="${ctx}/student/studentClass/form">学生班级添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="studentClass" action="${ctx}/student/studentClass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>班级号：</label>
				<form:input path="classId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>0:初始化,最大值：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学号</th>
				<th>班级号</th>
				<th>0:初始化,最大值</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="student:studentClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="studentClass">
			<tr>
				<td><a href="${ctx}/student/studentClass/form?id=${studentClass.id}">
					${studentClass.studentNumber}
				</a></td>
				<td>
					${studentClass.classId}
				</td>
				<td>
					${studentClass.orderNumber}
				</td>
				<td>
					<fmt:formatDate value="${studentClass.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${studentClass.remarks}
				</td>
				<shiro:hasPermission name="student:studentClass:edit"><td>
    				<a href="${ctx}/student/studentClass/form?id=${studentClass.id}">修改</a>
					<a href="${ctx}/student/studentClass/delete?id=${studentClass.id}" onclick="return confirmx('确认要删除该学生班级吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>