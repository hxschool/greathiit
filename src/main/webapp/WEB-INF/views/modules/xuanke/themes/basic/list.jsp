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
								<li id="cate1"><a href="/xuanle/list-${category.id }.html">常见问题与解答</a></li>
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



				<div class="col-xs-12 col-md-9 main">
					<p class="main-title hidden-xs"></p>
					<div class="main__board  panel panel-default panel-body">
						<!--  <ul class=" nav nav-pills pb10 mb10 mt10">
                        <li class="active">
                          <a href="/index.php?m=&c=Index&a=dongtai">动态</a>
                        </li>
                        <li>
                          <a href="/index.php?m=&c=Index&a=zanart">超赞</a>
                        </li>

                        <li>
                            <a href="/index.php?m=&c=Index&a=gzart">关注</a>
                          </li>                    </ul> -->

						<c:forEach items="${articles}" var="article" varStatus="status">

							<c:set var="link"
								value="/xuanke/view-${article.category.id}-${article.id}${urlSuffix}"
								scope="session"></c:set>
							<c:if test="${!empty article.link }">
								<c:set var="link" value="${article.link}" scope="session"></c:set>
							</c:if>
							<div class="stream-list blog-stream">
								<section class="stream-list__item">
									<div class="blog-rank">
										<div class="votes plus">
											3<small>赞</small>
										</div>
										<div class="views hidden-xs">
											${article.hits } <small>浏览</small>
										</div>
									</div>
									<div class="summary ">

										<ul class="author list-inline">
											<li class="pull-right" title="${article.title}"><small
												class="glyphicon glyphicon-bookmark"></small> 1</li>
											<li><a href="${link }"><img
													class="avatar-20 mr10 hidden-xs"
													src="${ctxStatic }/xuanke/Addons/Avatar/default_32_32.png"
													alt="admin">${article.articleData.copyfrom } </a> <span
												class="split"></span> <fmt:formatDate
													value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
												<span class="split"></span> <code>
													<a href="${link }">${fns:abbr(article.title,96)}</a>
												</code></li>
										</ul>
										<h2 class="title">
											<a href="${link }">[<i class="icon-arrow-up"></i> 置顶]<span>${fns:abbr(article.title,96)}</span></a>
										</h2>


										<div class="sumcontent">
											<p class="excerpt wordbreak ">${article.description}</p>
										</div>
									</div>
								</section>
							</div>
						</c:forEach>


					</div>
				
				</div>
				

			</div>
		</div>
	</div>


</body>
</html>