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
				哈尔滨信息工程学院 <small>Version 2.0</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"></a></li>

			</ol>
		</section>
		<section class="content">
			<!-- Info boxes -->
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-aqua"><i
							class="ion ion-ios-gear-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">CPU Traffic</span> <span
								class="info-box-number">90<small>%</small></span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-red"><i
							class="fa fa-google-plus"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">Likes</span> <span
								class="info-box-number">41,410</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->

				<!-- fix for small devices only -->
				<div class="clearfix visible-sm-block"></div>

				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-green"><i
							class="ion ion-ios-cart-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">Sales</span> <span
								class="info-box-number">760</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-yellow"><i
							class="ion ion-ios-people-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">New Members</span> <span
								class="info-box-number">2,000</span>
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
								<div class="btn-group">
									<button type="button" class="btn btn-box-tool dropdown-toggle"
										data-toggle="dropdown">
										<i class="fa fa-wrench"></i>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li class="divider"></li>
										<li><a href="#">Separated link</a></li>
									</ul>
								</div>
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
											class="fa fa-caret-up"></i> 17%</span>
										<h5 class="description-header">$35,210.43</h5>
										<span class="description-text">TOTAL REVENUE</span>
									</div>
									<!-- /.description-block -->
								</div>
								<!-- /.col -->
								<div class="col-sm-3 col-xs-6">
									<div class="description-block border-right">
										<span class="description-percentage text-yellow"><i
											class="fa fa-caret-left"></i> 0%</span>
										<h5 class="description-header">$10,390.90</h5>
										<span class="description-text">TOTAL COST</span>
									</div>
									<!-- /.description-block -->
								</div>
								<!-- /.col -->
								<div class="col-sm-3 col-xs-6">
									<div class="description-block border-right">
										<span class="description-percentage text-green"><i
											class="fa fa-caret-up"></i> 20%</span>
										<h5 class="description-header">$24,813.53</h5>
										<span class="description-text">TOTAL PROFIT</span>
									</div>
									<!-- /.description-block -->
								</div>
								<!-- /.col -->
								<div class="col-sm-3 col-xs-6">
									<div class="description-block">
										<span class="description-percentage text-red"><i
											class="fa fa-caret-down"></i> 18%</span>
										<h5 class="description-header">1200</h5>
										<span class="description-text">GOAL COMPLETIONS</span>
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
									<div id="sex-markers" style="width:250px;height: 250px;">zhaojunfei</div>
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
					<div class="row">
						<div class="col-md-12">
							<!-- 应用系统 -->
							<div class="box box-info">
								<div class="box-header with-border">
									<h3 class="box-title">Latest Orders</h3>

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
														<td><a href="${appconfig.url }">${appconfig.id }</a></td>
														<td>${appconfig.name }</td>
														<td><span class="label ${j }">${v }</span></td>

													</tr>

												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>

							</div>


						</div>
					</div>
					<!-- email -->


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
									<input class="form-control" name="emailto"
										placeholder="Email to:" type="email">
								</div>
								<div class="form-group">
									<input class="form-control" name="subject"
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
									<textarea class="textarea"
										style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid rgb(221, 221, 221); padding: 10px; display: none;"
										placeholder="Message"></textarea>
									<input name="_wysihtml5_mode" value="1" type="hidden">
									<iframe class="wysihtml5-sandbox" security="restricted"
										allowtransparency="true" marginwidth="0" marginheight="0"
										style="display: inline; background-color: rgb(255, 255, 255); border-collapse: separate; border-color: rgb(221, 221, 221); border-style: solid; border-width: 1px; clear: none; float: none; margin: 0px; outline: rgb(51, 51, 51) none 0px; outline-offset: 0px; padding: 10px; position: static; top: auto; left: auto; right: auto; bottom: auto; z-index: auto; vertical-align: text-bottom; text-align: start; box-sizing: border-box; box-shadow: none; border-radius: 0px; width: 100%; height: 125px;"
										width="0" height="0" frameborder="0"></iframe>
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

				<!-- /.col -->

				<div class="col-md-4">
					<!-- Info Boxes Style 2 -->
					<div class="info-box bg-yellow">
						<span class="info-box-icon"><i
							class="ion ion-ios-pricetag-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">Inventory</span> <span
								class="info-box-number">5,200</span>

							<div class="progress">
								<div class="progress-bar" style="width: 50%"></div>
							</div>
							<span class="progress-description"> 50% Increase in 30
								Days </span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box bg-green">
						<span class="info-box-icon"><i
							class="ion ion-ios-heart-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">Mentions</span> <span
								class="info-box-number">92,050</span>

							<div class="progress">
								<div class="progress-bar" style="width: 20%"></div>
							</div>
							<span class="progress-description"> 20% Increase in 30
								Days </span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box bg-red">
						<span class="info-box-icon"><i
							class="ion ion-ios-cloud-download-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">Downloads</span> <span
								class="info-box-number">114,381</span>

							<div class="progress">
								<div class="progress-bar" style="width: 70%"></div>
							</div>
							<span class="progress-description"> 70% Increase in 30
								Days </span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box bg-aqua">
						<span class="info-box-icon"><i
							class="ion-ios-chatbubble-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">Direct Messages</span> <span
								class="info-box-number">163,921</span>

							<div class="progress">
								<div class="progress-bar" style="width: 40%"></div>
							</div>
							<span class="progress-description"> 40% Increase in 30
								Days </span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->

					<div class="box box-default">
						<div class="box-header with-border">
							<h3 class="box-title">Browser Usage</h3>

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
								<div class="col-md-8">
									<div class="chart-responsive">
										<canvas id="pieChart" height="150"></canvas>
									</div>
									<!-- ./chart-responsive -->
								</div>
								<!-- /.col -->
								<div class="col-md-4">
									<ul class="chart-legend clearfix">
										<li><i class="fa fa-circle-o text-red"></i> Chrome</li>
										<li><i class="fa fa-circle-o text-green"></i> IE</li>
										<li><i class="fa fa-circle-o text-yellow"></i> FireFox</li>
										<li><i class="fa fa-circle-o text-aqua"></i> Safari</li>
										<li><i class="fa fa-circle-o text-light-blue"></i> Opera</li>
										<li><i class="fa fa-circle-o text-gray"></i> Navigator</li>
									</ul>
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.box-body -->
						<div class="box-footer no-padding">
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">United States of America <span
										class="pull-right text-red"><i class="fa fa-angle-down"></i>
											12%</span></a></li>
								<li><a href="#">India <span
										class="pull-right text-green"><i class="fa fa-angle-up"></i>
											4%</span></a></li>
								<li><a href="#">China <span
										class="pull-right text-yellow"><i
											class="fa fa-angle-left"></i> 0%</span></a></li>
							</ul>
						</div>
						<!-- /.footer -->
					</div>
					<!-- /.box -->

					<div class="box box-solid bg-teal-gradient">
						<div class="box-header ui-sortable-handle" style="cursor: move;">
							<i class="fa fa-th"></i>

							<h3 class="box-title">Sales Graph</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn bg-teal btn-sm"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" class="btn bg-teal btn-sm"
									data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<div class="box-body border-radius-none">
							<div class="chart" id="line-chart" style="height: 250px;">
								<svg height="250" version="1.1" width="647.083"
									xmlns="http://www.w3.org/2000/svg"
									xmlns:xlink="http://www.w3.org/1999/xlink"
									style="overflow: hidden; position: relative; left: -0.900024px; top: -0.383362px;">
									<desc>Created with Raphaël 2.2.0</desc>
									<defs></defs>
									<text
										style="text-anchor: end; font-family: Open Sans; font-size: 10px; font-weight: normal;"
										x="44.233333587646484" y="211" text-anchor="end"
										font-family="Open Sans" font-size="10px" stroke="none"
										fill="#ffffff" font-weight="normal">
									<tspan dy="3.5">0</tspan></text>
									<path style="" fill="none" stroke="#efefef"
										d="M56.733333587646484,211H622.083" stroke-width="0.4"></path>
									<text
										style="text-anchor: end; font-family: Open Sans; font-size: 10px; font-weight: normal;"
										x="44.233333587646484" y="164.5" text-anchor="end"
										font-family="Open Sans" font-size="10px" stroke="none"
										fill="#ffffff" font-weight="normal">
									<tspan dy="3.5">5,000</tspan></text>
									<path style="" fill="none" stroke="#efefef"
										d="M56.733333587646484,164.5H622.083" stroke-width="0.4"></path>
									<text
										style="text-anchor: end; font-family: Open Sans; font-size: 10px; font-weight: normal;"
										x="44.233333587646484" y="118.00000000000001"
										text-anchor="end" font-family="Open Sans" font-size="10px"
										stroke="none" fill="#ffffff" font-weight="normal">
									<tspan dy="3.500000000000014">10,000</tspan></text>
									<path style="" fill="none" stroke="#efefef"
										d="M56.733333587646484,118.00000000000001H622.083"
										stroke-width="0.4"></path>
									<text
										style="text-anchor: end; font-family: Open Sans; font-size: 10px; font-weight: normal;"
										x="44.233333587646484" y="71.5" text-anchor="end"
										font-family="Open Sans" font-size="10px" stroke="none"
										fill="#ffffff" font-weight="normal">
									<tspan dy="3.5">15,000</tspan></text>
									<path style="" fill="none" stroke="#efefef"
										d="M56.733333587646484,71.5H622.083" stroke-width="0.4"></path>
									<text
										style="text-anchor: end; font-family: Open Sans; font-size: 10px; font-weight: normal;"
										x="44.233333587646484" y="25.00000000000003" text-anchor="end"
										font-family="Open Sans" font-size="10px" stroke="none"
										fill="#ffffff" font-weight="normal">
									<tspan dy="3.5000000000000284">20,000</tspan></text>
									<path style="" fill="none" stroke="#efefef"
										d="M56.733333587646484,25.00000000000003H622.083"
										stroke-width="0.4"></path>
									<text
										style="text-anchor: middle; font-family: Open Sans; font-size: 10px; font-weight: normal;"
										x="518.3554184346715" y="223.5" text-anchor="middle"
										font-family="Open Sans" font-size="10px" stroke="none"
										fill="#ffffff" font-weight="normal"
										transform="matrix(1,0,0,1,0,7)">
									<tspan dy="3.5">2013</tspan></text>
									<text
										style="text-anchor: middle; font-family: Open Sans; font-size: 10px; font-weight: normal;"
										x="266.93624722334533" y="223.5" text-anchor="middle"
										font-family="Open Sans" font-size="10px" stroke="none"
										fill="#ffffff" font-weight="normal"
										transform="matrix(1,0,0,1,0,7)">
									<tspan dy="3.5">2012</tspan></text>
									<path style="" fill="none" stroke="#efefef"
										d="M56.733333587646484,186.2062C72.53289899163693,185.9458,104.13202979961778,187.775575,119.93159520360823,185.1646C135.73116060759867,182.553625,167.33029141557952,166.4743950819672,183.12985681956997,165.3184C198.7576878169953,164.17497008196722,230.01334981184596,178.173325,245.64118080927128,175.9669C261.2690118066966,173.760475,292.52467380154724,149.8671487704918,308.1525047989726,147.667C323.952070202963,145.4426737704918,355.5512010109439,155.955625,371.3507664149343,158.269C387.15033181892477,160.582375,418.7494626269056,177.16471967213116,434.54902803089607,166.174C450.17685902832136,155.30274467213115,481.43252102317206,77.67916919889502,497.06035202059735,70.8211C512.5164486114576,64.03839419889503,543.428641793178,103.97557445054946,558.8847383840382,111.61090000000002C574.6843037880286,119.41589945054946,606.2834345960096,127.33952500000001,622.083,132.5824"
										stroke-width="2"></path>
									<circle cx="56.733333587646484" cy="186.2062" r="4"
										fill="#efefef" stroke="#efefef" style="" stroke-width="1"></circle>
									<circle cx="119.93159520360823" cy="185.1646" r="4"
										fill="#efefef" stroke="#efefef" style="" stroke-width="1"></circle>
									<circle cx="183.12985681956997" cy="165.3184" r="4"
										fill="#efefef" stroke="#efefef" style="" stroke-width="1"></circle>
									<circle cx="245.64118080927128" cy="175.9669" r="4"
										fill="#efefef" stroke="#efefef" style="" stroke-width="1"></circle>
									<circle cx="308.1525047989726" cy="147.667" r="4"
										fill="#efefef" stroke="#efefef" style="" stroke-width="1"></circle>
									<circle cx="371.3507664149343" cy="158.269" r="4"
										fill="#efefef" stroke="#efefef" style="" stroke-width="1"></circle>
									<circle cx="434.54902803089607" cy="166.174" r="4"
										fill="#efefef" stroke="#efefef" style="" stroke-width="1"></circle>
									<circle cx="497.06035202059735" cy="70.8211" r="4"
										fill="#efefef" stroke="#efefef" style="" stroke-width="1"></circle>
									<circle cx="558.8847383840382" cy="111.61090000000002" r="4"
										fill="#efefef" stroke="#efefef" style="" stroke-width="1"></circle>
									<circle cx="622.083" cy="132.5824" r="4" fill="#efefef"
										stroke="#efefef" style="" stroke-width="1"></circle></svg>
								<div class="morris-hover morris-default-style"
									style="left: 263.103px; top: 80px; display: none;">
									<div class="morris-hover-row-label">2012 Q1</div>
									<div class="morris-hover-point" style="color: #efefef">
										Item 1: 6,810</div>
								</div>
							</div>
						</div>
						<!-- /.box-body -->
						<div class="box-footer no-border">
							<div class="row">
								<div class="col-xs-4 text-center"
									style="border-right: 1px solid #f4f4f4">
									<div style="display: inline; width: 60px; height: 60px;">
										<canvas width="60" height="60"></canvas>
										<input class="knob" data-readonly="true" value="20"
											data-width="60" data-height="60" data-fgcolor="#39CCCC"
											readonly="readonly"
											style="width: 34px; height: 20px; position: absolute; vertical-align: middle; margin-top: 20px; margin-left: -47px; border: 0px none; background: rgba(0, 0, 0, 0) none repeat scroll 0% 0%; font: bold 12px Arial; text-align: center; color: rgb(57, 204, 204); padding: 0px;"
											type="text">
									</div>

									<div class="knob-label">Mail-Orders</div>
								</div>
								<!-- ./col -->
								<div class="col-xs-4 text-center"
									style="border-right: 1px solid #f4f4f4">
									<div style="display: inline; width: 60px; height: 60px;">
										<canvas width="60" height="60"></canvas>
										<input class="knob" data-readonly="true" value="50"
											data-width="60" data-height="60" data-fgcolor="#39CCCC"
											readonly="readonly"
											style="width: 34px; height: 20px; position: absolute; vertical-align: middle; margin-top: 20px; margin-left: -47px; border: 0px none; background: rgba(0, 0, 0, 0) none repeat scroll 0% 0%; font: bold 12px Arial; text-align: center; color: rgb(57, 204, 204); padding: 0px;"
											type="text">
									</div>

									<div class="knob-label">Online</div>
								</div>
								<!-- ./col -->
								<div class="col-xs-4 text-center">
									<div style="display: inline; width: 60px; height: 60px;">
										<canvas width="60" height="60"></canvas>
										<input class="knob" data-readonly="true" value="30"
											data-width="60" data-height="60" data-fgcolor="#39CCCC"
											readonly="readonly"
											style="width: 34px; height: 20px; position: absolute; vertical-align: middle; margin-top: 20px; margin-left: -47px; border: 0px none; background: rgba(0, 0, 0, 0) none repeat scroll 0% 0%; font: bold 12px Arial; text-align: center; color: rgb(57, 204, 204); padding: 0px;"
											type="text">
									</div>

									<div class="knob-label">In-Store</div>
								</div>
								<!-- ./col -->
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
        // 基于准备好的dom，初始化echarts实例
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
		        color:['#E01F54','#001852','#f5e8c8','#b8d2c7','#c6b38e',
		            '#a4d8c2','#f3d999','#d3758f','#dcc392','#2e4783',
		            '#82b6e9','#ff6347','#a092f1','#0a915d','#eaf889',
		            '#6699FF','#ff6666','#3cb371','#d5b158','#38b6b6'
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
