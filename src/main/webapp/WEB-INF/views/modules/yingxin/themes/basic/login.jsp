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
    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/css-nr-bdxz.css" type="text/css"/>
    <link rel="stylesheet" href="${ctxStatic }/yingxin/style/lrtk.css" type="text/css"/>
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
                <dd class="p-l-10">1）新生登录 <span class="orange bold">用户名</span>：学号</dd>
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
                <form action=""  autocomplete="on" method="get">
                <dd>
                    <label for="u_name">用户名：</label>
                    <input type="text" id="u_name" name="u_name"
                           placeholder="例：2018010101" maxlength="10" required >
                </dd>
                <dd>
                    <label for="u_pwd">  密   码  ：</label>
                    <input type="password" id="u_pwd" name="u_pwd" maxlength="6"
                           required pattern="^\d{6,}$">
                </dd>
                <dd class="int_login"><input type="submit" value="登录"/></dd>
                <dd class="int_reset"><input type="reset" value="重置"/></dd>
                </form>
            </dl>
        </li>
        <li class="city1"></li>
        <li class="city2"></li>
    </ul>
</body>
</html>