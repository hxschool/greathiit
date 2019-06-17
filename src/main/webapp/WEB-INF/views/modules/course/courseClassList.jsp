<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程班级管理</title>
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
		<li ><a href="${ctx}/course/course/">返回</a></li>
		<li class="active"><a href="${ctx}/course/courseClass/">课程班级列表</a></li>
		<shiro:hasPermission name="course:courseClass:edit"><li><a href="${ctx}/course/courseClass/form">课程班级添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseClass" action="${ctx}/course/courseClass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>班级编码：</label>
				<form:input path="cls.id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>tips</th>
				<th>班级编码</th>
				
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="course:courseClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseClass">
			<tr>
				<td><a href="${ctx}/course/courseClass/form?id=${courseClass.id}">
					${courseClass.course.id}
				</a></td>
				<td>
					${courseClass.cls.id}
				</td>
				<td>
					${courseClass.tips}
				</td>
				<td>
					<fmt:formatDate value="${courseClass.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseClass.remarks}
				</td>
				<shiro:hasPermission name="course:courseClass:edit"><td>
    				<a href="${ctx}/course/courseClass/form?id=${courseClass.id}">修改</a>
					<a href="${ctx}/course/courseClass/delete?id=${courseClass.id}" onclick="return confirmx('确认要删除该课程班级吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>