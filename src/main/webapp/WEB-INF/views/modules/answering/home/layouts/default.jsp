<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title default="欢迎光临" /> - ${site.title} - 答辩抽签计时系统  Answering ballot time system</title>
<%@include file="/WEB-INF/views/modules/answering/home/layouts/head.jsp"%>
<sitemesh:head />
</head>

<body>
<header>
      <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
          <div class="row">
            <div class="col-sm-8 col-md-7 py-4">
              <h4 class="text-white">学院简介</h4>
              <p class="text-muted">哈尔滨信息工程学院始建于1995年，是经国家教育部批准的普通本科高等院校。20年励精图治，现拥有两个校区，分别坐落在江北学院路和哈东（宾西）国家经济技术开发区。校园占地面积1044亩，校舍建筑面积25.6万平方米，教学仪器设备总值5261.18万元，图书76.8万册；专任教师473人，副高级以上的任课教师达41%。校园占地面积、校舍建筑面积以及一流的硬件条件建设，在全省同类院校名列前茅！国家教育部批准的黑龙江省仅有的2所"国家示范软件学院"，其中一所是久负盛名的哈尔滨工业大学，另一所就是异军突起的哈尔滨信息工程学院！.</p>
            </div>
            <div class="col-sm-4 offset-md-1 py-4">
              <h4 class="text-white">联系我们</h4>
              <ul class="list-unstyled">
              	 <li><a href="http://www.greathiit.com" class="text-white">学院官网</a></li>
                <li><a href="tel:0451-58607916" class="text-white">0451-58607916</a></li>
                <li><a href="mailto:773152@qq.com" class="text-white">773152@qq.com</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between">
          <a href="#" class="navbar-brand d-flex align-items-center">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path><circle cx="12" cy="13" r="4"></circle></svg>
            
            <strong>哈尔滨信息工程学院</strong>
          </a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        </div>
      </div>
    </header>

<sitemesh:body />



    <footer class="text-muted">
      <div class="container">
        <p class="float-right">
          <!-- <a href="#">Back to top</a> -->
        </p>
        <p>学校地址：哈尔滨市宾西经济技术开发区大学城9 号　　邮编：150431 　 电话：0451-58607916</p>
       	<p>Copyright @ 1998-2020 Harbin Institute Of Information Technology All Rights Reservd 黑ICP备05007064号</p>
      </div>
    </footer>


</body>
</html>