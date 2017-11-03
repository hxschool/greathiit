<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	
</head>
<body>
	<ul class="nav nav-tabs">
		
		<li class="active"><a href="#">借阅信息</a></li>
	</ul><br/>
	<div class="form-horizontal">

		<c:forEach items="${userinfos}" var="userinfo">
		<c:forEach items="${userinfo}" var="mymap" > 
		<div class="control-group">
			<label class="control-label"><c:out value="${mymap.key}" /> :</label>
			<div class="controls">
				<input name="no"  maxlength="50" class="required" value="${mymap.value}" />
			</div>
		</div>
		</c:forEach>
		</c:forEach>
	</div>
</body>
</html>