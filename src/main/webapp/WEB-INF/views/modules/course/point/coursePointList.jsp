<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程班级计数点管理</title>
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
		<li class="active"><a href="${ctx}/course/point/coursePoint/">课程班级计数点列表</a></li>
		<shiro:hasPermission name="course:point:coursePoint:edit"><li><a href="${ctx}/course/point/coursePoint/form">课程班级计数点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="coursePoint" action="${ctx}/course/point/coursePoint/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>班级编号,默认公共课：</label>
				<form:input path="office.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>课程编码：</label>
				<form:input path="course.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>百分比</th>
				<th>计数点</th>
				<th>班级编号,默认公共课</th>
				<th>课程编码</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="course:point:coursePoint:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="coursePoint">
			<tr>
				<td><a href="${ctx}/course/point/coursePoint/form?id=${coursePoint.id}">
					${coursePoint.percentage}
				</a></td>
				<td>
					${coursePoint.point}
				</td>
				<td>
					${coursePoint.office.id}
				</td>
				<td>
					${coursePoint.course.id}
				</td>
				<td>
					<fmt:formatDate value="${coursePoint.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${coursePoint.remarks}
				</td>
				<shiro:hasPermission name="course:point:coursePoint:edit"><td>
    				<a href="${ctx}/course/point/coursePoint/form?id=${coursePoint.id}">修改</a>
					<a href="${ctx}/course/point/coursePoint/delete?id=${coursePoint.id}" onclick="return confirmx('确认要删除该课程班级计数点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>