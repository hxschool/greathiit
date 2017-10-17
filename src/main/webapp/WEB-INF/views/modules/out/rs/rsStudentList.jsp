<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单招报名申请表管理</title>
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
		<li class="active"><a href="${ctx}/out/rs/rsStudent/">单招报名申请表列表</a></li>
		<shiro:hasPermission name="out:rs:rsStudent:edit"><li><a href="${ctx}/out/rs/rsStudent/form">单招报名申请表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rsStudent" action="${ctx}/out/rs/rsStudent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="hc_form_xm" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:input path="hc_form_xb" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号：</label>
				<form:input path="hc_form_sfzh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>意向专业1：</label>
				<form:input path="hc_form_zy1" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>意向专业2：</label>
				<form:input path="hc_form_zy2" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>意向专业3：</label>
				<form:input path="hc_form_zy3" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>负责人电话：</label>
				<form:input path="hc_form_fzrdh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>身份证号</th>
				<th>意向专业1</th>
				<th>意向专业2</th>
				<th>意向专业3</th>
				<th>所在中学</th>
				<th>中学地址</th>
				<th>班主任或学校负责人姓名</th>
				<th>负责人电话</th>
				<th>希望就读地区</th>
				<th>就读地市</th>
				<th>外地就读</th>
				<th>包就业分配</th>
				<th>期待月薪</th>
				<th>学生类型</th>
				<th>成绩</th>
				<th>所在身份</th>
				<th>所在城市</th>
				<th>所在地区</th>
				<th>通讯地址</th>
				<th>联系方式</th>
				<th>备用联系方式</th>
				<th>qq</th>
				<th>备注</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="out:rs:rsStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rsStudent">
			<tr>
				<td><a href="${ctx}/out/rs/rsStudent/form?id=${rsStudent.id}">
					${rsStudent.hc_form_xm}
				</a></td>
				<td>
					${rsStudent.hc_form_xb}
				</td>
				<td>
					${rsStudent.hc_form_sfzh}
				</td>
				<td>
					${rsStudent.hc_form_zy1}
				</td>
				<td>
					${rsStudent.hc_form_zy2}
				</td>
				<td>
					${rsStudent.hc_form_zy3}
				</td>
				<td>
					${rsStudent.hc_form_szzx}
				</td>
				<td>
					${rsStudent.hc_form_zxdz}
				</td>
				<td>
					${rsStudent.hc_form_fzrxm}
				</td>
				<td>
					${rsStudent.hc_form_fzrdh}
				</td>
				<td>
					${rsStudent.hc_form_add1}
				</td>
				<td>
					${rsStudent.hc_form_add2}
				</td>
				<td>
					${rsStudent.hc_form_kuasheng}
				</td>
				<td>
					${rsStudent.hc_form_baojiuye}
				</td>
				<td>
					${rsStudent.hc_form_yuexin}
				</td>
				<td>
					${rsStudent.hc_form_xslx}
				</td>
				<td>
					${rsStudent.hc_form_cj}
				</td>
				<td>
					${rsStudent.hc_form_province}
				</td>
				<td>
					${rsStudent.hc_form_city}
				</td>
				<td>
					${rsStudent.hc_form_area}
				</td>
				<td>
					${rsStudent.hc_form_dz}
				</td>
				<td>
					${rsStudent.hc_form_sj}
				</td>
				<td>
					${rsStudent.hc_form_bysj}
				</td>
				<td>
					${rsStudent.hc_form_qq}
				</td>
				<td>
					${rsStudent.hc_form_bz}
				</td>
				<td>
					<fmt:formatDate value="${rsStudent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rsStudent.remarks}
				</td>
				<shiro:hasPermission name="out:rs:rsStudent:edit"><td>
    				<a href="${ctx}/out/rs/rsStudent/form?id=${rsStudent.id}">修改</a>
					<a href="${ctx}/out/rs/rsStudent/delete?id=${rsStudent.id}" onclick="return confirmx('确认要删除该单招报名申请表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>