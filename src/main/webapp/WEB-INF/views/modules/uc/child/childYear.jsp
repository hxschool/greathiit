<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${label}管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">年级管理</a></li>

	</ul>

	<form:form id="searchForm" modelAttribute="office"
		action="${ctx}/uc/child/year" method="post"
		class="breadcrumb form-search ">
		
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />

		<li><label>年级：</label> <form:select path="year" class="input-medium ">
				<form:options items="${years}" itemLabel="year" itemValue="year"
					htmlEscape="false" />
			</form:select></li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary"
			type="submit" value="查询" onclick="return page();" /> </li>
		<li class="clearfix"></li>
		
	</form:form>
	<sys:message content="${message}" />

	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th style="width: 20px;"><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/> </th>
				<th>${label}名称</th>
				<th>班级人数(男)</th>
					<th>班级人数(女)</th>
				<th>是否可用</th>
				<th>备注</th>
				
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${list}" var="office">
				<tr>
					<td><input type="checkbox" name="ids" value="${office.id}" onclick="selectSingle()"/></td> 
					<td>${office.name}</td>
					<td>${office.male}</td>
						<td>${office.female}</td>
					<td>${fns:getDictLabel(office.useable, "yes_no", "")}</td>
					<td>${office.remarks}</td>
					
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th><input type=checkbox name="selid" id="checkId"
					onclick="checkAll(this, 'ids')" /></th>
				<th colspan="15"><a href="#"
					onclick="batchBox('${ctx}/uc/child/useableList')"
					class="btn btn-info">批量停用</a>
					
					<a href="#"
					onclick="batchBox('${ctx}/uc/child/deleteList')"
					class="btn btn-danger">批量删除</a>
					</th>
			</tr>
		</tfoot>
	</table>

</body>
</html>