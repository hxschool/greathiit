<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="yingxin_default_${site.theme}" />
<meta name="description"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.description}" />
<meta name="keywords"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.keywords}" />


</head>
<body>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"
					style="background-color: #3a6fc9; color: #fff; text-align: center;">
					用户登录</div>
				<div class="modal-body">
					<div></div>
					<div>
						<div class="row">
							<div class="col-md-12">
								
								<form action="http://wangwei.com:8080/login?service=http://lab.wangwei.com:8080/a" name="form1" method="post" id="loginForm" onsubmit="return loginValidate();"   class="form-horizontal">
									<fieldset>
										<div id="legend" class="">
											<legend class="">
												<p style="font-size: 12px;">
													1、网上报到：到校前须完成网上报到；（<a href="javascript:void(0)"
														data-toggle="modal" data-target="#myShuomingModal">密码详见登录说明</a>）
												</p>

												<p style="font-size: 12px;">2、现场报到：请按录取通知书要求在指定时间内到校办理现场报到相关手续。</p>
											</legend>
										</div>
										<div class="row" style="margin-bottom: 10px;">
											<div class="col-lg-8 col-lg-offset-2">

												<div class="controls">
													<input placeholder="请输入身份证号码" name="username"
														class="form-control input-sm" type="text" id="username">

												</div>
											</div>
										</div>

										<div class="row" style="margin-bottom: 10px;">
											<div class="col-lg-8 col-lg-offset-2">

												<div class="controls">
													<input placeholder="请输入身份证后6位" name="password"
														class="form-control input-sm" type="password"
														id="password">
												</div>
											</div>
										</div>

										<div class="row" style="margin-bottom: 10px;">
											<div class="col-lg-8 col-lg-offset-2">



												<div class="controls">
													<button type="button" id="submit_login"
														class="btn btn-primary" style="background-color: #1678c2">点击登录</button>
														            <input type="hidden" name="execution" id="execution" value="" />
                <input type="hidden" name="login_from" value="" />  
                <input type="hidden" name="_eventId" value="submit" />  
												</div>

											</div>
										</div>
									</fieldset>
								</form>
							</div>
						</div>

					</div>

				</div>
				<div class="modal-footer"
					style="background-color: #f9fafb; text-align: center;">©
					Copyright 2017, D2C. All rights reserved.</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>


	<div class="modal fade" id="myShuomingModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">登录说明</h4>
				</div>
				<div class="modal-body">
					<img src="${ctxStatic}/yingxin2/images/shuoming.png" />
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<script>
	    $('#submit_login').click(function(){
			var xhr = $.getJSON("http://wangwei.com:8080/login?action=getlt&service=http://lab.wangwei.com:8080/a&callback=?",    
                    function(response) {
                        $("#execution").val(response.execution);
                        $('#loginForm').submit();
                    }); 
					xhr.fail(function(jqXHR, textStatus, ex) {
						alert('服务器繁忙,请稍后再试');
					});
		});  

	var loginValidate = function() {
		
      var msg;
      if ($.trim($('#username').val()).length == 0) {
         msg = "用户名不能为空。";
      } else if ($.trim($('#password').val()).length == 0) {
         msg = "密码不能为空。";
      }
      if (msg && msg.length > 0) {
		  alert(msg);
         $('#J_ErrorMsg').fadeOut().text(msg).fadeIn();
         return false;
      } 
   } 

$(document).ready(function(){
 
$('#myModal').modal({backdrop: 'static', keyboard: false});
});
</script>
</body>