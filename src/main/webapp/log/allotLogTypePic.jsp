<%@ page language="java" pageEncoding="UTF-8"%>
<body>
    <p>请选择时间展现彩种分布情况:</p>
    <form id="log_allotltp_allotltpform" method="post">
    	各彩种平均用时分析:<select id="log_allotltp_allotltpselect" ></select>
    </form>
     <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="log_allotltp_allotltppanel" style="height:400px"></div>
   <script type="text/javascript">
        $(function(){
        	 $.ajaxSettings.async = false;  
             $.ajax({
             	type:"post",
             	data:{"logType":"allot","timeType":"24","valid":"1","sort": "logTime","order" : "desc"},
             	url: '${pageContext.request.contextPath}/operateLogAction!getDateList.action', 
             	success : function(data){
             		//console.info("get json:"+data);
             		var obj = jQuery.parseJSON(data);
             		$('#log_allotltp_allotltpselect').append("<option value='0' selected='true'>请选择日期</option>");
             		var mdate = obj.dateValue;
             		//console.info("get mdate:"+mdate);
             		$.each(mdate,function(i){
             			//console.info("this json:"+mdate[i]);
             			$('#log_allotltp_allotltpselect').append($("<option/>").text(mdate[i].logTime.toString().substring(0,10)).attr("value",mdate[i].logTime.toString().substring(0,10)));
             			//$('#log_allotltp_allotltpselect').append("<option value="+mdate[i].logTime+">"+mdate[i].logTime.toString().substring(0,10)+"</option>");
             		})
             }});
        }
        );
        $('#log_allotltp_allotltpselect').change(function(){
        	if($('#log_allotltp_allotltpselect option:selected').val()!=0){
        		getEcharts();
        	}else{
        		$('#log_allotltp_allotltppanel').empty();
        	}
        });
        function getEcharts(){
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
                    'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
                ],
                function (ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('log_allotltp_allotltppanel')); 
                    var _lotteryTypesNames = [];
                    var _lotteryTypePara = [];
                    // 同步执行  
                    $.ajaxSettings.async = false;  
                    $.ajax({
                    	type:"post",
                    	url: '${pageContext.request.contextPath}/operateLogAction!datagrid4Pie.action', 
                    	data:{"logType":"allot","timeType":"24","valid":"1","logTime":$('#log_allotltp_allotltpselect option:selected').text()},
                    	success: function(data){
    						var obj = jQuery.parseJSON(data);
    						_lotteryTypesNames = obj.lotteryTypesNames;
    	           			_lotteryTypePara = obj.lotteryTypePara;
                    }});
                    var option = {
                    	    title : {
                    	        text: '分票日志彩种分布',
                    	        subtext: '彩票种类',
                    	        x:'center'
                    	    },
                    	    tooltip : {
                    	        trigger: 'item',
                    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    	    },
                    	    legend: {
                    	        orient : 'vertical',
                    	        x : 'left',
                    	        data:_lotteryTypesNames
                    	    },
                    	    toolbox: {
                    	        show : true,
                    	        feature : {
                    	            mark : {show: true},
                    	            dataView : {show: true, readOnly: false},
                    	            magicType : {
                    	                show: true, 
                    	                type: ['pie', 'funnel'],
                    	                option: {
                    	                    funnel: {
                    	                        x: '25%',
                    	                        width: '50%',
                    	                        funnelAlign: 'left',
                    	                        max: 1548
                    	                    }
                    	                }
                    	            },
                    	            restore : {show: true},
                    	            saveAsImage : {show: true}
                    	        }
                    	    },
                    	    calculable : true,
                    	    series : [
                    	        {
                    	            name:'数据信息',
                    	            type:'pie',
                    	            radius : '75%',
                    	            center: ['50%', '60%'],
                    	            data:_lotteryTypePara
                    	        }
                    	    ]
                    	};
            
                    // 为echarts对象加载数据 
                    myChart.setOption(option); 
                }
            );
        }
    </script>
</body>