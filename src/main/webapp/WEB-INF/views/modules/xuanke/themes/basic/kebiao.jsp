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



	<div class="wrap maincontent">

		<div class="container-fluid">
			<div class="row">
				<%@include file="/WEB-INF/views/modules/xuanke/include/left.jsp"%>

				<div class="col-xs-12 col-md-9 main mt30">
					<div class="panel panel-default panel-archive">
						<div class="panel-body">
							
							<div style="min-height: 300px;">
								
								<br>


								<table class="dataTable table text-left" id="dataTables">
									<thead>
										<tr>
											<th>课程</th>
											<th>教师</th>
											<th>时间</th>
											<th>地点</th>
											<th>班级</th>
											<th>备注</th>
											<th>资料</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${courseScheduleExts}" var="courseScheduleExt"
									varStatus="status">
										<tr>
											<td>${courseScheduleExt.cursName }</td>
											<td>${courseScheduleExt.tchrName }</td>
											<td>第${fnc:GetTimeCol(courseScheduleExt.timeAdd).week}周 ${fnc:zhou(fnc:GetTimeCol(courseScheduleExt.timeAdd).zhou)} ${fnc:jie(fnc:GetTimeCol(courseScheduleExt.timeAdd).jie)}</td>
											<td>${fnc:jiaoxuelou(fnc:GetTimeCol(courseScheduleExt.timeAdd).school)}${fnc:jiaoshi(fnc:GetTimeCol(courseScheduleExt.timeAdd).school)}</td>
											<td>${courseScheduleExt.courseClass }</td>
											<td>${courseScheduleExt.remarks }</td>
											<td></td>
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

	<!-- ${empty  fns:getUser().id} -->


</body>
</html>