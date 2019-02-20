<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${productName }</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description"
	content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.">

<link rel="stylesheet" href="${ctxStatic}/jquery-weui/lib/weui.min.css">
<link rel="stylesheet"
	href="${ctxStatic}/jquery-weui/css/jquery-weui.min.css">

<script src="${ctxStatic}/jquery-weui/lib/jquery-2.1.4.js"></script>

<script src="${ctxStatic}/jquery-weui/js/jquery-weui.js"></script>
<style>
.blue-btn {
	background-color: #5cadff;
	color: #fff;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#loginform').submit(function() {
			var ret = check_sfzh($("#hc_form_sfzh").val());
			var msg = "";
			
			if(!ret){
				 msg = "填写18位二代身份证号码";
				 $("#hc_form_sfzh").blur();
			}
			ret = $("input[type='checkbox']").is(':checked'); 
			if(!ret){
				if(msg!=''){
					msg = msg + "<br>";
				}
				msg = msg + "请勾选同意";
			}
			if(msg!=""){
				 $.alert(msg);
				return false;
			}
			return true;
		});
	})
	
	function check_sfzh(value) {

		var arrExp = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]; //加权因子  
		var arrValid = [1, 0, "X", 9, 8, 7, 6, 5, 4, 3, 2]; //校验码  
		if (/^\d{17}\d|x$/i.test(value)) {
			var sum = 0,
				idx;
			for (var i = 0; i < value.length - 1; i++) {
				// 对前17位数字与权值乘积求和  
				sum += parseInt(value.substr(i, 1), 10) * arrExp[i];
			}
			// 计算模（固定算法）  
			idx = sum % 11;
			// 检验第18为是否与校验码相等  
			return arrValid[idx] == value.substr(17, 1).toUpperCase();
		} else {
			return false;
		}
	}
</script>
</head>
<body ontouchstart>


	<form id="loginform" method="post" class="form-horizontal"
		action="skip_ZhaoSheng" onsubmit="return false;">

		<article class="weui-article">
			<section>
				<h1 class="title">单独招生专业志愿填报系统说明</h1>
				<section>

					<p>${config.description}</p>

					<p class="text-s16">
						<a href="http://www.hxci.com.cn/zy/"
							class="weui-btn  weui-btn_mini blue-btn">查看招生简章</a> <a
							href="skip_Jieguo"
							class="weui-btn weui-btn_mini weui-btn_default">查看报考结果</a>
					</p>
				</section>
			</section>
		</article>

		<div class="weui-cell ">
			<div class="weui-cell__hd">
				<label class="weui-label">身份证号码</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" name="hc_form_sfzh" id="hc_form_sfzh"
					placeholder="输入18位身份证号" value="">
			</div>
		</div>

		<label for="agreebbrule" class="weui-agree"> <input
			type="checkbox" class="weui-agree__checkbox" name="agreebbrule"
			id="agreebbrule"> <span class="weui-agree__text">
				阅读并同意<a href="javascript:void(0);">《相关条款》</a>
		</span>
		</label>
		

		<div class="weui-btn-area">
			<c:if test="${config.sw=='YES'}">
				<input type="submit" class="weui-btn weui-btn_primary"
					name='bm_submit' value="提交申请表">
			</c:if>
			<c:if test="${config.sw=='NO'}">
				<a href="javascript:;"
					class="weui-btn weui-btn_disabled weui-btn_warn">禁用提交,请查看相关协议</a>
			</c:if>
		</div>
	</form>

	<div class="weui-footer ">
		<p class="weui-footer__links">
			<a href="http://www.greathiit.com/f/2018" class="weui-footer__link">哈尔滨信息工程学院-单考单招系统</a>
		</p>
		<p class="weui-footer__text">Copyright @ 1998-2017 Harbin
			Institute Of Information Technology All Rights Reservd 黑ICP备05007064号</p>
	</div>

	<c:if test="${config.sw=='NO'}">
		<script>
	 	$(document).ready(function(){
		 $.alert("专业填报系统已关闭，有问题请咨询0451-58607888.省考试成绩已出,点击查看报考结果查询成绩");
		});
	</script>
	</c:if>
</body>
</html>
