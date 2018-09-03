<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>选课班级学生信息统计</title>
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
		<li class="active"><a href="${ctx}/course/select/">信息列表</a></li>

	</ul>
	<div class="container">

		<c:forEach items="${cls}" var="entry">


			<c:choose>
				<c:when test="${entry.key.id=='99999999' }">
					<a href="${ctx}/course/select/info?clsId=${entry.key.id}"
						class="btn btn-danger">${entry.key}(${entry.value})</a>
				</c:when>
				<c:otherwise>
					<div class="btn-group">
						<button type="button" class="btn btn-success dropdown-toggle"
							data-toggle="dropdown">
							${entry.key}(${entry.value}) <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a
								href="${ctx}/course/select/info?clsId=${entry.key.id}">查看</a></li>
							<li><a
								href="${ctx}/course/select/export?cla.id=${entry.key.id}">导出</a></li>
						</ul>
					</div>
				</c:otherwise>

			</c:choose>

		</c:forEach>


	</div>
</body>
</html>