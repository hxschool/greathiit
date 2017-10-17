function IsPC()
{  
	var userAgentInfo = navigator.userAgent;  
	var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");  
	var flag = true;  
	for (var v = 0; v < Agents.length; v++) {  
		if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; }  
	}  
	return flag;  
}

$(document).ready(function(){
	
	var yxqq = getCookie("yxqq");
	
	if( yxqq != "yxqq" )
	{
		setCookie("yxqq","yxqq");
		
		if( IsPC() ) {
			var yxqq_url = $("#yxqq_url").val();
			$("#iframe_yxqq").attr("src", yxqq_url );
		}
	}
	
	function getCookie(name)
	{
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
		else
		return null;
	}
	
	function setCookie( name, value )
	{
		var s = 10 * 60;
		var exp = new Date();
		exp.setTime( exp.getTime() + s * 1000 );
		var domain = $("#yxqq").attr("data");
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/;domain=" + domain;
	}
	
	$('#side-qq .close').bind('click',function(){
		$('#side-qq').hide();
		$('#open-qq').show();
	});
	
	$('#open-qq').bind('click',function(){
		$('#side-qq').show();
		$(this).hide();
	});
	
	function changeborder(){
		$('#side-qq .blink').each(function(){
			if($(this).hasClass("blink1")){
				$(this).removeClass("blink1");
			}else{
				$(this).addClass("blink1");
			}
		})
	}
	
	setInterval(function(){
		changeborder();
	},300);
	
	
});


function dxtxcheck(){
	
	$sj = $.trim($("#dxtx_sj").val());
	
	if($sj == ""){
		alert("联系电话不能为空");
		$("#dxtx_sj").focus();
		$("#dxtx_sj").select();
		return false;
	}
	
	var flag = false;
	var reg0 =/^\d{3,4}-\d{7,8}(-\d{3,4})?$/;      //判断 固话  
	var reg1 =/^((\(\d{2,3}\))|(\d{3}\-))?(1)\d{10}$/;                     //判断 手机  
	if (reg0.test($sj)) flag=true;
	if (reg1.test($sj)) flag=true;
	if(!flag){  
		alert("电话号码，格式不对。\r\n固定电话格式：区号-号码（例：0773-2991410）\r\n手机格式：11位号码（例：13911111111）。");  
		$("#dxtx_sj").focus();
		$("#dxtx_sj").select();
		return false;
	}
	
	$cj = $.trim($("#dxtx_cj").val());
	if($cj == ""){
		alert("预计高考成绩未选择");
		$("#dxtx_cj").focus();
		$("#dxtx_cj").select();
		return false;
	}
	
	return true;
}