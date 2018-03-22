<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报修管理管理</title>
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
		<li class="active"><a href="${ctx}/dorm/ucDormRepair/">报修管理列表</a></li>
		<shiro:hasPermission name="dorm:ucDormRepair:edit"><li><a href="${ctx}/dorm/ucDormRepair/form">我要报修</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ucDormRepair" action="${ctx}/dorm/ucDormRepair/" method="post" class="breadcrumb form-search">
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
				<th>报修类型</th>
				<th>报修主题</th>
				<th>报修内容</th>
				<th>报修人</th>
				<th>报修时间</th>
				<th>坐标</th>
				<th>报修状态</th>
				<th>维修人</th>
				<th>维修回复</th>
				<th>备注</th>
				<th>操作</th>
				<shiro:hasPermission name="dorm:ucDormRepair:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucDormRepair">
			<tr>
				<td>${fns:getDictLabel(ucDormRepair.repairType, 'repair_type', 0)}</td>
				<td>${ucDormRepair.repairTitle}</td>
				<td>${ucDormRepair.repairContent}</td>
				<td>
					${ucDormRepair.user.name}
				</td>
				<td><fmt:formatDate value="${ucDormRepair.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					${ucDormRepair.dorm.ucDormBuild.id}栋${ucDormRepair.dorm.dormFloor}层${ucDormRepair.dorm.dormNumber}
				</td>
				<td>
					${fns:getDictLabel(ucDormRepair.repairState, 'repair_state', 0)}
				</td>
				<td>
					${fns:getUserById(ucDormRepair.operationId).name}
				</td>
				<td>
					${ucDormRepair.repairReplace}
				</td>
				
				
				<td>
					${ucDormRepair.remarks}
				</td>
				<td>
					<c:if test="${ucDormRepair.repairState==1}">
						<a href="${ctx}/dorm/ucDormRepair/jiedan?id=${ucDormRepair.id}&repairState=2">接单</a>
					</c:if>
					<c:if test="${ucDormRepair.repairState==2}">
					<a href="${ctx}/dorm/ucDormRepair/form?id=${ucDormRepair.id}">操作</a>
					</c:if>
					<shiro:hasPermission name="dorm:ucDormRepair:edit"><td>
    				
					<!-- <a href="${ctx}/dorm/ucDormRepair/delete?id=${ucDormRepair.id}" onclick="return confirmx('确认要删除该报修管理吗？', this.href)">删除</a> -->
				</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>