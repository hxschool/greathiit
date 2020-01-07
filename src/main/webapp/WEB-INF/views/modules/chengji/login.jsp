<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<title>哈尔滨信息工程学院-成绩查询系统</title>
<!-- BOOTSTRAP CORE STYLE  -->
<link href="${ctxStatic}/chengji/assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="${ctxStatic}/chengji/assets/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="${ctxStatic}/chengji/assets/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

</head>
<body>
	<div class="navbar navbar-inverse set-radius-zero">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html"> <img
					src="${ctxStatic}/chengji/assets/img/logo.png" />
				</a>

			</div>

			<div class="right-div">
				
			</div>
		</div>
	</div>
	<!-- LOGO HEADER END-->
	<section class="menu-section">
		<div class="container">
			<div class="row ">
				<div class="col-md-12">
					<div class="navbar-collapse collapse ">
						<ul id="menu-top" class="nav navbar-nav navbar-right">
							<li><a href="index.html" class="menu-top-active">首页</a></li>
							<li><a href="http://leave.greathiit.com/">退出</a></li>
							<!-- <li><a href="http://www.hxci.com.cn/">哈尔滨信息工程学院</a></li>
							<li><a href="http://ar.greathiit.com/">数字校园</a></li>
							<li><a href="http://www.greathiit.com/a">信息化中心</a></li>
							<li><a href="#" class="dropdown-toggle" id="ddlmenuItem"
								data-toggle="dropdown">UI ELEMENTS <i
									class="fa fa-angle-down"></i></a>
								<ul class="dropdown-menu" role="menu"
									aria-labelledby="ddlmenuItem">
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="ui.html">UI ELEMENTS</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">EXAMPLE LINK</a></li>
								</ul></li><li><a href="table.html">TABLES</a></li>
							<li><a href="http://paper.greathiit.com/">论文管理</a></li>
							<li><a href="http://leave.greathiit.com/">自助离校</a></li>
							 -->

						</ul>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- MENU SECTION END-->
	<div class="content-wrapper">
		<div class="container">
			<div class="row pad-botm">
				<div class="col-md-12">
					<h4 class="header-line">哈尔滨信息工程学院-成绩查询系统</h4>
				</div>

			</div>


			<div class="row pad-botm">

				<div class="col-md-8 col-sm-8 col-xs-12">
					<div id="carousel-example" class="carousel slide slide-bdr"
						data-ride="carousel">

						<div class="carousel-inner">
							<div class="item active">

								<img src="${ctxStatic}/chengji/assets/img/1.jpg" alt="" />

							</div>
							<div class="item">
								<img src="${ctxStatic}/chengji/assets/img/2.jpg" alt="" />

							</div>
							<div class="item">
								<img src="${ctxStatic}/chengji/assets/img/3.jpg" alt="" />

							</div>
						</div>
						<!--INDICATORS-->
						<ol class="carousel-indicators">
							<li data-target="#carousel-example" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example" data-slide-to="1"></li>
							<li data-target="#carousel-example" data-slide-to="2"></li>
						</ol>
						<!--PREVIUS-NEXT BUTTONS-->
						<a class="left carousel-control" href="#carousel-example"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left"></span>
						</a> <a class="right carousel-control" href="#carousel-example"
							data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</div>
				</div>

				<div class="col-md-4 col-sm-4 col-xs-12">
				<form role="form" action="${pageContext.request.contextPath}/chengji/view" method="post">
					<div class="panel panel-primary ">
						<div class="panel-heading">成绩查询</div>
						<div class="panel-body chat-widget-main">
							
								<div class="form-group">
									<label>姓名</label> <input class="form-control" name="username"
										type="text" />
									
								</div>
								<div class="form-group">
									<label>密码</label> <input class="form-control"
										type="password" name="idcard" />
									
								</div>

								<button type="submit" class="btn btn-info">点击查询
								</button>
								<p class="help-block">
								<c:if test="${message==null}">&nbsp;</c:if>
								${message}</p>
							
						</div>

					</div>
					</form>
				</div>

			</div>
		

			<!-- <div class="row">
				<div class="col-md-4 col-sm-4 col-xs-12">
					<div class="panel panel-default">
						<div class="panel-heading">优秀教师</div>
						<div class="panel-body text-center recent-users-sec">
系统升级中,敬请期待 
							<img class="img-thumbnail" src="assets/img/user.gif" /> <img
								class="img-thumbnail" src="assets/img/user2.png" /> <img
								class="img-thumbnail" src="assets/img/user.gif" /> <img
								class="img-thumbnail" src="assets/img/user2.png" /> <img
								class="img-thumbnail" src="assets/img/user.gif" /> <img
								class="img-thumbnail" src="assets/img/user2.png" /> <img
								class="img-thumbnail" src="assets/img/user.gif" /> <img
								class="img-thumbnail" src="assets/img/user2.png" /> <img
								class="img-thumbnail" src="assets/img/user.gif" />
						</div>
					</div>
				</div>
				<div class="col-md-8 col-sm-8 col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">最新成绩</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>姓名</th>
											<th>学院/专业</th>
											<th>成绩</th>
											<th>学分</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="5">系统升级中,敬请期待 </td>
										</tr>
										 <tr>
											<td>1</td>
											<td>Mark</td>
											<td>Otto</td>
											<td>@mdo</td>
											<td>100090</td>
										</tr> 
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

			</div> -->


		</div>
	</div>
	<!-- CONTENT-WRAPPER SECTION END-->
	<section class="footer-section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					Copyright @ 2019-2025 Harbin Institute Of Information Technology All Rights Reservd 黑ICP备05007064号
				</div>

			</div>
		</div>
	</section>
	<!-- FOOTER SECTION END-->
	<!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
	<!-- CORE JQUERY  -->
	<script src="${ctxStatic}/chengji/assets/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS  -->
	<script src="${ctxStatic}/chengji/assets/js/bootstrap.js"></script>
	<!-- CUSTOM SCRIPTS  -->
	<script src="${ctxStatic}/chengji/assets/js/custom.js"></script>

</body>
</html>
