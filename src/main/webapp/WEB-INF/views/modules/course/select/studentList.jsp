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
		<li class="active"><a href="${ctx}/course/select/">信息列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/course/select/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程编号</th>
				<th>课程名称</th>
				<th>学生</th>
				<th>更新时间</th>
				<th>remarks</th>
				<th>查看</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="selectCourse">
			<tr>
				<td>
					${selectCourse.course.cursNum}
				</td>
				<td>
					${selectCourse.course.cursName}
				</td>
				<td>
					${selectCourse.student.name}
				</td>
				
				<td>
					<fmt:formatDate value="${selectCourse.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${selectCourse.remarks}
				</td>
				<td>
    				
					<a href="${ctx}/course/select/student/delete?id=${course.id}" onclick="return confirmx('确认要删除该课程基本信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>