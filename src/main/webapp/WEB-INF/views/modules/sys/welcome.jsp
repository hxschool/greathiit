<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />

<style>

	#con{
		width: 570px;
		height: 270px;
		margin: 0px auto;
		position: relative;
		top:150px;
	}
	.app{
		width:100px;
		margin:10px;
		float: left;
	}
</style>
</head>
<body>
	<div id="con">
	<sys:message content="${message}" />
	<c:forEach items="${list}" var="appconfig">
		<c:if test="${appconfig.status=='1' }">
			<div class="app">
			<p>
				<a href="${appconfig.url }" title="${appconfig.name }" target="_blank">${appconfig.name }</a>
				
			</p>
			<a href="${appconfig.url }" title="${appconfig.name }" target="_blank"><img src="${ctxStatic }/${appconfig.logo }"/></a>
			</div>
		</c:if>
	</c:forEach>
	</div>
</body>
</html>