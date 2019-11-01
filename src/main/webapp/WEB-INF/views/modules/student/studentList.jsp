<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出学生数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$("#searchForm").attr("action", "${ctx}/student/student/export");
					$("#searchForm").submit();
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		});
		
		
		$("#btnImport").click(function() {
			$.jBox($("#importBox").html(), {
				title : "导入数据",
				buttons : {
					"关闭" : true
				},
				bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
			});
		});
		
		
	    $('#element_id').cxSelect({ 
			  url: '${ctx}/sys/office/treeClassLink',
			  selects: ['department', 'specialty','grade',"clazz"], 
			  jsonName: 'name',
			  jsonValue: 'value',
			  jsonSub: 'sub'
			});
})
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	function showModal(url,name){
		var index = layer.open({
			 title:name,
		     type: 2,
		     area: ['560px','560px'],
		     shade: 0.3,
		     shadeClose: false,//默认开启遮罩关闭
		     resize: false,//默认重设大小是否
		     maxmin: true,//默认最大最小化按钮
		     moveType: 1,//默认拖拽模式，0或者1
		     content: url, 
		     btn: ['确定','关闭'],
		     yes: function (index) {
		    	 layer.close(index)
		     },
		     cancel: function(){
		              
		     }
		 });
		layer.full(index);
	}
	
	</script>
</head>
<body>
		<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/student/student/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/student/student/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/student/student/">学生信息列表</a></li>
		<shiro:hasPermission name="student:student:edit"><li><a href="${ctx}/student/student/form">学生信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="student" action="${ctx}/student/student/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
			<label>考生号：</label>
				<form:input path="exaNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号码：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li class="clearfix"></li>

			<div id="element_id">
			<li><label>所属学院：</label> <select class="department input-medium" style="width:175px"
				name="department"><option>请选择</option></select></li>

			<li><label>所属专业：</label> <select id="specialty"
				class="specialty input-medium" style="width:175px" name="specialty"><option>请选择</option></select>
			</li>
			<li><label> 年级：</label> <select id="grade"
				class="grade input-medium" style="width:175px"><option>请选择</option></select></li>
			<li><label>选择班级：</label> <select id="clazz"
				class="clazz input-medium" name="clazz" style="width:175px"><option>请选择</option></select>
			</li>
			</div>
		
		<li class="clearfix"></li>
	<li><label>班主任：</label>
				  


<input id="primaryPerson"  class="input-medium ">
<input type="hidden" id="primaryPersonId" name="primaryPersonId"  class="input-medium ">
			</li>
				<li><label>导员：</label>
				<input id="deputyPerson"  class="input-medium">
				<input type="hidden" id="deputyPersonId" name="deputyPersonId"  class="input-medium ">
			</li>
			<li><label>学历：</label>
				
				<form:select path="edu" class="input-medium " style="width:175px">
							<option value="">请选择</option>
							<form:options items="${fns:getDictList('student_edu')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
			</li>
			
			
			<li><label>状态：</label>
						<form:select path="status" class="input-medium " style="width:175px">
							<option value="">请选择</option>
							<form:options items="${fns:getDictList('student_status')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<shiro:hasPermission name="student:student:export">
			<input
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" />
				</shiro:hasPermission>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考生号</th>
				<th>学号</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>生日</th>
				<th>性别</th>
				<th>民族</th>
				<th>政治面貌</th>
				<th>联系地址</th>
				<th>email</th>
				<th>学历</th>
				<th>户口所在地</th>
				<th>学制</th>
				
				<shiro:hasPermission name="student:student:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="student">
			<tr>
				<td>${student.exaNumber}</td>
				<td>
					${student.studentNumber}
				</td>
				<td><a href="${ctx}/student/student/form?id=${student.id}">
					${student.name}
				</a></td>
				<td>
					${fns:abbr(student.idCard,13)}
				</td>
				<td>
					<fmt:formatDate value="${student.birthday}" pattern="yyyy年MM月dd"/>
				</td>
				<td>
					${fns:getDictLabel(student.gender,'sex','')}
				</td>
				<td>
					${fns:getDictLabel(student.nation,'nation','')}
				</td>
				<td>
					${fns:getDictLabel(student.political,'political','')}
				</td>
				<td>
					${student.address}
				</td>
				<td>
					${student.mail}
				</td>

				<td>
					${fns:getDictLabel(student.edu,'student_edu','')}
				</td>
				<td>
					${student.nativePlace}
				</td>
				<td>
					${fns:getDictLabel(student.studentLength,'student_school_system','')}
				</td>
				
				<shiro:hasPermission name="student:student:edit"><td>
					<shiro:hasPermission name="student:student:chengji">
						<a  href="javascript:void(0);" onclick="showModal('${ctx}/student/studentCourse/wechat?student.studentNumber=${student.studentNumber}','${student.name}')">成绩信息</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="student:student:info">
						<a  href="javascript:void(0);" onclick="showModal('${ctx}/student/student/info?id=${student.id}','${student.name}')">个人信息</a>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="student:student:prove">
						<a href="${ctx}/student/student/prove?id=${student.id}">在校证明</a>
					</shiro:hasPermission>
					
    				<a href="${ctx}/student/student/form?id=${student.id}">修改</a>
					<a href="${ctx}/student/student/delete?id=${student.id}" onclick="return confirmx('确认要删除该学生信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	
	
<script>
  $( function() {
	  // url : '${ctx}/teacher/teacher/ajaxTeacher',
	  $("#primaryPerson").autocomplete({
		    source: function (request, response){
		  	  $.ajax({  
		          url:  '${ctx}/teacher/teacher/ajaxTeacher',
		          dataType: "json",  
		          data:{  
		              tchrName: request.term  
		          },  
		          success: function( data ) {  
		              response( $.map( data, function( item ) {  
		                  return {  
		                  	value:item.teacherNumber,  
		                    label:item.tchrName
		                  }  
		              }));  
		          }  
		      });  
		  	},
		    select: function(event, ui){
		    	console.log(ui.item.label)
		    	console.log(ui.item.value)
		        $("#primaryPerson").val( ui.item.label );
		        $("#primaryPersonId").val( ui.item.value );
		        
		        // 必须阻止事件的默认行为，否则autocomplete默认会把ui.item.value设为输入框的value值
		        event.preventDefault();     
		    }
		});
	  
	  $("#deputyPerson").autocomplete({
		    source: function (request, response){
		  	  $.ajax({  
		          url:  '${ctx}/teacher/teacher/ajaxTeacher',
		          dataType: "json",  
		          data:{  
		              tchrName: request.term  
		          },  
		          success: function( data ) {  
		              response( $.map( data, function( item ) {  
		                  return {  
		                  	value:item.teacherNumber,  
		                    label:item.tchrName
		                  }  
		              }));  
		          }  
		      });  
		  	},
		    select: function(event, ui){
		    	console.log(ui.item.label)
		    	console.log(ui.item.value)
		        $("#deputyPerson").val( ui.item.label );
		        $("#deputyPersonId").val( ui.item.value );
		        event.preventDefault();     
		    }
		});
  });
  </script>	
</body>
</html>