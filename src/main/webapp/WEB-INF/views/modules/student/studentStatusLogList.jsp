<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学籍异动</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function showMessage(module,moduleId){
		$.ajax({
		    type: 'post',
		    url: '${ctx}/student/studentStatusLog/listData',
		    contentType: 'application/json;charset=utf-8',
		    data:{"module":module, "moduleId":moduleId },
		    success: function (data) { //返回json结果
		        for(ll in data){
		    		console.log(ll);
		    	}
		        layer.open({
					  type: 1,
					  skin: 'layui-layer-rim', //加上边框
					  area: ['420px', '240px'], //宽高
					  content: 'html内容'
					});
		    }
		});
		
		
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/student/studentStatusLog/">变动进度表列表</a></li>

	</ul>
	<form:form id="searchForm" modelAttribute="studentStatusLog"
		action="${ctx}/student/studentStatusLog/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">

			<li><label>模块：</label><select path="module"
					class="input-medium " style="width:175px" onchange="findtemplate(this.options[this.options.selectedIndex].value);">
					<option >请选择</option>
					<option value="uc_student" <c:if test="${param.module=='uc_student' }"> selected </c:if> >(省)学籍信息</option>
					<option value="student"  <c:if test="${param.module=='student' }"> selected </c:if>>(校)学籍信息</option>
				</select></li>
			<li><label>状态：</label> <form:select path="status"
					class="input-medium " style="width:175px">
					<option value="">请选择</option>
					<form:options items="${fns:getDictList('student_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td>操作模型</td>
				<td>学号</td>
				<td>姓名</td>
				<td>操作人</td>
				<td>操作类型</td>
				
				<td>过程描述</td>
				<td>操作时间</td>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="student:studentStatusLog:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="studentStatusLog">
				<tr>
					<td>${studentStatusLog.module=="uc_student"?"(省)学籍信息":"(校)学籍信息"}</td>
					<td>${studentStatusLog.student.studentNumber}</td>
					<td><a href="javascript:void(0)" onclick="showMessage('${studentStatusLog.module}','${studentStatusLog.moduleId}')">${studentStatusLog.student.name}</a></td>
					<td>${studentStatusLog.createBy.name} </td>
					<td>
						<c:if test="${studentStatusLog.module=='student'}">
							<c:if test="${studentStatusLog.before!=null and studentStatusLog.before!=''}">
								${fns:getDictLabel(studentStatusLog.before,'student_status','')} ->
							</c:if>
								${fns:getDictLabel(studentStatusLog.status,'student_status','')}
						</c:if>
						<c:if test="${studentStatusLog.module=='uc_student'}">
							<c:if test="${studentStatusLog.before!=null and studentStatusLog.before!=''}">
							${fns:getDictLabel(studentStatusLog.before,'student_learning','')} ->
							</c:if>
							${fns:getDictLabel(studentStatusLog.status,'student_learning','')}
						</c:if>
					</td>
					
					<td>${studentStatusLog.description }</td>
					<td><fmt:formatDate value="${studentStatusLog.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${studentStatusLog.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${studentStatusLog.remarks}</td>
					<shiro:hasPermission name="student:studentStatusLog:edit">
						<td><a
							href="${ctx}/student/studentStatusLog/form?id=${studentStatusLog.id}">修改</a>
							<a
							href="${ctx}/student/studentStatusLog/delete?id=${studentStatusLog.id}"
							onclick="return confirmx('确认要删除该变动进度表吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<script type="text/javascript">

	function findtemplate(value) {
		value = value + "_status";
	    $.ajax({
	        type : "post",
	        async : false,
	        url : "${ctx}/sys/dict/listData",
	        data : {
	            'type' : value
	        },
	        dataType : "json",
	        success : function(msg) {
	            $("#status").empty();
	            if (msg.length > 0) {
	                for (var i = 0; i < msg.length; i++) {
	                        var partId = msg[i].value;
	                        var partName = msg[i].label;
	                        var $option = $("<option>").attr({
	                            "value" : partId
	                        }).text(partName);
	                        $("#status").append($option);
	                }
	                $("#status").change();

	            }
	        },
	        error : function(json) {
	            $.jBox.alert("网络异常！");
	        }
	    });
	}
	</script>
</body>
</html>