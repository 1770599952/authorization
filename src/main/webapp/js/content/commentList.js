/**
 * Created by MAIBENBEN on 2017/12/24.
 */
// 信息展示
$(function() {
    common.showMessage($("#message").val());
});
// 分页查询
function search(currentPage) {
    $("#currentPage").val(currentPage);
    $("#mainForm").submit();
}
// 删除
function remove(id) {
    if(confirm("确定要删除这条评论吗？")) {
        $("#id").val(id);
        $("#mainForm").attr("action",$("#basePath").val() + "/comments/remove");
        $("#mainForm").submit();
    }
}