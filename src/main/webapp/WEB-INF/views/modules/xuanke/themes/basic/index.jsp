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
	<c:set var="nowDate" value="<%=new java.util.Date()%>" />

	<%
		request.setAttribute("vEnter", "\n");
	%>
	<div class="wrap maincontent">

		<div class="container-fluid">
			<div class="row">
				<%@include file="/WEB-INF/views/modules/xuanke/include/left.jsp"%>
				<div class="col-xs-12 col-md-9 main mt30">
					<div class="panel panel-default panel-archive">
						<div class="panel-body">
							<!-- Nav tabs -->
							<div id="myAlert" class="alert alert-danger">
								<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>${config.tip}</strong>
							</div>
							<ul class=" nav nav-pills pb10 mb10 mt10">
								<li><button class="btn btn-danger">已选课程</button> </li>
								
								<c:forEach items="${selectedCourseMap}" var="selectCourse"
									varStatus="status">
									<li><button class="btn btn-success" onclick="ajaxCourse('${selectCourse.key}');">${selectCourse.value}</button></li>
								</c:forEach>
							</ul>
							
							
							<form action="/xuanke" method="post" class="form-horizontal">
								<div class="box-body">
									<div class="row">
										<div class="col-md-4 col-xs-12">
											<div class="form-group">
												<label class="control-label col-sm-4" title="">
													课程名称：<i class="fa icon-question hide"></i>
												</label>
												<div class="col-sm-8">
													<input type="text" value="" name="cursName" size="10"
														class="form-control" />
												</div>
											</div>
										</div>
										<div class="col-md-4 col-xs-12 ">
											<div class="form-group">
												<label class="control-label col-sm-4" title="">
													任课老师：<i class="fa icon-question hide"></i>
												</label>
												<div class="col-sm-8">
													<input type="text" value="" name="teacher.tchrName"
														size="10" class="form-control" />
												</div>
											</div>
										</div>
										<div class="col-md-4 col-xs-12">
											<button type="submit" class="btn btn-info">
												<i class="glyph-icon icon-search"></i> 查询
											</button>
											<a class="btn medium bg-orange"
												href="javascript:clearQuery(this)"> <span
												class="button-content"><i
													class="glyph-icon icon-undo"></i> 清空查询</span>
											</a>
										</div>
									</div>
								</div>
							</form>


							<br>
							<c:if test="${not empty message}">
								<div id="myAlert" class="alert alert-warning">
									<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>
										${message}</strong>
								</div>
							</c:if>

							<div class="row">
								<div class="col-xs-12 col-md-12 ">
									<div class="panel-body">
										<div class="table-responsive">
											<table class="table table-striped table-bordered table-hover" >
												<thead>
													<tr>
														<th class="text-center">#</th>
														<th>课程类别</th>
														<th>课程名称</th>
														<th>任课老师</th>
														<th>学时</th>
														<th>学分</th>
														<th>时间(地点)</th>
														<th class="text-center" width="170">操作</th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${courses}" var="course"
														varStatus="status">
														<tr <c:if test="${selectedCourseMap[course.id]!=null and selectedCourseMap[course.id]!=''}">  class="success"  </c:if> >
															<td class="text-center">${status.index+1 }  
															
															
															</td>
															<td><c:choose>
																	<c:when test="${fns:startsWith(course.cursEduNum,'B')}">
												        				本科课程
																    </c:when>
																	<c:otherwise>
																       		高职/专科 课程
																    </c:otherwise>
																</c:choose></td>
															<td>${course.cursName}</td>

															<td>${course.teacher.tchrName}</td>

															<td>${course.cursClassHour }</td>

															<td>${course.cursCredit}</td>
															<td>
																<c:if test="${course.courseTeachingMode!=null}">
																	<c:choose>
																		<c:when
																			test="${course.courseTeachingMode.teacMethod=='01'}">
																			<c:if test="${!empty courseScheduleMap[course.id]}">
																				<c:set var="timeAdd"
																					value="${courseScheduleMap[course.id].timeAdd}"
																					scope="request" />
																					${fnc:jiaoxuelou(fnc:GetTimeCol(timeAdd).school)} ${fnc:jiaoshi(fnc:GetTimeCol(timeAdd).school)} ${fnc:zhou(fnc:GetTimeCol(timeAdd).zhou)} ${fnc:jie(fnc:GetTimeCol(timeAdd).jie)} 
																			</c:if>
																		</c:when>
																		<c:otherwise>
																			${fns:getDictLabel(course.courseTeachingMode.teacMethod, "teac_method", "未知")}
																		</c:otherwise>
																	</c:choose>
																</c:if>



															</td>
															<td>
															<a href="javascript:void(0)"
																onclick="showRemark('${course.cursName }',' ${fn:replace(course.cursIntro,vEnter,'')}');"
																class="btn small bg-blue"><span
																	class="button-content"><i class="glyph-icon icon-search"></i> 查看</span></a>
																<c:if
																	test="${not empty  fns:getUser().id}">

																	<c:set var="bgColor" value="bg-green"
																		scope="application"></c:set>
																	<c:set var="bgIcon" value="icon-plus-sign"
																		scope="application"></c:set>
																		<c:if test="${isSelected}">
																	<c:set var="label" value="选课" scope="application"></c:set>
																	
																		<c:if test="${selectedCourseMap[course.id]!=null and selectedCourseMap[course.id]!=''}">
																			<c:set var="bgColor" value="bg-orange"
																				scope="application"></c:set>
																			<c:set var="label" value="退课" scope="application"></c:set>
																			<c:set var="bgIcon" value="icon-minus-sign"
																				scope="application"></c:set>
																		</c:if>

																		</c:if>


																	<c:if test="${ret}">
																		<a href="/xuanke/select?id=${course.id }"
																			class="btn small ${bgColor }" target="ajaxTodo"><span
																			class="button-content"><i
																				class="glyph-icon ${bgIcon }"></i> ${label }</span></a>
																	</c:if>

																	<c:if test="${!ret and bgColor=='bg-orange'}">
																		<button class="btn small bg-green" target="ajaxTodo">
																			<span class="button-content">已选</span>
																		</button>
																	</c:if>

																</c:if> 
																

															</td>
														</tr>
<tr>
													<td colspan="8"
														style="border-top: 0px; border-bottom: 0px;"><c:set
															var="upperLimit" value="${course.upperLimit }"
															scope="request" /> <c:set var="count"
															value="${fnc:countStudents(course.id) }" scope="request" />
														<c:if test="${count>=upperLimit }">

														</c:if> <c:choose>
															<c:when test="${count>=upperLimit }">
																<div class="progress progress-striped active"
																	style="padding: 0px; margin: 0px; height: 2px;">
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
																	style="padding: 0px; margin: 0px; height: 2px;">
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
		  content: "<div style='margin:10px;'>"+remark+"</div>"
		}); 
}

function ajaxCourse(courseId){
	  $.ajax({url:'/a/course/course/ajaxCourse?id='+courseId,success:function(course){
		  layer.open({
			  type: 1,
			  title: course.cursName,
			  shadeClose: true,
			  shade: 0.8,
			  area: ['800px', '600px'],
			  content: "<div style='margin:10px;'><p>课程名称:"+course.cursName+"</p><p>教师:"+course.teacher.tchrName+"</p><p>学时:"+course.cursClassHour+"</p><p>学分:"+course.cursCredit+"</p>"+course.cursIntro+"</div>"
			}); 
	    }});
}
</script>

</body>
</html>
