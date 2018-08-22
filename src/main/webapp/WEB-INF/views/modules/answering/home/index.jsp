<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="answering_default_${site.theme}" />
<meta name="description"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.description} 答辩抽签计时系统  Answering ballot time system" />
<meta name="keywords"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.keywords} 答辩抽签计时系统  Answering ballot time system" />
</head>
<body>





	<div class="album py-5 bg-light">
		<div class="container">

			<div class="row">
				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
						<div
							style="height: 225px; width: 348px; background-color: #55595C; color: white; text-align: center; padding-top: 50px;">
							<h3 class="title">标题</h3>
							<p class="current">[答辩]某某某</p>
							<p class="next">[候场]谁谁谁</p>
						</div>

						<div class="school card-body">
							<p class="card-text">
								<span class="glyphicon glyphicon-sort-by-attributes"></span>123123This
								is a wider card with supporting text below as a natural lead-in
								to additional content. This content is a little bit longer.
							</p>
							
								<a href="http://www.bootcss.com/"
									class="button button-block button-rounded button-primary button-large">进行中</a>

								<a href="http://www.bootcss.com/"
									class="button button-block button-rounded button-action button-large" style="margin-top:5px;">请准备</a>
							

							<div class="progress progress-striped active" style="margin-top: 5px;">

								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="60" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%;">

									<span class="sr-only">40% 完成</span>

								</div>

							</div>
							
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>



	<!-- ${empty  fns:getUser().id} -->

	<button type="button" class="btn btn-default" aria-label="Left Align">
		<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
	</button>
</body>
</html>