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
						<span class="info-box-icon bg-red"><i
							class="fa fa-home"></i></span>

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
			<!-- /.row -->

			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">学院树状图</h3>

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
							<div class="row">
								<div class="col-md-12" id="main" style="height: 480px;"></div>
								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>
						<!-- ./box-body -->
						<div class="box-footer">
							<div class="row">
								<div class="col-sm-3 col-xs-6">
									<div class="description-block border-right">
										<span class="description-percentage text-green"><i
											class="fa fa-caret-up"></i> 0%</span>
										<h5 class="description-header">1044亩</h5>
										<span class="description-text">校园占地面积</span>
									</div>
									<!-- /.description-block -->
								</div>
								<!-- /.col -->
								<div class="col-sm-3 col-xs-6">
									<div class="description-block border-right">
										<span class="description-percentage text-yellow"><i
											class="fa fa-caret-left"></i> 0%</span>
										<h5 class="description-header">5261.18万元</h5>
										<span class="description-text">教学仪器设备总值</span>
									</div>
									<!-- /.description-block -->
								</div>
								<!-- /.col -->
								<div class="col-sm-3 col-xs-6">
									<div class="description-block border-right">
										<span class="description-percentage text-green"><i
											class="fa fa-caret-up"></i> 0%</span>
										<h5 class="description-header">25.6万平方米</h5>
										<span class="description-text">校舍建筑面积</span>
									</div>
									<!-- /.description-block -->
								</div>
								<!-- /.col -->
								<div class="col-sm-3 col-xs-6">
									<div class="description-block">
										<span class="description-percentage text-red"><i
											class="fa fa-caret-down"></i> 0%</span>
										<h5 class="description-header">473人</h5>
										<span class="description-text">专任教师</span>
									</div>
									<!-- /.description-block -->
								</div>
							</div>
							<!-- /.row -->
						</div>
						<!-- /.box-footer -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
			<div class="row">
				<!-- Left col -->

				<div class="col-md-12">
					<!-- MAP & BOX PANE -->
					<div class="box box-success">
						<div class="box-header with-border">
							<h3 class="box-title">历年招生趋势</h3>

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
						<div class="box-body no-padding">
							<div class="row">
								<div class="col-md-9 col-sm-8">
									<div class="pad">
										<!-- Map will be created here -->
										<div id="edu-line-markers" style="height: 500px;"></div>
									</div>
								</div>
								<!-- /.col -->
								<div class="col-md-3 col-sm-4">
									<div class="pad box-pane-right bg-green"
										style="min-height: 80px">
										<div class="description-block margin-bottom">
											<div class="sparkbar pad" data-color="#fff">全国招生总人数</div>
											<h5 class="description-header" >8142</h5>

										</div>
										<!-- /.description-block -->
										<div class="description-block margin-bottom">
											<div class="sparkbar pad" data-color="#fff">其中本科</div>
											<h5 class="description-header" id="eduBoy">3764</h5>
										</div>
										<!-- /.description-block -->
										<div class="description-block">
											<div class="sparkbar pad" data-color="#fff">其中专科</div>
											<h5 class="description-header" id="eduGril">4378</h5>
										</div>
										<!-- /.description-block -->
									</div>
								</div>
								<div class="col-md-3 col-sm-4">
									<div id="edu-markers" style="width: 100%; height: 250px;"></div>
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.box-body -->
					</div>
				</div>
				
			</div>
			<!-- Main row -->
			<div class="row">
				<!-- Left col -->
				<div class="col-md-8">
					<!-- MAP & BOX PANE -->
					<div class="box box-success">
						<div class="box-header with-border">
							<h3 class="box-title">全国招生统计</h3>

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
						<div class="box-body no-padding">
							<div class="row">
								<div class="col-md-9 col-sm-8">
									<div class="pad">
										<!-- Map will be created here -->
										<div id="world-map-markers" style="height: 500px;"></div>
									</div>
								</div>
								<!-- /.col -->
								<div class="col-md-3 col-sm-4">
									<div class="pad box-pane-right bg-green"
										style="min-height: 80px">
										<div class="description-block margin-bottom">
											<div class="sparkbar pad" data-color="#fff">全国招生总人数</div>
											<h5 class="description-header" id="wordTotal">8390</h5>
											
										</div>
										<!-- /.description-block -->
										<div class="description-block margin-bottom">
											<div class="sparkbar pad" data-color="#fff">其中男生</div>
											<h5 class="description-header" id="wordBoy"></h5>
										</div>
										<!-- /.description-block -->
										<div class="description-block">
											<div class="sparkbar pad" data-color="#fff">其中女生</div>
											<h5 class="description-header" id="wordGril"></h5>
										</div>
										<!-- /.description-block -->
									</div>
								</div>
								<div class="col-md-3 col-sm-4">
									<div id="sex-markers" style="width:250px;height: 250px;"></div>
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
					
					<!-- email -->


					
				</div>

				<!-- /.col -->

				<div class="col-md-4">
					<!-- Info Boxes Style 2 -->
					<div class="info-box bg-yellow">
						<span class="info-box-icon"><i
							class="ion ion-ios-cloud-download-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">软件学院</span> <span
								class="info-box-number">5,185</span>

							<div class="progress">
								<div class="progress-bar" style="width: 63%"></div>
							</div>
							<span class="progress-description"> 64% member in 8142
								 </span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box bg-green">
						<span class="info-box-icon"><i
							class="ion ion-ios-heart-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">艺术设计学院</span> <span
								class="info-box-number">1,275</span>

							<div class="progress">
								<div class="progress-bar" style="width: 15%"></div>
							</div>
							<span class="progress-description"> 15% member in 8142
								 </span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box bg-red">
						<span class="info-box-icon"><i
							class="ion ion-ios-pricetag-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">商学院</span> <span
								class="info-box-number">1,276</span>

							<div class="progress">
								<div class="progress-bar" style="width: 16%"></div>
							</div>
							<span class="progress-description"> 16% member in 8142
								 </span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box bg-aqua">
						<span class="info-box-icon"><i
							class="ion-ios-chatbubble-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">电子工程学院</span> <span
								class="info-box-number">406</span>

							<div class="progress">
								<div class="progress-bar" style="width: 5%"></div>
							</div>
							<span class="progress-description"> 5% member in 8142 </span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->

					

					

					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
			<div class="row">
						<div class="col-md-8">
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
						
						<div class="col-md-4">
						<div class="box box-info">
						<div class="box-header ui-sortable-handle" style="cursor: move;">
							<i class="fa fa-envelope"></i>

							<h3 class="box-title">Quick Email</h3>
							<!-- tools box -->
							<div class="pull-right box-tools">
								<button type="button" class="btn btn-info btn-sm"
									data-widget="remove" data-toggle="tooltip" title=""
									data-original-title="Remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
							<!-- /. tools -->
						</div>
						<div class="box-body">
							<form action="#" method="post">
								<div class="form-group">
									<input class="form-control" id="emailto" name="emailto"
										placeholder="Email to:" type="email">
								</div>
								<div class="form-group">
									<input class="form-control" id="subject" name="subject"
										placeholder="Subject" type="text">
								</div>
								<div>
									<ul class="wysihtml5-toolbar" style="">
										<li class="dropdown"><a
											class="btn btn-default dropdown-toggle "
											data-toggle="dropdown"> <span
												class="glyphicon glyphicon-font"></span> <span
												class="current-font">Normal text</span> <b class="caret"></b>
										</a>
											<ul class="dropdown-menu">
												<li><a data-wysihtml5-command="formatBlock"
													data-wysihtml5-command-value="p" tabindex="-1"
													href="javascript:;" unselectable="on">Normal text</a></li>
												<li><a data-wysihtml5-command="formatBlock"
													data-wysihtml5-command-value="h1" tabindex="-1"
													href="javascript:;" unselectable="on">Heading 1</a></li>
												<li><a data-wysihtml5-command="formatBlock"
													data-wysihtml5-command-value="h2" tabindex="-1"
													href="javascript:;" unselectable="on">Heading 2</a></li>
												<li><a data-wysihtml5-command="formatBlock"
													data-wysihtml5-command-value="h3" tabindex="-1"
													href="javascript:;" unselectable="on">Heading 3</a></li>
												<li><a data-wysihtml5-command="formatBlock"
													data-wysihtml5-command-value="h4" tabindex="-1"
													href="javascript:;" unselectable="on">Heading 4</a></li>
												<li><a data-wysihtml5-command="formatBlock"
													data-wysihtml5-command-value="h5" tabindex="-1"
													href="javascript:;" unselectable="on">Heading 5</a></li>
												<li><a data-wysihtml5-command="formatBlock"
													data-wysihtml5-command-value="h6" tabindex="-1"
													href="javascript:;" unselectable="on">Heading 6</a></li>
											</ul></li>
										<li>
											<div class="btn-group">
												<a class="btn  btn-default" data-wysihtml5-command="bold"
													title="CTRL+B" tabindex="-1" href="javascript:;"
													unselectable="on">Bold</a> <a class="btn  btn-default"
													data-wysihtml5-command="italic" title="CTRL+I"
													tabindex="-1" href="javascript:;" unselectable="on">Italic</a>
												<a class="btn  btn-default"
													data-wysihtml5-command="underline" title="CTRL+U"
													tabindex="-1" href="javascript:;" unselectable="on">Underline</a>

												<a class="btn  btn-default" data-wysihtml5-command="small"
													title="CTRL+S" tabindex="-1" href="javascript:;"
													unselectable="on">Small</a>

											</div>
										</li>
										<li><a class="btn  btn-default"
											data-wysihtml5-command="formatBlock"
											data-wysihtml5-command-value="blockquote"
											data-wysihtml5-display-format-name="false" tabindex="-1"
											href="javascript:;" unselectable="on"> <span
												class="glyphicon glyphicon-quote"></span>

										</a></li>
										<li>
											<div class="btn-group">
												<a class="btn  btn-default"
													data-wysihtml5-command="insertUnorderedList"
													title="Unordered list" tabindex="-1" href="javascript:;"
													unselectable="on"> <span
													class="glyphicon glyphicon-list"></span>

												</a> <a class="btn  btn-default"
													data-wysihtml5-command="insertOrderedList"
													title="Ordered list" tabindex="-1" href="javascript:;"
													unselectable="on"> <span
													class="glyphicon glyphicon-th-list"></span>

												</a> <a class="btn  btn-default"
													data-wysihtml5-command="Outdent" title="Outdent"
													tabindex="-1" href="javascript:;" unselectable="on"> <span
													class="glyphicon glyphicon-indent-right"></span>

												</a> <a class="btn  btn-default" data-wysihtml5-command="Indent"
													title="Indent" tabindex="-1" href="javascript:;"
													unselectable="on"> <span
													class="glyphicon glyphicon-indent-left"></span>

												</a>
											</div>
										</li>
										<li>
											<div class="bootstrap-wysihtml5-insert-link-modal modal fade"
												data-wysihtml5-dialog="createLink">
												<div class="modal-dialog ">
													<div class="modal-content">
														<div class="modal-header">
															<a class="close" data-dismiss="modal">×</a>
															<h3>Insert link</h3>
														</div>
														<div class="modal-body">
															<div class="form-group">
																<input value="http://"
																	class="bootstrap-wysihtml5-insert-link-url form-control"
																	data-wysihtml5-dialog-field="href">
															</div>
															<div class="checkbox">
																<label> <input
																	class="bootstrap-wysihtml5-insert-link-target"
																	checked="" type="checkbox">Open link in new
																	window
																</label>
															</div>
														</div>
														<div class="modal-footer">
															<a class="btn btn-default" data-dismiss="modal"
																data-wysihtml5-dialog-action="cancel" href="#">Cancel</a>
															<a href="#" class="btn btn-primary" data-dismiss="modal"
																data-wysihtml5-dialog-action="save">Insert link</a>
														</div>
													</div>
												</div>
											</div> <a class="btn  btn-default"
											data-wysihtml5-command="createLink" title="Insert link"
											tabindex="-1" href="javascript:;" unselectable="on"> <span
												class="glyphicon glyphicon-share"></span>

										</a>
										</li>
										<li>
											<div
												class="bootstrap-wysihtml5-insert-image-modal modal fade"
												data-wysihtml5-dialog="insertImage">
												<div class="modal-dialog ">
													<div class="modal-content">
														<div class="modal-header">
															<a class="close" data-dismiss="modal">×</a>
															<h3>Insert image</h3>
														</div>
														<div class="modal-body">
															<div class="form-group">
																<input value="http://"
																	class="bootstrap-wysihtml5-insert-image-url form-control"
																	data-wysihtml5-dialog-field="src">
															</div>
														</div>
														<div class="modal-footer">
															<a class="btn btn-default" data-dismiss="modal"
																data-wysihtml5-dialog-action="cancel" href="#">Cancel</a>
															<a class="btn btn-primary" data-dismiss="modal"
																data-wysihtml5-dialog-action="save" href="#">Insert
																image</a>
														</div>
													</div>
												</div>
											</div> <a class="btn  btn-default"
											data-wysihtml5-command="insertImage" title="Insert image"
											tabindex="-1" href="javascript:;" unselectable="on"> <span
												class="glyphicon glyphicon-picture"></span>

										</a>
										</li>
									</ul>
									<textarea class="textarea" id="textarea"
										style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid rgb(221, 221, 221); padding: 10px; "
										placeholder="Message"></textarea>
									
								</div>
							</form>
						</div>
						<div class="box-footer clearfix">
							<button type="button" class="pull-right btn btn-default"
								id="sendEmail">
								Send <i class="fa fa-arrow-circle-right"></i>
							</button>
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
	$(function(){
		$("#sendEmail").bind("click",function(){
			$.post("${ctx}/mail/send",{email:$("#emailto").val(),subject:$("#subject").val(),textarea:$("#textarea").val()},function(result){
			    alert("send is ok");
			  });
		});
		var yearChart = echarts.init(document.getElementById('edu-line-markers'));
		yearChart.showLoading();
		$.get('${ctx}/uc/student/year.json', function (data) {
			yearChart.hideLoading();
			var xAxData = [];
			 var labData = [];  
			 
		 for (var i=0;i<data.length;i++){
		 	var item = data[i];
		 	xAxData.push(item.TOTAL || ""); 
		 	labData.push(item.LABEL || ""); 
		 }
			yearChart.hideLoading();
			console.log(xAxData);
			console.log(labData);
			yearChart.setOption(option = {
		    	    xAxis: {
		    	        type: 'category',
		    	        data: labData
		    	    },
		    	    yAxis: {
		    	        type: 'value'
		    	    },
		    	    series: [{
		    	        data: xAxData,
		    	        type: 'line'
		    	    }],
		            
		    	});
		});
		
		var eduChart = echarts.init(document.getElementById('edu-markers'));
		eduChart.showLoading();
		$.get('${ctx}/uc/student/edu.json', function (data) {
			eduChart.hideLoading();
			  var xAxData = [];
			    var serData = [];  
			    for (var i=0;i<data.length;i++){
			     	var item = data[i];
			    xAxData.push(item.NAME || ""); 
			    serData.push({  
			        name: item.NAME,  
			        value: item.TOTAL || 0  
			    }); 
			    }
		
			option = {
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				  
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data:xAxData
				    },
				    color:['red', 'blueviolet'],
				    series: [
				        {
				            name:'专业统计',
				            type:'pie',
				           
				            avoidLabelOverlap: false,
				            label: {
				                normal: {
				                    show: false,
				                    position: 'center'
				                },
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '30',
				                        fontWeight: 'bold'
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: false
				                }
				            },
				            data:serData
				        }
				    ]
				};
			eduChart.setOption(option);
		})
		
      var myChart = echarts.init(document.getElementById('main'));
