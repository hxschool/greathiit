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
	<form id="form" action="${ctx}/dorm/ucDorm/save" method="post" class="form-horizontal">
		
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
					<select id="area" class="area input-medium"><option>请选择</option></select>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">学员：</label>
				<div class="controls">
					<select id="studentNumber" name="studentNumber" class="input-medium" ><option>请选择</option></select>
					&nbsp;&nbsp;&nbsp;
						<span id="studentNumberMessage"></span>
				</div>
			</div>
			
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
						<select id="dorm" class="dorm input-medium" name="dorm" required="required"><option>请选择</option></select>
						&nbsp;&nbsp;&nbsp;
						<span id="dormMessage"></span>
					</div>
				</div>
				
			</div>

		</div>

		<div class="form-actions">
		<input type="hidden" id="ajax_total"> 
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
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
				var dorm = $("#dorm").val();
				 $.ajax({
			          url: "${ctx}/dorm/ucDorm/getAjaxDorm",
			          data: {
			        	  id: dorm
			          },
			          success: function( data ) {
			        	 
			                if(data){          
			                	var dorm = data.result;
			                	if((dorm.total-dorm.cnt)>0){
			                		$("#dormMessage").css("color","green");
			                		$("#ajax_total").val((dorm.total-dorm.cnt));
			                		$("#dormMessage").html("当前寝室已住"+dorm.cnt+"人,还可以入住"+(dorm.total-dorm.cnt)+"人");
			                	}else{
			                		$("#dormMessage").css("color","red");
			                		$("#dormMessage").html("当前寝室已住满");
			                		
			                	}
			                }  
			          }
			        });
			});
			$('#element_id').cxSelect({ 
			  url: '${ctx}/sys/office/treeLink',
			  selects: ['province', 'city', 'area'], 
			  jsonName: 'name',
			  jsonValue: 'value',
			  jsonSub: 'sub'
			}); 
			
			
			
			$('#area').change(function(){
				var officeId = $("#city").val();
				var clazzId = $(this).children('option:selected').val();
				 console.log(officeId);
				 $.ajax({
			          url: "${ctx}/dorm/ucDorm/getAjaxStudent",
			          async: false,
			          data: {
			        	  officeId: officeId,
			        	  clazzId: clazzId,
			          },
			          success: function( data ) {
			        	  $("#studentNumber").empty();
			              var optionString = "";
			        	  var beanList = data;            //返回的json数据
			                if(beanList){                   //判断
			                    for(var i=0; i<beanList.length; i++){ //遍历，动态赋值
			                        optionString +="<option  value=\""+beanList[i].id+"\"";  
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
                	
                	
                	if($("#studentNumber").val()!=''&&$("#ajax_total").val()>0){
                		form.submit();
                	}
                	
                    return false;
                }          
            });    
		});
	</script>
</body>
</html>