<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="xuanke_default_${site.theme}" />
<meta name="description"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.description}" />
<meta name="keywords"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.keywords}" />
</head>
<body>



	<div class="wrap maincontent">
		
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-md-3 mt30 side hidden-xs ">

    <div class="widget-box clearfix" style=" margin:3px; border-style:solid; border-color:#CC9900; text-align:center;">
        <script type="text/javascript" charset="utf-8" src="/Public/home/Js/showtime.js"></script>
    </div>

    <div class="widget-box clearfix">
        <h2 class="h4 widget-box__title">分类列表(1)</h2>
        <div class="pcss3mm ">
            <ul id="pcss3mm" class="nav nav-pills" role="tablist">
                <li  id="cate1"><a href="/index.php?m=&c=Index&a=artlist&cid=1">常见问题与解答</a></li><li  id="cate2"><a href="/index.php?m=&c=Index&a=artlist&cid=2">反馈消息</a></li>            </ul>
        </div>
    </div>

    <div class="widget-box clearfix">
        <h2 class="h4 widget-box__title">最近用户</h2>
        <ul class="list-unstyled list-unstyled">
            <li class="widget-user media">
                    <a class="pull-left" href="/index.php?m=&c=Ucenter&a=userart&uid=1">
                        <img class="media-object avatar-40" src="Addons/Avatar/default_64_64.png" alt="admin" title="admin">
                    </a>
                </li><li class="widget-user media">
                    <a class="pull-left" href="/index.php?m=&c=Ucenter&a=userart&uid=2">
                        <img class="media-object avatar-40" src="Addons/Avatar/default_64_64.png" alt="赵俊飞" title="赵俊飞">
                    </a>
                </li>        </ul>
    </div>

    <div class="widget-box no-border">
        <h2 class="h4 widget-box__title">最新公告</h2>
        <ul class="widget-links list-unstyled">
            <li class="widget-links__item">
                    <a href="/index.php?m=&c=Index&a=artc&id=1" title="常见问题与解答">常见问题与解答</a>
                    <small class="text-muted">
                        1 收藏，
                        80 浏览
                    </small>
                </li><li class="widget-links__item">
                    <a href="/index.php?m=&c=Index&a=artc&id=2" title="常见问题与解答2">常见问题与解答2</a>
                    <small class="text-muted">
                        1 收藏，
                        18 浏览
                    </small>
                    
                </li>        </ul>
    </div>

    
</div><!-- /.layout-sidebar -->

				

<div class="col-xs-12 col-md-9 main mt30">
    <div class="panel panel-default panel-archive">
        <div class="panel-body">
            <!-- Nav tabs -->
            <ul class=" nav nav-pills pb10 mb10 mt10">
                <li class="active"><a href="/index.php?m=&c=Index&a=course">已选课程</a></li>
                            </ul>
            <div style="min-height:300px;">
                <form action="/index.php?m=&c=index&a=course" method="post" id="formsearch">
                    <div class="example-code">
                        <div class="form-label float-left" >
                            <label>课程名称：</label>
                        </div>
                        <div class="form-input col-md-2">
                            <input type="text" value="" name="name" size="10" />`
                        </div>

                        <button type="submit" class="btn medium bg-blue"><span class="button-content">
                            <i class="glyph-icon icon-search"></i>查询</span>
                        </button>

                        <a class="btn medium bg-orange" href="javascript:clearQuery(this)">
                            <span class="button-content"><i class="glyph-icon icon-undo"></i> 清空查询</span>
                        </a>

                    </div><!-- example-code -->
                    <div class="divider"></div>
                </form>

                <table class="dataTable table text-left"  id="dataTables">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>课程名称</th>
                            <th>任课老师</th>
                            <th>所属专业</th>
                            <th>学分</th>
                            <th class="text-center" width="60">操作</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                                <td>1</td>
                                <td>C语言</td>
                                <td>王晓华</td>
                                <td></td>
                                <td>2</td>
                                <td>
                                    <a href="/index.php/Home/Index/xuanke/id/1/status/1"  class="btn small bg-green" target="ajaxTodo"   ><span class="button-content"><i class="glyph-icon icon-plus"></i> 选课</span></a>                                </td>
                            </tr><tr>
                                <td>2</td>
                                <td>数据库管理与应用</td>
                                <td>王晓华</td>
                                <td>计算机科学与技术</td>
                                <td>2</td>
                                <td>
                                    <a href="/index.php/Home/Index/xuanke/id/2/status/1"  class="btn small bg-green" target="ajaxTodo"   ><span class="button-content"><i class="glyph-icon icon-plus"></i> 选课</span></a>                                </td>
                            </tr>                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


			</div>
		</div>
	</div>

</body>
</html>