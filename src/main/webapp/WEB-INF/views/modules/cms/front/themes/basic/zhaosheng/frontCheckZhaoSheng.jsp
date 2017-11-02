<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>2018年哈尔滨信息工程学院单招报名申请表</title>

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

<link rel='stylesheet'
	href='${ctxStatic}/zhaosheng/style-jihua_0d25696.css' type='text/css' />
<script src='${ctxStatic}/zhaosheng/jihua_b3d1b39.js'
	type='text/javascript'></script>
<link href='${ctxStatic}/zhaosheng/front_82277c3.css' rel='stylesheet'
	type='text/css'>
<link href='${ctxStatic}/zhaosheng/common_7039432.css' rel='stylesheet'
	type='text/css'>
<link
	href='${ctxStatic}/datetimepicker/css/bootstrap-datetimepicker.min.css'
	rel='stylesheet' type='text/css'>
<script src='${ctxStatic}/zhaosheng/main_2c6fa1b.js'></script>
<script
	src='${ctxStatic}/datetimepicker/js/bootstrap-datetimepicker.min.js'></script>
	<script type="text/javascript" src="${ctxStatic}/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

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

<body class=" layout-home">
<c:if test="${empty param.hc_form_sfzh}">  
<script>
alert("参数异常即将关闭当前窗口");
window.close();
</script>

