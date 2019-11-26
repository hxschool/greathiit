<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设置分班</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		/*
	    $('#element_id').cxSelect({ 
			  url: '${ctx}/sys/office/treeLink',
			  selects: ['department', 'specialty',"clazz"], 
			  jsonName: 'name',
			  jsonValue: 'value',
			  jsonSub: 'sub'
			});
		*/
		
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$("#searchForm").attr("action", "${ctx}/student/tracked/export");
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
})
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	
	function getIds(){
		var id = document.getElementsByName("ids");// 获取全选复选框
		var ids = "";
		for (var i = 0; i < id.length; i++) {
			if (id[i].checked) {
				ids += id[i].value + ",";
			}
		}
		ids = ids.substring(0, ids.length - 1);
		return ids;
	}
	
	function batchAction(action){
		var num = $("input[type='checkbox']:checked").length;
		if (num == 0) {
			  layer.alert('请选择你要操作的数据', {
				    skin: 'layui-layer-lan'
				    ,closeBtn: 0
				    ,anim: 4 //动画类型
				  });
			return;
		} 
		
			layer.open({
			     type: 2,
			     area: ['560px','560px'],
			     shade: 0.3,
			     shadeClose: false,//默认开启遮罩关闭
			     resize: false,//默认重设大小是否
			     maxmin: true,//默认最大最小化按钮
			     moveType: 1,//默认拖拽模式，0或者1
			     content: "${ctx}/system/clazz", 
			     btn: ['确定','关闭'],
			     yes: function (index) {
			         //获取选择的row,并加载到页面
			         var text = window["layui-layer-iframe" + index].callbackdata();
			         if(text){
					    $("#description").val(text);
					    $("#searchForm").attr("action","${ctx}/student/tracked/batchCls?ids=" + getIds());
						$("#searchForm").submit();
						 $("#description").val("");
			             layer.close(index)
			         }else{
			             layer.msg('请选择班级', {icon: 0});
			         }
			     },
			     cancel: function(){
			              
			     }
			 });
	}
	</script>
</head>
<body>

<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/student/tracked/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/student/tracked/import/template">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/student/tracked/">分班管理</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="student" action="${ctx}/student/tracked" method="post" class="breadcrumb form-search">
		<input id="description" name="description" type="hidden" />
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>是否分班：</label>
				<form:select path="isClass" class="input-medium " style="width:175px">
							<option value="">请选择</option>
							<option value="0" <c:if test="${student.isClass=='0'}"> selected </c:if> >未分班</option>
							<option value="1" <c:if test="${student.isClass=='1'}"> selected </c:if> >已分班</option>
						</form:select>
			</li>
			
			<li><label>班号：</label>
				<form:input path="classno" htmlEscape="false" maxlength="64" placeholder="20180101"  class="input-medium"/>
			</li>
			
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				
				<form:select path="gender" class="input-medium " style="width:175px">
							<option value="">请选择</option>
							<form:options items="${fns:getDictList('sex')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
			</li>
			
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			
			<input
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="20px;"><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/> </th>
				<th width="80px;">班级</th>
				<th width="80px;">学号</th>
				<th>姓名</th>
				
				<th>性别</th>
				<th>学历</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="student">
			<tr>
				<td><input type="checkbox" name="ids" value="${student.id}" onclick="selectSingle()"/></td> 
				<td>
					${student.clazz.name}
				</td>
				<td>
					${student.studentNumber}
				</td>
				<td><a href="${ctx}/student/student/form?id=${student.id}">
					${student.name}
				</a></td>
				
				<td>
					${fns:getDictLabel(student.gender,'sex','')}
				</td>
				
				<td>
					${fns:getDictLabel(student.edu,'student_edu','')}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
		<tfoot><tr>
			<th ><input type=checkbox name="selid" id="checkId" onclick="checkAll(this, 'ids')"/></th><th colspan="14"> 
			
			<a href="#" onclick="batchAction(${param.action})" class="btn btn-primary">分班</a>
			 未设置班级信息,或者班级信息异常数据可以进行手动分班
			</th>
			</tr>
		</tfoot>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>