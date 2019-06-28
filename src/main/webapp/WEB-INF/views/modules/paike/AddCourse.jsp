<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>排课管理</title>
<meta name="decorator" content="default" />

<style>
body {
	font-family: '微软雅黑';
	background-color: #EBEAEB;
}

.psw {
	width: 160px;
	height: 25px;
	background-color: #6e6e6e;
	border-radius: 3px;
	border: 0px;
	color: #f9f9f9;
	font-family: '微软雅黑';
	padding-left: 5px;
}

.a {
	display: block;
	border: 1px solid;
	border-radius: 3px;
	border-color: #09F;
	color: #000;
	padding: 4px 10px;
	margin-top: 2px;
	box-shadow: #6e6e6e 0 0 3px;
	text-decoration: none;
}

.a:hover {
	background-color: #C6B19A;
	color: #FFF;
	box-shadow: #2d2d2d 0 0 3px;
}

.prime_a:hover {
	color: #0CF;
}

table {
	border-collapse: collapse;
	font-size: 15px;
	/*border-color:#000;*/
}

td {
	width: 200px;
	height: 50px;
	border: 1px solid #000000;
}

#up {
	position: absolute;
	background-color: #ffffff;
	left: 50%;
	top: 300px;
	margin-top: -205px;
	margin-left: -200px;
	z-index: 6;
	border-radius: 6px;
	-webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
	box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
}

#mask {
	background-color: #2d2d2d;
	opacity: 0;
	filter: alpha(opacity = 0);
	position: fixed;
	width: 100%;
	height: 100%;
	top: 0px;
	left: 0px;
	z-index: -5;
}

.course_text {
	text-align: center;
}

.admin_tips {
	font-size: 14px;
	line-height: 30px;
	color: #999;
}

.container {
	width: 90%;
}

.span12 {
	width: 100%;
}
</style>
<link href="${ctxStatic}/admin/css/table.css" type="text/css"
	rel="stylesheet" />
