<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师信息管理</title>
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
		<li class="active"><a href="${ctx}/teacher/teacher/">教师信息列表</a></li>
		<shiro:hasPermission name="teacher:teacher:edit"><li><a href="${ctx}/teacher/teacher/form">教师信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="teacher" action="${ctx}/teacher/teacher/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任职</th>
				<th>生日</th>
				<th>学位</th>
				<th>email</th>
				<th>邮政编码</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>性别</th>
				<th>民族</th>
				<th>政治面貌</th>
				<th>专业</th>
				<th>毕业院校</th>
				<th>办公地点</th>
				<th>联系电话</th>
				<th>政治面貌</th>
				<th>自我介绍</th>
				<th>英文自我介绍</th>
				<th>职称</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="teacher:teacher:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="teacher">
			<tr>
				<td><a href="${ctx}/teacher/teacher/form?id=${teacher.id}">
					<fmt:formatDate value="${teacher.tchrAttendDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					<fmt:formatDate value="${teacher.tchrBirthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${teacher.tchrDegree}
				</td>
				<td>
					${teacher.tchrEmail}
				</td>
				<td>
					${teacher.tchrFax}
				</td>
				<td>
					${teacher.tchrName}
				</td>
				<td>
					${teacher.tchrIdcard}
				</td>
				<td>
					${teacher.tchrGender}
				</td>
				<td>
					${teacher.tchrNation}
				</td>
				<td>
					${teacher.tchrPolitical}
				</td>
				<td>
					${teacher.tchrMajor}
				</td>
				<td>
					${teacher.tchrGraduateSchool}
				</td>
				<td>
					${teacher.tchrOfficeAddr}
				</td>
				<td>
					${teacher.tchrPhone}
				</td>
				<td>
					${teacher.tchrSchool}
				</td>
				<td>
					${teacher.tchrSelfIntroduce}
				</td>
				<td>
					${teacher.tchrEngSelfIntroduce}
				</td>
				<td>
					${teacher.tchrTitle}
				</td>
				<td>
					<fmt:formatDate value="${teacher.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${teacher.remarks}
				</td>
				<shiro:hasPermission name="teacher:teacher:edit"><td>
    				<a href="${ctx}/teacher/teacher/form?id=${teacher.id}">修改</a>
					<a href="${ctx}/teacher/teacher/delete?id=${teacher.id}" onclick="return confirmx('确认要删除该教师信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>