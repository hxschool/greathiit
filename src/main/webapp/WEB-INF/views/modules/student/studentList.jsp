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
			  url: '${ctx}/sys/office/treeLink',
			  selects: ['department', 'specialty',"clazz"], 
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
	function getIds(){
		var id = document.getElementsByName("ids");// 获取全选复选框
		var ids = "";
		for (var i = 0; i < id.length; i++) {
			if (id[i].checked) {
				ids += id[i].value + ",";
			}
		}
		ids = ids.substring(0, ids.length - 1);
		return ids;
	}
	
	function batchAction(action){
		var num = $("input[type='checkbox']:checked").length;
		if (num == 0) {
			  layer.alert('请选择你要操作的数据', {
				    skin: 'layui-layer-lan'
				    ,closeBtn: 0
				    ,anim: 4 //动画类型
				  });
			return;
		} 
		var batchUrl = "${ctx}/student/action/batchAction?module=student"
		$("#action").val(action);
		if(action==1||action==12){
			$("#action").val("1");
			layer.confirm('您确定修改当前学生信息为在籍?', {
			  btn: ['确定','取消'] 
			}, function(text, index){
				$("#searchForm").attr("action",batchUrl+"&ids=" + getIds());
				$("#searchForm").submit();
				layer.close(index);
			});
			return;
		}
		//选择专业
		if(action==2){
			layer.open({
			     type: 2,
			     area: ['560px','560px'],
			     shade: 0.3,
			     shadeClose: false,//默认开启遮罩关闭
			     resize: false,//默认重设大小是否
			     maxmin: true,//默认最大最小化按钮
			     moveType: 1,//默认拖拽模式，0或者1
			     content: "${ctx}/system/major", 
			     btn: ['确定','关闭'],
			     yes: function (index) {
			         //获取选择的row,并加载到页面
			         var text = window["layui-layer-iframe" + index].callbackdata();
			         if(text){
					    $("#description").val(text);
					    $("#searchForm").attr("action",batchUrl+"&ids=" + getIds());
						$("#searchForm").submit();
			             layer.close(index)
			         }else{
			             layer.msg('请选择专业', {icon: 0});
			         }
			     },
			     cancel: function(){
			              
			     }
			 });
		}
		
		if(action==3||action==4||action==5||action==6){
			layer.open({
			     type: 2,
			     area: ['560px','560px'],
			     shade: 0.3,
			     shadeClose: false,//默认开启遮罩关闭
			     resize: false,//默认重设大小是否
			     maxmin: true,//默认最大最小化按钮
			     moveType: 1,//默认拖拽模式，0或者1
			     content: "${ctx}/system/clazz", 
			     btn: ['确定','关闭'],
			     yes: function (index) {
			         //获取选择的row,并加载到页面
			         var text = window["layui-layer-iframe" + index].callbackdata();
			         if(text){
					    $("#description").val(text);
					    $("#searchForm").attr("action",batchUrl+"&ids=" + getIds());
						$("#searchForm").submit();
			             layer.close(index)
			         }else{
			             layer.msg('请选择班级', {icon: 0});
			         }
			     },
			     cancel: function(){
			              
			     }
			 });
		}
		if(action==7){
			layer.prompt({
				  formType: 0,
				  title: '请输入学校名称',
				}, function(text, index, elem){
				  $("#description").val(text);
			      $("#searchForm").attr("action",batchUrl+"&ids=" + getIds());
				  $("#searchForm").submit();
				  layer.close(index);
				});
		}
		
		if(action==8||action==11||action==99||action==14){
			var title = "请输入保留学籍原因";
			if(action==8){
				title = "请输入休学原因";
			}else if(action==11){
				title = "请输入暂缓注册原因";
			}else if(action==14){
				title = "请输入保留学籍原因";
			}else if(action==99){
				title = "请输入学籍注销原因";
			}
			layer.prompt({
				  formType: 2,
				  title: title,
				}, function(text, index, elem){
				  $("#description").val(text);
			      $("#searchForm").attr("action",batchUrl+"&ids=" + getIds());
				  $("#searchForm").submit();
				  layer.close(index);
				});
		}
		if(action==9){
			layer.confirm("是否将当前学生设置已结业", {btn: ['确定', '取消'], title: "提示信息"}, function () {
				$("#searchForm").attr("action",batchUrl+"&ids=" + getIds());
				$("#searchForm").submit();
		    })
		}
		if(action==10){
			layer.confirm("是否将当前学生设置已毕业", {btn: ['确定', '取消'], title: "提示信息"}, function () {
				$("#searchForm").attr("action",batchUrl+"&ids=" + getIds());
				$("#searchForm").submit();
		    })
		}
		if(action==13){
			layer.confirm("是否将当前信息设置成预计毕业生,如设置预计毕业生数据将通知离校系统", {btn: ['确定', '取消'], title: "提示信息"}, function () {
				$("#searchForm").attr("action",batchUrl+"&ids=" + getIds());
				$("#searchForm").submit();
		    })
		}
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
		<input id="action" name="action" type="hidden" />
		<input type="hidden" name="description" id="description"/>
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
				<li><label>年级：</label>
				<form:input path="year" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<div id="element_id">
			<li><label>所属学院：</label> <select class="department input-medium" style="width:175px" 
				name="department" data-value="${param.department}"><option>请选择</option></select></li>

			<li><label>所属专业：</label> <select id="specialty"
				class="specialty input-medium" style="width:175px" name="specialty" data-value="${param.specialty}"><option>请选择</option></select>
			</li>
			
			<li><label>选择班级：</label> <select id="clazz"
				class="clazz input-medium" name="clazz" style="width:175px" data-value="${param.clazz}"><option>请选择</option></select>
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" name="search" value="查询"/>
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
				<shiro:hasPermission
							name="uc:student:operation">
				<th><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/> </th>
				</shiro:hasPermission>
				<th>考生号</th>
				<th>学号</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>院系</th>
				<th>专业</th>
				<th>班级</th>
				<th>导员</th>
				<th>政治面貌</th>
				<th>联系地址</th>
				<th>学历</th>
				<th>学籍状态</th>
				<shiro:hasPermission name="student:student:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="student">
			<c:set var="banji" scope="session" value="${student.clazz}"/>
			<c:set var="zhuanye" scope="session" value="${fns:getParentOffice(banji.id)}"/>
			<c:set var="xueyuan" scope="session" value="${fns:getParentOffice(zhuanye.id)}"/>
			<tr>
				<shiro:hasPermission
							name="uc:student:operation"><td><input type="checkbox" name="ids" value="${student.id}" /></td>  </shiro:hasPermission>
				<td>${student.exaNumber}</td>
				<td>
					${student.studentNumber}
				</td>
				<td><a href="${ctx}/student/student/form?id=${student.id}">
					${student.name}
				</a></td>
				<td>
					
					<a  href="javascript:void(0);" onclick="js_method('${student.idCard}')">${fn:substring(student.idCard, 0, 10)}****${fn:substring(student.idCard, 14,18)}</a>
				</td>
				<td>
					${xueyuan.name}
				</td>
				<td>
					
					${zhuanye.name}
				</td>
				<td>
					${banji.name}
				</td>
				
				<td>
					${student.clazz.deputyPerson}
				</td>
				
				
				<td>
					${fns:getDictLabel(student.political,'political','')}
				</td>
				<td>
					${student.address}
				</td>

				<td>
					${fns:getDictLabel(student.edu,'student_edu','')}
				</td>
				<td>
					${fns:getDictLabel(student.status,'student_status','')}
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
		<tfoot><tr>
			<th ><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/></th><th colspan="15"> 
			
			
				   <c:forEach items="${fns:getDictList('student_status')}" var="dict">
				   		<c:set value="button-primary" var="cssColor"></c:set>
						<c:if test="${dict.value==99}">
							<c:set value="button-caution" var="cssColor"></c:set>
						</c:if>
						<button class="button ${cssColor} button-rounded button-tiny " onclick="batchAction('${dict.value}')" type="button">${dict.label}</button>
				   </c:forEach>
			</th>
			</tr>
		</tfoot>
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
  function js_method(idcard){
	  layer.msg(idcard)
  }
  </script>	
</body>
</html>