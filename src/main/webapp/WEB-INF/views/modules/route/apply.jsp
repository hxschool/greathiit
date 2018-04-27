<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctxStatic}/zhaosheng/bootstrap.min.css">
</head>
<body>

	<div class="main-container container no-sidebar">
		<div class="main-content">
			<div class="page-content">
				<div class="row-fluid">
					<!--PAGE CONTENT BEGINS HERE-->
					<div class="content">
						<div class="panel-body">
							<div class="clearfix"></div>
							<form class="form-horizontal" action="save" method="post">
								<fieldset>
									<div id="legend" class="">
										<legend class="">获取教师号</legend>
									</div>
									<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">员工职称</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											<select name="userType"  class="form-control">
												<c:forEach items="${fns:getDictList('sys_user_type')}" var="dict">
	                                        	<option value="${dict.value}">${dict.label}</option>
	                                    		</c:forEach>
                                    		</select>
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">工作类型</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											
												<select name="role" class="form-control"><option value="10">正式</option><option value="20">外聘</option></select>
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">姓名</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											<input placeholder="请输入真实姓名" class="form-control" type="text"
												name="name">
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">身份证</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											<input placeholder="选填项,避免教师姓名重复问题" class="form-control"
												type="text" name="idcard">
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">移动电话</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											<input placeholder="请输入移动电话号码" class="form-control"
												type="text" name="mobile">
											<p class="help-block"></p>
										</div>
									</div>

									

									<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">email</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											<input placeholder="请输入email" class="form-control"
												type="text" name="email">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group text-s12 ">

										<!-- Select Basic -->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs">请选择部门</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											<select class="form-control" name="dept">
												<option value="">请选择部门</option>
												<option value="11">校领导</option>
												<option value="12">办公室</option>
												<option value="13">人力资源部</option>
												<option value="14">教务处</option>
												<option value="15">学生处</option>
												<option value="16">招生办</option>
												<option value="17">素质教育中心</option>
												<option value="18">就业指导中心</option>
												<option value="19">学籍科</option>
												<option value="20">团委</option>
												<option value="21">公寓管理中心</option>
												<option value="22">电子工程学院</option>
												<option value="23">软件学院</option>
												<option value="24">英语数学教研室</option>
												<option value="25">艺术设计学院</option>
												<option value="26">商学院</option>
												<option value="27">思政公选教研室</option>
												<option value="28">图书馆</option>
												<option value="29">保卫处</option>
												<option value="30">继续教育学院</option>
												<option value="31">体育教研室</option>
												<option value="32">财务处</option>
												<option value="33">总务处</option>
												<option value="34">车队</option>
												<option value="35">运维中心</option>
												<option value="36">基建办</option>
												<option value="37">饮食中心</option>
												<option value="38">教学楼管理中心</option>
												<option value="39">商服管理中心</option>
												<option value="40">艺体中心</option>
												<option value="41">资产管理中心</option>
												<option value="42">江北运维中心</option>
												<option value="99">学生</option>
											</select>
										</div>

									</div>

									<div class="form-group text-s12 ">

										<!-- Text input-->
										<label
											class="control-label col-sm-3 col-xs-12 text-right text-left-xs"
											for="input01">请求码</label>
										<div class="input-group input-group-sm col-sm-7 col-xs-12">
											<input placeholder="请输入请求码" class="form-control" type="text"
												name="code">
											<p class="help-block">联系其他教师获取请求码</p>
										</div>
									</div>

									<div class="form-group text-s12">
										<div class="text-center">
											<input type="submit" class="btn btn-info" name='bm_submit'
												value="点击获取教师号">

										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
