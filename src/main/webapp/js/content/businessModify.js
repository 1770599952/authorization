$(function(){
    common.showMessage($("#message").val());
    $("#mainForm").validate({
        rules : {
            "title" : "required",
            "subtitle" : "required",
            "distance" : {
                required : true,
                digits : true,
                maxlength : 5
            }
        },
        messages : {
            "title" : "请输入标题！",
			"subtitle":"请输入副标题"
        }
    });
});

function modify() {
	$("#mainForm").submit();
}
function goback() {
    location.href = $('#basePath').val()+'/businesses';
}