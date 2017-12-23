<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教学方式管理</title>
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
		<li class="active"><a href="${ctx}/course/courseTeachingMode/">教学方式列表</a></li>
		<shiro:hasPermission name="course:courseTeachingMode:edit"><li><a href="${ctx}/course/courseTeachingMode/form">教学方式添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="courseTeachingMode" action="${ctx}/course/courseTeachingMode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课程内容：</label>
				<form:input path="cursContent" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>学时：</label>
				<form:input path="period" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>教学方式：</label>
				<form:input path="teacMethod" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>course_id：</label>
				<form:input path="courseId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程内容</th>
				<th>学时</th>
				<th>教学方式</th>
				<th>course_id</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="course:courseTeachingMode:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="courseTeachingMode">
			<tr>
				<td><a href="${ctx}/course/courseTeachingMode/form?id=${courseTeachingMode.id}">
					${courseTeachingMode.cursContent}
				</a></td>
				<td>
					${courseTeachingMode.period}
				</td>
				<td>
					${courseTeachingMode.teacMethod}
				</td>
				<td>
					${courseTeachingMode.courseId}
				</td>
				<td>
					<fmt:formatDate value="${courseTeachingMode.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${courseTeachingMode.remarks}
				</td>
				<shiro:hasPermission name="course:courseTeachingMode:edit"><td>
    				<a href="${ctx}/course/courseTeachingMode/form?id=${courseTeachingMode.id}">修改</a>
					<a href="${ctx}/course/courseTeachingMode/delete?id=${courseTeachingMode.id}" onclick="return confirmx('确认要删除该教学方式吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>