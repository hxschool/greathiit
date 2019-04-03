<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<div class="col-xs-12 col-md-3 mt30 side hidden-xs ">

					<div class="widget-box clearfix"
						style="margin: 5px; border: 1px solid #CC9900; text-align: center;">
						<script type="text/javascript" charset="utf-8"
							src="${ctxStatic}/xuanke/home/Js/showtime.js"></script>
					</div>
					<div class="widget-box clearfix"
						style="margin: 5px; border: 1px solid #CC9900; text-align: center;">
						<font
							style="color: #000; font-family: 微软雅黑; font-size: 14pt; width: 100px;">距离选课结束还有
							<br> <span id="_d">00</span> <span id="_h">00</span> <span
							id="_m">00</span> <span id="_s">00</span>
						</font>
					</div>
					<div class="widget-box clearfix">
						<h2 class="h4 widget-box__title">分类列表(1)</h2>
						<div class="pcss3mm ">
							<ul id="pcss3mm" class="nav nav-pills" role="tablist">
								<li id="cate1"><a href="/xuanke/list-${category.id }.html">常见问题与解答</a></li>
								<li id="cate2"><a href="tel:18801029695">反馈消息</a></li>
							</ul>
						</div>
					</div>




					<div class="widget-box no-border">
						<h2 class="h4 widget-box__title">最新公告</h2>
						<ul class="widget-links list-unstyled">
							<c:forEach items="${articles}" var="article" varStatus="status">
								<c:set var="link"
									value="/xuanke/view-${article.category.id}-${article.id}${urlSuffix}"
									scope="session"></c:set>
								<c:if test="${!empty article.link }">
									<c:set var="link" value="${article.link}" scope="session"></c:set>
								</c:if>
								<li class="widget-links__item">${ status.index + 1}<a
									href="${link }" title="${article.title }">
										${fn:substring(article.title,0,25)}${fn:length(article.title)>25?'...':''}
								</a>
								</li>
							</c:forEach>

						</ul>
					</div>


				</div>
				
				<script>
				function countTime() {
				    //获取当前时间
				    var date = new Date();
				    var now = date.getTime();
				    //设置截止时间
				    var endDate = new Date("${fns:getDictLabel('end', 'select_course_end', '')}");
				    var end = endDate.getTime();
				    //时间差
				    var leftTime = end-now;
				    //定义变量 d,h,m,s保存倒计时的时间
				    var d='0',h='0',m='0',s='0';
				    if (leftTime>=0) {
				        d = Math.floor(leftTime/1000/60/60/24);
				        h = Math.floor(leftTime/1000/60/60%24);
				        m = Math.floor(leftTime/1000/60%60);
				        s = Math.floor(leftTime/1000%60);                   
				    }
				    //将倒计时赋值到div中
				    document.getElementById("_d").innerHTML = d+"天";
				    document.getElementById("_h").innerHTML = h+"时";
				    document.getElementById("_m").innerHTML = m+"分";
				    document.getElementById("_s").innerHTML = s+"秒";
				    //递归每秒调用countTime方法，显示动态时间效果
				    setTimeout(countTime,1000);

				}
				$(function () {
					countTime();
				})
				</script>