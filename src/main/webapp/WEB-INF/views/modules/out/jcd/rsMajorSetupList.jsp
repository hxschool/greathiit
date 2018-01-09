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
		<li class="active"><a href="${ctx}/out/jcd/rsMajorSetup/">招生计划列表</a></li>
		<shiro:hasPermission name="out:jcd:rsMajorSetup:edit"><li><a href="${ctx}/out/jcd/rsMajorSetup/form">招生计划添加</a></li></shiro:hasPermission>
	</ul>
	
		<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/out/jcd/rsMajorSetup/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/out/jcd/rsMajorSetup/template">下载模板</a>
		</form>
	</div>
	
	<form:form id="searchForm" modelAttribute="rsMajorSetup" action="${ctx}/out/jcd/rsMajorSetup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>专业编码：</label>
				<form:input path="majorId" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>专业编码</th>
				<th>专业名称</th>
				<th>已招人数</th>
				<th>计划人数</th>
				<th>状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="out:jcd:rsMajorSetup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rsMajorSetup">
			<tr>
				<td><a href="${ctx}/out/jcd/rsMajorSetup/form?id=${rsMajorSetup.id}">
					${rsMajorSetup.majorId}
				</a></td>
				<td>
					${rsMajorSetup.majorName}
				</td>
				<td>
					${rsMajorSetup.majorCount}
				</td>
				<td>
					${rsMajorSetup.majorTotal}
				</td>
				<td>
					${rsMajorSetup.status}
				</td>
				<td>
					<fmt:formatDate value="${rsMajorSetup.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rsMajorSetup.remarks}
				</td>
				<shiro:hasPermission name="out:jcd:rsMajorSetup:edit"><td>
    				<a href="${ctx}/out/jcd/rsMajorSetup/form?id=${rsMajorSetup.id}">修改</a>
					<a href="${ctx}/out/jcd/rsMajorSetup/delete?id=${rsMajorSetup.id}" onclick="return confirmx('确认要删除该招生计划吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>