myChart.showLoading();
$.get('${ctx}/sys/information/greathiit/greathiit.json', function (data) {
   		 myChart.hideLoading();
    myChart.setOption(option = {
    		 
      	    tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'
        },
        series: [
            {
                type: 'tree',

                data: [data],

                top: '1%',
                left: '7%',
                bottom: '1%',
                right: '20%',

                symbolSize: 7,

                label: {
                    normal: {
                        position: 'left',
                        verticalAlign: 'middle',
                        align: 'right',
                        fontSize: 9
                    }
                },

                leaves: {
                    label: {
                        normal: {
                            position: 'right',
                            verticalAlign: 'middle',
                            align: 'left'
                        }
                    }
                },

                expandAndCollapse: true,
                animationDuration: 550,
                animationDurationUpdate: 750
            }
        ]
    });
});

//基于准备好的dom，初始化echarts实例
var wordChart = echarts.init(document.getElementById('world-map-markers'));
var wordTotal = 0;
var wordBoy = 0;
var wordGril = 0;
	wordChart.showLoading();
$.get('${ctx}/uc/student/region.json', function (data) {
	wordChart.hideLoading();	
	var xAxData = [];
	 var serDataBoy = [];  
	 var serDataGril = [];  
	 var labData = [];  
	 
 for (var i=0;i<data.length;i++){
 	var item = data[i];
 	xAxData.push(item.NAME || ""); 
 	labData.push(item.LABEL || ""); 
 	wordTotal = wordTotal + item.TOTAL;
 	if(item.LABEL=="1"){
 		wordBoy = wordBoy + item.TOTAL;
 		serDataBoy.push({  
	        name: item.NAME,  
	        value: item.TOTAL || 0  
	    }); 
 	}else{
 		wordGril = wordGril + item.TOTAL;
 		 serDataGril.push({  
		        name: item.NAME,  
		        value: item.TOTAL || 0  
		    }); 
 	}
 }
 $("#wordTotal").html(wordTotal);
	$("#wordBoy").html(wordBoy);
	$("#wordGril").html(wordGril);
 option = {
		    tooltip : {
		        trigger: 'item'
		    },
		   
		    legend: {
		        orient: 'vertical',
		        x:'left',
		        data:['男','女']
		    },
		    dataRange: {
		        min: 0,
		        max: 8000,
		        color:[
		            
		            '#82b6e9','#ff6347','#a092f1','#0a915d','#eaf889',
		            '#a4d8c2','#f3d999','#d3758f','#dcc392','#2e4783',
		            '#6699FF','#ff6666','#3cb371','#d5b158','#38b6b6','#E01F54','#001852','#f5e8c8','#b8d2c7','#c6b38e'
		        ],
		        x: 'left',
		        y: 'bottom',
		        text:['高','低'],           // 文本，默认为数值文本
		        calculable : true
		    },
		    
		    roamController: {
		        show: true,
		        x: 'right',
		        mapTypeControl: {
		            'china': true
		        }
		    },
		    series : [
		        {
		            name: '男',
		            type: 'map',
		            mapType: 'china',
		            roam: false,
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            
		            data:serDataBoy
		        },
		        {
		            name: '女',
		            type: 'map',
		            mapType: 'china',
		            itemStyle:{
		                normal:{ areaColor: '#dec313',label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:serDataGril
		        }
		    ]
		};
		wordChart.setOption(option);
		});
		var sexChart = echarts.init(document.getElementById('sex-markers'));
		$.get('${ctx}/uc/student/sex.json', function (data) {
			
			  var xAxData = [];
			    var serData = [];  
			    for (var i=0;i<data.length;i++){
			     	var item = data[i];
			    xAxData.push(item.NAME || ""); 
			    serData.push({  
			        name: item.NAME,  
			        value: item.TOTAL || 0  
			    }); 
			    }
		
			option = {
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b}: {c} ({d}%)"
				    },
				    toolbox: {
				        show: true,
				        orient : 'vertical',
				        x: 'right',
				        y: 'center',
				        feature : {
				            mark : {show: true},
				            magicType : {
				                show: true,
				                type: ['pie', 'funnel']
				            },

				        },
				        
				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data:xAxData
				    },
				    series: [
				        {
				            name:'性别统计',
				            type:'pie',
				            radius: ['50%', '70%'],
				            avoidLabelOverlap: false,
				            label: {
				                normal: {
				                    show: false,
				                    position: 'center'
				                },
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '30',
				                        fontWeight: 'bold'
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: false
				                }
				            },
				            data:serData
				        }
				    ]
				};
			sexChart.setOption(option);
		})
		
	});
</script>
</body>
</html>
