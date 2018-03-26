<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/modules/kaoqin/header.jsp"%>
<html>
<head>
	<title>宿舍管理管理</title>
	
</head>
<body>

	
<c:forEach var="item" items="${list}" varStatus="status">
       <a href="${ctx}/dorm/kaoqin/init?id=${item.id}"> ${item.studentNumber} </a>
       <br>
       ${item.username}<br>
       ${item.dormBuildId}<br>
        ${item.dormId}<br>
 <fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd" /><br>
          
<c:if test="${item.detail==0}"><a href="#">考勤</a></c:if>
<c:if test="${item.detail==1}">已考勤</c:if>
<c:if test="${item.detail==2}">缺勤</c:if>
<c:if test="${item.detail==3}">请假</c:if>
 <br>

</c:forEach> 

	<div class="weui-flex">
      <div class="weui-flex__item"><div class="placeholder">weui</div></div>
      <div class="weui-flex__item"><div class="placeholder">weui</div></div>
      <div class="weui-flex__item"><div class="placeholder">weui</div></div>
      <div class="weui-flex__item"><div class="placeholder">weui</div></div>
    </div>
</body>
</html>