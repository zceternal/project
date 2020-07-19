<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>三开股份客户关系管理系统</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/common.css" />
	<style>
	
	</style>
	</head>
	<body class="errBgBlue">
		<div class="wid721">
			<div class="error_img fl" >
				<img src="${pageContext.request.contextPath}/content/img/500_0.jpg" width="257" />
			</div>
			<div class="errText fl">
	            <p><b>sorry 您访问的页面弄丢了</b></p>
	            <p><font class="timeout">10</font>秒后自动返回上一页</p>
	            <p><a href="javascript:window.history.go(-1);" style="color: #107281;text-decoration: underline;letter-spacing: 1px;">返回上一页&nbsp;>></a></p>
	            <div class="img_bottom">
	            	<img src="${pageContext.request.contextPath}/content/img/500_1.jpg" width="109" />
	            </div>
			</div>
	    </div>
    <!-- 
    	${requestScope.exception}
     -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/content/js/jquery-1.8.3.min.js" ></script>
	<script>
	//关闭窗口倒计时
	function timeOut(n){
		var n= parseInt($('.timeout').eq(0).text());
		n--;
		if(n==0){
           window.history.go(-1);//倒计时完成后跳转页面
		}else{
		    $('.timeout').text(n);
		}
	}
	setInterval('timeOut(10)',1000)
	</script>	
	</body>
</html>

