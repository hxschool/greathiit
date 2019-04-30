<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>招生计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/out/score/rsEnrollmentPlan/">招生计划列表</a></li>
		<li class="active"><a href="${ctx}/out/score/rsEnrollmentPlan/form?id=${rsEnrollmentPlan.id}">招生计划<shiro:hasPermission name="out:score:rsEnrollmentPlan:edit">${not empty rsEnrollmentPlan.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="out:score:rsEnrollmentPlan:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rsEnrollmentPlan" action="${ctx}/out/score/rsEnrollmentPlan/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">学期设置：</label>
			<div class="controls">
				<select name="termYear" style="width: 200px;">

					<c:forEach items="${fns:termYear()}" var="termYear">
						<option value="${termYear.key}"
							<c:if test="${config.termYear==termYear.key}"> selected="selected" </c:if>>${termYear.key}</option>
					</c:forEach>
				</select>
				 默认学期设置:${config.termYear}
			</div>
		</div>
		
		<div id="element_id">
						<div class="control-group control-group-left">
							<label class="control-label">所属学院：</label>
							<div class="controls">
								<select class="province input-medium"><option>请选择</option></select>
							</div>
						</div>
						<div class="control-group control-group-left">
							<label class="control-label">专业名称：</label>
							<div class="controls">
								<select id="city" name="majorId" class="city input-medium"><option>请选择</option></select>
							</div>
						</div>
						</div>
		

		<div class="control-group">
			<label class="control-label">所选专业：</label>
			<div class="controls">
				<form:input path="majorName" id="majorName" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已招人数：</label>
			<div class="controls">
				<form:input path="majorCount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计划人数：</label>
			<div class="controls">
				<form:input path="majorTotal" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				
				<form:select path="status"
					class="input-medium">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="out:score:rsEnrollmentPlan:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#element_id').cxSelect({ 
				  url: '${ctx}/sys/office/treeLink',
				  selects: ['province', 'city'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
			$('#city').change(function(){  
			　　　　　   $("#majorName").val($(this).children('option:selected').text());
			　　　　})  
		});
		</script>
</body>
</html>