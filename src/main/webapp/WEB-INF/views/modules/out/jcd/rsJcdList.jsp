<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试成绩单管理</title>
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
		<li class="active"><a href="${ctx}/out/jcd/rsJcd/">考试成绩单列表</a></li>
		<shiro:hasPermission name="out:jcd:rsJcd:edit"><li><a href="${ctx}/out/jcd/rsJcd/form">考试成绩单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rsJcd" action="${ctx}/out/jcd/rsJcd/" method="post" class="breadcrumb form-search">
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
				<th>考生号</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>总分</th>
				<th>投档成绩</th>
				<th>科目1</th>
				<th>科目2</th>
				<th>科目3</th>
				<th>意向专业1</th>
				<th>意向专业2</th>
				<th>意向专业3</th>
				<th>意向专业4</th>
				<th>意向专业5</th>
				<th>意向专业6</th>
				<th>是否服从专业调剂</th>
				<shiro:hasPermission name="out:jcd:rsJcd:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rsJcd">
			<tr>
				<td><a href="${ctx}/out/jcd/rsJcd/form?id=${rsJcd.id}">
					${rsJcd.ksh}
				</a></td>
				<td>
					${rsJcd.xm}
				</td>
				<td>
					${rsJcd.sfzh}
				</td>
				<td>
					${rsJcd.zf}
				</td>
				<td>
					${rsJcd.cj}
				</td>
				<td>
					${rsJcd.km1}
				</td>
				<td>
					${rsJcd.km2}
				</td>
				<td>
					${rsJcd.km3}
				</td>
				<td>
					${rsJcd.zy1}
				</td>
				<td>
					${rsJcd.zy2}
				</td>
				<td>
					${rsJcd.zy3}
				</td>
				<td>
					${rsJcd.zy4}
				</td>
				<td>
					${rsJcd.zy5}
				</td>
				<td>
					${rsJcd.zy6}
				</td>
				<td>
					${rsJcd.zytj}
				</td>
				<shiro:hasPermission name="out:jcd:rsJcd:edit"><td>
    				<a href="${ctx}/out/jcd/rsJcd/form?id=${rsJcd.id}">修改</a>
					<a href="${ctx}/out/jcd/rsJcd/delete?id=${rsJcd.id}" onclick="return confirmx('确认要删除该考试成绩单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>