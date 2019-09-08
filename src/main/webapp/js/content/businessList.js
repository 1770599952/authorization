$(function() {
	common.showMessage($("#message").val());	
});
function remove(id) {
	if(confirm("确定要删除这个商户吗？")) {
		$("#mainForm").attr("action",$("#basePath").val() + "/businesses/" + id);
		$("#mainForm").submit();
	}
}

function search() {
	$("#mainForm").attr("method","GET");
	$("#mainForm").attr("action",$("#basePath").val() + "/businesses");
	$("#mainForm").submit();
}

function modifyInit(id) {
	$("#id").val(id);
	$("#mainForm").attr("method","GET");
	$("#mainForm").attr("action",$("#basePath").val() + "/businesses/"+id);
	$("#mainForm").submit();
}
