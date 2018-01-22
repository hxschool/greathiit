<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>宿舍管理管理</title>
	<meta name="decorator" content="default"/>
	
</head>


<body>
	<br/>
	<form id="form" action="${ctx}/dorm/ucDorm/flightDormByClazz" method="post" class="form-horizontal">
		<input type="hidden" name="studentDormType" value="unUcDormClazzForm">
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
				<label class="control-label">年级：</label>
				<div class="controls">
					<select id="clazz" class="clazz input-medium" ><option>请选择</option></select>
					&nbsp;&nbsp;&nbsp;
						<span id="areaMessage"></span>
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

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="腾出"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#element_id').cxSelect({ 
			  url: '${ctx}/sys/office/treeClassLink',
			  selects: ['province', 'city','clazz', 'area'], 
			  jsonName: 'name',
			  jsonValue: 'value',
			  jsonSub: 'sub'
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