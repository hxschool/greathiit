<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/modules/kaoqin/header.jsp"%>
<html>
<head>
<title>宿舍管理管理</title>

</head>
<body>


	

	<c:if test="${tab=='tab1'}">
		<div id="tab1"
			class="weui-tab__bd-item <c:if test="${tab=='tab1'}"> weui-tab__bd-item--active</c:if> ">
			<header class='demos-header'>
				<h1 class="demos-title">考勤</h1>
			</header>

			<div class='demos-content-padded'>
				<a href="${ctx}/dorm/kaoqin/list?ucDormBuild.id=1A"
					class="weui-btn weui-btn_primary">1A</a> <a
					href="${ctx}/dorm/kaoqin/list?ucDormBuild.id=1B"
					class="weui-btn weui-btn_primary">1B</a> <a
					href="${ctx}/dorm/kaoqin/list?ucDormBuild.id=2A"
					class="weui-btn weui-btn_primary">2A</a> <a
					href="${ctx}/dorm/kaoqin/list?ucDormBuild.id=2B"
					class="weui-btn weui-btn_primary">2B</a> <a
					href="${ctx}/dorm/kaoqin/list?ucDormBuild.id=3"
					class="weui-btn weui-btn_warn">3</a> <a
					href="${ctx}/dorm/kaoqin/list?ucDormBuild.id=4"
					class="weui-btn weui-btn_warn">4</a> <a
					href="${ctx}/dorm/kaoqin/list?ucDormBuild.id=5A"
					class="weui-btn weui-btn_primary">5A</a> <a
					href="${ctx}/dorm/kaoqin/list?ucDormBuild.id=5B"
					class="weui-btn weui-btn_primary">5B</a>
			</div>
	</c:if>
	</div>
	<c:if test="${tab=='tab2'}">

			<div id="tab2"
			class="weui-tab__bd-item <c:if test="${tab=='tab2'}"> weui-tab__bd-item--active</c:if> ">
			<header class='demos-header'>
				<h1 class="demos-title">缺勤信息</h1>
			</header>


			<div class='demos-content-padded'>
				<a href="${ctx}/dorm/kaoqin/query?dormBuildId=1A&detail=2"
					class="weui-btn weui-btn_primary">1A</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=1B&detail=2"
					class="weui-btn weui-btn_primary">1B</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=2A&detail=2"
					class="weui-btn weui-btn_primary">2A</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=2B&detail=2"
					class="weui-btn weui-btn_primary">2B</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=3&detail=2"
					class="weui-btn weui-btn_warn">3</a> <a
					href="${ctx}/dorm/kaoqin/duty?dormBuildId=4&detail=2"
					class="weui-btn weui-btn_warn">4</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=5A&detail=2"
					class="weui-btn weui-btn_primary">5A</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=5B&detail=2"
					class="weui-btn weui-btn_primary">5B</a>
			</div>
		</div>
	</c:if>
	<c:if test="${tab=='tab3'}">
		<div id="tab3"
			class="weui-tab__bd-item <c:if test="${tab=='tab3'}"> weui-tab__bd-item--active</c:if> ">
			<header class='demos-header'>
				<h1 class="demos-title">请假信息</h1>
			</header>
			<div class='demos-content-padded'>
				<a href="${ctx}/dorm/kaoqin/query?dormBuildId=1A&detail=3"
					class="weui-btn weui-btn_primary">1A</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=1B&detail=3"
					class="weui-btn weui-btn_primary">1B</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=2A&detail=3"
					class="weui-btn weui-btn_primary">2A</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=2B&detail=3"
					class="weui-btn weui-btn_primary">2B</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=3&detail=3"
					class="weui-btn weui-btn_warn">3</a> <a
					href="${ctx}/dorm/kaoqin/duty?dormBuildId=4&detail=3"
					class="weui-btn weui-btn_warn">4</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=5A&detail=3"
					class="weui-btn weui-btn_primary">5A</a> <a
					href="${ctx}/dorm/kaoqin/query?dormBuildId=5B&detail=3"
					class="weui-btn weui-btn_primary">5B</a>
			</div>
		</div>
	</c:if>
	<c:if test="${tab=='tab4'}">
		<div id="tab4"
			class="weui-tab__bd-item <c:if test="${tab=='tab4'}"> weui-tab__bd-item--active</c:if> ">
			<h1>开发中</h1>
		</div>
	</c:if>





	<div class="weui-tabbar">
		<a href="${ctx}/dorm/kaoqin?tab=tab1"
			<c:choose>
<c:when test="${tab=='tab1'}"> 
         class="weui-tabbar__item weui-bar__item--on" 
   </c:when>
   <c:otherwise>
     class="weui-tabbar__item"
   </c:otherwise>
</c:choose>>
			<span class="weui-badge"
			style="position: absolute; top: -.4em; right: 1em;">8</span>
			<div class="weui-tabbar__icon">
				<img src="${ctxStatic}/jquery-weui/images/icon_nav_button.png"
					alt="">
			</div>
			<p class="weui-tabbar__label">考勤</p>
		</a> <a href="${ctx}/dorm/kaoqin?tab=tab2"
			<c:choose>
 <c:when test="${tab=='tab2'}"> 
         class="weui-tabbar__item weui-bar__item--on" 
   </c:when>
   <c:otherwise>
     class="weui-tabbar__item"
   </c:otherwise>
</c:choose>>
			<div class="weui-tabbar__icon">
				<img src="${ctxStatic}/jquery-weui/images/icon_nav_msg.png" alt="">
			</div>
			<p class="weui-tabbar__label">缺勤信息</p>
		</a> <a href="${ctx}/dorm/kaoqin?tab=tab3"
			<c:choose>
 <c:when test="${tab=='tab3'}"> 
         class="weui-tabbar__item weui-bar__item--on" 
   </c:when>
   <c:otherwise>
     class="weui-tabbar__item"
   </c:otherwise>
</c:choose>>
			<div class="weui-tabbar__icon">
				<img src="${ctxStatic}/jquery-weui/images/icon_nav_article.png"
					alt="">
			</div>
			<p class="weui-tabbar__label">请假记录</p>
		</a> <a href="${ctx}/dorm/kaoqin?tab=tab4"
			<c:choose>
 <c:when test="${tab=='tab4'}"> 
         class="weui-tabbar__item weui-bar__item--on" 
   </c:when>
   <c:otherwise>
     class="weui-tabbar__item"
   </c:otherwise>
</c:choose>>
			<div class="weui-tabbar__icon">
				<img src="${ctxStatic}/jquery-weui/images/icon_nav_cell.png" alt="">
			</div>
			<p class="weui-tabbar__label">个人信息</p>
		</a>

	</div>

</body>
</html>