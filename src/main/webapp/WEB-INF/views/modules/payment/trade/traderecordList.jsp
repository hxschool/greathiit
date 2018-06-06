<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易信息管理</title>
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
		<li class="active"><a href="${ctx}/payment/trade/traderecord/">交易信息列表</a></li>
		<shiro:hasPermission name="payment:trade:traderecord:edit"><li><a href="${ctx}/payment/trade/traderecord/form">交易信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="traderecord" action="${ctx}/payment/trade/traderecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>支付金额：</label>
				<form:input path="payAmount" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>支付时间：</label>
				<input name="payTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${traderecord.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>错误码：</label>
				<form:input path="errorCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>错误描述：</label>
				<form:input path="errorMsg" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>回调地址：</label>
				<form:input path="notifyUrl" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>trd_extra：</label>
				<form:input path="extra" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>主题：</label>
				<form:input path="subject" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>详情：</label>
				<form:input path="detail" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>渠道：</label>
				<form:input path="channel" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>用户：</label>
				<form:input path="user.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户端IP：</label>
				<form:input path="userIp" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>trd_id</th>
				<th>支付金额</th>
				<th>支付时间</th>
				<th>错误码</th>
				<th>错误描述</th>
				<th>trd_code_url</th>
				<th>回调地址</th>
				<th>trd_extra</th>
				<th>主题</th>
				<th>详情</th>
				<th>渠道</th>
				<th>状态</th>
				<th>用户</th>
				<th>客户端IP</th>
				<th>身份证</th>
				<shiro:hasPermission name="payment:trade:traderecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="traderecord">
			<tr>
				<td><a href="${ctx}/payment/trade/traderecord/form?id=${traderecord.id}">
					${traderecord.id}
				</a></td>
				<td>
					${traderecord.payAmount}
				</td>
				<td>
					<fmt:formatDate value="${traderecord.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${traderecord.errorCode}
				</td>
				<td>
					${traderecord.errorMsg}
				</td>
				<td>
					${traderecord.codeUrl}
				</td>
				<td>
					${traderecord.notifyUrl}
				</td>
				<td>
					${traderecord.extra}
				</td>
				<td>
					${traderecord.subject}
				</td>
				<td>
					${traderecord.detail}
				</td>
				<td>
					${traderecord.channel}
				</td>
				<td>
					${traderecord.status}
				</td>
				<td>
					${traderecord.user.name}
				</td>
				<td>
					${traderecord.userIp}
				</td>
				<td>
					${traderecord.idCard}
				</td>
				<shiro:hasPermission name="payment:trade:traderecord:edit"><td>
    				<a href="${ctx}/payment/trade/traderecord/form?id=${traderecord.id}">修改</a>
					<a href="${ctx}/payment/trade/traderecord/delete?id=${traderecord.id}" onclick="return confirmx('确认要删除该交易信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>