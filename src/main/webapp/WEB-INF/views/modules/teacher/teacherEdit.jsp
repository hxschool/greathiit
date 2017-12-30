<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>教师信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}

	//显示后将request里的Message清空，防止回退时重复显示。
	function validate() {
		var pwd1 = document.getElementById("input-password1").value.toString();
		var pwd2 = document.getElementById("input-password2").value.toString();
		if (pwd1.trim().length == 0) {
			alert("密码不能为空");
			return false;
		} else {
			if (pwd1 != pwd2) {
				alert("两次密码不相同");
				return false;
			}
			return true;
		}
	}
	function isEmpty() {
		var school = document.getElementById("input-school");
		var position = document.getElementById("input-position");
		var time = document.getElementById("input-time");

		if (school.value.toString().trim().length != 0
				&& position.value.toString().trim().length != 0
				&& time.value.toString().trim().length != 0) {
			return true;
		} else {
			if (school.value.toString().trim().length == 0) {
				school.focus();
			}
			if (position.value.toString().trim().length == 0) {
				position.focus();
			}
			if (time.value.toString().trim().length == 0) {
				time.focus();
			}
			alert("输入框不能为空！");
			return false;
		}
	}
	function check(form) {
		var birthday = document.getElementById("tchrBirthday").value;
		if (birthday.trim() == "")
			return true;
		var r = birthday.match(/^(\d{1,2})(-|\/)(\d{1,2})\2(\d{1,2})$/);
		if (r == null) {
			alert("请输入格式正确的日期\n\r日期格式：yy-mm-dd\n\r例    如：68-12-25\n\r");
			return false;
		}
		var filename = document.getElementById("filename").value;
		if (filename == "") {
			return true;
		}
		var index1 = filename.lastIndexOf(".");
		var index2 = filename.length;
		var postf = filename.substring(index1 + 1, index2);//后缀名  
		if (postf != "img" && postf != "png" && postf != "jpg") {
			alert("您选择的文件格式不正确！");
			return false;
		}
		return true;
	}
</script>
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/teacher/teacher_information.css" />
</head>
<body>
	<div class="content">
		<div class="container">

			
				<div class="row">
					<div class="span12 div-content-white-bgr">
						<!-- 修改教师基本信息 -->
						<div class="div-inf-bar">
							<label>修改基本资料</label>
						</div>
						<form class="form-horizontal" enctype="multipart/form-data"
							action="teacherEditSave" method="post">
							<input type="hidden" name="id"
										value="${teacher.id}">
							<div class="control-group">
								<label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
								<div class="controls">
									<input type="text" name="tchrName"
										value="${teacher.tchrName}">
									<!-- <label class="lable-modify"
								data-toggle="modal" data-target="#myModal1">修改密码</label> -->
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">出生年月</label>
								<div class="controls">
									<input type="text" name="tchrBirthday"
										id="tchrBirthday" value="${teacher.tchrBirthday}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
								<div class="controls">
									<input type="text" name="tchrTitle" id="title"
										value="${teacher.tchrTitle}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</label>
								<div class="controls">
									<input type="text" name="tchrPhone" id="phone"
										value="${teacher.tchrPhone}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真</label>
								<div class="controls">
									<input type="text" name="tchrFax" id="fax"
										value="${teacher.tchrFax}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">电子邮件</label>
								<div class="controls">
									<input type="email" name="tchrEmail" id="email"
										value="${teacher.tchrEmail}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">办&nbsp;&nbsp;公&nbsp;&nbsp;室</label>
								<div class="controls">
									<input type="text" name="tchrOfficeAddr" id="office"
										value="${teacher.tchrOfficeAddr}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">上传头像</label>
								<div class="controls">
									<a class="btn btn-file">选择文件<input type="file" name="file"
										id="filename"></a><input name="uploadUrl" value="/tchrImg"
										style="display: none" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">自我介绍</label>
								<textarea name="tchrSelflIntroduction"
									class="textarea-selfIntr">${teacher.tchrSelfIntroduce }</textarea>
							</div>
							<div class="control-group">
								<div class="controls">
									<input type="submit" class="btn" value="提  交"
										onclick="return check(this.form)" />
								</div>
							</div>
						</form>
						<!-- 修改教师基本信息完 -->
						<!-- 修改教师学历履历 -->
						<div class="div-inf-bar">
							<label>修改学历履历信息</label>
						</div>
						<div class="div-inf-tbl">
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th>学校</th>
										<th>学位</th>
										<th>时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="tbody-exp">
									<c:forEach var="e" items="${teacherExperiments}"
										varStatus="status">
										<tr>
											<td>${e.tchrSchool }</td>
											<td>${e.tchrPosition }</td>
											<td>${e.tchrTime }</td>
											<td><a
												href="Teacher_Information_Modify_deleteExpById?expId=${e.id}">删除</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<label class="lable-add" data-toggle="modal"
								data-target="#myModal">添加</label>
						</div>

					</div>
				</div>
			</div>


			<!-- 模态框，用于添加教师学历履历信息 -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h5 class="modal-title">添加教师学历/履历信息</h5>
						</div>
						<div class="modal-body">
							<form action="Teacher_Information_Modify_addExpByTchrNum"
								method="post" class="form-horizontal form-add"
								enctype="multipart/form-data"
								onsubmit="javascript:return isEmpty()">
								<div class="control-group">
									<label class="control-label">学校/单位：</label>
									<div class="controls">
										<input id="input-school" type="text" name="tchrSchool">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">学位/职称：</label>
									<div class="controls">
										<input id="input-position" type="text"
											name="tchrPosition">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">时间：</label>
									<div class="controls">
										<input id="input-time" type="text" name="tchrTime">
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
			<!-- 模态框，用于添加教师学历履历信息完 -->
			<!-- 模态框，用于修改密码 -->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true"
				style="display: none">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h5 class="modal-title">修改密码</h5>
						</div>
						<div class="modal-body">
							<form action="Teacher_Information_Modify_modifyPassword"
								method="post" class="form-horizontal form-add"
								enctype="multipart/form-data"
								onsubmit="javascript:return validate()">
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
			
			<!-- 模态框，用于修改密码完 -->

		</div>
	</div>
</body>
</html>