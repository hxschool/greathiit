<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<div id="content" class="row-fluid">
		<div id="left" class="accordion-group">
			<div class="accordion-heading">
		    	<a class="accordion-toggle">教学大纲</a>
		    </div>
			
			 <div id="ztree" class="ztree"></div>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="officeContent" src="${ctx}/course/course/teacherCourse_Modify_1_selectBasicInfByCursId?id=${course.id}" width="100%" height="91%" frameborder="0"></iframe>
		</div>
	</div>
	<script type="text/javascript">
	
		var data = {
		    teacher:[
		    	 {id:"teacherCourse_Modify_1_selectBasicInfByCursId",name:"课程基本信息",pId:"0"},
		    	 {id:"teacherCourse_Modify_2_selectTchingTargetByCursId",name:"课程教学目标",pId:"0"},
		    	 {id:"teacherCourse_Modify_3_selectSpeContentCursId",name:"课程具体内容",pId:"0"},
		    	 {id:"teacherCourse_Modify_4_selectTchingModeByCursId",name:"教学方式",pId:"0"},
		    	 {id:"teacherCourse_Modify_7_selectBookByCursId",name:"参考教材",pId:"0"},
		    	 {id:"teacherCourse_Modify_8_selectNoteByCursId",name:"说明",pId:"0"}
		    ]
		}
		
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
				callback:{onClick:function(event, treeId, treeNode){
						var id = treeNode.id == '0' ? '' :treeNode.id;
						
						//$('#officeContent').attr("src","${ctx}/sys/user/list?office.id="+id+"&office.name="+treeNode.name);
						$('#officeContent').attr("src","${ctx}/course/course/"+id+"?id=${course.id}");
						
					}
				}
			};
			
			function refreshTree(){
				var teacherList = data.teacher;
				 var treeData = [];
				    treeData.push({id:"root",name:"${course.cursName}",pId:null});
				    for(var i=0,il=teacherList.length;i<il;i++){
				        teacherList[i].pId = "root";
				        treeData.push(teacherList[i]);
				    }
				$.fn.zTree.init($("#ztree"), setting, treeData).expandAll(true);
			}
			refreshTree();
		
		var leftWidth = 180; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
			mainObj.css("width","auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			$(".ztree").width(leftWidth - 10).height(frameObj.height() - 46);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>