<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>哈尔滨信息工程-单独招生专业志愿填报系统说明</title>

<link rel="stylesheet" href="${ctxStatic}/zhaosheng/bootstrap.min.css">
<link rel="stylesheet"
	href="${ctxStatic}/zhaosheng/font-awesome.min.css">
<link rel="stylesheet"
	href="${ctxStatic}/zhaosheng/font_cbmay4ubfls1yvi.css">
<script src="${ctxStatic}/zhaosheng/jquery-1.11.3.min.js"></script>
<script src="${ctxStatic}/zhaosheng/bootstrap.min.js"></script>
<!-- 加载Scripts -->
<link href='${ctxStatic}/zhaosheng/bootstrapValidator.min.css'
	rel='stylesheet' type='text/css'>
<script src='${ctxStatic}/zhaosheng/bootstrapValidator.min.js'></script>
<script src='${ctxStatic}/city/jquery.cityselect.js'></script>

<link rel='stylesheet'
	href='${ctxStatic}/zhaosheng/style-jihua_0d25696.css' type='text/css' />
<script src='${ctxStatic}/zhaosheng/jihua_b3d1b39.js'
	type='text/javascript'></script>
<link href='${ctxStatic}/zhaosheng/front_82277c3.css' rel='stylesheet'
	type='text/css'>
<link href='${ctxStatic}/zhaosheng/common_7039432.css' rel='stylesheet'
	type='text/css'>
<script src='${ctxStatic}/zhaosheng/main_2c6fa1b.js'></script>
<script src="${ctxStatic}/layer/layer.js"></script>
<!-- 加载Scripts结束 -->
<style type="text/css">
.duilian {
	top: 260px;
	position: absolute;
	width: 120px;
	overflow: hidden;
	display: none;
}

.duilian_left {
	left: 6px;
}

.duilian_right {
	right: 6px;
}

.duilian_con {
	border: #CCC solid 1px;
	width: 120px;
	height: 220px;
	overflow: hidden;
}

.duilian_close {
	width: 100%;
	height: 24px;
	line-height: 24px;
	text-align: center;
	display: block;
	font-size: 13px;
	color: #555555;
	text-decoration: none;
}
</style>
</head>


<c:if test="${config.status==1}">  
<script>
alert("${config.tip}");
</script>
</c:if>
<body class=" layout-home">


	<!--下面是对联广告的html代码结构-->
	<div class="duilian duilian_left">
		<div class="duilian_con">
			<a href="http://www.hxci.com.cn/zy/" target="_blank"><img
				src="http://login.greathiit.com/images/jz.jpg"></a>
		</div>
		<a href="#" class="duilian_close">X关闭</a>
	</div>



	<div class="main-container container no-sidebar">
		<div class="main-content">

			<div class="page-content">
				<div class="row-fluid">
					<!--PAGE CONTENT BEGINS HERE-->
					<div class="content">


						<div class="widget-box">
							<div class="margin-top-10">
								<div class="col-sm-1"></div>
								<div class="col-sm-10 col-xs-12">
									<div class="panel panel-primary text-wr">
										<div class="panel-heading text-center text-s28 text-bold"
											id="bmform">单独招生专业志愿填报系统说明</div>

				<div class="panel-body"
											style="border-bottom: 1px solid #337AB7;">
											
		${config.description}
		
		<p class="text-s16">
		<a href="http://www.hxci.com.cn/zy/"
												class="btn btn-info btn-sm ">查看招生简章</a> <!-- <a
												href="skip_Jieguo" class="btn btn-success btn-sm">查看报考结果</a> -->
											</p>
		</div>

											

										</div>
										<div class="panel-body">
											<div class="clearfix"></div>
											<form id="form" method="post" class="form-horizontal"
												action="skip_ZhaoSheng" >
												<fieldset>
													<input type="hidden" class="hc_form_cat" id="hc_form_cat"
														name="hc_form_cat" value="补录申请">



													<div class="form-group text-s12 " id="fg_hc_form_sfzh">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_sfzh"> <b class="text-red">*</b>
															身份证号:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-credit-card"></i></span> <input type="text"
																class="form-control hc_form_sfzh" name="hc_form_sfzh"
																id="hc_form_sfzh" placeholder="输入18位身份证号" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_sfzh"></p>
													</div>
													<div class="form-group text-s12">
														<div class="text-center">

															
																<c:if test="${config.sw==0}">  
																<input type="checkbox" class="pc" name="agreebbrule" id="agreebbrule" >
																<label for="agreebbrule">同意<!--  <a
																href="javascript:;" style="color:red" onclick="showBBRule()"> 特别提示 </a> --></label> 
																
																	<input
																	type="submit" class="btn btn-primary btn-sm"
																	name='bm_submit' value="提交申请表">
																</c:if>
																
																
																<c:if test="${config.sw==1}">  
																	<button class="btn btn-danger"
																	>报考系统已关闭</button>
																</c:if>
																
														</div><p class="col-sm-offset-3 col-xs-offset-3"
															id="error-agreebbrule"></p>
													</div>
												</fieldset>
											</form>

										</div>
									</div>
								</div>
								<div class="col-sm-1"></div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<!--PAGE CONTENT ENDS HERE-->
				</div>
				<!--/row-->
			</div>
			<!--/#page-content-->

		</div>
		<!--/#main-content-->
	
	<script type="text/javascript">
		
		$(document).ready(function() {
			var duilian = $("div.duilian");
			var duilian_close = $("a.duilian_close");
			var screen_w = screen.width;
			if (screen_w > 1024) {
				duilian.show();
			}
			$(window).scroll(function() {
				var scrollTop = $(window).scrollTop();
				duilian.stop().animate({
					top : scrollTop + 260
				});
			});
			duilian_close.click(function() {
				$(this).parent().hide();
				return false;
			});
		});
	</script>

	
</body>
</html>
