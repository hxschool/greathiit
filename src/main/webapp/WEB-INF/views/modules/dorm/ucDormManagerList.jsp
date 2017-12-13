<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生查看</title>
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
		<li class="active"><a href="${ctx}/dorm/ucDorm/">宿舍管理列表</a></li>
		<shiro:hasPermission name="dorm:ucDorm:edit"><li><a href="${ctx}/dorm/ucDorm/form">宿舍管理添加</a></li></shiro:hasPermission>
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>楼层</th>
				<th>寝室号</th>
				<th>寝室长</th>
				
				<shiro:hasPermission name="dorm:ucDorm:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucDorm">
			<tr>
				<td>
					${ucDorm.studentNumber}
				</td>
				<td>
					${ucDorm.name}
				</td>
				<td>
					${ucDorm.dormFloor}
				</td>
				<td>
					${ucDorm.dormNumber}
				</td>
				<td>
					${ucDorm.matser}
				</td>
				
				<shiro:hasPermission name="dorm:ucDorm:edit"><td>
    				<a href="${ctx}/dorm/ucDorm/form?id=${ucDorm.userId}">添加缺勤记录</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>