<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全局缴费类型配置管理</title>
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
		<li class="active"><a href="${ctx}/payment/sysPaymentType/">全局缴费类型配置列表</a></li>
		<shiro:hasPermission name="payment:sysPaymentType:edit"><li><a href="${ctx}/payment/sysPaymentType/form">全局缴费类型配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysPaymentType" action="${ctx}/payment/sysPaymentType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>描述：</label>
				<form:input path="description" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>状态,默认为0,当状态为1的时候读取关闭提示：</label>
				<form:input path="status" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>编码</th>
				<th>描述</th>
				<th>状态,默认为0,当状态为1的时候读取关闭提示</th>
				<shiro:hasPermission name="payment:sysPaymentType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysPaymentType">
			<tr>
				<td><a href="${ctx}/payment/sysPaymentType/form?id=${sysPaymentType.id}">
					${sysPaymentType.title}
				</a></td>
				<td>
					${sysPaymentType.code}
				</td>
				<td>
					${sysPaymentType.description}
				</td>
				<td>
					${sysPaymentType.status}
				</td>
				<shiro:hasPermission name="payment:sysPaymentType:edit"><td>
    				<a href="${ctx}/payment/sysPaymentType/form?id=${sysPaymentType.id}">修改</a>
					<a href="${ctx}/payment/sysPaymentType/delete?id=${sysPaymentType.id}" onclick="return confirmx('确认要删除该全局缴费类型配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>