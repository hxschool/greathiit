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
						$("#searchForm").attr("action", "${ctx}/uc/student/export");
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
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/uc/student/biyesheng">学籍信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/student/biyesheng" method="post" class="breadcrumb form-search">
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
				
	</table>
	<div class="pagination">${page}</div>

	<div id="student_status_2" style="display: none">
		
	</div>


</body>
</html>