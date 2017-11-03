<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>图书查询</title>
<meta name="decorator" content="default" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
	<!-- 	<ul class="nav nav-tabs"> -->
	<%-- 		<li class="active"><a href="${ctx}/sys/log/">日志列表</a></li> --%>
	<!-- 	</ul> -->
	<form:form id="searchForm" action="${ctx}/sys/book/look" method="post"
		class="breadcrumb form-search">

		<div>
			<ul class="ul-form">
				<li><label>姓名：</label><input id="name" name="name"
					type="text" maxlength="50" class="input-small" value="${name }" /></li>
				
				<li><label>编号：</label> <input id="studentNumber"
					name="studentNumber" type="text" maxlength="50" class="input-small"
					value="${studentNumber }" />&nbsp;&nbsp;&nbsp;</li>
				
				<li>&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="查询" /></li>
			</ul>
		</div>
	</form:form>
	<sys:message content="${message}" />




	<c:forEach items="${userinfos}" var="userinfo">
		<div class="form-horizontal">
			<c:forEach items="${userinfo}" var="mymap">
				<div class="control-group">
					<label class="control-label"><c:out value="${mymap.key}" />
						:</label>
					<div class="controls">
						<input name="no" maxlength="50" class="required"
							value="${mymap.value}" />
					</div>
				</div>
			</c:forEach>

		</div>
	</c:forEach>



	<script>
		$(function() {
			
			
			 $("#name").autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			          url: "reader",
			          data: {
			        	  name: $("#name").val()
			          },
			          success: function( data ) {
			            response( $.map( data, function( item ) {
			              return {
			                label: item,
			                value: item
			              }
			            }));
			          }
			        });
			      }});
			 $("#studentNumber").autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			          url: "reader",
			          data: {
			        	  studentNumber: $("#studentNumber").val()
			          },
			          success: function( data ) {
			            response( $.map( data, function( item ) {
			              return {
			                label: item,
			                value: item
			              }
			            }));
			          }
			        });
			      }});
		});
	</script>
	
	
</body>
</html>