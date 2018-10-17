<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}-树状图</title>
	
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.min.js"></script>
    <script src="${ctxStatic}/echart/echarts.min.js"></script>
	</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width:100%;height:800px;"></div>

<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
		myChart.showLoading();
$.get('${ctx}/sys/information/greathiit/greathiit.json', function (data) {
    myChart.hideLoading();

    echarts.util.each(data.children, function (datum, index) {
        //index % 2 === 0 && (datum.collapsed = true);
    });

    myChart.setOption(option = {
    		  title: {
      	        text: '哈尔滨信息工程学院-树状图',
      	        textStyle: {
      	            fontSize: 14,
      	            align: 'center'
      	        },
      	        subtextStyle: {
      	            align: 'center'
      	        },
      	    },
      	    tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'
        },
        series: [
            {
                type: 'tree',

                data: [data],

                top: '1%',
                left: '7%',
                bottom: '1%',
                right: '20%',

                symbolSize: 7,

                label: {
                    normal: {
                        position: 'left',
                        verticalAlign: 'middle',
                        align: 'right',
                        fontSize: 9
                    }
                },

                leaves: {
                    label: {
                        normal: {
                            position: 'right',
                            verticalAlign: 'middle',
                            align: 'left'
                        }
                    }
                },

                expandAndCollapse: true,
                animationDuration: 550,
                animationDurationUpdate: 750
            }
        ]
    });
});
</script>
</body>
</html>