</c:if>


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
											id="bmform">2018年哈尔滨信息工程学院单招报名申请表</div>
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
												<a href="http://www.hxci.com.cn/zy/"
													class="btn btn-info btn-sm ">查看招生简章</a> <a
													href="skip_Jieguo" class="btn btn-success btn-sm">查看报考结果</a>
											</p>
										</div>
										<div class="panel-body">
											<div class="clearfix"></div>
											<form id="form" method="post" class="form-horizontal"
												action="zhaosheng">
												<fieldset>


													<button class="btn btn-default btn-sm">考生基本信息</button>
													
													
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_kl"> <b class="text-red">*</b>
															城市:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<SELECT name="hc_form_province" id="to_cn" style="width: 30%;"
																onchange="set_city(this, document.getElementById('city')); WYL();"
																class="form-control margin-r-5 hc_form_bylb">
																<option value="">请选择</option>

																<option value=哈尔滨市>哈尔滨市</option>

																<option value=齐齐哈尔市>齐齐哈尔市</option>

																<option value=鸡西市>鸡西市</option>

																<option value=鹤岗市>鹤岗市</option>

																<option value=双鸭山市>双鸭山市</option>

																<option value=大庆市>大庆市</option>

																<option value=伊春市>伊春市</option>

																<option value=佳木斯市>佳木斯市</option>

																<option value=七台河市>七台河市</option>

																<option value=牡丹江市>牡丹江市</option>

																<option value=绥化市>绥化市</option>

																<option value=黑河市>黑河市</option>

																<option value=大兴安岭>大兴安岭</option>

																<option value=省农垦总局>省农垦总局</option>

																<option value=省森工总局>省森工总局</option>

															</SELECT> <select id="city" name="hc_form_city"
																class="form-control margin-r-5 hc_form_bylb"
																style="width: 30%;">

																<option value="">请选择</option>
															</select>
														</div>

														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_to_cn"></p>
															
													</div>
													
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_kl"> <b class="text-red">*</b>
															课类:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-mortar-board"></i></span> <select
																class="form-control margin-r-5 hc_form_bylb"
																id="hc_form_kl" name="hc_form_kl">
																<option value='' selected='selected'>== 请选择 ==</option>
															      <option value="文史类">文史类</option>
															      <option value="理工类">理工类</option>
															      <option value="中职">中职</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_kl"></p>
													</div>
													
													<div class="form-group text-s12 " id="fg_hc_form_xm">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_bkh">  报考号:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text"
																class="form-control hc_form_bkh" name="hc_form_bkh"
																id="hc_form_bkh" placeholder=" (可不填写)按考生报名地县（市、区）招考办（或中学）报名时所给报名号填写" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_bkh"></p>
													</div>
													
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

													<div class="form-group text-s12 " id="fg_hc_form_age">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_age"> <b class="text-red">*</b> 年龄:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-bell"></i></span> <input type="text"
																class="form-control hc_form_age" name="hc_form_age"
																id="hc_form_age" placeholder="年龄" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_age"></p>
													</div>


													<div class="form-group text-s12 " id="fa_hc_form_birth">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_birth"> <b class="text-red">*</b> 出生日期:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-birthday-cake"></i></span> <input type="text"
																class="form-control hc_form_birth" name="hc_form_birth"
																id="hc_form_birth" placeholder="出生日期"  readonly="readonly">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_birth"></p>
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
													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_bylb"> <b class="text-red">*</b>
															毕业类别:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<span class="input-group-addon"><i
																class="fa fa-mortar-board"></i></span> <select
																class="form-control margin-r-5 hc_form_bylb"
																id="hc_form_bylb" name="hc_form_bylb">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value="高中毕业">高中毕业</option>
																<option value="中等师范毕业">中等师范毕业</option>
																<option value="其它中等专业学校毕业">其它中等专业学校毕业</option>
																<option value="职业高中毕业">职业高中毕业</option>
																<option value="技工学校毕业">技工学校毕业</option>
																<option value="其它">其它</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_bylb"></p>
													</div>


													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_xslx"> <b class="text-red">*</b>
															考试类别:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<select class="form-control margin-r-5 hc_form_xslx"
																id="hc_form_xslx" name="hc_form_xslx">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value="城市应届">城市应届</option>
																<option value="农村应届">农村应届</option>
																<option value="城市往届">城市往届</option>
																<option value="农村往届">农村往届</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_xslx"></p>
													</div>


													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_mingzu"> <b class="text-red">*</b>
															民族:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<select class="form-control margin-r-5 hc_form_mingzu"
																id="hc_form_mingzu" name="hc_form_mingzu">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value="汉族">汉族</option>

																<option value="蒙古族">蒙古族</option>

																<option value="回族">回族</option>

																<option value="藏族">藏族</option>

																<option value="维吾尔族">维吾尔族</option>

																<option value="苗族">苗族</option>

																<option value="彝族 ">彝族</option>

																<option value="壮族">壮族</option>

																<option value="布依族">布依族</option>

																<option value="朝鲜族">朝鲜族</option>

																<option value="满族">满族</option>

																<option value="侗族">侗族</option>

																<option value="瑶族">瑶族</option>

																<option value="白族">白族</option>

																<option value="土家族">土家族</option>

																<option value="哈尼族">哈尼族</option>

																<option value="哈萨克斯">哈萨克斯</option>

																<option value="傣族">傣族</option>

																<option value="黎族">黎族</option>

																<option value="僳族">僳族</option>

																<option value="佤族">佤族</option>

																<option value="畲族">畲族</option>

																<option value="高山族">高山族</option>

																<option value="拉祜族">拉祜族</option>

																<option value="水族">水族</option>

																<option value="东乡族">东乡族</option>

																<option value="纳西族">纳西族</option>

																<option value="景颇族">景颇族</option>

																<option value="柯尔族">柯尔族</option>

																<option value="土族">土族</option>

																<option value="达斡尔族">达斡尔族</option>

																<option value="仫佬族">仫佬族</option>

																<option value="羌族">羌族</option>

																<option value="布郎族">布郎族</option>

																<option value="撒拉族">撒拉族</option>

																<option value="毛难族">毛难族</option>

																<option value="仡佬族">仡佬族</option>

																<option value="锡伯族">锡伯族</option>

																<option value="阿昌族">阿昌族</option>

																<option value="普米族">普米族</option>

																<option value="塔吉克族">塔吉克族</option>

																<option value="怒族">怒族</option>

																<option value="乌孜别克">乌孜别克</option>

																<option value="俄罗斯族">俄罗斯族</option>

																<option value="鄂温克族">鄂温克族</option>

																<option value="崩龙族">崩龙族</option>

																<option value="保安族">保安族</option>

																<option value="裕固族">裕固族</option>

																<option value="京族">京族</option>

																<option value="塔塔尔族">塔塔尔族</option>

																<option value="独龙族">独龙族</option>

																<option value="鄂伦春族">鄂伦春族</option>

																<option value="赫哲族">赫哲族</option>

																<option value="门巴族">门巴族</option>

																<option value="珞巴族">珞巴族</option>

																<option value="基诺族">基诺族</option>

																<option value="其他">其他</option>

																<option value="外国血统中国籍人士">外国血统中国籍人士</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_mingzu"></p>
													</div>


													<div class="form-group text-s12">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zhengzhimianmao"> <b
															class="text-red">*</b> 政治面貌:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<select
																class="form-control margin-r-5 hc_form_zhengzhimianmao"
																id="hc_form_zhengzhimianmao"
																name="hc_form_zhengzhimianmao">
																<option value='' selected='selected'>== 请选择 ==</option>

																<option value="中共党员">中共党员</option>

																<option value="中共预备党员">中共预备党员</option>

																<option value="共青团员">共青团员</option>

																<option value="民革会员">民革会员</option>

																<option value="民盟盟员">民盟盟员</option>

																<option value="民建会员">民建会员</option>

																<option value="民进会员">民进会员</option>

																<option value="农工党党员">农工党党员</option>

																<option value="致公党党员">致公党党员</option>

																<option value="九三学社社员">九三学社社员</option>

																<option value="台盟盟员">台盟盟员</option>

																<option value="无党派民主人士">无党派民主人士</option>

																<option value="群众">群众</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zhengzhimianmao"></p>
													</div>


													<div class="form-group text-s12 " id="fg_hc_form_xm">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_byxx"> <b class="text-red">*</b>
															毕业学校及班级:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text" class="form-control hc_form_byxx"
																name="hc_form_byxx" id="hc_form_byxx"
																placeholder="毕业学校及班级" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_byxx"></p>
													</div>


													<div class="form-group text-s12 " id="fg_hc_form_xm">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_hkszd"> <b class="text-red">*</b>
															户口所在地:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text" class="form-control hc_form_hkszd"
																name="hc_form_hkszd" id="hc_form_hkszd"
																placeholder="户口所在地" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_hkszd"></p>
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
																id="hc_form_sj" placeholder="随时能找到考生的联系电话" value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_sj"></p>
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


													<div class="form-group text-s12 " id="fg_hc_form_zy1">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zy1"> <b class="text-red">*</b>
															意向专业1:
														</label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<select class="form-control margin-r-5 hc_form_zy1"
																id="hc_form_zy1" name="hc_form_zy1">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value="软件技术">软件技术</option>

																<option value="计算机应用技术">计算机应用技术</option>

																<option value="广告设计与制作">广告设计与制作</option>

																<option value="建筑室内设计">建筑室内设计</option>

																<option value="会计">会计</option>

																<option value="市场营销">市场营销</option>

																<option value="电子商务">电子商务</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zy1"></p>
													</div>


													<div class="form-group text-s12 " id="fg_hc_form_zy2">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zy2"> 意向专业2: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<select class="form-control margin-r-5 hc_form_zy2"
																id="hc_form_zy2" name="hc_form_zy2">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value="软件技术">软件技术</option>

																<option value="计算机应用技术">计算机应用技术</option>

																<option value="广告设计与制作">广告设计与制作</option>

																<option value="建筑室内设计">建筑室内设计</option>

																<option value="会计">会计</option>

																<option value="市场营销">市场营销</option>

																<option value="电子商务">电子商务</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zy2"></p>
													</div>


													<div class="form-group text-s12 " id="fg_hc_form_zy3">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zy3"> 意向专业3: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<select class="form-control margin-r-5 hc_form_zy3"
																id="hc_form_zy3" name="hc_form_zy3">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value="软件技术">软件技术</option>

																<option value="计算机应用技术">计算机应用技术</option>

																<option value="广告设计与制作">广告设计与制作</option>

																<option value="建筑室内设计">建筑室内设计</option>

																<option value="会计">会计</option>

																<option value="市场营销">市场营销</option>

																<option value="电子商务">电子商务</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zy3"></p>
													</div>


													<div class="form-group text-s12 " id="fg_hc_form_zy4">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zy4"> 意向专业4: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<select class="form-control margin-r-5 hc_form_zy4"
																id="hc_form_zy4" name="hc_form_zy4">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value="软件技术">软件技术</option>

																<option value="计算机应用技术">计算机应用技术</option>

																<option value="广告设计与制作">广告设计与制作</option>

																<option value="建筑室内设计">建筑室内设计</option>

																<option value="会计">会计</option>

																<option value="市场营销">市场营销</option>

																<option value="电子商务">电子商务</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zy4"></p>
													</div>

													<div class="form-group text-s12 " id="fg_hc_form_zy5">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zy5"> 意向专业5: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<select class="form-control margin-r-5 hc_form_zy5"
																id="hc_form_zy5" name="hc_form_zy5">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value="软件技术">软件技术</option>

																<option value="计算机应用技术">计算机应用技术</option>

																<option value="广告设计与制作">广告设计与制作</option>

																<option value="建筑室内设计">建筑室内设计</option>

																<option value="会计">会计</option>

																<option value="市场营销">市场营销</option>

																<option value="电子商务">电子商务</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zy5"></p>
													</div>

													<div class="form-group text-s12 " id="fg_hc_form_zytj">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zytj"> 是否服从专业调剂: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<select class="form-control margin-r-5 hc_form_zytj"
																id="hc_form_zytj" name="hc_form_zytj">
																<option value='' selected='selected'>== 请选择 ==</option>
																<option value="软件技术">软件技术</option>

																<option value="是">是</option>

																<option value="否">否</option>
															</select>
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_zytj"></p>
													</div>

													<hr>

													<button class="btn btn-default btn-sm">本人简历信息</button>



													<div class="form-group text-s12 " id="fg_hc_form_szzx">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_szzx"> 所在高中: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-6">
															<input type="text" class="form-control hc_form_szzx"
																name="hc_form_szzx" id="hc_form_szzx"
																placeholder="你所读的高中名称" value="">

														</div>

														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_szzx"></p>
													</div>

													<div class="form-group text-s12 " id="fg_hc_form_zxdz">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_zxdz"> 高中学校地址: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text" class="form-control hc_form_zxdz"
																name="hc_form_zxdz" id="hc_form_zxdz"
																placeholder="你所读的高中地址" value="">
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

													<div class="form-group text-s12 "
														id="fg_hc_form_jdstarttime">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_jdstarttime"> 就读开始时间: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text"
																class="form-control hc_form_jdstarttime"
																name="hc_form_jdstarttime" id="hc_form_jdstarttime"
																placeholder="就读开始时间" data-date-format="yyyy-mm-dd"
																readonly="readonly">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_jdstarttime"></p>
													</div>

													<div class="form-group text-s12 " id="fg_hc_form_jdendtime">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_jdendtime"> 就读结束时间: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text"
																class="form-control hc_form_jdstarttime"
																name="hc_form_jdendtime" id="hc_form_jdendtime"
																placeholder="就读结束时间 " data-date-format="yyyy-mm-dd"
																readonly="readonly">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_jdendtime"></p>
													</div>


													<div class="form-group text-s12 " id="fg_hc_form_yhtc">
														<label
															class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
															for="hc_form_yhtc"> 有何特长: </label>
														<div class="input-group input-group-sm col-sm-7 col-xs-12">
															<input type="text" class="form-control hc_form_yhtc"
																name="hc_form_yhtc" id="hc_form_yhtc"
																placeholder="有何特长 " value="">
														</div>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_yhtc"></p>
													</div>



													<hr>

													<button class="btn btn-default btn-sm">紧急联系人</button>

													<div class="form-group text-s12 " id="fg_hc_form_jxlxr">
														<br>
														<table style="width: 100%;"
															class="table table-striped table-hover table-bordered">
															<thead>
																<tr>
																	<th>关系</th>
																	<th>姓名</th>
																	<th>联系电话</th>
																	<th>现在何单位工作</th>
																	<th>任何职务</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_fa_gx" name="hc_form_jjlxr_fa_gx"
																		id="hc_form_jjlxr_fa_gx" placeholder="与本人关系 " value="">
																		</div>
																		</td>
																	<td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_fa_name" name="hc_form_jjlxr_fa_name"
																		id="hc_form_jjlxr_fa_name" placeholder="联系人姓名 " value="">
																		
																		</div>
																		
																		</td><td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_fa_tel" name="hc_form_jjlxr_fa_tel"
																		id="hc_form_jjlxr_fa_tel" placeholder="联系人电话 " value="">
																		</div>
																		</td><td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_fa_work" name="hc_form_jjlxr_fa_work"
																		id="hc_form_jjlxr_fa_work" placeholder="工作单位" value="">
																		</div>
																		</td><td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_fa_zw" name="hc_form_jjlxr_fa_zw"
																		id="hc_form_jjlxr_fa_zw" placeholder="任何职务" value="">
																		</div>
																		</td>
																</tr>
																
																<tr>
																	<td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_ma_gx" name="hc_form_jjlxr_ma_gx"
																		id="hc_form_jjlxr_ma_gx" placeholder="与本人关系 " value="">
																		</div>
																		</td>
																	<td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_ma_name" name="hc_form_jjlxr_ma_name"
																		id="hc_form_jjlxr_ma_name" placeholder="联系人姓名 " value="">
																		</div>
																		</td><td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_ma_tel" name="hc_form_jjlxr_ma_tel"
																		id="hc_form_jjlxr_ma_tel" placeholder="联系人电话 " value="">
																		</div>
																		</td><td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_ma_work" name="hc_form_jjlxr_ma_work"
																		id="hc_form_jjlxr_ma_work" placeholder="工作单位" value="">
																		</div>
																		</td><td>
																	<div class="input-group input-group-sm">
																	<input type="text"
																		class="form-control hc_form_jjlxr_ma_zw" name="hc_form_jjlxr_ma_zw"
																		id="hc_form_jjlxr_ma_zw" placeholder="任何职务" value="">
																		</div>
																		</td>
																</tr>

															</tbody>
														</table>
														<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_jjlxr_fa_tel"></p>
															<p class="col-sm-offset-3 col-xs-offset-3"
															id="error-hc_form_jjlxr_fa_name"></p>
													</div>
													<hr>
													<button class="btn btn-default btn-sm">社交联系方式</button>


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
	 var idCard = "${param.hc_form_sfzh}"
		 $("#hc_form_sfzh").val(idCard);
	 if(idCard != null && idCard != ""){  
         if(idCard.length == 15){  
             birthday = "19"+idCard.substr(6,6);  
         } else if(idCard.length == 18){  
             birthday = idCard.substr(6,8);  
         }  
       
         birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-");  
     } 
	 $("#hc_form_birth").val(birthday);
	 
	 if (parseInt(idCard.substr(16, 1)) % 2 == 1) {
		 $("#hc_form_xb_0").attr('checked','true');
	 }else{
		 $("#hc_form_xb_1").attr('checked','true');
	 }
	
	 var myDate = new Date();
     var month = myDate.getMonth() + 1;
     var day = myDate.getDate();
     var age = myDate.getFullYear() - idCard.substring(6, 10) - 1;
     if (idCard.substring(10, 12) < month || idCard.substring(10, 12) == month && idCard.substring(12, 14) <= day) {
         age++;
     }
     $("#hc_form_age").val(age);
	
    $('#hc_form_jdstarttime').datetimepicker({
    	language:  'zh-CN',
    	 minView: "month",//设置只显示到月份
    	  format : "yyyy-mm-dd",//日期格式
    	  autoclose:true,//选中关闭
    	  todayBtn: true//今日按钮
    });
    $('#hc_form_jdendtime').datetimepicker({
    	language:  'zh-CN',
    	minView: "month",//设置只显示到月份
    	  format : "yyyy-mm-dd",//日期格式
    	  autoclose:true,//选中关闭
    	  todayBtn: true//今日按钮
    });
    
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

