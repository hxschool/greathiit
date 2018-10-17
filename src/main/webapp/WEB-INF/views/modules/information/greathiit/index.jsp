<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}-拓扑图</title>
	
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.min.js"></script>
    <script src="${ctxStatic}/echart/echarts.min.js"></script>
	</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width:100%;height:800px;"></div>

<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

$.get('${ctx}/sys/information/greathiit/greathiit.json', function (data) {
   

    myChart.setOption(option = {
    	    title: {
    	        text: '哈尔滨信息工程学院拓扑图',
    	        
    	        textStyle: {
    	            fontSize: 14,
    	            align: 'center'
    	        },
    	        subtextStyle: {
    	            align: 'center'
    	        },
    	    },
    	    series: {
    	        type: 'sunburst',
    	        highlightPolicy: 'ancestor',
    	        data: [data],
    	        radius: [0, '95%'],
    	        sort: null,
    	        levels: [{}, {
    	            r0: '15%',
    	            r: '35%',
    	            itemStyle: {
    	                borderWidth: 2
    	            },
    	            label: {
    	                rotate: 'tangential'
    	            }
    	        }, {
    	            r0: '35%',
    	            r: '70%',
    	            label: {
    	                align: 'right'
    	            }
    	        }, {
    	            r0: '70%',
    	            r: '72%',
    	            label: {
    	                position: 'outside',
    	                padding: 3,
    	                silent: false
    	            },
    	            itemStyle: {
    	                borderWidth: 3
    	            }
    	        }]
    	    }
    	});
});
</script>
</body>
</html>