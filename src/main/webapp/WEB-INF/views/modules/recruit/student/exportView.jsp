<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
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
				  url: '${ctx}/recruit/student/recruitStudent/ajaxReport',
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
					$("#searchForm").attr("action", "${ctx}/recruit/student/recruitStudent/export");
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

	<form id="searchForm"
		
		method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}"
			callback="page();" />
		<ul class="ul-form" id="element_id">

			<li><label>学院:</label><select name="company.name" id="school"
				class="school" style="width: 200px;">
					<option value="" selected="selected">==请选择学院==</option>
			</select></li>
			<li><label>专业:</label> <select name="office.name" id="major"
				class="major" style="width: 200px;">
					<option value="" selected="selected">==请选择专业==</option>
			</select></li>
			<li><label>年级:</label> <select name="grade" id="grade"
				class="grade" style="width: 200px;">
					<option value="" selected="selected">==请选择年级==</option>
			</select></li>
			<li><label>班级:</label> <select name="classno" id="classno"
				class="clazz.id" style="width: 200px;">

			</select></li>
			<li class="btns"> <input
				id="btnExport" class="btn btn-primary" type="button" value="导出" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<sys:message content="${message}" />
	<div id="total">统计信息</div>
</body>
</html>