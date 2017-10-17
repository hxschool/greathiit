<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>2018年哈尔滨信息工程学院单招报名申请表</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="renderer" content="webkit" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon"
	href="${ctxStatic}/campus-account/images/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="${ctxStatic}/campus-account/images/apple-touch-icon-retina.png">

<script src="${ctxStatic}/campus-account/js/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<link
	href="${ctxStatic}/campus-account/css/bundle-bundle_bootstrap_head.css"
	type="text/css" rel="stylesheet" media="screen, projection" />

<link
	href="${ctxStatic}/campus-account/css/bundle-bundle_theme.WbAce.fonts_head.css"
	type="text/css" rel="stylesheet" media="screen, projection" />
<!--[if IE 7]><link href="${ctxStatic}/campus-account/css/font-awesome-ie7.css" type="text/css" rel="stylesheet" media="screen, projection" /><![endif]-->

<link
	href="${ctxStatic}/campus-account/css/bundle-bundle_ui.WbAce_head.css"
	type="text/css" rel="stylesheet" media="screen, projection" />

<link
	href="${ctxStatic}/campus-account/css/bundle-bundle_theme.WbAce_head.css"
	type="text/css" rel="stylesheet" media="screen, projection" />




<!--[if lte IE 8]><link href="${ctxStatic}/campus-account/css/ace-ie.css" type="text/css" rel="stylesheet" media="screen, projection" /><![endif]-->

</head>
<body class=" layout-home">
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a href="/" class="brand"> <small> <i class="icon-user"></i>
						哈尔滨信息工程学院
				</small>
				</a>
				<!--/.brand-->

				<ul class="nav ace-nav pull-right">
					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <img
							src="${ctxStatic}/campus-account/images/avatar.jpg"
							class="nav-user-photo" alt="Avatar" /> <span class="user-info">
								<small>您好</small> 访客
						</span> <i class="icon-caret-down"></i>
					</a></li>
				</ul>
			</div>
		</div>
	</div>


	<div class="main-container container no-sidebar">
		<div class="main-content">

			<div class="page-content">
				<div class="row-fluid">
					<!--PAGE CONTENT BEGINS HERE-->
					<div class="content">

						

						<form action="common"
							method="post" class="form-horizontal">
							

							<div class="widget-box">
								<div class="widget-header widget-header-large header-color-blue">
									<h4>2018年哈尔滨信息工程学院单招报名申请表</h4>
								</div>

								<div class="widget-body">
									<div class="widget-body-inner">
										<div class="widget-main">
											


											<hr>

											 <table border="0" width="950" cellspacing="0" cellpadding="10">
						                  <tr>
						                    <td width="100%" height="130" valign="bottom">
						                    <p align="center" style="margin-bottom: 20"><b><font color="#FF0000" style="font-size: 18px">恭喜您，提交成功！请等待管理员审核！严禁重复提交！</font></b>
						                   <a href="skip_Jieguo" class="btn btn-success btn-sm">查看报考结果</a>
						                    </p>
						                    <p align="center" style="margin-bottom: 20"><b><font style="font-size: 18px">&nbsp;</font></b></p>
						                    <p align="center" style="margin-bottom: 10">
											<font style="font-size: 18px" color="#008000"><b>&nbsp;</b></font></p> 
						                    </td>   
						                  </tr>     
						                 
						                
						                </table>  
										</div>
									</div>
								</div>
							</div>


						</form>

						<script language="JavaScript">
							$(function() {
								$('btn-exit').click(function() {
									window.close();
								});
							});
							function toNext() {
								var Sys = {}, s;
								(s = (navigator.userAgent.toLowerCase())
										.match(/msie ([\d.]+)/)) ? Sys.ie = s[1]
										: 0;
								if (Sys.ie && Number(Sys.ie) <= 7)
									$('form').submit();
							}
						</script>

					</div>
					<!--PAGE CONTENT ENDS HERE-->
				</div>
				<!--/row-->
			</div>
			<!--/#page-content-->

		</div>
		<!--/#main-content-->
	</div>
	<!--/.fluid-container#main-container-->

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-small btn-inverse"> <i
		class="icon-double-angle-up icon-only bigger-110"></i>
	</a>

	<script
		src="${ctxStatic}/campus-account/js/bundle-bundle_bootstrap_defer.js"
		type="text/javascript"></script>





	<!--[if IE 7]><script src="/campus-account/static/plugins/wb-ace-ui-0.2.3/js/elusive-icons-lte-ie7.js" type="text/javascript" ></script><![endif]-->
	<script
		src="${ctxStatic}/campus-account/js/bundle-bundle_ui.WbAce_defer.js"
		type="text/javascript"></script>


	<script
		src="${ctxStatic}/campus-account/js/bundle-bundle_theme.WbAce_defer.js"
		type="text/javascript"></script>



	<script src="${ctxStatic}/campus-account/js/application.js"
		type="text/javascript"></script>
</body>
</html>
