<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:title default="欢迎光临"/> - ${site.title} - 迎新系统</title>
	<%@include file="/WEB-INF/views/modules/yingxin/include/head.jsp" %>
	    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/top-bottom.css" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/css-index.css" type="text/css"/>
    
    <script type="text/javascript" src="${ctxStatic }/yingxin/js/jquery.SuperSlide.2.1.1.js"></script>
    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/lrtk.css" type="text/css"/>
	<sitemesh:head/>
</head>
<body>
	<header class="o_h">
    <div id="top">
    <div id="logo">
        <span>迎新网</span>
    </div>
    <div id="hxci" class="f_r">
        <p>哈尔滨信息工程欢迎你</p>
        <p>Welcome to HXCI</p>
    </div>
    <nav>
        <ul class="o_h">
            <li><a href="index.html">首页</a></li>
            <li><a href="sub-wsdl.html">网上登录</a></li>
            <li><a href="sub-rxxz.html">入学须知</a></li>
            <li><a href="#">了解哈信息</a></li>
            <li><a href="#">学在哈信息</a></li>
            <li><a href="#">帮困助学</a></li>
            <li><a href="sub-lyb.html">在线咨询</a></li>
        </ul>
    </nav>
        </div>
</header>
	
	
	
		<main>
			<sitemesh:body/>
		</main>
		
	
<footer>
    <div id="f-list">
    <span>版权所有© 刘鑫鑫</span>
    <span>地址：黑龙江省哈尔滨市宾县宾西镇宾西大学城9号</span>
    </div>
</footer>
</body>
</html>