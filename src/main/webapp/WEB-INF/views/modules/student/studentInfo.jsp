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
			width:180px;
			height:30px;
			font-size:20px;
		}		
		table.gridtable {
			font-family: verdana,arial,sans-serif;
			font-size:11px;
			color:#333333;
			border-width: 1px;
			border-color: #666666;
			border-collapse: collapse;
		}
		table.gridtable th {
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #dedede;
		}
		table.gridtable td {
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #ffffff;
		}
	</style>
</head>
<body>
	<table border="1" cellpadding="12" cellspacing="0" align="center">
		<tr align=center>
			<td colspan="5">基本信息</td>
		</tr>
		<tr align=center>
			<td>姓名:</td><td colspan="3">${student.name }</td>
			
			<td rowspan="5">	<img src="${student.face}" class="img-rounded"></td>
		</tr>
		<tr align=center>
			<td>性别:</td><td>${fns:getDictLabel(student.gender,'sex','')}</td>
			<td>民族:</td><td>${fns:getDictLabel(student.nation,'nation','')}</td>
		</tr>
		<tr align=center>
			<td>最高学历:</td><td>${fns:getDictLabel(student.edu,'student_edu','')}</td>
			<td>政治面貌:</td><td>${fns:getDictLabel(student.political,'political','')}</td>
		</tr>
		<tr align=center>
			
			<td >出生年月:</td><td colspan="3"><fmt:formatDate value="${student.birthday}" pattern="yyyy年MM月dd"/></td>
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
			<td colspan="5">学籍信息</td>
		</tr>
		<tr align=center>
			<td>学校名称:</td><td>哈尔滨信息工程学院</td>
			<td>学籍状态:</td><td colspan="2">${fns:getDictLabel(student.status,'student_status','')}</td>
		</tr>
		
		<tr align=center>
			<td>专业:</td><td>${student.clazz.parent.name }</td>
			<td>生源省市:</td><td colspan="2">${fns:getProvinceByIdCard(student.idCard)}</td>
		</tr>
		<tr align=center>
			<td>层次:</td><td>${fns:getDictLabel(student.edu,'student_edu','')}</td>
			<td>学历类别:</td><td colspan="2">普通高等教育</td>
		</tr>
		<tr align=center>
			<td>学制:</td><td>${fns:getDictLabel(student.studentLength,'student_school_system','')}</td>
			<td>学习形式:</td><td colspan="2">普通全日制</td>
		</tr>
		<!-- <tr align=center>
			<td>分校:</td><td></td>
			<td>当前所以在级:</td><td colspan="2">普通全日制</td>
		</tr> -->
		<tr align=center>
			<td>系:</td><td>${student.clazz.parent.parent.name }</td>
			<td>入学日期:</td><td colspan="2">${student.startDate }</td>
		</tr>
		<tr align=center>
			<td>班号:</td><td>${student.clazz.name }
			</td>
			<td>预计毕业日期:</td><td colspan="2">${student.overDate }</td>
		</tr>
		<tr align=center>
			<td>学号:</td>
			<td colspan="4">${student.studentNumber }</td>
		</tr>


		
		<tr align=center>
			<td colspan="5">学籍注册日志</td>
		</tr>

		<tr>
			<td colspan="5">

				<table class="gridtable" style="width:100%">


						<tr>
							<th>操作人</th>
							<th>操作类型</th>
							<th>创建时间</th>
							<th>过程描述</th>
						</tr>
						<c:forEach items="${studentUcList}" var="studentUcList">
							<tr>
								<td>${studentUcList.createBy}</td>
								<td>${fns:getDictLabel(studentUcList.status,'student_uc_status','')}</td>
								<td><fmt:formatDate value="${studentUcList.createDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${studentUcList.description }</td>
							</tr>
						</c:forEach>
					
				</table>
			</td>
		</tr>


		<tr align=center>
			<td colspan="5">学籍注册日志</td>
		</tr>
		<tr>
			<td colspan="5">

				<table class="gridtable" style="width:100%">
						<tr>
							<th>操作人</th>
							<th>操作类型</th>
							<th>创建时间</th>
							<th>过程描述</th>
						</tr>
						<c:forEach items="${studentList}" var="studentUcList">
							<tr>
								<td>${studentUcList.createBy}</td>
								<td>${fns:getDictLabel(studentUcList.status,'student_status','')}</td>
								<td><fmt:formatDate value="${studentUcList.createDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${studentUcList.description }</td>
							</tr>
						</c:forEach>
				</table>
			</td>
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
		</tr> 
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
		
		-->
	</table>
</body>
</html>