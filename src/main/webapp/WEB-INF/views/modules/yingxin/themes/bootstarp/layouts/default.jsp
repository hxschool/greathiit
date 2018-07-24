<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title default="欢迎光临" /> - ${site.title} - 迎新系统</title>
<%@include file="/WEB-INF/views/modules/yingxin/include/head.jsp"%>
<sitemesh:head />
</head>
<body>
	
<div id="header">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" style="padding:0 0 0 15px;" href="#"><img alt="Brand" style="max-width:260px;" src="${ctxStatic}/yingxin2/logo.png"></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#">首页 <span class="sr-only">(current)</span></a><p class="line-top hidden-xs"></p></li>
                           
                                        	<c:forEach items="${categorys}" var="category">
												<li><a href="/yingxin/list-${category.id}${urlSuffix}">${category.name }</a></li>
											</c:forEach>
											
                                        <li class="hidden-sm"><a href="#">网上登录</a></li>
                                    </ul>
                            </div>
        </div>
    </nav>
</div>

<sitemesh:body />


<!-- 我们的客户 -->
<div class="index-row our-client">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 main-title">
                <h2 class="h1">我们的客户</h2>

                <p class="line line-big"></p>

                <h2>
                    <small>他们与中岚建立了合作关系</small>
                </h2>
            </div>
            <c:forEach items="${links}" var="link">
            <div class="col-xs-4 col-sm-3 col-md-2 our-client-item">
                <div class="our-client-logo">
                    <img class="img-responsive center-block" src="${link.image }" alt="${link.title }">
                </div>
            </div>
           </c:forEach>
        </div>
    </div>
</div>
<!-- 我们的客户 -->
	
	
	
<!-- 数字滚动插件 -->
<script type="text/javascript" src="${ctxStatic}/yingxin2/countUp.js"></script>
<script type="text/javascript">
    $(function () {
        /**通用-banner大图自定义缩放**/
        var zoomWidth = 992; //缩放阀值992px, 即所有小于992px的视口都会对原图进行缩放, 只是缩放比例不同
        var maxWidth = 1920; //最大宽度1920px
        var ratio = 1; //缩放比例
        var viewWidth = window.innerWidth; // 视口宽度
        var zoomSlider = function () {
            if (viewWidth < 768) { //当视口小于768时(移动端), 按992比例缩放
                ratio = viewWidth / zoomWidth; //视口宽度除以阀值, 计算缩放比例
            } else if (viewWidth < zoomWidth) { //当视口界于768与992之间时, bootstrap主宽度为750, 这区间图片缩放比例固定.
                ratio = zoomWidth / (zoomWidth + (zoomWidth - 750));
            } else { // PC端不缩放
                ratio = 1;
            }
            //ratio = viewWidth / zoomWidth; //视口宽度除以阀值, 计算缩放比例
            //ratio = (ratio<=1) ? ratio : 1; //如果比例值大于1, 说明视口宽度高于阀值, 则不进行任何缩放
            var width = maxWidth * ratio; //缩放宽度
            $(".my-slide img").each(function () {
                $(this).css({
                    "width": width,
                    "max-width": width,
                    "margin-left": -(width - viewWidth) / 2
                }); //图片自适应居中, 图片宽度与视口宽度差除以2的值, 设置为负margin
            });
        }
        /**通用-我们的成绩等数字滚动特效**/
        var numOptions = {
            useEasing: true,
            useGrouping: true,
            separator: ',',
            decimal: '.',
            prefix: '',
            suffix: ''
        }
        var numGroup = new Array(
                new CountUp("sum-apply", 0, 1044, 0, 2.5, numOptions),
                new CountUp("sum-rate", 0, 25.6, 1, 2.5, numOptions),
                new CountUp("sum-urgent", 0, 5262, 0, 2.5, numOptions),
                new CountUp("urgent-rate", 0, 473, 0, 2.5, numOptions)
        );
        var runNumber = function () {
            $('.run-number').each(function () {
                var oTop = $(this).offset().top;
                var sTop = $(window).scrollTop();
                var oHeight = $(this).height();
                var oIndex = $(this).index('.run-number');
                //console.log(oTop+'\r\n'+sTop+'\r\n'+oHeight+'\r\n'+$(window).height());
                if (oTop >= sTop && (oTop + (oHeight / 2)) < (sTop + $(window).height())) {
                    numGroup[oIndex].start();
                    //console.log('元素'+$(this).index('.run-number')+'可见');
                } else {
                    //console.log('元素'+$(this).index('.run-number')+'不可见');
                }
            });
        }

        zoomSlider(); //页面加载时初始化并检查一次.
        runNumber(); //页面加载时判断一次
        /**视口发生变化时的事件**/
        $(window).resize(function () {
            viewWidth = window.innerWidth; // 重置视口宽度
            zoomSlider();//判断是否绽放banner
            runNumber();//判断是否执行动画
        });
        /**滚动事件**/
        $(window).scroll(function () {
            runNumber();
        });

        //首页-我们的服务
        $('.card-item').each(function () {
            $(this).mouseover(function () {
                $(this).addClass('card-active');
                $(this).siblings().removeClass('card-active');
                $(this).find(".btn").addClass('btn-outline-inverse').removeClass('btn-outline-blue');
                $(this).siblings().find(".btn").addClass('btn-outline-blue').removeClass('btn-outline-inverse');
            });
        });

    });
