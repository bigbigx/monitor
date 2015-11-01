<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_yhgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/userAction!datagrid.action',
			fit : true,
			fitColumns : true,//用于自适应列宽，列比较少的时候用这个合适，能顶满格
			border : false,
			pagination : true,//列表下方翻页工具条
			idField : 'id',//主键，建议写上，用途是如果选择了多行表格数据，可以返回选择的行数，否则只能返回选择了1行；可用于跨页选择
			pageSize : 10,//如果不想用翻页工具条默认的初始化显示10条，就用这个pageSize参数来定义，但是必须与pageList一起使用，选择他数组里存在的,否则将不生效
			pageList : [ 5, 10, 20, 30, 40, 50 ],//翻页共具条可显示下拉选择显示的条数，默认不用写，是[10,20,30,40,50]
			//rownumbers:true,//序号，这个如果要加行号，将会延迟加载时间
			sortName : 'name',//初始化时想排序，字段field的值
			sortOrder : 'asc',//默认asc升序
			checkOnSelect : false,//为true时选中一行时，多选框也被选中
			selectOnCheck : false,//为true选中多选框时，该行也被选中
			
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					editFun();
				}
			} ],
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true
			} ,//注意，建议加上width，因为如果不写，将会延迟加载时间
			{
				field : 'name',
				title : '名称',
				width : 150,
				sortable : true
			}] ],
			//frozenColumns:[[{field:'name',title:'名称',width:150},]],//冻结name字段的列，随着拖动而不变
			onLoadSuccess : function() {
				$(this).datagrid('freezeRow', 0).datagrid('freezeRow', 1);
			},//冻结第1、2行
			columns : [ [ /**{
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true
			//hidden:true隐藏
			}, */{
				field : 'pwd',
				title : '密码',
				width : 150,
				//sortable : true,
				formatter : function(value, row, index) {
					//formatter的功能是将鼠标放在一单元格或一行时显示里面全部的内容
					//value当前密码的值,row一行的值,index第几行的索引
					//	return '<span title="'+row.name+"###"+value+'">' + value + '</span>';
					//如果说不想让前台看到密码可使用这个方法      
					return '******';
				}

			},//此处sortable和sortName的区别是，前者为加载后手动去排序，后者为初始化排序
			{
				field : 'createdatetime',
				title : '创建时间',
				width : 150
			}, {
				field : 'modifydatetime',
				title : '修改时间',
				width : 150
			} ] ],
			
		//toolbar:'#admin_yhgl_toolbar'
		});
	});

	function searchFun() {
		/**
		$('#admin_yhgl_datagrid').datagrid('load',{
			name:$('#admin_yhgl_layout input[name=name]').val()
		});*/
		//此方法比上面注释的好处是，他可以不用填写想提交查询的字段类型，不管有多少查询，他都会将有name值的查询内容提交给后台，减少代码量
		$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_search')));
	};
	function clearFun() {
		$('#admin_yhgl_layout input[name=name]').val('');
		$('#admin_yhgl_datagrid').datagrid('load', {});
	};

	function append() {
		$('#admin_yhgl_addForm input').val('');//清空文本框内容
		$('#admin_yhgl_addDialog').dialog('open');
	};

	function editFun() {
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');//选中命中了checkbox的行
		if (rows.length == 1) {
			var d = $('<div/>').dialog({
				width : 500,
				height : 200,
				href : '${pageContext.request.contextPath}/admin/yhglEdit.jsp',//实现动态加载
				title : '修改用户',
				modal : true,//模式化
				buttons : [ {
					text : '编辑',
					handler : function() {
						$('#admin_yhglEdit_form').form('submit', {
							url : '${pageContext.request.contextPath}/userAction!edit.action',
							success : function(data) {
								var obj = jQuery.parseJSON(data);
								console.info(obj);
								console.info(rows);
								if (obj.success) {
									d.dialog('close');
									//$('#admin_yhgl_datagrid').datagrid('reload');
									$('#admin_yhgl_datagrid').datagrid('updateRow',{
										index:$('#admin_yhgl_datagrid').datagrid('getRowIndex',rows[0]),
										row : obj.obj
									});
								}
								$.messager.show({
									title : '提示',
									msg : obj.msg
								});
							}
						});
					}
				} ],
				//由于这种动态增加窗口，随着窗口关闭后，他的div还是保存在IE缓存中。占用资源。故需要销毁
				onClose : function() {
					$(this).dialog('destroy');
				},
				onLoad : function() {
					//$('#admin_yhglEdit_form input[name=id]').val(rows[0].id);
					//$('#admin_yhglEdit_form input[name=name]').val(rows[0].name);
					//console.info(rows[0]);
					$('#admin_yhglEdit_form').form('load', rows[0]);
				}
			});
		} else {
			$.messager.alert('提示', '请选择一条记录进行编辑');
		}
	}
	function remove() {
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');//选中命中了checkbox的行
		//$('#admin_yhgl_datagrid').datagrid('getSelected');//选中的是选中一行的数据，checkbox不一定被选中，但是多选时，也只能读出一条记录。
		//$('#admin_yhgl_datagrid').datagrid('getSelections');//选中的是选中一行的数据，checkbox不一定被选中，可多选记录
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/userAction!remove.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#admin_yhgl_datagrid').datagrid('load');
							$('#admin_yhgl_datagrid').datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}
</script>
<div id="admin_yhgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height:100px;">
		<form id="admin_yhgl_search">
			模糊查询:<input name="name" /> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onClick="searchFun()">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onClick="clearFun()">清空</a>
		</form>
	</div>
	<div data-options="region:'center'">
		<table id="admin_yhgl_datagrid"></table>
	</div>
</div>
<div id="admin_yhgl_addDialog" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					$('#admin_yhgl_addForm').form('submit',{
						url:'${pageContext.request.contextPath}/userAction!addUser.action',
						success:function(data){
       						var returnObj = jQuery.parseJSON(data);
							if (returnObj.success) {
								//$('#admin_yhgl_datagrid').datagrid('load');//该方法会重新向后台发送一次查询，资源有消耗，暂时不用
								//$('#admin_yhgl_datagrid').datagrid('appendRow',returnObj.obj);//该方法不需要向后台发送请求，且能将新增的内容插入到表格当前页最下面一行。obj是returnObj从后台返回的对象。returnObj.obj是easyui智能将对应弹出内容映射到表格中。类似于name:returnObj.obj.name,pwd:returnObj.obj.pwd等。可能考虑插入最下面不如最前面来的明显，暂时不用
								$('#admin_yhgl_datagrid').datagrid('insertRow',{
									index:0,
									row:returnObj.obj
								});//此方法可替换前两个插入到表格中，且省去向后台请求
								$('#admin_yhgl_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : returnObj.msg
							});
   						 }
					});
				}
				}]"
	style="width:500px;heigh:500px;" align="center">
	<form id="admin_yhgl_addForm" method="post">
		<table>
			<tr>
				<th>编号</th>
				<td><input name="id" readonly="readonly" />
				</td>
				<th>登录名称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true ,missingMessage:'请输入登录名'" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true ,missingMessage:'请输入密码'" />
				</td>
				<th>创建时间</th>
				<td><input name="createdatetime" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>修改时间</th>
				<td><input name="modifydatetime" readonly="readonly" />
				</td>
			</tr>
		</table>
	</form>
</div>
<!-- <div id="admin_yhgl_toolbar">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" style="float:left;">编辑</a>
		<div class="datagrid-btn-separator"></div>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">帮助</a>
</div> -->