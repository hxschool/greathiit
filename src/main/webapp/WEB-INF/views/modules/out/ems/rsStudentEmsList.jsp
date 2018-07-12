<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单招录取通知书管理</title>
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
		<li class="active"><a href="${ctx}/out/ems/rsStudentEms/">单招录取通知书列表</a></li>
		<shiro:hasPermission name="out:ems:rsStudentEms:edit"><li><a href="${ctx}/out/ems/rsStudentEms/form">单招录取通知书添加</a></li></shiro:hasPermission>
	</ul>
	
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/out/ems/rsStudentEms/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " />
				
				
				
				 <a
				href="${ctx}/recruit/student/recruitStudent/template">下载模板</a>
		</form>
	</div>
	<form:form id="searchForm" modelAttribute="rsStudentEms" action="${ctx}/out/ems/rsStudentEms/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>EMS邮件编号：</label>
				<form:input path="hcFormEms" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>报考号：</label>
				<form:input path="hcFormBkh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="hcFormXm" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="hcFormSfzh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>地址：</label>
				<form:input path="hcFormDz" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>收件人：</label>
				<form:input path="hcFormSjf" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="hcFormStatus" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>EMS邮件编号</th>
				<th>报考号</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>地址</th>
				<th>收件人</th>
				<th>备注</th>
				<th>状态</th>
				<shiro:hasPermission name="out:ems:rsStudentEms:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rsStudentEms">
			<tr>
				<td><a href="${ctx}/out/ems/rsStudentEms/form?id=${rsStudentEms.id}">
					${rsStudentEms.hcFormEms}
				</a></td>
				<td>
					${rsStudentEms.hcFormBkh}
				</td>
				<td>
					${rsStudentEms.hcFormXm}
				</td>
				<td>
					${rsStudentEms.hcFormSfzh}
				</td>
				<td>
					${rsStudentEms.hcFormDz}
				</td>
				<td>
					${rsStudentEms.hcFormSjf}
				</td>
				<td>
					${rsStudentEms.hcFormRemarks}
				</td>
				<td>
					${rsStudentEms.hcFormStatus}
				</td>
				<shiro:hasPermission name="out:ems:rsStudentEms:edit"><td>
    				<a href="${ctx}/out/ems/rsStudentEms/form?id=${rsStudentEms.id}">修改</a>
					<a href="${ctx}/out/ems/rsStudentEms/delete?id=${rsStudentEms.id}" onclick="return confirmx('确认要删除该单招录取通知书吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>