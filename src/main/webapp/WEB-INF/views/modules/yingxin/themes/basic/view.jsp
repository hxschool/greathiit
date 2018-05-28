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
    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/css-nr-bdxz.css" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/lrtk.css" type="text/css"/>
</head>
<body>

	<script type="text/javascript"
		src="${ctxStatic }/yingxin/js/backtop.js"></script>
	
    <div id="hd" class="o_h">
        <ul class="place">
            <li>当前位置：</li>
            <li><a href="../yingxin">首页</a>></li>
            <li><a href="../yingxin/list-${article.category.id}${urlSuffix}">${article.category.name }</a>>${article.title }</li>
        </ul>
        <article>
        <h2>${article.title }</h2>

            <center><time><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></time>   来源： ${article.articleData.copyfrom }   　点击：${article.hits }</center>
        ${article.articleData.content }
        </article>
    </div>
</body>
</html>