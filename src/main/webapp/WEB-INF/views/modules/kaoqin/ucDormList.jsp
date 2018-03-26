<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/modules/kaoqin/header.jsp"%>
<html>
<head>
<title>宿舍管理管理</title>

</head>
<body ontouchstart>


	<div class="weui-tab">

		<div class="weui-tab__bd">
			<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
				<div class="weui-cells">
					<c:forEach var="item" items="${list}" varStatus="status">
						<a class="weui-cell weui-cell_access"
							href="${ctx}/dorm/kaoqin/init?id=${item.id}">
							<div class="weui-cell__bd">
								<p>${item.dormNumber}</p>
							</div>
							<div class="weui-cell__ft"></div>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>



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
	</div>


</body>
</html>