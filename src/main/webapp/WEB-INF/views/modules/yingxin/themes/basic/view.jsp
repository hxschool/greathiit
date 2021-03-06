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
	<!-- <!-- 整站通用的头部及导航条 -->
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<ol class="breadcrumb"
					style="margin-top: 10px; margin-bottom: 10px;">
					<li><a href="http://yingxin.greathiit.com">首页</a></li>
					<li><a
						href="../yingxin/list-${article.category.id}${urlSuffix}">${article.category.name }</a></li>
				</ol>
			</div>
		</div>
	</div>

	<div class="main-container">
		<div class="container">
			<div class="row main-container-row" style="position: relative">
				<div class="col-xs-12 col-sm-9 news-article">
					<div class="article-title">
						<h1 class="h2" style="text-align: center;">${article.title }</h1>
					</div>
					<div class="article-content">
					
					           <center><time><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></time>   来源： ${article.articleData.copyfrom }   　点击：${article.hits }</center>
        ${article.articleData.content }
					</div>
				</div>
				<div class="col-sm-3">
					<div id="sidebar">
						<div id="sidebar-content" class="sidebar-right">
							<h4 class="hidden-xs">快速通道</h4>
							<div class="sidebar-contact hidden-xs">
								<a class="sidebar-phone" title="电话咨询">0451-58607916</a> <a
									class="sidebar-qq" target="_blank"
									href="http://wpa.qq.com/msgrd?v=3&uin=773152&site=qq&menu=yes">点击QQ咨询</a>
								<a class="sidebar-chat web-chat" href="javascript:;">点击在线咨询</a>
							</div>
							<h4 class="hidden-xs">常用电话</h4>

							<ul>
								<li><a href="tel:0451-58607888" title="招生电话">招生电话:0451-58607888</a></li>

							</ul>

						</div>
					</div>
				</div>
				<p class="hidden-xs" id="right-line"></p>
			</div>
		</div>
	</div>


</body>
</html>