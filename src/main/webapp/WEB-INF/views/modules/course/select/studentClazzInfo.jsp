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
		<li ><a href="${ctx}/course/select/clazz">报考班级</a></li>
		<li class="active"><a href="${ctx}/course/select/">报考信息</a></li>
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程</th>
				<th>讲授模式</th>
				<th>专业</th>
				<th>班级</th>
				<th>学号</th>
				<th>姓名</th>
				<th>更新时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="selectCourse">
			<c:set var="clazz" value="${fns:getOffice(fnc:getClassId(selectCourse.student.no))}"/>
			<tr>
				<td>
					${selectCourse.course.cursName}
				</td>
				<td>
				
				${fns:getDictLabel(selectCourse.course.courseTeachingMode.teacMethod, "teac_method", "未知")}
				</td>
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