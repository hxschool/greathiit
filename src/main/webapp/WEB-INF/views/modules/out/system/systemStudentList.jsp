<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单招报名申请表管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/out/system/systemStudent/">单招报名申请表列表</a></li>
		<shiro:hasPermission name="out:system:systemStudent:edit"><li><a href="${ctx}/out/system/systemStudent/form">单招报名申请表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="systemStudent" action="${ctx}/out/system/systemStudent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>科类：</label>
				<form:input path="hcFormKl" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>报考号：</label>
				<form:input path="hcFormBkh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="hcFormXm" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<select name="hcFormXb" class="input-medium"><option value="">请选择</option><option value="男">男</option><option value="女">女</option></select>
			</li>
			<li><label>身份证号：</label>
				<form:input path="hcFormSfzh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/><input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<!-- <input id="btnImport" class="btn btn-primary" type="button" value="导入"/> -->
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>顺序号</th>
				<th>所在省份</th>
				<th>所在城市</th>
				<th>科类</th>
				<th>报考号</th>
				<th>姓名</th>
				
				<th>出生日期</th>
				<th>性别</th>
				<th>身份证号</th>
				<th>毕业类别</th>
				<th>毕业学校</th>
				<th>联系方式</th>
				
				<th>报考专业1</th>
				
				<th>是否服从专业调剂</th>
				<th>与本人关系</th>
				<th>联系人姓名</th>
				<th>联系电话</th>
				<th>工作单位</th>
				<th>任何职务</th>
				
				<th>备用联系方式</th>
				<th>qq</th>
				<th>备注</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="out:system:systemStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="systemStudent">
			<tr>
				
				<td>
					${systemStudent.hcFormArea}
				</td>
				<td>
					${systemStudent.hcFormProvince}
			</td>
				<td>
					${systemStudent.hcFormCity}
				</td>
				<td>
					${systemStudent.hcFormKl}
				</td>
				<td>
					${systemStudent.hcFormBkh}
				</td>
				<td>
					${systemStudent.hcFormXm}
				</td>
			
				<td>
					${systemStudent.hcFormBirth}
				</td>
				<td>
					${systemStudent.hcFormXb}
				</td>
				<td>
					${systemStudent.hcFormSfzh}
				</td>
				<td>
					${systemStudent.hcFormBylb}
				</td>
	
				<td>
					${systemStudent.hcFormByxx}
				</td>

				<td>
					${systemStudent.hcFormSj}
				</td>

				<td>
					${systemStudent.hcFormZy1}
				</td>
				
				<td>
					${systemStudent.hcFormZytj}
				</td>
				
				<td>
					${systemStudent.hcFormJjlxrFaGx}
				</td>
				<td>
					${systemStudent.hcFormJjlxrFaName}
				</td>
				<td>
					${systemStudent.hcFormJjlxrFaTel}
				</td>
				<td>
					${systemStudent.hcFormJjlxrFaWork}
				</td>
				<td>
					${systemStudent.hcFormJjlxrFaZw}
				</td>
				
				<td>
					${systemStudent.hcFormBysj}
				</td>
				<td>
					${systemStudent.hcFormQq}
				</td>
				<td>
					${systemStudent.hcFormBz}
				</td>
				<td>
					<fmt:formatDate value="${systemStudent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${systemStudent.remarks}
				</td>
				<shiro:hasPermission name="out:system:systemStudent:edit"><td>
    				<a href="${ctx}/out/system/systemStudent/form?id=${systemStudent.id}">修改</a>
					<!-- <a href="${ctx}/out/system/systemStudent/delete?id=${systemStudent.id}" onclick="return confirmx('确认要删除该单招报名申请表吗？', this.href)">删除</a> -->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/out/system/systemStudent/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		
	</script>
</body>
</html>