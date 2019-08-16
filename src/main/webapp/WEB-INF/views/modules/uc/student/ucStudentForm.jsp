<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学籍信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
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
		<li><a href="${ctx}/uc/student/">学籍信息列表</a></li>
		<li class="active"><a
			href="${ctx}/uc/student/form?id=${ucStudent.id}">学籍信息<shiro:hasPermission
					name="uc:ucStudent:edit">${not empty ucStudent.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="uc:ucStudent:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ucStudent"
		action="${ctx}/uc/student/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />


		<ul id="myTab" class="nav nav-pills" role="tablist">
			<li class="active"><a href="#bulletin" role="tab"
				data-toggle="pill">基础信息</a></li>
			<li><a href="#rule" role="tab" data-toggle="pill">学院信息</a></li>
			<li><a href="#forum" role="tab" data-toggle="pill">录取通知书</a></li>
			<li><a href="#security" role="tab" data-toggle="pill">成绩信息</a></li>
		</ul>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="bulletin">
				<div class="control-group">
					<label class="control-label">考试号：</label>
					<div class="controls">
						<form:input path="exaNumber" htmlEscape="false" maxlength="64"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">生源所在地：</label>
					<div class="controls">
						<form:input path="location" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学号：</label>
					<div class="controls">
						<form:input path="studentNumber" htmlEscape="false" maxlength="64"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">真实姓名：</label>
					<div class="controls">
						<form:input path="username" htmlEscape="false" maxlength="64"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">性别：</label>
					<div class="controls">


						<form:select path="gender" class="input-medium">
							<form:options items="${fns:getDictList('sex')}" itemLabel="label"
								itemValue="value" htmlEscape="false" />
						</form:select>

						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">出生日期：</label>
					<div class="controls">
						<form:input path="birthday" htmlEscape="false" maxlength="14"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">身份证号码：</label>
					<div class="controls">
						<form:input path="idCard" htmlEscape="false" maxlength="18"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">政治面貌：</label>
					<div class="controls">
						<form:select path="political" class="input-xlarge ">
							<form:options items="${fns:getDictList('political')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">民族：</label>
					<div class="controls">

						<form:select path="nation" class="input-xlarge ">
							<form:options items="${fns:getDictList('nation')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">状态：</label>
					<div class="controls">
							<form:select path="status" class="input-xlarge ">
							<form:options items="${fns:getDictList('student_uc_status')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">身份所在城市代码：</label>
					<div class="controls">
						<form:input path="regionCode" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">身份所在城市信息：</label>
					<div class="controls">
						<form:input path="regionName" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注信息：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="4"
							maxlength="255" class="input-xxlarge " />
					</div>
				</div>

			</div>
			<div class="tab-pane fade" id="rule">
				<div class="control-group">
					<label class="control-label">标注代码：</label>
					<div class="controls">
						<form:input path="departmentCode" htmlEscape="false"
							maxlength="64" class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学院代码：</label>
					<div class="controls">
						<form:input path="departmentId" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学院名称：</label>
					<div class="controls">
						<form:input path="departmentName" htmlEscape="false"
							maxlength="64" class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">标注代码：</label>
					<div class="controls">
						<form:input path="majorCode" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">专业代码：</label>
					<div class="controls">
						<form:input path="majorId" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">专业名称：</label>
					<div class="controls">
						<form:input path="majorName" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">班号：</label>
					<div class="controls">
						<form:input path="classNumber" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学历：</label>
					<div class="controls">

						<form:select path="edu" class="input-medium" style="width:178px">
							<form:option value="" label="请选择" />
							<form:option value="本科" label="本科" />
							<form:option value="专科" label="专科" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学制：</label>
					<div class="controls">


						<form:select path="schoolSystem" class="input-medium">
							<form:option value="" label="请选择" />
							<form:option value="2" label="2" />
							<form:option value="3" label="3" />
							<form:option value="4" label="4" />
							<form:option value="5" label="5" />
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学习形式：</label>
					<div class="controls">
						<form:input path="learning" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">入学日期：</label>
					<div class="controls">
						<form:input path="startDate" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">当前所在年级：</label>
					<div class="controls">
						<form:input path="currentLevel" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">结业日期(预计毕业日期)：</label>
					<div class="controls">
						<form:input path="overDate" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>

			</div>

			<div class="tab-pane fade" id="forum">

				<div class="control-group">
					<label class="control-label">考试类别：</label>
					<div class="controls">
						<form:input path="exaCategory" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">录取通知书编号：</label>
					<div class="controls">
						<form:input path="noticeNumber" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">收件人：</label>
					<div class="controls">
						<form:input path="addressee" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">联系电话：</label>
					<div class="controls">
						<form:input path="phone" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>


				<div class="control-group">
					<label class="control-label">家庭地址：</label>
					<div class="controls">
						<form:input path="homeAddress" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">邮政编码：</label>
					<div class="controls">
						<form:input path="zipCode" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">户口所在地：</label>
					<div class="controls">
						<form:input path="hokouAddress" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>

			</div>

			<div class="tab-pane fade" id="security">

				<div class="control-group">
					<label class="control-label">是否服从定向调剂：</label>
					<div class="controls">
						<form:input path="isChange" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">语文：</label>
					<div class="controls">
						<form:input path="yuwen" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">数学：</label>
					<div class="controls">
						<form:input path="shuxue" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">外语：</label>
					<div class="controls">
						<form:input path="waiyu" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">综合：</label>
					<div class="controls">
						<form:input path="zonghe" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">总分：</label>
					<div class="controls">
						<form:input path="zongfen" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">特长：</label>
					<div class="controls">
						<form:input path="techang" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
					</div>
				</div>


			</div>
		</div>




		<div class="form-actions">
			<shiro:hasPermission name="uc:ucStudent:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	
	<script type="text/javascript">
	
	$(function(){
		var p = location.hash;
		
		if(p!=""){
			$("#myTab li").each(function(){
	    		var href = $(this).children("a").attr("href");
	    		$(this).removeClass("active");
	    		if(href==p){
	    			$(this).addClass("active");
	    		}
	  		});
			
			$("#myTabContent .tab-pane").each(function(){
				$(this).removeClass('in active')
	    		if("#"+$(this).attr("id")==p){
	    			$(this).addClass("in active");
	    		}
	  		});
			
		}
    });
	</script>
</body>
</html>