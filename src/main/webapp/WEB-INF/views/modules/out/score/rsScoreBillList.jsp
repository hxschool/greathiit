<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试成绩单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function() {
				$.jBox($("#importBox").html(), {
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
	</script>
</head>
<body>
	
	
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/out/score/rsScoreBill/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/out/score/rsScoreBill/template">下载模板</a>
		</form>
	</div>
	
	<form:form id="searchForm" modelAttribute="rsJcd" action="${ctx}/out/score/rsScoreBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>考试号：</label>
				<form:input path="ksh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="xm" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证号码：</label>
				<form:input path="sfzh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>专业调剂：</label>
				<select name="zytj" class="input-medium" style="width: 175px;"><option
						value="">请选择</option>
					<option value="是" <c:if test="${zytj=='是'}">selected</c:if> >是</option>
					<option value="否" <c:if test="${zytj=='否'}">selected</c:if> >否</option></select></li>
			<li><label>专业调剂：</label>
				<select name="status"  class="input-medium" ><option value="">请选择</option>
				<option value="1" <c:if test="${status==1}">selected</c:if> >已录取</option>
				<option value="2" <c:if test="${status==2}">selected</c:if>>名额已满</option>
				<option value="3" <c:if test="${status==3}">selected</c:if>>专业异常</option>
				<option value="4" <c:if test="${status==4}">selected</c:if>>分数过低</option>
				<option value="5" <c:if test="${status==5}">selected</c:if>>未参加考试</option>
				</select>
			</li>
			
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考生号</th>
				<th>姓名</th>
				<th>身份证号</th>
				<th>总分</th>
				<th>投档成绩</th>
				<th>科目1</th>
				<th>科目2</th>
				<th>科目3</th>
				<th>意向专业1</th>
				<th>意向专业2</th>
				<th>意向专业3</th>
				<th>意向专业4</th>
				<th>意向专业5</th>
				<th>是否服从专业调剂</th>
				<th>录入状态</th>
				<shiro:hasPermission name="out:jcd:rsJcd:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rsJcd">
			<tr >
				<td><a href="${ctx}/out/score/rsScoreBill/form?id=${rsJcd.id}">
					${rsJcd.ksh}
				</a></td>
				<td>
					${rsJcd.xm}
				</td>
				<td>
					${rsJcd.sfzh}
				</td>
				<td>
					${rsJcd.zf}
				</td>
				<td>
					${rsJcd.cj}
				</td>
				<td>
					${rsJcd.km1}
				</td>
				<td>
					${rsJcd.km2}
				</td>
				<td>
					${rsJcd.km3}
				</td>
				<td>
					${rsJcd.zy1}
				</td>
				<td>
					${rsJcd.zy2}
				</td>
				<td>
					${rsJcd.zy3}
				</td>
				<td>
					${rsJcd.zy4}
				</td>
				<td>
					${rsJcd.zy5}
				</td>
				
				<td>
					${rsJcd.zytj}
				</td>
				<td>
				
				<c:choose>
				<c:when test="${rsJcd.status ==1}">
				已录取
				
				</c:when>
				<c:when test="${rsJcd.status ==2}">
				名额已满
				</c:when>
				
				<c:when test="${rsJcd.status ==3}">
				专业异常
				
				</c:when>
				
				<c:when test="${rsJcd.status ==4}">
				分数过低
				</c:when>
				
				<c:when test="${rsJcd.status ==5}">
				未参加考试
				</c:when>
				</c:choose>
				</td>
				
				<shiro:hasPermission name="out:jcd:rsJcd:edit"><td>
    				<a href="${ctx}/out/score/rsScoreBill/form?id=${rsJcd.id}">查看</a>
					<!-- <a href="${ctx}/out/score/rsScoreBill/delete?id=${rsJcd.id}" onclick="return confirmx('确认要删除该考试成绩单吗？', this.href)">删除</a> -->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>