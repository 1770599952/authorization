var common = window.common || {};

/**
 * 展示指定的消息内容。
 */
common.showMessage = function(msg) {
	if(msg) {
		alert(msg);
	}
}

/**
 * 对jQuery的ajax方法的二次封装
 */
common.ajax = function(param) {
	var mergeParam = $.extend({
		timeout : 10000
	} , param , {
		complete : function(response) {
			var url = response.getResponseHeader("url");
			if(url) {
				location.href = url;
			} else {
				// 如果传来的参数中有complete方法，而且参数中的传来的complete必须为一个方法，才会对其进行调用
				if(param.complete && typeof param.complete == "function") {
					param.complete();
				}
			}
		}
	});
	$.ajax(mergeParam);
}

// 上传图片进行图片预览， 
function PreviewImage(imgFile) {
	var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
	if (!pattern.test(imgFile.value)) {
		alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
		imgFile.focus();
	} 
	path = URL.createObjectURL(imgFile.files[0]);
	document.getElementById("imgPreview").innerHTML = "<img src='"+path+"' style='width:227px; height:130px;' />";
}

/**
 * 页面返回码定义，与后台PageCode定义对应
 */
common.pageCode = {
		"ADD_SUCCESS" : 1000,
		"MODIFY_SUCCESS" : 1100,
		"REMOVE_SUCCESS" : 1200
}
// 菜单前缀
common.menuPrefix = {
		"PREFIX_MENU" : "MENU_",
		"PREFIX_ACTION" : "ACTION_"
}
// 商户与广告查看原图
function showPreAdPhoto(path){
	document.getElementById("imgPreview").innerHTML = "<img src='"+path+"' style='width:227px; height:130px;' />";
}