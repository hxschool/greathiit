<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>答辩抽签管理</title>
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
		<li class="active"><a href="${ctx}/answering/admin/asAnswering/">答辩抽签列表</a></li>
		<li><a href="${ctx}/answering/admin/asAnswering/form">答辩抽签添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="asAnswering" action="${ctx}/answering/admin/asAnswering/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
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
				<th>标题</th>
				<th>教师</th>
				<th>教室</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="asAnswering">
			<tr>
				<td><a href="${ctx}/answering/admin/asAnswering/form?id=${asAnswering.id}">
					${asAnswering.title}
				</a></td>
				<td>
					${fns:getTeachers(asAnswering.teacherIds)}
				</td>
				<td>
					${fnc:jiaoxuelou(fnc:GetTimeCol(asAnswering.timeAdd).school)}${fnc:jiaoshi(fnc:GetTimeCol(asAnswering.timeAdd).school)}
				</td>
				<td>
					${asAnswering.remarks}
				</td>
				<td>
				
					<a href="${ctx}/answering/admin/asAnsweringStudent/list?asAnsweringId=${asAnswering.id}">查看队列详情</a>
    				<a href="${ctx}/answering/admin/asAnswering/form?id=${asAnswering.id}">修改</a>
					<a href="${ctx}/answering/admin/asAnswering/delete?id=${asAnswering.id}" onclick="return confirmx('确认要删除该答辩抽签吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>