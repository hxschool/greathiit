<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>答辩管理</title>
<meta name="decorator" content="default" />
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
		<li class="active"><a
			href="${ctx}/answering/admin/asAnsweringStudent/">答辩列表</a></li>
		<shiro:hasPermission name="answering:admin:asAnsweringStudent:edit">
			<li><a href="${ctx}/answering/admin/asAnsweringStudent/form">答辩添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="asAnsweringStudent"
		action="${ctx}/answering/admin/asAnsweringStudent/" method="post"
		class="breadcrumb form-search">
		<input name="asAnsweringId" type="hidden"
			value="${param.asAnsweringId}" />
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>学号：</label> <form:input path="studentNumber"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>姓名：</label> <form:input path="username"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	
	<a href="${ctx}/answering/admin/asAnsweringStudent/update?asAnsweringId=${param.asAnsweringId}" class="button button-block button-primary button-small">点击更新队列</a>
	
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="asAnsweringStudent">
				<tr>

					<td>${asAnsweringStudent.studentNumber}</td>
					<td>${asAnsweringStudent.username}</td>
					<td><c:choose>
							<c:when test="${asAnsweringStudent.status == 0}">

								<a href="#" class="button button-pill button-action button-tiny">等待排队</a>
							</c:when>

							<c:when test="${asAnsweringStudent.status ==1}">

								<a href="#"
									class="button button-pill button-caution button-tiny">答辩中</a>
							</c:when>

							<c:when test="${asAnsweringStudent.status ==2}">

								<a href="#" class="button button-pill button-tiny">已完成答辩</a>
							</c:when>

							<c:otherwise>
            	未知状态
         </c:otherwise>
						</c:choose></td>
					<td><a
						href="${ctx}/answering/admin/asAnsweringStudent/form?id=${asAnsweringStudent.id}">
							<fmt:formatDate value="${asAnsweringStudent.updateDate}"
								pattern="yyyy-MM-dd HH:mm:ss" />
					</a></td>
					<td>${asAnsweringStudent.remarks}</td>
					
						<td>
						   
						    
						<a
							href="${ctx}/answering/admin/asAnsweringStudent/form?id=${asAnsweringStudent.id}">修改</a>
							<a
							href="${ctx}/answering/admin/asAnsweringStudent/delete?id=${asAnsweringStudent.id}"
							onclick="return confirmx('确认要删除该答辩吗？', this.href)">删除</a>
							
							</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>