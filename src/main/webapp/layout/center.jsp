<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function addTab(opts){
		var t = $('#layout_center_tabs');
		if(t.tabs('exists',opts.title)){
			t.tabs('select',opts.title);
		}else{
			t.tabs('add',opts);
		}
	}
</script>
<div id="layout_center_tabs" class="easyui-tabs" data-options="fit:true,border:false">
	<div title="首頁">欢迎登陆乐彩监控平台</div>
</div>