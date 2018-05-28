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
	<!--回到顶部-->
	<script type="text/javascript"
		src="${ctxStatic }/yingxin/js/backtop.js"></script>
	<div class="dong">
		<marquee behavior="left" direction="10">
			<span class="red">友情提醒: </span> 为避免影响到9月4、5日的正常报到，2018级新生敬请于报到前（截止至
			2017年9月4日）缴清学杂费等。
		</marquee>
		<ul class="m-list1">
			<li><img src="${ctxStatic }/yingxin/image/banner2.jpg" alt="" /></li>
			<li><img src="${ctxStatic }/yingxin/image/banner1.png" alt="" /></li>
			<li><img src="${ctxStatic }/yingxin/image/banner3.png" alt="" /></li>
			<li><img src="${ctxStatic }/yingxin/image/banner4.png" alt="" /></li>
			<li><img src="${ctxStatic }/yingxin/image/banner5.png" alt="" /></li>
		</ul>
		<nav class="nav-roundslide">
			<a class="prev" href="#"> <span class="icon-wrap"></span>
				<h3>上一页</h3>
			</a> <a class="next" href="#"> <span class="icon-wrap"></span>
				<h3>下一页</h3>
			</a>
		</nav>
	</div>
	<script type="text/javascript">
        jQuery(".dong").hover(function(){
                    jQuery(this).find(".prev,.next").stop(true,true).fadeTo("show",0.9) }
                ,function(){ jQuery(this).find(".prev,.next").fadeOut() });
        jQuery(".dong").slide({ mainCell:".m-list1",
            effect: "leftLoop", autoPlay:true, delayTime:400,trigger:"click",pnLoop:true});
    </script>
	<ul class="bd">
		<li>
			<dl class="bdsm">
				<dt>报到说明</dt>
				<div class="layer">
					<dd>1、网上报到：须在到校前完成网上报到；（密码详见登录说明）</dd>
					<dd>2、现场报到：请按录取通知书要求在指定时间内到校办理现场报到相关手续。 （报道详见入学须知）</dd>
					<dd>
						<a href="sub-wsdl.html">网上登录</a>
					</dd>
			</dl>
		</li>
		<li>
			<dl class="xszn">
				<dt>新生指南</dt>
				<div class="layer">
					<c:forEach items="${articles}" var="article">

						<dd>
							<a href="yingxin/view-${article.category.id}-${article.id}${urlSuffix}">${fns:abbr(article.title,96)} </a>
						</dd>
					</c:forEach>


				</div>
			</dl>
		</li>
	</ul>
	<ul class="m-yqlj o_h">
		<li>校内友情链接</li>
		<li><a href="http://www.hxci.com.cn/">哈尔滨信息工程学院</a></li>
		<li><a href="#">哈尔滨华夏中等专业学校</a></li>
		<li><a href="#">黑龙江省招生考试信息港</a></li>
		<li><a href="#">教务处</a></li>
		<li><a href="#">学生处</a></li>
		<li><a href="#">图书馆</a></li>
	</ul>
</body>
</html>