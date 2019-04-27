<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全局缴费配置管理</title>
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
		<li class="active"><a href="${ctx}/payment/sysPayment/">全局缴费配置列表</a></li>
		<shiro:hasPermission name="payment:sysPayment:edit"><li><a href="${ctx}/payment/sysPayment/form">全局缴费配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysPayment" action="${ctx}/payment/sysPayment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>缴费类型：</label>
				<form:input path="paymentTypeId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>缴费金额：</label>
				<form:input path="amount" htmlEscape="false" class="input-medium"/>
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
				<th>缴费类型</th>
				<th>标题</th>
				<th>缴费金额</th>
				<th>提示信息</th>
				<th>特别提示</th>
				<th>关闭提示</th>
				<th>状态,默认为0,当状态为1的时候读取关闭提示</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="payment:sysPayment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysPayment">
			<tr>
				<td><a href="${ctx}/payment/sysPayment/form?id=${sysPayment.id}">
					${sysPayment.paymentTypeId}
				</a></td>
				<td>
					${sysPayment.title}
				</td>
				<td>
					${sysPayment.amount}
				</td>
				<td>
					${sysPayment.message}
				</td>
				<td>
					${sysPayment.description}
				</td>
				<td>
					${sysPayment.tip}
				</td>
				<td>
					${sysPayment.status}
				</td>
				<td>
					<fmt:formatDate value="${sysPayment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysPayment.remarks}
				</td>
				<shiro:hasPermission name="payment:sysPayment:edit"><td>
    				<a href="${ctx}/payment/sysPayment/form?id=${sysPayment.id}">修改</a>
					<a href="${ctx}/payment/sysPayment/delete?id=${sysPayment.id}" onclick="return confirmx('确认要删除该全局缴费配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>