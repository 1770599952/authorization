$(function() {
	common.showMessage($("#message").val());
    // 验证信息
	$("#mainForm").validate({
		rules:{
			"name" : {
				required : true
			},
			"password" : {
				required : true
			}
		},
		messages : {
			"name" : "请输入合法用户名！",
			"password" : "请输入合法密码！",
		}
	});
	
	// 单击登录
	$("#submit_login").click(function () {
		if($("#password").val()) {
			$("#password_md5").val($.md5($("#password").val()));
		}
		$("#mainForm").submit();
    })
});