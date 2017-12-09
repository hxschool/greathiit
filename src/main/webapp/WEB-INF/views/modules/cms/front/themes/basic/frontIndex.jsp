<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.description}" />
	<meta name="keywords" content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.keywords}" />
	<script type="text/javascript" src="${ctxStaticTheme}/jquery.sliderPro.min.js"></script>
	<link href="${ctxStaticTheme}/css/slider-pro.min.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStaticTheme}/css/sliderPro.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	$( document ).ready(function( $ ) {
		$( '#example-slide' ).sliderPro({
			width: 936,
			height: 532,
			orientation: 'vertical',
			loop: false,
			arrows: true,
			buttons: false,
			thumbnailsPosition: 'right',
			thumbnailPointer: true,
			thumbnailWidth: 250,
			thumbnailHeight: 130,
		});
	});
</script>
</head>
<body>
<div id="example-slide" class="slider-pro">
  <div class="sp-slides">
    <div class="sp-slide"><img class="sp-image" src="${ctxStaticTheme}/css/images/blank.gif" data-src="${ctxStaticTheme}/images/1.jpg" data-retina="${ctxStaticTheme}/images/1.jpg"/>
      
    </div>
    <div class="sp-slide"><img class="sp-image" src="${ctxStaticTheme}/css/images/blank.gif" data-src="${ctxStaticTheme}/images/2.jpg" data-retina="${ctxStaticTheme}/images/2.jpg"/>
      
    </div>
   <div class="sp-slide"><img class="sp-image" src="${ctxStaticTheme}/css/images/blank.gif" data-src="${ctxStaticTheme}/images/3.jpg" data-retina="${ctxStaticTheme}/images/3.jpg"/>
      
    </div>
    <div class="sp-slide"><img class="sp-image" src="${ctxStaticTheme}/css/images/blank.gif" data-src="${ctxStaticTheme}/images/4.png" data-retina="${ctxStaticTheme}/images/4.png"/>
      
    </div>
  </div>
  <div class="sp-thumbnails">
    <div class="sp-thumbnail">
      <img class="sp-thumbnail-image" src="${ctxStaticTheme}/images/1.jpg" />
      
    </div>
    <div class="sp-thumbnail">
     <img class="sp-thumbnail-image" src="${ctxStaticTheme}/images/2.jpg"/>
    </div>
    <div class="sp-thumbnail">
     <img class="sp-thumbnail-image" src="${ctxStaticTheme}/images/3.jpg"/>
      
    </div>
    <div class="sp-thumbnail">
     <img class="sp-thumbnail-image" src="${ctxStaticTheme}/images/4.png"/>
      
    </div>
    
  </div>
