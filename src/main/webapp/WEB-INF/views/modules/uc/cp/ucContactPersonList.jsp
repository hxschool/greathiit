<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>紧急联系人管理</title>
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
		<li class="active"><a href="${ctx}/uc/cp/ucContactPerson/">紧急联系人列表</a></li>
		<shiro:hasPermission name="uc:cp:ucContactPerson:edit"><li><a href="${ctx}/uc/cp/ucContactPerson/form">紧急联系人添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ucContactPerson" action="${ctx}/uc/cp/ucContactPerson/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="ucStudent.username" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			
			
			
			<li><label>联系人：</label>
				<form:input path="contact" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>联系类型：</label>
				<form:select path="contactType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('contact_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>姓名</th>
				<th>性别</th>
				<th>联系类型</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>更新时间</th>
				<th>删除标记</th>
				<shiro:hasPermission name="uc:cp:ucContactPerson:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucContactPerson">
			<tr>
				
				
				<td>
				<a href="${ctx}/uc/cp/ucContactPerson/form?id=${ucContactPerson.id}">
					${ucContactPerson.studentNumber}
				</a>
				</td>
				
					<td>
					${ucContactPerson.ucStudent.username}
				</td>
				<td>
					${fns:getDictLabel(ucContactPerson.ucStudent.gender, 'sex', '')}
				</td>
				<td>
					${fns:getDictLabel(ucContactPerson.contactType, 'contact_type', '')}
				</td>
				<td>
					${ucContactPerson.contact}
				</td>
				<td>
					${ucContactPerson.mobile}
				</td>
	
				<td>
					<fmt:formatDate value="${ucContactPerson.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					${fns:getDictLabel(ucContactPerson.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="uc:cp:ucContactPerson:edit"><td>
    				<a href="${ctx}/uc/cp/ucContactPerson/form?id=${ucContactPerson.id}">修改</a>
					<a href="${ctx}/uc/cp/ucContactPerson/delete?id=${ucContactPerson.id}" onclick="return confirmx('确认要删除该紧急联系人吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>