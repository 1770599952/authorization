<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>public remark webpublic scoring web</title>
<meta name="keywords" content="public remark webpublic scoring web">
<meta name="description" content="public remark webpublic scoring web">
<link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${basePath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${basePath}/css/animate.css" rel="stylesheet">
<link href="${basePath}/css/style.css" rel="stylesheet">
<link href="${basePath}/css/login.css" rel="stylesheet">
<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
</script>

</head>

<body class="signin">
	<div class="signinpanel">
		<div class="row">
			<div class="col-sm-7">
				<div class="signin-info">
					<div class="logopanel m-b">
						<h1>public remark webpublic scoring web</h1>
					</div>
				</div>
			</div>
			<input type="hidden" id="basePath" value="${basePath}" /> <input
				type="hidden" id="message" value="${pageCode.msg}" />
			<div class="col-sm-5">
				<form id="mainForm" action="${basePath}/login/validate"
					method="post">
					<h4 class="no-margins">登录：</h4>
					<br /> <input type="text" name="name" id="name"
						class="form-control uname" placeholder="用户名" /> <input
						type="password" id="password" class="form-control pword m-b"
						placeholder="密码" /> <input type="hidden" name="password"
						id="password_md5" /> <a href="">忘记密码了</a> <a href="#">立即注册&raquo;</a>
					<button class="btn btn-success btn-block" id="submit_login">登录</button>
				</form>
			</div>
		</div>
		<div class="signup-footer">
			<div class="pull-left">&copy; 2018 All Rights Reserved.</div>
		</div>
	</div>
	<script src="${basePath}/js/common/jquery.min.js?v=2.1.4"></script>
	<script src="${basePath}/js/common/bootstrap.min.js?v=3.3.6"></script>
	<script src="${basePath}/js/common/jQuery.md5.js"
		type="text/javascript"></script>
	<script src="${basePath}/js/common/validation/jquery.validate.min.js"
		type="text/javascript"></script>
	<script src="${basePath}/js/common/validation/messages_zh.js"
		type="text/javascript"></script>
	<script src="${basePath}/js/common/common.js" type="text/javascript"></script>
	<script src="${basePath}/js/system/login.js" type="text/javascript"></script>
</body>
</html>