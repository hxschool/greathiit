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
						$("#searchForm").attr("action", "${ctx}/course/paike/export_select");
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
	<style type="text/css">
	.chengji
	 {width:26px; height:22px; border:1px solid #F00; margin-right:-1px; float: left;  }
	
	</style>
</head>
<body>
<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/course/paike/import_select" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br />
	
			
			<input type="radio" name="currTerm" value="1" checked="checked"> 第一学期
			<input type="radio" name="currTerm" value="2"> 第二学期
			
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/course/paike/template">下载模板</a>
		</form>
	</div>
	<div class="breadcrumb form-search">
		
		<ul class="ul-form">
			
			<li class="btns"><input
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" /></li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<sys:message content="${message}"/>
</body>
</html>