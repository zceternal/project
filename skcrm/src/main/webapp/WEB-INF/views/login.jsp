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

<title>公司运管平台-登录</title>

<link rel="shortcut icon" type="image/x-icon" href="content/img/common/favicon.ico" />
<link rel="stylesheet" href="content/vendor/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="content/vendor/skCube/css/libs/nanoscroller.css" />
<link rel="stylesheet" href="content/vendor/skCube/css/compiled/theme_styles.css" />
<link rel="stylesheet" type="text/css" href="content/css/core/login.css" />
<link rel="stylesheet" type="text/css" href="content/css/haiyun_gm.css"/>
</head>
<body style="background:url(content/img/hy_login_bg.jpg) no-repeat center 0;">
<div id="floater"></div>
<div class="hy_login_main" style="text-align:center">
  <div class="hy_login_title"></div>
  <div class="hy_login_tab">
<!-- 代码 开始 -->
   <div id="fsD1" class="focus">  
    <div id="D1pic1" class="fPic">  
         <div class="fcon" style="display: none;">
            <a target="_blank" href="javascript:;"><img src="content/img/hy_login_radio_a.jpg" style="opacity: 1; "></a>
        </div>
        <div class="fcon">
            <a target="_blank" href="javascript:;"><img src="content/img/hy_login_radio_b.jpg" ></a>
        </div>
        <div class="fcon">
            <a target="_blank" href="javascript:;"><img src="content/img/hy_login_radio_c.jpg" ></a>
        </div>
    </div>
    <div class="fbg">  
    <div class="D1fBt" id="D1fBt">  
        <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>1</i></a> 
        <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>2</i></a> 
        <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>3</i></a>  
    </div>
    </div>    
</div>  
<!-- 代码 结束 -->
<form id="loginForm" class="form-horizontal" role="form" method="post" action="">
    <input name="username" placeholder="用户名" type="text" value="${param.username }" style="top:107px !important;" class="hy_login_inpa" />
    <input id="password" name="password" type="password" placeholder="密码" class="hy_login_inpa" style="top:177px;"/>
    <p class="hy_login_msgpa">${error }</p>
    <div class="boxa"><input name="" type="checkbox" value="" />&nbsp;&nbsp;记住密码</div>
    <input name="" type="submit" value="" class="hy_login_btna dev-encrypt"/>
    <input name="" type="reset" value="" class="hy_login_btnb"/>
</form>
  </div>
</div>
</body>
<script src="content/vendor/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="content/js/koala.min.1.5.js"></script>
<script src="content/vendor/jquery/validation/jquery.validate.min.js"></script>
<script src="content/js/jquery.md5.js"></script>


   <script type="text/javascript">
	Qfast.add('widgets', { path: "content/js/terminator2.2.min.js", type: "js", requires: ['fx'] });  
	Qfast(false, 'widgets', function () {
		K.tabs({
			id: 'fsD1',   //焦点图包裹id  
			conId: "D1pic1",  //** 大图域包裹id  
			tabId:"D1fBt",  
			tabTn:"a",
			conCn: '.fcon', //** 大图域配置class       
			auto: 1,   //自动播放 1或0
			effect: 'fade',   //效果配置
			eType: 'click', //** 鼠标事件
			pageBt:true,//是否有按钮切换页码
			bns: ['.prev', '.next'],//** 前后按钮配置class                          
			interval: 3000  //** 停顿时间  
		}) 
	})  
</script>

<script type="text/javascript">
$(function(){
	$(".dev-encrypt").on("click",function(){
		var pw = $("#password").val();
		$("#password").val($.md5(pw));
	})
});

</script>
</html>
