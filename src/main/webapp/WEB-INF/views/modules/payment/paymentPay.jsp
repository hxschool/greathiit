<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>扫码支付</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("img").attr("src","${ctx}/payment/qrcode?k=${k}");
			$.post("${ctx}/payment/msg?k=${k}",{k:"${k}"},function(result){
				$("#amount").html(parseFloat(result).toFixed(2)+"元");
			});
		});
		
	</script>
<style>
.main{
    text-align: center; /*让div内部文字居中*/
    background-color: #fff;
    width: 300px;
    height: 300px;
    margin: auto;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
}
</style>
</head>

<body>

<div class="container">   
    	<div class="main"  >
    	 <div>##扫码支付##</div>
          <img id="img" />
          <div id="amount" style="color: red"></div>
			<div>请使用支付宝或微信手机客户端扫一扫</div>
        </div>
  </div>  

</body>
</html>