</script>

<!-- 返回顶部滑块 -->
<div id="clan-slider">
    <ul>
        <li class="hidden-xs">
            <a id="slider-chat" class="web-chat" href="javascript:;"></a>
            <div class="clan-slider-tips">
                在线咨询
            </div>
        </li>
        <li class="hidden-xs">
            <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=274027502&amp;site=qq&amp;menu=yes" id="slider-qq"></a>
            <div class="clan-slider-tips">
                QQ咨询
            </div>
        </li>
        <li class="hidden-xs">
            <a id="slider-phone" href="javascript:void(0);"></a>
            <div class="clan-slider-tips">
                010-64648731
            </div>
        </li>
        <li class="hidden-xs">
            <a id="slider-wechat" href="javascript:void(0);"></a>
            <div class="clan-slider-tips-wechat">
                <img src="${ctxStatic}/yingxin2/wechat.png">
            </div>
        </li>
        <li><a id="slider-goTop" href="javascript:void(0);" style="display: none;"></a></li>
        <!--
        <li class="visible-xs-block">
            <a id="slider-menu" href="javascript:void(0);"></a>
        </li>
        -->
    </ul>
</div>
<!-- 返回顶部滑块 --><!-- 整站通用的尾部 -->
<div id="sidebar-bg" style="display: none;"></div>

