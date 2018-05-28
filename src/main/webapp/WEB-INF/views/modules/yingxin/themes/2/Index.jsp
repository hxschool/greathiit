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