<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师班级信息表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function showContact(name,studentNumber){
			$.post("contact",{studentNumber:studentNumber},function(result){
				var ret = "";
				if(result.length==0){
					 ret = "紧急联系人方式不存在,请联系该同学补充紧急联系人信息";
				}
			    for(var i=0;i<result.length;i++){
			    	var contact = result[i];
			    	ret = ret+ '<div style="line-height:28px">关系:'+getContactType(contact.contactType)+'</div><div style="line-height:28px">姓名:'+contact.contact+'</div><div style="line-height:28px">联系方式:'+contact.mobile+'</div>';
			    }
			    
			    showDialogMessage(name,studentNumber,ret);
			  });
		}
		
		function showEmergency(name,studentNumber){
			$.post("emergency",{studentNumber:studentNumber},function(result){
				var ret = "";
				if(result.length==0){
					 ret = "社交类型信息不存在";
				}
			    for(var i=0;i<result.length;i++){
			    	var contact = result[i];
			    	ret = ret+ '<div style="line-height:28px">社交类型:'+getEmergencyType(contact.contactType)+'</div><div style="line-height:28px">联系方式:'+contact.contact+'</div><div style="line-height:28px">';
			    }
			    
			    showDialogMessage(name,studentNumber,ret);
			  });
		}
		function showDialogMessage(name,studentNumber,ret){
			layer.open({
				  title: " 学号:" + studentNumber + ","+ name ,
				  area: ['400px'],
				  content: ret
				});  
		}
		function getContactType(ret){
			var dicts = {};
			<c:forEach items="${fns:getDictList('contact_type')}" var="testData">
				dicts['${testData.value}']='${testData.label}';
			</c:forEach>
			return dicts[ret];
		}
		
		
		function getEmergencyType(ret){
			var dicts = {};
			<c:forEach items="${fns:getDictList('social_contact')}" var="testData">
				dicts['${testData.value}']='${testData.label}';
			</c:forEach>
			return dicts[ret];
		}
		
	</script>


<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/css/bootstrap-dialog.min.css" rel="stylesheet" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/js/bootstrap-dialog.min.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/teacher/teacherClass/">我的学生</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="student" action="${ctx}/teacher/teacherClass/student" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学生姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>班号</th>
				<th>班级</th>
				<th>更新时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="teacher:teacherClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="student">
			<tr>
				<td>
				${student.student.no}
				</td>
				<td>
				${student.name}
				</td>
				<td>
				${student.clazz.id}
				</td>
				<td>
				${student.clazz.name}
				</td>
				
				
				<td>
					<fmt:formatDate value="${student.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${student.remarks}
				</td>
				<shiro:hasPermission name="teacher:teacherClass:edit"><td>
				<a href="javascript:void(0)" onclick="showEmergency('${student.name}','${student.student.no}')" class="btn btn-primary">社交信息</a>
    				<a href="javascript:void(0)" onclick="showContact('${student.name}','${student.student.no}')" class="btn btn-warning">紧急联系人</a>
    				
					<a href="${ctx}/teacher/teacherClass/delete?id=${student.id}" onclick="return confirmx('确认要删除该教师班级信息表吗？', this.href)" class="btn btn-danger">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>


	<div class="modal fade">  
  <div class="modal-dialog">  
    <div class="modal-content">  
      <div class="modal-header">  
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
        <h4 class="modal-title">Modal title</h4>  
      </div>  
      <div class="modal-body">  
        <p>One fine body…</p>  
      </div>  
      <div class="modal-footer">  
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>  
        <button type="button" class="btn btn-primary">Save changes</button>  
      </div>  
    </div><!-- /.modal-content -->  
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->


</body>
</html>