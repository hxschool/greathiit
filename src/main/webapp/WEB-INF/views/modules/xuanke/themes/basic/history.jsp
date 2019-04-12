
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="xuanke_default_${site.theme}" />
<meta name="description"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.description} -选课系统" />
<meta name="keywords"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.keywords} -选课系统" />
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "/xuanke/history");
		$("#searchForm").submit();
		return false;
	}
</script>
<style type="text/css">
.pagination {
	display: inline-block;
	padding: 0;
	margin: 0;
}

.pagination li {
	display: inline;
}

.pagination li a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	transition: background-color .3s;
	border: 1px solid #ddd;
}

.pagination li a.active {
	background-color: #4CAF50;
	color: white;
	border: 1px solid #4CAF50;
}

.pagination
 
li
 
a
:hover
:not
 
(
.active
 
)
{
background-color:#ddd;
}
.pagination li input {
	width: 24px;
	height: 20px;
}

div.center {
	text-align: center;
}

</style>
</head>
<body>


	<div class="wrap maincontent">

		<div class="container-fluid">
			<div class="row">
				<%@include file="/WEB-INF/views/modules/xuanke/include/left.jsp"%>

				<div class="col-xs-12 col-md-9 main">
					<p class="main-title hidden-xs"></p>
					<div class="main__board  panel panel-default panel-body">
						<form id="searchForm" action="/xuanke/history" method="post"
							class="breadcrumb form-search ">
							<input id="pageNo" name="pageNo" type="hidden"
								value="${page.pageNo}" /> <input id="pageSize" name="pageSize"
								type="hidden" value="${page.pageSize}" />

							<div class="box-body">
								<div class="row">

									<div class="col-md-8 col-xs-12">
										<div class="form-group">

											<div class="col-md-4 col-xs-12">
												<input type="text" value="${param.cursName}"
													placeholder="请输入课程名称..." name="cursName" size="10"
													class="form-control" />
													
											</div>
											<div class="col-md-4 col-xs-12">
												<select name="status" class="form-control">
													<option>请选择操作类型</option>
													<option value="20">选课</option>
													<option value="30">退课</option>
												</select>
											</div>
											<div class="col-md-4 col-xs-12">
												<button type="submit" class="btn btn-info">
													<i class="glyph-icon icon-search"></i> 查询
												</button>

											</div>
										</div>
									</div>


								</div>
							</div>

						</form>

						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading">选课操作记录</div>
									<div class="panel-body">
									
									
									
										<div class="table-responsive">
										
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>#</th>
														<th>课程名称</th>
														<th>操作过程</th>
														<th>操作IP</th>
														<th>选课时间</th>
														<th class="text-center" width="80px;">详情</th>
													</tr>
												</thead>
												<tbody>
												<c:if test="${page.list== null || fn:length(page.list) == 0}">
												<tr>
															<td colspan="6" class="text-center text-danger">暂无数据</td>
															</tr>
												</c:if>
												
													<c:forEach items="${page.list}" var="userOperationLog" varStatus="status">
														<tr >
															<td>${status.index+1}</td>
															<td>${userOperationLog.moduleName}</td>
															<td><c:choose>
																	<c:when test="${userOperationLog.status==20}">
																<a class="btn btn-success btn-xs">选课</a>
																</c:when>
																	<c:when test="${userOperationLog.status==30}">
																<a class="btn btn-danger btn-xs">退课</a>
																</c:when>
																</c:choose></td>

															<td class="center">${userOperationLog.remoteAddr}</td>
															<td class="center"><fmt:formatDate type="both"
																	value="${userOperationLog.createDate}" /></td>
															<td class="center"><a href="javascript:void(0)"
																onclick="ajaxCourse('${userOperationLog.moduleId}','${userOperationLog.status}','<fmt:formatDate type="both" value="${userOperationLog.createDate}" />','${userOperationLog.remoteAddr}','${userOperationLog.userAgent}','${userOperationLog.remarks}');"
																class="btn small bg-blue"><span
																	class="button-content"><i
																		class="glyph-icon icon-search"></i> 查看</span></a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>

											<div class="pagination">${page}</div>

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
	<script type="text/javascript">
		function ajaxCourse(courseId, status, createDate, remoteAddr,
				userAgent, remarks) {
			var opt = "退课";
			if (status == 20) {
				opt = "选课";
			}
			$
					.ajax({
						url : '/a/course/course/ajaxCourse?id=' + courseId,
						success : function(course) {
							layer
									.open({
										type : 1,
										title : course.cursName,
										shadeClose : true,
										shade : 0.8,
										area : [ '800px', '600px' ],
										content : "<div style='margin:10px;'><p><a class=\"btn btn-success btn-xs\">"
												+ opt
												+ "</a> 课程名称:"
												+ course.cursName
												+ "</p><p>教师:"
												+ course.teacher.tchrName
												+ "</p><p>学时:"
												+ course.cursClassHour
												+ "</p>"
												+ "<p>操作时间:"
												+ createDate
												+ "</p>"
												+ "<p>请求IP:"
												+ remoteAddr
												+ "</p>"
												+ "<p>请求浏览器:"
												+ userAgent
												+ "</p>"
												+ "<p>学分:"
												+ course.cursCredit
												+ "</p>"
												+ "<p>学分:"
												+ course.cursIntro
												+ "</p>"
												+ "<p>操作过程:"
												+ remarks
												+ "</p></div>"
									});
						}
					});
		}
	</script>
</body>
</html>
