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

			<div id="tab2" class="weui-tab__bd-item weui-tab__bd-item--active">
				<div class="weui-cells">
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