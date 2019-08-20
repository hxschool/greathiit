<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>社交通讯录管理</title>
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
		<li class="active"><a href="${ctx}/uc/ec/ucEmergencyContact/">社交通讯录列表</a></li>
		<shiro:hasPermission name="uc:ec:ucEmergencyContact:edit"><li><a href="${ctx}/uc/ec/ucEmergencyContact/form">社交通讯录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ucEmergencyContact" action="${ctx}/uc/ec/ucEmergencyContact/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
				<li><label>姓名：</label>
				<form:input path="ucStudent.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>是否显示：</label>
				<form:select path="showFlag" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="clearfix"></li>
			
			
			<li><label>联系方式：</label>
				<form:input path="contact" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
			<li><label>类型：</label>
				<form:select path="contactType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('social_contact')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>姓名</th>
				<th>性别</th>
				<th>类型</th>
				<th>联系方式</th>
				<th>更新时间</th>
				
				<th>删除标记</th>
				<th>是否显示</th>
				<shiro:hasPermission name="uc:ec:ucEmergencyContact:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucEmergencyContact">
			<tr>
	
				<td>
				<a href="${ctx}/uc/ec/ucEmergencyContact/form?id=${ucEmergencyContact.id}">
					${ucEmergencyContact.studentNumber}
				</a>
				</td>
				<td>
					${ucEmergencyContact.ucStudent.name}
				</td>
				<td>
					${fns:getDictLabel(ucEmergencyContact.ucStudent.gender, 'sex', '')}
				</td>
				<td>
					${fns:getDictLabel(ucEmergencyContact.contactType, 'social_contact', '')}
				</td>
				<td>
					${ucEmergencyContact.contact}
				</td>
			
				<td>
					<fmt:formatDate value="${ucEmergencyContact.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					${fns:getDictLabel(ucEmergencyContact.delFlag, 'del_flag', '')}
				</td>
				<td>
					${fns:getDictLabel(ucEmergencyContact.showFlag, 'show_hide', '')}
				</td>
				<shiro:hasPermission name="uc:ec:ucEmergencyContact:edit"><td>
    				<a href="${ctx}/uc/ec/ucEmergencyContact/form?id=${ucEmergencyContact.id}">修改</a>
					<a href="${ctx}/uc/ec/ucEmergencyContact/delete?id=${ucEmergencyContact.id}" onclick="return confirmx('确认要删除该社交通讯录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>