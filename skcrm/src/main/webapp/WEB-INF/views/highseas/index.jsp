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
<title>公海管理</title>
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
.search_sk li{font-weight:400;color:#005aa0;font-family:"Arial, Verdana, 宋体";width: 100px;}
.search_sk li:hover{color:#dd3b43 }
.search_sk li.on{font-family:Arial, Verdana, 宋体}
.search_sk li.on:hover{color:#fff }
a.btn_blueg2:hover,span.btn_blueg2:hover{background:#fff;color:#21b2cc}
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
						<input id="page" name="page" type="hidden"
							value="${search.page }" /> 
							
							<input id="pageSize" name="pageSize" type="hidden"
							value="${search.pageSize }" />
							
							<input id="traceType"
							name="traceType" type="hidden" title="跟踪状态" value="${search.traceType }" /> <input
							id="status" name="status" type="hidden" title="销售状态" value="${search.status }" />
						<input id="customerType" name="customerType" type="hidden"
							title="客户类型" value="-1" /> <input id="accountId"
							name="accountId" type="hidden" value="${search.accountId }" />
							
							<input id="salesSuccessRate" name="salesSuccessRate" type="hidden"
							title="销售成功率" value="-1" />
							
							<input id="orderType" name="orderType" title="排序类型" type="hidden" value="${search.orderType }"></input>
							<input id="orderField" name="orderField" title="排序字段" type="hidden" value="${search.orderField }"></input>
							
							
						<div class="form-group form-inline">
							<label class="col-sm-1 control-label dev-col-sm-1">客  户  状  态：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="xsztTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">

									<li class='${search.status == -1 ? "on":"" } dev-xszt' data-value="-1">全选</li>
									<c:if test="${xszt != null }">
										<c:forEach var="item" items="${xszt }">
											<li class="dev-xszt ${search.status == item.id ? "on":"" }" data-value="${item.id }">${item.name }</li>
										</c:forEach>
									</c:if>

								</ul>
							</div>
						</div>
						
						<div class="form-group form-inline">
							<label class="col-sm-1 control-label dev-col-sm-1"><a href="javascript:;" onclick="showH()" id="a_hs">高 级 查 询</a></label>
						</div>
						
						<div class="form-group form-inline dev-highSeacrch" style="display:none;">
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
						<div class="form-group form-inline dev-highSeacrch" style="display:none;">
							<label class="col-sm-1 control-label dev-col-sm-1">平台版本 ：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="khlxTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li id="dev-khcgl-all" class="${search.customerType == -1 ? "on":"" } dev-khcgl" data-value="-1" />全选
									</li>
									<c:if test="${khcgl != null }">
										<c:forEach var="item" items="${khcgl }">
											<li class="dev-khcgl ${search.customerType == item.id ? "on":"" }" data-value="${item.id }">${item.name }</li>
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
								<div class="search fl" style="width: 150px !important; margin-left: 50px;">
									<input type="text" name="allowAccountName" placeholder="请输入销售负责人"
										style="width: 150px!important" value="${search.allowAccountName }"/>
								</div>
								<button type="submit" style="margin-left:20px" class="btn btn_white20 dev-search-page">查找</button>

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
		src="../content/module/highseas/highseas.index.js"></script>
	<!--引入地图搜索JS (1/2)  -->
	<script type="text/javascript"
		src="http://api.map.baidu.com/api?v=2.0&ak=2i6NaIyF6fP4NOFdLs4I9Fk9"></script>

	<script type="text/javascript"
		src="../content/module/highseas/highseas_share.js"></script>

	<script type="text/javascript"
		src="../content/module/highseas/highseas_transfer.js"></script>
<script type="text/javascript">

</script>
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