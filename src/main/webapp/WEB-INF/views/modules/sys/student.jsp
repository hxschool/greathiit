<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>


<link rel="stylesheet" href="${ctxStatic}/admin/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${ctxStatic}/admin/css/bootstrap3-wysihtml5.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${ctxStatic}/admin/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="${ctxStatic}/admin/css/ionicons.min.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="${ctxStatic}/admin/css/jquery-jvectormap.css">
<!-- Theme style -->
<link rel="stylesheet" href="${ctxStatic}/admin/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="${ctxStatic}/admin/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<script src="${ctxStatic}/echart/echarts.min.js"></script>
<script src="${ctxStatic}/echart/chart/map/china.js"></script>


</head>


<body class="hold-transition skin-blue sidebar-mini">
	<!-- Content Wrapper. Contains page content -->
	<div>
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				哈尔滨信息工程学院 <small>Version 1.0</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"></a></li>

			</ol>
		</section>
		<section class="content">
			<!-- Info boxes -->
			<div class="row">

				<!-- /.col -->
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-red"><i class="fa fa-home"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">校园占地面积</span> <span
								class="info-box-number">1044亩</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->


				<!-- /.col -->
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-yellow"><i
							class="ion ion-ios-people-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">MEMBER</span> <span
								class="info-box-number">11592</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- fix for small devices only -->
				<div class="clearfix visible-sm-block"></div>

				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-green"><i
							class="ion ion-ios-bookmarks-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">图书</span> <span
								class="info-box-number">76.8万册</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-aqua"><i
							class="ion ion-ios-gear-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">APP</span> <span
								class="info-box-number">10</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
			</div>




			<div class="row">
				<div class="col-md-8">

					<div class="box box-default">
						<div class="box-header with-border">
							<h3 class="box-title">校历</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="table-responsive">
								<img src="${ctxStatic}/admin/img/20181206150132.png" />
							</div>

						</div>

					</div>

				</div>





				<div class="col-md-4">

					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">公寓报修</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th>报修类型</th>
											<th>报修主题</th>
											<th>报修内容</th>

											<th>报修时间</th>
											<th>坐标</th>
											<th>报修状态</th>

										</tr>
									</thead>
									<tbody>
										<c:if test="${ucDormRepairs.size()==0}">
											<tr>
												<td colspan="3">暂无任何公告信息~</td>
											</tr>
										</c:if>
										<c:forEach items="${ucDormRepairs}" var="ucDormRepair">
											<tr>
												<td>${fns:getDictLabel(ucDormRepair.repairType, 'repair_type', 0)}</td>
												<td>${ucDormRepair.repairTitle}</td>
												<td>${ucDormRepair.repairContent}</td>

												<td><fmt:formatDate value="${ucDormRepair.createDate}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td>
													${ucDormRepair.dorm.ucDormBuild.id}栋${ucDormRepair.dorm.dormFloor}层${ucDormRepair.dorm.dormNumber}
												</td>
												<td>${fns:getDictLabel(ucDormRepair.repairState, 'repair_state', 0)}
												</td>
												<td>${fns:getUserById(ucDormRepair.operationId).name}</td>


											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>

					</div>

				</div>
				<div class="col-md-4">
					<!-- 应用系统 -->
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">应用系统</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th>编码</th>
											<th>名称</th>
											<th>运行状态</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="appconfig">
											<c:set var="j" value="label-success" />
											<c:set var="v" value="运行中" />
											<c:if test="${appconfig.status!='1' }">
												<c:set var="j" value="label-danger" />
												<c:set var="v" value="已停止" />
											</c:if>
											<tr>
												<td><a href="${appconfig.url }" target="_blank">${appconfig.id }</a></td>
												<td>${appconfig.name }</td>
												<td><span class="label ${j }">${v }</span></td>

											</tr>

										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>

					</div>


				</div>
			</div>

			<!-- /.row -->
			<div class="row">
				<div class="col-md-8">

					<div class="box box-default">
						<div class="box-header with-border">
							<h3 class="box-title">参观校园</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="table-responsive">
								<iframe src="https://720yun.com/t/e27jurymkf9" frameborder="no"
									width="100%" height="500"></iframe>
							</div>

						</div>

					</div>

				</div>





				<div class="col-md-4">

					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">公告</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<th>标题</th>
											<th>类型</th>
											<th>状态</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${oaNotifys.size()==0}">
											<tr>
												<td colspan="3">暂无任何公告信息~</td>
											</tr>
										</c:if>
										<c:forEach items="${oaNotifys}" var="oaNotify">
											<tr>
												<td><a
													href="${ctx}/oa/oaNotify/${requestScope.oaNotify.self?'view':'form'}?id=${oaNotify.id}">
														${fns:abbr(oaNotify.title,50)} </a></td>
												<td>${fns:getDictLabel(oaNotify.type, 'oa_notify_type', '')}
												</td>
												<td>${fns:getDictLabel(oaNotify.status, 'oa_notify_status', '')}
												</td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>

					</div>

				</div>

			</div>

			<!-- /.row -->
			<div class="row">
				<div class="col-md-12">
					<!-- 应用系统 -->
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">我的成绩</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="table-responsive">
								<table class="table no-margin">
									<thead>
										<tr>
											<td>学期</td>
											<td>课程</td>
											<td>其中成绩</td>
											<td>期末成绩</td>
											<td>综合成绩</td>
										</tr>
									</thead>

									<tbody>
										<c:if test="${studentCourses.size()==0}">
											<tr>
												<td colspan="3">暂无任何成绩信息~</td>
											</tr>
										</c:if>
										<c:forEach items="${studentCourses}" var="studentCourse">
										<tr>
											<td>${fns:termYear().get(studentCourse.termYear)}</td>
											<td>${studentCourse.course.cursName }</td>
											<td>${studentCourse.midEvaValue }</td>
											<td>${studentCourse.finEvaValue }</td>
											<td>${studentCourse.evaValue }</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>

					</div>


				</div>

				
			</div>
			
			<div class="row">
				<div class="col-md-6">
					
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">食堂订餐 </h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="table-responsive">
								需求持续整理中...
							</div>

						</div>

					</div>
				</div>
				
				
				<div class="col-md-6">
					<!-- 应用系统 -->
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">图书馆借阅情况</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn btn-box-tool"
									data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="table-responsive">
								系统升级中~
							</div>

						</div>

					</div>
				</div>
				
			</div>
		</section>
	</div>
	<!-- jQuery 3 -->
	<script src="${ctxStatic}/admin/js/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="${ctxStatic}/admin/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="${ctxStatic}/admin/js/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="${ctxStatic}/admin/js/adminlte.min.js"></script>

	<!-- jvectormap  -->
	<script src="${ctxStatic}/admin/js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="${ctxStatic}/admin/js/jquery-jvectormap-world-mill-en.js"></script>
	<!-- SlimScroll -->
	<script src="${ctxStatic}/admin/js/jquery.slimscroll.min.js"></script>
	<!-- ChartJS -->



	<script type="text/javascript">
		$(function() {
			$("#sendEmail").bind("click", function() {
				$.post("${ctx}/mail/send", {
					email : $("#emailto").val(),
					subject : $("#subject").val(),
					textarea : $("#textarea").val()
				}, function(result) {
					alert("send is ok");
				});
			});
			var yearChart = echarts.init(document
					.getElementById('edu-line-markers'));
			yearChart.showLoading();
			$.get('${ctx}/uc/student/year.json', function(data) {
				yearChart.hideLoading();
				var xAxData = [];
				var labData = [];

				for (var i = 0; i < data.length; i++) {
					var item = data[i];
					xAxData.push(item.TOTAL || "");
					labData.push(item.LABEL || "");
				}
				yearChart.hideLoading();
				console.log(xAxData);
				console.log(labData);
				yearChart.setOption(option = {
					xAxis : {
						type : 'category',
						data : labData
					},
					yAxis : {
						type : 'value'
					},
					series : [ {
						data : xAxData,
						type : 'line'
					} ],

				});
			});

			var eduChart = echarts.init(document.getElementById('edu-markers'));
			eduChart.showLoading();
			$.get('${ctx}/uc/student/edu.json', function(data) {
				eduChart.hideLoading();
				var xAxData = [];
				var serData = [];
				for (var i = 0; i < data.length; i++) {
					var item = data[i];
					xAxData.push(item.NAME || "");
					serData.push({
						name : item.NAME,
						value : item.TOTAL || 0
					});
				}

				option = {
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b}: {c} ({d}%)"
					},

					legend : {
						orient : 'vertical',
						x : 'left',
						data : xAxData
					},
					color : [ 'red', 'blueviolet' ],
					series : [ {
						name : '专业统计',
						type : 'pie',

						avoidLabelOverlap : false,
						label : {
							normal : {
								show : false,
								position : 'center'
							},
							emphasis : {
								show : true,
								textStyle : {
									fontSize : '30',
									fontWeight : 'bold'
								}
							}
						},
						labelLine : {
							normal : {
								show : false
							}
						},
						data : serData
					} ]
				};
				eduChart.setOption(option);
			})

			var myChart = echarts.init(document.getElementById('main'));
			myChart.showLoading();
			$.get('${ctx}/sys/information/greathiit/greathiit.json', function(
					data) {
				myChart.hideLoading();
				myChart.setOption(option = {

					tooltip : {
						trigger : 'item',
						triggerOn : 'mousemove'
					},
					series : [ {
						type : 'tree',

						data : [ data ],

						top : '1%',
						left : '7%',
						bottom : '1%',
						right : '20%',

						symbolSize : 7,

						label : {
							normal : {
								position : 'left',
								verticalAlign : 'middle',
								align : 'right',
								fontSize : 9
							}
						},

						leaves : {
							label : {
								normal : {
									position : 'right',
									verticalAlign : 'middle',
									align : 'left'
								}
							}
						},

						expandAndCollapse : true,
						animationDuration : 550,
						animationDurationUpdate : 750
					} ]
				});
			});

			//基于准备好的dom，初始化echarts实例
			var wordChart = echarts.init(document
					.getElementById('world-map-markers'));
			var wordTotal = 0;
			var wordBoy = 0;
			var wordGril = 0;
			wordChart.showLoading();
			$.get('${ctx}/uc/student/region.json', function(data) {
				wordChart.hideLoading();
				var xAxData = [];
				var serDataBoy = [];
				var serDataGril = [];
				var labData = [];

				for (var i = 0; i < data.length; i++) {
					var item = data[i];
					xAxData.push(item.NAME || "");
					labData.push(item.LABEL || "");
					wordTotal = wordTotal + item.TOTAL;
					if (item.LABEL == "1") {
						wordBoy = wordBoy + item.TOTAL;
						serDataBoy.push({
							name : item.NAME,
							value : item.TOTAL || 0
						});
					} else {
						wordGril = wordGril + item.TOTAL;
						serDataGril.push({
							name : item.NAME,
							value : item.TOTAL || 0
						});
					}
				}
				$("#wordTotal").html(wordTotal);
				$("#wordBoy").html(wordBoy);
				$("#wordGril").html(wordGril);
				option = {
					tooltip : {
						trigger : 'item'
					},

					legend : {
						orient : 'vertical',
						x : 'left',
						data : [ '男', '女' ]
					},
					dataRange : {
						min : 0,
						max : 8000,
						color : [

						'#82b6e9', '#ff6347', '#a092f1', '#0a915d', '#eaf889',
								'#a4d8c2', '#f3d999', '#d3758f', '#dcc392',
								'#2e4783', '#6699FF', '#ff6666', '#3cb371',
								'#d5b158', '#38b6b6', '#E01F54', '#001852',
								'#f5e8c8', '#b8d2c7', '#c6b38e' ],
						x : 'left',
						y : 'bottom',
						text : [ '高', '低' ], // 文本，默认为数值文本
						calculable : true
					},

					roamController : {
						show : true,
						x : 'right',
						mapTypeControl : {
							'china' : true
						}
					},
					series : [ {
						name : '男',
						type : 'map',
						mapType : 'china',
						roam : false,
						itemStyle : {
							normal : {
								label : {
									show : true
								}
							},
							emphasis : {
								label : {
									show : true
								}
							}
						},

						data : serDataBoy
					}, {
						name : '女',
						type : 'map',
						mapType : 'china',
						itemStyle : {
							normal : {
								areaColor : '#dec313',
								label : {
									show : true
								}
							},
							emphasis : {
								label : {
									show : true
								}
							}
						},
						data : serDataGril
					} ]
				};
				wordChart.setOption(option);
			});
			var sexChart = echarts.init(document.getElementById('sex-markers'));
			$.get('${ctx}/uc/student/sex.json', function(data) {

				var xAxData = [];
				var serData = [];
				for (var i = 0; i < data.length; i++) {
					var item = data[i];
					xAxData.push(item.NAME || "");
					serData.push({
						name : item.NAME,
						value : item.TOTAL || 0
					});
				}

				option = {
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b}: {c} ({d}%)"
					},
					toolbox : {
						show : true,
						orient : 'vertical',
						x : 'right',
						y : 'center',
						feature : {
							mark : {
								show : true
							},
							magicType : {
								show : true,
								type : [ 'pie', 'funnel' ]
							},

						},

					},
					legend : {
						orient : 'vertical',
						x : 'left',
						data : xAxData
					},
					series : [ {
						name : '性别统计',
						type : 'pie',
						radius : [ '50%', '70%' ],
						avoidLabelOverlap : false,
						label : {
							normal : {
								show : false,
								position : 'center'
							},
							emphasis : {
								show : true,
								textStyle : {
									fontSize : '30',
									fontWeight : 'bold'
								}
							}
						},
						labelLine : {
							normal : {
								show : false
							}
						},
						data : serData
					} ]
				};
				sexChart.setOption(option);
			})

		});
	</script>
</body>
</html>
