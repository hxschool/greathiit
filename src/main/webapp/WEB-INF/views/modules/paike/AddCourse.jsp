<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>校历校准管理</title>
	<meta name="decorator" content="default"/>
	
	<style>

body
{
	font-family:'微软雅黑';
	background-color:#EBEAEB;
}

#contest{
	background-color:#FFF;
	/*position:absolute;
	top:7%;
	left:13%;
	margin-left:auto;
	margin-right:auto;*/
	position:relative;
	top:20px;
	margin:0 auto;
	width:1000px;
	height:800px;
	
	box-shadow:0px 0px 50px #000;
	border-radius:5px;
}

.psw
{
	width:160px;
	height:25px;
	background-color:#6e6e6e;
	border-radius:3px;
	border:0px;
	color:#f9f9f9;
	font-family:'微软雅黑';
	padding-left:5px;
}

.a
{
	display:block;

	border:1px solid;
	border-radius:3px;
	border-color:#09F;
	color:#000;
	padding:4px 10px;
	margin-top:2px;

	box-shadow:#6e6e6e 0 0 3px;
	text-decoration:none;
}
.a:hover
{
	background-color:#C6B19A;
	color:#FFF;
	box-shadow:#2d2d2d 0 0 3px;	
}

.prime_a:hover
{
	color:#0CF;
}

table
{
	border-collapse:collapse;
	font-size:15px;
	/*border-color:#000;*/
}

td{
	width:200px;
	height:50px;
	border:1px solid #000000;
}
#up{
	position:absolute;
	background-color:#ffffff;
	left:50%;
	top:300px;
	margin-top:-205px;
	margin-left:-200px;
	z-index:6;
	border-radius:6px;
	-webkit-box-shadow:0 3px 7px rgba(0, 0, 0, 0.3);
	box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
}

#mask
{
	background-color:#2d2d2d;
	opacity:0;
	filter:alpha(opacity=0);
	position:fixed;
	width:100%;
	height:100%;
	top:0px;
	left:0px;
	z-index:-5;
}

.course_text{
	text-align:center;
}

