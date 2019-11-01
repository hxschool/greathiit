<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统招数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$(".editBtn").click(function(){
				submitUrl("${ctx}/recruit/student/assign/ajaxSingleSetup",$(this).attr("id"));
			});
			
			$(".resetBtn").click(function(){
				layer.confirm('您是如何看待前端开发？', {
					  btn: ['确认重置','取消'] //按钮
					}, function(){
						$.post("${ctx}/recruit/student/assign/ajaxResetSetup",{majorId:$("#majorId").val(),classNo:$("#classNo").val(),ids:$(this).attr("id")},function(result){
						    if(result!=null&&result!="null"){
						    	layer.msg(result.responseMessage);
						    	layer.close(index);
						    	if(result.responseCode=='00000000'){
						    		window.location.reload();
						    	}
						    }
					   });
						
					}, function(){
					  
					});
			});
			
			$("#inputSubmit").click(function(){
				 var flag = false;
		          $("[name=ids]").each(function(){
		                if($(this).attr('checked')=='checked'){
		                     flag = true;
		                     return false;  
		                }
		           });
		          if (flag){
		        	  var chk_value =[];
					  	$('input[name=ids]:checked').each(function(){  
						   chk_value.push($(this).val());
						});
		        	  submitUrl("${ctx}/recruit/student/assign/ajaxSetup",chk_value);
		          }else{
		               alert('请选择需要分班的学生!');
		          }
			});
			
			$("#allcheckbox").click(function() {
				if (this.checked) {
					$("[name=ids]").attr("checked", true);
				} else {
					$("[name=ids]").attr("checked", false);
				}
			});
		});
			
	 	function submitUrl(url,value){
	 		 layer.open({
					title:" ",
				  type: 1,
				  btn: ['确认', '取消'],
				  area: ['535px', '345px'], //宽高
				  content: $("#diglog"),
				  yes: function(index, layero){  

					  if($("#classNo").val()==''){
						  layer.msg("请填写班级编号");
						  return;
					  }
					  //"${ctx}/recruit/student/recruitStudent/assign/ajaxSetup"
					  $.post(url,{majorId:$("#majorId").val(),classNo:$("#classNo").val(),ids:value},function(result){
						    
						    if(result!=null&&result!="null"){
						    	layer.msg(result.responseMessage);
						    	layer.close(index);
						    	if(result.responseCode=='00000000'){
						    		//window.location.href = window.location.href;
						    	}
						    }
					   });
					  
	                }
				});
	 	}
	</script>
<style type="text/css">

    /* Basic Grey */
    .basic-grey {
    margin-left:auto;
    margin-right:auto;
    max-width: 500px;
    background: #F7F7F7;
    padding: 25px 15px 25px 10px;
    font: 12px Georgia, "Times New Roman", Times, serif;
    color: #888;
    text-shadow: 1px 1px 1px #FFF;
    border:1px solid #E4E4E4;
    }
    .basic-grey h1 {
    font-size: 25px;
    padding: 0px 0px 10px 40px;
    display: block;
    border-bottom:1px solid #E4E4E4;
    margin: -10px -15px 30px -10px;;
    color: #888;
    }
    .basic-grey h1>span {
    display: block;
    font-size: 11px;
    }
    .basic-grey label {
    display: block;
    margin: 0px;
    }
    .basic-grey label>span {
    float: left;
    width: 20%;
    text-align: right;
    padding-right: 10px;
    margin-top: 10px;
    color: #888;
    }
    .basic-grey input[type="text"], .basic-grey input[type="email"], .basic-grey textarea, .basic-grey select {
    border: 1px solid #DADADA;
    color: #888;
    height: 30px;
    margin-bottom: 16px;
    margin-right: 6px;
    margin-top: 2px;
    outline: 0 none;
    padding: 3px 3px 3px 5px;
    width: 70%;
    font-size: 12px;
    line-height:15px;
    box-shadow: inset 0px 1px 4px #ECECEC;
    -moz-box-shadow: inset 0px 1px 4px #ECECEC;
    -webkit-box-shadow: inset 0px 1px 4px #ECECEC;
    }
    .basic-grey textarea{
    padding: 5px 3px 3px 5px;
    }
    .basic-grey select {
    background: #FFF url('down-arrow.png') no-repeat right;
    background: #FFF url('down-arrow.png') no-repeat right);
    appearance:none;
    -webkit-appearance:none;
    -moz-appearance: none;
    text-indent: 0.01px;
    text-overflow: '';
    width: 70%;
    height: 35px;
    line-height: 25px;
    }
    .basic-grey textarea{
    height:100px;
    }
    .basic-grey .button {
    background: #E27575;
    border: none;
    padding: 10px 25px 10px 25px;
    color: #FFF;
    box-shadow: 1px 1px 5px #B6B6B6;
    border-radius: 3px;
    text-shadow: 1px 1px 1px #9E3F3F;
    cursor: pointer;
    }
    .basic-grey .button:hover {
    background: #CF7A7A
    }

</style>
</head>
<body>
<br>
<jsp:useBean id="IdcardUtils" class="com.thinkgem.jeesite.common.utils.IdcardUtils" scope="page"/> 
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th>
			<input type="checkbox"
						name="allcheckbox" id="allcheckbox">
						</th>
				<th>考生号</th>
				<th>姓名</th>
				<th>性别</th>
				
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><input type="checkbox" name="ids" id="${user.id}"
						value="${user.id}"></td>
				<td>
					${user.name}
				</td>
				
				<td>
					${user.loginName}
				</td>
				
				<td>
					${IdcardUtils.getGender(user.loginName) }
				</td>
			
				<td>
    				<a class="editBtn" id="${user.id}">添加学号</a>
					<a href="${ctx}/recruit/student/recruitStudent/delete?id=${user.id}" onclick="return confirmx('确认要删除该统招数据吗？', this.href)">重置学号</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<a id="inputSubmit" class="button button-block button-rounded button-primary button-large">确认分配学号</a>
	
	<input type="hidden" name="majorId" id="majorId" value="${majorId }"/>
	
	<div id="diglog" style="display: none">
		<form action="" method="post" class="basic-grey"
			style="margin-top: 5px">
			<h1>
				信息核实 <span>请输入班级排序号</span>
			</h1>
			 <label> <span>班号:</span> <input id="classNo" type="text"
				name="classNo" placeholder="请输入班级编号" />
			</label>
		</form>
	</div>
</body>
</html>