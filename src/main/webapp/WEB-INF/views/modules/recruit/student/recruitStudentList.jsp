<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统招数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/recruit/student/recruitStudent/export");
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
	.chengji
	 {width:26px; height:22px; border:1px solid #F00; margin-right:-1px; float: left;  }
	
	</style>
</head>
<body>
<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/recruit/student/recruitStudent/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/recruit/student/recruitStudent/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/recruit/student/recruitStudent/">统招数据列表</a></li>
		<shiro:hasPermission name="recruit:student:recruitStudent:edit"><li><a href="${ctx}/recruit/student/recruitStudent/form">统招数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="recruitStudent" action="${ctx}/recruit/student/recruitStudent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>考试号：</label>
				<form:input path="exaNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:input path="gender" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>出生日期：</label>
				<form:input path="birthday" htmlEscape="false" maxlength="14" class="input-medium"/>
			</li>
			<li><label>身份证号码：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/><input
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考试号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>身份号</th>
				<th>
				<div class="chengji" style="margin-left: -1px;">数学</div>
				<div class="chengji">语文</div>
				<div class="chengji">外语</div>
				<div class="chengji">综合</div>
				<div class="chengji">总分</div>
				</th>
				<th>录取学院</th>
				<th>录取专业</th>
				<th>联系电话</th>
				<!-- <th>毕业中学名称</th>
				<th>毕业中学名称</th>
				<th>生源所在地</th>
				<th>录取通知书编号</th> -->
				<th>省份</th>
				<th>状态</th>
				<shiro:hasPermission name="recruit:student:recruitStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recruitStudent">
			<tr>
				<td><a href="${ctx}/recruit/student/recruitStudent/form?id=${recruitStudent.id}">
					${recruitStudent.exaNumber}
				</a></td>
				
				<td>
					${recruitStudent.username}
				</td>
				
				<td>
					${recruitStudent.gender}
				</td>
				
				<td>
					${recruitStudent.birthday}
				</td>
				
				
				<td>
					${recruitStudent.idCard}
				</td>
				
				<td>
				<div class="chengji" style="margin-left: -1px;"> ${recruitStudent.shuxue}</div>
				<div class="chengji" > ${recruitStudent.yuwen}</div>
				<div class="chengji"> ${recruitStudent.waiyu}</div>
				<div class="chengji"> ${recruitStudent.zonghe}</div>
				<div class="chengji"> ${recruitStudent.techang}</div>
				</td>
				
			
				
				<td>
					${recruitStudent.companyName}
				</td>
				
				<td>
					${recruitStudent.officeName}
				</td>
				
				<td>
					${recruitStudent.phone}
				</td>
				
				<!-- <td>
					${recruitStudent.middleSchool}
				</td>
				<td>
					
					${fn:substring(recruitStudent.location, 0, 16)}
				</td>
				<td>
					${recruitStudent.noticeNumber}
				</td> -->
				<td>
					${recruitStudent.province}
				</td>
				<td>
					${recruitStudent.status}
				</td>
				
			
				<shiro:hasPermission name="recruit:student:recruitStudent:edit"><td>
    				<a href="${ctx}/recruit/student/recruitStudent/form?id=${recruitStudent.id}">修改</a>
					<a href="${ctx}/recruit/student/recruitStudent/delete?id=${recruitStudent.id}" onclick="return confirmx('确认要删除该统招数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>