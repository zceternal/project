<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>平台规则</title>
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<script type="text/javascript">
	var rootPath = "../";
</script>


<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />



<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=2i6NaIyF6fP4NOFdLs4I9Fk9"></script>
</head>
<body>
	<div class="main_content create_site">
		
				<div class="site_block member_block">
					<div class="panel-body">

						<div class="row">

							<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12"
								style="width: 90% !important;">
								<div class="panel panel-default dev-record">
									<!-- Default panel contents -->
									<div class="">
										<div class="site_change "
											style="height: 33px; margin-bottom: 20px; margin-top: 10px; border-bottom: solid 1px #ccc; margin-left: 20px;">
											<span class="colorshenblue font16"
												style="border-bottom: 2px #49b79d solid; padding: 0px 5px 10px 5px;">维护平台规则</span>
											<a class="btn btn_blueg2 font14 pull-right"  href="javascript:void(0)" onclick="closeRecordShow()" id="closerecord"
												 style="margin-right:18px;">关闭</a>	
												
										</div>
									</div>

									<form class="form-inline pl20" role="form" id="myform"
										method="post" action="plat_rule" data-ajax="true"
										data-ajax-mode="replace"
										data-ajax-update="#platRuleContent">
											<textarea id="container"  style="height:200px;width: 100%;">${content }</textarea>
    								  		<input type="text"  id="content" name="content"
											class="form-control input900 validate[required]"
											style="width:0px;border: 0px;height: 32px;padding: 4px 0px;"
											placeholder="输入内容..."></input>

										<div class="form-group"
											style="padding: 15px 0px 5px; width: 100%">
										
											<div class="pull-right mr15">
												<button type="submit" class="btn btn_white20" onclick="return submitRecord();"
													style="width: 126px; height: 41px; font-size: 18px">发&nbsp;&nbsp;&nbsp;布</button>
											</div>
										</div>
									</form>
								</div>

								<div class="panel panel-default" style="margin-top: 20px;">
									<div class="">
										<div class="site_change "
											style="height: 33px; margin-bottom: 20px; margin-top: 10px; border-bottom: solid 1px #ccc; margin-left: 20px;">
											<span class="colorshenblue font16"
												style="border-bottom: 2px #49b79d solid; padding: 0px 5px 10px 5px;">平台规则内容</span>
													
												<a class="btn btn_blueg2 font14 pull-right"  href="javascript:void(0)" onclick="addRecordShow()" id="addrecord"
												 style="margin-right:18px;">维护平台规则</a>	
								
										</div>
									</div>

									<div id="platRuleContent" >
										<jsp:include page="plat_rule_content.jsp"></jsp:include>
									</div>

								</div>

							</div>

				</div>
			</div>

		</div>
	</div>

	<!-- 引用js -->
	<script type="text/javascript"
		src="../content/js/jquery.ptTimeSelect.js"></script>
	<script type="text/javascript" src="../content/js/common.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>

	<script type="text/javascript"
		src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
	<script type="text/javascript">
		$(function() {

			
			$("#logsType>li").click(function() {
				var $this = $(this);
				$("#logsTypeHid").val($this.data("id"));
				$this.addClass("on").siblings().removeClass("on");
			});

			$("#type>li").click(function() {
				var $this = $(this);

				$("#typeHid").val($this.data("id"));
				$("#accountId").val();

				$this.addClass("on").siblings().removeClass("on");
				$("#form_customer_record_search").submit();
			});

			$("#filterType>li").click(function() {
				var $this = $(this);
				$("#accountId").val($this.data("id"));
				$this.addClass("on").siblings().removeClass("on");
				$("#form_customer_record_search").submit();
			});

		});
		

	</script>
	
	<!-- 配置文件 -->
    <script type="text/javascript" src="../content/ueditor.1.4.3/ueditor.config.easy.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="../content/ueditor.1.4.3/ueditor.all.min.js"></script>
	 <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
        $(".dev-record").hide();
        $("#myform").validationEngine({
    		ajaxFormValidationMethod : 'post',
    		promptPosition : "centerRight"
    	});
        function submitRecord(){
        	var content = ue.getContent();
     		$("#content").val(content);
     		return true;
        }
        //是否添加-关闭=“添加处理记录”
		function addRecordShow(){
			$("#addrecord").hide();
	        $(".dev-record").show("slow"); 
		}
		function closeRecordShow(){
			 $("#addrecord").text("维护平台规则");
			 $("#addrecord").show();
		     $(".dev-record").hide("slow");
		}
    </script>
	
	
</body>
</html>
