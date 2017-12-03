<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公寓管理管理</title>
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
		<li class="active"><a href="${ctx}/dorm/ucDormBuild/">公寓管理列表</a></li>
		<shiro:hasPermission name="dorm:ucDormBuild:edit"><li><a href="${ctx}/dorm/ucDormBuild/form">公寓管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ucDormBuild" action="${ctx}/dorm/ucDormBuild/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<tr>
			<c:forEach items="${page.list}" var="ucDormBuild">

				<td>
				
				<a  href="${ctx}/dorm/ucDorm/?dormbuildId=${ucDormBuild.dormBuildNo}">
					<img src="${ctxStatic}/dorm/${ucDormBuild.dormBuildNo}.png" height="200px"
					alt="${ucDormBuild.dormBuildCnt}" />
					</a>
				</td>
			</c:forEach>
		</tr>
	</table>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>楼号</th>
				<th>名称</th>
				<th>类型</th>
				<th>已入住人数</th>
				<th>可入住人数</th>
				<th>总人数</th>
				<th>地址</th>
				<th>更新时间</th>
				<th>楼长</th>
				<th>备注信息</th>
				
				<shiro:hasPermission name="dorm:ucDormBuild:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucDormBuild">
			<tr>
				<td><a href="${ctx}/dorm/ucDormBuild/form?id=${ucDormBuild.id}">
					${ucDormBuild.dormBuildNo}
				</a></td>
				<td>
					${ucDormBuild.dormBuildName}
				</td>
				<td>
					${fns:getDictLabel(ucDormBuild.dormBuildType, 'sex', '')}
				</td>
				
				
				<td>
					${ucDormBuild.dormBuildCnt}
				</td>
				<td>
					${ucDormBuild.dormBuildTotal-ucDormBuild.dormBuildCnt}
				</td>
				<td>
					${ucDormBuild.dormBuildTotal}
				</td>
				<td>
					${ucDormBuild.dormBuildAddress}
				</td>
				<td>
					<fmt:formatDate value="${ucDormBuild.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ucDormBuild.master}
				</td>
				<td>
					${ucDormBuild.remarks}
				</td>
				
				<shiro:hasPermission name="dorm:ucDormBuild:edit"><td>
    				<a href="${ctx}/dorm/ucDormBuild/form?id=${ucDormBuild.id}">修改</a>
					<a href="${ctx}/dorm/ucDormBuild/delete?id=${ucDormBuild.id}" onclick="return confirmx('确认要删除该公寓管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>