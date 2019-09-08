<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>public remark webpublic scoring web</title>
    <meta name="keywords" content="public remark webpublic scoring web">
    <meta name="description" content="public remark webpublic scoring web">o
    <link rel="shortcut icon" href="${basePath}/favicon.ico"> <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath}/css/animate.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    <!-- 全局js -->
    <script src="${basePath}/js/common/jquery.min.js"></script>
    <script src="${basePath}/js/common/bootstrap.min.js"></script>
    <script src="${basePath}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${basePath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${basePath}/js/plugins/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <script src="${basePath}/js/common/hplus.js"></script>
    <script type="text/javascript" src="${basePath}/js/common/contabs.js"></script>
    <script src="${basePath}/js/common/common.js" type="text/javascript"></script>
    <script type="text/javascript" src="${basePath}/js/system/index.js"></script>
    <!-- 第三方插件 -->
    <script src="${basePath}/js/plugins/pace/pace.min.js"></script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
 <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <input type="hidden" id="basePath" value="${basePath}"/>
		<input type="hidden" id="message" value="${pageCode.msg}"/>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">F
                    <div class="dropdown profile-element">
                        <span><img alt="image" class="img-circle" src="${basePath}/images/profile_small.jpg" /></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                <span class="block m-t-xs"><strong class="font-bold">${USER_INFO.name}</strong></span>
 <!--                                <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                               -->  </span>
                        </a>
<                   </div>
                    <div class="logo-element">
                    </div>
                </li>
                <!-- 追加到这里 -->
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="../index/main">Home</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <form name="outForm" action="${basePath}/session" method="post">
            	<input type="hidden" name="_method" value="DELETE">
         	   <a href="#" onclick="javascript:document:outForm.submit();" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
      		 </form>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${basePath}/index/main"  data-id="../main/main" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 2018-2019 <a href="#" target="_blank"></a>
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
</div>
</body>
</html>
