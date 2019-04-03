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

		<div class="container-fluid">
			<div class="row">
				<%@include file="/WEB-INF/views/modules/xuanke/include/left.jsp"%>

				<div class="col-xs-12 col-md-9 main">
					<p class="main-title hidden-xs"></p>
					<div class="main__board  panel panel-default panel-body">
						
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