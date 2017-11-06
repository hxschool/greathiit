<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>2018年高职(专科)单独招生专业志愿填报系统说明</title>

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


<c:if test="${fns:getDictValue('switch', 'greathiit_zhaosheng', '关')=='no'}">  
<script>
alert("${fns:getDictDescription('switch', 'greathiit_zhaosheng', '关')}");
window.close();
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
											id="bmform">2018年高职(专科)单独招生专业志愿填报系统说明</div>

				<div class="panel-body"
											style="border-bottom: 1px solid #337AB7;">
											
		${fns:getDictDescription('2018', 'greathiit_zhaosheng', '暂无')}
		
		<p class="text-s16">
		<a href="http://www.hxci.com.cn/zy/"
												class="btn btn-info btn-sm ">查看招生简章</a> <a
												href="skip_Jieguo" class="btn btn-success btn-sm">查看报考结果</a>
											</p>
		</div>
<!-- 
										<div class="panel-body"
											style="border-bottom: 1px solid #337AB7;">

											<p class="text-s16">
											<p align="center">哈尔滨信息工程学院</p>
											<p align="center">2018年高职（专科）单独招生章程</p>
											<p align="left">
												哈尔滨信息工程学院是国家教育部批准的全日制普通本科高等学校，是首批国家示范性软件学院，是教育部授予的全国毕业生就业工作先进集体。根据教育部(教高[2003]7号)、黑龙江省教育厅(黑教高[2004]70号)、黑龙江省教育厅相关文件和黑龙江省招考院《关于我省2018年普通高等学校招生报名工作的通知》文件精神，2018年，我校在省教育厅和省招考院领导监督下，继续进行高职（专科）单独招生。为进一步做好2018年单独招生工作，制定本章程。
											</p>
											<p align="left">
												<strong>一、单独招生目的及原则</strong>
											</p>
											<p align="left">
												为深入贯彻落实教育部及省教育厅相关文件精神，加快推进高职院校分类考试，进一步提高通过分类考试招生比例。加快发展现代职业教育，以提高质量为核心，以深化改革为动力，以优化结构为重点，落实好国家提出的关于高职院校考试招生与普通高校相对分开、实行“文化素质+职业技能”评价方式和逐步扩大分类考试招生规模。单独招生工作遵循遵循公平竞争、公正选拔、公开透明的原则，对考生进行德智体美全面考核、综合评价、择优录取。
											</p>
											<p align="left">
												<strong>二、组织领导</strong>
											</p>
											<p align="left">
												1、学校成立由校长任组长的单独招生工作领导小组，学校纪检监察部门负责对单独招生工作的全程监督，并成立由学校招生办、教务处和各院系为主的单独招生办公室，凡单独招生考试、录取中的重大问题，一律由学校单独招生工作领导小组研究决定。
											</p>
											<p align="left">2、加强招生人员的培训和政策宣传工作，实施高考“阳光工程”。</p>
											<p align="left">3、学校单独招生全过程在省教育厅和省招考院的监督和领导下进行。</p>
											<p align="left">
												<strong>三、报名条件</strong>
											</p>
											<p align="left">
												高中、中职（职高、中专、技校）应往届毕业生，且符合黑龙江省招考院《关于我省2018年普通高等学校招生报名工作的通知》文件中报考条件的考生，均可报考我校高职（专科）单独招生考试。
											</p>
											<p align="left">
												报考我省单独招生的高职（专科）院校的考生，须参加高考报名，由招生院校单独组织考试，考生只允许报考一所院校，考生根据所报院校相关要求，填报院校专业志愿，参加单独招生的考生可兼报普通高校招生全国统一考试。凡被单独招生院校录取的考生不得参加普通高校招生全国统一考试及录取。
											</p>
											<p align="left">
												<strong>四、报名时间及现场信息确认地点</strong>
											</p>
											<p align="left">报名分为网上填报基本信息、网上缴费、现场资格审查及信息确认。</p>
											<p align="left">1、网上报名时间</p>
											<p align="left">
												我省普通高校招生全国统一考试网上报名从2017年10月16日上午9:00时开始，2017年10月31日上午10:00时截止。
											</p>
											<p align="left">2、现场资格审查及信息确认时间</p>
											<p align="left">
												考生网上填报基本信息、网上缴费结束后应携带相关材料按县（市、区）招考办规定的时间及地点进行确认。全省考生现场资格审查及信息确认从2017年10月18日开始，2017年11月16日下午17:00时截止。
											</p>
											<p align="left">3、现场信息确认地点</p>
											<p align="left">
												（1）户籍和学籍在我省的应届高中毕业生由中学负责组织，到县（市、区）招考办指定的地点进行信息确认；</p>
											<p align="left">（2）往届生及其他考生在户籍所在市（行署）、县（市、区）招考办进行信息确认；</p>
											<p align="left">
												（3）户籍为我省久居但为外省学籍的应届考生，凭应届毕业证明信原件、外省省级学业水平考试（或高中会考）管理机构提供的成绩证明材料和就读学校提供的思想政治品德考核材料，在户籍所在市（行署）、县（市、区）招考办进行信息确认。
											</p>
											<p align="left">
												<strong>五、报名工作程序</strong>
											</p>
											<p align="left">1、网上注册</p>
											<p align="left">
												考生登录黑龙江省招生考试信息港（www.lzk.hl.cn）后，进入网报中心，点击2018年黑龙江省普通高校招生全国统一考试报名入口，凭考生本人身份证号进行注册，设置网上报名密码。
											</p>
											<p align="left">2、网上报名和缴费</p>
											<p align="left">
												考生应在规定时间内上网填报、修改及查询本人信息。网上报名时所填报的信息，如姓名、出生日期、性别、民族等必须与居民身份证信息保持一致，所填报的信息要真实、准确、有效，并严格按照信息格式和操作提示进行填报。如考生已参加了高考报名，现需兼报我校高职（专科）单独招生，只需在考生标记处选择我校，具体操作按省招考办有关文件执行。
											</p>
											<p align="left">
												考生报考信息填写完毕后须进行网上支付报考费操作（网上支付支持中国农业银行账户或其它具有银联标识的银行卡），考生网上报考费支付成功即完成网上报名。凡是参加高考报名的考生须交报名考试费150元。
											</p>
											<p align="left">3、报名信息确认</p>
											<p align="left">
												考生完成网上信息填报、缴费提交成功后，还须在县（市、区）招考办规定的时间和地点进行现场确认。考生不按时完成网上基本信息填报、报考费支付、现场确认等均视为自动放弃普通高考报名。
											</p>
											<p align="left">
												<strong>六、填报专业志愿方式</strong>
											</p>
											<p align="left">1、网上填报专业志愿</p>
											<p align="left">
												已报考我校高职（专科）单独招生的考生须在2017年11月16日前登录我校网站http://www.greathiit.com，进入2018年高职（专科）单独招生志愿填报系统，阅读单独招生专业志愿填报的有关说明，按提示填报专业志愿。考生填报信息必须与高考网上报名信息一致。
											</p>
											<p align="left">2、报考专业确认</p>
											<p align="left">
												考生本人须在2018年单独招生考试前到我校现场确认所填报专业志愿，凭填报专业志愿时返回的报名顺序号、第二代《居民身份证》领取准考证，领取准考证具体时间在学校官网另行通知。考生领取准考证时，需交纳考试费。
											</p>
											<p align="left">3、考试费：按照有关规定收取150元/人，由考生所报考的高校收取。</p>
											<p align="left">
												4、考生不按时完成网上专业志愿填报、现场确认专业志愿、缴纳考试费等均视为自动放弃我校单独招生报名。</p>
											<p align="left">
												<strong>七、招生专业及招生计划</strong>
											</p>
											<p align="left">
												我校拟定2018年单独招生计划1000人，在黑龙江招生考试信息港（www.lzk.hl.cn）和我校网站（http://www.greathiit.com）发布。
											</p>
											<p align="left">单独招生共7个专业，学制均为3年。各专业招生计划见下表：</p>
											<table width="575" cellspacing="0" cellpadding="0" border="1">
												<tbody>
													<tr>
														<td width="53">
															<p align="center">序号</p>
														</td>
														<td width="394" nowrap="">
															<p align="center">招生专业</p>
														</td>
														<td width="80" nowrap="">
															<p align="center">招生计划</p>
														</td>
														<td width="48">
															<p align="center">学制</p>
														</td>
													</tr>
													<tr>
														<td width="53">
															<p align="center">01</p>
														</td>
														<td width="394" nowrap="">
															<p align="left">软件技术</p>
														</td>
														<td width="80" valign="top" nowrap="">
															<p align="center">563</p>
														</td>
														<td width="48">
															<p align="center">3年</p>
														</td>
													</tr>
													<tr>
														<td width="53">
															<p align="center">02</p>
														</td>
														<td width="394" nowrap="">
															<p align="left">计算机应用技术</p>
														</td>
														<td width="80" valign="top" nowrap="">
															<p align="center">71</p>
														</td>
														<td width="48">
															<p align="center">3年</p>
														</td>
													</tr>
													<tr>
														<td width="53" valign="top">
															<p align="center">03</p>
														</td>
														<td width="394" valign="top" nowrap="">
															<p align="left">广告设计与制作</p>
														</td>
														<td width="80" valign="top" nowrap="">
															<p align="center">52</p>
														</td>
														<td width="48" valign="top">
															<p align="center">3年</p>
														</td>
													</tr>
													<tr>
														<td width="53" valign="top">
															<p align="center">04</p>
														</td>
														<td width="394" valign="top" nowrap="">
															<p align="left">建筑室内设计</p>
														</td>
														<td width="80" valign="top" nowrap="">
															<p align="center">124</p>
														</td>
														<td width="48" valign="top">
															<p align="center">3年</p>
														</td>
													</tr>
													<tr>
														<td width="53" valign="top">
															<p align="center">05</p>
														</td>
														<td width="394" valign="top" nowrap="">
															<p align="left">会计</p>
														</td>
														<td width="80" valign="top" nowrap="">
															<p align="center">49</p>
														</td>
														<td width="48" valign="top">
															<p align="center">3年</p>
														</td>
													</tr>
													<tr>
														<td width="53" valign="top">
															<p align="center">06</p>
														</td>
														<td width="394" valign="top" nowrap="">
															<p align="left">市场营销</p>
														</td>
														<td width="80" valign="top" nowrap="">
															<p align="center">38</p>
														</td>
														<td width="48" valign="top">
															<p align="center">3年</p>
														</td>
													</tr>
													<tr>
														<td width="53" valign="top">
															<p align="center">07</p>
														</td>
														<td width="394" valign="top" nowrap="">
															<p align="left">电子商务</p>
														</td>
														<td width="80" valign="top" nowrap="">
															<p align="center">103</p>
														</td>
														<td width="48" valign="top">
															<p align="center">3年</p>
														</td>
													</tr>
													<tr>
														<td colspan="2" width="447">
															<p align="center">合计</p>
														</td>
														<td width="80" nowrap="">
															<p align="center">1000</p>
														</td>
														<td width="48" valign="top"><br /></td>
													</tr>
												</tbody>
											</table>
											<p align="left">
												注：以上为学校拟定招生计划，招生专业及招生计划具体数额以黑龙江省教育厅正式下达的数据为准。</p>
											<p>
												<strong>八、学费、住宿费标准</strong>
											</p>
											<p>学校根据黑龙江省物价部门有关规定收取学费、住宿费。</p>
											<p>学费：高职（专科）所有专业学费均为12000元/年。</p>
											<p>住宿费：根据学校安排统一入住。四人间：每人每年1500元。</p>
											<p>
												要求学生入学时自备笔记本电脑的专业：所有专业均要求学生入学时自备笔记本电脑；其中，软件技术、计算机应用技术专业想学习iOS软件开发的学生要求入学时自备苹果笔记本电脑。
											</p>
											<p align="left">
												<strong>九、考试科目、时间、地点</strong>
											</p>
											<p align="left">1、我校单独招生考试，在省招考院领导监督下，自行组织实施。</p>
											<p align="left">
												2、考试科目：实行“文化素质+职业技能”评价方式。文化课综合考试：满分300分（语文150分、数学150分）；职业技能（职业适应性）测试：满分200分。总分500分。考试采用闭卷、笔答形式。
											</p>
											<p align="left">
												3、命题：文化课综合考试参考普通高中和中职学校教学大纲和教材命题。职业技能（职业适应性）测试以普通高中、中职学校的教科书为基础，结合高中和中职教育的学习要求及教育实际，侧重考察学生的思想素质、科学素质、人文素质、健康素质等，其目的是测试考生的综合素质和职业倾向性。
											</p>
											<p align="left">4、命题、试卷印刷及考务工作由我校自行组织、安排。</p>
											<p align="left">5、考试时间：2018年3月份，具体时间以黑龙江省招考院发布时间为准。</p>
											<p align="left">6、考试地点：哈尔滨信息工程学院</p>
											<p align="left">地 址：哈尔滨市宾西经济技术开发区大学城9号</p>
											<p align="left">
												7、考试实施程序参照普通高校招生考试相关要求执行，要求考生携带第二代居民身份证和准考证参加考试。</p>
											<p align="left">
												<strong>十、阅卷、登分</strong>
											</p>
											<p align="left">
												单独招生考试的阅卷、登分工作，由我校统一组织。考生成绩全部录入计算机，复查无误后向考生公布。</p>
											<p align="left">
												<strong>十一、录取</strong>
											</p>
											<p align="left">
												1、单独招生录取工作在省招考院领导下进行，学校向省招考院上报拟录取考生名单并办理相关录取手续。</p>
											<p align="left">
												2、录取原则：遵照省招考院关于普通高校招生工作有关规定，在考试成绩达到全省高职（专科）录取最低控制线的考生中，按单独招生考试总成绩确定考生的录取顺序，按招生计划的一定比例确定调档比例，从高分到低分依次录取。
											</p>
											<p align="left">
												3、录取专业：按照考生填报专业志愿的先后顺序，按专业优先的原则为考生分配专业，即先录取第一专业志愿考生，从高分到低分录取，如没录满，再录取第二专业志愿考生，以此类推。若考生单独招生考试总分相同，以职业技能（职业适应性）测试、语文、数学顺序优先为单科成绩高者安排专业。若考生填报专业志愿不能满足，又不服从专业调剂，将不予录取。
											</p>
											<p align="left">
												4、学校各专业录取对考生身体健康状况要求按教育部、卫生部、中国残疾人联合会制定的《普通高等学校招生体检工作指导意见》执行。已录取考生入学后，将进行体检复查，对不符合专业体检要求的考生，取消入学资格。
											</p>
											<p align="left">
												5、已录取的考生，由学校下发录取通知书，考生按录取通知书规定时间到校报到，逾期不报到者视为放弃入学资格。</p>
											<p align="left">
												<strong>十二、毕业证书</strong>
											</p>
											<p align="left">
												学生学业期满，成绩合格，颁发经教育部统一电子注册的哈尔滨信息工程学院全日制普通高等教育高职（专科）毕业证书。</p>
											<p align="left">
												<strong>十三、联系方式</strong>
											</p>
											<p align="left">学校全称：哈尔滨信息工程学院</p>
											<p align="left">国标代码：11635</p>
											<p align="left">学校地址：黑龙江省哈尔滨市宾西经济技术开发区大学城9号</p>
											<p align="left">邮政编码：150431</p>
											<p align="left">咨询热线：0451-58607888</p>
											<p align="left">学校网址：http://www.greathiit.com</p>
											<p align="left">电子信箱：hxcizsb@126.com</p>
											<p align="left">
												乘车路线：哈尔滨火车站前乘6路公共汽车到哈东站，换乘三棵树公路客运站发往宾县的天元集团客车，在宾西大学城哈尔滨信息工程学院下车即到。
											</p>
											<p align="left">
												<strong>十四、附则</strong>
											</p>
											<p align="left">
												1、本章程将根据教育部、生源地省级招生管理部门当年招生政策的调整进行修订。本章程若与国家法律、法规和上级有关政策相抵触，以国家法律、法规和上级有关政策为准。
											</p>
											<p align="left">
												2、本章程自发布之日起生效执行。学校以往有关招生工作的要求、规定如与本章程冲突，以本章程为准，并即时废止以往有关规定。
											</p>
											<p align="left">3、本章程由哈尔滨信息工程学院招生办公室负责解释。</p>
											<p align="right">哈尔滨信息工程学院</p>
											<p align="right">2018年10月9日</p>
