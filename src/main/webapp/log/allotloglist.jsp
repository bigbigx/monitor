<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
		$('#log_allotlog_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/operateLogAction!datagrid.action',
			fit : true,
			fitColumns : true,//用于自适应列宽，列比较少的时候用这个合适，能顶满格
			border : false,
			pagination : true,//列表下方翻页工具条
			idField : 'id',//主键，建议写上，用途是如果选择了多行表格数据，可以返回选择的行数，否则只能返回选择了1行；可用于跨页选择
			pageSize : 10,//如果不想用翻页工具条默认的初始化显示10条，就用这个pageSize参数来定义，但是必须与pageList一起使用，选择他数组里存在的,否则将不生效
			pageList : [ 5, 10, 20, 30, 40, 50 ],//翻页共具条可显示下拉选择显示的条数，默认不用写，是[10,20,30,40,50]
			//rownumbers:true,//序号，这个如果要加行号，将会延迟加载时间
			sortName : 'logTime',//初始化时想排序，字段field的值
			sortOrder : 'desc',//默认asc升序
			checkOnSelect : false,//为true时选中一行时，多选框也被选中
			selectOnCheck : false,//为true选中多选框时，该行也被选中
			queryParams:{
				logType:'allot'         
    		},
			frozenColumns : [ [ {
				field : 'logType',
				title : '操作类型',
				width : 150,
				checkbox : false
			} ,//注意，建议加上width，因为如果不写，将会延迟加载时间
			{
				field : 'allPlanTotal',
				title : '方案总数',
				width : 150,
				sortable : true
			}] ],
			columns : [ [{
				field : 'allCostTime',
				title : '所用总时(ms)',
				width : 150,
			},{
				field : 'averageUseTime',
				title : '平均用时(ms)',
				width : 150
			},
			{
				field : 'timeType',
				title : '时间区域(h)',
				width : 150
			}, {
				field : 'recordNum',
				title : '记录条数',
				width : 150
			},{
				field : 'logTime',
				title : '日志日期',
				width : 150,
				formatter : function(value){
					return value.toString().substring(0,10);
				}
			} ] ]
		//toolbar:'#admin_yhgl_toolbar'
		});
	});
</script>
<table id="log_allotlog_datagrid"></table>
