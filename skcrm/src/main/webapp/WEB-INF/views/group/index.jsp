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
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/chosen/chosen.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>

</head>
<body>
	<div class="main_content member_list" >
		<div class="col-xs-12 col-sm-12 div_bordered">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<form method="post" action="index" class="form-inline pl20" role="form"
						id="myform" data-ajax="true" data-ajax-mode="replace"
						data-ajax-update="#result">
						<div class="form-group">
							<div class="search fl" style="width: 400px">
								<input type="text" id="nameSerch" name="content"
									value="${param.content }"
									placeholder="请输入需要群组名称/群组成员"
									style="width: 400px" />
							</div>

						</div>
						<div class="form-group">
							<button type="submit" class="btn btn_white20 dev-search-page">查找</button>
						</div>
						<div class="pull-right text-right mr15">
						<%-- <shiro:hasPermission name="group_add"> --%>
							<a class="btn_blueg2 font14" href="javascript:void(0)"
								id="group_add">+新增群组</a>
						<%-- </shiro:hasPermission> --%>
						</div>
					</form>
				</div>
				<div id="result">
					<jsp:include page="_list.jsp" />
				</div>
			</div>

		</div>
	</div>



	<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
	<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>

	<!--表单验证 s-->
	<script type="text/javascript" src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript" src="../content/js/jquery.validationEngine.js"></script>
	<script type="text/javascript" src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
	<!--表单验证 e-->
	
	<script type="text/javascript" src="../content/module/group/group.index.js"></script>
	

</body>
</html>