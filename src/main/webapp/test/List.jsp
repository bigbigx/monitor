<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../index.jsp"%>
<head>
<title>Bootstrap 实例 - 默认的导航栏</title>
</head>
<div class="container">
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
			下拉菜单 <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
			<li role="presenttation" class="dropdown-header">----字母---</li>
			<li><a href="#" role="menuitem">A</a>
			</li>
			<li><a href="#" role="menuitem">B</a>
			</li>
			<li><a href="#" role="menuitem">C</a>
			</li>
			<li><a href="#" role="menuitem">D</a>
			</li>
			<li><a href="#" role="menuitem">E</a>
			</li>
			<li role="presenttation" class="dropdown-header">---数字---</li>
			<li><a href="#" role="menuitem">1</a>
			</li>
			<li><a href="#" role="menuitem">2</a>
			</li>
			<li><a href="#" role="menuitem">3</a>
			</li>
			<li><a href="#" role="menuitem">4</a>
			</li>
			<li><a href="#" role="menuitem">5</a>
			</li>
		</ul>
	</div>
	<div class="dropdown pull-right">
		<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
			下拉菜单 <span class="caret"></span>
		</button>
		<ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu1">
			<li><a href="#" role="menuitem">A</a>
			</li>
			<li><a href="#" role="menuitem">B</a>
			</li>
			<li><a href="#" role="menuitem">C</a>
			</li>
			<li><a href="#" role="menuitem">D</a>
			</li>
			<li><a href="#" role="menuitem">E</a>
			</li>
			<li role="presenttation" class="divider"></li>
			<li><a href="#" role="menuitem">1</a>
			</li>
			<li><a href="#" role="menuitem">2</a>
			</li>
			<li><a href="#" role="menuitem">3</a>
			</li>
			<li class="disabled"><a href="#" role="menuitem">4</a>
			</li>
			<li><a href="#" role="menuitem">5</a>
			</li>
		</ul>
	</div>
</div>
