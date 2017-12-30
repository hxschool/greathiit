<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

<title>学生基本信息</title>
<meta charset="UTF-8">
<meta name="decorator" content="default"/>
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/teacher/student_information.css" />

<script type="text/javascript">
	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}
	
	function isEmpty(){
		var pwd1 = document.getElementById("input-password1").value.toString();
		var pwd2 = document.getElementById("input-password2").value.toString();
		if(pwd1.trim().length == 0){
			alert("密码不能为空");
			return false;
		}
		else{
			if(pwd1!=pwd2){
				alert("两次密码不相同");
				return false;
			}
			return true;
		}
	}
</script>
</head>

<body>
	
	<div class="content">
		<div class="container">
			<div class="row">
				
				<div class="span12">
					<div class="row">
						<div class="span12 div-content-white-bgr">
							<!-- 学生基本信息 -->
							<div class="div-inf-bar">
								<label>修改基本信息</label>
							</div>
								<form class="form-horizontal" action="Student_Information_Modify_Save"
									method="post">
									<input type="hidden" name="id" value="${student.id}">
									<div class="control-group">
										<label class="control-label">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;:&nbsp;</label>
										<div class="controls"><input type="text" name="s.stuSchNum"
											id="stuSchNum" value="${student.student.no}"
											disabled="disabled"><label class="lable-modify" data-toggle="modal"
									data-target="#myModal">修改密码</label>
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="text" name="name"
											id="stuName" value="${student.name}"
											>
											</div>
									</div>
									<%-- 
									<div class="control-group">
										<lable class="control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;:&nbsp;</lable> <input type="text" name="s.stuGender"
											id="stuGender" value="${student.stuGender"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族&nbsp;:&nbsp;</lable> <input type="text" name="s.stuNation"
											id="stuNation" value="${student.stuNation"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">院&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系&nbsp;:&nbsp;</lable> <input type="text" name="s.dept.deptName"
											id="deptName" value="${student.dept.deptName"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级&nbsp;:&nbsp;</lable> <input type="text" name="s.clazz.clazzName"
											id="clazzName"
											value="${student.clazz.clazzName"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">入学日期&nbsp;:&nbsp;</lable> <input type="text" name="s.stuAttendDate"
											id="stuAttendDate"
											value="${student.stuAttendDate"/>"
											disabled="disabled">
									</div>
									<div class="control-group">
										<lable class="control-label">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;制&nbsp;:&nbsp;</lable> <input type="text" name="s.stuSchLength"
											id="stuSchLength"
											value="${student.stuSchLength"/>"
											disabled="disabled">
									</div> --%>
									
									<div class="control-group">
										<label class="control-label">籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="text" name="nativePlace"
											id="stuNativePlace"
											value="${student.nativePlace}">
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="date" name="birthday"
											id="stuBirthday" value='<fmt:formatDate value="${student.birthday}" />'>       
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="text" name="phone"
											id="stuPhone" value="${student.phone}">
									</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="email" name="mail"
											id="stuMail" value="${student.mail}">
									</div>
									</div>
									<div class="control-group">
										<label class="control-label">通信地址&nbsp;:&nbsp;</label> 
										<div class="controls"><input type="text" name="address"
											id="stuCommAddr" value="${student.address}">
									</div>
									</div>
									<div class="control-group">
									<label class="control-label">中文介绍:</label>
									<textarea name="selfIntroduce" class="textarea">${student.selfIntroduce}</textarea>
								</div>
								<div class="control-group">
									<label class="control-label">英文介绍:</label>
									<textarea name="selfEngIntroduce" class="textarea">${student.selfEngIntroduce}</textarea>
								</div>
									<div class="control-group">
									<div class="controls">
										<input type="submit" class="btn" value="提  交" />
									</div>
								</div>
								</form>
							<!-- 学生基本信息完 -->
							<!-- 自我介绍 -->
							
							<!-- 自我介绍完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框，用于修改密码 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="display:none">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h5 class="modal-title">修改密码</h5>
				</div>
				<div class="modal-body">
					<form action="Student_Information_Modify_modifyPassword" method="post"
						class="form-horizontal form-add" enctype="multipart/form-data"
						onsubmit="javascript:return isEmpty()">
						<div class="control-group">
							<label class="control-label">新密码：</label>
							<div class="controls">
								<input id="input-password1" type="password" name="password">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">确认新密码：</label>
							<div class="controls">
								<input id="input-password2" type="password">
							</div>
						</div>
						<div class="div-btn">
							<input type="submit" value="提交" class="btn">
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- 模态框，用于修改密码完 -->
	
	<script>
	$(function(){
		$(".container").css("min-height",$(document).height()-90-88-41+"px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
	});
</script>
</body>
</html>
