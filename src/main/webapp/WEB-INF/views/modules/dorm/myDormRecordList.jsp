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
	<br>
	
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
				<td>
					<fmt:formatDate value="${ucDormRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					${ucDormRecord.remarks}
				</td>
				<td>
    				<a href="${ctx}/dorm/ucDormRecord/detail?id=${ucDormRecord.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>