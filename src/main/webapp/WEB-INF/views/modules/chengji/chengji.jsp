<%@ page contentType="text/html;charset=UTF-8"%>
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
<link href="${ctxStatic}/chengji/assets/css/bootstrap.css"
	rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="${ctxStatic}/chengji/assets/css/font-awesome.css"
	rel="stylesheet" />
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

			<div class="right-div"></div>
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
							<li><a href="http://www.hxci.com.cn/">哈尔滨信息工程学院</a></li>
							<li><a href="http://ar.greathiit.com/">数字校园</a></li>
							<li><a href="http://www.greathiit.com/a">信息化中心</a></li>
							<!-- <li><a href="#" class="dropdown-toggle" id="ddlmenuItem"
								data-toggle="dropdown">UI ELEMENTS <i
									class="fa fa-angle-down"></i></a>
								<ul class="dropdown-menu" role="menu"
									aria-labelledby="ddlmenuItem">
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="ui.html">UI ELEMENTS</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">EXAMPLE LINK</a></li>
								</ul></li><li><a href="table.html">TABLES</a></li> -->
							<li><a href="http://paper.greathiit.com/">论文管理</a></li>
							<li><a href="http://leave.greathiit.com/">自助离校</a></li>

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



			<c:if test="${chengjis!=null }">

				<c:if test="${fn:length(chengjis)==1}">
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading">&nbsp;</div>
								<div class="panel-body">
									<p id="chengji_default">
										<img src="../userinfo/${chengjis[0]}"  />
									</p>
								</div>
								<div class="panel-footer">
									<a href="javascript:void(0)" onclick="printImg($('#chengji_default'))"  class="btn btn-primary"><span
										class="glyphicon glyphicon-print"> </span> 打印</a> <a
										href="../userinfo/${chengjis[0]}"
										download="chengji_default.jpg" class="btn btn-success"><span
										class="glyphicon glyphicon-download-alt"> </span> 下载</a>
								</div>
							</div>
						</div>
						
					</div>
				</c:if>
				<c:if test="${fn:length(chengjis)==2}">
					<div class="row">
						<div class="col-md-6 col-sm-6">
							<div class="panel panel-default">
								<div class="panel-heading">&nbsp;</div>
								<div class="panel-body">
									<p id="chengji_default">
										<img src="../userinfo/${chengjis[0]}" width="525px" />
									</p>
								</div>
								<div class="panel-footer">
									<a href="javascript:void(0)" onclick="printImg($('#chengji_default'))"  class="btn btn-primary"><span
										class="glyphicon glyphicon-print"> </span> 打印</a> <a
										href="../userinfo/${chengjis[0]}"
										download="chengji_default.jpg" class="btn btn-success"><span
										class="glyphicon glyphicon-download-alt"> </span> 下载</a>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6">
							<div class="panel panel-default">
								<div class="panel-heading">&nbsp;</div>
								<div class="panel-body">
									<p id="chengji_other">
										<img src="../userinfo/${chengjis[1]}" width="525px" />
									</p>
								</div>
								<div class="panel-footer">
									<a href="javascript:void(0)" onclick="printImg($('#chengji_other'))"  class="btn btn-primary"><span
										class="glyphicon glyphicon-print"> </span> 打印</a> <a
										href="../userinfo/${chengjis[1]}"
										download="chengji_other.jpg" class="btn btn-success"><span
										class="glyphicon glyphicon-download-alt"> </span> 下载</a>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</c:if>
			<div class="row">
				<div class="col-md-12">
					<!--   Kitchen Sink -->
					<div class="panel panel-default">
						<div class="panel-heading">成绩详情</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											
											<th>课程名称</th>
											<th>课堂表现成绩</th>
											<th>期末成绩</th>
											<th>综合成绩</th>
											<th>学分</th>
											<th>绩点</th>
										</tr>
									</thead>
									<tbody>
										
										<c:forEach items="${scs}" var="entry">
											<tr class="info">
											<td colspan="6"><strong>${entry.key}</strong></td>
											</tr>
											
											<c:forEach items="${entry.value}" var="isc">
											<tr>
											<td>${isc.course.cursName}</td>
											<td>${isc.classEvaValue }</td>
											<td>${isc.finEvaValue }</td>
											<td>${isc.evaValue }</td>
											<td>${isc.credit }</td>
											<td>${isc.point }</td>
											</tr>
											</c:forEach>
											
										</c:forEach>
										

									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- End  Kitchen Sink -->
				</div>
			</div>


		</div>
	</div>
	<!-- CONTENT-WRAPPER SECTION END-->
	<section class="footer-section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">Copyright @ 2019-2025 Harbin Institute
					Of Information Technology All Rights Reservd 黑ICP备05007064号</div>

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
<script type="text/javascript">
function printImg(object){
	var printHtml = object.html();
	newWindow = window.open("",'newwindow');
	newWindow.document.body.innerHTML = printHtml;
	newWindow.print();
}
</script>
</body>
</html>