.admin_tips
{
	font-size:14px;
	line-height:30px;
	color:#999;
}
</style>
</head>
<body>

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
                   
                    <input type="text" name="url_time" value="" style="display:none">
                    <input type="text" name="year"  style="display:none;" value="${yearTerm}" class="input_text"/>
                    <input type="text" name="servers_time" style="display:none;" value="${mm}@${dd}">
                   
                 
                    <input type="text" name="year_rili" id="year_rili"
							style="display: none;" value="${courseCalendar.calendarYear}" />
						<input type="text" name="month_rili" id="month_rili"
							style="display: none;" value="${courseCalendar.calendarMonth}" />
						<input type="text" name="day_rili" id="day_rili"
							style="display: none;" value="${courseCalendar.calendarDay}" />
                  	<div id="top">
							第&nbsp;<select id="week_select" onchange="change_week()"
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
								class="address" onchange="change_address()"
								style="width: 200px;">
							</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						</div>
                    <p>
                    <table id="s_week" style="font-size:10px;height:550px;">
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
								for (int $row = 1; $row <= 5; $row++) {
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
                   
            
            <!-- div层[排一个课]-->
                    <div id="up" style="display:none; width:400px; height:600px;">
                    <form action="" name="form" style="margin-left:30px; margin-top:50px;">
                        <p>
                        <a onclick="putongke()" class="prime_a">普通课</a>&nbsp;<!-- |&nbsp;<a onclick="renxuanke()" class="prime_a">任选课</a> -->
                        <input  type="text" style="display:none" name="time" /> <!-- 存放时间地址字段-->
                        
						<p>
						
						<div id="element_id" style="width:340px;">
						<p>
							学院:&nbsp;&nbsp; <select name="school" id="school"
								class="school" style="width:200px;">
								<option value="" selected="selected">==请选择学院==</option>
							</select>
							
							<p>
								专业:&nbsp;&nbsp; <select name="major" id="major"
									class="major" style="width:200px;">
									<option value="" selected="selected">==请选择专业==</option>
								</select>
								
							<p>年级:&nbsp;&nbsp;
	                        <select name="grade"  id="grade" class="grade" style="width:200px;" >
	                          <option value="" selected="selected">==请选择年级==</option>
	                        </select>
	                      
							<p>
							<div style="width:370px;float:left;margin-bottom:10px">班级:&nbsp;&nbsp;
								 <div id="w_test"></div></div>
								
						</div>
						
						<div id="course_id" style="width:370px;float:left">
							<p>课程:&nbsp;&nbsp;
	                        <select name="course" id="course" class="course" style="width:280px">
	                        <option value="" selected="selected">==请选择课程==</option>
	                        </select>
                        </div>
                       
                        <p>
                        	 周期:&nbsp;&nbsp;<select name="w" id="select_id" style="width:280px"></select>
                        </p>
                         <p>
                     		   备注:&nbsp;&nbsp;<input type="text" name="tips"/>
                        <p>
                        <input  name="add" type="button" value="添加" onclick="resure()" class="button" />&nbsp;&nbsp;&nbsp;&nbsp;
                        <input  name="over" type="button" value="返回" onclick="cancel()"  class="button"/>
                    </form>
            
            
            	</div>
    
				<div id="mask"></div>
				
					
				
			</div>
		</div>
	</div>


<script>
if((screen.width <= 1024) && (screen.height <= 768))
{
	//alert("哈哈");
	document.getElementById("contest").style.left="0";
}
var time_array = new Array();
//设置下拉列表默认值函数
function select_ini(select_name,select_value)
{
	var s = document.getElementById(select_name);  
    var ops = s.options;  
    for(var i=0;i<ops.length; i++)
	{  
        var tempValue = ops[i].value;  
        if(tempValue == select_value)  
        {  
            ops[i].selected = true;  
        }  
    }  
}
//页面加载完成后执行change()填表格操作
$(document).ready(function() 
{
	//设置默认值为信息学院
	select_ini("h_school","04");
	var url_time = document.form0.url_time.value;
	if(url_time.length=="12")
	{
		select_ini("week_select",url_time.substr(10,2))
		select_ini("h_school",url_time.substr(5,2));
		select_ini("address",url_time.substr(7,3));
		chuancan(url_time);
	}
	else
		change() 
});

function rili(year,month,day)
{
	flag = 0;
	flag_year = 0;
	if((year%4==0 && year%100!=0) || year%400==0)
	{
		flag = 1;
	}
	else
		flag = 0;
	for(i=1;i<=20;i++)
	{
		time_array[i] = new Array();
		for(j = 1;j<=7;j++)
		{	
			if(month==13)
			{
				month=1;
				flag_year = 1;
			}
			time_array[i][j] = month+"月"+day+"日";
			if((month==1 || month==3 ||month==5 ||month==7 ||month==8 ||month==10 ||month==12) && day==31)
			{
				day=1;
				month++;
			}
			else if(day==28 && month==2 && flag == 0)
			{
				day=1;
				month++;
			}
			else if(day==29 && month==2 && flag == 1)
			{
				day=1;
				month++;
			}
			else if((month==4 ||month==6 ||month==9 ||month==11) &&day==30)
			{
				day=1;
				month++;
			}
			else
				day++;
		}
	}
	return flag_year;
}
//机房下拉列表改变时调用函数
function change_address()
{
	change();
}
//第几周下拉列表改变时调用函数
function change_week()
{
	change();
}
//获取当前表单数据
function change()
{
	
	var year = document.form0.year.value;//获取年份

	var h_school = $("#h_school").children('option:selected').val();
	var address = $("#address").children('option:selected').val();
	//alert(address);
	var temp = document.getElementById("week_select");//获取周次
	week= temp.options[temp.selectedIndex].value;
	time = year+''+h_school+''+address+''+week;
	//alert(time);
	
	//调用动态填写表格函数
	chuancan(time);
//	$('#up').fadeOut(500);
}
function rili_table()
{
	var table_temp = document.getElementById("s_week");//表格 
	var temp = document.getElementById("week_select");//获取周次
	var week_rili = temp.options[temp.selectedIndex].text;
	//alert(time);
	var year_rili = document.form0.year_rili.value;//年份
	var month_rili = document.form0.month_rili.value;//月
	var day_rili  = document.form0.day_rili.value;//日
	var flag_year = rili(year_rili,month_rili,day_rili);

	table_temp.rows[0].cells[1].innerHTML = "星期一 ["+time_array[week_rili][1]+"]";
	table_temp.rows[0].cells[2].innerHTML = "星期二 ["+time_array[week_rili][2]+"]";
	table_temp.rows[0].cells[3].innerHTML = "星期三 ["+time_array[week_rili][3]+"]";
	table_temp.rows[0].cells[4].innerHTML = "星期四 ["+time_array[week_rili][4]+"]";
	table_temp.rows[0].cells[5].innerHTML = "星期五 ["+time_array[week_rili][5]+"]";
	table_temp.rows[0].cells[6].innerHTML = "星期六 ["+time_array[week_rili][6]+"]";
	table_temp.rows[0].cells[7].innerHTML = "星期日 ["+time_array[week_rili][7]+"]";
}
function time_limit(xingqi)
{	
	var t = document.getElementById("week_select");//获取周次
	var zhou = t.options[t.selectedIndex].text;
	var servers_time = document.form0.servers_time.value.split("@");
	var local_time=time_array[zhou][xingqi];

	local_time=local_time.replace("月","日");
	local_time=local_time.split("日");
	var local_mon=parseInt(local_time[0]);
	var servers_mon=parseInt(servers_time[0]);
	var local_day=parseInt(local_time[1]);
	var servers_day=parseInt(servers_time[1]);
        
//        console.log(servers_mon);
//        console.log(local_mon);
//        console.log(servers_day);
//        console.log(local_day);
        
        
	if(servers_mon<local_mon)
	{
		//alert(servers_mon+"<"+local_mon);
		return 1;
	}
	else if(servers_mon==local_mon && servers_day<=local_day)
	{
		return 1;
	}
        else if((servers_mon >= 8 && servers_mon <= 12) && local_mon == 1)
        {
            return 1;
        }
	else
	{	return 0;}
}
</script>

<script>
//动态填写表格函数,参数为字段前12位
function chuancan(selected)
{
	rili_table();//调用日历设置函数
	//selected为前12位，即没有节次和第几周
	//JQ的ajax返回id为selected的数据
	$.ajax({
   		type: "POST",
  		url: "ajaxChangeTable",
  	 	data: "time_add="+selected,
   		success: function(msg)
		{
			var temp = document.getElementById("s_week");
			var change,cnt=0;
			change=msg.split("@");//分割返回数据
			//alert(msg);
			if(msg  == '')
			{
				for(i=1;i<=5;i++)
					for(j=1;j<=7;j++)
						temp.rows[i].cells[j].innerHTML="";
			}
			else
			{
				for(i=1;i<=5;i++)
				{
					for(j=1;j<=7;j++)
					{
					
						lock = change[cnt].substr(0,1);
						change[cnt] = change[cnt].substr(1);
						tips = change[cnt].split("备注:");
						//alert(lock);
						if(lock=='2')
						{
							
							if(change[cnt].indexOf("删除")!=-1)
							{
								tt = change[cnt].replace("删除","");
								temp.rows[i].cells[j].innerHTML=tt+"<div class=\"course_text\"><a style = \"color:red;\" onclick=\"deleted("+selected+","+i+","+j+")\" class=\"prime_a\">删除</a></div>";
							}
							else 
								temp.rows[i].cells[j].innerHTML=change[cnt];
						}
						else if(lock=='0')
						{
							temp.rows[i].cells[j].innerHTML="<div class=\"course_text\">管理员已加锁<p>备注:"+tips[1]+"</div>";
						}
						else
						{
							var temp = document.getElementById("s_week");
							temp.rows[i].cells[j].innerHTML="<div class=\"course_text\"><a onclick=\"paike("+selected+","+i+","+j+")\"  class=\"btn btn-mini btn-primary\" >点此排课</a></div>";
						}
						cnt++;
					}
				}
			}
   		}
	   }); 
}
//获取点击坐标 显示div隐藏排课层
function paike(time,row,cell)
{
	if(time_limit(cell)==0)
	{
		alert("日期已过，不允许排课");
	}
	else
	{
		document.form.add.value="添加"
		var temp = document.getElementById("s_week");
		time_add = time+''+row+''+cell;
		document.form.time.value = time_add;//给存储时间地址ID赋值
		
		  var optionString = "";
		 
		  var  week_select = $("#week_select").children('option:selected').val();
		  
           for(var i=week_select; i<=20; i++){ //遍历，动态赋值
        	   if(i<=9){
        		   if(i==week_select){
        			   i = week_select;
        		   }else{
        			   i = "0"+i;
        		   }
        		   optionString +="<option  value=\""+i+"\"";  
                   optionString += ">"+i+"</option>";  //动态添加数据  
        	   }else{
        		   optionString +="<option  value=\""+i+"\"";  
                   optionString += ">"+i+"</option>";  //动态添加数据  
        	   }
               
           }   
       		$("#select_id").append(optionString);
          
		
		
			$('#mask').css({'zIndex':'5'});
			$('#mask').animate({'opacity':'0.5'},200);
		
		$('#up').fadeIn(200);
	}
}

//删除排课
function deleted(time,row,cell)
{
	
	if(time_limit(cell)==0)
	{
		alert("对不起,日期已过,不允许删除");
	}
	else
	{
		time_add = time+''+row+''+cell;
		var conf = confirm("是否删除此排课");
		if(conf)
		{
			$.ajax({
			type: "POST",
			url: "ajaxDeleteCourse",
			data: "time_add="+time_add,
			success: function(msg)
			{
				//alert(msg);
				if(msg=='1')
				{
					
	//				$('#up').fadeOut(100);
					chuancan(time_add.substr(0,12));
					
				}
				else
				{
					alert("删除失败");
	//				$('#up').fadeOut(100);
					chuancan(time_add.substr(0,12));
				}
			}
		   }); 
		}
	}
}
//普通课
var renxuanke_or_putongke=1;
function putongke()
{
	renxuanke_or_putongke=1;
	select_ini("school","04");//设置学院默认值为信息学院
	document.form.school.disabled="";
	document.form.major.disabled="";
	document.form.grade.disabled="";
	document.form.w_class.disabled="";
}
//任选课
function renxuanke()
{
	renxuanke_or_putongke=0;
	select_ini("school","");//设置学院默认值为空即｛==请选择学院==｝
	select_ini("major","");
	select_ini("grade","");
	select_ini("w_class",""); 
	//禁用部分下拉列表
	document.form.school.disabled="false";
	document.form.major.disabled="false";
	document.form.grade.disabled="false";
	document.form.w_class.disabled="false";
}
//确定按钮 用来加一门课
function resure()
{

	$("#address").children('option:selected').val();
	
	var grade_id = $("#grade").children('option:selected').val();
	var school_id = $("#school").children('option:selected').val();
	var major_id = $("#major").children('option:selected').val();
	//var class_id = $("#w_class").children('option:selected').val();
	
	var dropIds = new Array();
	$("[name='classNumber']").each(function(){
		if($(this).is(':checked')){
			dropIds.push($(this).val());
		}
	})
	//var student_id = grade_id+''+school_id+''+major_id+''+class_id;
	var student_id = dropIds;
	
	var course_id =  $("#course").children('option:selected').val();
	tips = document.form.tips.value;
	var w = document.form.w.value;
	time_add = document.form.time.value;
	
	if(school_id=="" && renxuanke_or_putongke)
	{
		alert("您需要选择学院");
	}
	else if(major_id=="" && renxuanke_or_putongke)
	{
		alert("您需要选择专业");
	}
	else if(grade_id=="" && renxuanke_or_putongke)
	{
		alert("您需要选择年级");
	}
	else if(student_id=="" && renxuanke_or_putongke)
	{
		alert("您需要选择班级,至少选择一个");
	}
	else if(course_id=="")
	{
		alert("您需要选择课程");
	}
	else
	{	
/****************************
*****************************/
	//通过ajax给数据库添加一个课程安排
	$.ajax({
   		type: "POST",
  		url: "ajaxAddCourse",
  	 	data: "time_add="+time_add+"&course_id="+course_id+"&student_id="+student_id+"&tips="+tips+"&w="+w,
   		success: function(msg)
		{
			//alert(msg);
			if(msg=='1')
			{
				alert("添加成功");
				document.form.add.value="继续添加"
				chuancan(time_add.substr(0,12));
			}
			else if(msg=='2')
			{
				alert("系统检测到该班在机房有课,添加失败");
				$('#mask').animate({'opacity':'0'},function(){$('#mask').css({'zIndex':'-5'});});
				$('#up').fadeOut(100);
				chuancan(time_add.substr(0,12));
			}
			else
			{
				alert("添加失败");
				$('#mask').animate({'opacity':'0'},function(){$('#mask').css({'zIndex':'-5'});});
				$('#up').fadeOut(100);
				chuancan(time_add.substr(0,12));
			}
   		}
	   }); 
	//alert(student_id+''+time_add+course_id);
	}
}
//取消按钮，返回并更新页面
function cancel()
{
	$('#mask').animate({'opacity':'0'},function(){$('#mask').css({'zIndex':'-5'});});
	$('#up').fadeOut(500);
	time_add = document.form.time.value;
	chuancan(time_add.substr(0,12));
}
function zhuxiao()
{
	x = "000";
	$.ajax({
   		type: "POST",
  		url: "logout.ajax.php",
		data:"x="+x,
   		success: function(msg)
		{
			window.top.location.href = "../index.php";
   		}
	   }); 
}


function select_ini(select_name,select_value)
{
	var s = document.getElementById(select_name);  
    var ops = s.options;  
    for(var i=0;i<ops.length; i++)
	{  
        var tempValue = ops[i].value;  
        if(tempValue == select_value)  
        {  
            ops[i].selected = true;  
        }  
    }  
}
$(document).ready(function()
		{
			$('#top').cxSelect({ 
				  url: '${ctx}/school/schoolRoot/treeLink',
				  selects: ['h_school', 'address'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
			change();
			
			$('#element_id').cxSelect({ 
				  url: '${ctx}/sys/office/treeClassLink',
				  selects: ['school', 'major', 'grade'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
			
			$('#grade').change(function(){
				
				var parnetId = $("#major").children('option:selected').val();
				var grade = $("#grade").children('option:selected').val();
				 console.log(parnetId);
				 console.log(grade);
				 $.ajax({
					  url: '${ctx}/sys/office/ajaxClass',
			          async: false,
			          data: {
			        	  parnetId: parnetId,
			        	  grade: grade
			          },
			          success: function( data ) {
			        	  $("#w_test").empty();
			        	  if(data.length==0){
			        		//  $("#dormMessage").css("color","red");
		                	//  $("#dormMessage").html("当前寝室没有入住任何学员");
			        	  }
			        	  
			        	  for(var i=0 ;i<data.length;i++){
			        		  $("#w_test").append("<div style='width:120px;float:left;'> <input type='checkbox' class='classNumber' value='"+data[i].id+"' name='classNumber'/>"+data[i].name +"</div>");
			           	  }
			          }
			        });
			});
			
			$('#course_id').cxSelect({ 
				  url: '${ctx}/course/paike/ajaxAllCourse',
				  selects: ['course'], 
				  jsonName: 'name',
				  jsonValue: 'value'
				}); 
		   //document.form0.h_school.options[04].selected = true; 
		});
</script>



</body>
</html>