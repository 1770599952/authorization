$(function() {
	common.showMessage($("#message").val());
	$("#mainForm").validate({
		rules : {
			"title" : "required",
			"subtitle" : "required",
			"price" : {
				required : true,
				digits : true,
				maxlength : 5,
				range:[1,10000]
			},
			"distance" :{
				required : true,
				digits : true,
				maxlength : 5,
				range:[1,10000]
			},
			"imgFile":{
				required : true,
			}
	
		},
		messages : {
			"title" : "请输入标题！",
			"subtitle"  : "请输入链接！",
			"price": "请输入合法价格！",
			"distance" : "请输入合法距离！",
			"imgFile":"亲，请上传商户图片哦！"
		}
	});
});
function add() {
	$("#mainForm").submit();
}