<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售CRM</title>
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
<!--表单验证css-->
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet"
	href="../content/ztree-3.5/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
</head>
<body style="background-color: #edf0f2 !important;">
	<div class="main_content">
		
		<div class="rows">
			
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="panel panel-default choose_block ">
	   <div class="panel-heading color333 mb0">
		   	时间:
		   	<div class="inline relative mr0">
		   	<input type="text" class="form-control input150"
								placeholder="请输入开始时间" name="startTime" id="startTime"
								input-type="date" value="${startTime }"/> <span class="date_icon" style="cursor: pointer;padding-left:0px;"><i></i></span>
								</div>
								至
								<div class="inline relative mr0" style="margin-left: 8px;">
								<input type="text" class="form-control input150"
								placeholder="请输入结束时间" name="endTime" id="endTime"
								input-type="date" value="${endTime }"/> <span class="date_icon" style="cursor: pointer;padding-left:0px;"><i></i></span>
							</div>
	<!-- 						<button id="searchBtn" onclick=treeOnclick(34,"张泽超")>搜索</button> -->
							
		</div>
	   </div>
				</div>
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
					<div class="panel panel-default choose_block " style="padding-top:10px;">
				<div class="panel-body" style="background-color: #FFF; border: 1px solid #ddd; padding:3px;">
					     <ul style="min-height: 600px;overflow-y:auto; overflow-x:hidden;" data-url="../analysis/ajaxManageTreeView?t=1"  class="ztree">
							数据正在加载中...
						</ul>
				</div>
<input type="hidden" id="accountIds"></input>
<input type="hidden" id="title"></input>
			</div>
				</div>
				<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
					<div class="panel panel-default " style="padding-top:10px;">
						<div class="panel-body" style="border: 1px solid #ddd; padding:3px;">
							<ul class="site_tab_ul">
	  			<li class="on" data-link="panel1"><a class="color333" href="javascript:void(0)" >销售漏斗图</a></li>
	  			<li data-link="panel2"><a href="javascript:void(0)" >客户增量走势图</a></li>
	  		</ul>
	  		<div class="panelUse">
	  			<div id="panel1">
	  			<input type="hidden" id="count" value='${count}' />
	  				<div id="echart1">暂无数据</div>
	  			</div>
	  			<div id="panel2" style="display:none;">
	  				<div id="echart2" >暂无数据</div>
	  			</div>
					</div>	
						</div>
					</div>
				</div>
			</div>

		</div>
	
	</div>
</body>
	<!-- 引用js -->
	<script type="text/javascript"
		src="../content/js/jquery.ptTimeSelect.js"></script>
		<!--日历插件-->
<script type="text/javascript"
		src="../content/js/jquery.JPlaceholder.js"></script>


	<!--表单验证 s-->
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine.js"></script>
	<!--表单验证 e-->
	
	 <script src="../content/js/echarts-all.js"></script>
	


	<script type="text/javascript"
		src="../content/js/jquery.unobtrusive-ajax.min.js"></script>

	<!-- ztree.js -->
	<script type="text/javascript"
		src="../content/ztree-3.5/js/jquery.ztree.core-3.5.js"></script>
		
		
		<script type="text/javascript"
		src="../content/ztree-3.5/js/jquery.ztree.excheck-3.5.js"></script>
		
	<script type="text/javascript"
		src="../content/ztree-3.5/js/jquery.ztree.exedit-3.5.min.js"></script>
	<script src="../content/module/analysis/analysis.index.js"></script>
<script type="text/javascript">
	$("#echart1").width($(document).width() - 320).height($(document).height() - 210);
	$("#echart2").width($(document).width() - 320).height($(document).height() - 210);
	$(".ztree").height($(document).height() - 150);
	
$("li[data-link]").click(function(){

	var $this = $(this);

	$this.siblings().removeClass('on');
	$this.addClass('on');

	$(".panelUse>div").hide();

	$("#"+$this.data("link")).show();


})


</script>
</html>