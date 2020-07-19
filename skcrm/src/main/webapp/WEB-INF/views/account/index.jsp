<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="../content/ztree-3.5/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
<!-- 上传文件 -->
<script type="text/javascript" src="../content/js/ajaxfileupload.js"></script>

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
						<input id="state" name="state" type="hidden"
							value="0" />
						<input id="page" name="page" type="hidden"
							value="${pager.pageNum }" />
							<input id="pageSize" name="pageSize" type="hidden"
							value="${pager.pageSize }" />
						<div class="form-group">
							<div class="search fl" style="width: 400px">
								<input type="text" id="nameSerch" name="content"
									value="${param.content }"
									placeholder="请输入需要查询的姓名/姓名简拼/姓名全拼/工号/用户名"
									style="width: 400px" />

		 
							</div>

						</div>
						<div class="form-group">在职状态：
						<select id="selState">
						    <option value="1">全部</option>
						    <option value="0" selected>在职</option>
							<option value="-1">离职</option>
						</select>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn_white20 dev-search-page">查找</button>
						</div>
						<div class="pull-right text-right mr15">
						<%-- <shiro:hasPermission name="account_add"> --%>
							<a class="btn_blueg2 font14" href="javascript:void(0)"
								id="account_add">+新增用户</a>
						<%-- </shiro:hasPermission> --%>
						</div>
					</form>
				</div>
				<div id="result">
					<jsp:include page="account_list.jsp" />
				</div>
			</div>

		</div>
	</div>



	<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
	<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>

	<!--表单验证 s-->
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine.js"></script>
	<!-- ztree.js -->
	<script type="text/javascript"
		src="../content/ztree-3.5/js/jquery.ztree.core-3.5.js"></script>

	<!--表单验证 e-->
	<script type="text/javascript"
		src="../content/js/jquery.JPlaceholder.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
	<script type="text/javascript"
		src="../content/module/account/account.index.js"></script>
<script type="text/javascript">

</script>
</body>
</html>