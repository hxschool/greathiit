<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>查课表</title>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style type="text/css">
.navbar {
	background-color: #444C82;
	border:0px;
}
.table{
-webkit-border-radius: 3px;
-moz-border-radius: 3px;
border-radius: 10px;
box-shadow: 5px 5px 4px #888888;
background-color:#fff;
}
.title{
	color:#3F5276;
	font-size: 14px;
	font-weight: bold;
}
.message{
	color:#A1A2A8;
	font-size: 12px;
}
.top{
height:61px;background-image : url(${ctxStatic}/images/course_header_bg.png);background-repeat : repeat-x;
border: none;
}
</style>
</head>
<body style="background-color: #D0D1D4">

	<div class="container-fluid">


		<nav class="navbar navbar-default" role="navigation" >
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#" style="color: #fff;">哈尔滨信息工程学院</a>
				</div>
				<div>
					<ul class="nav navbar-nav ">
						<li class="active"><select class="form-control"
							style="margin-top: 8px;" onchange="getMyCourseCard(this.options[this.options.selectedIndex].value)">
								
						</select></li>


					</ul>
					

				</div>
			</div>
		</nav>


		<table class="table">
			
			<thead>
				<tr class="top">
					<td colspan="3" >
						<div class="title">成绩单</div>
						<div class="message">姓名：${fns:getUser().name}  学号：${fns:getUser().no}</div>
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
					<td>${studentCourse.course.cursName }</td>
					<td>${studentCourse.evaValue }</td>
					<td>${studentCourse.credit }</td>
					<td>${studentCourse.point }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>


</body>
</html>