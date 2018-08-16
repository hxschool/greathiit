<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的好友管理</title>
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
		<li class="active"><a href="${ctx}/im/friend/chatFriend/">我的好友列表</a></li>
		<shiro:hasPermission name="im:friend:chatFriend:edit"><li><a href="${ctx}/im/friend/chatFriend/form">我的好友添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="chatFriend" action="${ctx}/im/friend/chatFriend/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>gid：</label>
				<form:input path="用户组" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>uid：</label>
				<form:input path="uid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>fid：</label>
				<form:input path="fid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>gid</th>
				<th>uid</th>
				<th>fid</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="im:friend:chatFriend:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="chatFriend">
			<tr>
				<td><a href="${ctx}/im/friend/chatFriend/form?id=${chatFriend.id}">
					${chatFriend.用户组}
				</a></td>
				<td>
					${chatFriend.uid}
				</td>
				<td>
					${chatFriend.fid}
				</td>
				<td>
					<fmt:formatDate value="${chatFriend.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${chatFriend.remarks}
				</td>
				<shiro:hasPermission name="im:friend:chatFriend:edit"><td>
    				<a href="${ctx}/im/friend/chatFriend/form?id=${chatFriend.id}">修改</a>
					<a href="${ctx}/im/friend/chatFriend/delete?id=${chatFriend.id}" onclick="return confirmx('确认要删除该我的好友吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>