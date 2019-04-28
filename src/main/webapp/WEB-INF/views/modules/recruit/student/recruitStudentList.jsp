<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统招数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#allcheckbox").click(function() {
				if (this.checked) {
					$("[name=ids]").attr("checked", true);

				} else {
					$("[name=ids]").attr("checked", false);
					
				}
			});
			
			$('#element_id').cxSelect({ 
				  url: '${ctx}/sys/office/treeClassLink',
				  selects: ['school', 'major', 'grade'], 
				  jsonName: 'name',
				  jsonValue: 'value',
				  jsonSub: 'sub'
				}); 
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/recruit/student/recruitStudent/export");
						$("#searchForm").submit();
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
			$("#btnImport").click(function() {
				$.jBox($("#importBox").html(), {
					title : "导入数据",
					buttons : {
						"关闭" : true
					},
					bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
				});
			});
			
			$("#btnSimpleImport").click(function() {
				$.jBox($("#importSimpleBox").html(), {
					title : "导入数据",
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
		function up_Inquiry(){
			layer.confirm('<form  id="classForm" action="${ctx}/recruit/student/recruitStudent/assign" method="post" >学院/专业名称:${recruitStudent.department.name}/${recruitStudent.major.name}<br>请班级名称:<input name="classname" id="classname" /><input type="hidden" name="id_card_str" id="id_card_str" /><input type="hidden" name="majorId" id="majorId" value="${recruitStudent.major.id}"/></form>', {
				  btn: ['确认','关闭'],
				  yes: function(index, layero){
					  $("input:checkbox[name='ids']:checked").each(function() {
						  if($("#id_card_str").val()==""){
							  $("#id_card_str").val($(this).val());
						  }else{
							  $("#id_card_str").val($("#id_card_str").val()+","+$(this).val());
						  }
					  });
					  	$("#classForm").submit();
					  layer.close(index);
					  },
					  no: function(index, layero){
						  layer.close(index);
						  }
				});
		}
	</script>
	<style type="text/css">
	.chengji
	 {width:26px; height:22px; border:1px solid #F00; margin-right:-1px; float: left;  }
	
	</style>
</head>
<body>
<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/recruit/student/recruitStudent/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " />
				
				
				
				 <a
				href="${ctx}/recruit/student/recruitStudent/import/template">下载模板</a>
		</form>
	</div>
	
	<div id="importSimpleBox" class="hide">
		<form id="importForm" action="${ctx}/recruit/student/recruitStudent/importSimple" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " />
				 <a
				href="${ctx}/recruit/student/recruitStudent/import/simpleTemplate">下载模板</a>
				
				
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/recruit/student/recruitStudent/list">统招数据列表</a></li>
		<shiro:hasPermission name="recruit:student:recruitStudent:edit"><li><a href="${ctx}/recruit/student/recruitStudent/form">统招数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="recruitStudent" action="${ctx}/recruit/student/recruitStudent/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<div id="element_id">
				<s:if test="${fns:getUser().id=='1' }">

				<li><label>所属学院：</label> <select name="department.id" id="school"
					class="school" style="width: 176px;">
						<option value="" selected="selected">==请选择学院==</option>
				</select></li>
				<li><label>所属专业：</label> <select name="major.id" id="major"
					class="major" style="width: 176px;">
						<option value="" selected="selected">==请选择专业==</option>
				</select></li>
				
				<li><label>状态：</label>
				
				<form:select path="status" id="status"
					class="input-status" style="width:178px">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('RECRUIT_STUDENT_STATUS')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				

				</li>
				</s:if>
			</div>
			<li class="clearfix"></li>
	
			<li><label>真实姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				
				<select id="gender" name="gender" class=" input-medium" style="width: 176px;">
				<option value="">请选择</option>
				<option value="男">男</option>
				<option value="女">女</option>
				</select>
			</li>
			
			<li><label>身份证号码：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/><input
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" />
				<input
				id="btnSimpleImport" class="btn btn-primary" type="button" value="快速导入(简版)" />
				</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<input id="allocation" class="btn btn-success"
				type="button" onclick="up_Inquiry()" value="分配班级" />
				
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="margin-top:10px;">
		<thead>
			<tr><th><input type="checkbox"
						name="allcheckbox" id="allcheckbox"></th>
				<th>考试号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>身份号</th>
				<th>
				<div class="chengji" >数学</div>
				<div class="chengji" >语文</div>
				<div class="chengji">外语</div>
				<div class="chengji">综合</div>
				<div class="chengji">总分</div>
				<div class="chengji">美术</div>
				</th>
				<th>录取学院</th>
				<th>录取专业</th>
				<th>联系电话</th>
				<!-- <th>毕业中学名称</th>
				<th>毕业中学名称</th>
				<th>生源所在地</th>
				<th>录取通知书编号</th> -->
				<th>省份</th>
				<th>状态</th>
				<shiro:hasPermission name="recruit:student:recruitStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recruitStudent">
			<tr><td>
			<input type="checkbox" name="ids" id="${recruitStudent.idCard}"
						value="${recruitStudent.idCard}">

			</td>
				<td><a href="${ctx}/recruit/student/recruitStudent/form?id=${recruitStudent.id}">
					${recruitStudent.exaNumber}
				</a></td>
				
				<td>
					${recruitStudent.username}
				</td>
				
				<td>
					${recruitStudent.gender}
				</td>
				
				<td>
					${recruitStudent.birthday}
				</td>
				
				
				<td>
					${recruitStudent.idCard}
				</td>
				
				<td>
				<div class="chengji" > ${fn:substring(recruitStudent.shuxue, 0, fn:indexOf(recruitStudent.shuxue,"."))}</div>
				<div class="chengji" > ${fn:substring(recruitStudent.yuwen, 0, fn:indexOf(recruitStudent.yuwen,"."))}</div>
				<div class="chengji"> ${fn:substring(recruitStudent.waiyu, 0, fn:indexOf(recruitStudent.waiyu,"."))}</div>
				<div class="chengji"> ${fn:substring(recruitStudent.zonghe, 0, fn:indexOf(recruitStudent.zonghe,"."))}</div>
				<div class="chengji"> ${fn:substring(recruitStudent.zongfen, 0, fn:indexOf(recruitStudent.zongfen,"."))}</div>
				<div class="chengji">  ${fn:substring(recruitStudent.techang, 0, fn:indexOf(recruitStudent.techang,"."))}</div>
				</td>
				
			
				
				<td>
					${recruitStudent.department.name}
				</td>
				
				<td>
					${recruitStudent.major.name}
				</td>
				
				<td>
					${recruitStudent.phone}
				</td>
				
				<!-- <td>
					${recruitStudent.middleSchool}
				</td>
				<td>
					
					${fn:substring(recruitStudent.location, 0, 16)}
				</td>
				<td>
					${recruitStudent.noticeNumber}
				</td> -->
				<td>
					${recruitStudent.province}
				</td>
				<td>
					
					${fns:getDictLabel(recruitStudent.status, 'RECRUIT_STUDENT_STATUS', '未知状态')}
				</td>
				
			
				<shiro:hasPermission name="recruit:student:recruitStudent:edit"><td>
    				<a href="${ctx}/recruit/student/recruitStudent/form?id=${recruitStudent.id}">查看</a>
    				<a href="${ctx}/recruit/student/recruitStudent/form?id=${recruitStudent.id}">分班</a>
<!-- 					<a href="${ctx}/recruit/student/recruitStudent/delete?id=${recruitStudent.id}" onclick="return confirmx('确认要删除该统招数据吗？', this.href)">删除</a> -->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>