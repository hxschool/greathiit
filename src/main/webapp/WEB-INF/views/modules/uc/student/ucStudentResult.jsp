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
	<style type="text/css">
	.table tbody tr td{
      vertical-align: middle;
      text-align: center;
 	}
 	.table thead tr th{
      vertical-align: middle;
      text-align: center;
 	}
	</style>
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
		<li ><a href="${ctx}/uc/student/">学籍信息</a></li>
		<shiro:hasPermission name="uc:ucStudent:edit"><li><a href="${ctx}/uc/student/form">学籍添加</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/uc/student/result">成绩信息</a></li>
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
	<div class="table-responsive">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考试号</th>
				<th>学号</th>
				<th>真实姓名</th>
				<th>数学</th>
				<th>语文</th>
				<th>外语</th>
				<th>综合</th>
				<th>总分</th>
				<th>特长</th>
				<th>录取学院</th>
				<th>录取专业</th>
				<th>联系电话</th>
				<!-- <th>毕业中学名称</th>
				<th>毕业中学名称</th>
				<th>生源所在地</th>
				<th>录取通知书编号</th> -->
				<th>省份</th>
				<th>状态</th>
				<!-- <th>更新时间</th> -->
				
				<shiro:hasPermission name="uc:ucStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucStudent">
			<tr>
				<td>
					${ucStudent.exaNumber}
				</td>
				<td>
					${ucStudent.studentNumber}
				</td>
				<td>
					${ucStudent.username}
				</td>
				
				<td> ${fn:substring(recruitStudent.shuxue, 0, fn:indexOf(recruitStudent.shuxue,"."))}</td>
				<td> ${fn:substring(recruitStudent.yuwen, 0, fn:indexOf(recruitStudent.yuwen,"."))}</td>
				<td> ${fn:substring(recruitStudent.waiyu, 0, fn:indexOf(recruitStudent.waiyu,"."))}</td>
				<td> ${fn:substring(recruitStudent.zonghe, 0, fn:indexOf(recruitStudent.zonghe,"."))}</td>
				<td> ${fn:substring(recruitStudent.zongfen, 0, fn:indexOf(recruitStudent.zongfen,"."))}</td>
				<td>  ${fn:substring(recruitStudent.techang, 0, fn:indexOf(recruitStudent.techang,"."))}</td>

				<td>
					${recruitStudent.department.name}
				</td>
				
				<td>
					${recruitStudent.major.name}
				</td>
				
				<td>
					${recruitStudent.phone}
				</td>

				<td>
					${recruitStudent.province}
				</td>
				<td>
					
					${fns:getDictLabel(recruitStudent.status, 'RECRUIT_STUDENT_STATUS', '未知状态')}
				</td>
				
			
				<shiro:hasPermission name="uc:ucStudent:edit"><td width="120px">
				<a class="btn btn-small btn-info" href="${ctx}/uc/student/form?id=${ucStudent.id}">查看</a>
					<a class="btn btn-small btn-danger" href="${ctx}/uc/student/delete?id=${ucStudent.id}" onclick="return confirmx('确认要删除该学籍信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="pagination">${page}</div>
	
	

	
</body>
</html>