<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>宿舍管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dorm/ucDorm/">按学号分配</a></li>
	</ul><br/>
	<form id="form" action="${ctx}/dorm/ucDorm/saveDorm" method="post" class="form-horizontal">
		<input type="hidden" name="studentDormType" value="ucDormStudentNumberForm">
		<sys:message content="${message}"/>
		


			<div id="element_id">


				<div class="control-group">
					<label class="control-label">公寓：</label>
					<div class="controls">
						<select class="dormBuild input-medium"><option>请选择</option></select>
					</div>
				</div>
	
				<div class="control-group">
					<label class="control-label">寝室：</label>
					<div class="controls">
						<select id="dorm" class="dorm input-medium" name="dorm"><option>请选择</option></select>
						&nbsp;&nbsp;&nbsp;
						<span id="dormMessage"></span>
					</div>
				</div>
				
			</div>

			
			<div class="control-group">
				<label class="control-label">学号：</label>
				<div class="controls">
					<input id="studentNumber"
					name="studentNumber" type="text" maxlength="50" class="form-control"
					 placeholder="请输入学号" required="required"/> &nbsp;&nbsp;&nbsp;<span id="studentNumberMessage" style="color:red"></span>
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

		
		$(function() {
			
			$('#element_id').cxSelect({ 
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
			
			
			$("#studentNumber").blur(function() {
				var studentNumber = $("#studentNumber").val();
				if(studentNumber!=''){
					$.post("${ctx}/dorm/ucDorm/ajaxStudentnumber", {
						studentNumber : studentNumber
					}, function(data) {
							$("#ajax_studentNumber").val("1");
							$("#studentNumberMessage").html("");
							if(data.responseCode!='0000'){
								$("#studentNumberMessage").html(data.responseMessage);
								$("#ajax_studentNumber").val("0");
							}
							
							if(data.responseCode=="9998"){
								var btn ='<input id="btnStudentNumber" class="btn btn-danger " type="button" value="{studentNumber}"/></span>';
								
								var val = "腾出学员:["+$("#studentNumber").val()+"]";
								btn = btn.replace("{studentNumber}",val);
								
								$("#studentNumberMessage").html($("#studentNumberMessage").html() + btn);
							}
						
					});
				}
				
			});
			

			var validate = $("#form").validate({
                submitHandler: function(form){
                	if($("#ajax_studentNumber").val()!='0'&&$("#ajax_total").val()>0){
                		form.submit();
                	}
                	
                    return false;
                }          
            });    
		});
	</script>
	
</body>
</html>