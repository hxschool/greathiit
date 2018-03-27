<%@ page contentType="text/html;charset=UTF-8" %>
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
			 <div class='demos-content-padded'>
				
				<c:forEach var="item" items="${list}" varStatus="status">
       <a href="${ctx}/dorm/kaoqin/init?id=${item.id}" class="weui-btn weui-btn_primary"> ${item.dormNumber} </a>
</c:forEach> 
				<a href="?ucDormBuild.id=1A" class="weui-btn weui-btn_primary">1A</a>
				<a href="?ucDormBuild.id=1B" class="weui-btn weui-btn_primary">1B</a>
				<a href="?ucDormBuild.id=2A" class="weui-btn weui-btn_primary">2A</a>
				<a href="?ucDormBuild.id=2B" class="weui-btn weui-btn_primary">2B</a>
				<a href="?ucDormBuild.id=3" class="weui-btn weui-btn_warn">3</a>
				<a href="?ucDormBuild.id=4" class="weui-btn weui-btn_warn">4</a>
				<a href="?ucDormBuild.id=5A" class="weui-btn weui-btn_primary">5A</a>
				<a href="?ucDormBuild.id=5B" class="weui-btn weui-btn_primary">5B</a>
			</div>
        </div>
        <div id="tab2" class="weui-tab__bd-item">
          <div class='demos-content-padded'>
				<a href="kaoqin?ucDormBuild.id=1A" class="weui-btn weui-btn_primary">1A</a>
				<a href="kaoqin?ucDormBuild.id=1B" class="weui-btn weui-btn_primary">1B</a>
				<a href="kaoqin?ucDormBuild.id=2A" class="weui-btn weui-btn_primary">2A</a>
				<a href="kaoqin?ucDormBuild.id=2B" class="weui-btn weui-btn_primary">2B</a>
				<a href="kaoqin?ucDormBuild.id=3" class="weui-btn weui-btn_warn">3</a>
				<a href="kaoqin?ucDormBuild.id=4" class="weui-btn weui-btn_warn">4</a>
				<a href="kaoqin?ucDormBuild.id=5A" class="weui-btn weui-btn_primary">5A</a>
				<a href="kaoqin?ucDormBuild.id=5B" class="weui-btn weui-btn_primary">5B</a>
			</div>
        </div>
        <div id="tab3" class="weui-tab__bd-item">
          <div class='demos-content-padded'>
				<a href="kaoqin?ucDormBuild.id=1A" class="weui-btn weui-btn_primary">1A</a>
				<a href="kaoqin?ucDormBuild.id=1B" class="weui-btn weui-btn_primary">1B</a>
				<a href="kaoqin?ucDormBuild.id=2A" class="weui-btn weui-btn_primary">2A</a>
				<a href="kaoqin?ucDormBuild.id=2B" class="weui-btn weui-btn_primary">2B</a>
				<a href="kaoqin?ucDormBuild.id=3" class="weui-btn weui-btn_warn">3</a>
				<a href="kaoqin?ucDormBuild.id=4" class="weui-btn weui-btn_warn">4</a>
				<a href="kaoqin?ucDormBuild.id=5A" class="weui-btn weui-btn_primary">5A</a>
				<a href="kaoqin?ucDormBuild.id=5B" class="weui-btn weui-btn_primary">5B</a>
			</div>
        </div>
		 <div id="tab4" class="weui-tab__bd-item">
          <h1>页面si</h1>
        </div>
		
      </div>
    
      

      <div class="weui-tabbar">
        <a href="#tab1" class="weui-tabbar__item weui-bar__item--on">
          <span class="weui-badge" style="position: absolute;top: -.4em;right: 1em;">8</span>
          <div class="weui-tabbar__icon">
            <img src="./images/icon_nav_button.png" alt="">
          </div>
          <p class="weui-tabbar__label">考勤</p>
        </a>
        <a href="#tab2" class="weui-tabbar__item">
          <div class="weui-tabbar__icon">
            <img src="./images/icon_nav_msg.png" alt="">
          </div>
          <p class="weui-tabbar__label">考勤记录</p>
        </a>
        <a href="#tab3" class="weui-tabbar__item">
          <div class="weui-tabbar__icon">
            <img src="./images/icon_nav_article.png" alt="">
          </div>
          <p class="weui-tabbar__label">请假记录</p>
        </a>
        <a href="#tab4" class="weui-tabbar__item">
          <div class="weui-tabbar__icon">
            <img src="./images/icon_nav_cell.png" alt="">
          </div>
          <p class="weui-tabbar__label">我的信息</p>
        </a>

	
      </div>
    </div>

	


	<div class="weui-flex">
      <div class="weui-flex__item"><div class="placeholder">weui</div></div>
      <div class="weui-flex__item"><div class="placeholder">weui</div></div>
      <div class="weui-flex__item"><div class="placeholder">weui</div></div>
      <div class="weui-flex__item"><div class="placeholder">weui</div></div>
    </div>
</body>
</html>