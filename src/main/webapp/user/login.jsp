<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$("#user_login_loginForm input").bind('keyup', function(event) {
			if (event.keyCode == '13') {
				loginFun();
			}
		});
		window.setTimeout(function(){
			$('#user_login_loginForm input[name=name]').focus();
		}, 0);
	});
	function loginFun() {
		$('#user_login_loginForm').form('submit', {
			url : '${pageContext.request.contextPath}/userAction!login.action',
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				if (obj.success) {
					$('#user_login_loginDialog').dialog('close');
				}
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
	}
</script>
<div id="user_login_loginDialog" class="easyui-dialog"
	data-options="title:'登录',modal:true,closable:false,buttons:[{
				text:'注册',
				iconCls:'icon-edit',
				handler:function(){
					$('#user_reg_regDialog').dialog('open');
				}
			},{
				text:'登录',
				iconCls:'icon-help',
				handler:function(){
					loginFun();
				}
			}]">
	<form id="user_login_loginForm" method="post">
		<table>
			<tr>
				<th>登录名</th>
				<td><input name="name" class="easyui-validatebox"
					data-options="required:true ,missingMessage:'请输入登录名'"/>
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input type="password" name="pwd" class="easyui-validatebox"
					data-options="required:true ,missingMessage:'请输入密碼'"/>
				</td>
			</tr>
		</table>
	</form>
</div>
