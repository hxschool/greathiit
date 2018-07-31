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
$(function () {
    $(".nav").find("li").each(function () {
        var a = $(this).find("a:first")[0];
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
	<div class="container" style="position: relative"><!-- logo -->
		<div class="logo hidden-xs"><a href="/index.php?m=&c=Index&a=index"></a></div>
		<!-- end logo --> <!-- 导航 -->

		<div class="top-nav navbar">
			<div class="navbar-header">
				<button class="navbar-toggle pull-left" data-toggle="collapse"	data-target=".navbar-collapse"><span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span></button>
			</div>
				<nav role="navigation"
					class="collapse navbar-collapse bs-navbar-collapse active">
					<ul class="nav nav-pills ml30 mt10">
						<li class="active"><a  href="#">首页</a></li>
						<li><a href="#">动态</a></li>
						<li><a href="#">选课</a></li>
					</ul>
				</nav>
			</div><!-- end 导航 -->

		
			<div class="user-nav"><a class="login btn btn-normal btn-primary"
				href="/index.php?m=&c=User&a=login">登录</a> <a
				class="register btn btn-normal btn-success"
				href="/index.php?m=&c=User&a=register">注册</a>
			</div>
		<div class="search-box  "><!-- 搜索框 -->
			<form class="navbar-search" action="/index.php?m=&c=Index&a=search" id="global_search_form" method="post">
				<input	class="form-control search-query" type="text" placeholder="搜索" autocomplete="on" name="keyword" id="keyword"	value="">
				<span title="搜索"	id="global_search_btns" onclick="$('#global_search_form').submit();"><i class="icon icon-search"></i></span>
			</form>
		</div><!-- end 搜索框 -->
	</div>
</div>

<sitemesh:body />


<footer id="footer">
<div class="container">
	<div class="row hidden-xs">
			</div>
	<div class="copyright">
		${site.copyright} -选课系统 	</div>
</div>
</footer>


</body>
</html>