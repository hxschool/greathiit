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
	    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/top-bottom.css" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/css-rxxz.css" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/lrtk.css" type="text/css"/>

</head>
<body>

	<script type="text/javascript"
		src="${ctxStatic }/yingxin/js/backtop.js"></script>
	
    <div id="hd" class="o_h">
    <ul class="place">
        <li>当前位置：</li>
        <li><a href="/yingxing">首页</a>></li>
        <li><a href="/yingxin/list-${category.id }${urlSuffix}">入学须知</a></li>
    </ul>
    <ul class="all o_h">

			<c:forEach items="${articles}" var="article" varStatus="status">

				<li>
					<dl>
						<dt>0${status.index+1 }</dt>
						<dd>
							<a
								href="/yingxin/view-${article.category.id}-${article.id}${urlSuffix}">${fns:abbr(article.keywords,96)}</a>
						</dd>
						<dd>${fns:abbr(article.description,200)}</dd>

						<dd class="f_r">
							<a
								href="/yingxin/view-${article.category.id}-${article.id}${urlSuffix}">了解更多</a>
						</dd>
					</dl>
				</li>
			</c:forEach>
		</ul>
</div>
</body>
</html>