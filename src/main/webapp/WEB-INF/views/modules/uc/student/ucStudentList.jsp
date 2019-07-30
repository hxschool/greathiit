<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学籍信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出学籍数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/uc/student/export");
						$("#searchForm").submit();
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
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

<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/uc/student/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/uc/student/import/template">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/uc/student/">学籍信息</a></li>
		<shiro:hasPermission name="uc:ucStudent:edit"><li><a href="${ctx}/uc/student/form">学籍添加</a></li></shiro:hasPermission>
		<li><a href="${ctx}/uc/student/result">成绩信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/student/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号码：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>年级：</label>
				<form:input path="currentLevel" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			
			
			<li><label>学历：</label>
				
				<form:select path="edu"
					class="input-medium" style="width:178px">
					<form:option value="" label="请选择" />
					<form:option value="本科" label="本科" />
					<form:option value="专科" label="专科" />
				</form:select>
			</li>
			<li><label>学制：</label>
				
				
				<form:select path="schoolSystem"
					class="input-medium" style="width:178px">
					<form:option value="" label="请选择" />
					<form:option value="2" label="2" />
					<form:option value="3" label="3" />
					<form:option value="4" label="4" />
					<form:option value="5" label="5" />
				</form:select>
			</li>
			
			<li class="clearfix"></li>
			
			<li class="btns"><label></label><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学号</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>身份证号码</th>
				<th>政治面貌</th>
				<th>民族</th>
				<th>学院名称</th>
				<th>专业名称</th>
				<th>班号</th>
				<th>学历</th>
				<th>学制</th>
				<th>学习形式</th>
				<th>入学日期</th>
				<th>当前所在年级</th>
				<th>结业日期</th>
				<!-- <th>状态</th> -->
				<th>身份所在城市信息</th>
				<!-- <th>更新时间</th> -->
				
				<shiro:hasPermission name="uc:ucStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucStudent">
			<tr>
				<td><a href="${ctx}/uc/student/form?id=${ucStudent.id}">
					${ucStudent.studentNumber}
				</a></td>
				<td>
					${ucStudent.username}
				</td>
				<td>
					${fns:getDictValue(ucStudent.gender, 'sex', '男')}
				</td>
				<td>
					${ucStudent.birthday}
				</td>
				<td>
					
					${fns:abbr(ucStudent.idCard,14)}
				</td>
				<td>
					${ucStudent.political}
				</td>
				<td>
					${ucStudent.nation}
				</td>
				<td>
					${ucStudent.departmentName}
				</td>
				<td>
					${ucStudent.majorName}
				</td>
				<td>
					${ucStudent.classNumber}
				</td>
				<td>
					${ucStudent.edu}
				</td>
				<td>
					${ucStudent.schoolSystem}
				</td>
				<td>
					${ucStudent.learning}
				</td>
				<td>
					${ucStudent.startDate}
				</td>
				<td>
					${ucStudent.currentLevel}
				</td>
				<td>
					${ucStudent.overDate}
				</td>
				<!-- <td>
					${ucStudent.status}
				</td> -->
				<td>
					${ucStudent.regionName}
				</td>
				<!-- <td>
					<fmt:formatDate value="${ucStudent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> -->
			
				<shiro:hasPermission name="uc:ucStudent:edit"><td>
    				<a class="btn btn-small btn-info" href="${ctx}/uc/student/form?id=${ucStudent.id}">修改</a>
					<a class="btn btn-small btn-danger" href="${ctx}/uc/student/delete?id=${ucStudent.id}" onclick="return confirmx('确认要删除该学籍信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>