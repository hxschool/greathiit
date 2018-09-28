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
.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
}
</style>

<script type="text/javascript">

$(document).ready(function(){
		getMyCourseCard();
	});
	function getMyCourseCard(weekNumber){
		for(var i=1;i<=5;i++){
			for(var j=1;j<=7;j++){
				$("#show_"+i+"_"+j).css("backgroundColor","");
				$("#show_"+i+"_"+j).html("");
			}
		}
		var colors = new Array();
		colors[0]="#FF7F50";
		colors[1]="#C6E2FF";
		colors[2]="#71C671";
		colors[3]="#CD3278";
		colors[4]="#E0EEE0";
		colors[5]="#9F79EE";
		colors[6]="#00EE76";
		$.post("Ajax_Student_Class_Schedule_Card",{"weekNumber":weekNumber}, function(data) {
			$.each(data, function(index,value){
				var timeAdd = value.timeAdd;
				var school = timeAdd.substring(5,7);
				var root = timeAdd.substring(7,10);
				
				var x = timeAdd.substring(12,13);
				var y = timeAdd.substring(13,14);
				var label = value.cursName + "<br>" + school +"教学楼" + root + "教室" ;
				$("#show_"+x+"_"+y).css("backgroundColor",colors[y-1]);
				$("#show_"+x+"_"+y).css("color","#fff");
				$("#show_"+x+"_"+y).html(label);
			})
		});
	}
</script>
</head>
<body>

	<div class="container-fluid">


		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">哈尔滨信息工程学院</a>
				</div>
				<div>
					<ul class="nav navbar-nav ">
						<li class="active"><select class="form-control"
							style="margin-top: 8px;" onchange="getMyCourseCard(this.options[this.options.selectedIndex].value)">
								<c:forEach items="${studentCourses}" var="w">
									<option value="${w}">第${w}周</option>
								</c:forEach>
						</select></li>


					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">设置</a></li>

					</ul>

				</div>
			</div>
		</nav>

		<table class="table table-bordered">

			<tr class="info">
				<td style="width: 40px;">课次</td>
				<c:forEach items="${xqs}" var="xq">
					<td
						<c:if test="${week == xq}"> style="background-color:#FAFAFA;" </c:if>>${xq }</td>
				</c:forEach>

			</tr>

			<tr>
				<td>1</td>
				<td rowspan="2" id="show_1_1">&nbsp;</td>
				<td rowspan="2" id="show_1_2">&nbsp;</td>
				<td rowspan="2" id="show_1_3">&nbsp;</td>
				<td rowspan="2" id="show_1_4">&nbsp;</td>
				<td rowspan="2" id="show_1_5">&nbsp;</td>
				<td rowspan="2" id="show_1_6">&nbsp;</td>
				<td rowspan="2" id="show_1_7">&nbsp;</td>
			</tr>
			<tr>
				<td>2</td>
			</tr>



			<tr>
				<td>3</td>
				<td rowspan="2" id="show_2_1">&nbsp;</td>
				<td rowspan="2" id="show_2_2">&nbsp;</td>
				<td rowspan="2" id="show_2_3">&nbsp;</td>
				<td rowspan="2" id="show_2_4">&nbsp;</td>
				<td rowspan="2" id="show_2_5">&nbsp;</td>
				<td rowspan="2" id="show_2_6">&nbsp;</td>
				<td rowspan="2" id="show_2_7">&nbsp;</td>
			</tr>
			<tr>
				<td>4</td>
			</tr>


			<tr>
				<td>5</td>
				<td rowspan="2" id="show_3_1">&nbsp;</td>
				<td rowspan="2" id="show_3_2">&nbsp;</td>
				<td rowspan="2" id="show_3_3">&nbsp;</td>
				<td rowspan="2" id="show_3_4">&nbsp;</td>
				<td rowspan="2" id="show_3_5">&nbsp;</td>
				<td rowspan="2" id="show_3_6">&nbsp;</td>
				<td rowspan="2" id="show_3_7">&nbsp;</td>
			</tr>
			<tr>
				<td>6</td>
			</tr>



			<tr>
				<td>7</td>
				<td rowspan="2" id="show_4_1">&nbsp;</td>
				<td rowspan="2" id="show_4_2">&nbsp;</td>
				<td rowspan="2" id="show_4_3">&nbsp;</td>
				<td rowspan="2" id="show_4_4">&nbsp;</td>
				<td rowspan="2" id="show_4_5">&nbsp;</td>
				<td rowspan="2" id="show_4_6">&nbsp;</td>
				<td rowspan="2" id="show_4_7">&nbsp;</td>
			</tr>
			<tr>
				<td>8</td>
			</tr>


			<tr>
				<td>9</td>
				<td rowspan="2" id="show_5_1">&nbsp;</td>
				<td rowspan="2" id="show_5_2">&nbsp;</td>
				<td rowspan="2" id="show_5_3">&nbsp;</td>
				<td rowspan="2" id="show_5_4">&nbsp;</td>
				<td rowspan="2" id="show_5_5">&nbsp;</td>
				<td rowspan="2" id="show_5_6">&nbsp;</td>
				<td rowspan="2" id="show_5_7">&nbsp;</td>
			</tr>
			<tr>
				<td>10</td>
			</tr>
		</table>
	</div>


</body>
</html>