<!-- 通用页脚 -->
<div id="footer">
    <div class="container">
        <div class="row">
            <div class="col-xs-6 col-sm-2 footer-item">
                <div class="footer-list">
                    <h4>常用工具</h4>
                    <ul>
                        <li><a href="http://www.clantrip.com/code" target="_blank">电码查询</a></li>
                        <li><a href="http://www.clantrip.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=12&amp;id=36#track">护照追踪</a></li>
                        <li><a href="http://www.clantrip.com/index.php?m=content&amp;c=index&amp;a=lists&amp;catid=14">资料下载</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-6 col-sm-2 footer-item">
                <div class="footer-list">
                    <h4>快捷裢接</h4>
                    <ul>
                        <li><a href="http://www.clantrip.com/index.php?m=content&amp;c=index&amp;a=lists&amp;catid=10">签证资讯</a></li>
                        <li><a href="http://www.clantrip.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=7&amp;id=1">签证指南</a></li>
                        <li><a href="http://www.clantrip.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=37">常见问题</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-6 col-sm-2 footer-item">
                <div class="footer-list">
                    <h4>关于我们</h4>
                    <ul>
                        <li><a href="http://www.clantrip.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=11&amp;id=17">关于中岚</a></li>
                        <li><a href="http://www.clantrip.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=11&amp;id=19">服务协议</a></li>
                        <li><a href="javascript:AddFavorite('%E7%BE%8E%E5%9B%BD%E7%AD%BE%E8%AF%81-%E4%B8%AD%E5%B2%9A','http://www.clantrip.com/');">收藏本站</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-6 col-sm-2 footer-item">
                <div class="footer-wechat">
                    <img class="img-responsive" src="${ctxStatic}/yingxin2/wechat.png">
                    <p>微信<sapn class="hidden-sm">号:</sapn>18401238365</p>
                </div>
            </div>
            <div class="col-xs-12 col-sm-4 footer-item footer-item-last">
                <div class="footer-contact">
                    <h2><img src="${ctxStatic}/yingxin2/pc-footer-phone.png">010-64648731</h2>
                    <h2><img src="${ctxStatic}/yingxin2/pc-footer-qq.png">274027502</h2>
                    <h2><img src="${ctxStatic}/yingxin2/pc-footer-mob.png">18401238365</h2>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div id="copyright">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <p>${site.copyright} <a target="_blank" href="http://www.miitbeian.gov.cn/">黑ICP备05007064号-3</a></p>

              
            </div>
        </div>
    </div>
</div>
<!-- 通用页脚 -->
<!-- 手机端底部 -->
<div id="mob-bottom" class="visible-xs-block">
    <div class="container">
        <div class="row">
            <div class="col-xs-3 mob-bottom-item">
                <a href="tel:01064648731">
                    <img class="img-responsive center-block" src="${ctxStatic}/yingxin2/mob-footer-phone.png">
                    <span>电话咨询</span>
                </a>
            </div>
            <div class="col-xs-3 mob-bottom-item">
                <a href="tel:18401238365">
                    <img class="img-responsive center-block" src="${ctxStatic}/yingxin2/mob-footer-mob.png">
                    <span>紧急电话</span>
                </a>
            </div>
            <div class="col-xs-3 mob-bottom-item">
                <a href="#">
                    <img class="img-responsive center-block web-chat" src="${ctxStatic}/yingxin2/mob-footer-chat.png">
                    <span>在线咨询</span>
                </a>
            </div>
            <div class="col-xs-3 mob-bottom-item">
                <a href="sms:18401238365">
                    <img class="img-responsive center-block" src="${ctxStatic}/yingxin2/mob-footer-msm.png">
                    <span>短信咨询</span>
                </a>
            </div>
        </div>
    </div>
</div>
<!-- 手机端底部 -->
<!-- 整站通用的尾部 -->


