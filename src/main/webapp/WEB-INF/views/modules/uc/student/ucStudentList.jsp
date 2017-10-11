<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学籍信息管理</title>
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
		<li class="active"><a href="${ctx}/uc/ucStudent/">学籍信息列表</a></li>
		<shiro:hasPermission name="uc:ucStudent:edit"><li><a href="${ctx}/uc/student/form">学籍信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/student/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号码：</label>
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
				<th>学号</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>身份证号码</th>
				<th>政治面貌</th>
				<th>民族</th>
				<th>学院名称</th>
				<th>专业名称</th>
				<th>班号</th>
				<th>学历</th>
				<th>学制</th>
				<th>学习形式</th>
				<th>入学日期</th>
				<th>当前所在年级</th>
				<th>结业日期</th>
				<!-- <th>状态</th> -->
				<th>身份所在城市信息</th>
				<!-- <th>更新时间</th> -->
				
				<shiro:hasPermission name="uc:ucStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucStudent">
			<tr>
				<td><a href="${ctx}/uc/ucStudent/form?id=${ucStudent.id}">
					${ucStudent.studentNumber}
				</a></td>
				<td>
					${ucStudent.username}
				</td>
				<td>
					${fns:getDictValue(ucStudent.gender, 'sex', '男')}
				</td>
				<td>
					${ucStudent.birthday}
				</td>
				<td>
					
					${fns:abbr(ucStudent.idCard,14)}
				</td>
				<td>
					${ucStudent.political}
				</td>
				<td>
					${ucStudent.nation}
				</td>
				<td>
					${ucStudent.departmentName}
				</td>
				<td>
					${ucStudent.majorName}
				</td>
				<td>
					${ucStudent.classNumber}
				</td>
				<td>
					${ucStudent.edu}
				</td>
				<td>
					${ucStudent.schoolSystem}
				</td>
				<td>
					${ucStudent.learning}
				</td>
				<td>
					${ucStudent.startDate}
				</td>
				<td>
					${ucStudent.currentLevel}
				</td>
				<td>
					${ucStudent.overDate}
				</td>
				<!-- <td>
					${ucStudent.status}
				</td> -->
				<td>
					${ucStudent.regionName}
				</td>
				<!-- <td>
					<fmt:formatDate value="${ucStudent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> -->
			
				<shiro:hasPermission name="uc:ucStudent:edit"><td>
    				<a href="${ctx}/uc/student/form?id=${ucStudent.id}">修改</a>
					<a href="${ctx}/uc/student/delete?id=${ucStudent.id}" onclick="return confirmx('确认要删除该学籍信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>