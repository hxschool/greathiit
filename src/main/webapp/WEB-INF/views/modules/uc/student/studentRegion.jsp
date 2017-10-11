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
		<li   class="active"><a href="${ctx}/uc/ucStudent/region">全国招生统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/department">学院统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/major"> 专业统计</a></li>
		<li><a href="${ctx}/uc/ucStudent/edu">学历统计</a></li>
	</ul>
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
    <div id="main" style="width: 1024px;height:530px;margin:0 auto; "></div>

    <script type="text/javascript">
		    var xAxData = [];
		    var serDataBoy = [];  
		    var serDataGril = [];  
		    var labData = [];  
		    <c:forEach var="item" items="${list}" varStatus="status">
		    xAxData.push('${item.NAME}' || ""); 
		    labData.push('${item.LABEL}' || ""); 
		    <c:choose>
			    <c:when test="${item.LABEL=='1'}"> 
			    serDataBoy.push({  
			        name: '${item.NAME}',  
			        value: ${item.TOTAL} || 0  
			    }); 
			    </c:when>
			    <c:otherwise>
			    serDataGril.push({  
			        name: '${item.NAME}',  
			        value: ${item.TOTAL} || 0  
			    }); 
			    </c:otherwise>
			</c:choose>
		   
			</c:forEach> 
        // 基于准备好的dom，初始化echarts实例
       
        			var myChart = echarts.init(document.getElementById('main'));
		
        // 指定图表的配置项和数据
					option = {
					    title : {
					        text: '行政区招生统计',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item'
					    },
					   
					    legend: {
					        orient: 'vertical',
					        x:'left',
					        data:['男','女']
					    },
					    dataRange: {
					        min: 0,
					        max: 200,
					        color:['#E01F54','#001852','#f5e8c8','#b8d2c7','#c6b38e',
					            '#a4d8c2','#f3d999','#d3758f','#dcc392','#2e4783',
					            '#82b6e9','#ff6347','#a092f1','#0a915d','#eaf889',
					            '#6699FF','#ff6666','#3cb371','#d5b158','#38b6b6'
					        ],
					        x: 'left',
					        y: 'bottom',
					        text:['高','低'],           // 文本，默认为数值文本
					        calculable : true
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
					    
					    roamController: {
					        show: true,
					        x: 'right',
					        mapTypeControl: {
					            'china': true
					        }
					    },
					    series : [
					        {
					            name: '男',
					            type: 'map',
					            mapType: 'china',
					            roam: false,
					            itemStyle:{
					                normal:{label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					            
					            data:serDataBoy
					        },
					        {
					            name: '女',
					            type: 'map',
					            mapType: 'china',
					            itemStyle:{
					                normal:{ areaColor: '#dec313',label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					            data:serDataGril
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