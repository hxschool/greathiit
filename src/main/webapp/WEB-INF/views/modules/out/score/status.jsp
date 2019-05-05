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
	
	<form:form id="searchForm" modelAttribute="rsScoreBill" action="${ctx}/out/score/bill/status/${status}" method="post" class="breadcrumb form-search">
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
			<li class="clearfix"></li>
			<li><label>学期：</label>
				<select name="termYear" class="input-medium" style="width:175px">
					<c:forEach items="${fns:termYear()}" var="termYear">
						<option value="${termYear.key}"
							<c:if test="${config.termYear==termYear.key}"> selected="selected" </c:if>>${termYear.key}</option>
					</c:forEach>
				</select>
			</li>
			<li><label>专业调剂：</label>
				<select name="zytj" class="input-medium" style="width: 175px"><option
						value="">请选择</option>
					<option value="是">是</option>
					<option value="否">否</option></select></li>
			
			
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
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rsScoreBill">
			<tr>
				<td><a href="${ctx}/out/score/bill/form?id=${rsScoreBill.id}">
					${rsScoreBill.ksh}
				</a></td>
				<td>
					${rsScoreBill.xm}
				</td>
				<td>
					${rsScoreBill.sfzh}
				</td>
				<td>
					${rsScoreBill.zf}
				</td>
				<td>
					${rsScoreBill.cj}
				</td>
				<td>
					${rsScoreBill.km1}
				</td>
				<td>
					${rsScoreBill.km2}
				</td>
				<td>
					${rsScoreBill.km3}
				</td>
				<td>
					${rsScoreBill.zy1}
				</td>
				<td>
					${rsScoreBill.zy2}
				</td>
				<td>
					${rsScoreBill.zy3}
				</td>
				<td>
					${rsScoreBill.zy4}
				</td>
				<td>
					${rsScoreBill.zy5}
				</td>
				<td>
					${rsScoreBill.zytj}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>