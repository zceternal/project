<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html lang="zh-cn" xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta name="renderer" content="webkit"/>
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script type="text/javascript">
    if (window != top)
        top.location.href = location.href;
</script>

<title>三开科技客户关系管理系统</title>

<link rel="shortcut icon" type="image/x-icon" href="content/img/common/favicon.ico" />
<link rel="stylesheet" href="content/vendor/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="content/vendor/skCube/css/libs/nanoscroller.css" />
<link rel="stylesheet" href="content/vendor/skCube/css/compiled/theme_styles.css" />
<link rel="stylesheet" type="text/css" href="content/css/core/login.css" />
</head>
<body>
	<div id="floater"></div>
	
	<div id="loginbox" class="loginbox">
		<div class="logintopbox">
			<img src="content/img/login/login_logo.png" />
		</div>
		<div class="loginminbox">
			<div id="loginfr" class="loginfr">
				<form id="loginForm" class="form-horizontal" role="form" method="post" style="width: 400px; height: 220px; padding: 40px 30px 30px 30px;" action="">
					<div class="form-group">
						<label class="col-lg-3 control-label">用户名：</label>
						<div class="col-lg-8">
							<input type="text" name="username" placeholder="用户名" class="form-control " value="${param.username }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">密 码：</label>
						<div class="col-lg-8">
							<input type="password" id="password" name="password" placeholder="密码" class="form-control " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label"></label>
						<div class="col-lg-8">
							<button type="submit" class="btn btn-primary col-lg-5 dev-encrypt">登 录</button>
							<button type="reset" id="resetBtn" class="btn col-lg-5 col-md-offset-2">重 置</button>
						</div>
					</div>
				</form>
				<div id="showErrorMsg" class="errorMsg" style="color: red;">${error }</div>
			</div>
		</div>
	</div>
</body>
<script src="content/vendor/jquery/jquery-2.1.1.min.js"></script>
<script src="content/vendor/jquery/validation/jquery.validate.min.js"></script>
<script src="content/js/jquery.md5.js"></script>
<script type="text/javascript">
$(function(){
	$(".dev-encrypt").on("click",function(){
		var pw = $("#password").val();
		$("#password").val($.md5(pw));
	})
});

</script>
</html>
