<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>教师信息管理</title>
<meta name="decorator" content="default" />
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
		<li class="active"><a href="${ctx}/teacher/teacher/">教师信息列表</a></li>
		<shiro:hasPermission name="teacher:teacher:edit">
			<li><a href="${ctx}/teacher/teacher/form">教师信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="teacher"
		action="${ctx}/teacher/teacher/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
			
			
		<ul class="ul-form">
			<li><label>教师姓名：</label> <form:input path="tchrName"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>
					<li><label>职称：</label> <form:input path="tchrTitle"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>
				<li><label>性别：</label>	
					<form:select path="tchrGender" class="input-mini">
							<option value="">请选择</option>
							<form:options items="${fns:getDictList('sex')}" itemLabel="label"
								itemValue="value" htmlEscape="false" />
						</form:select>
						</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>教师号</th>
				<th>任职时间</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>性别</th>
				<th>民族</th>
				<th>政治面貌</th>
				<th>生日</th>
				<th>学位</th>
				<th>email</th>
				<th>邮政编码</th>
				<th>毕业院校</th>
				<th>专业</th>
				<th>曾供职院校</th>
				<th>职称</th>
				<th>办公地点</th>
				<th>联系电话</th>

				<shiro:hasPermission name="teacher:teacher:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="teacher">
				<tr>
					<td>${teacher.teacherNumber}</td>
					<td><fmt:formatDate value="${teacher.tchrAttendDate}"
							pattern="yyyy-MM-dd" /></td>
					<td>${teacher.tchrName}</td>
					<td>${teacher.tchrIdcard}</td>
					<td>
						${fns:getDictLabel(teacher.tchrGender,'sex','')}
					</td>
					<td>
					${fns:getDictLabel(teacher.tchrNation,'nation','')}
					</td>
					<td>${fns:getDictLabel(teacher.tchrPolitical,'political','')}</td>
					<td><fmt:formatDate value="${teacher.tchrBirthday}"
							pattern="yyyy-MM-dd" /></td>
					<td>${fns:getDictLabel(teacher.tchrDegree,'student_edu','')}</td>
					<td>${teacher.tchrEmail}</td>
					<td>${teacher.tchrFax}</td>
					<td>${teacher.tchrGraduateSchool}</td>
					<td>${teacher.tchrMajor}</td>
					<td>${teacher.tchrSchool}</td>
					<td>${teacher.tchrTitle}</td>
					<td>${teacher.tchrOfficeAddr}</td>
					<td>${teacher.tchrPhone}</td>
					<shiro:hasPermission name="teacher:teacher:edit">
						<td><a href="${ctx}/teacher/teacher/form?id=${teacher.id}">修改</a>
							<a href="${ctx}/teacher/teacher/delete?id=${teacher.id}"
							onclick="return confirmx('确认要删除该教师信息吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>