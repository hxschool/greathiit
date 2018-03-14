<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学院教室管理管理</title>
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
		<li class="active"><a href="${ctx}/school/schoolCourse/">学院教室管理列表</a></li>
		<shiro:hasPermission name="school:schoolCourse:edit"><li><a href="${ctx}/school/schoolCourse/form">学院教室管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="schoolCourse" action="${ctx}/school/schoolCourse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学院编码：</label>
				<sys:treeselect id="office" name="office.id" value="${schoolCourse.office.id}" labelName="office.name" labelValue="${schoolCourse.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			
			<li><label>教室编码：</label>
				<form:input path="schoolRoot.id" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>学院名称</th>
				<th>教室名称</th>
				<th>备注信息</th>
				<shiro:hasPermission name="school:schoolCourse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="schoolCourse">
			<tr>
				<td><a href="${ctx}/school/schoolCourse/form?id=${schoolCourse.id}">
					${schoolCourse.office.name}
				</a></td>
				
				<td>
					${schoolCourse.schoolRoot.label}
				</td>
	
				<td>
					${schoolCourse.remarks}
				</td>
				<shiro:hasPermission name="school:schoolCourse:edit"><td>
    				<a href="${ctx}/school/schoolCourse/form?id=${schoolCourse.id}">修改</a>
					<a href="${ctx}/school/schoolCourse/delete?id=${schoolCourse.id}" onclick="return confirmx('确认要删除该学院教室管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>