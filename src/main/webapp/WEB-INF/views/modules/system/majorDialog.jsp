<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>选择专业</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
			    $('#greathiit_area_edu').cxSelect({ 
					  url: '${ctx}/sys/office/treeClassLink',
					  selects: ['department', 'specialty'], 
					  jsonName: 'name',
					  jsonValue: 'value',
					  jsonSub: 'sub'
					});
})
			    </script>
</head>
<body>
	
	<br />
	<form
		action="" method="post" class="form-horizontal">
		<div id="greathiit_area_edu">

	<div class="control-group">
		<label class="control-label">所属学院:</label>
		<div class="controls">
			<select class="department input-large" name="department"><option>请选择</option></select>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">所属专业:</label>
		<div class="controls">
			<select id="specialty" class="specialty input-large" name="specialty"><option>请选择</option></select>
		</div>
	</div>

</div>
	</form>
	<script type="text/javascript">
		function callbackdata(){
			return $("#specialty").val();
		}
	</script>
</body>
</html>