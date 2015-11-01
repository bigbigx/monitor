<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function importDBFun(){
		$('#wjrk_splitlogindb_splitlogindbsearch').form('submit',{
			url:'${pageContext.request.contextPath}/operateLogAction!addLog.action',
			queryParams:{
				logType:'split',
				timeType:24
    		},
			success : function(data){
				var obj = jQuery.parseJSON(data);
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		})
	};
	function clearFun() {
		$('#wjrk_splitlogindb_splitlogindblayout input[name=filePath]').val('');
	};
</script>
<div id="wjrk_splitlogindb_splitlogindblayout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'拆票入库文件',border:false" style="height:100px;">
		<form id="wjrk_splitlogindb_splitlogindbsearch">
			文件路径:<input name="filePath" style="width:400px"/> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onClick="importDBFun()">提交</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:true" onClick="clearFun()">清空</a>
		</form>
	</div>
</div>