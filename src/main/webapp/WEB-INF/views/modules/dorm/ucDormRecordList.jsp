<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>缺勤记录列表</title>
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
		<li class="active"><a href="${ctx}/dorm/ucDormRecord/">缺勤记录列表</a></li>
		<shiro:hasPermission name="dorm:ucDormRecord:edit"><li><a href="${ctx}/dorm/ucDormRecord/form">缺勤记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ucDormRecord" action="${ctx}/dorm/ucDormRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>楼号：</label>
				<form:input path="dormBuildId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>寝室号：</label>
				<form:input path="dormId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>日期：</label>
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ucDormRecord.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>详情：</label>
				<form:input path="detail" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>	
				<th>日期</th>
				<th>学号</th>
				<th>姓名</th>
				<th>寝室</th>
				<th>缺勤原因</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="dorm:ucDormRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucDormRecord">
			<tr>
			<td>
					<fmt:formatDate value="${ucDormRecord.date}" pattern="yyyy-MM-dd"/>
				</td>
				 <td>
					${ucDormRecord.studentNumber}
				</td>
				<td>
					${ucDormRecord.username}
				</td>
				<td>
				${ucDormRecord.dormBuildId}栋${ucDormRecord.dormId}
				</td>
				
				<td>
					${ucDormRecord.detail}
				</td>
				<td><a href="${ctx}/dorm/ucDormRecord/form?id=${ucDormRecord.id}">
					<fmt:formatDate value="${ucDormRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${ucDormRecord.remarks}
				</td>
				<shiro:hasPermission name="dorm:ucDormRecord:edit"><td>
    				<a href="${ctx}/dorm/ucDormRecord/form?id=${ucDormRecord.id}">修改</a>
					<a href="${ctx}/dorm/ucDormRecord/delete?id=${ucDormRecord.id}" onclick="return confirmx('确认要删除该查寝记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>