<script type="text/javascript" src="${ctxStatic}/yingxin2/common.js"></script>



	<ins id="newBridge">
		<!-- target: nodeBoard -->
		<ins
			class="nb-nodeboard-base nb-nodeboard-position-base nb-nodeboard-left-bottom nb-hide"
			id="nb_nodeboard">
			<ins class="nb-nodeboard-contain-base nb-nodeboard-contain">
				<ins class="nb-nodeboard-top nb-nodeboard-top-0">
					<ins class="nb-head-title">请您留言</ins>
					<ins class="nb-nodeboard-close" id="nb_nodeboard_close"
						data-type="min"></ins>
				</ins>
				<ins id="nb_nodeboard_text" class="nb-nodeboard-text">
					<p class="nb-nodeboard-company">哈尔滨信息工程学院</p>
					<p class="nb-nodeboard-link">010-64648731</p>
				</ins>
				<form id="nb_nodeboard_form" autocomplete="off"
					class="nb-board-form"
					action="//p.qiao.baidu.com/cps//bookmanage/saveBook.action?userId=20073939"
					method="post" accept-charset="utf-8">
					<ins id="nb_nodeboard_set" class="nb-nodeboard-set">
						<ins class="nb-nodeboard-content">
							<textarea id="nb-nodeboard-set-content-js" name="content"
								data-ph="请在此输入留言内容，我们会尽快与您联系。（必填）"
								placeholder="请在此输入留言内容，我们会尽快与您联系。（必填）"
								class="nb-nodeboard-set-content"></textarea>
						</ins>
						<ins class="nb-nodeboard-name" style="z-index: 4;">
							<ins class="nb-nodeboard-icon nodeName"></ins>
							<input id="nb_nodeboard_set_name" data-write="0"
								name="visitorName" maxlength="30" class="nb-nodeboard-input"
								data-ph="姓名" placeholder="姓名" type="text">
						</ins>
						<ins class="nb-nodeboard-name" id="nb_nodeboard_phone"
							style="z-index: 3;">
							<ins class="nb-nodeboard-icon nodePhone"></ins>
							<input id="nb_nodeboard_set_phone" name="visitorPhone"
								maxlength="30" class="nb-nodeboard-input" data-write="1"
								data-ph="电话（必填）" placeholder="电话（必填）" type="text">
						</ins>
						<ins class="nb-nodeboard-name" id="nb_nodeboard_mail"
							style="z-index: 2;">
							<ins class="nb-nodeboard-icon nodeMail"></ins>
							<input id="nb_nodeboard_set_email" name="visitorEmail"
								maxlength="50" class="nb-nodeboard-input" data-write="0"
								data-ph="邮箱" placeholder="邮箱" type="text">
						</ins>
						<ins class="nb-nodeboard-name" style="z-index: 1;">
							<ins class="nb-nodeboard-icon nodeAddress"></ins>
							<input id="nb_nodeboard_set_address" name="visitorAddress"
								class="nb-nodeboard-input" maxlength="50" data-write="0"
								data-ph="地址" placeholder="地址" type="text">
						</ins>
					</ins>
				</form>
				<ins class="nb-nodeboard-success" id="nb_nodeboard_success">
					<ins class="nb-success-box">
						<ins class="nb-success-icon"></ins>
						<ins class="nb-success-title" id="nb_node_messagetitle">感谢留言</ins>
						<ins class="nb-success-content" id="nb_node_messagecontent">我们会尽快与您联系</ins>
						<ins id="sucess_close" class="nb-sucess-close">关闭</ins>
					</ins>
				</ins>
				<ins class="nb-nodeboard-send" id="nb_node_contain">
					<ins id="nb_nodeboard_send"
						class="nb-nodeboard-send-btn nb-nodeboard-send-btn-0">发送</ins>
				</ins>
			</ins>
		</ins>
		<!-- end -->
		<!-- target: invite -->
		<ins
			class="nb-invite-wrap nb-invite-wrap-base nb-position-base nb-middle  "
			id="nb_invite_wrap"
			style="width: 400px; display: none; margin-left: -200px; margin-top: -88px;">
			<ins class="nb-invite-body nb-invite-skin-0" style="height: 175px;">
				<ins class="nb-invite-tool nb-invite-tool-base" id="nb_invite_tool"
					style=""></ins>
				<ins class="nb-invite-text nb-invite-text-base">
					<ins class="nb-invite-welcome nb-invite-welcome-base nb-show"
						id="nb_invite_welcome">
						<p style="color: #fff">欢迎来到本网站，请问有什么可以帮您？</p>
					</ins>
				</ins>
				<ins class="nb-invite-btn-base nb-invte-btns-0">
					<ins class="nb-invite-cancel nb-invite-cancel-base"
						id="nb_invite_cancel">稍后再说</ins>
					<ins class="nb-invite-ok nb-invite-ok-base" id="nb_invite_ok">现在咨询</ins>
				</ins>
			</ins>
		</ins>
		<!-- end -->
	</ins>
</body>
</html>