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
	
		function showComplete(name,studentNumber){
			layer.open({
				 title:name,
			     type: 2,
			     area: ['560px','560px'],
			     shade: 0.3,
			     shadeClose: false,//默认开启遮罩关闭
			     resize: false,//默认重设大小是否
			     maxmin: true,//默认最大最小化按钮
			     moveType: 1,//默认拖拽模式，0或者1
			     content: "${ctx}/student/studentCourse/wechat?student.studentNumber="+studentNumber, 
			     btn: ['确定','关闭'],
			     yes: function (index) {
			    	 layer.close(index)
			     },
			     cancel: function(){
			              
			     }
			 });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/student/complete/">毕业生管理</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="student" action="${ctx}/student/complete/?op=search" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
			<label>考试号：</label>
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
			
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考试号</th>
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
				<th>成绩</th>
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
					<td>
					<a  href="javascript:void(0);" onclick="showComplete('${student.name}','${student.studentNumber}')">成绩信息</a>
					<shiro:hasPermission name="student:student:info">
						<a href="${ctx}/student/student/info?id=${student.id}">个人信息</a>
					</shiro:hasPermission>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>