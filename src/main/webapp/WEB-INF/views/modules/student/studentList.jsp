<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
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
		<li class="active"><a href="${ctx}/student/student/">学生信息列表</a></li>
		<shiro:hasPermission name="student:student:edit"><li><a href="${ctx}/student/student/form">学生信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="student" action="${ctx}/student/student/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>身份证号</th>
				<th>生日</th>
				<th>性别</th>
				<th>民族</th>
				<th>政治面貌</th>
				<th>联系地址</th>
				<th>email</th>
				<th>长期目标</th>
				<th>中期目标</th>
				<th>短期目标</th>
				<th>英文简介</th>
				<th>中文简介</th>
				<th>学历</th>
				<th>户口所在地</th>
				<th>学制</th>
				<th>用户号</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="student:student:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="student">
			<tr>
				<td><a href="${ctx}/student/student/form?id=${student.id}">
					${student.name}
				</a></td>
				<td>
					${student.idCard}
				</td>
				<td>
					<fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${student.gender}
				</td>
				<td>
					${student.nation}
				</td>
				<td>
					${student.political}
				</td>
				<td>
					${student.address}
				</td>
				<td>
					${student.mail}
				</td>
				<td>
					${student.longGoal}
				</td>
				<td>
					${student.midGoal}
				</td>
				<td>
					${student.shortGoal}
				</td>
				<td>
					${student.selfEngIntroduce}
				</td>
				<td>
					${student.selfIntroduce}
				</td>
				<td>
					${student.edu}
				</td>
				<td>
					${student.nativePlace}
				</td>
				<td>
					${student.studentLength}
				</td>
				<td>
					${student.student.no}
				</td>
				<td>
					<fmt:formatDate value="${student.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${student.remarks}
				</td>
				<shiro:hasPermission name="student:student:edit"><td>
    				<a href="${ctx}/student/student/form?id=${student.id}">修改</a>
					<a href="${ctx}/student/student/delete?id=${student.id}" onclick="return confirmx('确认要删除该学生信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>