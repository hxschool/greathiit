<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选课课程导入</title>
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
	<style type="text/css">
	.chengji
	 {width:26px; height:22px; border:1px solid #F00; margin-right:-1px; float: left;  }
	
	</style>
</head>
<body>

<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/course/select/import_select" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br />

			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="button button-primary button-rounded button-small"
				type="submit" value="   导    入   " /> <a class="button button-action button-rounded button-small"
				href="${pageContext.request.contextPath}/resources/course/import_select_download.xlsx">下载模板</a>
		</form>
	</div>
	<div class="breadcrumb form-search">
		
		<ul class="ul-form">
			
			<li class="btns"> <input
				id="btnImport" class="button button-primary button-rounded button-small" type="button" value="导入选课" />
				
				<a
				href="${pageContext.request.contextPath}/resources/course/import_select_download.xlsx" class="button button-action button-rounded button-small">下载模板</a>
				</li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<sys:message content="${message}"/>
</body>
</html>