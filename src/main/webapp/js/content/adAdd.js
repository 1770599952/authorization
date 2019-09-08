$(function() {
	common.showMessage($("#message").val());
	$("#mainForm").validate({
		rules : {
			"title" : "required",
			"link" : "required",
			"weight" : {
				required : true,
				digits : true,
				maxlength : 5
			},
			"imgFile":{
				required : true
			}
		},
		messages : {
			"title" : "请输入标题！",
			"link"  : "请输入链接！",
			"weight": "请输入合法权重！",
			"imgFile":"亲，请添加广告图片哦！"
		}
	});
});

function add() {
	$("#mainForm").submit();
}

function goback() {
	location.href = $('#basePath').val() + '/ad';
}