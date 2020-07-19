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
<link rel="stylesheet"
	href="../content/ztree-3.5/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
</head>
<body style="background-color: #edf0f2 !important;">
	<div class="main_content">
		<div class="col-xs-2 col-sm-2 div_bordered">
			<div class="panel panel-default choose_block " style="padding-top:10px;">
				<div class="panel-heading color333" style="margin-bottom: 10px;">部门管理</div>
				<div class="divider"></div>
				<div class="panel-body" style="background-color: #FDFDFD">
					<ul data-url="../department/ajaxManageTree?t=1"
							style="height: 520px;" id=department class="ztree">
							数据正在加载中...
						</ul>
				</div>

			</div>
		</div>
		<div class="col-xs-10 col-sm-10 " style="background: none;">
			<div class="div_bordered" style="margin-left:2px">
			<div class="panel panel-default choose_block " style="padding-top:10px;">
				<div class="panel-heading color333" style="margin-bottom: 10px;">
				
				部门列表
				<div class="pull-right text-right mr15" style="padding-top:2px;">
				<%-- <shiro:hasPermission name="dept_add"> --%>
							<a class="btn_blueg2 font14" href="javascript:void(0)"
								data-id="department_add">+新增部门</a>
				<%-- </shiro:hasPermission> --%>
						</div>
				</div>
				
				<div class="divider"></div>
				<div class="panel-body">
					<form action="index" data-ajax="true" data-ajax-mode="replace"
							data-ajax-update="#depart_list" id="form_search" method="post">
							<input id="pid" name="pid" type="hidden" value="${pid }" /> <input
								id="Id" name="id" type="hidden" value="${pid }" /> <input id="name" name="name"
								type="hidden" value="部门管理" /><input id="page" name="page"
								type="hidden" value="1" /><input id="pageSize" name="pageSize"
								type="hidden" value="10" /><input id="selectId" name="selectId"
								type="hidden" value="0" />
						</form>
						
						<div id="depart_list">
							<jsp:include page="depart_list.jsp"></jsp:include>
						</div>
				
				</div>
</div>
			</div>
		</div>


	</div>

	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
	<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.JPlaceholder.js"></script>


	<!--表单验证 s-->
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine.js"></script>
	<!--表单验证 e-->


	<script type="text/javascript"
		src="../content/js/jquery.unobtrusive-ajax.min.js"></script>

	<!-- ztree.js -->
	<script type="text/javascript"
		src="../content/ztree-3.5/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript"
		src="../content/ztree-3.5/js/jquery.ztree.exedit-3.5.min.js"></script>

	<script type="text/javascript"
		src="../content/module/department/department.index.js"></script>

</body>
</html>