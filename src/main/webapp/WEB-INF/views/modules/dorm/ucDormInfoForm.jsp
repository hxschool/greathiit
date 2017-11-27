<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>宿舍管理管理</title>
	<meta name="decorator" content="default"/>
	
</head>


<body>
	
	<form action="${ctx}/dorm/ucDorm/save" method="post" class="form-horizontal">
		
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
					<select id="student" class="input-medium"><option>请选择</option></select>
				</div>
			</div>
			
				<div class="control-group">
				<label class="control-label">公寓：</label>
				<div class="controls">
					<select  class="input-medium">
					<option>请选择</option>
				
					   <c:forEach var="ret"  items="${fns:getDictList('yes_no')}"   varStatus="status">
                            <option value="${ret.value }">${ret.label }</option>
                  		</c:forEach>
					</select>
				</div>
			</div>

		</div>

		<div class="form-actions">
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
				var officeId = $("#city").val();
				var clazzId = $(this).children('option:selected').val();
				
				 $.ajax({
			          url: "${ctx}/dorm/ucDorm/student",
			          data: {
			        	  officeId: officeId,
			        	  clazzId: clazzId,
			          },
			          success: function( data ) {
			        	  $("#student").empty();
			              var optionString = "";
			        	  var beanList = data;            //返回的json数据
			                if(beanList){                   //判断
			                    for(var i=0; i<beanList.length; i++){ //遍历，动态赋值
			                        optionString +="<option  value=\""+beanList[i].id+"\"";  
			                        optionString += ">"+beanList[i].name+"</option>";  //动态添加数据  
			                    }   
			                	$("#student").append(optionString);  // 为当前name为asd的select添加数据。
			                }  
			          }
			        });
			});
		});
	</script>
</body>
</html>