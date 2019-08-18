<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学籍信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出学籍数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/uc/student/export?action=download");
						$("#searchForm").submit();
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
			
			$("#btnStudent").click(function() {
				top.$.jBox.confirm("确认要导出学生数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/uc/student/toStudent?action=download");
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



	<ul class="nav nav-tabs">
		<li class="active"><a href="#">学籍下载</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ucStudent"
		action="${ctx}/uc/student/download" method="post"
		class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>考试号：</label> <form:input path="exaNumber"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>学号：</label> <form:input path="studentNumber"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>真实姓名：</label> <form:input path="username"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>身份证号码：</label> <form:input path="idCard"
					htmlEscape="false" maxlength="18" class="input-medium" /></li>
			<li class="clearfix"></li>
			<li><label>年级：</label> <form:input path="currentLevel"
					htmlEscape="false" maxlength="18" class="input-medium" /></li>


			<li><label>学历：</label> <form:select path="edu"
					class="input-medium " style="width:175px;">
					<option value="">请选择</option>
					<form:options items="${fns:getDictList('student_edu')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>学制：</label> <form:select path="schoolSystem"
					class="input-medium " style="width:175px;">
					<option value="">请选择</option>
					<form:options items="${fns:getDictList('student_school_system')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>

			<li class="clearfix"></li>

			<li class="btns"><input id="btnExport" class="btn btn-primary"
				type="button" value="导出学籍信息" />
				
				<input id="btnStudent" class="btn btn-primary"
				type="button" value="导出学生信息" />
				</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
</body>
</html>