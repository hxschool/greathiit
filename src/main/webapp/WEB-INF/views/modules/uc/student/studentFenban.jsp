<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分班管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/layer/css/layui.css"/>
 <script type="text/javascript" src="${ctxStatic}/layer/layui.js"></script>
	<script type="text/javascript">
	
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
						    $("#searchForm").attr("action","${ctx}/uc/student/batchCls?ids=" + getIds());
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
	</script>
</head>
<body>

<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/uc/student/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/uc/student/import/template">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/uc/student/">学籍信息</a></li>
		<shiro:hasPermission name="uc:ucStudent:add"><li><a href="${ctx}/uc/student/form">学籍添加</a></li></shiro:hasPermission>
		<li><a href="${ctx}/uc/student/result">成绩信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/student/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<li><label>考生号：</label>
				<form:input path="exaNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>学号：</label>
				<form:input path="studentNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号码：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>年级：</label>
				<form:input path="currentLevel" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			
			
			<li><label>学历：</label>
				
				<form:select path="edu" class="input-medium ">
							<option value="">请选择</option>
							<form:options items="${fns:getDictList('student_edu')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
			</li>
			<li><label>学制：</label>
						<form:select path="studentLength" class="input-medium ">
							<option value="">请选择</option>
							<form:options items="${fns:getDictList('student_school_system')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
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
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" />
				
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
				<th>学号</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>身份证号码</th>
				<th>政治面貌</th>
				<th>民族</th>
				<th>学院名称</th>
				<th>专业名称</th>
				<th>班号</th>
				<th>学历</th>
				<th>学制</th>
				<th>学习形式</th>
				<th>入学日期</th>
				<th>当前所在年级</th>
				<th>结业日期</th>
				<th>状态</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ucStudent">
			<tr>
				<shiro:hasPermission
							name="uc:ucStudent:operation"><td><input type="checkbox" name="ids" value="${ucStudent.id}" onclick="selectSingle()"/></td>  </shiro:hasPermission>
				<td><a href="${ctx}/uc/student/form?id=${ucStudent.id}">
					${ucStudent.studentNumber}
				</a></td>
				<td>
					${ucStudent.name}
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
					${ucStudent.political}
				</td>
				<td>
					${ucStudent.nation}
				</td>
				<td>
					${ucStudent.departmentName}
				</td>
				<td>
					${ucStudent.majorName}
				</td>
				<td>
					${ucStudent.classNumber}
				</td>
				<td>
					${fns:getDictLabel(ucStudent.edu,'student_edu','')}
				</td>
				<td>
					${fns:getDictLabel(ucStudent.studentLength,'student_school_system','')}
				</td>
				<td>
					${fns:getDictLabel(ucStudent.learning,'student_learning','')}
				</td>
				<td>
					${ucStudent.startDate}
				</td>
				<td>
					${ucStudent.currentLevel}
				</td>
				<td>
					${ucStudent.overDate}
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
							name="uc:ucStudent:operation">
		<tfoot><tr>
			<th ><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/></th><th colspan="15"> 
			
			<a href="#" onclick="batchAction(${param.action})" class="btn btn-primary">分班</a>
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