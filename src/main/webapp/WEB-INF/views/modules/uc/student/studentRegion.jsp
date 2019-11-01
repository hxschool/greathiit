<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据统计</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/echart/echarts-all.js" type="text/javascript"></script>
</head>
<body>
	
	
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/uc/student/group">招生统计</a></li>
		<li><a href="${ctx}/uc/student/sex">性别统计</a></li>
		<li   class="active"><a href="${ctx}/uc/student/region">全国招生统计</a></li>
		<li><a href="${ctx}/uc/student/department">学院统计</a></li>
		<li><a href="${ctx}/uc/student/major"> 专业统计</a></li>
		<li><a href="${ctx}/uc/student/edu">学历统计</a></li>
	</ul>
	 
	<form:form id="searchForm" modelAttribute="ucStudent" action="${ctx}/uc/student/region" method="post" class="breadcrumb form-search">
		<div style="margin-top:8px;">
			<label>录取年份：&nbsp;</label><input id="year" name="year" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="${ucStudent.year}" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
			
			&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
			<button type="reset" class="btn btn-default ">重置</button>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 1024px;height:530px;margin:0 auto; "></div>
	<div class="container-fluid">
		<c:set var="sum" value="0" />
		<table id="contentTable"
			class="table table-striped table-bordered table-hover ">
			<thead>
				<tr>
					<th width="20px;">序号</th>
					<th>省份名称</th>
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

		    var serDataBoy = [];  

		    <c:forEach var="item" items="${list}" varStatus="status">

		    serDataBoy.push({  
		        name: '${item.NAME}',  
		        value: ${item.TOTAL} || 0  
		    }); 
			</c:forEach> 
        // 基于准备好的dom，初始化echarts实例
       
        			var myChart = echarts.init(document.getElementById('main'));
		
        // 指定图表的配置项和数据
					option = {
					    title : {
					        text: '${ucStudent.year}行政区招生统计',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item'
					    },
					   
					    dataRange: {
					        min: 0,
					        max: 3000,
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
					            name: '省份',
					            type: 'map',
					            mapType: 'china',
					            roam: false,
					            itemStyle:{
					                normal:{label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					           
					            data:serDataBoy
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