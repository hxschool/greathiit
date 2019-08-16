<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学籍信息管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/layer/css/layui.css"/>
 <script type="text/javascript" src="${ctxStatic}/layer/layui.js"></script>
	<script type="text/javascript">
	
	
		$(document).ready(function() {
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出学籍数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/uc/action/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action", "${ctx}/uc/action/");
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
		});
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
					$("#searchForm").attr("action","${ctx}/uc/action/batchAction?ids=" + getIds());
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
						    $("#searchForm").attr("action","${ctx}/uc/action/batchAction?ids=" + getIds());
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
						    $("#searchForm").attr("action","${ctx}/uc/action/batchAction?ids=" + getIds());
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
				      $("#searchForm").attr("action","${ctx}/uc/action/batchAction?ids=" + getIds());
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
				      $("#searchForm").attr("action","${ctx}/uc/action/batchAction?ids=" + getIds());
					  $("#searchForm").submit();
					  layer.close(index);
					});
			}
			if(action==9){
				layer.confirm("是否将当前学生设置已结业", {btn: ['确定', '取消'], title: "提示信息"}, function () {
					$("#searchForm").attr("action","${ctx}/uc/action/batchAction?ids=" + getIds());
					$("#searchForm").submit();
			    })
			}
			if(action==10){
				layer.confirm("是否将当前学生设置已毕业", {btn: ['确定', '取消'], title: "提示信息"}, function () {
					$("#searchForm").attr("action","${ctx}/uc/action/batchAction?ids=" + getIds());
					$("#searchForm").submit();
			    })
			}
			if(action==13){
				layer.confirm("是否将当前信息设置成预计毕业生,如设置预计毕业生数据将通知离校系统", {btn: ['确定', '取消'], title: "提示信息"}, function () {
					$("#searchForm").attr("action","${ctx}/uc/action/batchAction?ids=" + getIds());
					$("#searchForm").submit();
			    })
			}
		}
	</script>
</head>
<body>


	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/uc/action/">学籍信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/action/?op=search" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
						<form:select path="status" class="input-medium ">
							<option value="">请选择</option>
							<form:options items="${fns:getDictList('student_status')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
			</li>
			
			<li class="clearfix"></li>
			
			<li class="btns"><label></label><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> 
				<input type="hidden" name="action" id="action"/>
				<input type="hidden" name="description" id="description"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-hover ">
		<thead>
			<tr>
					<shiro:hasPermission
							name="uc:ucStudent:operation">
				<th><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/> </th>
				</shiro:hasPermission>
				<th>真实姓名</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>身份证号码</th>

				<th>学院名称</th>
				<th>专业名称</th>
				<th>学历</th>
				<th>学制</th>
				<th>学习形式</th>
				<th>入学日期</th>
				<th>状态</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucStudent">
			<tr>
				<shiro:hasPermission
							name="uc:action:operation"><td><input type="checkbox" name="ids" value="${ucStudent.id}" onclick="selectSingle()"/></td>  </shiro:hasPermission>

				<td>
					${ucStudent.username}
				</td>
				<td>
					${fns:getDictValue(ucStudent.gender, 'sex', '男')}
				</td>
				<td>
					${ucStudent.birthday}
				</td>
				<td>
					${fns:abbr(ucStudent.idCard,13)}
				</td>
				
				<td>
					${ucStudent.departmentName}
				</td>
				<td>
					${ucStudent.majorName}
				</td>

				<td>
					${fns:getDictLabel(ucStudent.edu,'student_edu','')}
				</td>
				<td>
					${fns:getDictLabel(ucStudent.schoolSystem,'student_school_system','')}
				</td>
				<td>
					${fns:getDictLabel(ucStudent.learning,'student_learning','')}
				</td>
				<td>
					${ucStudent.startDate}
				</td>
	

				<td>
					${fns:getDictLabel(ucStudent.status,'student_status','')}
					
					<c:if test="${ucStudent.description!=null}">
					              <p class="text-warning">${ucStudent.description}</p>
					</c:if>
				</td> 
			</tr>
		</c:forEach>
		</tbody>
				<shiro:hasPermission
							name="uc:action:operation">
		<tfoot><tr>
			<th ><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/></th><th colspan="15"> 
			<shiro:hasPermission
							name="uc:action:tingyong">
				<a href="#" onclick="batchBox('${ctx}/uc/action/deleteList')" class="btn btn-primary">批量停用</a>
			</shiro:hasPermission>
			<c:if test="${param.action!=null and param.action!='' and param.action!=1 }">
			
			<shiro:hasPermission
							name="uc:action:batch">
				<a href="#" onclick="batchAction(${param.action})" class="btn btn-primary">${fns:getDictLabel(param.action,'student_status','')}</a>
				</shiro:hasPermission>
			</c:if>
			</th>
			</tr>
		</tfoot>
		</shiro:hasPermission>
	</table>
	<div class="pagination">${page}</div>

	<div id="student_status_2" style="display: none">
		
	</div>


</body>
</html>