-->
											

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

															
																<c:if test="${fns:getDictValue('switch', 'greathiit_zhaosheng', '')=='yes'}">  
																<input type="checkbox" class="pc" name="agreebbrule" id="agreebbrule" >
																<label for="agreebbrule">同意<!--  <a
																href="javascript:;" style="color:red" onclick="showBBRule()"> 特别提示 </a> --></label> 
																
																	<input
																	type="submit" class="btn btn-primary btn-sm"
																	name='bm_submit' value="提交申请表">
																</c:if>
																
																
																<c:if test="${fns:getDictValue('switch', 'greathiit_zhaosheng', '关')=='no'}">  
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
	</div>
	<script type="text/javascript">
		function showBBRule() {
			layer.open({
				type : 1,
				area : [ '680px', '400px' ],
				shadeClose : true, //点击遮罩关闭
				content : $('#msg')
			});
		}
		function check(){
			if(!$('#agreebbrule').is(':checked')) {
				layer.alert('请确认点击同意特别提示信息')
			   	return false;
			}
			return true;
		}
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

	<div id="msg" style="display: none;margin: 10px;padding:20px;">
		<p align="center">2018年高职(专科)单独招生专业志愿填报系统说明</p>
		<font color="red">
		<p>
			<strong >特别提示：</strong>
		</p>
		<p>
			<strong>考生必须在当地招考办报名后，方能在我校网站填报专业志愿，否则报考无效。</strong>
		</p>
		<p>
			<strong>
				由于我省教育主管部门高职（专科）专业审批结果未公布，目前填报专业为我校拟开设专业，具体招生专业以黑龙江省教育厅、招考办公布为准。如我省教育主管部门审批专业没有变化，则以本次填报为准；如招生专业有变化，考生需重新填报。
			</strong>
		</p>
		<p>
			<strong>专业志愿填报截止时间2017</strong> <strong>年11</strong> <strong>月30日。</strong>
			
		</p>
		</font >
		<p>为方便考生填写《专业志愿表》，现将填写办法按表中需填内容顺序说明如下：</p>
		<p>1、姓名：用汉字填写，与身份证一致。</p>
		<p>2、性别：1-男 2-女。</p>
		<p>3、身份证号码：从左到右逐格填写，身份证号码为18位，与身份证一致。</p>
		<p>4、毕业中学：用汉字准确填写毕业中学名称。应届毕业生须填写考生学籍所在中学名称，往届毕业生须填写发放毕业证书的中学名称。</p>
		<p>5、毕业类别：</p>
		<p>0-普通高中毕业 1-中等师范毕业 2-其它中等专业学校毕业</p>
		<p>3-职业高中毕业 4-技工学校毕业 5-其它</p>
		<p>6、随时能找到考生的联系电话：准确写明本人联系电话，以能找到本人为准。</p>
		<p>7、报考专业：按顺序选择报考专业，可选择5个报考专业。</p>
		<p>8、专业是否服从调剂：所报考专业录取额满，是否服从调剂到其他未录满专业。</p>

	</div>
</body>
</html>
