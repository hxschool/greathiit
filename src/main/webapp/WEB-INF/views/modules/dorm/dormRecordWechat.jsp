<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html>
<head>
<title>哈尔滨信息工程学院-设备保修</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description"
	content=哈尔滨信息工程学院是经教育部批准设置的全日制普通本科高等学校，其前身是哈尔滨华夏计算机职业技术学院。">

<link rel="stylesheet" href="${ctxStatic}/jquery-weui/lib/weui.min.css">
<link rel="stylesheet"
	href="${ctxStatic}/jquery-weui/css/jquery-weui.css">
<link rel="stylesheet" href="${ctxStatic}/jquery-weui/css/demos.css">
<script src="${ctxStatic}/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${ctxStatic}/jquery-weui/lib/fastclick.js"></script>

<script src="${ctxStatic}/jquery-weui/js/jquery-weui.js"></script>
</head>
<body ontouchstart>
	<div class="weui-tab">
		<div class="weui-tab__bd">

			<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
			<form id="inputForm" action="${ctx}/dorm/ucDormRepair/save" method="post" class="form-horizontal">
				<div class="weui-panel__bd">
					<div class="weui-cell">
						<div class="weui-cell__hd">
							<label class="weui-label">报修类型</label>
						</div>
						<div class="weui-cell__bd">
							<input class="weui-input" id="repair_show" 
								type="text">
								<input class="weui-input" id="repairType" name="repairType"
								type="hidden" required="required">
						</div>
					</div>

					<div class="weui-cell">
						<div class="weui-cell__hd">
							<label class="weui-label">报修主题</label>
						</div>
						<div class="weui-cell__bd">
							<input class="weui-input" id="repairTitle" name="repairTitle" type="text"
								placeholder="请输入报修主题" required="required">
						</div>
					</div>

					<div class="weui-cells__title">报修描述</div>
					<div class="weui-cells weui-cells_form">
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<textarea class="weui-textarea" name="repairContent" id="repairContent"
									placeholder="请输入文本" rows="3"></textarea>
								<div class="weui-textarea-counter">
									<span>0</span>/500
								</div>
							</div>
						</div>
					</div>

					<div class="weui-cell">
						<div class="weui-cell__hd">
							<label class="weui-label">手机号</label>
						</div>
						<div class="weui-cell__bd">
							<input class="weui-input" type="tel" name="repairPhone" id="repairPhone"
								placeholder="请输入手机号" required="required">
						</div>

					</div>

					<div class="weui-cell"></div>
					 <div class="weui-btn-area">
				      <a class="weui-btn weui-btn_primary" href="javascript:save()" id="showTooltips">确定</a>
				    </div>
				</div>
				</form>
			</div>
			<div id="tab2" class="weui-tab__bd-item ">

				<div class="weui-panel__bd">
					<c:forEach items="${page.list}" var="ucDormRepair">
						<a href="javascript:void(0);"
							class="weui-media-box weui-media-box_appmsg">
							<div class="weui-media-box__hd">
								<img class="weui-media-box__thumb"
									src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAMAAAAOusbgAAAAeFBMVEUAwAD///+U5ZTc9twOww7G8MYwzDCH4YcfyR9x23Hw+/DY9dhm2WZG0kbT9NP0/PTL8sux7LFe115T1VM+zz7i+OIXxhes6qxr2mvA8MCe6J6M4oz6/frr+us5zjn2/fa67rqB4IF13XWn6ad83nxa1loqyirn+eccHxx4AAAC/klEQVRo3u2W2ZKiQBBF8wpCNSCyLwri7v//4bRIFVXoTBBB+DAReV5sG6lTXDITiGEYhmEYhmEYhmEYhmEY5v9i5fsZGRx9PyGDne8f6K9cfd+mKXe1yNG/0CcqYE86AkBMBh66f20deBc7wA/1WFiTwvSEpBMA2JJOBsSLxe/4QEEaJRrASP8EVF8Q74GbmevKg0saa0B8QbwBdjRyADYxIhqxAZ++IKYtciPXLQVG+imw+oo4Bu56rjEJ4GYsvPmKOAB+xlz7L5aevqUXuePWVhvWJ4eWiwUQ67mK51qPj4dFDMlRLBZTqF3SDvmr4BwtkECu5gHWPkmDfQh02WLxXuvbvC8ku8F57GsI5e0CmUwLz1kq3kD17R1In5816rGvQ5VMk5FEtIiWislTffuDpl/k/PzscdQsv8r9qWq4LRWX6tQYtTxvI3XyrwdyQxChXioOngH3dLgOFjk0all56XRi/wDFQrGQU3Os5t0wJu1GNtNKHdPqYaGYQuRDfbfDf26AGLYSyGS3ZAK4S8XuoAlxGSdYMKwqZKM9XJMtyqXi7HX/CiAZS6d8bSVUz5J36mEMFDTlAFQzxOT1dzLRljjB6+++ejFqka+mXIe6F59mw22OuOw1F4T6lg/9VjL1rLDoI9Xzl1MSYDNHnPQnt3D1EE7PrXjye/3pVpr1Z45hMUdcACc5NVQI0bOdS1WA0wuz73e7/5TNqBPhQXPEFGJNV2zNqWI7QKBd2Gn6AiBko02zuAOXeWIXjV0jNqdKegaE/kJQ6Bfs4aju04lMLkA2T5wBSYPKDGF3RKhFYEa6A1L1LG2yacmsaZ6YPOSAMKNsO+N5dNTfkc5Aqe26uxHpx7ZirvgCwJpWq/lmX1hA7LyabQ34tt5RiJKXSwQ+0KU0V5xg+hZrd4Bn1n4EID+WkQdgLfRNtvil9SPfwy+WQ7PFBWQz6dGWZBLkeJFXZGCfLUjCgGgqXo5TuSu3cugdcTv/HjqnBTEMwzAMwzAMwzAMwzAMw/zf/AFbXiOA6frlMAAAAABJRU5ErkJggg=="
									alt="">
							</div>
							<div class="weui-media-box__bd">
								<h4 class="weui-media-box__title">${ucDormRepair.repairTitle}</h4>
								<p class="weui-media-box__desc">
									${fns:getDictLabel(ucDormRepair.repairType, 'repair_type', 0)},${fns:getDictLabel(ucDormRepair.repairState, 'repair_state', 0)},${ucDormRepair.dorm.ucDormBuild.id}栋${ucDormRepair.dorm.dormFloor}层${ucDormRepair.dorm.dormNumber}
								</p>
								<p class="weui-media-box__desc">
									<fmt:formatDate value="${ucDormRepair.createDate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</p>
							</div>
						</a>
					</c:forEach>
				</div>


			</div>
		</div>

		<div class="weui-tabbar">
			<a href="#tab1" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<img src="${ctxStatic}/jquery-weui/images/icon_nav_article.png"
						alt="">
				</div>
				<p class="weui-tabbar__label">我要报修</p>
			</a> <a href="#tab2" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<img src="${ctxStatic}/jquery-weui/images/icon_nav_cell.png" alt="">
				</div>
				<p class="weui-tabbar__label">我的报修</p>
			</a>
		</div>
	</div>
	<script>
	function save(){
		   var ret = true;
		   var message = "";
		   if($("#repairType").val()==""){ 
			   ret=false
			   message = "请选择报修类型!<br>";
		   }
		   if($("#repairTitle").val()==""){ 
			   ret=false
			   message = message + "请输入报修主题!<br>";
		   }
		   if($("#repairContent").val()==""){ 
			   ret=false
			   message = message + "请输入描述信息!<br>";
		   }

		   if($("#repairPhone").val()==""){
			   message = message + "请输入正确的手机号码!<br>";
		   }
		   if(!ret){
			  
			   $.alert(message, '操作失败');
			   return;
		   }
		  if(ret){
			  $.ajax({
		             type: "POST",
		             url:'${ctx}/dorm/ucDormRepair/save',
		             contentType : "application/x-www-form-urlencoded; charset=utf-8",
		             data:$("#inputForm").serialize(),
		             dataType: "text",
		             success: function () {
						 $.toast('提交成功!','success');
						 window.location.reload();
		               },
		             error: function () {
						 $.toast('操作失败!', 'cancel');
		             }
		         })
		  }

	}
	var datalist = new Array();
	<c:forEach items="${fns:getDictList('repair_type')}" var="dict">
		var info = { "title": "${dict.label}", "value": "${dict.value}" };
    	datalist.push(info);
	</c:forEach>
      $("#repair_show").select({
        title: "选择类型",
        items: datalist,
        onClose: function(data) {
        	$("#repairType").val(data.data.values);
        } 
        });
    </script>
</body>
</html>


