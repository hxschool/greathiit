<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>选课班级学生信息列表</title>
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
		<li class="active"><a href="${ctx}/course/select/">信息列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/course/select/clazz/Info" method="post" class="breadcrumb form-search">
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
				<th>专业</th>
				<th>班级</th>
				<th>学号</th>
				<th>姓名</th>
				<th>更新时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="selectCourse">
			<c:set var="clazz" value="${fns:getOffice(fn:substring(selectCourse.student.no, 0, 8))}"/>
			<tr>
				<td>
					${clazz.parent }
				</td>
				<td>
					${clazz }
				</td>
				<td>
					${selectCourse.student.no}
				</td>
				<td>
					${selectCourse.student.name}
				</td>
				
				<td>
					<fmt:formatDate value="${selectCourse.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>