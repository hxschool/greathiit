<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>宿舍管理管理</title>
	<meta name="decorator" content="default"/>
	
</head>


<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dorm/ucDorm/">按学号分配</a></li>
	</ul><br/>
	<form id="form" action="${ctx}/dorm/ucDorm/saveClazzDorm" method="post" class="form-horizontal">
		<input type="hidden" name="studentDormType" value="ucDormClassForm">
		<sys:message content="${message}"/>
		<div id="element_id">


			<div class="control-group">
				<label class="control-label">学院：</label>
				<div class="controls">
					<select class="province input-medium"><option>请选择</option></select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">专业：</label>
				<div class="controls">
					<select id="city" class="city input-medium"><option>请选择</option></select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">班级：</label>
				<div class="controls">
					<select id="area" class="area input-medium" name="clazzId"><option>请选择</option></select>
					&nbsp;&nbsp;&nbsp;
						<span id="areaMessage"></span>
				</div>
			</div>
			
		</div>

		<div id="boy_id">
			<div class="control-group">
				<label class="control-label">选择要分配的男寝：</label>
				<div class="controls">
					<select id="boy" class="boy ajx input-medium" name="boy"><option>请选择</option></select>
					&nbsp;&nbsp;&nbsp;
						<span id="boyMessage"></span>
				</div>
			</div>
		</div>
		
		<div id="gril_id">
			<div class="control-group">
				<label class="control-label">选择要分配的女寝：</label>
				<div class="controls">
					<select id="gril" class="gril ajx input-medium" name="gril"><option>请选择</option></select>
					&nbsp;&nbsp;&nbsp;
						<span id="grilMessage"></span>
				</div>
			</div>
		</div>

		<div class="form-actions">
		<input type="hidden" id="ajax_boy_total"> 
		<input type="hidden" id="ajax_gril_total">
		<input type="hidden" id="ajax_total" value="1"> 
		<input type="hidden" id="ajax_studentNumber" value="1"> 
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#element_id').cxSelect({ 
			  url: '${ctx}/sys/office/treeLink',
			  selects: ['province', 'city', 'area'], 
			  jsonName: 'name',
			  jsonValue: 'value',
			  jsonSub: 'sub'
			}); 
			
			$('#boy_id').cxSelect({ 
				  url: '${ctx}/dorm/ucDormBuild/info?dormBuildType=1',
				  selects: ['boy'], 
				  jsonName: 'dormBuildName',
				  jsonValue: 'id'
			}); 
			$('#gril_id').cxSelect({ 
				  url: '${ctx}/dorm/ucDormBuild/info?dormBuildType=2',
				  selects: ['gril'], 
				  jsonName: 'dormBuildName',
				  jsonValue: 'id'
			}); 
			
			$('.ajx').change(function(){
				var operType = $(this).attr("id");
				var val = $(this).children('option:selected').val();
				$("#ajax_total").val("1");
				
				$.ajax({
			          url: "${ctx}/dorm/ucDormBuild/info",
			          async: false,
			          success: function( data ) {
			        	  $("#"+operType+"Message").html("");
			        	  for (var i=0;i<data.length;i++)
			        	  {
			        	 		var dormBuild = data[i];
			        	 		if(dormBuild.id==val){
			        	 			var dormBuildTotal = dormBuild.dormBuildTotal;
			        	 			var dormBuildCnt = dormBuild.dormBuildCnt;
			        	 			
			        	 			if((dormBuildTotal-dormBuildCnt)<$("#ajax_"+operType+"_total").val()){
			        	 				
			        	 				$("#"+operType+"Message").css("color","red");
						        		$("#"+operType+"Message").html("当前公寓信息人数小于可以分配的人数,请确认后重新操作.当前公寓可入住人数"+(dormBuildTotal-dormBuildCnt) +",实际需要入住人数"+$("#ajax_"+operType+"_total").val());
			        	 				$("#ajax_total").val("0");
			        	 			}
			        	 		}
			        	  }
			          }
			        });
				
			})
			
			$('#area').change(function(){
				 $("#ajax_studentNumber").val("0");
				 var clazzId = $(this).children('option:selected').val();
				 $.ajax({
			          url: "${ctx}/dorm/ucDorm/ajaxClazzNumber",
			         
			          data: {
			        	  clazzId: clazzId,
			          },
			          success: function( data ) {
			        	  console.log(data.result);
			        	  if(data.responseCode=='9999'){
			        		  $("#areaMessage").css("color","red");
			        		  $("#areaMessage").html(data.responseMessage);
			        	  }else{
			        		 var boyList=data.result.boyList;
			        		 var grilList=data.result.grilList;
			        		 $("#ajax_boy_total").val(boyList.length);
			        		 $("#ajax_gril_total").val(grilList.length);
			        		  $("#areaMessage").css("color","green");
			        		  $("#areaMessage").html("当前班级共计"+(boyList.length+grilList.length)+"人,男生共计"+boyList.length+"人,女生共计"+grilList.length+"人");
			        		  $("#ajax_studentNumber").val("1");
			        	  }
			          }
			        });
			});
			

			var validate = $("#form").validate({
				
                submitHandler: function(form){

                	
                	
                	if($("#ajax_studentNumber").val()!='0'&&$("#ajax_total").val()!=''&&$("#ajax_total").val()!='0'){
                		form.submit();
                	}
                	
                    return false;
                }          
            });    
		});
	</script>
</body>
</html>