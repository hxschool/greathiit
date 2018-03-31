<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>省成绩管理</title>
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
		<li class="active"><a href="${ctx}/out/sc/rsStudentResult/">省成绩列表</a></li>
		<shiro:hasPermission name="out:sc:rsStudentResult:edit"><li><a href="${ctx}/out/sc/rsStudentResult/form">省成绩添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rsStudentResult" action="${ctx}/out/sc/rsStudentResult/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>报考号：</label>
				<form:input path="hcFormBkh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="hcFormXm" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="hcFormSfzh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>报考号</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>语文</th>
				<th>数学</th>
				<th>职业技能</th>
				<th>成绩</th>
				<shiro:hasPermission name="out:sc:rsStudentResult:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rsStudentResult">
			<tr>
				<td><a href="${ctx}/out/sc/rsStudentResult/form?id=${rsStudentResult.id}">
					${rsStudentResult.hcFormBkh}
				</a></td>
				<td>
					${rsStudentResult.hcFormXm}
				</td>
				<td>
					${rsStudentResult.hcFormSfzh}
				</td>
				<td>
					${rsStudentResult.hcFormYuwen}
				</td>
				<td>
					${rsStudentResult.hcFormShuxue}
				</td>
				<td>
					${rsStudentResult.hcFormZonghe}
				</td>
				<td>
					${rsStudentResult.hcFormCj}
				</td>
				<shiro:hasPermission name="out:sc:rsStudentResult:edit"><td>
    				<a href="${ctx}/out/sc/rsStudentResult/form?id=${rsStudentResult.id}">修改</a>
					<a href="${ctx}/out/sc/rsStudentResult/delete?id=${rsStudentResult.id}" onclick="return confirmx('确认要删除该省成绩吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>