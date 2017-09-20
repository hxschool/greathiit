<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据统计</title>
	<meta name="decorator" content="default"/>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/uc/ucStudent/group">招生统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/sex">性别统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/region">全国招生统计</a></li>
		<li class="active"><a href="${ctx}/uc/ucStudent/department">学院统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/major"> 专业统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/edu">学历统计</a></li>

	</ul>
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/ucStudent/" method="post" class="breadcrumb form-search">
		<div style="margin-top:8px;">
			<label>日期范围：&nbsp;</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${log.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			&nbsp;<label for="exception"><input id="exception" name="exception" type="checkbox"${log.exception eq '1'?' checked':''} value="1"/>只查询异常信息</label>
			&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 800px;height:600px;margin:0 auto; "></div>
    
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
        			var myChart = echarts.init(document.getElementById('main'));
		
        // 指定图表的配置项和数据
					option = {
						title : {
							text : '学院统计',
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
							name : '学院统计',
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