<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta name="renderer" content="webkit"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司运管平台-主页</title>
<jsp:include page="layout/importCSS.jsp" />

<link rel="stylesheet" href="content/css/glyphicon.css" />

<style type="text/css">
.left_slide {
	cursor: pointer;
	font-size: 12px;
	width: 100%;
	height: 20px;
	text-align: center;
	margin: auto;
	outline: solid 1px #1d1f1f;
	border: solid 1px #2b2d2e;
	background: url(content/img/left_slide_icon.png) no-repeat 45% 1px;
}

.left_slide_show {
	background-position: 45% -13px;
}

body.left_short {
	background: #edf0f2 url(content/img/left_bg_short.jpg) repeat-y 0px 0px
		!important;
}
.mes a{width:22px}
</style>

</head>
<body
	style="background: #edf0f2 url(content/img/left_bg.jpg) repeat-y 0px 0px;">
	<div class="header">
		<div class="logo fl">
			<a href="index"><img src="content/img/logo.png"  /></a>
		</div>
		<div class="fr">
			<div class="login_box pull-right btn-group">
				<p class="dropdown-toggle" style="height:62px !important;">
					<span> <c:if test="${empty accountInfo.avatar}">
							<img src='content/img/default_avatar.png' width="28px"
								height="28px"
								style="display: inline-block; margin-right: 5px; -webkit-border-radius: 50%; vertical-align: -10px;" />
							<em id="loginName">${accountInfo.name}</em>
						</c:if> <c:if test="${not empty accountInfo.avatar}">
							<img src='${accountInfo.avatar.replace("../../", "")}' width="28px"
								height="28px"
								style="display: inline-block; margin-right: 5px; -webkit-border-radius: 50%; vertical-align: -10px;" />
							<em id="loginName">${accountInfo.name}</em>
						</c:if>
					</span> <i class="arrow_down"></i>
				</p>
				<ul class="dropdown-menu-header">

					<shiro:hasPermission name="account_info">
						<li data-info-id><a href="javascript:void(0)">个人信息</a></li>
					</shiro:hasPermission>

					<shiro:hasPermission name="account_edit_pwd">
						<li data-editpwd-id><a href="javascript:void(0)">修改密码</a></li>
					</shiro:hasPermission>

					<li><a href="logout">安全退出</a></li>
				</ul>
			</div>

			<%-- <shiro:hasPermission name="message_list"> --%>
				<div class="mes pull-right" title="">
                    <a id="msgCount" class="font12 text-center" href="javascript:void(0)" data-id="message" data-link="message/index" >
                    <font class="font12 text-center colorbg" style="display:block; margin-top: 3px;">${messageCount }</font>
                    
                    </a>
				</div>
				<script type="text/javascript">
					function readCount(){		
						try{
						$.post("message/unreadCount.ajax",{},function(result){
							
							if(result.success){								
								$("#msgCount").text(result.data);								
							}
							
						},"json");
						}catch (e) {
							
						}
						setTimeout(readCount, 30000);
					}
					readCount();
				</script>
			<%-- </shiro:hasPermission> --%>

			<div class="search pull-right"></div>
			<!-- <div class="mes advice fl" id="feed_back"></div> -->



		</div>

	</div>
	<div id="mainFrame">
		<div class="left_menu fl">

			<jsp:include page="layout/menu.jsp" />
		</div>
		<div class="right_main fl">
			<iframe id="myFrame" src="home/content" width="100%"
				frameborder="0" style="overflow: hidden;"></iframe>
		</div>
	</div>
</body>
<jsp:include page="layout/importJS.jsp" />
<script src="content/pace-0.5.6/pace.min.js" type="text/javascript"></script>
</html>
<script type="text/javascript">
	function SlideLeft(obj) {
		if ($(obj).hasClass("left_slide_show")) {
			$(obj).removeClass("left_slide_show");
			$(".left_menu").css("width", "180px");
			$("body").removeClass("left_short");
			$(".right_main").width(
					$(document.body).width() - $(".left_menu").width());
			$(".left_menu ul li span").css("display", "inline-block");
		} else {
			$(obj).addClass("left_slide_show");
			$(".left_menu").css("width", "70px");
			$(".right_main").width(
					$(document.body).width() - $(".left_menu").width());
			$("body").addClass("left_short");
			$(".left_menu ul li span").css("display", "none");
		}

	}
	$(window).resize(
			function() {
				$(".right_main").width(
						$(document.body).width() - $(".left_menu").width());
			})
			
			
function loadLeftHeight(){
    $(".left_menu").height($("html").height()-62)
}
$(function(){
    loadLeftHeight();
    $(window).resize(function(){
        loadLeftHeight();
    });
})

</script>
