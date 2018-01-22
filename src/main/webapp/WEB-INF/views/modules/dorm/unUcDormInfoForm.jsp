<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>宿舍管理管理</title>
	<meta name="decorator" content="default"/>
	
</head>


<body>
	<br/>
	<form id="form" action="${ctx}/dorm/ucDorm/flightDormByStudentNumbers" method="post" class="form-horizontal">
		<input type="hidden" name="studentDormType" value="unUcDormInfoForm">
		<sys:message content="${message}"/>


		<div id="dormBuild_id">
			<div class="control-group">
				<label class="control-label">公寓：</label>
				<div class="controls">
					<select class="dormBuild input-medium"><option>请选择</option></select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">寝室：</label>
				<div class="controls">
					<select id="dorm" class="dorm input-medium" name="dorm"
						required="required"><option>请选择</option></select>
					&nbsp;&nbsp;&nbsp; <span id="dormMessage"></span>
				</div>
			</div>

		</div>
		<div class="control-group">
				<label class="control-label">当前入住学员信息：</label>
				<div class="controls" id="student">
				</div>
			</div>


		<div class="form-actions">
		<input type="hidden" id="ajax_total"> 
		<input type="hidden" id="ajax_studentNumber" value="1"> 
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			<span id="studentMessage" style="color:red"></span>
		</div>
	</form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#dormBuild_id').cxSelect({ 
				  url: '${ctx}/dorm/ucDormBuild/treeLink',
				  selects: ['dormBuild', 'dorm'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
			
			$('#dorm').change(function(){
				
				var dormId = $(this).children('option:selected').val();
				 console.log(dormId);
				 $.ajax({
			          url: "${ctx}/dorm/ucDorm/ajaxStudentByDorm",
			          async: false,
			          data: {
			        	  dormId: dormId
			          },
			          success: function( data ) {
			        	  $("#student").empty();
			        	  $("#dormMessage").empty();
			        	  if(data.length==0){
			        		  $("#dormMessage").css("color","red");
		                	  $("#dormMessage").html("当前寝室没有入住任何学员");
			        	  }
			        	  
			        	  for(var i=0 ;i<data.length;i++){
			                 $("#student").append("<input type='checkbox' value='"+data[i].no+"' class='student' checked name='studentNumber'/>"+data[i].name);
			           	  }
			          }
			        });
			});
			
			
			var validate = $("#form").validate({
				
                submitHandler: function(form){
                	 $("#studentMessage").empty();
                	var ret = false;
                	$("[name='studentNumber'][checked]").each(function(){
                		if($(this).is(':checked')){
                			ret = true
                		}
                	})
                	
                	if(!ret){
                		 $("#studentMessage").html("请至少选择一位学员信息");
                	}
                	
                	if(ret){
                		form.submit();
                	}
                	
                    return false;
                }          
            });    
		});
	</script>
</body>
</html>