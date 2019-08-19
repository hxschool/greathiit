<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学生信息管理</title>
<meta name="decorator" content="default" />

	<style>
		body{
			margin:0;
			padding:0;
		}
		td{
			width:160px;
			height:30px;
			font-size:20px;
		}		
	</style>
</head>
<body>
	<table border="1" cellpadding="12" cellspacing="0" align="center">
		<tr align=center>
			<td colspan="5">基本信息</td>
		</tr>
		<tr align=center>
			<td>姓名:</td><td>${student.name }</td>
			<td>性别:</td><td>${fns:getDictLabel(student.gender,'sex','')}</td>
			<td rowspan="5">	<img src="${student.face}" class="img-rounded"></td>
		</tr>
		<tr align=center>
			<td>毕业院校:</td><td>哈尔滨信息工程学院</td>
			<td>民族:</td><td>${fns:getDictLabel(student.nation,'nation','')}</td>
		</tr>
		<tr align=center>
			<td>最高学历:</td><td>${fns:getDictLabel(student.edu,'student_edu','')}</td>
			<td>政治面貌:</td><td>${fns:getDictLabel(student.political,'political','')}</td>
		</tr>
		<tr align=center>
			<td>专业:</td><td>${zhuanye }</td>
			<td>出生年月:</td><td><fmt:formatDate value="${student.birthday}" pattern="yyyy年MM月dd"/></td>
		</tr>
		<tr align=center>
			<td>籍贯:</td><td>${student.nativePlace}</td>
			<td>手机号码:</td><td>${student.phone}</td>
		</tr>
		<tr align=center>
			<td>通讯地址:</td><td colspan="4">${student.address}</td>
		</tr>
		<tr align=center>
			<td>电子信箱:</td><td colspan="4">${student.mail}</td>
		</tr>
		<tr align=center>
			<td colspan="5">成绩信息</td>
		</tr>
		<!-- <tr align=center>
			<td colspan="5" style="height:100px"></td>
		</tr>
		<tr align=center>
			<td colspan="5">专业课程</td>
		</tr>
		<tr>
			<td colspan="5" style="height:100px"></td>
		</tr>
		<tr align=center>
			<td colspan="5">主要技能</td>
		</tr>
		<tr>
			<td colspan="5" style="height:100px"></td>
		</tr>
		<tr align=center>
			<td colspan="5">项目经验</td>
		</tr> -->
		<tr>
			<td colspan="5" >
			<table class="table">
			
			<thead>
				<tr class="top">
					<td colspan="4" >
						<div class="title">成绩单</div>
						<div class="message">姓名：${student.name}  学号：${student.studentNumber}</div>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>课程</td>
					<td>综合成绩</td>
					<td>学分</td>
					<td>绩点</td>
				</tr>
				<c:forEach items="${studentCourses}" var="studentCourse">
				<tr>
					<td>${studentCourse.course.cursName}</td>
					<td>${studentCourse.evaValue }</td>
					<td>${studentCourse.credit }</td>
					<td>${studentCourse.point }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
			</td>
		</tr>
	</table>
</body>
</html>