</head>
<body>

	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="span12">

				<fmt:formatDate value="<%=new java.util.Date()%>" pattern="MM"
					var="mm" />
				<fmt:formatDate value="<%=new java.util.Date()%>" pattern="dd"
					var="dd" />

				<form name="form0">
					<input type="text" name="year_rili" id="year_rili"
						style="display: none;" value="${courseCalendar.calendarYear}" />
					<input type="text" name="month_rili" id="month_rili"
						style="display: none;" value="${courseCalendar.calendarMonth}" />
					<input type="text" name="day_rili" id="day_rili"
						style="display: none;" value="${courseCalendar.calendarDay}" />
					<div id="top">
						<input type="hidden" name="url_time" value=""> <input
							type="hidden" name="servers_time" value="${mm}@${dd}">
						学期: <select name="year" id="termYear" style="width: 200px;">
							<c:forEach items="${fns:termYear()}" var="termYear">
								<option value="${termYear.key}"
									<c:if test="${termYear.key==yearTerm}">selected</c:if>>${termYear.key}</option>
							</c:forEach>
						</select> 第&nbsp;<select id="week_select" onchange="change_week()"
							style="width: 100px;">
							<option value="01" selected>1</option>
							<%
								for (int $i = 2; $i <= 9; $i++)
									out.println("<option value=\"" + '0' + $i + "\">" + $i + "</option>");
								for (int $i = 10; $i <= 20; $i++)
									out.println("<option value=\"" + $i + "\">" + $i + "</option>");
							%>
						</select>&nbsp;周 &nbsp;&nbsp;&nbsp;&nbsp; 学院:&nbsp;&nbsp;<select
							name="h_school" id="h_school" class="h_school"
							onchange="change()" style="width: 100px;">

						</select> &nbsp;&nbsp;&nbsp;&nbsp;教室:&nbsp;&nbsp; <select id="address"
							class="address" onchange="change_address()" style="width: 200px;">
						</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<p>
					<table id="s_week" style="font-size: 10px; height: 550px;">
						<tr align="center" height="50px;">
							<td></td>
							<td>星期一</td>
							<td>星期二</td>
							<td>星期三</td>
							<td>星期四</td>
							<td>星期五</td>
							<td>星期六</td>
							<td>星期日</td>
						</tr>

						<%
							//生成空表格
							int i = 1;
							int j = 2;
							for (int $row = 1; $row <= 6; $row++) {
								out.println("<tr height=\"100px;\">");
								out.println("<td align=\"center\">" + i + "-" + j + "节</td>");
								i += 2;
								j += 2;
								for (int $cell = 1; $cell <= 7; $cell++) {
									out.println("<td></td>");
								}
								out.println("</tr>");
							}
						%>
					</table>
				</form>


				<div id="mask"></div>



			</div>
		</div>
	</div>

	<div id="up" style="display: none;">
		<form action="" method="post" class="form-horizontal smart-green" id="corseForm">
			<h1>
				排课操作<span id="title_message"></span>
			</h1>
			<div class="control-group">
			 <div style="text-align:center"> <a onclick="putongke()"
				class="button button-primary button-small">普通课</a>&nbsp;&nbsp;<a
				onclick="renxuanke()" class="button  button-caution button-small">公选课</a>
				&nbsp;&nbsp;<a
				onclick="quick()" class="button  button-caution button-small">快速排课</a>
				<input type="hidden" name="time" id="time" />
				</div>

			</div>
			<div id="putongke">
				<div class="control-group">
					<label class="control-label">学院 :</label>
					<div class="controls">
						<select name="school" id="school" class="school"
							style="width: 280px;">
							<option value="" selected="selected">==请选择学院==</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">专业 :</label>
					<div class="controls">
						<select name="major" id="major" class="major"
							style="width: 280px;">
							<option value="" selected="selected">==请选择专业==</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">年级 :</label>
					<div class="controls">
						<select name="grade" id="grade" class="grade"
							style="width: 280px;">
							<option value="" selected="selected">==请选择年级==</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">班级:</label>
					<div class="controls" id="ptk_course_class"></div>
				</div>

			
					<div class="control-group">
						<label class="control-label">课程:</label>
						<div class="controls">
							<select name="course" id="course" class="course"
								style="width: 280px">
								<option value="" selected="selected">==请选择课程==</option>
							</select>
						</div>
					</div>
				

				<div class="control-group">
					<label class="control-label">周期:</label>
					<div class="controls">
						<select name="w" id="select_id" style="width: 280px"></select>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">备注:</label>
					<div class="controls">
						<textarea id="tips" name="tips" placeholder="请输入备注信息"></textarea>
					</div>
				</div>
				<div class="form-actions">
					<input name="add" type="button" value="添加" onclick="resure()"
						class="button" />&nbsp;&nbsp;&nbsp;&nbsp; <input name="over"
						type="button" value="返回" onclick="cancel()" class="button" />
				</div>
			</div>


			<div id="renxuanke">
				<div class="control-group">
					<label class="control-label">课程:</label>
					<div class="controls">
						<select name="course" id="renxuanke_course" class="course"
							style="width: 280px" onchange="">
							<option value="" selected="selected">==请选择课程==</option>
						</select>
					</div>

				</div>
				<div class="control-group">
					<label class="control-label"> 班级:</label>
					<div class="controls" id="rxk_course_class"></div>
				</div>

				<div class="control-group">
					<label class="control-label">周期:</label>
					<div class="controls">
						<select name="renxuanke_w" id="renxuanke_select_id"
							style="width: 280px"></select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注:</label>
					<div class="controls">
						<textarea id="renxuanke_tips" name="renxuanke_tips"
							placeholder="请输入备注信息"></textarea>
					</div>
				</div>
					<div class="form-actions"> <input name="add" type="button"
					value="添加" onclick="renxuanke_resure()" class="button" /> <input
					name="over" type="button" value="返回" onclick="cancel()"
					class="button" />
				</div>
			</div>

			<div id="quick">
				<div class="control-group">
					<label class="control-label">课程:</label>
					<div class="controls">
						<select name="course" id="quick_course" class="course"
							style="width: 280px" onchange="">
							<option value="" selected="selected">==请选择课程==</option>
						</select>
					</div>

				</div>


				<div class="control-group">
					<label class="control-label">周期:</label>
					<div class="controls">
						<select name="quick_w" id="quick_select_id"
							style="width: 280px"></select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注:</label>
					<div class="controls">
						<textarea id="quick_tips" name="quick_tips"
							placeholder="请输入备注信息"></textarea>
					</div>
				</div>
					<div class="form-actions"> <input name="add" type="button"
					value="添加" onclick="quick_resure()" class="button" /> <input
					name="over" type="button" value="返回" onclick="cancel()"
					class="button" />
				</div>
			</div>



		</form>
	</div>



	<script>
		var time_array = new Array();
		//设置下拉列表默认值函数
		function select_ini(select_name, select_value) {
			var s = document.getElementById(select_name);
			var ops = s.options;
			for (var i = 0; i < ops.length; i++) {
				var tempValue = ops[i].value;
				if (tempValue == select_value) {
					ops[i].selected = true;
				}
			}
		}
		//页面加载完成后执行change()填表格操作
		$(document).ready(function() {
			$("#renxuanke").hide();
			$("#quick").hide();
			//设置默认值为信息学院
			select_ini("h_school", "04");
			var url_time = document.form0.url_time.value;
			if (url_time.length == "19") {
				select_ini("week_select", url_time.substr(10, 2))
				select_ini("h_school", url_time.substr(5, 2));
				select_ini("address", url_time.substr(7, 3));
				chuancan(url_time);
			} else
				change()
		});

		function rili(year, month, day) {
			flag = 0;
			flag_year = 0;
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				flag = 1;
			} else
				flag = 0;
			for (i = 1; i <= 20; i++) {
				time_array[i] = new Array();
				for (j = 1; j <= 7; j++) {
					if (month == 13) {
						month = 1;
						flag_year = 1;
					}
					time_array[i][j] = month + "月" + day + "日";
					if ((month == 1 || month == 3 || month == 5 || month == 7
							|| month == 8 || month == 10 || month == 12)
							&& day == 31) {
						day = 1;
						month++;
					} else if (day == 28 && month == 2 && flag == 0) {
						day = 1;
						month++;
					} else if (day == 29 && month == 2 && flag == 1) {
						day = 1;
						month++;
					} else if ((month == 4 || month == 6 || month == 9 || month == 11)
							&& day == 30) {
						day = 1;
						month++;
					} else
						day++;
				}
			}
			return flag_year;
		}
		//机房下拉列表改变时调用函数
		function change_address() {
			change();
		}
		//第几周下拉列表改变时调用函数
		function change_week() {
			change();
		}
		//获取当前表单数据
		function change() {
			var year = document.form0.year.value;//获取年份

			var h_school = $("#h_school").children('option:selected').val();
			var address = $("#address").children('option:selected').val();
			var temp = document.getElementById("week_select");//获取周次
			week = temp.options[temp.selectedIndex].value;
			time = year + '' + h_school + '' + address + '' + week;
			if (h_school != null && address != null) {
				chuancan(time);
			}

		}
		function rili_table() {
			var table_temp = document.getElementById("s_week");//表格 
			var temp = document.getElementById("week_select");//获取周次
			var week_rili = temp.options[temp.selectedIndex].text;
			var year_rili = document.form0.year_rili.value;//年份
			var month_rili = document.form0.month_rili.value;//月
			var day_rili = document.form0.day_rili.value;//日
			var flag_year = rili(year_rili, month_rili, day_rili);

			table_temp.rows[0].cells[1].innerHTML = "星期一 ["
					+ time_array[week_rili][1] + "]";
			table_temp.rows[0].cells[2].innerHTML = "星期二 ["
					+ time_array[week_rili][2] + "]";
			table_temp.rows[0].cells[3].innerHTML = "星期三 ["
					+ time_array[week_rili][3] + "]";
			table_temp.rows[0].cells[4].innerHTML = "星期四 ["
					+ time_array[week_rili][4] + "]";
			table_temp.rows[0].cells[5].innerHTML = "星期五 ["
					+ time_array[week_rili][5] + "]";
			table_temp.rows[0].cells[6].innerHTML = "星期六 ["
					+ time_array[week_rili][6] + "]";
			table_temp.rows[0].cells[7].innerHTML = "星期日 ["
					+ time_array[week_rili][7] + "]";
		}
		function time_limit(xingqi) {
			var t = document.getElementById("week_select");//获取周次
			var zhou = t.options[t.selectedIndex].text;
			var servers_time = document.form0.servers_time.value.split("@");
			var local_time = time_array[zhou][xingqi];

			local_time = local_time.replace("月", "日");
			local_time = local_time.split("日");
			var local_mon = parseInt(local_time[0]);
			var servers_mon = parseInt(servers_time[0]);
			var local_day = parseInt(local_time[1]);
			var servers_day = parseInt(servers_time[1]);

			if (servers_mon < local_mon) {
				//alert(servers_mon+"<"+local_mon);
				return 1;
			} else if (servers_mon == local_mon && servers_day <= local_day) {
				return 1;
			} else if ((servers_mon >= 8 && servers_mon <= 12)
					&& local_mon == 1) {
				return 1;
			} else {
				return 0;
			}
		}

		function chuancan(selected) {
			debugger;
			rili_table();
			$
					.ajax({
						type : "POST",
						url : "ajaxChangeTable",
						data : "time_add=" + selected,
						success : function(msg) {
							var temp = document.getElementById("s_week");
							var change, cnt = 0;
							change = msg.split("@");//分割返回数据
							if (msg == '') {
								for (i = 1; i <= 6; i++)
									for (j = 1; j <= 7; j++)
										temp.rows[i].cells[j].innerHTML = "";
							} else {
								for (i = 1; i <= 6; i++) {
									for (j = 1; j <= 7; j++) {

										lock = change[cnt].substr(0, 1);
										change[cnt] = change[cnt].substr(1);
										tips = change[cnt].split("备注:");
										//alert(lock);
										if (lock == '2') {

											if (change[cnt].indexOf("删除") != -1) {
												tt = change[cnt].replace("删除",
														"");
												temp.rows[i].cells[j].innerHTML = tt
														+ "<div class=\"course_text\"><a style = \"color:red;\" onclick=\"deleted("
														+ selected
														+ ","
														+ i
														+ ","
														+ j
														+ ")\" class=\"prime_a\">删除</a></div>";
											} else
												temp.rows[i].cells[j].innerHTML = change[cnt];
										} else if (lock == '0') {
											temp.rows[i].cells[j].innerHTML = "<div class=\"course_text\">管理员已加锁<p style=\"margin:0px;width: 180px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;\">备注:"
													+ tips[1] + "</div>";
										} else {
											var temp = document
													.getElementById("s_week");
											temp.rows[i].cells[j].innerHTML = "<div class=\"course_text\"><a onclick=\"paike('"
													+ selected
													+ "',"
													+ i
													+ ","
													+ j
													+ ")\"  class=\"btn btn-mini btn-primary\" >点此排课</a></div>";
										}
										cnt++;
									}
								}
							}
						}
					});
		}
		//获取点击坐标 显示div隐藏排课层
		function paike(time, row, cell) {

			if (time_limit(cell) == 0) {
				alert("日期已过，不允许排课");
			} else {

				document.getElementById("corseForm").add.value = "添加"

				var temp = document.getElementById("s_week");
				time_add = time + '' + row + '' + cell;
				$("#time").val(time_add);//给存储时间地址ID赋值

				var optionString = "";

				var week_select = $("#week_select").children('option:selected')
						.val();
				$("#select_id").empty();
				$("#renxuanke_select_id").empty();
				for (var i = week_select; i <= 20; i++) { //遍历，动态赋值
					if (i <= 9) {
						if (i == week_select) {
							i = week_select;
						} else {
							i = "0" + i;
						}
						optionString += "<option  value=\""+i+"\"";  
                   optionString += ">"
								+ i + "</option>"; //动态添加数据  
					} else {
						optionString += "<option  value=\""+i+"\"";  
                   optionString += ">"
								+ i + "</option>"; //动态添加数据  
					}

				}
				$("#select_id").append(optionString);
				$("#renxuanke_select_id").append(optionString);

				$('#mask').css({
					'zIndex' : '5'
				});
				$('#mask').animate({
					'opacity' : '0.5'
				}, 200);

				$('#up').fadeIn(200);
			}
		}

		//删除排课
		function deleted(time, row, cell) {

			if (time_limit(cell) == 0) {
				alert("对不起,日期已过,不允许删除");
			} else {
				time_add = time + '' + row + '' + cell;
				var conf = confirm("是否删除此排课");
				if (conf) {
					$.ajax({
						type : "POST",
						url : "ajaxDeleteCourse",
						data : "time_add=" + time_add,
						success : function(msg) {
							//alert(msg);
							if (msg == '1') {

								//				$('#up').fadeOut(100);
								chuancan(time_add.substr(0, 19));

							} else {
								alert("删除失败");
								//				$('#up').fadeOut(100);
								chuancan(time_add.substr(0, 19));
							}
						}
					});
				}
			}
		}
		//普通课
		var renxuanke_or_putongke = 1;
		function putongke() {
			$("#putongke").show();
			$("#renxuanke").hide();
			$("#quick").hide();
			$("#title_message").html("普通科");
			renxuanke_or_putongke = 1;
			select_ini("school", "04");//设置学院默认值为信息学院
			document.getElementById("corseForm").school.disabled = "";
			document.getElementById("corseForm").major.disabled = "";
			document.getElementById("corseForm").grade.disabled = "";
			
			getCourse( $("#termYear").children('option:selected').val(),0);
		}
		//任选课
		function renxuanke() {
			$("#quick").hide();
			$("#putongke").hide();
			$("#renxuanke").show();
			$("#title_message").html("任选课");
			renxuanke_or_putongke = 0;
			select_ini("school", "");//设置学院默认值为空即｛==请选择学院==｝
			select_ini("major", "");
			select_ini("grade", "");
			//禁用部分下拉列表
			document.getElementById("corseForm").school.disabled = "false";
			document.getElementById("corseForm").major.disabled = "false";
			document.getElementById("corseForm").grade.disabled = "false";
			getCourse( $("#termYear").children('option:selected').val(),50);
		}
		
		function quick() {
			$("#quick").show();
			$("#putongke").hide();
			$("#renxuanke").hide();
			$("#title_message").html("QUICK");
			renxuanke_or_putongke = 0;
			select_ini("school", "");//设置学院默认值为空即｛==请选择学院==｝
			select_ini("major", "");
			select_ini("grade", "");
			//禁用部分下拉列表
			document.getElementById("corseForm").school.disabled = "false";
			document.getElementById("corseForm").major.disabled = "false";
			document.getElementById("corseForm").grade.disabled = "false";
			getCourse( $("#termYear").children('option:selected').val(),1);
		}
		//确定按钮 用来加一门课

		function renxuanke_resure() {
			var dropIds = new Array();
			$("[name='classNumber']").each(function() {
				if ($(this).is(':checked')) {
					dropIds.push($(this).val());
				}
			})
			var course_id = $("#renxuanke_course").children('option:selected')
					.val();
			var student_id = dropIds;
			tips = document.getElementById("corseForm").renxuanke_tips.value;
			var w = document.getElementById("corseForm").renxuanke_w.value;
			time_add = document.getElementById("corseForm").time.value;

			if (student_id == "") {
				alert("您需要选择班级,至少选择一个");
			} else if (course_id == "") {
				alert("您需要选择课程");
			} else {
				/****************************
				 *****************************/
				//通过ajax给数据库添加一个课程安排
				submitCourse(time_add, course_id, student_id, tips, w);
			}
		}
		
		function quick_resure() {
			
			var course_id = $("#quick_course").children('option:selected').val();
			tips = document.getElementById("corseForm").quick_tips.value;
			var w = document.getElementById("corseForm").quick_w.value;
			time_add = document.getElementById("corseForm").time.value;

			if (course_id == "") {
				alert("您需要选择课程");
			} else {
				submitCourse(time_add, course_id, student_id, tips, w);
			}
		}

		function resure() {

			$("#address").children('option:selected').val();

			var grade_id = $("#grade").children('option:selected').val();
			var school_id = $("#school").children('option:selected').val();
			var major_id = $("#major").children('option:selected').val();

			var dropIds = new Array();
			$("[name='classNumber']").each(function() {
				if ($(this).is(':checked')) {
					dropIds.push($(this).val());
				}
			})
			var student_id = dropIds;

			var course_id = $("#course").children('option:selected').val();
			var tips = document.getElementById("corseForm").tips.value;
			var w = document.getElementById("corseForm").w.value;
			var time_add = document.getElementById("corseForm").time.value;

			if (school_id == "") {
				alert("您需要选择学院");
			} else if (major_id == "") {
				alert("您需要选择专业");
			} else if (grade_id == "") {
				alert("您需要选择年级");
			} else if (student_id == "") {
				alert("您需要选择班级,至少选择一个");
			} else if (course_id == "") {
				alert("您需要选择课程");
			} else {
				/****************************
				 *****************************/
				//通过ajax给数据库添加一个课程安排
				submitCourse(time_add, course_id, student_id, tips, w);
			}
		}

		function submitCourse(time_add, course_id, student_id, tips, w) {
			$.ajax({
				type : "POST",
				url : "ajaxAddCourse",
				data : "time_add=" + time_add + "&course_id=" + course_id
						+ "&student_id=" + student_id + "&tips=" + tips + "&w="
						+ w,
				success : function(msg) {
					//alert(msg);
					if (msg == '1') {
						alert("添加成功");
						document.getElementById("corseForm").add.value = "继续添加"
						chuancan(time_add.substr(0, 19));
					} else if (msg == '2') {
						alert("系统检测到该班在机房有课,添加失败");
						$('#mask').animate({
							'opacity' : '0'
						}, function() {
							$('#mask').css({
								'zIndex' : '-5'
							});
						});
						$('#up').fadeOut(100);
						chuancan(time_add.substr(0, 19));
					} else {
						alert("添加失败");
						$('#mask').animate({
							'opacity' : '0'
						}, function() {
							$('#mask').css({
								'zIndex' : '-5'
							});
						});
						$('#up').fadeOut(100);
						chuancan(time_add.substr(0, 19));
					}
				}
			});
		}
		//取消按钮，返回并更新页面
		function cancel() {
			$('#mask').animate({
				'opacity' : '0'
			}, function() {
				$('#mask').css({
					'zIndex' : '-5'
				});
			});
			$('#up').fadeOut(500);
			time_add = $("#time").val();
			chuancan(time_add.substr(0, 19));
		}
		function zhuxiao() {
			x = "000";
			$.ajax({
				type : "POST",
				url : "logout.ajax.php",
				data : "x=" + x,
				success : function(msg) {
					window.top.location.href = "../index.php";
				}
			});
		}

		function select_ini(select_name, select_value) {
			var s = document.getElementById(select_name);
			var ops = s.options;
			for (var i = 0; i < ops.length; i++) {
				var tempValue = ops[i].value;
				if (tempValue == select_value) {
					ops[i].selected = true;
				}
			}
		}
		$(document)
				.ready(
						function() {
							$('#top').cxSelect({
								url : '${ctx}/school/schoolRoot/treeLink',
								selects : [ 'h_school', 'address' ],
								jsonName : 'name',
								jsonValue : 'value',
								jsonSub : 'sub'
							});
							change();

							$('#putongke').cxSelect({
								url : '${ctx}/sys/office/treeClassLink',
								selects : [ 'school', 'major', 'grade' ],
								jsonName : 'name',
								jsonValue : 'value',
								jsonSub : 'sub'
							});

							$('#renxuanke_course')
									.change(
											function() {
												var courseId = $("#renxuanke_course").children('option:selected').val();

												$.ajax({
															url : '${ctx}/course/paike/ajaxAllClassByCourseId',
															async : false,
															data : {
																courseId : courseId
															},
															success : function(
																	data) {
																$("#rxk_course_class").empty();
																if (data.length == 0) {
																	$("#rxk_course_class")
																			.append("<div style='width:100px;float:left;'> <input type='checkbox' class='classNumber' value='00000000' checked disabled name='classNumber'/>公共课</div>");
																}

																for (var i = 0; i < data.length; i++) {
																	var clazz = data[i].clazz;
																	$("#rxk_course_class")
																			.append(
																					"<div style='width:100px;float:left;'> <input type='checkbox' class='classNumber' value='"+clazz.id+"' name='classNumber'/>"
																							+ clazz.name
																							+ "</div>");
																}
															}
														});
											});

							$('#grade').change(function() {
								
												debugger;
												var parnetId =$("#major").children('option:selected').val();

												var grade = $("#grade").children('option:selected').val();
												$.ajax({
															url : '${ctx}/sys/office/ajaxClass',
															async : false,
															data : {
																parnetId : parnetId,
																grade : grade
															},
															success : function(data) {
																$("#ptk_course_class").empty();
																if (data.length == 0) {
																}

																for (var i = 0; i < data.length; i++) {
																	$("#ptk_course_class").append("<div style='width:100px;float:left;'> <input type='checkbox' class='classNumber' value='"+data[i].id+"' name='classNumber'/>"+ data[i].name+ "</div>");
																}
															}
														});
											});
							getCourse( $("#termYear").children('option:selected').val(),0);
							
						});
		
		
		function getCourse(termYear,cursProperty){
			debugger;
			$.ajax({
				url : '${ctx}/course/paike/ajaxAllCourse',
				async : false,
				data : {
					cursProperty :cursProperty,
					termYear :termYear
				},
				success : function(
						data) {
					$("#course").empty();
					$("#quick_course").empty();
					$("#renxuanke_course").empty();
					for (var i = 0; i < data.length; i++) {
						var item =data[i];
						if(cursProperty==0){
							$("#course").append("<option value="+item.value+">" + item.name+ "</option>");
						}else if(cursProperty==1){
							$("#quick_course").append("<option value="+item.value+">" + item.name+ "</option>");
						}else if(cursProperty==50){
							$("#renxuanke_course").append("<option value="+item.value+">" + item.name+ "</option>");
						}
					}
					
				}
			});
		}
		
	</script>



</body>
</html>