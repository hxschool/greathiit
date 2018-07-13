<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>哈尔滨信息工程学院录取查询系统</title>

<link rel="stylesheet"
	href="${ctxStatic}/zhaosheng/bootstrap.min.css">
<link rel="stylesheet"
	href="${ctxStatic}/zhaosheng/font-awesome.min.css">
<link rel="stylesheet" href="${ctxStatic}/zhaosheng/font_cbmay4ubfls1yvi.css">
<script
	src="${ctxStatic}/zhaosheng/jquery-1.11.3.min.js"></script>
<script
	src="${ctxStatic}/zhaosheng/bootstrap.min.js"></script>
<!-- 加载Scripts -->
<link
	href='${ctxStatic}/zhaosheng/bootstrapValidator.min.css'
	rel='stylesheet' type='text/css'>
<script
	src='${ctxStatic}/zhaosheng/bootstrapValidator.min.js'></script>
<script src='${ctxStatic}/city/jquery.cityselect.js'></script>

<link rel='stylesheet'
	href='${ctxStatic}/zhaosheng/style-jihua_0d25696.css'
	type='text/css' />
<script
	src='${ctxStatic}/zhaosheng/jihua_b3d1b39.js'
	type='text/javascript'></script>
<link
	href='${ctxStatic}/zhaosheng/front_82277c3.css'
	rel='stylesheet' type='text/css'>
<link
	href='${ctxStatic}/zhaosheng/common_7039432.css'
	rel='stylesheet' type='text/css'>
