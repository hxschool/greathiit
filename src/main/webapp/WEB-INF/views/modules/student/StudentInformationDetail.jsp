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

</head>

<body>
	<div class="content">
		<div class="container ">
			<div class="span12 div-content-white-bgr">
				<!-- 学生基本信息 -->
				<div class="div-inf-bar">
					<label>基本信息</label>
				</div>
				<div class="div-inf-tbl">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td>学号</td>
								<td>${student.studentNumber}</td>
								<td>姓名</td>
								<td>${student.name}</td>
								<td class="img-stu" rowspan=4><img
									src="stuImg/${student.studentNumber}.jpg" /></td>
							</tr>
							<tr>
								<td>性别</td>
								<td>${fns:getDictLabel(student.gender, 'sex', '')}</td>
								<td>生日</td>
								<td><fmt:formatDate value="${student.birthday}" /></td>
							</tr>
							<tr>
								<td>籍贯</td>
								<td>${student.nativePlace}</td>
								<td>民族</td>
								<td> ${fns:getDictLabel(student.nation, 'nation', '')}</td>
							</tr>
							<tr>
								<td>院系</td>
								<td>
									哈尔滨信息工程学院
								</td>
								<td>班级</td>
								<td>${fn:substring(student.studentNumber, 0, 4)}</td>
							</tr>
							<tr>
								<td>入学日期</td>
								<td>
									<!-- zhaojunfei -->${student.startDate}
								</td>
								<td>学制</td>
								<td colspan=2>${student.studentLength}</td>
							</tr>
							<tr>
								<td>手机</td>
								<td>${student.phone}</td>
								<td>宿舍电话</td>
								<td colspan=2>宿舍电话</td>
							</tr>
							<tr>
								<td>邮箱</td>
								<td>${student.mail}</td>
								<td>通信地址</td>
								<td colspan=2>${student.address}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 学生基本信息完 -->
				<!-- 自我介绍 -->
				<div>
					<div class="div-inf-bar">
						<label>自我介绍</label>
					</div>
					<div class="row-fluid">
						<div class="span5 offset1 div-self-intr">
							<img src="${ctxStatic}/modules/img/vesion-Chinese.jpg">
							<div class="div-self-content">${student.selfIntroduce}</div>
						</div>
						<div class="span5 div-self-intr">
							<img src="${ctxStatic}/modules/img/vesion-English.jpg">
							<div class="div-self-content">${student.selfEngIntroduce}</div>
						</div>
					</div>
				</div>
				<!-- 自我介绍完 -->
			</div>
		</div>
	</div>

</body>
</html>
