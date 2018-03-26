<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/modules/kaoqin/header.jsp"%>
<html>
<head>
	<title>宿舍管理管理</title>
	
</head>
<body>




	<div class="weui-tab">

		<div class="weui-tab__bd">

			<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
				<header class='demos-header'>
					<h1 class="demos-title"></h1>
				</header>

				<c:forEach var="item" items="${list}" varStatus="status">
					<div class="weui-form-preview">

						<div class="weui-form-preview__hd">
							<label class="weui-form-preview__label">寝室</label> <em
								class="weui-form-preview__value">${item.dormBuildId}
								${item.dormId} </em>
						</div>
						<div class="weui-form-preview__bd">
							<label class="weui-form-preview__label">学号</label> <em
								class="weui-form-preview__value"> ${item.studentNumber} </em>
						</div>
						<div class="weui-form-preview__bd">
							<label class="weui-form-preview__label">姓名</label> <span
								class="weui-form-preview__value">${item.username}</span>
						</div>
						<div class="weui-form-preview__bd">
							<label class="weui-form-preview__label">日期</label> <em
								class="weui-form-preview__value"> <fmt:formatDate
									value="${item.date}" pattern="yyyy-MM-dd" /></em>
						</div>

					</div>

					<div class="weui-form-preview__ft">
						<a class="weui-form-preview__btn weui-form-preview__btn_primary"
							href="javascript:" id="${item.id}" title="${item.username}" name="${item.detail}"> <c:if test="${item.detail==1}">已考勤</c:if>
							<c:if test="${item.detail==2}">缺勤</c:if> <c:if
								test="${item.detail==3}">请假</c:if> <c:if
								test="${item.detail==0}">考勤</c:if>
						</a>
					</div>


				</c:forEach>
<br>
<br>
			</div>
			<div id="tab2" class="weui-tab__bd-item">
			<header class='demos-header'>
					<h1 class="demos-title"></h1>
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
			<div id="tab3" class="weui-tab__bd-item">
			<header class='demos-header'>
					<h1 class="demos-title"></h1>
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
			<div id="tab4" class="weui-tab__bd-item">
				<h1>开发中</h1>
			</div>
		</div>
	



	<div class="weui-tabbar">
			<a href="#tab1" class="weui-tabbar__item weui-bar__item--on"> <span
				class="weui-badge"
				style="position: absolute; top: -.4em; right: 1em;">8</span>
				<div class="weui-tabbar__icon">
					<img src="${ctxStatic}/jquery-weui/images/icon_nav_button.png"
						alt="">
				</div>
				<p class="weui-tabbar__label">考勤</p>
			</a> <a href="#tab2" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<img src="${ctxStatic}/jquery-weui/images/icon_nav_msg.png" alt="">
				</div>
				<p class="weui-tabbar__label">缺勤信息</p>
			</a> <a href="#tab3" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<img src="${ctxStatic}/jquery-weui/images/icon_nav_article.png"
						alt="">
				</div>
				<p class="weui-tabbar__label">请假记录</p>
			</a> <a href="#tab4" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<img src="${ctxStatic}/jquery-weui/images/icon_nav_cell.png" alt="">
				</div>
				<p class="weui-tabbar__label">个人信息</p>
			</a>

		</div>
</div>

<script type="text/javascript">
$(document).on("click", ".weui-form-preview__btn", function() {
	
	if($(this).attr("name")==0){
		$.modal({
		      title: $(this).attr("title")+"<div id='"+ $(this).attr("id")+"' class='intro' title='"+$(this).attr("title")+"'></div>",
		      text: "操作考勤信息",
		      buttons: [
		        { text: '正常', onClick: function(){ 
		        	var id = $(".intro:first").attr("id");
		        	var title = $(".intro:first").attr("title");
		        	  $.post("operation",{id:id,detail:"1"},function(result){
		        		$.alert(title+"正常考勤,操作成功"); 
		        		window.location.reload();
		        	  });
		        }
		        },
		        { text: '<span style="color:#F00">缺勤</span>', onClick: function(){ 
		        	var id = $(".intro:first").attr("id");
		        	var title = $(".intro:first").attr("title");
		        	  $.post("operation",{id:id,detail:"2"},function(result){
		        		$.alert(title+"缺勤,操作成功"); 
		        		window.location.reload();
		        	  });
		        } 
		        },
		        { text: '取消', className: "default"},
		      ]
		    });
	}else{
		$.alert("不允许重复考勤,请在PC上操作相关数据"); 
	}
    
  });
</script>
</body>
</html>