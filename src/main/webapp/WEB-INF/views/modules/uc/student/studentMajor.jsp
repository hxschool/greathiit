<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据统计</title>
	<meta name="decorator" content="default"/>
	
</head>
<body>
	<!-- 
	<ul class="nav nav-tabs">
				<li><a href="${ctx}/uc/ucStudent/group">招生统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/sex">性别统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/region">全国招生统计</a></li>
		<li ><a href="${ctx}/uc/ucStudent/department">学院统计</a></li>
		<li  class="active"><a href="${ctx}/uc/ucStudent/major">专业统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/edu">学历统计</a></li>
	</ul>
	 -->
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/student/" method="post" class="breadcrumb form-search">
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
    <div id="main" style="width: 100%;height:600px;margin:0 auto; "></div>
    
    <script type="text/javascript">
		    var xAxData = [];
		    var serData = [];  
		    <c:forEach var="item" items="${list}" varStatus="status">
		    xAxData.push('${item.NAME}' || ""); 
		    serData.push(${item.TOTAL} || 0  ); 
			</c:forEach> 
        // 基于准备好的dom，初始化echarts实例
           var myChart = echarts.init(document.getElementById('main'));
		
        // 指定图表的配置项和数据


			option = {
			    title : {
			        text: '专业统计'
			        
			    },
			      tooltip : {
			            trigger : 'axis',
			            showDelay : 0, // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
			            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			            }
			        },
			    legend: {
			        data:['专业统计']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data :xAxData,
			            axisLabel: {  
			                interval:0,  
			                rotate:40  
			             }  
			        }
			    ],
			    grid: {  
			        left: '10%',  
			        bottom:'35%',
			        y2:120
			        },  
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel : {
	                         formatter: '{value}'
	                     }
			        }
			    ],
			    
			    series : [
			        
			        { itemStyle: {
			            normal: {
			                color: function(params) {
			                    // build a color map as your need.
			                    var colorList = [
			                      '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
			                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
			                       '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
			                       '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
			                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
			                       '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
			                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
			                       '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
			                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
			                       '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
			                       '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD'
			                    ];
			                    return colorList[params.dataIndex]
			                },
			                label: {
			                    show: true,
			                    position: 'top',
			                    formatter: '{b}\n{c}'
			                }
			            }
			        },
			            name:'专业',
			            type:'bar',
			            data:serData
			        }
			    ]
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