<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>哈尔滨信息工程学院-论文答辩系统</title>
<meta name="decorator" content="answering_default_${site.theme}" />
<meta name="description"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.description} 答辩抽签计时系统  Answering ballot time system" />
<meta name="keywords"
	content="哈尔滨信息工程学院-国家示范性软件学院 http://greathiit.com ${site.keywords} 答辩抽签计时系统  Answering ballot time system" />
</head>
<body>

	<div class="album py-5 bg-light">
		<div class="container">

			<div class="row" id="info">

				
			</div>
			

		</div>
	</div>




	<!-- ${empty  fns:getUser().id} -->

	<!-- <button type="button" class="btn btn-default" aria-label="Left Align">
		<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
	</button> -->
	<script id="answeringTemplate" type="text/html">

				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
						<div
							style="background-color: #55595C; color: white; text-align: center; padding-top: 50px;">
							<h3 class="title">{title}</h3>
							<p class="current">{school}</p>
							<!--<p class="current">[答辩] {current.name}</p>-->
							<p class="next">[教师] {teachers}</p>
						</div>

						<div class="school card-body">
							

							<a href="#"
								class="goods button button-block button-rounded button-primary button-large">{current.name}</a>

							<a href="answering/list-{asAnsweringId}"
								class="goods button button-block button-rounded button-action button-large"
								style="margin-top: 5px;">{next.name}</a>


							<div class="goods progress progress-striped active"
								style="margin-top: 5px;height:8px">

								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="60" aria-valuemin="0"
									aria-valuemax="100" style="width: {completed}%">
									<span class="sr-only">100% 完成</span>
								</div>

							</div>

						</div>
					</div>
				</div>
					
	</script>
	
	<script type="text/javascript">
	consoleLog();
	$(document).ready(function(){
		setInterval(function(){
		    consoleLog();
		},30000)

		});
	function consoleLog(){
		$.ajax({
		    url:'answering/get',
		    type:'POST',
		    async:true,
		    timeout:5000,
		    dataType:'json',
		    success:function(data){
		        $('#info').empty();
		        var msg = "";
		        $.each(data, function(index, item){
		        	var next = "";
		        	var current = "";
		        	var title = "当前已结束";
		        	if(item.current=='' || item.current =='undefined' || item.current == null){
		        		
		        	}else{
		        		
		        		title = item.title;
		        		current = item.current.name;
		        		if( item.current !='undefined'){
			        		next = item.next.name;
			        	}
		        	}
		        	msg  = msg + $("#answeringTemplate").html().replace("{asAnsweringId}",item.asAnsweringId).replace("{title}",title).replace("{teachers}",item.teachers).replace("{school}",item.school).replace(new RegExp("{current.name}","g"),current).replace(new RegExp("{next.name}","g"),next).replace("{total}",item.total).replace("{completed}",(item.completed/item.total)*100);
		        	
		        	
		        })
		         $('#info').html(msg);
				$(".goods").each(function(){
        			if($(this).html()==""){
        				$(this).hide()
        			}else{
        				$(this).show()
        			}
        		})
		    }
		})     
	}
	</script>
</body>
</html>