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
<title>任务管理</title>
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
	<link rel="stylesheet" href="../content/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
<!-- 开启跟禁用的引用 -->
<link rel="stylesheet"
	href="../content/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css" />
<script type="text/javascript"
	src="../content/bootstrap-switch-master/dist/js/bootstrap-switch.min.js"></script>
	<!-- 上传文件 -->
	<script type="text/javascript" src="../content/js/ajaxfileupload.js"></script>

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
						<input id="status" name="status" type="hidden" title="任务状态" value="${search.status }" />
						<input id="quadrant" name="quadrant" type="hidden" title="任务象限" value="${search.quadrant }" />
						<input id="accountId" name="accountId" type="hidden" title="负责人" value="${search.accountId }" />
						<input id="source" name="source" type="hidden" title="任务来源" value="${search.source }" />

						<div class="form-group form-inline" style="padding: 5px 0px;">
							<label class="col-sm-1 control-label dev-col-sm-1">负责人：</label>
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

						<div class="form-group form-inline dev-highSeacrch" >
							<label class="col-sm-1 control-label dev-col-sm-1">任务象限：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li id="dev-khlx-all" class="${search.quadrant == -1 ? "on":"" } dev-khlx" data-value="-1" />全选</li>
									<c:if test="${taskxx != null }">
										<c:forEach var="item" items="${taskxx }">
											<li class="dev-khlx ${search.quadrant == item.id ? "on":"" }" data-value="${item.id }">${item.name }</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="form-group form-inline dev-highSeacrch" >
							<label class="col-sm-1 control-label dev-col-sm-1">任务状态 ：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li id="dev-khcgl-all" class="${search.status == -1 ? "on":"" } dev-khcgl" data-value="-1" />全选</li>
									<c:if test="${taskzt != null }">
										<c:forEach var="item" items="${taskzt }">
											<li class="dev-khcgl ${search.status == item.id ? "on":"" }" data-value="${item.id }">${item.name }</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
						<div class="form-group form-inline dev-highSeacrch" >
							<label class="col-sm-1 control-label dev-col-sm-1">任务来源 ：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li id="dev-rwly-all" class="${search.source == -1 ? "on":"" } dev-rwly" data-value="-1" />全选</li>
									<li class="dev-rwly ${search.source == 1 ? "on":"" }" data-value="1">我指派的</li>
									<li class="dev-rwly ${search.source == 2 ? "on":"" }" data-value="2">被指派的</li>
								</ul>
							</div>
						</div>

						<div class="divider" style="width:100%;margin:15px 0px 15px -10px"></div>
						<div class="form-group">
							<div class="col-sm-11 favorite dev-col-sm-11">
								<div class="search fl" style="width: 280px; margin-left: 15px;">
									<input type="text" name="content" value="${search.content }" placeholder="请输入任务名称" style="width: 280px !important" />
								</div>
								<button type="submit" style="margin-left:20px" class="btn btn_white20 dev-search-page">查找</button>

								<div class="pull-right text-right mr15">
								<shiro:hasPermission name="task_index">
									<a class="btn_blueg2 font14" href="javascript:void(0)"
										data-id="task_add" data-href="task/add"><i class="icon_array_a"></i>新增任务</a>
										</shiro:hasPermission>
									<shiro:hasPermission name="task_index">
										<span class="btn_blueg2 font14" style="cursor: pointer" href="javascript:void(0)" data-id="task_back"
											data-href="task/back"><i  class="icon_array_c"></i>任务反馈</span>
									</shiro:hasPermission>
										<shiro:hasPermission name="task_index">
											<span class="btn_blueg2 font14 " style="cursor: pointer" href="javascript:void(0)" data-id="task_sharex"
										data-href="customer/share"><i  class="icon_array_b"></i>共享</span>
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
			var selectShareIds = [];//选中的sys_task_share中的id集合
		</script>
		<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
		<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
		<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>
		<script type="text/javascript" src="../content/js/jquery.ptTimeSelect.js"></script>
		<!--表单验证 s-->
		<script type="text/javascript" src="../content/js/jquery.validationEngine-zh_CN.js"></script>
		<script type="text/javascript" src="../content/js/jquery.validationEngine.js"></script>
	
		<!--表单验证 e-->
		<script type="text/javascript" src="../content/js/jquery.JPlaceholder.js"></script>
		<script type="text/javascript" src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
		<script type="text/javascript" src="../content/module/task/task.index.js"></script>
		<script type="text/javascript" src="../content/module/task/task.share.js"></script>
		<script type="text/javascript" src="../content/module/task/task.back.js"></script>

	<script type="text/javascript">

	</script>

</body>
</html>