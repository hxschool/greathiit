<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程内容管理</title>
	<meta name="decorator" content="default"/>

	<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/teacher/teaching_management.css" />


<script type="text/javascript">
	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}

	//显示后将request里的Message清空，防止回退时重复显示。
	function check(form) {
		var filename = document.getElementById("filename").value;
		if (filename == "") {
			alert("请选择文件！");
			return false;
		}
		var index1 = filename.lastIndexOf(".");
		var index2 = filename.length;
		var postf = filename.substring(index1 + 1, index2);//后缀名  
		if (postf != "xls" && postf != "xlsx") {
			alert("您选择的文件格式不正确！");
			return false;
		}
		document.getElementById("fileName").value = filename;
		return true;
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
							<!-- 成绩评定与查询 -->
							<div class="div-tchr-curs">
								<div class="div-inf-bar">
									<label>成绩评定与查询</label>
								</div>
								<div class="div-inf-tbl">
									<div class="div-result-import">
										<form action="upload" method="post"
											enctype="multipart/form-data">
											<h5>
												${yearTerm}
											</h5>
											<div class="div-select">
												课程&nbsp;&nbsp;<select id="course" name="course" style="width:200px;">
													
													<c:forEach var="item" items="${courses}" varStatus="status">
													     <option value="${item.id}"> ${fns:abbr(item.cursName,22)}</option>
													</c:forEach>
														
												</select>
											</div>
											
											<div class="div-select">
												班级&nbsp;&nbsp;
												
												<select id="example-multiple-selected" name="clazz"  multiple="multiple" style="width:200px;">
													
												</select>
											</div>
											<a class="btn btn-file">选择文件<input type="file"
												name="file" id="filename"></a>
											<input name="uploadUrl" value="/excel" type="hidden"  />
											<input id="fileName" type="hidden" 
												name="fileName" />
											
											<input type="submit" value="上传成绩" class="btn"
												onclick="return check(this.form)">
												
										</form>
									</div>
								</div>

								<div class="div-inf-tbl">
									<div class="div-result-import">
										<form action="Teacher_Management_2_selectStuPer" method="post">
											<h5>
												${yearTerm}
											</h5>
											<div class="div-select">
												课程&nbsp;&nbsp;<select 
													name="stuCursLimit.cursName" style="width:200px;">
													<c:forEach var="item" items="${courses}" varStatus="status">
													     <option value="${item.id}">${fns:abbr(item.cursName,22)}</option>
													</c:forEach>
												</select>
											</div>
											<div class="div-select">
												班级&nbsp;&nbsp;<select id="class" name="stuCursLimit.claName" style="width:200px;">
													<option value="" selected="selected">全部</option>
													
												</select>
											</div>
											<input class="btn" type="submit" value="查询成绩">
											<a class="btn" >导出数据</a>
										</form>
									</div>
									
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th class="th1">学号</th>
												<th class="th2">姓名</th>
												<th>学分</th>
												<th>课堂表现</th>
												<!-- <th>平时作业</th>
												<th>实验成绩</th> -->
												<th>期中成绩</th>
												<th>期末成绩</th>
												<th class="th8">综合成绩</th>
												<th>绩点(综合-60)*0.1</th>
												
											</tr>
										</thead>
										<tbody>
											
											<c:forEach var="item" items="${studentCourses}" varStatus="status">
												<tr>
													<td>${item.studentNumber }</td>
													<td>${item.studentName }</td>
													<td>${item.credit }</td>
													<td>${item.classEvaValue }</td>
													<!-- <td>${item.workEvaValue }</td>
													<td>${item.expEvaValue }</td> -->
													<td>${item.midEvaValue }</td>
													<td>${item.finEvaValue }</td>
													<td>${item.evaValue }</td>
													<td>${item.point }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- 成绩评定与查询完 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<script type="text/javascript">

	$(document).ready(function() {
		
		$('#course').change(function() {
	
				$.ajax({
					url : '${ctx}/teacher/course/ajaxAllClass',
					async : false,
					data : {
						courseId : $(this).val()
					},
					success : function(data) {
						
						$("#example-multiple-selected").empty();
						var optionString = "";
						for(var i=0; i<data.length; i++){ //遍历，动态赋值
	                        optionString +="<option  value=\""+data[i]+"\"";  
	                        optionString += ">"+data[i]+"</option>";  //动态添加数据  
	                    }   
	                	$("#example-multiple-selected").append(optionString);
					}
				});
			});
	});
</script>
</body>
</html>
