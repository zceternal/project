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
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
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
/* .dev-xszt-v{width: 100px!important;} */
</style>
</head>
<body>
	<div class="main_content member_list">
		<div class="col-xs-12 col-sm-12 div_bordered">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<form method="post" action="index" class="form-inline pl20" role="form"
						id="myform" data-ajax="true" data-ajax-mode="replace"
						data-ajax-update="#result">
						<input id="page" name="page" type="hidden" value="${search.page }" /> 
						<input id="pageSize" name="pageSize" type="hidden" value="${search.pageSize }" />
						<input id="traceType" name="traceType" type="hidden" title="跟踪状态" value="${search.traceType }" /> 
						<input id="status" name="status" type="hidden" title="销售状态" value="${search.status }" />
						<input id="customerType" name="customerType" type="hidden" title="客户类型" value="${search.customerType }" /> 
						<input id="accountId" name="accountId" type="hidden" value="${search.accountId }" />
						<input id="salesSuccessRate" name="salesSuccessRate" type="hidden" title="销售成功率" value="${search.salesSuccessRate }" />
						<input id="orderType" name="orderType" title="排序类型" type="hidden" value="${search.orderType }"></input>
						<input id="orderField" name="orderField" title="排序字段" type="hidden" value="${search.orderField }"></input>
						
						<div class="form-group form-inline" style="padding: 5px 0px;">
							<label class="col-sm-1 control-label dev-col-sm-1">销售负责人：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="acclistTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li class="dev-acclist ${search.accountId == -1 ? "on":"" }" id="dev-acclist-all" data-value="-1">全选</li>
									<c:if test="${accList != null }">
										<c:forEach var="item" items="${accList }">
											<li class="dev-acclist ${search.accountId == item.id ? "on":"" }" data-value="${item.id }">${item.isMySelf()?"自己":item.name }</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="form-group form-inline">
							<label class="col-sm-1 control-label dev-col-sm-1">客  户  状  态：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="xsztTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">

									<li class='${search.status == -1 ? "on":"" } dev-xszt' data-value="-1">全选</li>
									<c:if test="${xszt != null }">
										<c:forEach var="item" items="${xszt }">
										<c:set var="selid" >,${item.id },</c:set>
										<c:if test="${fn:contains(',38,39,40,41,42,' ,selid )}">
										<li class="dev-xszt dev-xszt-v ${search.status == item.id ? "on":"" }" title="公海规则(${item.value }天)" data-value="${item.id }">${item.name }</li>
										</c:if>
										<c:if test="${!fn:contains(',38,39,40,41,42,' ,selid )}">
										<li class="dev-xszt dev-xszt-v ${search.status == item.id ? "on":"" }" title="公海规则(无)" data-value="${item.id }">${item.name }</li>
										</c:if>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
					<%-- 	<div class="form-group form-inline">
							<label class="col-sm-1 control-label dev-col-sm-1">跟  踪  状  态：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="traceTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li class="dev-trace ${search.traceType == -1 ? "on":"" }" data-value="-1">全选</li>
									<li class="dev-trace ${search.traceType == 7 ?  "on":"" }" data-value="7">7天未跟踪</li>
									<li class="dev-trace ${search.traceType == 10 ? "on":"" }" data-value="10">10天未跟踪</li>
									<li class="dev-trace ${search.traceType == 15 ? "on":"" }" data-value="15">15天未跟踪</li>
									<li class="dev-trace ${search.traceType == 30 ? "on":"" }" data-value="30">30天未跟踪</li>
									<li class="dev-trace ${search.traceType == 60 ? "on":"" }" data-value="60">60天未跟踪</li>
									<li class="dev-trace ${search.traceType == -2 ? "on":"" }" data-value="-2">无跟踪状态</li>
								</ul>
							</div>
						</div> --%>
						<div class="form-group form-inline">
						<c:if test="${search.customerType != -1 or search.salesSuccessRate!=-1}">
							<label class="col-sm-1 control-label dev-col-sm-1"><a href="javascript:;" onclick="hideH()" id="a_hs">隐 藏 查 询</a></label>
						</c:if>
						<c:if test="${search.customerType == -1 and search.salesSuccessRate ==-1}">
						<label class="col-sm-1 control-label dev-col-sm-1"><a href="javascript:;" onclick="showH()" id="a_hs">高 级 查 询</a></label>
						</c:if>
						</div>
						<div class="form-group form-inline dev-highSeacrch" style='${(search.customerType!=-1 or search.salesSuccessRate!=-1)?"":"display:none"}'>
							<label class="col-sm-1 control-label dev-col-sm-1">客 户 类 型：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="khlxTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li id="dev-khlx-all" class="${search.customerType == -1 ? "on":"" } dev-khlx" data-value="-1" />全选
									</li>
									<c:if test="${khlx != null }">
										<c:forEach var="item" items="${khlx }">
											<li class="dev-khlx ${search.customerType == item.id ? "on":"" }" data-value="${item.id }">${item.name }</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="form-group form-inline dev-highSeacrch" style='${(search.customerType!=-1 or search.salesSuccessRate!=-1)?"":"display:none"}'>
							<label class="col-sm-1 control-label dev-col-sm-1">平台版本 ：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="khlxTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li id="dev-khcgl-all" class="${search.salesSuccessRate == -1 ? "on":"" } dev-khcgl" data-value="-1" />全选
									</li>
									<c:if test="${ptbb != null }">
										<c:forEach var="item" items="${ptbb }">
											<li class="dev-khcgl ${search.salesSuccessRate == item.id ? "on":"" }" data-value="${item.id }">${item.name }</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="divider" style="width:100%;margin:15px 0px 15px -10px"></div>
						<div class="form-group">
							<label for="lastname" class="col-sm-1 control-label dev-col-sm-1">客 户 条 件：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<div class="search fl" style="width: 250px; margin-left: 15px;">
									<input type="text" name="content" placeholder="请输入简称/名称全拼/名称简拼/联系方式"
										style="width: 280px!important" value="${search.content }"/>
								</div>
								<input id="isFrom" name="isFrom" type="hidden" value="${search.isFrom }" />
								<div class="search fl" style="width: 120px; margin-left: 20px;">
									来源： <select id="selFrom">
										<option value="-1" ${search.isFrom=="-1"?"selected":"" }>全部</option>
										<option value="0" ${search.isFrom=="0"?"selected":"" }>创建</option>
										<option value="1" ${search.isFrom=="1"?"selected":"" }>转移</option>
										<option value="2" ${search.isFrom=="2"?"selected":"" }>共享</option>
										<option value="3" ${search.isFrom=="3"?"selected":"" }>公海</option>										
									</select>
								</div>
								<button type="submit" style="margin-left:20px" class="btn btn_white20 dev-search-page">查找</button>

								<div class="pull-right text-right mr15">
								<shiro:hasPermission name="customer_add">
									<a class="btn_blueg2 font14" href="javascript:void(0)"
										data-id="customer_add" data-href="customer/add"><i class="icon_array_a"></i>新增客户</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="customer_share"> <span
										class="btn_blueg2 font14 " style="cursor: pointer"
										href="javascript:void(0)" data-id="customer_sharex"
										data-href="customer/share"><i  class="icon_array_b"></i>共享</span>
										</shiro:hasPermission>
										<shiro:hasPermission name="customer_transfer"> <span
										class="btn_blueg2 font14" style="cursor: pointer"
										href="javascript:void(0)" data-id="customer_transfer"
										data-href="transfer"><i  class="icon_array_c"></i>转移</span>
										</shiro:hasPermission>
										<shiro:hasPermission name="highseas_transfer">
										 <span
										class="btn_blueg2 font14" style="cursor: pointer"
										href="javascript:void(0)" data-id="customer_pool_batch"
										data-href="transfer"><i  class="icon_array_c"></i>批量投入公海</span>
										</shiro:hasPermission>
										
								</div>
							</div>
						</div>

					</form>
				</div>
				<div id="result">
					<jsp:include page="_list.jsp" />
				</div>
				</div>
			</div>
		</div>
	
		<script type="text/javascript">
			var selectIds = [];//选中的ids
			var selectNames = [];//选中的名称集合
			var selectShareIds = [];//选中的sys_customer_share中的id集合
		</script>
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
		<script type="text/javascript"
			src="../content/module/customer/customer.index.js"></script>
		<!--引入地图搜索JS (1/2)  -->
		<script type="text/javascript"
			src="http://api.map.baidu.com/api?v=2.0&ak=2i6NaIyF6fP4NOFdLs4I9Fk9"></script>
	
		<script type="text/javascript"
			src="../content/module/customer/customer_share.js"></script>
	
		<script type="text/javascript"
			src="../content/module/customer/customer_transfer.js"></script>
			
			<script type="text/javascript"
			src="../content/module/customer/customer_pool_batch.js"></script>
	<script type="text/javascript">
	function showH(){
		$(".dev-highSeacrch").each(function(){
			$(this).show();
		});
		$("#a_hs").text("隐 藏 查 询");
		$("#a_hs").attr("onclick","hideH()");
}
	function hideH(){
		$(".dev-highSeacrch").each(function(){
			$(this).hide();
		});
		$("#a_hs").text("高 级 查 询");
		$("#a_hs").attr("onclick","showH()")
}
</script>

</body>
</html>