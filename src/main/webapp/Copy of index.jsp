<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>monitor</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="jslib/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="jslib/jquery-easyui-1.4.1/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="jslib/jquery-easyui-1.4.1/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="jslib/sunUtil.js"></script>
</head>

<body class="easyui-layout">
	<script src="jslib/echarts/echarts.js"></script>
	<div data-options="region:'north'" style="height:100px;">监控平台</div>
	<div data-options="region:'south'" style="height:20px;"></div>

	<div data-options="region:'west'" style="width:250px;">
		<jsp:include page="layout/west.jsp"></jsp:include>
	</div>
	<div data-options="region:'east',title:'east',split:true" style="width:100px;"></div>
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<jsp:include page="layout/center.jsp"></jsp:include>
	</div>

	<jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/reg.jsp"></jsp:include>
</body>
</html>
