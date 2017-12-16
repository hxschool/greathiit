<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>校历校准管理</title>
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
		<li class="active"><a href="${ctx}/calendar/courseCalendar/">校历校准列表</a></li>
		<shiro:hasPermission name="calendar:courseCalendar:edit"><li><a href="${ctx}/calendar/courseCalendar/form">校历校准添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseCalendar" action="${ctx}/calendar/courseCalendar/" method="post" class="breadcrumb form-search">
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
				<th>月</th>
				<th>日</th>
				<th>年</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="calendar:courseCalendar:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseCalendar">
			<tr>
				<td><a href="${ctx}/calendar/courseCalendar/form?id=${courseCalendar.id}">
					${courseCalendar.calendarMonth}
				</a></td>
				<td>
					${courseCalendar.calendarDay}
				</td>
				<td>
					${courseCalendar.calendarYear}
				</td>
				<td>
					<fmt:formatDate value="${courseCalendar.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseCalendar.remarks}
				</td>
				<shiro:hasPermission name="calendar:courseCalendar:edit"><td>
    				<a href="${ctx}/calendar/courseCalendar/form?id=${courseCalendar.id}">修改</a>
					<a href="${ctx}/calendar/courseCalendar/delete?id=${courseCalendar.id}" onclick="return confirmx('确认要删除该校历校准吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>