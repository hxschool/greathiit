<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>哈尔滨信息工程学院-新生签到</title>
        <meta name="keywords" content="哈尔滨信息工程学院-新生签到"/>
	<meta name="description" content="哈尔滨信息工程学院-新生签到"/>
        <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">

        <link rel="stylesheet" href="${ctxStatic }/yingxin2/login/mui/css/mui.css">
        <link rel="stylesheet" type="text/css" href="${ctxStatic }/yingxin2/login/css/common.css"/>
        <style>
            .container{width:90%}
            body{
                background-color: #000;
            }
            .login_box{
                background: #FFF;
                border-radius: 4px;
                display: block;
                padding: 15px;

                 position: fixed;
                top:3rem;
                width: 90%;
                left:5%
            }

            .login_head {
                border-bottom: 1px solid #e5e5e5;
                margin-bottom: 10px;
            }

            .login_head h2 {
                font-size: 0.8rem;
                line-height: 16px;
                margin-bottom: 16px;
                color:#06bf04;text-align: center;
                font-weight: normal
            }
            .mui-input-row{
                border:1px solid #ddd;
                border-radius: 5px;
            }
            .btn_login{width:100%;height:2.2rem;line-height: 2.2rem;display: block;background-color:#06bf04;font-size:0.75rem;
                       text-align: center;margin:1.5rem auto 0;color:#FFF; border-color: #03b401;border-radius: 4px;}
            .btn_login:hover{color:#FFF}
            .mui-input-row label {
                width: 25%;
            }
            .mui-input-row label ~ input, .mui-input-row label ~ select, .mui-input-row label ~ textarea{
                width: 75%;
            }
        </style>
    </head>
    <body>
        <div class="mui-content">
            <div class="container">
                <div class="login_box" >
                    <div class="login_head">
                        <h2>哈尔滨信息工程学院</h2>
                    </div>
                    <div class="mui-input-row" style='margin:1.2rem 0 0.7rem'>
                        <label>姓名</label>
                        <input type="text" id='phone' placeholder="请输入姓名"/>
                    </div>
                    <div class="mui-input-row">
                        <label>证件号</label>
                        <input type="text" id='pwd' placeholder="请输入身份证号"/>
                    </div>
                    <a class="btn_login" id="btn_login">签 到</a>
                </div>
            </div>
        </div>

                <script type="text/javascript" src="${ctxStatic }/yingxin2/login/mui/js/mui.min.js"></script>
        <script type="text/javascript" src="${ctxStatic }/yingxin2/login/js/jquery.js"></script>
        <script type="text/javascript">

            mui.init();
            document.getElementById("btn_login").addEventListener('tap', function() {

                var pwd = $.trim($("#pwd").val());
                var phone = $.trim($("#phone").val());
                var phone_pattern = /(?:\(?[0\+]?\d{1,3}\)?)[\s-]?(?:0|\d{1,4})[\s-]?(?:(?:13\d{9})|(?:\d{7,8}))/;
                if (phone == '') {
                    mui.alert('请输入姓名', '系统提示', function() {
                        $('#phone').focus();
                    });
                    return false;
                }
    
                if (pwd == '') {
                    mui.alert('请输入身份证号', '系统提示', function() {
                        $('#pwd').focus();
                    });
                    return false;
                }
           
                $.post('checkSign', {"phone": phone, "pwd": pwd}, function(data) {
                	alert(data.responseMessage);

                }, "json")
            });
        </script>
    </body>
</html>