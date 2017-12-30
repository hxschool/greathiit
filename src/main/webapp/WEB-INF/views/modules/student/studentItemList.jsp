<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>获奖信息管理</title>
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
		<li class="active"><a href="${ctx}/student/studentItem/">获奖信息列表</a></li>
		<shiro:hasPermission name="student:studentItem:edit"><li><a href="${ctx}/student/studentItem/form">获奖信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="studentItem" action="${ctx}/student/studentItem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目名称：</label>
				<form:input path="itemName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>项目编号：</label>
				<form:input path="itemNum" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>项目表彰对象：</label>
				<form:input path="itemPrizeObject" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>项目参与角色：</label>
				<form:input path="itemRole" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>审核得分：</label>
				<form:input path="itemScore" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="itemState" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>提交时间：</label>
				<input name="itemSubmitDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${studentItem.itemSubmitDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>主办单位：</label>
				<form:input path="itemUnit" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>审核意见：</label>
				<form:input path="note" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>项目指标点：</label>
				<form:select path="itemEvaPoint" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_point')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>项目等级：</label>
				<form:select path="itemEvaScore" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_layer')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>项目类别：</label>
				<form:select path="itemEvaType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>项目名称</th>
				<th>项目编号</th>
				<th>项目表彰对象</th>
				<th>项目参与角色</th>
				<th>审核得分</th>
				<th>状态</th>
				<th>提交时间</th>
				<th>主办单位</th>
				<th>审核意见</th>
				<th>项目指标点</th>
				<th>项目等级</th>
				<th>项目类别</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="student:studentItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="studentItem">
			<tr>
				<td><a href="${ctx}/student/studentItem/form?id=${studentItem.id}">
					${studentItem.itemName}
				</a></td>
				<td>
					${studentItem.itemNum}
				</td>
				<td>
					${studentItem.itemPrizeObject}
				</td>
				<td>
					${studentItem.itemRole}
				</td>
				<td>
					${studentItem.itemScore}
				</td>
				<td>
					${studentItem.itemState}
				</td>
				<td>
					<fmt:formatDate value="${studentItem.itemSubmitDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${studentItem.itemUnit}
				</td>
				<td>
					${studentItem.note}
				</td>
				<td>
					${fns:getDictLabel(studentItem.itemEvaPoint, 'project_point', '')}
				</td>
				<td>
					${fns:getDictLabel(studentItem.itemEvaScore, 'project_layer', '')}
				</td>
				<td>
					${fns:getDictLabel(studentItem.itemEvaType, 'project_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${studentItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${studentItem.remarks}
				</td>
				<shiro:hasPermission name="student:studentItem:edit"><td>
    				<a href="${ctx}/student/studentItem/form?id=${studentItem.id}">修改</a>
					<a href="${ctx}/student/studentItem/delete?id=${studentItem.id}" onclick="return confirmx('确认要删除该获奖信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>