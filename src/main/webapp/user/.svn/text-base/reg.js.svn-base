
function regUser() {
		/**$('#index_regForm').form('submit', {
			url : '${pageContext.request.contextPath}/userAction!reg.action',
			success : function(data) {
				var obj = JQuery.parseJSON(data);
				if(obj.sucess){
					$('#index_regDialog').dialog('close');
				}
				$.messager.show({
					title:'提示',
					msg:obj.msg,
					showType:'slide'
				});
			}
		});
		上面是jeasyui提交，下面是普通的ajax提交
		 */
		if ($('#index_regForm').form('validate')) {
			$.ajax({
				url : '${pageContext.request.contextPath}/userAction!reg.action',
				data : $('#index_regForm').serialize(),//传过去的字段值
				type : 'json',//告诉返回的类型为json
				success : function(obj, textStatus, jqXHR) {
					if (obj.sucess) {
						$('#index_regDialog').dialog('close');
					}
					$.messager.show({
						title : '提示',
						msg : obj.msg
					});
				}
			});
		} else {
			alert('验证失败');
		}
	}


