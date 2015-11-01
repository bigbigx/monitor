<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$("#user_reg_regForm input").bind('keyup', function(event) {
			if (event.keyCode == '13') {
				regFun();
			}
		});
	});
	function regFun() {
		$('#user_reg_regForm').form('submit', {
			url : '${pageContext.request.contextPath}/userAction!reg.action',
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.success) {
					$('#user_reg_regDialog').dialog('close');
				}
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
	}
</script>
<div id="user_reg_regDialog" class="easyui-dialog"
	data-options="title:'注册',closed:true,modal:true,buttons:[{
				text:'注册',
				iconCls:'icon-edit',
				handler:function(){
					regFun();
				}
			}]">
	<form id="user_reg_regForm" method="post">
		<table>
			<tr>
				<th>登录名</th>
				<td><input name="name" class="easyui-validatebox"
					data-options="required:true ,missingMessage:'请输入登录名'" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>重复密码</th>
				<td><input name="rePwd" type="password"
					class="easyui-validatebox"
					data-options="required:true,validType:'eqPwd[\'#user_reg_regForm input[name=pwd]\']'" />
				</td>
			</tr>
		</table>
	</form>
</div>