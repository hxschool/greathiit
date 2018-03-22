<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
		<li class="active"><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal"><%--
		<form:hidden path="email" htmlEscape="false" maxlength="255" class="input-xlarge"/>
		<sys:ckfinder input="email" type="files" uploadPath="/mytask" selectMultiple="false"/> --%>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">头像:</label>
			<div class="controls">
				<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div id="element_id">
		
		<div class="control-group">
			<label class="control-label">所属学院:</label>
			<div class="controls">
				<label class="lbl">${user.company.name}</label>
			</div>
		</div>

			<div class="control-group">
				<label class="control-label">所属专业:</label>
				<div class="controls">
				<c:choose>
				   <c:when test="${user.office.name!=null&&user.office.name!=''}"> 
					<label class="lbl">${user.office.name}</label>
					 </c:when>
				   <c:otherwise>
					学院：<select class="province input-medium"><option>请选择</option></select>
					专业：<select id="city" class="city input-medium"><option>请选择</option></select>
					</c:otherwise>
				</c:choose>
					
				</div>
			</div>

			<div class="control-group">
			<label class="control-label">所属班级:</label>
			<div class="controls">
			<c:choose>
				   <c:when test="${user.clazz.name!=null&&user.clazz.name!=''}"> 
					<label class="lbl">${user.clazz.name}</label>
					 </c:when>
				   <c:otherwise>
				年级：<select id="clazz" class="clazz input-medium"><option>请选择</option></select>
				班级：<select id="area" class="area input-medium" name="clazzId"><option>请选择</option></select> 
					
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">所在寝室:</label>
			<div class="controls">
			<div id="dormBuild_id">
				<c:choose>
				   <c:when test="${user.dorm!=null}"> 
				        	<label class="lbl">所在${user.dorm.ucDormBuild.dormBuildNo }公寓,第${user.dorm.dormFloor }层,${user.dorm.dormNumber }室</label>
				   </c:when>
				   <c:otherwise>
							公寓：<select class="dormBuild input-medium"><option>请选择</option></select> 寝室 ：<select id="dorm" class="dorm input-medium" name="dormId"
						required="required"><option>请选择</option></select>
						床位： 1 <input type="radio" name="chuangwei"  value="a" checked> 2 <input type="radio" name="chuangwei"  value="b" > 3 <input type="radio" name="chuangwei"  value="c" > 4 <input type="radio" name="chuangwei"  value="d" >
						
				   </c:otherwise>
				</c:choose>
				
			</div>	
				
				
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">学号:</label>
			<div class="controls">
				<form:input path="no" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50" class="email" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型:</label>
			<div class="controls">
				<label class="lbl">${fns:getDictLabel(user.userType, 'sys_user_type', '无')}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<label class="lbl">${user.roleNames}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上次登录:</label>
			<div class="controls">
				<label class="lbl">IP: ${user.oldLoginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.oldLoginDate}" type="both" dateStyle="full"/></label>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#dormBuild_id').cxSelect({ 
				  url: '${ctx}/dorm/ucDormBuild/treeLink',
				  selects: ['dormBuild', 'dorm'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
			
			$('#element_id').cxSelect({ 
				  url: '${ctx}/sys/office/treeClassLink',
				  selects: ['province', 'city','clazz', 'area'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
		});
		</script>
</body>
</html>