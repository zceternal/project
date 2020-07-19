<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户管理</title>
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
<link rel="stylesheet" href="../content/css/jquery.ptTimeSelect.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>
<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
<!-- 开启跟禁用的引用 -->
<link rel="stylesheet"
	href="../content/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css" />
<script type="text/javascript"
	src="../content/bootstrap-switch-master/dist/js/bootstrap-switch.min.js"></script>

<style type="text/css">
.member_list .form-group {
	width: 100%;
	padding: 5px 0px;
}

.member_list .checkbox-inline {
	padding-right: 10px;
}

.member_list .check_home {
	margin-top: 10px;
}
.dev-col-sm-1{
width:120px !important;
}
.dev-col-sm-11{
width:88.66666667% !important;
}
.search_sk li{
font-weight:400;
color:#005aa0;
font-family:"Arial, Verdana, 宋体";margin-top: 5px;}
.search_sk li:hover{color:#dd3b43 }
.search_sk li.on{font-family:"Arial, Verdana, 宋体";}
.search_sk li.on:hover{color:#fff }
a.btn_blueg2:hover,span.btn_blueg2:hover{background:#fff;color:#21b2cc}
.dev-xszt-v{width: 100px!important;}
</style>
</head>
<body>
	<div class="main_content member_list">
		<div class="col-xs-12 col-sm-12 div_bordered">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<form method="post" class="form-inline pl20" role="form" id="myform" >
						<input id="page" name="page" type="hidden" value="${pager.pageNum }" /> 
						<input name="customerId" id="customerId" type="hidden" value="${customerRecord.customerId }" />
						<input name="isFirst" id="isFirst" value="${customerRecord.isFirst }" type="hidden" /> 
						<input name="accountId" id="accountId" value="${customerRecord.accountId }" type="hidden" /> 
						<input name="betweenTime" id="betweenTime" type="hidden" /> 
						<input name="backType" id="backType" type="hidden" /> 
						<%-- <div class="divider" style="width:100%;margin:15px 0px 15px -10px"></div>
						<div class="form-group">
							<div class="col-sm-11 favorite dev-col-sm-11">
								<div class="search fl" style="width: 300px; margin-left: 15px;">
								客户名称：${customerInfo.name }
								</div>
								<div class="search fl" style="width: 250px; margin-left: 20px;">
								客户状态：<c:forEach items="${allStatus }" var="st">
											<c:if test="${customerInfo.status==st.id }">
												${st.name }
											</c:if>
										</c:forEach>
								</div>
								<div class="search fl" style="width: 250px; margin-left: 15px;">
								销售负责人名称：${firstCustomerShare.allowName}
								</div>
								<div class="search fl" style="width: 250px; margin-left: 15px;">
								销售负责人电话：${firstCustomerShare.phone==null?"":firstCustomerShare.phone }
								</div>

								<div class="pull-right text-right mr15">
									<a class="btn_blueg2 font14" href="javascript:void(0)" onclick="back();">&lt;&lt;返回</a>
								</div>
							</div>
						</div> --%>
						<div class="form-group" style="padding: 15px 0px 5px 0px; width: 100%">
							<ul id="typeUl" class="tab_button" style="margin-bottom: 0px;">
								<li <c:if test="${customerRecord.betweenTime!=1&&customerRecord.betweenTime!=2 }">class='on'</c:if>>全部</li>
								<li data-id="1" <c:if test="${customerRecord.betweenTime==1 }">class='on'</c:if>>一月内</li>
								<li data-id="2" <c:if test="${customerRecord.betweenTime==2 }">class='on'</c:if>>三月内</li>
							</ul>
							<div class="inline relative mr0">
									<input id="startTime" class="form-control input150" type="text" input-type="date" name="startTime" placeholder="开始日期" value="${customerRecord.startTime }">
									<span class="date_icon" style="cursor: pointer;">
									<i></i>
									</span>
							</div>
							<div class="inline relative mr0">
									<input id="endTime" class="form-control input150" type="text" input-type="date" name="endTime" placeholder="结束日期" value="${customerRecord.endTime }">
									<span class="date_icon" style="cursor: pointer;">
									<i></i>
									</span>
							</div>
							<div class="inline relative mr0"><a class="btn btn_white20 dev-search-page" style="margin-left:20px;background-color: #39cfbd" onclick="getHtml(2);">查找</a></div>
							<div class="pull-right text-right mr15">
									<a class="btn_blueg2 font14" href="javascript:void(0)" onclick="back();">&lt;&lt;返回</a>
								</div>
							<!-- <ul id="type" class="tab_button" style="margin-bottom: 0px;" style="background-color: #39cfbd">
								<li class='on' data-id="">查找</li>
							</ul> -->
						</div>
					</form>
				</div>
				<div id="result">
					<jsp:include page="reply_list_show.jsp" />
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="../content/js/jquery.ptTimeSelect.js" ></script><!--日历插件--> 
<script type="text/javascript" src="../content/js/pickday.js" ></script><!--日历插件配置和调用-->
<script type="text/javascript">
$("#typeUl>li").click(function() {
	var $this = $(this);

	//$("#typeHid").val($this.data("id"));
	$("#accountId").val();
	$("#betweenTime").val($this.data("id"));
	$this.addClass("on").siblings().removeClass("on");
	$("#endTime").val("");
	$("#startTime").val("");
	getHtml(2);
});

function getHtml(type){
	$("#page").val("1");
	var endTime=$("#endTime").val();
	var startTime=$("#startTime").val();
	if(endTime&&startTime){
		var d1 = new Date(startTime.replace(/\-/g, "\/")); 
		var d2 = new Date(endTime.replace(/\-/g, "\/"));
		if(d1>d2){
			$.sk.error("开始时间不能大于结束时间！");
			return;
		}
	}
	if(type=='2'){
		$("#backType").val("1");
	}
	$.ajax({
		type:'POST',
		url:'../customer/getCustomer.html',
		dataType:'html',
		data:$("#myform").serialize(),
		success:function(data){
			$("#result").html(data);
			$("#backType").val("");
		}
	});
}
function back(){
	window.location = "../customer/show?customerId=${customerRecord.customerId }&isFirst=${customerRecord.isFirst }";
}


</script>
</body>
</html>