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
</head>
<body>


	<!-- 全屏轮播图 -->
<div id="carousel-example-generic" class="carousel slide my-slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class=""></li>
        <li data-target="#carousel-example-generic" data-slide-to="1" class="active"></li>
    </ol>
    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item">
            <a target="_blank" href="">
                <img src="${ctxStatic}/yingxin2/banner1.jpg" alt="哈尔滨，一座东西方文化互相交融的城市，说起城市的美好，丽江大理最美丽,西藏最圣洁，然而哈尔滨的魅力却是最独特的！玩在哈信息" style="width: 1920px; max-width: 1920px; margin-left: 0px;">
            </a>
        </div>
        <div class="item active">
            <a target="_blank" href="">
                <img src="${ctxStatic}/yingxin2/banner2.jpg" alt="  一座花园式的现代化校园——走近哈尔滨信息工程学院哈东新校园门口，极目四望，新校园的规划设计匠心独具，现代、大气、还颇具洋气！高起点、高规格的建设，使新校园充满生机与活力！新校园自落成以来就成为哈东一处最靓丽、最引人注目和驻足的新景观：雄伟壮观的教学大楼、卓尔不俗的大学生艺体活动中心、高雅别致的国际交流中心、绿草如茵的凝萃园、碧波荡漾的荷花湖、苍翠欲滴的太平山、奔流不息的猞猁河……小桥流水的温馨，亭台楼阁的浪漫，让新校园生机盎然！" style="width: 1920px; max-width: 1920px; margin-left: 0px;">
            </a>
        </div>
    </div>
</div>
<!-- 全屏轮播图 -->
<!-- 我们的成绩 -->
<div class="index-row our-data">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 main-title">
                <h2 class="h1">我们的成绩</h2>

                <p class="line line-big"></p>

                <h2>
                    <small>哈尔滨信息工程学院始建于1995年，是经国家教育部批准的普通本科高等院校。</small>
                </h2>
            </div>
            <div class="col-sm-3 our-data-item sum-apply">
                <div class="number-wrapper">
                    <span id="sum-apply" class="run-number" data-to="240">1044</span>
                </div>
                <h4 class="title">占地面积（亩）</h4>
            </div>
            <div class="col-sm-3 our-data-item sum-rate">
                <div class="number-wrapper">
                    <span id="sum-rate" class="run-number" data-to="240">25.6</span>
                </div>
                <h4 class="title">校舍建筑面积(万平方米)</h4>
            </div>
            <div class="col-sm-3 our-data-item sum-urgent">
                <div class="number-wrapper">
                    <span id="sum-urgent" class="run-number" data-to="240">5,261.18</span>
                </div>
                <h4 class="title">教学仪器总值(万元)</h4>
            </div>
            <div class="col-sm-3 our-data-item urgent-rate">
                <div class="number-wrapper">
                    <span id="urgent-rate" class="run-number" data-to="240">473</span>
                </div>
                <h4 class="title">专任教师</h4>
            </div>
			
        </div>
    </div>
</div>
<!-- 我们的成绩 -->


<!-- 学院概况 -->
<div class="index-row our-service">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 main-title">
                <h2 class="h1">学院概况</h2>

                <p class="line line-big"></p>

                <h2>
                    <small>国家示范软件学院。</small>
                </h2>
            </div>
            <div class="col-sm-12 text-center card-box">
                <ul class="card-area">
                    <li class="card-item">
                        <div class="card-title">
                            <h4>软件学院</h4>

                            <p>黑龙江省外包产业人才培养基地</p>

                      </div>
                        <div class="card-content">
                            <p>软件工程<br>
                              计算机科学与技术<br>
                              软件技术<br>
                              动漫制作技术<br>
                              移动应用开发<br>
                              计算机网络技术<br>
                              云计算技术与应用<br>
                              大数据技术与应用<br>
                              信息安全与管理<br>
                            计算机应用技术</p>

                            <a href="http://www.hxci.com.cn/index.php?m=content&c=index&a=show&catid=226&id=1072" class="btn btn-outline-blue">查看详情</a>

                      </div>
                    </li>
                    <li class="card-item">
                        <div class="card-title">
                            <h4>艺术设计学院</h4>

                            <p> 先进、系统的教育体系</p>
                      </div>
                        <div class="card-content">
                            <p>环境设计<br>
                              视觉传达设计<br>
                              建筑室内设计<br>
                              工程造价<br>
                              动漫设计<br>
                            广告设计与制作<br><br><br><br><br></p>

                            <a href="http://www.hxci.com.cn/index.php?m=content&c=index&a=show&catid=226&id=1093" class="btn btn-outline-blue">查看详情</a>
                      </div>
                    </li>
                    <li class="card-item">
                        <div class="card-title">
                            <h4>商学院</h4>

                            <p>对外电商人才培养基地</p>
                      </div>
                        <div class="card-content">
                            <p>电子商务<br>
                              市场营销<br>
                              会计<br>
                              计算机信息管理<br>
                              审计<br>
                            游戏设计<br><br><br><br><br></p>

                            <a href="http://www.hxci.com.cn/index.php?m=content&c=index&a=show&catid=226&id=1077" class="btn btn-outline-blue">查看详情</a>
                      </div>
                    </li>
                    <li class="card-item card-active">
                        <div class="card-title">
                            <h4>电子工程学院</h4>

                            <p>全国电子信息产业紧缺人才培养基地</p>
                      </div>
                        <div class="card-content">
                            <p>电子信息工程<br>
                              自动化<br>
                              物联网应用技术<br>
                              电子信息工程技术<br>
                              汽车检测与维修技术<br>
                              移动通信技术<br>
                            汽车电子技术<br><br><br><br></p>

                            <a href="http://www.hxci.com.cn/index.php?m=content&c=index&a=show&catid=226&id=1068" class="btn btn-outline-inverse">查看详情</a>
                      </div>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="col-sm-12 text-center">
                <a href="http://www.hxci.com.cn/index.php?m=content&c=index&a=lists&catid=193" class="btn btn-outline-blue btn-lg">查看更多服务</a>
            </div>
        </div>
    </div>
