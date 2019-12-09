<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据统计</title>
	<meta name="decorator" content="default"/>
		<script type="text/javascript">
	$(document).ready(function() {
		
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$("#searchForm").attr("action", "${ctx}/uc/student/eduExport");
					$("#searchForm").submit();
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		});
	})
	</script>
</head>
<body>
	
	<ul class="nav nav-tabs">
				<li><a href="${ctx}/uc/student/group">招生统计</a></li>
		<li><a href="${ctx}/uc/student/sex">性别统计</a></li>
		<li><a href="${ctx}/uc/student/region">全国招生统计</a></li>
		<li ><a href="${ctx}/uc/student/department">学院统计</a></li>
		<li><a href="${ctx}/uc/student/major"> 专业统计</a></li>
		<li class="active"><a href="${ctx}/uc/student/edu">学历统计</a></li>
	</ul>
	 
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/student/edu" method="post" class="breadcrumb form-search">
		<div style="margin-top:8px;">
			<label>录取年份：&nbsp;</label><input id="year" name="year" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${ucStudent.year}" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
			
			&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
			
			<input
				id="btnExport" class="btn btn-primary" type="button" value="导出" />&nbsp;&nbsp;
			
			<button type="reset" class="btn btn-default ">重置</button>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 800px;height:600px;margin:0 auto; "></div>
   <div class="container-fluid">
		<c:set var="sum" value="0" />
		<table id="contentTable"
			class="table table-striped table-bordered table-hover ">
			<thead>
				<tr>
					<th width="20px;">序号</th>
					<th>学历名称</th>
					<th>录取人数</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${list}" var="item" varStatus="status">
				 	<c:set var="sum" value="${sum+item.TOTAL}"/>
					<tr>

						<td>${status.index +1}</td>
						<td>${item.NAME}</td>
						<td>${item.TOTAL}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>

						<td colspan="2">录取总人数</td>
						<td>${sum}</td>
					</tr>
			</tfoot>
		</table>
	</div>
    <script type="text/javascript">
		    var xAxData = [];
		    var serData = [];  
		    <c:forEach var="item" items="${list}" varStatus="status">
		    xAxData.push('${item.NAME}' || ""); 
		    serData.push({  
		        name: '${item.NAME}',  
		        value: ${item.TOTAL} || 0  
		    }); 
			</c:forEach> 
        // 基于准备好的dom，初始化echarts实例
        			var myChart = echarts.init(document.getElementById('main'),'shine');
		
        // 指定图表的配置项和数据
					option = {
						title : {
							text : '学历统计',
							//subtext : '纯属虚构',
							x : 'right'
						},
						 toolbox: {
						        show: true,
						        orient : 'vertical',
						        x: 'right',
						        y: 'center',
						        feature : {
						            mark : {show: true},
						            magicType : {
			                            show: true,
			                            type: ['pie', 'funnel']
			                        },
						            dataView : {show: true, readOnly: false},
						            restore : {show: true},
						            saveAsImage : {show: true}
						        },
						        
						    },
					    toolbox: {
					        show: true,
					        orient : 'vertical',
					        x: 'right',
					        y: 'center',
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
						tooltip : {
							trigger : 'item',
							formatter : "{a} <br/>{b} : {c} ({d}%)"
						},
						legend : {
							 orient: 'horizontal',      // 布局方式，默认为水平布局，可选为：
                             // 'horizontal' ¦ 'vertical'
							  x: 'left',               // 水平安放位置，默认为全图居中，可选为：
							  y: 'top',                  // 垂直安放位置，默认为全图顶端，可选为：
							  itemWidth: 20,             // 图例图形宽度
							  itemHeight: 14,            // 图例图形高度
							  textStyle: {
							      color: '#333'          // 图例文字颜色
							  },
							data : xAxData
						},
						series : [ {
							name : '学历统计',
							type : 'pie',
							radius : '75%',
							center : [ '50%', '50%' ],
							data :serData,
							
							itemStyle : {
								emphasis : {
									shadowBlur : 10,
									shadowOffsetX : 0,
									shadowColor : 'rgba(0, 0, 0, 0.5)'
								},
								normal: {label:{  
					                show:true,  
					                formatter:'{b} : {c} ({d}%)'  
					            },  
					            labelLine:{show:true}}
							}
						
						} ]
					};

					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);
					
					myChart.on("dblclick", function (param){               
			                alert(param.dataIndex);
			                alert(param.value);
			            });


				</script>
				
				
</body>
</html>