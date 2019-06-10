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
			$("#btnTeacherExport").click(function() {
				top.$.jBox.confirm("确认要导出全部教师数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/course/export/allCourse");
						$("#searchForm").submit();
						$("#searchForm").attr("action", "${ctx}/course/export/teacher");
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
			
			$("#btnRootExport").click(function() {
				top.$.jBox.confirm("确认要导出全部教师数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#rootForm").attr("action", "${ctx}/course/export/allRoot");
						$("#rootForm").submit();
						$("#rootForm").attr("action", "${ctx}/course/export/root");
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
			
			$('#top').cxSelect({ 
				  url: '${ctx}/school/schoolRoot/treeLink',
				  selects: ['school', 'address'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
		});
		
	</script>
	<script src="${ctxStatic}/layer/layer.js"></script>
</head>
<body>
	
	
	<sys:message content="${message}"/>
	<form id="searchForm" class="breadcrumb form-search" action="${ctx}/course/export/teacher" method="post">
		
		<ul class="ul-form">
			
			<li>
				教师：&nbsp;&nbsp;<select name="teacherNumber" style="width: 200px;">
				<c:forEach items="${teachers}" var="item">
					<option value="${item.user.no }">${item.tchrName }</option>
				 </c:forEach>
				</select>
			</li>
			<li class="btns"><input class="btn btn-primary" value="导出此教师数据" type="submit"> <input id="btnTeacherExport" class="btn btn-primary" value="导出全部教师数据" type="button"></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	
	<hr>
	
	
	<form id="rootForm" class="breadcrumb form-search" action="${ctx}/course/export/root" method="post">
		
		<ul class="ul-form">
			
			<li id="top">
			
				学院：&nbsp;&nbsp;<select
								name="school" id="school" class="school"
								style="width: 200px;">

							</select> &nbsp;&nbsp;&nbsp;&nbsp;机房:&nbsp;&nbsp; <select id="address" name="address"
								class="address"
								style="width: 200px;">
							</select>
							
			</li>
			<li class="btns"><input  class="btn btn-primary" value="导出此教室数据" type="submit"> <input id="btnRootExport" class="btn btn-primary" value="导出全部教室数据" type="button"></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	
</body>
</html>