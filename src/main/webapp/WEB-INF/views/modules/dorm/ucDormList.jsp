<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>宿舍管理管理</title>
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
	<form:form id="searchForm" modelAttribute="ucDorm" action="${ctx}/dorm/ucDorm/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公寓号：</label>
				<form:input path="ucDormBuild.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>宿舍门牌号：</label>
				<form:input path="dormNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>楼层：</label>
				<form:input path="dormFloor" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>可入住人数：</label>
				<form:input path="cnt" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>总人数：</label>
				<form:input path="total" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>寝室长：</label>
				<form:input path="master" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公寓号</th>
				<th>楼层</th>
				<th>宿舍门牌号</th>
				<th>已入住人数</th>
				<th>可入住人数</th>
				<th>总人数</th>
				<th>寝室长</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="dorm:ucDorm:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucDorm">
			<tr>
				<td><a href="${ctx}/dorm/ucDorm/form?id=${ucDorm.id}">
					${ucDorm.ucDormBuild.id}栋${ucDorm.dormFloor}层${ucDorm.dormNumber}
				</a></td>
				<td>
					${ucDorm.dormFloor}
				</td>
				<td>
					${ucDorm.dormNumber}
				</td>
				
				<td>
					${ucDorm.cnt}
				</td>
				<td>
					${ucDorm.total-ucDorm.cnt}
				</td>
				<td>
					${ucDorm.total}
				</td>
				
				<td>
					${ucDorm.master}
				</td>
				<td>
					<fmt:formatDate value="${ucDorm.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ucDorm.remarks}
				</td>
				<shiro:hasPermission name="dorm:ucDorm:edit"><td>
    				<a href="${ctx}/dorm/ucDorm/form?id=${ucDorm.id}">修改</a>
					<a href="${ctx}/dorm/ucDorm/delete?id=${ucDorm.id}" onclick="return confirmx('确认要删除该宿舍管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>