<script language=javascript> 

cities = new Object(); 
cities['哈尔滨市']=new Array('道里区','南岗区','道外区','松北区', '香坊区', '平房区', '呼兰区', '阿城区', '依兰县', '宾县', '方正县', '双城市', '五常市', '巴彦县', '木兰县', '通河县', '尚志市', '延寿县'); 

cities['齐齐哈尔市']=new Array('龙沙区', '建华区', '铁锋区', '昂昂溪区', '富拉尔基区', '碾子山区', '梅里斯区', '龙江县', '讷河市', '依安县', '泰来县','甘南县', '富裕县', '克山县', '克东县', '拜泉县'); 
cities['鸡西市']=new Array('鸡西市','鸡东县', '密山市', '虎林市'); 
cities['鹤岗市']=new Array('鹤岗市','绥滨县', '萝北县'); 
cities['双鸭山市']=new Array('双鸭山市','集贤县', '宝清县', '友谊县', '饶河县'); 

cities['大庆市']=new Array('萨尔图区', '龙凤区', '红岗区', '大同区', '让胡路区', '杜蒙自治县', '林甸县', '肇源县', '肇州县'); 
cities['伊春市']=new Array('伊春区', '友好区', '五营区', '新青区', '南岔区', '带岭区', '铁力市', '嘉荫县'); 
cities['佳木斯市']=new Array('佳木斯市','富锦市', '桦南县', '桦川县', '汤原县', '同江市', '抚远县'); 

