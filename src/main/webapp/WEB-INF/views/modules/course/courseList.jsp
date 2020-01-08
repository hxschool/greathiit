<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程基本信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function repairDel() {
		var id = document.getElementsByName("ids");
		// 拼接所有id
		for (var i = 0; i < id.length; i++) {
			if (id[i].checked) {
				ids += id[i].value + ",";
			}
		}
		ids = ids.substring(0, ids.length - 1);// 干掉字符串最后一个逗号
		$("#searchForm").attr("action",
				"${ctx}/course/course/deleteList?ids=" + ids);
		$("#searchForm").submit();
	}
		$(document).ready(function() {
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出课程数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/course/course/export");
						$("#searchForm").submit();
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});

			$("#btnImport").click(function() {
				$.jBox($("#importBox").html(), {
					title : "导入课程数据",
					buttons : {
						"关闭" : true
					},
					bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
				});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function showClass(courseId,courseName){
		
			$.post("${ctx}/course/courseClass/showClass",{"course.id":courseId},function(result){
				var ret = "";
				if(result.length==0){
					 ret = "该课程未设置任何班级信息,会导致导出成绩信息为空";
				}
			    for(var i=0;i<result.length;i++){
			    	var contact = result[i];
			    	ret = ret + '<div style="line-height:28px">'+contact.name+'</div>';
			    }
			    
			    showDialogMessage(courseName,ret);
			  });
		}
		function showDialogMessage(courseName,ret){
			layer.open({
				  title: courseName ,
				  area: ['400px'],
				  content: ret
				});  
		}
	</script>
</head>
<body>

	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/course/course/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/course/course/import/template">下载模板</a>
		</form>
	</div>

	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/course/course/${ctx}/course/course/?cursProperty=${param.cursProperty} ">课程维护</a></li>
		<shiro:hasPermission name="course:course:edit"><li><a href="${ctx}/course/course/form?cursProperty=${param.cursProperty}">课程添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="course" action="${ctx}/course/course/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
		<li><label>课程名称：</label>
					<form:input path="cursName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
		
		<li><label>教师姓名：</label>
					<form:input path="teacher.tchrName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
					<li><label>学期：</label>
					<select name="cursYearTerm" class="input-medium">
						<option value="">请选择</option>
						<c:forEach items="${fns:termYear()}" var="termYear">
							<option value="${termYear.key}"
																	<c:if test="${config.termYear==termYear.key}"> selected="selected" </c:if>>${termYear.key}</option>
						</c:forEach>
					</select>
			</li>
		
			<li class="btns"><input id="btnSubmit" class="button button-primary button-small" type="submit" value="查询"/>
			<input
				id="btnExport" class="button button-primary button-small" type="button" value="课程导出" /> <input
				id="btnImport" class="button button-primary button-small" type="button" value="课程导入" />
				
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/> </th>
				<th>课程编号</th>
				<th>课程名称</th>
				<th>任课教师</th>
				<!-- <th>专业</th> -->
				<th>学时</th>
				<th>学分</th>
				<th>开设学期</th>
				
				<!--<th>与相关课程的分工衔接</th>
				<th>其他说明</th> 
				<th>先修课程</th> -->
				<th>课程性质</th>
				<th>课程类型</th>
				<th>考核</th>

				<th>授课班级</th>
				<shiro:hasPermission name="course:course:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="course">
			<tr>
				<td><input type="checkbox" name="ids" value="${course.id}" /></td>
				<td><a href="${ctx}/course/course/form?id=${course.id}">
					${course.cursNum}
				</a></td>
				<td>
					${course.cursName}
				</td>
				<td>
					${course.teacher.tchrName}
				</td>
				<!-- <td>
					${course.cursMajor}
				</td -->
				<td>
					${course.cursClassHour}
				</td>
				<td>
					${course.cursCredit}
				</td>
				<td>
					${course.cursYearTerm}
				</td>
				

				<td>
		
					${fns:getDictLabel(course.cursProperty, 'course_property', '')}
				</td>

				<td>
					
					${fns:getDictLabel(course.cursType, 'course_curs_type', '')}
				</td>
				<td>
					
					${fns:getDictLabel(course.cursForm, 'course_curs_form', '')}
				</td>

				<td>
					<c:forEach items="${course.ccs}" var="courseClass">
					${courseClass.className }
					</c:forEach>
				</td>
				<td>
					<shiro:hasPermission name="course:course:oper">
    				<a class="button button-primary button-tiny"  href="${ctx}/course/course/form?id=${course.id}">修改</a>
					
					</shiro:hasPermission>
					<shiro:hasPermission name="course:course:select">
					<c:if test="${course!=null&&course.cursProperty==50}">
					<a  class="button button-action button-tiny" href="${ctx}/course/select/export?course.id=${course.id}">学生名单</a>
					</c:if>
					</shiro:hasPermission>
					
					<a class="button button-primary button-tiny"  href="${ctx}/course/course/submit?id=${course.id}">成绩参数</a>
					
					<shiro:hasPermission name="course:course:studentCourse">
    					<a  class="button button-action button-tiny" href="${ctx}/student/studentCourse/export/student?id=${course.id}">下载模板</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="course:course:export">
<div class="btn-group">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="width:80px;height:24px;margin:0px;padding:0px;">
    成绩单 <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
   	 <li><a   href="${ctx}/course/course/exportStudentCourse?id=${course.id}">导出</a></li>
     <li><a   href="${ctx}/course/course/viewExcel?id=${course.id}" target="_blank">浏览</a></li>
  </ul>
</div>
    				
    					
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		
		<tfoot><tr>
			<th ><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/></th><th colspan="11"> <a href="#" onclick="checkdel()" class="button button-caution button-small">批量删除</a></th>
			</tr>
		</tfoot>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>