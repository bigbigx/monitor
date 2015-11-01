<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$.ajaxSettings.async = false;  
	    $.ajax({
	    	type:"post",
	    	data:{"logType":"check","timeType":"24","valid":"1","sort": "logTime","order" : "desc"},
	    	url: '${pageContext.request.contextPath}/operateLogAction!getDateList.action', 
	    	success : function(data){
	    		//console.info("get json:"+data);
	    		var obj = jQuery.parseJSON(data);
	    		$('#log_checkldl_getDateListSelect').append("<option value='0' selected='true'>请选择日期</option>");
	    		var mdate = obj.dateValue;
	    		//console.info("get mdate:"+mdate);
	    		$.each(mdate,function(i){
	    			//console.info("this json:"+mdate[i]);
	    			$('#log_checkldl_getDateListSelect').append($("<option/>").text(mdate[i].logTime.toString().substring(0,10)).attr("value",mdate[i].logTime.toString().substring(0,10)));
	    		})
	    }});
	}
	);
	 $('#log_checkldl_getDateListSelect').change(function(){
		 $("#log_checkldl_checkldlD2").show();
		 if($('#log_checkldl_getDateListSelect option:selected').val()==0){
			 $('#log_checkldl_checkldldatagrid').datagrid('loadData', { total: 0, rows: [] });
			 $('#log_checkldl_getTypeSelect').val(0);
			 $('#log_checkldl_checkldlD2').hide();
			 $('#log_checkldl_checkldldatagrid').hide();
		 }
     });
	 $('#log_checkldl_getTypeSelect').change(function(){
		 if($('#log_checkldl_checkldlselect option:selected').val()!=0){//彩种
			 if($('#log_checkldl_getTypeSelect option:selected').val()==1){
				 getcheckLotteryTypeDataGrid();	 	
			 }else if($('#log_checkldl_getTypeSelect option:selected').val()==2){//终端
				 getcheckTerminalDataGrid();
			 }
     	}else{
     		$('#log_checkldl_checkldldatagrid').datagrid('loadData', { total: 0, rows: [] });
     	}
     });
	 function getcheckTerminalDataGrid(){
			$('#log_checkldl_checkldldatagrid').datagrid({
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
					"queryName":'terminalIdPara',
					"logType":'check',
					"logTime":$('#log_checkldl_getDateListSelect option:selected').text()
	    		},
				frozenColumns : [ [ {
					field : 'analyName',
					title : '终端号',
					width : 150,
					checkbox : false
				} ,//注意，建议加上width，因为如果不写，将会延迟加载时间
				{
					field : 'analyNum',
					title : '终端发送数量',
					width : 150,
					sortable : true
				}] ],
				columns : [ [{
					field : 'analyAllTime',
					title : '终端总耗时(ms)',
					width : 150,
				},{
					field : 'analyAveTime',
					title : '终端平均耗时(ms)',
					width : 150
				}, ] ],
			//toolbar:'#admin_yhgl_toolbar'
			});
		};
	function getcheckLotteryTypeDataGrid(){
		$('#log_checkldl_checkldldatagrid').datagrid({
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
				"logType":'check',
				"logTime":$('#log_checkldl_getDateListSelect option:selected').text()
    		},
			frozenColumns : [ [ {
				field : 'analyName',
				title : '彩种',
				width : 150,
				checkbox : false
			} ,//注意，建议加上width，因为如果不写，将会延迟加载时间
			{
				field : 'analyNum',
				title : '方案总数',
				width : 150,
				sortable : true
			}] ],
			columns : [ [{
				field : 'analyAllTime',
				title : '方案总耗时(ms)',
				width : 150,
			},{
				field : 'analyAveTime',
				title : '方案平均耗时(ms)',
				width : 150
			}, ] ],
		//toolbar:'#admin_yhgl_toolbar'
		});
	};
</script>

<form id="log_checkldl_checkldlForm" method="post">
	请选择时间:<select id="log_checkldl_getDateListSelect" ></select>
	<div id = "log_checkldl_checkldlD2" style="display:none">
		需分析类型:
		<select id="log_checkldl_getTypeSelect" >
	 		<option value="0">请选择类型</option>
	 		<option value="1">彩种分析</option>
	 		<option value="2">终端分析</option>
		</select>
	</div>
</form>
<table id="log_checkldl_checkldldatagrid"></table>