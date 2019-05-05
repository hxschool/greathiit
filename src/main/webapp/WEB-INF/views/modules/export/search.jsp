<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>导出数据</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#element_id').cxSelect({ 
			  url: '${ctx}/sys/office/treeClassLink',
			  selects: ['school', 'major', 'grade'], 
			  jsonName: 'name',
			  jsonValue: 'value',
			  jsonSub: 'sub'
			}); 
		
		$('#grade').change(function(){
			
			var parnetId = $("#major").children('option:selected').val();
			var grade = $("#grade").children('option:selected').val();
			 $.ajax({
				  url: '${ctx}/sys/office/ajaxClass',
		          async: false,
		          data: {
		        	  parnetId: parnetId,
		        	  grade: grade
		          },
		          success: function( data ) {
		        	  $("#classno").empty();
		        	  if(data.length==0){
		        	  }
		        	  
		        	  for(var i=0 ;i<data.length;i++){
		        		  $("#classno").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		           	  }
		          }
		        });
		});
		$('#classno').change(function(){
			var classno = $("#classno").children('option:selected').val();
			 $.ajax({
				  url: '${ctx}/export/ajaxReport',
		          async: false,
		          data: {
		        	  classno: classno
		          },
		          success: function( data ) {
		        	  var M = data.M;
		        	  var F = data.F;
		        	  var O = data.O;
		        	  var T = data.T;
		        	  $("#total").html("男:"+M + "女:"+F +"其他:"+O+"总人数:"+T);
		        	 
		          }
		        });
		});
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出学生数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$("#searchForm").attr("action", "${ctx}/export/rollcall");
					$("#searchForm").submit();
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		});
		
	});
	
</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li class="active"><a href="#">数据统计</a></li>
		
	</ul>
	<form id="searchForm"
		method="post" class="breadcrumb form-search ">
		
		<ul class="ul-form" id="element_id">

			<li><label>学院：</label><select name="company.name" id="school"
				class="school" style="width: 200px;">
					<option value="" selected="selected">==请选择学院==</option>
			</select></li>
			<li><label>专业：</label> <select name="office.name" id="major"
				class="major" style="width: 200px;">
					<option value="" selected="selected">==请选择专业==</option>
			</select></li>
			<li><label>年级：</label> <select name="grade" id="grade"
				class="grade" style="width: 200px;">
					<option value="" selected="selected">==请选择年级==</option>
			</select></li>
			<li><label>班级：</label> <select name="classno" id="classno"
				class="clazz.id" style="width: 200px;">

			</select></li>
			<li class="btns"> <input
				id="btnExport" class="btn btn-primary" type="button" value="导出" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form>
	
	<div id="total">统计信息</div>
	
	<fieldset>
			<legend>使用说明</legend>
			${config.tip }
		</fieldset>
	<sys:message content="${message}" />
</body>
</html>