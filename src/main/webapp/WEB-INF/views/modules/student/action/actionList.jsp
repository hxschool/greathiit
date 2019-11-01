<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
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
		$("#action").val(action);
		if(action==1||action==12){
			$("#action").val("1");
			layer.confirm('您确定修改当前学生信息为在籍?', {
			  btn: ['确定','取消'] 
			}, function(text, index){
				$("#searchForm").attr("action","${ctx}/student/action/batchAction?ids=" + getIds());
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
					    $("#searchForm").attr("action","${ctx}/student/action/batchAction?ids=" + getIds());
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
					    $("#searchForm").attr("action","${ctx}/student/action/batchAction?ids=" + getIds());
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
			      $("#searchForm").attr("action","${ctx}/student/action/batchAction?ids=" + getIds());
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
			      $("#searchForm").attr("action","${ctx}/student/action/batchAction?ids=" + getIds());
				  $("#searchForm").submit();
				  layer.close(index);
				});
		}
		if(action==9){
			layer.confirm("是否将当前学生设置已结业", {btn: ['确定', '取消'], title: "提示信息"}, function () {
				$("#searchForm").attr("action","${ctx}/student/action/batchAction?ids=" + getIds());
				$("#searchForm").submit();
		    })
		}
		if(action==10){
			layer.confirm("是否将当前学生设置已毕业", {btn: ['确定', '取消'], title: "提示信息"}, function () {
				$("#searchForm").attr("action","${ctx}/student/action/batchAction?ids=" + getIds());
				$("#searchForm").submit();
		    })
		}
		if(action==13){
			layer.confirm("是否将当前信息设置成预计毕业生,如设置预计毕业生数据将通知离校系统", {btn: ['确定', '取消'], title: "提示信息"}, function () {
				$("#searchForm").attr("action","${ctx}/student/action/batchAction?ids=" + getIds());
				$("#searchForm").submit();
		    })
		}
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/student/action/">${fns:getDictLabel(param.action,'student_status','学生信息')}</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="student" action="${ctx}/student/action/?op=search" method="post" class="breadcrumb form-search">
		<input id="action" name="action" type="hidden" value="${param.action}"/>
		<input type="hidden" name="description" id="description"/>
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

			<li><label>学历：</label>
				
				<form:select path="edu" class="input-medium " style="width:175px">
							<option value="">请选择</option>
							<form:options items="${fns:getDictList('student_edu')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
			</li>
			<li><label>学制：</label>
						<form:select path="studentLength" class="input-medium " style="width:175px">
							<option value="">请选择</option>
							<form:options items="${fns:getDictList('student_school_system')}"
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission
							name="student:student:action">
				<th><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/> </th>
				</shiro:hasPermission>
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
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="student">
			<tr>
				<shiro:hasPermission
							name="student:student:action"><td><input type="checkbox" name="ids" value="${student.id}" onclick="selectSingle()"/></td>  </shiro:hasPermission>
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
				
				
			</tr>
		</c:forEach>
		</tbody>
		
		<shiro:hasPermission
							name="student:student:action">
		<tfoot><tr>
			<th ><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/></th><th colspan="15"> 
			
			<c:if test="${param.action!=null and param.action!='' and param.action!=1 }">
				<a href="#" onclick="batchAction(${param.action})" class="btn btn-small btn-info">${fns:getDictLabel(param.action,'student_status','')}</a>
			</c:if>
			</th>
			</tr>
		</tfoot>
		</shiro:hasPermission>
		
	</table>
	<div class="pagination">${page}</div>
</body>
</html>