cities['七台河市']=new Array('七台河市','勃利县'); 

cities['牡丹江市']=new Array('牡丹江市','绥芬河市', '宁安市', '海林市', '穆棱县', '东宁县', '林口县'); 

cities['绥化市']=new Array('北林区', '安达市', '海伦市', '肇东市', '望奎县', '兰西县', '青冈县', '庆安县', '明水县', '绥棱县'); 

cities['黑河市']=new Array('黑河市', '北安市', '五大连池市', '嫩江县', '五大连池名胜风景区', '逊克县', '孙吴县'); 

cities['大兴安岭']=new Array('松岭区', '新林区', '呼中区', '呼玛县', '塔河县', '漠河县', '加格达奇区'); 

cities['省农垦总局']=new Array('省农垦总局','哈尔滨农垦分局局直', '红兴隆农垦分局局直', '宝泉岭农垦分局局直', '建三江农垦分局局直', '北安农垦分局第一高中', '北安农垦分局第二高中', '九三农垦分局局直', '齐齐哈尔农垦分局查哈阳', '牡丹江农垦分局局直', '绥化农垦分局局直'); 

cities['省森工总局']=new Array('省森工总局','沾河林业局', '绥棱林业局', '方正林业局', '亚布力(苇河)林业局', '东方红林业局', '清河林业局', '大海林林业局', '柴河林业局', '穆棱林业局', '东京城林业局', '绥阳林业局', '海林林业局'); 

function set_city(province, city) 

{ 

var pv, cv; 

var i, ii; 

pv=province.value; 

cv=city.value; 

city.length=1; 

if(pv=='0') return; 

if(typeof(cities[pv])=='undefined') return; 

for(i=0; i<cities[pv].length; i++) 

{ 

ii = i+1; 

city.options[ii] = new Option(); 

city.options[ii].text = cities[pv][i]; 

city.options[ii].value = cities[pv][i]; 

} 

} 

</script> 
</body>
</html>
