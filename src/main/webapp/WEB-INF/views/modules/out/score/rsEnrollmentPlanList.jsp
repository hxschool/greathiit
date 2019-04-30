<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>招生计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#btnImport").click(function() {
			$.jBox($("#importBox").html(), {
				title : "导入数据",
				buttons : {
					"关闭" : true
				},
				bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
			});
		});
		
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
		<li class="active"><a href="${ctx}/out/score/rsEnrollmentPlan/">招生计划列表</a></li>
		<li><a href="${ctx}/out/score/rsEnrollmentPlan/form">招生计划添加</a></li><shiro:hasPermission name="out:score:rsEnrollmentPlan:edit"></shiro:hasPermission>
	</ul>
	
		<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/out/score/rsEnrollmentPlan/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/out/score/rsEnrollmentPlan/import/template">下载模板</a>
		</form>
	</div>
	
	<form:form id="searchForm" modelAttribute="rsEnrollmentPlan" action="${ctx}/out/score/rsEnrollmentPlan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			
			<li><label>学期：</label>
				<select name="termYear" style="width: 200px;">

					<c:forEach items="${fns:termYear()}" var="termYear">
						<option value="${termYear.key}"
							<c:if test="${config.termYear==termYear.key}"> selected="selected" </c:if>>${termYear.key}</option>
					</c:forEach>
				</select>
			</li>
			<li><label>专业名称：</label>
				<form:input path="majorName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>已招人数：</label>
				<form:input path="majorCount" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>计划人数：</label>
				<form:input path="majorTotal" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				  <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年份</th>
				<th>专业名称</th>
				<th>已招人数</th>
				<th>计划人数</th>
				<th>状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="out:score:rsEnrollmentPlan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rsEnrollmentPlan">
			<tr>
				<td><a href="${ctx}/out/score/rsEnrollmentPlan/form?id=${rsEnrollmentPlan.id}">
					${rsEnrollmentPlan.termYear}
				</a></td>
				
				<td>
					${rsEnrollmentPlan.majorName}
				</td>
				<td>
					${rsEnrollmentPlan.majorCount}
				</td>
				<td>
					${rsEnrollmentPlan.majorTotal}
				</td>
				<td>
					${fns:getDictLabel(user.userType,'yes_no','')}
				</td>
				<td>
					<fmt:formatDate value="${rsEnrollmentPlan.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rsEnrollmentPlan.remarks}
				</td>
				<shiro:hasPermission name="out:score:rsEnrollmentPlan:edit"><td>
    				<a href="${ctx}/out/score/rsEnrollmentPlan/form?id=${rsEnrollmentPlan.id}">修改</a>
					<a href="${ctx}/out/score/rsEnrollmentPlan/delete?id=${rsEnrollmentPlan.id}" onclick="return confirmx('确认要删除该招生计划吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>