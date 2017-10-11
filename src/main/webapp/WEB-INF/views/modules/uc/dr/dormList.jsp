<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>寝室信息管理</title>
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
		<li class="active"><a href="${ctx}/uc/dr/dorm/">寝室信息列表</a></li>
		<shiro:hasPermission name="uc:dr:dorm:edit"><li><a href="${ctx}/uc/dr/dorm/form">寝室信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dorm" action="${ctx}/uc/dr/dorm/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>体重：</label>
				<form:input path="weight" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li><label>寝室号：</label>
				<form:input path="dormNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>公寓编号</th>
				
				<th>公寓名称</th>
				<th>寝室号</th>
				<th>家庭地址</th>
				<th>手机号</th>
				<th>健康状态</th>
				<th>班主任</th>
				<th>导员</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>删除标记</th>
				<shiro:hasPermission name="uc:dr:dorm:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dorm">
			<tr>
				<td><a href="${ctx}/uc/dr/dorm/form?id=${dorm.id}">
					${dorm.studentNumber}
				</a></td>
				
				<td>
					${dorm.ucStudent.username}
				</td>
				<td>
					${fns:getDictLabel(dorm.ucStudent.gender, 'sex', '')}
				</td>
				
				<td>
					${dorm.dormbuildId}
				</td>
				<td>
					${dorm.dormbuildName}
				</td>
				<td>
					${dorm.dormNumber}
				</td>
				<td>
					${dorm.location}
				</td>
				<td>
					${dorm.mobile}
				</td>
				<td>
					${dorm.health}
				</td>
				<td>
					${dorm.master}
				</td>
				<td>
					${dorm.instructor}
				</td>
				<td>
					<fmt:formatDate value="${dorm.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dorm.remarks}
				</td>
				<td>
					${fns:getDictLabel(dorm.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="uc:dr:dorm:edit"><td>
    				<a href="${ctx}/uc/dr/dorm/form?id=${dorm.id}">修改</a>
					<a href="${ctx}/uc/dr/dorm/delete?id=${dorm.id}" onclick="return confirmx('确认要删除该寝室信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>