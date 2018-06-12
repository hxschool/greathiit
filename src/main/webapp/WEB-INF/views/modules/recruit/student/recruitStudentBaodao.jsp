<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

	<title>统招数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#inputSubmit").click(function(){
				layer.open({
						title:" ",
					  type: 1,
					  btn: ['确认', '取消'],
					  area: ['800px', '420px'], //宽高
					  content: $("#diglog").html(),
					  yes: function(index, layero){  
						  
						  $.post("${ctx}/recruit/student/recruitStudent/checkUsernameAndIdcard",{username:$("#username").val(),idCard:$("#idCard").val()},function(result){
							    $("span").html(result);
							    if(result!=null&&result!="null"){
							    	$("#inputForm").submit();
							    	layer.close(index);
							    }else{
							    	layer.msg('实名信息核对失败,请仔细检查信息是否正确.', function(){
										//关闭后的操作
										});
							    }
						   });
						  
		                }
					});
				});
			
			
			
			
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
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

	<form:form id="inputForm" modelAttribute="recruitStudent"
		action="${ctx}/recruit/student/recruitStudent/baodao" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />

		<div class="form-actions">
			<a id="inputSubmit"
				class="button button-block button-rounded button-glow  button-raised  button-primary  button-large ">我要报到</a>
		</div>
	</form:form>


	<div id="diglog" style="display: none">
		<form action="" method="post" class="basic-grey"
			style="margin-top: 20px">
			<h1>
				信息核实 <span>请输入真实信息</span>
			</h1>
			 <label> <span>姓名:</span> <input id="username" type="email"
				name="username" placeholder="请输入您的真实姓名" />
			</label>
<label> <span>身份证号 :</span> <input id="idCard" type="text"
				name="idCard" placeholder="请输入您的身份证号" />
			</label>

		</form>
	</div>

</body>
</html>