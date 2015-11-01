<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<form id="admin_yhglEdit_form" method="post">
		<table>
			<tr>
				<th>编号</th>
				<td><input name="id" readonly="readonly"/></td>
				<th>登录名称</th>
				<td><input name="name" class="easyui-validatebox"
					data-options="required:true"/></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" class="easyui-validatebox"
					data-options="required:true "/></td>
				<th>创建时间</th>
				<td><input name="createdatetime"/></td>
			</tr>
			<tr>
				<th>修改时间</th>
				<td><input name="modifydatetime"/></td>
			</tr>
		</table>
	</form>