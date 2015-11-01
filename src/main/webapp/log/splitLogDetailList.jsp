<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$.ajaxSettings.async = false;  
	    $.ajax({
	    	type:"post",
	    	data:{"logType":"split","timeType":"24","valid":"1","sort": "logTime","order" : "desc"},
	    	url: '${pageContext.request.contextPath}/operateLogAction!getDateList.action', 
	    	success : function(data){
	    		//console.info("get json:"+data);
	    		var obj = jQuery.parseJSON(data);
	    		$('#log_splitldl_splitldlselect').append("<option value='0' selected='true'>请选择日期</option>");
	    		var mdate = obj.dateValue;
	    		//console.info("get mdate:"+mdate);
	    		$.each(mdate,function(i){
	    			//console.info("this json:"+mdate[i]);
	    			$('#log_splitldl_splitldlselect').append($("<option/>").text(mdate[i].logTime.toString().substring(0,10)).attr("value",mdate[i].logTime.toString().substring(0,10)));
	    		})
	    }});
	}
	);
	 $('#log_splitldl_splitldlselect').change(function(){
		 if($('#log_splitldl_splitldlselect option:selected').val()!=0){
			 getsplitldlDataGrid();
     	}else{
     		$('#log_splitldl_splitldldatagrid').datagrid('loadData', { total: 0, rows: [] });
     	}
     		
     });
	function getsplitldlDataGrid(){
		$('#log_splitldl_splitldldatagrid').datagrid({
			url : '${pageContext.request.contextPath}/operateLogAction!datagridWithDetailAnalysis.action',
			fit : true,
			fitColumns : true,//用于自适应列宽，列比较少的时候用这个合适，能顶满格
			border : false,
			pagination : true,//列表下方翻页工具条
			idField : 'id',//主键，建议写上，用途是如果选择了多行表格数据，可以返回选择的行数，否则只能返回选择了1行；可用于跨页选择
			pageSize : 10,//如果不想用翻页工具条默认的初始化显示10条，就用这个pageSize参数来定义，但是必须与pageList一起使用，选择他数组里存在的,否则将不生效
			pageList : [ 5, 10, 20, 30, 40, 50 ],//翻页共具条可显示下拉选择显示的条数，默认不用写，是[10,20,30,40,50]
			//rownumbers:true,//序号，这个如果要加行号，将会延迟加载时间
			//sortName : 'logTime',//初始化时想排序，字段field的值
			//sortOrder : 'desc',//默认asc升序
			checkOnSelect : false,//为true时选中一行时，多选框也被选中
			selectOnCheck : false,//为true选中多选框时，该行也被选中
			queryParams:{
				"queryName":'lotteryTypePara',
				"logType":'split',
				"logTime":$('#log_splitldl_splitldlselect option:selected').text()
    		},
			frozenColumns : [ [ {
				field : 'analyName',
				title : '彩种',
				width : 150,
				checkbox : false
			},{
				field : 'analyTicketCount',
				title : '该彩种拆票票数',
				width : 150,
			} ,//注意，建议加上width，因为如果不写，将会延迟加载时间
			{
				field : 'analyNum',
				title : '该彩种拆票记录条数',
				width : 150,
				sortable : true
			}] ],
			columns : [ [{
				field : 'analyAllTime',
				title : '该彩种拆票记录总耗时(ms)',
				width : 150,
			},{
				field : 'analyAveTime',
				title : '该彩种拆票记录平均耗时(ms)',
				width : 150
			}, ] ],
		//toolbar:'#admin_yhgl_toolbar'
		});
	};
</script>
 <p>请选择时间展现彩种分布情况:</p>
 <form id="log_splitldl_splitldlform" method="post">
 	各彩种平均用时:<select id="log_splitldl_splitldlselect" ></select>
 </form>
<table id="log_splitldl_splitldldatagrid"></table>