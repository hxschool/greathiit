<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统招数据管理</title>
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
	</script>
	
</head>
<body>
<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/student/student/upload" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> 
		</form>
	</div>
	<div class="container-fluid">
	<div class="breadcrumb form-search">
		
		<ul class="ul-form">
			
			<li class="btns">
			
			
			<input
				id="btnImport" class="btn btn-primary" type="button" value="像片导入" /></li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<sys:message content="${message}"/>
		
		<fieldset>
			<legend>使用说明</legend>
			请使用zip压缩进行像片压缩.相片已身份证号命名.
		</fieldset>
		</div>
</body>
</html>