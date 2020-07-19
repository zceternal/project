<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
 <link rel="stylesheet" href="../content/chosen/chosen.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
 <script type="text/javascript">
    	window.UEDITOR_HOME_URL = "<%=path %>";
 </script>
</head>
<body>
	<div class="main_content add_block">
		<div class='div_bordered'>
			<div class="panel panel-default choose_block">
				<div class="panel-heading color333">发送消息</div>
				<div class="divider"></div>
				<form method="post" action="" class="form-horizontal old_block" role="form" id="myform" data-ajax="true"
			data-ajax-success="sendResult">
					
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">收件人：</label>
						<div class="col-sm-10">
							
							<select data-placeholder="请选择收件人员" 
								style="width: 450px;" class="chzn-select" data-no_results_text="没有匹配结果" multiple tabindex="6">
								
									<c:forEach items="${users }" var="item">
										<option value="${item.id }">${item.name }</option>
									</c:forEach>
							</select>
							<input type="text" name="receives" id="receives" class="validate[required]" style="width:0px;border: 0px;height: 32px;top:-9px;padding: 4px 0px;position: relative;"/>
							 
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 control-label">标题：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control validate[required,maxSize[200]]" style="width:450px;" 
								placeholder="" name="title" >
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 0px;">
						<label for="" class="col-sm-2 control-label">内容：</label>
						<div class="col-sm-10">
							<!-- 加载编辑器的容器 -->
    						<textarea id="container"  style="height:400px;width: 800px;" name="content" ></textarea>
    						<!-- <input type="text" name="content" id="content" class="validate[required]" style="width:0px;border: 0px;height: 32px;padding: 4px 0px;"/> -->
						</div>
					</div>

					<div class="form-group mb0">
						<div class=" col-sm-12 text-center">
							<div class="wid800">
								<button type="submit" class="btn btn_green">发送</button>
								<button type="button" class="btn btn_whites" onclick="window.location.href='../message/index'">取消</button>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
		<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
	<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
		<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>
	
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
	
	<script type="text/javascript" src="../content/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript"">
		var selectSub = [];
		$(".chzn-select").chosen().change(function(select, value){

			if(value.selected){
				selectSub.push(value.selected);
				$(".receivesformError").hide();
			}
			if(value.deselected){
				selectSub.remove(value.deselected);
			}
			if(selectSub.length == 0){
				$(".receivesformError").show();
			}
		    $("#receives").val(selectSub.join(","));
		});
	</script>
	
	<!-- 配置文件 -->
    <script type="text/javascript" src="../content/ueditor.1.4.3/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="../content/ueditor.1.4.3/ueditor.all.min.js"></script>
	 <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
        /* ue.addListener("contentChange",function(){
        	var content = ue.getContent();
     		if(content == ""){
     			$(".contentformError").show();
     		}else{
     			$(".contentformError").hide();
     		}
     		$("#content").val(content);
        	
        });  */
        $("#myform").validationEngine({
    		ajaxFormValidationMethod : 'post',
    		promptPosition : "centerRight"
    	});
        
        
        function sendResult(data){
        	if(data.success){
        		
        		$.sk.success("发送成功",function(){
        			
        			window.location.href = "../message/index";
        			
        		});
        		
        	}
        }
    </script>
</body>
</html>