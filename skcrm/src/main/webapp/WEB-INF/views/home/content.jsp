<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>


</head>
<body>
	<div class="main_content">
		<div class="col-xs-12 col-sm-12 overview">
			<ul class="site_ul">
				<li class="on">
					<div class="well">
						<a href="../customer/index?myself=1">
							<h1 class=" mt0 colorblue">${HomeCount.totalCustomerCount }</h1>
							<h5>客户总数</h5>
						</a>
					</div>
				</li>
				<li class="on">
					<div class="well">
						<a href="../contact/index?myself=1">
							<h1 class=" mt0 colorblue">${HomeCount.totalContactCount }</h1>
							<h5>联系人总数</h5>
						</a>
					</div>
				</li>
				<c:if test="${dictList!=null }">
				<c:forEach var="item" items="${dictList}">
				<li class="on">
					<div class="well">
						<a href="../customer/index?status=${item.id }">
							<h1 class=" mt0 colorred">${item.customerNo }</h1>
							<h5>${item.name }</h5>
						</a>
					</div>
				</li>
				</c:forEach>
				</c:if>
			</ul>
			<div class="well">
					<form method="post" action="" class="form-inline pl20" role="form"
						id="myformcontent" data-ajax="true" data-ajax-mode="replace"
						data-ajax-update="#contentresult"></form>
					<div id="contentresult">
						<jsp:include page="_list.jsp" />
					</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
	<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>

	<script type="text/javascript" src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript" src="../content/js/jquery.validationEngine.js"></script>
	<script type="text/javascript" src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
	<script type="text/javascript" src="../content/js/jquery.ptTimeSelect.js"></script>
	
	<script type="text/javascript" src="../content/module/home/home.content.js"></script>
	
	

</body>
</html>
