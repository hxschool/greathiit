<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html>
  <head>
    <title>绑定用户信息</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description" content=哈尔滨信息工程学院是经教育部批准设置的全日制普通本科高等学校，其前身是哈尔滨华夏计算机职业技术学院。">

<link rel="stylesheet" href="${ctxStatic}/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${ctxStatic}/jquery-weui/css/jquery-weui.css">
<script src="${ctxStatic}/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${ctxStatic}/jquery-weui/lib/fastclick.js"></script>

<script src="${ctxStatic}/jquery-weui/js/jquery-weui.js"></script>
  </head>
  <body ontouchstart>

<a href="javascript:;" id='show-login' class="weui-btn weui-btn_primary">验证登录</a>


    <script>
    $(document).ready(function(){
    	initLogin();
    	});
        
    $(document).on('click', '#show-login', function() {
    	initLogin();
    });
    function initLogin(){
    	$.login({
            title: '登录',
            text: '请输入用户名和密码',
            onOK: function (username, password) {
           	 // $.post("bind",{username:username,password:password});
              //$.toast('登录成功!');
              $("#hidden_username").val(username);
              $("#hidden_password").val(password);
              $("#hidden_form").submit();
            },
            onCancel: function () {
              $.toast('取消登录!', 'cancel');
            }
          });
    }
    </script>
    <form id="hidden_form" action="bind" method="post">
    	<input id="hidden_username" name="username" type="hidden">
    	<input id="hidden_password" name="password" type="hidden">
    </form>
  </body>
</html>