</div>

    
    
  
<br>
  
   <div class="common-race-list">
                <ul class="clear" data-bind="foreach: raceList">
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/298">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201711/c84f9c26d992216935b3896a825acb61.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017环珠三角自行车联赛·深圳站</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-17 08:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-17 18:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-11-10 10:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-14 12:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="深圳市龙岗体育中心">深圳市龙岗体育中心</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="广东省体育局">广东省体育局</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">17</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">13265</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/312">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201711/f41cc0928addb49b9abb63485697e5fd.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017千里达轮动全城·广州站</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-17 07:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-17 18:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-11-24 18:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-13 23:59</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="广州市花都区香草世界">广州市花都区香草世界</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="千里达">千里达</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">9</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">11035</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/318">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201712/040c6d0b4793bcdbdcc52fa225c22bc8.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017环珠三角自行车联赛·广州站</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-24 08:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-24 16:30</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-12-01 18:55</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-21 12:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="广州黄村体育训练中心">广州黄村体育训练中心</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="广东省体育局">广东省体育局</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">5</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">4931</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/314">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201711/95c77242af0fd82ca0adfbfa8db0c654.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">“农商银行”七佛山爬坡王自行车挑战赛</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-09 07:30</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-09 11:50</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-11-30 18:51</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-06 18:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="高平市炎帝大道">高平市炎帝大道</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="山西高平农村商业银行">山西高平农村商业银行</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">2</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">6626</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/300">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201711/7c50ef94f01c9de5689e59189419f7a8.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017南京大屠杀纪念日线上跑</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-10 00:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-13 00:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-11-10 00:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-09 00:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="线上活动任意地点">线上活动任意地点</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="全民一起跑">全民一起跑</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">1</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">632</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/304">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201711/169309c28fd0a1fa7fb886a0bed51f7e.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017广州市番禺区第五届自行车邀请赛</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-15 09:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-15 13:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-11-15 11:43</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-08 12:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="番禺区大夫山">番禺区大夫山</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="广州市番禺区文广新局（区体育局）">广州市番禺区文广新局（区体育局）</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">3</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">1595</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/320">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201712/c7a1a05b786a09250cefb8a47f9ec242.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017保利罗兰国际儿童平衡车挑战赛</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-16 09:30</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-17 17:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-12-04 18:30</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-14 12:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="广东省广州市黄埔区开创大道2656号保利罗兰国际营销中心">广东省广州市黄埔区开创大道2656号保利罗兰国际营销中心</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="保利罗兰国际">保利罗兰国际</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">2</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">3186</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/316">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201712/59c99e2fc89d97684ad8bfc927e5a8bf.png" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">碧桂园RUN！KIDS小铁三城市赛-广州站</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-17 07:30</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-17 12:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-11-20 12:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-15 20:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="华南师范大学（大学城校区）">华南师范大学（大学城校区）</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="盛博林文化发展有限公司">盛博林文化发展有限公司</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">0</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">261</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/305">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201711/ace49dbe0fba17da807fef02252b9615.png" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017年广西忻城第六届全国山地自行车 越野公开赛</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-17 08:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-17 16:30</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-11-16 15:10</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-12 18:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="广西来宾市忻城县盘鹤岭体育森林公园">广西来宾市忻城县盘鹤岭体育森林公园</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="来宾市体育局、忻城县人民政府">来宾市体育局、忻城县人民政府</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">1</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">324</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/319">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201712/2516b753559b6abe12e144b9c0934e18.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">广东省第四届自行车绿道联赛 （佛山南庄绿岛湖站）</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-17 10:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-17 16:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-12-04 12:25</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-15 12:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                              <div class="clear">
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="绿岛湖国际中心">绿岛湖国际中心</span></p>
                            </div>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="广东省自行车运动协会">广东省自行车运动协会</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">2</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">1322</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/321">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201712/f6d2f96646c3c31d851fe63c91174a48.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017西安首届古城小骑手儿童平衡车大奖赛</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-17 10:30</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-17 16:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-12-05 17:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-12 23:59</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="丰禾路332号 【鑫苑大都汇】">丰禾路332号 【鑫苑大都汇】</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="古城小骑手俱乐部、酷宝贝儿童平衡车俱乐部">古城小骑手俱乐部、酷宝贝儿童平衡车俱乐部</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">2</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">293</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/315">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201712/0f479bc62ac5cc61cc6f6a40a66d29cc.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017圣诞节线上跑</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-20 00:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-25 00:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-12-01 00:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-19 23:59</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="线上活动 任意地点">线上活动 任意地点</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="全民一起跑">全民一起跑</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">0</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">211</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/311">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201711/78f8c77766916c93e2180c6a535bef6d.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">坦洲水乡休闲骑</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-24 08:30</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-24 16:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-11-27 00:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-22 23:59</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="珠海铭兴自行车（前山店）">珠海铭兴自行车（前山店）</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="珠海铭兴自行车">珠海铭兴自行车</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">1</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">1052</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/313">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201711/1b0f08fee9d56dbfe7b8a950163edf5e.png" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">X-Pro甘肃儿童滑步车公开赛（兰州城关区站）</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-30 09:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-30 16:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-12-01 12:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-12-20 23:59</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="甘肃国际会展中心">甘肃国际会展中心</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="城关区文化和体育局、城关区教育局">城关区文化和体育局、城关区教育局</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">2</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">778</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/228">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201708/971340e4dbe33f1a7d6e78c766ac4c43.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2018 Challenge Taiwan 國際鐵人三項競賽</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing" style="display: none;">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2018-04-28 06:00</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2018-04-29 12:00</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-08-28 13:21</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2018-01-31 23:59</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="台湾台东县台东马亨亨大道">台湾台东县台东马亨亨大道</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="台灣鐵人三項運動發展協會">台灣鐵人三項運動發展協會</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">0</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">614</span>
                            </div>
                        </div>
                    </li>
                
                    <li class="race-item">
                        <a data-bind="attr:{href: '#/detail/'+ race_id}" target="_blank" href="#/detail/283">
                            <img class="item-img" src="https://c4.biketo.com/racegood-com/race_cover/201710/071d5542d6c34f2927018d52f2f827d0.jpg" data-bind="attr:{'data-echo':poster}">
                        </a>
                        <div class="race-content">
                            <h1 class="race-name" data-bind="text:title">2017中国·元江第三届“云海梯田杯”山地自行车爬坡赛</h1>
                            <div class="race-status">
                                <span data-bind="visible:status == 'signup'" class="signing" style="display: none;">报名中</span>
                                <span data-bind="visible:status == 'pre-signup'" class="will-start" style="display: none;">即将报名</span>
                                <span data-bind="visible:status == 'start'" class="raceing">进行中</span>
                                <span data-bind="visible:status == 'end'" class="end" style="display: none;">已结束</span>
                            </div>
                            <div class="time">
                                <div class="clear">
                                    <div class="fl">比赛时间：</div>
                                    <div class="race-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:start_time">2017-12-09 08:30</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:end_time">2017-12-09 12:08</span></p>
                                    </div>
                                </div>
                                <div class="clear">
                                    <div class="fl">报名时间：</div>
                                    <div class="sign-time fl">
                                        <p><i class="fl start"></i><span data-bind="dateFormat:signup_start_time">2017-10-23 11:20</span></p>
                                        <p><i class="fl end"></i><span data-bind="dateFormat:signup_end_time">2017-11-26 23:00</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <p>地点 : <span class="address" data-bind="attr:{title: address},text: address" title="元江县太阳城广场">元江县太阳城广场</span></p>
                            <p>主办方 : <span class="sponsor-name" data-bind="attr:{title: sponsor},text: sponsor" title="元江县文化旅游广电和体育局">元江县文化旅游广电和体育局</span>
                            </p>
                        </div>
                        <div class="race-info">
                            <div class="race-info-star">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:collect_num">3</span>
                            </div>
                            <div class="race-info-member">
                                <i class="race-info-icon"></i>
                                <span data-bind="text:views_num ">1957</span>
                            </div>
                        </div>
                    </li>
                </ul>
                <div data-bind="visible:!raceList().length" class="no-result" style="display: none;">
                    <p><img src="/player/public/images/no-found.png">抱歉，暂时找不到符合条件的活动记录</p>
                </div>
            </div>

	<div class="row">
      <div class="span4">
        <h4><small><a href="${ctx}/list-2${urlSuffix}" class="pull-right">更多&gt;&gt;</a></small>组织机构</h4>
		<ul><c:forEach items="${fnc:getArticleList(site.id, 2, 8, '')}" var="article">
			<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
		</c:forEach></ul>
      </div>
      <div class="span4">
        <h4> <small><a href="${ctx}/list-6${urlSuffix}" class="pull-right">更多&gt;&gt;</a></small>质量监督</h4>
		<ul><c:forEach items="${fnc:getArticleList(site.id, 6, 8, '')}" var="article">
			<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
		</c:forEach></ul>
      </div>
      <div class="span4">
        <h4><small><a href="${ctx}/list-10${urlSuffix}" class="pull-right">更多&gt;&gt;</a></small>政策法规</h4>
		<ul><c:forEach items="${fnc:getArticleList(site.id, 10, 8, '')}" var="article">
			<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
		</c:forEach></ul>
      </div>
    </div>
</body>
</html>