</div>
<!-- 学院概况 -->

<!-- 最新资讯 -->
<div class="index-row our-news">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 main-title">
                <h2 class="h1">最新资讯</h2>

                <p class="line line-big"></p>

                <h2>
                    <small>国家示范性软件学院之一。</small>
                </h2>
            </div>

				<c:forEach items="${articles}" var="article">
					<c:set var="link"
						value="/yingxin/view-${article.category.id}-${article.id}${urlSuffix}"
						scope="session"></c:set>
					<c:if test="${!empty article.link }">
						<c:set var="link" value="${article.link}" scope="session"></c:set>
					</c:if>
					<div class="col-sm-6">
						<div class="media our-news-item">
							<div class="media-left">
								<a class="thumbnail" href="${link }"> <img
									class="media-object" src="${article.image }"
									alt="${article.title}">
								</a>
							</div>
							<div class="media-body">
								<a href="${link }"><h4 class="media-heading">${fns:abbr(article.title,40)}</h4></a>
								<div class="dynamic-li">
									<span>时间：<fmt:formatDate value="${article.createDate}"
											type="both" />
									</span>
								</div>
								<p>${fns:abbr(article.description,100)}</p>
								<div class="dynamic-li">
									<a href="${link }">[详细]</a> <span>作者：哈尔滨信息工程学院 <span
										class="hidden-xs">www.greathiit.com</span>
									</span>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

				<div class="col-sm-12 our-news-more">
                <h2>
                    <small><a href="/yingxin/list-200005.html">更多资讯&gt;&gt;&gt;</a></small>
                </h2>
            </div>
        </div>
    </div>
</div>
<!-- 数字滚动插件 -->
<script type="text/javascript" src="${ctxStatic}/yingxin2/countUp.js"></script>
<script type="text/javascript">
    $(function () {
        /**通用-banner大图自定义缩放**/
        var zoomWidth = 992; //缩放阀值992px, 即所有小于992px的视口都会对原图进行缩放, 只是缩放比例不同
        var maxWidth = 1920; //最大宽度1920px
        var ratio = 1; //缩放比例
        var viewWidth = window.innerWidth; // 视口宽度
        var zoomSlider = function () {
            if (viewWidth < 768) { //当视口小于768时(移动端), 按992比例缩放
                ratio = viewWidth / zoomWidth; //视口宽度除以阀值, 计算缩放比例
            } else if (viewWidth < zoomWidth) { //当视口界于768与992之间时, bootstrap主宽度为750, 这区间图片缩放比例固定.
                ratio = zoomWidth / (zoomWidth + (zoomWidth - 750));
            } else { // PC端不缩放
                ratio = 1;
            }
            //ratio = viewWidth / zoomWidth; //视口宽度除以阀值, 计算缩放比例
            //ratio = (ratio<=1) ? ratio : 1; //如果比例值大于1, 说明视口宽度高于阀值, 则不进行任何缩放
            var width = maxWidth * ratio; //缩放宽度
            $(".my-slide img").each(function () {
                $(this).css({
                    "width": width,
                    "max-width": width,
                    "margin-left": -(width - viewWidth) / 2
                }); //图片自适应居中, 图片宽度与视口宽度差除以2的值, 设置为负margin
            });
        }
        /**通用-我们的成绩等数字滚动特效**/
        var numOptions = {
            useEasing: true,
            useGrouping: true,
            separator: ',',
            decimal: '.',
            prefix: '',
            suffix: ''
        }
        var numGroup = new Array(
                new CountUp("sum-apply", 0, 1044, 0, 2.5, numOptions),
                new CountUp("sum-rate", 0, 25.6, 1, 2.5, numOptions),
                new CountUp("sum-urgent", 0, 5262, 0, 2.5, numOptions),
                new CountUp("urgent-rate", 0, 473, 0, 2.5, numOptions)
        );
        var runNumber = function () {
            $('.run-number').each(function () {
                var oTop = $(this).offset().top;
                var sTop = $(window).scrollTop();
                var oHeight = $(this).height();
                var oIndex = $(this).index('.run-number');
                //console.log(oTop+'\r\n'+sTop+'\r\n'+oHeight+'\r\n'+$(window).height());
                if (oTop >= sTop && (oTop + (oHeight / 2)) < (sTop + $(window).height())) {
                    numGroup[oIndex].start();
                    //console.log('元素'+$(this).index('.run-number')+'可见');
                } else {
                    //console.log('元素'+$(this).index('.run-number')+'不可见');
                }
            });
        }

        zoomSlider(); //页面加载时初始化并检查一次.
        runNumber(); //页面加载时判断一次
        /**视口发生变化时的事件**/
        $(window).resize(function () {
            viewWidth = window.innerWidth; // 重置视口宽度
            zoomSlider();//判断是否绽放banner
            runNumber();//判断是否执行动画
        });
        /**滚动事件**/
        $(window).scroll(function () {
            runNumber();
        });

        //首页-我们的服务
        $('.card-item').each(function () {
            $(this).mouseover(function () {
                $(this).addClass('card-active');
                $(this).siblings().removeClass('card-active');
                $(this).find(".btn").addClass('btn-outline-inverse').removeClass('btn-outline-blue');
                $(this).siblings().find(".btn").addClass('btn-outline-blue').removeClass('btn-outline-inverse');
            });
        });

    });
</script>
</body>
</html>