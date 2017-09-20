<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生基本信息管理</title>
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
		<li class="active"><a href="${ctx}/uc/ucStudent/">学生基本信息列表</a></li>
		<li><a href="${ctx}/uc/ucStudent/group">数据统计</a></li>
		<shiro:hasPermission name="uc:ucStudent:edit"><li><a href="${ctx}/uc/ucStudent/form">学生基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/ucStudent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>学号：</label>
				<form:input path="number" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考试号</th>
				<th>学号</th>
				<th>真实姓名</th>
				<th>身份证号</th>
				<th>性别</th>
				<th>生日</th>
				<th>院系</th>
				<th>专业</th>
				<th>班号</th>
				<th>学历</th>
				<shiro:hasPermission name="uc:ucStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucStudent">
			<tr>
				<td><a href="${ctx}/uc/ucStudent/form?id=${ucStudent.id}">
					${ucStudent.exaNumber}
				</a></td>
				<td>
					${ucStudent.number}
				</td>
				<td>
					${ucStudent.username}
				</td>
				<td>
					${ucStudent.idCard}
				</td>
				<td>
					${fns:getDictLabel(ucStudent.gender, 'sex', '')}
				</td>
				<td>
					${ucStudent.birthday}
				</td>
				<td>
					${ucStudent.department}
				</td>
				<td>
					${ucStudent.major}
				</td>
				<td>
					${ucStudent.classNumber}
				</td>
				<td>
					${ucStudent.edu}
				</td>
				<shiro:hasPermission name="uc:ucStudent:edit"><td>
    				<a href="${ctx}/uc/ucStudent/form?id=${ucStudent.id}">修改</a>
					<a href="${ctx}/uc/ucStudent/delete?id=${ucStudent.id}" onclick="return confirmx('确认要删除该学生基本信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>