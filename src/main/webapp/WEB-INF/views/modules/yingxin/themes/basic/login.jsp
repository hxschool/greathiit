<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="yingxin_default_${site.theme}" />
<meta name="description"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.description}" />
<meta name="keywords"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.keywords}" />
	    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/top-bottom.css" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/css-wsdl.css" type="text/css"/>
    <script src="http://code.jquery.com/jquery-2.2.4.min.js"
			  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
			  crossorigin="anonymous"></script>
</head>
<body>

	<script type="text/javascript"
		src="${ctxStatic }/yingxin/js/backtop.js"></script>
	
   <h1>Welcome To HXCI</h1>
    <ul class="o_h">
        <li class="dlsm">
            <dl>
                <dt>迎新系统登录说明</dt>
                <dd>1、查看 <span class="orange bold">“入学须知”</span>下内容，并按要求做好入学准备</dd>
                <dd>2、登录迎新系统说明</dd>
                <dd class="p-l-10">1）新生登录 <span class="orange bold">用户名</span>：身份证号</dd>
                <dd class="p-l-10">2）<span class="orange bold">初始密码</span></dd>
                <dd class="p-l-20">学生的密码为学生身份证号码后6位。</dd>
                <dd class="p-l-20">身份证最后一位为字母的，则密码为大写。
                </dd>
                <dd>欢迎你来到哈尔滨信息工程学院，祝你有个美好的校园生活！</dd>
            </dl>
        </li>
        <li class="wsdl">
            <dl>
                <dt>新生网上登录</dt>
               <form action="http://login.greathiit.com/login?service=http://www.greathiit.com/a" name="form1" method="post" id="loginForm" onsubmit="return loginValidate();"  class="login">  
                <dd>
                    <label for="u_name">用户名：</label>

                            <input  type="text" name="username" value="" placeholder="例：2018010101" class="user" id="username">  
                </dd>
                <dd>
                    <label for="u_pwd">  密   码  ：</label>
                    <input type="password" name="password" value="" placeholder="密码" class="pwd" id="password">  
                </dd>
                <dd class="int_login">
            <input type="button" id="submit_login" value="登陆"/>
             </dd>
                <dd class="int_reset"><input type="reset" value="重置"/></dd>
                <input type="hidden" name="execution" id="execution" value="" />
                <input type="hidden" name="login_from" value="" />  
                <input type="hidden" name="_eventId" value="submit" />  
                
                </form>
            </dl>
        </li>
        <li class="city1"></li>
        <li class="city2"></li>
    </ul>
    
    <script>
	    $('#submit_login').click(function(){
			var xhr = $.getJSON("http://login.greathiit.com/login?action=getlt&service=http://www.greathiit.com/a&callback=?",    
                    function(response) {
                        $("#execution").val(response.execution);
                        $('#loginForm').submit();
                    }); 
					xhr.fail(function(jqXHR, textStatus, ex) {
						alert('服务器繁忙,请稍后再试');
					});
		});  

	var loginValidate = function() {
		
      var msg;
      if ($.trim($('#username').val()).length == 0) {
         msg = "用户名不能为空。";
      } else if ($.trim($('#password').val()).length == 0) {
         msg = "密码不能为空。";
      }
      if (msg && msg.length > 0) {
		  alert(msg);
         $('#J_ErrorMsg').fadeOut().text(msg).fadeIn();
         return false;
         // Can't request the login ticket.  
      } 
   } 
	</script>
</body>
</html>