<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统招数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	</script>
</head>
<body>
<form id="form" action="${ctx }/payment/pay.html" method="post">
		<c:forEach items="${list}" var="paymentEntity" varStatus="i">
			<tr>

				<td>
				<input type="text" name="ids" value="${paymentEntity.id}"/>
					${paymentEntity.id}
				</td>
				
			</tr>
		</c:forEach>
		 <input type="submit" value="Submit" />
</form>
	
</body>
</html>