<script src='${ctxStatic}/zhaosheng/main_2c6fa1b.js'></script>
<!-- 加载Scripts结束 -->
<style type="text/css">
.duilian{top:260px;position:absolute; width:120px; overflow:hidden; display:none;}
.duilian_left{ left:6px;}
.duilian_right{right:6px;}
.duilian_con{border:#CCC solid 1px; width:120px; height:220px; overflow:hidden;}
.duilian_close{ width:100%; height:24px; line-height:24px; text-align:center; display:block; font-size:13px; color:#555555; text-decoration:none;}
</style>
</head>

<body class=" layout-home">


<!--下面是对联广告的html代码结构-->
<div class="duilian duilian_left">
 <div class="duilian_con"><a href="http://www.hxci.com.cn/zy/" target="_blank"><img src="http://login.greathiit.com/images/jz.jpg"></a></div>
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
											id="bmform">哈尔滨信息工程学院录取查询系统</div>
										<div class="panel-body"
											style="border-bottom: 1px solid #337AB7;">
											<p class="text-s16" style="line-height: 26px;">
												<strong>报名条件</strong> <br>

												符合《关于做好2018年普通高等学校招生报名工作的通知》文件中有关条件的考生，可参加我院高职（专科）单独招生报名。<br>

												<strong>报名时间、报名方式</strong> <br>
												1、报名时间：预计报名时间2017年10月28日-11月15日（具体时间以省招考办公布时间为准）。<br>

												2、报名及填报专业志愿方式：<br>

												(1)报名方法：考生须持《户口簿》和第二代《居民身份证》到户籍或学籍所在地的县（市、区）招考办报名。如考生已参加了高考报名，现需兼报我院高职单独招生，只需在考生标记处选择院校代码。我院考生标记代码：兼报高考为18，只报单招为58。<br>

												(2)报名费：按照有关规定收取150元/人，由考生报名所在地招考办收取。<br>

												(3)填报专业志愿：已报名的考生须在2017年11月15日前登录我院网站http://www.hxci.com.cn，进入2018年单独招生志愿填报系统，阅读单独招生专业志愿填报的有关说明，按提示填报专业志愿。我院根据考生所填报专业志愿安排测试内容，考生信息一定要准确无误。<br>

												(4)考生本人须在考试前（具体时间关注我院招生网站）到我院现场确认所填报专业志愿，凭填报专业志愿时返回的报名顺序号、第二代《居民身份证》领取准考证。<br>
												<a href="http://www.hxci.com.cn/zy/" class="btn btn-info btn-sm ">查看招生简章</a> <a href="skip_Jieguo" class="btn btn-success btn-sm">查看报考结果</a>
											</p>
										</div>
										<div class="panel-body">
											<div class="clearfix"></div>
											<form id="form" method="post" class="form-horizontal"
												action="zhaosheng">
												<fieldset>
													<input type="hidden" class="hc_form_cat" id="hc_form_cat"
														name="hc_form_cat" value="补录申请">

													<div class="form-group text-s12 " id="fg_hc_form_xm">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_xm"> <b class="text-red">*</b> 姓名:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-user"></i></span> <input type="text"
																class="form-control hc_form_xm" name="hc_form_xm"
																id="hc_form_xm" placeholder="填写你的姓名" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_xm"></p>
													</div>
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_xb"> <b class="text-red">*</b> 性别:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<label class="radio-inline"> <input type="radio"
																id="hc_form_xb_0" name="hc_form_xb" value="男"
																checked='checked'> 男
															</label> <label class="radio-inline"> <input type="radio"
																id="hc_form_xb_1" name="hc_form_xb" value="女"> 女
															</label>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_xb"></p>
													</div>
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
													<div class="form-group text-s12 " id="fg_hc_form_zy1">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zy1"> <b class="text-red">*</b>
															意向专业1:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-heart"></i></span> <input type="text"
																class="form-control hc_form_zy1" name="hc_form_zy1"
																id="hc_form_zy1" placeholder="请输入您想就读的专业" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zy1"></p>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_zy2">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zy2"> 意向专业2: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-heart"></i></span> <input type="text"
																class="form-control hc_form_zy2" name="hc_form_zy2"
																id="hc_form_zy2" placeholder="请输入您想就读的专业" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zy2"></p>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_zy3">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zy3"> 意向专业3: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-heart"></i></span> <input type="text"
																class="form-control hc_form_zy3" name="hc_form_zy3"
																id="hc_form_zy3" placeholder="请输入您想就读的专业" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zy3"></p>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_szzx">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_szzx"> 所在中学: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text" class="form-control hc_form_szzx"
																name="hc_form_szzx" id="hc_form_szzx"
																placeholder="你所读的中学名称" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_szzx"></p>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_zxdz">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zxdz"> 中学地址: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text" class="form-control hc_form_zxdz"
																name="hc_form_zxdz" id="hc_form_zxdz"
																placeholder="你所读的中学地址" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zxdz"></p>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_fzrxm">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_fzrxm"> 班主任或学校负责人姓名: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text" class="form-control hc_form_fzrxm"
																name="hc_form_fzrxm" id="hc_form_fzrxm"
																placeholder="班主任或学校负责人姓名" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_fzrxm"></p>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_fzrdh">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_fzrdh"> 负责人电话: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text" class="form-control hc_form_fzrdh"
																name="hc_form_fzrdh" id="hc_form_fzrdh"
																placeholder="负责人电话" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_fzrdh"></p>
													</div>
													<hr>
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs">
															<b class="text-red">*</b> 希望就读地区:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12"
															id="jddq">
															<select class="form-control prov hc_form_add1"
																id="hc_form_add1" name="hc_form_add1"
																style="width: 30%;"></select> <select
																class="form-control city hc_form_add2" id="hc_form_add2"
																name="hc_form_add2" style="width: 30%;"></select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3" id="error-jddq"></p>
														<script>
															$("#jddq").citySelect();
														</script>
													</div>
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_kuasheng"> 外地就读: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<label class="radio-inline"> <input type="radio"
																id="hc_form_kuasheng_0" name="hc_form_kuasheng"
																value="不考虑" checked='checked'> 不考虑
															</label> <label class="radio-inline"> <input type="radio"
																id="hc_form_kuasheng_1" name="hc_form_kuasheng"
																value="本省其他城市"> 本省其他城市
															</label> <label class="radio-inline"> <input type="radio"
																id="hc_form_kuasheng_2" name="hc_form_kuasheng"
																value="外省"> 外省
															</label> <label class="radio-inline"> <input type="radio"
																id="hc_form_kuasheng_3" name="hc_form_kuasheng"
																value="都可以"> 都可以
															</label>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_kuasheng"></p>
													</div>
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_baojiuye"> 包就业分配: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<label class="radio-inline"> <input type="radio"
																id="hc_form_baojiuye_0" name="hc_form_baojiuye"
																value="希望包分配" checked='checked'> 希望包分配
															</label> <label class="radio-inline"> <input type="radio"
																id="hc_form_baojiuye_1" name="hc_form_baojiuye"
																value="不希望"> 不希望
															</label>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_baojiuye"></p>
													</div>
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_yuexin"> 期待月薪: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-money"></i></span> <select
																class="form-control margin-r-5 hc_form_yuexin"
																id="hc_form_yuexin" name="hc_form_yuexin">
																<option value='' selected='selected'>= 期待月薪 =</option>
																<option value='2000~3000元'>2000~3000元</option>
																<option value='3000~4000元'>3000~4000元</option>
																<option value='4000~5000元'>4000~5000元</option>
																<option value='5000~6000元'>5000~6000元</option>
																<option value='6000~8000元'>6000~8000元</option>
																<option value='8000元以上'>8000元以上</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_yuexin"></p>
													</div>
													<hr>
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_xslx"> <b class="text-red">*</b>
															学生类型:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-mortar-board"></i></span> <select
																class="form-control margin-r-5 hc_form_xslx"
																id="hc_form_xslx" name="hc_form_xslx">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value='高中应届'>高中应届</option>
																<option value='高中往届'>高中往届</option>
																<option value='中专应届'>中专应届</option>
																<option value='中专往届'>中专往届</option>
																<option value='其他'>其他</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_xslx"></p>
													</div>
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_cj"> <b class="text-red">*</b> 成绩:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-bar-chart"></i></span> <select
																class="form-control margin-r-5 hc_form_cj"
																id="hc_form_cj" name="hc_form_cj">
																<option value='' selected='selected'>=预计高考成绩=</option>
																<option value='200分以下'>200分以下</option>
																<option value='200~300分'>200~300分</option>
																<option value='300~400分'>300~400分</option>
																<option value='400~500分'>400~500分</option>
																<option value='500~600分'>500~600分</option>
																<option value='600分以上'>600分以上</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_cj"></p>
													</div>
													<div class="form-group text-s12">

														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs">
															<b class="text-red">*</b> 所在地区:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12"
															id="sfdq">
															<select class="form-control prov hc_form_province"
																id="hc_form_province" name="hc_form_province"
																style="width: 30%;"></select> <select
																class="form-control city hc_form_city" id="hc_form_city"
																name="hc_form_city" style="width: 30%;"></select> <select
																class="form-control dist hc_form_area" id="hc_form_area"
																name="hc_form_area" style="width: 30%;"></select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3" id="error-sfdq"></p>
														<script>
															$("#sfdq").citySelect();
														</script>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_dz">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_dz"> <b class="text-red">*</b> 通讯地址:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-home"></i></span> <input type="text"
																class="form-control hc_form_dz" name="hc_form_dz"
																id="hc_form_dz" placeholder="用于接收通知书，请具体到门号" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_dz"></p>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_sj">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_sj"> <b class="text-red">*</b> 联系方式:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-phone"></i></span> <input type="text"
																class="form-control hc_form_sj" name="hc_form_sj"
																id="hc_form_sj" placeholder="通知书寄到，电话联系" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_sj"></p>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_bysj">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_bysj"> 备用联系方式: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-phone"></i></span> <input type="text"
																class="form-control hc_form_bysj" name="hc_form_bysj"
																id="hc_form_bysj" placeholder="输入你的备用联系方式" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_bysj"></p>
													</div>
													<div class="form-group text-s12 " id="fg_hc_form_qq">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_qq"> QQ: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-qq"></i></span> <input type="text"
																class="form-control hc_form_qq" name="hc_form_qq"
																id="hc_form_qq" placeholder="输入你的QQ号码" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_qq"></p>
													</div>
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_bz"> 备注: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<textarea class="form-control hc_form_bz" rows="5"
																name="hc_form_bz" id="hc_form_bz"
																placeholder="详细介绍你的情况，更有利于我们为你提供帮助。"
																style="height: auto;"></textarea>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_bz"></p>
													</div>
													<div class="form-group text-s12">
														<div class="text-center">
															<input type="submit" class="btn btn-primary"
																name='bm_submit' value="提交申请表">
																
														</div>
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
	</div>
<script type="text/javascript">
$(document).ready(function(){
 var duilian = $("div.duilian");
 var duilian_close = $("a.duilian_close");
 var screen_w = screen.width;
 if(screen_w>1024){duilian.show();}
 $(window).scroll(function(){
  var scrollTop = $(window).scrollTop();
  duilian.stop().animate({top:scrollTop+260});
 });
 duilian_close.click(function(){
  $(this).parent().hide();
  return false;
 });
});
</script>
</body>
</html>
