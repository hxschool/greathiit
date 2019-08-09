<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>选择班级</title>
<meta name="decorator" content="default" />

</head>
<body>
	
	<br />
	<form
		action="" method="post" class="form-horizontal">
		<sys:edu></sys:edu>
	</form>
	<script type="text/javascript">
		function callbackdata(){
			return $("#clazz").val();
		}
	</script>
</body>
</html>