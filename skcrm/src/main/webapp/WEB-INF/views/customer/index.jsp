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
						<input id="orderType" name="orderType" title="排序类型" type="hidden" value="${search.orderType }">
						<input id="orderField" name="orderField" title="排序字段" type="hidden" value="${search.orderField }">
						<input id="cusSource" name="cusSource" type="hidden" title="客户来源(销售形式)" value="${search.cusSource }" />
						<input id="buyService" name="buyService" type="hidden" title="产品与服务" value="${search.buyService }" />
						<input id="followState" name="followState" type="hidden" title="销售推进状态" value="${search.followState }" />
						<input id="nextPlanState" name="nextPlanState" type="hidden" title="下一步计划状态" value="${search.accountId }" />
						
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
						<div class="form-group form-inline" style="padding: 5px 0px;">
							<label class="col-sm-1 control-label dev-col-sm-1">销售形式：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="acclistTab" class="tab_button search_sk" style="margin-bottom: 0px; margin-top: 3px">
									<li class="dev-cusSourceList ${search.cusSource == -1 ? "on":"" }" id="dev-cusSourceList-all" data-value="-1">全选</li>
									<c:if test="${lxr != null }">
										<c:forEach var="item" items="${lxr }">
											<li class="dev-cusSourceList ${search.cusSource == item.id ? "on":"" }" data-value="${item.id }">渠道-${item.name }</li>
										</c:forEach>
									</c:if>
									<c:if test="${khly != null }">
										<c:forEach var="item" items="${khly }">
											<li class="dev-cusSourceList ${search.cusSource == item.id ? "on":"" }" data-value="${item.id }">直销-${item.name }</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="form-group form-inline" style="padding: 5px 0px;">
							<label class="col-sm-1 control-label dev-col-sm-1">产品及服务：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="acclistTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li class="dev-buyServiceList ${search.buyService == -1 ? "on":"" }" id="dev-buyServiceList-all" data-value="-1">全选</li>
									<c:if test="${cpfw != null }">
										<c:forEach var="item" items="${cpfw }" varStatus="sta">
											<li class="dev-buyServiceList ${search.buyService == item.id ? "on":"" }" data-value="${item.id }">${item.name }</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="form-group form-inline" style="padding: 5px 0px;">
							<label class="col-sm-1 control-label dev-col-sm-1">销售推进状态：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="acclistTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li class="dev-followStateList ${search.followState == -1 ? "on":"" }" id="dev-followStateList-all" data-value="-1">全选</li>
									<c:if test="${xstjzt != null }">
										<c:forEach var="item" items="${xstjzt }">
											<li class="dev-followStateList ${search.followState == item.id ? "on":"" }" data-value="${item.id }">${item.name }</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="form-group form-inline" style="padding: 5px 0px;">
							<label class="col-sm-1 control-label dev-col-sm-1">下一步计划状态：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="acclistTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li class="dev-nextPlanStateList ${search.nextPlanState == -1 ? "on":"" }" id="dev-nextPlanStateList-all" data-value="-1">全选</li>
									<li class="dev-nextPlanStateList ${search.nextPlanState == item.id ? "on":"" }" id="dev-nextPlanStateList-all" data-value="-1">正常</li>
									<li class="dev-nextPlanStateList ${search.nextPlanState == item.id ? "on":"" }" id="dev-nextPlanStateList-all" data-value="-1">超期7天</li>
									<li class="dev-nextPlanStateList ${search.nextPlanState == item.id ? "on":"" }" id="dev-nextPlanStateList-all" data-value="-1">超期14天</li>
									<li class="dev-nextPlanStateList ${search.nextPlanState == item.id ? "on":"" }" id="dev-nextPlanStateList-all" data-value="-1">超期28天</li>
									<li class="dev-nextPlanStateList ${search.nextPlanState == item.id ? "on":"" }" id="dev-nextPlanStateList-all" data-value="-1">搁置</li>
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
	<script type="text/javascript"
			src="../content/module/customer/customer.list.js"></script>
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