<%@ page contentType="text/html;charset=UTF-8" %>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/zhaosheng/bootstrap.min.css">
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
							<form class="form-horizontal" action="actUser" method="post">
								<fieldset>
									<div id="legend" class="">
										<legend class="">获取教师号</legend>
									</div>


									<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">教师号</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											<input placeholder="请输入教师编号,如果没有请不填写" class="form-control"
												type="text" name="no" value="${responseResult}" readonly="readonly">
											<p class="help-block"></p>
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
