<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统招数据管理</title>
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
	<div class="container-fluid">
	<div class="breadcrumb form-search">
		
		<ul class="ul-form">
			
			<li class="btns">
			
			<a class="btn btn-primary"
				href="${ctx}/uc/student/import/template">下载模板</a>
			<input
				id="btnImport" class="btn btn-primary" type="button" value="学籍导入" /></li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<sys:message content="${message}"/>
		
		<fieldset>
			<legend>使用说明</legend>
			请按照模板格式进行数据导入。其中姓名、身份证号码不允许为空。导入时自动生成登录信息,登录用户名为身份证号或学号，默认密码为身份证后6位。
		</fieldset>
		</div>
</body>
</html>