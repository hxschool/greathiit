<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title default="欢迎光临" /> - ${site.title} - 选课系统</title>
<%@include file="/WEB-INF/views/modules/xuanke/include/head.jsp"%>
<sitemesh:head />
</head>
<script type="text/javascript">
	$(function() {
		$(".top-nav").find("li").each(function() {
			var a = $(this).find("a:first")[0];
			if (location.pathname.indexOf("view-${category.id }") > -1) {
				return;
			}
			if ($(a).attr("href") == location.pathname) {
				$(this).addClass("active");
			} else {
				$(this).removeClass("active");
			}
		});
	})
</script>
<body>
	<div class="top-menu-wrap">
		<div class="container-fluid" style="position: relative">
			<!-- logo -->
			<div class="logo hidden-xs">
				<a href="http://xuanke.greathiit.com"></a>
			</div>
			<!-- end logo -->
			<!-- 导航 -->

			<div class="top-nav navbar">
				<div class="navbar-header">
					<button class="navbar-toggle pull-left" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<nav role="navigation"
					class="collapse navbar-collapse bs-navbar-collapse active">
					<ul class="nav nav-pills ml30 mt10">
						<li class="active"><a href="/xuanke/index">首页</a></li>
						<li><a href="/xuanke/list-${category.id }.html">动态</a></li>
						<li><a href="/xuanke/kebiao">课表</a></li>
						<li><a href="/xuanke/history">选课记录</a></li>
					</ul>
				</nav>
			</div>
			<!-- end 导航 -->


			<div class="user-nav">



				<c:choose>
					<c:when test="${empty  fns:getUser().id}">
						<a class="login btn btn-normal btn-primary"
							href="http://login.greathiit.com/login?service=http://www.greathiit.com/xuanke">点击登录</a>
					</c:when>
					<c:otherwise>

						<button class=" btn btn-normal btn-primary">
							${fns:getUser().name}</button>
							
							<a href="http://www.greathiit.com/a/logout" class=" btn btn-normal btn-primary">
							退出</a>
					</c:otherwise>
				</c:choose>

			</div>

		</div>
	</div>

	<sitemesh:body />


	<footer id="footer">
		<div class="container">
			<div class="row hidden-xs"></div>
			<div class="copyright">Copyright © 2012-2013 选课系统 - Powered By
				哈尔滨信息工程学院 V1.1</div>
		</div>
	</footer>


</body>
</html>