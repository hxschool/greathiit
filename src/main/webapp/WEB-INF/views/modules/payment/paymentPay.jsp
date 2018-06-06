<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统招数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("img").attr("src","${ctx}/payment/qrcode?k=${k}");
		});
		
	</script>
</head>
<body>
<img id="img"/>
	11111111111111111
</body>
</html>