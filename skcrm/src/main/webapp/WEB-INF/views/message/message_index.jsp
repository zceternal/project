<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<div class="main_content">
		<div class="col-xs-12 col-sm-12 ">

			<div class="well">
				<ul class="site_tab_ul" id="site_tab_ul">
					<li class="on"><a class="color333" href="manage" data-ajax="true" data-ajax-mode="replace"
				data-ajax-update="#site_tab_message">我的消息</a></li>
					<!-- <li><a class="color333" href="notice" data-ajax="true" data-ajax-mode="replace"
				data-ajax-update="#site_tab_message">系统公告</a></li> -->
					<li><a class="color333" href="mymessage" data-ajax="true" data-ajax-mode="replace"
				data-ajax-update="#site_tab_message">已发消息</a></li>
				<!-- 	<li><a class="color333" href="mynotice" data-ajax="true" data-ajax-mode="replace"
				data-ajax-update="#site_tab_message">已发公告</a></li> -->
				</ul>
				
				<div class="row" id="site_tab_message">
					<jsp:include page="_message.jsp" />
				</div>
					
				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
	<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
	<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>
	<script type="text/javascript" src="../content/module/message/message.index.js"></script>

	<!--表单验证 s-->
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine.js"></script>

	<!--表单验证 e-->
	<script type="text/javascript"
		src="../content/js/jquery.JPlaceholder.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
	<script type="text/javascript">
		$("#site_tab_ul>li").click(function(){
			
			$(this).addClass("on").siblings().removeClass("on");
			
		});
	</script>
</body>
</html>