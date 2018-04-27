<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctxStatic}/zhaosheng/bootstrap.min.css">
</head>
<body>

	<div class="main-container container no-sidebar">
		<div class="main-content">
			<div class="page-content">
				<div class="row-fluid">
					<!--PAGE CONTENT BEGINS HERE-->
					<div class="content">
						<div class="panel-body">
							<div class="clearfix"></div>
							<form class="form-horizontal" action="save" method="post">
								<fieldset>
									<div id="legend" class="">
										<legend class="">获取教师号</legend>
									</div>
									
										<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">姓名</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											
											<p class="help-block">${responseResult }</p>
										</div>
									</div>
									<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">教师号</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12" >
											
											<p class="help-block">${responseCode }</p>
										</div>
									</div>
						
									
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
