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
					<form method="post" action="select_customer_list" class="form-inline pl20" role="form"
						id="myformSelectCustomer" data-ajax="true" data-ajax-mode="replace"
						data-ajax-update="#customerResult">
						<input id="page" name="page" type="hidden" value="${search.page }" /> 
						<input id="pageSize" name="pageSize" type="hidden" value="${search.pageSize }" />
						<input id="traceType" name="traceType" type="hidden" title="跟踪状态" value="${search.traceType }" />
						<input id="customerType" name="customerType" type="hidden" title="客户类型" value="${search.customerType }" /> 
						<input id="accountId" name="accountId" type="hidden" value="${search.accountId }" />
						<input id="salesSuccessRate" name="salesSuccessRate" type="hidden" title="销售成功率" value="${search.salesSuccessRate }" />
						<input id="orderType" name="orderType" title="排序类型" type="hidden" value="${search.orderType }">
						<input id="orderField" name="orderField" title="排序字段" type="hidden" value="${search.orderField }">
						<input id="cusSource" name="cusSource" type="hidden" title="客户来源(销售形式)" value="${search.cusSource }" />
						<input id="buyService" name="buyService" type="hidden" title="产品与服务" value="${search.buyService }" />
						<input id="followState" name="followState" type="hidden" title="销售推进状态" value="${search.followState }" />
						<input id="status" name="status" type="hidden" title="下一步计划状态" value="${search.status }" />

						<div class="form-group">
							<%--<label for="lastname" class="col-sm-1 control-label dev-col-sm-1">客 户 条 件：</label>--%>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<div class="search fl" style="width: 250px; margin-left: 15px;">
									<input type="text" name="content" placeholder="请输入简称/名称全拼/名称简拼/联系方式"
										style="width: 280px!important" value="${search.content }"/>
								</div>
								<input id="isFrom" name="isFrom" type="hidden" value="${search.isFrom }" />
								<button type="submit" style="margin-left:20px" class="btn btn_white20 dev-search-customer-page">查找</button>
							</div>
						</div>

					</form>
				</div>
				<div id="customerResult">
					<jsp:include page="select_customer_list.jsp" />
				</div>
				</div>
			</div>
		</div>
	
    <script type="text/javascript">
        var selectIds = [];//选中的ids
        var selectNames = [];//选中的名称集合
        var selectShareIds = [];//选中的sys_customer_share中的id集合
    </script>

    <script type="text/javascript"  src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
    <script type="text/javascript" src="../content/module/customer/select_customer.index.js"></script>
    <script type="text/javascript" src="../content/module/customer/select_customer.list.js"></script>

</body>
</html>