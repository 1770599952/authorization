$(function() {
	common.showMessage($("#message").val());
});

function search(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}

function remove(id) {
	if(confirm("确定要删除这条订单吗？")) {
		$("#id").val(id);
		$("#mainForm").attr("action",$("#basePath").val() + "/orders");
		$("#mainForm").submit();
	}
}

function modifyInit(id) {
	location.href = $("#basePath").val() + "/orders/" + id;
}