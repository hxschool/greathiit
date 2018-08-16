<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>好友分组管理</title>
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
		<li class="active"><a href="${ctx}/im/group/chatGroup/">好友分组列表</a></li>
		<shiro:hasPermission name="im:group:chatGroup:edit"><li><a href="${ctx}/im/group/chatGroup/form">好友分组添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="chatGroup" action="${ctx}/im/group/chatGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>group_type：</label>
				<form:input path="groupType" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>online：</label>
				<form:input path="online" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>avatar：</label>
				<form:input path="avatar" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>groupname：</label>
				<form:input path="groupname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>group_type</th>
				<th>online</th>
				<th>avatar</th>
				<th>groupname</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="im:group:chatGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="chatGroup">
			<tr>
				<td><a href="${ctx}/im/group/chatGroup/form?id=${chatGroup.id}">
					${chatGroup.groupType}
				</a></td>
				<td>
					${chatGroup.online}
				</td>
				<td>
					${chatGroup.avatar}
				</td>
				<td>
					${chatGroup.groupname}
				</td>
				<td>
					<fmt:formatDate value="${chatGroup.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${chatGroup.remarks}
				</td>
				<shiro:hasPermission name="im:group:chatGroup:edit"><td>
    				<a href="${ctx}/im/group/chatGroup/form?id=${chatGroup.id}">修改</a>
					<a href="${ctx}/im/group/chatGroup/delete?id=${chatGroup.id}" onclick="return confirmx('确认要删除该好友分组吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>