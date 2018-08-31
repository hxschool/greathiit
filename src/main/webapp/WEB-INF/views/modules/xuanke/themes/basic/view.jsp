<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="xuanke_default_${site.theme}" />
<meta name="description"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.description} -选课系统" />
<meta name="keywords"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.keywords} -选课系统" />


</head>
<body>


	<c:set var="articleLink"
		value="/xuanke/view-${article.category.id}-${article.id}${urlSuffix}"
		scope="session"></c:set>
	<c:if test="${!empty article.link }">
		<c:set var="articleLink" value="${article.link}" scope="session"></c:set>
	</c:if>



	<div class="wrap maincontent">

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-md-3 mt30 side hidden-xs ">

					<div class="widget-box clearfix"
						style="margin: 3px; border-style: solid; border-color: #CC9900; text-align: center;">
						<script type="text/javascript" charset="utf-8"
							src="${ctxStatic}/xuanke/home/Js/showtime.js"></script>
					</div>

					<div class="widget-box clearfix">
						<h2 class="h4 widget-box__title">分类列表(1)</h2>
						<div class="pcss3mm ">
							<ul id="pcss3mm" class="nav nav-pills" role="tablist">
								<li id="cate1"><a href="/xuanke/list-${category.id }.html">常见问题与解答</a></li>
								<li id="cate2"><a href="tel:18801029695">反馈消息</a></li>
							</ul>
						</div>
					</div>



					<div class="widget-box no-border">
						<h2 class="h4 widget-box__title">最新公告</h2>
						<ul class="widget-links list-unstyled">
							<c:forEach items="${articles}" var="article" varStatus="status">
								<c:set var="link"
									value="/xuanke/view-${article.category.id}-${article.id}${urlSuffix}"
									scope="session"></c:set>
								<c:if test="${!empty article.link }">
									<c:set var="link" value="${article.link}" scope="session"></c:set>
								</c:if>
								<li class="widget-links__item"><a href="${link }"
									title="${article.title }">${article.title }</a> <small
									class="text-muted"> ， ${article.hits } 浏览 </small></li>
							</c:forEach>

						</ul>
					</div>


				</div>



				<div class="col-xs-12 col-md-9 main mt30">

					<div class="post-topheader">
						<div class="container">
							<div class="row">
								<div class="col-md-6">
									<ol class="breadcrumb">
										<li><a href="${articleLink }">${article.articleData.copyfrom }</a></li>
										<li class="active">文章详情</li>
									</ol>
									<h1 class="h3 title" id="articleTitle">
										<a href="${articleLink }"> ${article.title }</a>
									</h1>
									<div class="author" data-username="GitCafe"
										data-userslug="gitcafe" data-useravatar="http://sfault-avatar.b0.upaiyun.com/380/542/3805425491-5449782ad67ea_huge128">
										
										<strong class="text-black mr10"></strong>
										<fmt:formatDate value="${article.createDate}"
											pattern="yyyy-MM-dd HH:mm:ss" />
										 点击:${article.hits }
										 
										来源:${article.articleData.copyfrom }
									</div>
								</div>
								
							</div>
						</div>
					</div>

					<div id="description" class="article fmt">
					
						${article.articleData.content }
						
					</div>

					<div class="clearfix">
						<ul class="taglist--inline pull-left">
						</ul>

						<ul class="pull-right article-operation list-inline"></ul>
					</div>

					<ul class="list-unstyled text-muted mt30  clearfix">

					</ul>
				</div>
			</div>
		</div>
	</div>


</body>
</html>