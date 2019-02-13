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

				<div class="col-md-12">
					<a href="#"
								class="goods button button-block button-rounded  {button_style}"
								style="margin-top: 5px;">[{id}]{username}</a>
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
		    url:'list?id=${id}',
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
		        	var button_style = "";
		        	if(index==0){
		        		button_style = "button-action";
		        	} 
		        	msg  = msg + $("#answeringTemplate").html().replace("{button_style}",button_style).replace("{id}",index+1).replace("{username}",item.username);
		        	
		        	
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