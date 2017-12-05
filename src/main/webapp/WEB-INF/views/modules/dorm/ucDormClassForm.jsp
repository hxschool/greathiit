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
	<form id="form" action="${ctx}/dorm/ucDorm/saveDorm" method="post" class="form-horizontal">
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
						<span id="areaMessage">男生共计31人,女生共计10人</span>
				</div>
			</div>
			
	

		</div>

		<div class="form-actions">
		<input type="hidden" id="ajax_total"> 
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
			$('#area').change(function(){
				var clazzId = $(this).children('option:selected').val();
				 console.log(officeId);
				 $.ajax({
			          url: "${ctx}/dorm/ucDorm/getAjaxStudent",
			          async: false,
			          data: {
			        	  clazzId: clazzId,
			          },
			          success: function( data ) {
			        	  
			        	  $("#studentNumber option").each(function(){
			        		  if($(this).val()!=''){
			        			  $(this).remove();
			        		  }
			        	  })
			              var optionString = "";
			        	  var beanList = data;            //返回的json数据
			                if(beanList){                   //判断
			                    for(var i=0; i<beanList.length; i++){ //遍历，动态赋值
			                        optionString +="<option  value=\""+beanList[i].no+"\"";  
			                        optionString += ">"+beanList[i].name+"</option>";  //动态添加数据  
			                    }   
			                	$("#studentNumber").append(optionString);
			                }  
			          }
			        });
			});
			

			var validate = $("#form").validate({
				
                submitHandler: function(form){
                	
                	if($("#studentNumber").val()==''||$("#studentNumber").val()==null){
                		$("#studentNumberMessage").css("color","red");
                		$("#studentNumberMessage").html("请选择学员信息");
                		return false;
    				}
                	
                	$("#studentNumberMessage").html("");
                	
                	
                	if($("#ajax_studentNumber").val()!='0'&&$("#studentNumber").val()!=''&&$("#ajax_total").val()>0){
                		form.submit();
                	}
                	
                    return false;
                }          
            });    
		});
	</script>
</body>
</html>