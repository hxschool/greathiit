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
	<form:form id="searchForm" action="${ctx}/sys/book/" method="post"
		class="breadcrumb form-search">

		<div>
			<ul class="ul-form">
				<li><label>输入书名：</label><input id="bookname" name="bookname"
					type="text" maxlength="50" class="input-small" value="${bookname }" /></li>
				
				<li><label>拼音查找：</label> <input id="sortbookname"
					name="sortbookname" type="text" maxlength="50" class="input-small"
					value="${sortbookname }" /></ &nbsp;&nbsp;&nbsp;</li>
				
				<li>&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="查询" /></li>
			</ul>
		</div>
	</form:form>
	<sys:message content="${message}" />

	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>书名</th>
				<th>册数</th>
				<th>可外借数</th>
				<th>已外借数</th>
				<th>预约数</th>
				<th>图象页数</th>
				<th>卷标</th>
				<th>上月外借册数</th>
				<th>本月外借册数</th>
				<th>去年外借册数</th>
				<th>今年外借册数</th>
				<th>累计外借册数</th>
		</thead>
		<tbody>
			<%
				request.setAttribute("strEnter", "\n");
				request.setAttribute("strTab", "\t");
			%>
			<c:forEach items="${booklist}" var="bookMap">
				<tr>
					<td>${bookMap['题名']}</td>
					<td>${bookMap['册数']}</td>
					<td>${bookMap['可外借数']}</td>
					<td>${bookMap['已外借数']}</td>
					<td>${bookMap['预约数']}</td>
					<td>${bookMap['图象页数']}</td>
					<td>${bookMap['卷标']}</td>

					<td>${bookMap['上月外借册数']}</td>
					<td>${bookMap['本月外借册数']}</td>
					<td>${bookMap['去年外借册数']}</td>
					<td>${bookMap['今年外借册数']}</td>
					<td>${bookMap['累计外借册数']}</td>

				</tr>

			</c:forEach>
		</tbody>
	</table>
	<script>
		$(function() {
			
			
			 $( "#sortbookname" ).autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			          url: "${ctx}/sys/book/sortname",
			          data: {
			        	  sortbookname: $("#sortbookname").val()
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