<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="include.jsp"%>
<head>
<title>Bootstrap 实例 - 默认的导航栏</title>
<link href="${ctx}/jslib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${ctx}/jslib/jquery-easyui-1.4.1/jquery.min.js"></script>
<script src="${ctx}/jslib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>
<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="${ctx}/index.jsp">日志监控平台</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> 拆分送检 <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="logManager/logCfsjOneDate.jsp">单日分析</a>
					<li><a href="printLog/totalList.jsp">时间范围分析</a></li>
				</ul></li>
			<li><a href="#">开派奖</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> 截期出票速度 <b class="caret"></b> </a>
				<ul class="dropdown-menu">
					<li><a href="#">jmeter</a></li>
					<li><a href="test/List.jsp">EJB</a></li>
					<li><a href="#">Jasper Report</a></li>
					<li class="divider"></li>
					<li><a href="#">分离的链接</a></li>
					<li class="divider"></li>
					<li><a href="#">另一个分离的链接</a></li>
				</ul>
			</li>
		</ul>
	</div>
</nav>
