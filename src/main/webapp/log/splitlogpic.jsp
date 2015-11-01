<%@ page language="java" pageEncoding="UTF-8"%>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="log_splitlogpic_splitlogpicpanel" style="height:400px"></div>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'jslib/echarts'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('log_splitlogpic_splitlogpicpanel')); 
                var logTime = [];  
                var useTime = [];
                // 同步执行  
                $.ajaxSettings.async = false;  
                $.ajax({
                	type:"post",
                	url: '${pageContext.request.contextPath}/operateLogAction!datagrid4Pic.action', 
                	data:{"logType":"split","timeType":"24","valid":"1"},
                	success: function(data){
						var obj = jQuery.parseJSON(data);
	           			logTime = obj.logTime;
                     	useTime = obj.useTime;
                }});
                var option = {
                	title : {
                		text:'拆票24小时平均用时',
                		x:'center'
                	},
                    tooltip: {
                        show: true
                    },
                    legend: {
                    	data:['每个方案平均用时ms'],
                    	x : 'left'
                    },
                    xAxis : [
                        {
                        	name : '日期',
                            type : 'category',
                            data : logTime
                        }
                    ],
                    yAxis : [
                        {
                        	name : '平均用时ms',
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"每个方案平均用时ms",
                            "type":"line",
                            "data":useTime
                        }
                    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
</body>
