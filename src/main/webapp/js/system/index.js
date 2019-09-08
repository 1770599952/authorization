// 退出系统
function signOut(){
	
}

// 当前登录用户可以访问的菜单Map。也就是我们从后台取出的菜单列表放到这个变量中。
var menuMap = {};
// $(function() {});这个函数就是在页面加载时调用此函数
$(function() {
	// 向后端发送一个请求
	common.ajax({
			// 请求URL
			url : $("#basePath").val() + "/session/menus",
			// 成功返回以后的结果。data是从后台返回的数据。也就是菜单列表。
			success : function(data) {
				// 如果菜单列表存在并且长度大于0
				if(data && data.length > 0) {
					// $.each(data,function(i,value) ：意思就是得到data中的每一项,i为该项的标号，value为列表的当前项，也就是第i项
					$.each(data,function(i,value) {
						// 如果这个父亲菜单还没有没实例化
						if(!menuMap[value.parentId]) {
							// 将它实例化
							menuMap[value.parentId] = new Array();
						}
						// 然后把当前菜单加载到它的父菜单列表中。
						menuMap[value.parentId].push(value);
					});
					// 把从后台返回的菜单列表显示在页面上
					initMenu();
				}
			}
	});
});
/**
 * 初始化根菜单。系统管理，内容管理，统计报表这一层是根菜单。
 */
function initMenu() {
	// 由上面的逻辑可知，根菜单存储在menuMap[0]这个集合中，因为父节点为0的都是根菜单
	var menuList = menuMap[0];
	// 遍历这个菜单的每一项，拼接到前端。
	$.each(menuList,function(i,value) {
		// 组织拼接到前端的Html代码
		var html = '';
		html += ''
            + '<li id="'+value.id+'">'		// 菜单id
            + '<a href="'+value.url+'">'	// 点击菜单的链接
            + '<i class="fa fa-home"></i>'
            + '<span class="nav-label">'
            + value.name	// 菜单名
            + '</span>'
            + '</a> '
            + '</li>';
		// 把html代码加载到id为side-menu的标签中。
		$("#side-menu").append(html);
		// 根据父菜单ID初始化子菜单
		initSubMenu(value.id);
	});
	var basePath = $("#basePath").val();
	// 在加载完菜单后加载这些css和js实现前端效果。什么效果呢，就是这个点击的动态效果
    $("body").append(
            "    <link href=\""+basePath+"/css/style.css\" rel=\"stylesheet\">\n" +
            "    <script src=\""+basePath+"/js/common/hplus.js\"></script>\n" +
            "    <script type=\"text/javascript\" src=\""+basePath+"/js/common/contabs.js\"></script>");
}

/**
 * 根据父菜单ID初始化子菜单：parentId父节点id
 */
function initSubMenu(parentId) {
	// 得到当前父节点id的子菜单列表。
	var menuList = menuMap[parentId];
	var subId = parentId;
	// 遍历菜单的每一项拼接到前端
	$.each(menuList,function(i,value) {
		var basePath = $("#basePath").val();
		var subHtml = '';
			subHtml += ''
	            + '<ul class="nav nav-second-level">'
	            + '<li>'
	            + '<a class="J_menuItem" href="'+basePath + value.url+'" data-index="0">' + value.name + '</a>'
	            + '</li>'
	            + '</ul>';
		// 把代码追加到前端
		$("#"+subId).append(subHtml);
	});
}