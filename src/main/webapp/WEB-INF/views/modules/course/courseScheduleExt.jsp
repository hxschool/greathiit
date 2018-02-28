<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.thinkgem.jeesite.common.utils.CourseUtil" %>
<%@ page import="com.thinkgem.jeesite.common.utils.SpringContextHolder" %>
<%@ page import="com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar" %>
<%@ page import="com.thinkgem.jeesite.modules.calendar.service.CourseCalendarService" %>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学期初始化管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#element_id').cxSelect({ 
				  url: '${ctx}/sys/office/treeClassLink',
				  selects: ['province', 'city','clazz', 'area'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
		});
		function up_Inquiry(){
			layer.confirm('<form  id="teacherForm" action="${ctx}/course/export/getCourseScheduleExt" method="post" >请输入教师名:<input name="teacherName" id="teacherName"/></form>', {
				  btn: ['搜索','关闭'],
				  yes: function(index, layero){
					  $("#teacherForm").submit();
					  layer.close(index);
					  },
					  no: function(index, layero){
						  layer.close(index);
						  }
				});
		}
		
	</script>
	<script src="${ctxStatic}/layer/layer.js"></script>
</head>
<body>
	
	
	<sys:message content="${message}"/>
	<form id="searchForm" action="${ctx}/course/export/getCourseScheduleExt" method="post" class="breadcrumb form-search">
		
		<div id="element_id">
			<label>学院：</label><select class="province input-medium"><option>请选择</option></select>
			<label>专业：</label><select id="city" class="city input-medium"><option>请选择</option></select>
			<label>年级：</label><select id="clazz" class="clazz input-medium" ><option>请选择</option></select>
			<label>班级：</label><select id="area" class="area input-medium" name="courseClass"><option>请选择</option></select>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			 <input type="button" class="btn btn-primary" value="按教师查询" onclick="up_Inquiry()" class="button"/>
		</div>
	</form>
	
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程 </th>
				<th>教师</th>
				<th>时间</th>
				<th>地点</th>
				<th>班级</th>
				<th>备注</th>
				<th>资料</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${courseScheduleExt}" var="item">
			<%
			  com.thinkgem.jeesite.modules.course.entity.CourseScheduleExt courseScheduleExt =(com.thinkgem.jeesite.modules.course.entity.CourseScheduleExt)pageContext.getAttribute("item");
			  String timeAdd = courseScheduleExt.getTimeAdd();
			%>
			<tr>
				<td>${item.cursName}</td>
				<td>${item.tchrName}</td>
				<td>
				<%
				Map<String,String> $col_a = CourseUtil.GetTimeCol(timeAdd);
				int date = (Integer.valueOf($col_a.get("week")) - 1) * 7 + Integer.valueOf($col_a.get("zhou"));
				CourseCalendarService courseCalendarService = (CourseCalendarService)SpringContextHolder.getBean("courseCalendarService");
				CourseCalendar courseCalendar = courseCalendarService.systemConfig();
				String today = courseCalendar.getCalendarYear() + "-" + courseCalendar.getCalendarMonth() + "-" + courseCalendar.getCalendarDay();
				
				out.print( "第" + $col_a.get("week") + "周");
				
				out.print( " " + CourseUtil.addDate(today,date) +" ");
				out.print( " " + CourseUtil.zhou($col_a.get("zhou")));
				out.print( " " + CourseUtil.jie($col_a.get("jie")));
				
				%>
				</td>
				<td>
				<%
				String k = timeAdd.substring(5,7);
				String c = timeAdd.substring(7,10);
				out.print( " " +com.thinkgem.jeesite.common.utils.CourseUtil.schoolRootMap.get(k));
				com.thinkgem.jeesite.modules.school.service.SchoolRootService schoolRootService = com.thinkgem.jeesite.common.utils.SpringContextHolder.getBean("schoolRootService");
				com.thinkgem.jeesite.modules.school.entity.SchoolRoot schoolRoot = schoolRootService.get(k.concat(c));
				out.print(schoolRoot.getLabel());
				%></td>
				<td>	
					<c:choose>
						<c:when test="${! empty param.courseClass}"> 
						${param.courseClass }
					</c:when>
					<c:otherwise>
						${item.courseClass}
					</c:otherwise>
					</c:choose>
				</td>
				<td>${item.remarks}</td>
				<td>${item.tips}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>