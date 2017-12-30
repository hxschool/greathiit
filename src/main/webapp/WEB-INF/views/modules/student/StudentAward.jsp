<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生参与项目页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
	<link rel="stylesheet" href="${ctxStatic}/modules/teacher/common.css" />
	<link rel="stylesheet" href="${ctxStatic}/modules/teacher/teacher_information.css" />
<link rel="stylesheet" href="${ctxStatic}/modules/teacher/teaching_management.css" />


<script type="text/javascript">
	var msg = "${message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。
</script>
</head>

<body>

	<div class="content">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="row">
						<div class="span9 div-content-white-bgr" style="min-height: 440px">
							<div class="div-inf-bar">
								<label>学生参与项目</label>
							</div>
							<div class="div-inf-tbl">
								<table class="table table-bordered table-condensed"
									id="studentItemList">
									<thead>
										<tr>
											<th>项目编号</th>
											<th>项目名称</th>
											<th>审核状态</th>
											<th>得分</th>
											<th>操作</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										

									<c:forEach items="${lists}" var="item">

											<tr>
												<td>${item.itemNum}</td>
												<td>${item.itemName}</td>
												<td>${item.itemState}</td>
												<td>${item.itemScore}</td>
												<td><a onclick="return confirm('确认删除？')"
													href="Student_Portfolio_Activity_deleteItem?itemId=${item.stuItemId}">删除</a></td>
												<td><a
													href="Student_Award_Info_selectItemInfo?itemId=${item.stuItemId}">详情</a></td>
											</tr>
										 </c:forEach>
									</tbody>
								</table>
								<label class="lable-add"><a
									href="Student_Award_Add_selectItemEvaType">添加</a></label>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		
		function selectStudentItems(page) {
			$("#studentItemList tbody").html("");
			$
					.getJSON(
							"Json_selectItem",
							{
								page : page
							},
							function(data) {
								$("#page").html(data.siPageBean.page);
								$("#totalPage").html(data.siPageBean.totalPage);
								$
										.each(
												data.siPageBean.list,
												function(i, value) {

													$("#studentItemList")
															.append(
																	"<tr><td>"
																			+ value.itemNum
																			+ "</td><td>"
																			+ value.itemName
																			+ "</td><td>"
																			+ value.itemState
																			+ "</td><td>"
																			+ value.itemScore
																			+ "</td><td><a onclick='return confirm('确认删除？')' href='Student_Portfolio_Activity_deleteItem?itemId="
																			+ value.stuItemId
																			+ "'>删除</a></td><td><a href='Student_Award_Info_selectItemInfo?itemId="
																			+ value.stuItemId
																			+ "'>详情</a></td></tr>");
												});
							});
		}
	</script>
</body>
</html>
