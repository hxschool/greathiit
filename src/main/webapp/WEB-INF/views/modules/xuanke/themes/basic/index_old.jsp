<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="xuanke_default_${site.theme}" />
<meta name="description"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.description} 选课系统" />
<meta name="keywords"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.keywords} 选课系统" />
</head>

<body>

	<c:set var="vEnter" value="\n" scope="request" />
	<c:set var="nowDate" value="<%= new java.util.Date()%>" />

	<% request.setAttribute("vEnter", "\n"); %>
	<div class="wrap maincontent">

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-md-3 mt30 side hidden-xs ">

					<div class="widget-box clearfix"
						style="margin: 3px; border-style: solid; border-color: #CC9900; text-align: center;">
						<script type="text/javascript" charset="utf-8"
							src="${ctxStatic}/xuanke/home/Js/showtime.js"></script>
					</div>
					<div class="widget-box clearfix"
						style="margin: 3px; border-style: solid; border-color: #CC9900; text-align: center;">
						<font style="color:#000;font-family:微软雅黑;font-size:14pt;width:100px;">距离选课结束还有
						<br>
						<span id="_d">00</span> <span id="_h">00</span> <span id="_m">00</span>
						<span id="_s">00</span>
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
								<li class="widget-links__item"><a href="${link }"
									title="${article.title }">${article.title }</a> <small
									class="text-muted"> ， ${article.hits } 浏览 </small></li>
							</c:forEach>

						</ul>
					</div>


				</div>



				<div class="col-xs-12 col-md-9 main mt30">
					<div class="panel panel-default panel-archive">
						<div class="panel-body">
							<!-- Nav tabs -->
							<div id="myAlert" class="alert alert-danger">
								<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>选课时间:1月16日7:00-1月21日07:00
									未在规定时间段选课同学的选课数据系统将自动清除。</strong>

							</div>
							<ul class=" nav nav-pills pb10 mb10 mt10">
								<li class="active"><a href="">已选课程</a></li>
								<c:forEach items="${selectCourses}" var="selectCourse"
									varStatus="status">
									<li><a>${selectCourse.course.cursName }</a></li>
								</c:forEach>
							</ul>
							<div style="min-height: 300px;">
								<form action="/xuanke" method="post" id="formsearch">
									<div class="example-code">
										<div class="form-label float-left">
											<label>课程名称：</label>
										</div>
										<div class="form-input col-md-2">
											<input type="text" value="" name="cursName" size="10" />
										</div>

										<div class="form-label float-left">
											<label>任课老师：</label>
										</div>
										<div class="form-input col-md-2">
											<input type="text" value="" name="teacher.tchrName" size="10" />
										</div>

										<button type="submit" class="btn medium bg-blue">
											<span class="button-content"> <i
												class="glyph-icon icon-search"></i>查询
											</span>
										</button>

										<a class="btn medium bg-orange"
											href="javascript:clearQuery(this)"> <span
											class="button-content"><i class="glyph-icon icon-undo"></i>
												清空查询</span>
										</a>

									</div>
									<!-- example-code -->
									<div class="divider"></div>
								</form>
								<br>
								<c:if test="${not empty message}">
									<div id="myAlert" class="alert alert-warning">
										<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>
											${message}</strong>
									</div>
								</c:if>
								<table class="dataTable table text-left" id="dataTables">
									<thead>
										<tr>
											<th>序号</th>
											<th>课程类别</th>
											<th>课程名称</th>

											<th>任课老师</th>
											<th>学时</th>
											<th>学分</th>
											<th>时间(地点)</th>
											<th class="text-center" width="120">操作</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${courses}" var="course" varStatus="status">
											<tr>
												<td>${status.index+1 }</td>
												<td>
												<c:choose>
												    <c:when test="${fns:startsWith(course.cursEduNum,'B')}">
												        	本科课程
												    </c:when>
												    <c:otherwise>
												       		高职/专科 课程
												    </c:otherwise>
												</c:choose>
												
												</td>
												<td>${course.cursName}</td>

												<td>${course.teacher.tchrName}</td>

												<td>${course.cursClassHour }</td>

												<td>${course.cursCredit}</td>
												<td>
												<!-- <c:if test="${course.cursLearningModel=='03'}">
												${fns:getDictLabel(course.cursLearningModel, "course_learning_model", "未知")}
												</c:if> <c:if test="${course.cursLearningModel!='03'}">
														<c:if test="${!empty courseScheduleMap[course.id]}">
															<c:set var="timeAdd"
																value="${courseScheduleMap[course.id].timeAdd}"
																scope="request" />
															 ${fnc:jiaoxuelou(fnc:GetTimeCol(timeAdd).school)} ${fnc:jiaoshi(fnc:GetTimeCol(timeAdd).school)} ${fnc:zhou(fnc:GetTimeCol(timeAdd).zhou)} ${fnc:jie(fnc:GetTimeCol(timeAdd).jie)} 
													</c:if>
													</c:if> -->
												</td>
												<td>
													<!-- ${isIndex } --> <c:if test="${not empty  fns:getUser().id}">

														<c:set var="bgColor" value="bg-green" scope="application"></c:set>
														<c:set var="bgIcon" value="icon-plus-sign"
															scope="application"></c:set>
														<c:set var="label" value="选课" scope="application"></c:set>
														<c:forEach items="${selectCourses}" var="selectCourse"
															varStatus="status">
															<c:if test="${course.id==selectCourse.course.id }">
																<c:set var="bgColor" value="bg-orange"
																	scope="application"></c:set>
																<c:set var="label" value="退课" scope="application"></c:set>
																<c:set var="bgIcon" value="icon-minus-sign"
																	scope="application"></c:set>
															</c:if>

														</c:forEach>
														
														
														<c:if test="${ret}">
															<a href="/xuanke/select?id=${course.id }"
															class="btn small ${bgColor }" target="ajaxTodo"><span
															class="button-content"><i
																class="glyph-icon ${bgIcon }"></i> ${label }</span></a>
														</c:if>
														
														<c:if test="${!ret and bgColor=='bg-orange'}">
															<button
															class="btn small bg-green" target="ajaxTodo"><span
															class="button-content">已选</span></button>
														</c:if>

													</c:if> <!-- <c:if test="${!isIndex or empty  fns:getUser().id}"></c:if> -->
													<a href="javascript:void(0)"
													onclick="showRemark('${course.cursName }',' ${fn:replace(course.cursIntro,vEnter,'')}');"
													class="btn small bg-blue"><span class="button-content">查看</span></a>

												</td>
											</tr>

											<!--<c:if test="${course.cursLearningModel=='01'}">
												<tr>
													<td colspan="7"
														style="border-top: 0px; border-bottom: 0px;"><c:set
															var="upperLimit" value="${course.upperLimit }"
															scope="request" /> <c:set var="count"
															value="${fnc:countStudents(course.id) }" scope="request" />
														<c:if test="${count>=upperLimit }">

														</c:if> <c:choose>
															<c:when test="${count>=upperLimit }">
																<div class="progress progress-striped active"
																	style="padding: 0px; margin: 0px; height: 5px;">
																	<div class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="60"
																		aria-valuemin="0" aria-valuemax="100"
																		style="width: 100%;">
																		<span class="sr-only">100% 完成</span>
																	</div>
																</div>
															</c:when>
															<c:otherwise>
																<div class="progress progress-striped active"
																	style="padding: 0px; margin: 0px; height: 5px;">
																	<div class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="60"
																		aria-valuemin="0" aria-valuemax="100"
																		style="width: ${(count/upperLimit)*100}%;">
																		<span class="sr-only">100% 完成</span>
																	</div>
																</div>
															</c:otherwise>
														</c:choose></td>
												</tr>
											</c:if>
											-->

										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>


			</div>
		</div>
	</div>

	<!-- ${empty  fns:getUser().id} -->

	<script type="text/javascript">
function showRemark(title,remark){
	if(remark==""){
		remark = "暂无简介";
	}
	layer.open({
		  type: 1,
		  title: title,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['380px', '320px'],
		  content: "<div style='margin:10px;'>"+remark+"</div>" //iframe的url
		}); 
}
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
</body>
</html>
