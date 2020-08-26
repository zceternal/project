<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>联系人管理</title>
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />

<!-- 日历样式 -->
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<!-- 上传文件 -->
<script type="text/javascript" src="../content/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
<!--日历插件配置和调用-->
<style type="text/css">
.contact_list .form-group {
	width: 100%;
}

.contact_list .checkbox-inline {
	padding-right: 10px;
}

.contact_list .check_home {
	margin-top: 10px;
}
.dev-col-sm-1{
width:130px !important;
}
.dev-col-sm-11{
width:86.66666667% !important;
}
.c_on {
  color:#39CFBD;
}
.search_sk li{color:#26bfab;}
.form-inline .control-label .label_check{
font-weight:400;
color:#005aa0;
font-family:Arial, Verdana, 宋体
}
.form-inline .control-label .label_check:hover{
color:#dd3b43
}
.search_sk li{
font-weight:400;
color:#005aa0;
font-family:Arial, Verdana, 宋体
}
.search_sk li:hover{color:#dd3b43 }
.search_sk li.on{font-family:Arial, Verdana, 宋体}
.search_sk li.on:hover{color:#fff }
a.btn_blueg2:hover,span.btn_blueg2:hover{background:#fff;color:#21b2cc}
</style>
</head>
<body style="background: #FFFFFF;">
	<div class="main_content dialog contact_list member_list">
		<div class="col-xs-12 col-sm-12 div_bordered">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">

					<form method="post" action="index" class="form-inline pl20" role="form"
						id="contactIndex" data-ajax="true" data-ajax-mode="replace"
						data-ajax-update="#contactDv">
						<input id="contactRole" name="contactRole" type="hidden"
							title="联系人角色" value="0" /> <input id="customerType"
							name="customerType" type="hidden" title="联系人客户" value="-1" /> <input
							id="page" name="page" type="hidden" value="${pager.pageNum }" />
							<input id="pageSize" name="pageSize" type="hidden"
							value="${pager.pageSize }" />
							
								<input id="orderType" name="orderType" title="排序类型" type="hidden" value="${search.orderType }"></input>
							<input id="orderField" name="orderField" title="排序字段" value="${search.orderField }" type="hidden"></input>
							

						<div class="form-group form-inline" style="padding: 5px 0px;">
							<label for="" class="col-sm-1 control-label dev-col-sm-1">负责人：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<div>
									<label class="control-label"> <label
										class="label_check"> <input type="checkbox" 
											class="dev-acclist" data-for="dev_acclist" value="-1" /><b>全选</b> 
									</label>
									</label>
									<c:if test="${accList != null }">

										<c:forEach var="item" items="${accList }">
											<label class="control-label"> <label
												class="label_check  ${item.isMySelf() && myself.equals('1')?'c_on':''}"> <input type="checkbox"
													name="accId" class="dev-acclist sk_checkbox" ${item.isMySelf() && myself.equals("1")?"checked":""}
													data-client="dev_acclist" value="${item.id }" />
													${item.isMySelf()?"登录用户":item.name }
											</label>
											</label>
										</c:forEach>

									</c:if>
								</div>
							</div>
						</div>
<%--						<div class="form-group form-inline">--%>
<%--							<label class="col-sm-1 control-label dev-col-sm-1"><a href="javascript:;" onclick="showH()" id="a_hs">高 级 查 询</a></label>--%>
<%--						</div>--%>
<%--						<div class="form-group form-inline dev-highSeacrch" style="padding: 5px 0px;display:none;">--%>
<%--							<label for="" class="col-sm-1 control-label dev-col-sm-1">联系人详情：</label>--%>
<%--							<div class="col-sm-11 favorite dev-col-sm-11">--%>
<%--								<div>--%>
<%--									<label class="control-label"> <label--%>
<%--										class="label_check"> <input type="checkbox"--%>
<%--											data-for="dev_contactDetails" value="-1" class="dev-contactDetails"/> <b>全选</b>--%>
<%--									</label>--%>
<%--									</label> <label class="control-label"> <label--%>
<%--										class="label_check"> <input type="checkbox"--%>
<%--											name="phone" class="dev-contactDetails"--%>
<%--											data-client="dev_contactDetails" value="true" /> 手机--%>
<%--									</label>--%>
<%--									</label> <label class="control-label"> <label--%>
<%--										class="label_check"> <input type="checkbox"--%>
<%--											name="wechat" class="dev-contactDetails"--%>
<%--											data-client="dev_contactDetails" value="true" /> 微信--%>
<%--									</label>--%>
<%--									</label> <label class="control-label"> <label--%>
<%--										class="label_check"> <input type="checkbox" name="qq"--%>
<%--											class="dev-contactDetails" data-client="dev_contactDetails"--%>
<%--											value="true" /> QQ--%>
<%--									</label>--%>
<%--									</label> <label class="control-label"> <label--%>
<%--										class="label_check"> <input type="checkbox"--%>
<%--											name="email" class="dev-contactDetails"--%>
<%--											data-client="dev_contactDetails" value="true" /> 邮箱--%>
<%--									</label>--%>
<%--									</label>--%>



<%--								</div>--%>
<%--							</div>--%>
<%--						</div>--%>

						<div class="form-group form-inline dev-highSeacrch" style="padding: 5px 0px;">
							<label for="lastname" class="col-sm-1 control-label dev-col-sm-1">联系人角色：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="lxrjsTab" class="tab_button search_sk">
									<li class="dev-lxrjs-all on" data-value="0">全选</li>
									<c:forEach items="${dictLxr }" var="item">
										<li class="dev-lxrjs-all" data-value="${item.id }">${item.name }</li>
									</c:forEach>

								</ul>
							</div>
						</div>

<%--						<div class="form-group form-inline dev-highSeacrch" style="padding: 5px 0px;display:none;">--%>
<%--							<label for="lastname" class="col-sm-1 control-label dev-col-sm-1">关 联 客 户：</label>--%>
<%--							<div class="col-sm-11 favorite dev-col-sm-11">--%>
<%--								<ul id="customerTypeTab" class="tab_button search_sk">--%>
<%--									<li class="dev-customerType on" data-value="-1">全选</li>--%>
<%--									<li class="dev-customerType" data-value="1">已关联客户</li>--%>
<%--									<li class="dev-customerType" data-value="0">未关联客户</li>--%>
<%--								</ul>--%>
<%--							</div>--%>
<%--						</div>--%>


						<div class="form-group" style="padding: 5px 0px;">
							<label for="lastname" class="col-sm-1 control-label dev-col-sm-1">联系人姓名：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<div class="search fl" style="width: 280px; margin-left: 15px;">
									<input type="text" name="content" value="${param.content }"
										placeholder="请输入联系人姓名/客户名称/简拼/全拼" style="width: 280px !important" />
								</div>
								<button type="submit" class="btn btn_white20 dev-search-page">查找</button>
								<div class="pull-right text-right mr15">

									<!-- <div class="pull-right text-right mr5">
										<a class="btn_blueg2 " href="#.html">发邮件</a>
									</div> -->
									<div class="pull-right text-right mr5">
										<shiro:hasPermission name="contact_share">
											<a class="btn_blueg2 font14" href="javascript:void(0)"
												data-url="contactShare" data-id="contact_share"><i  class="icon_array_b"></i>共享</a>
										</shiro:hasPermission>
									</div>
									<div class="pull-right text-right mr5">
										<shiro:hasPermission name="contact_add">
											<a class="btn_blueg2 font14" href="javascript:void(0)"
												data-id="contact_add"><i  class="icon_array_a"></i>新增联系人</a>
										</shiro:hasPermission>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div id="contactDv">
				<jsp:include page="_list.jsp"></jsp:include>
			</div>

		</div>
	</div>

	<!-- 引用js -->
	<script type="text/javascript"
		src="../content/js/jquery.ptTimeSelect.js"></script>


	<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
	<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.JPlaceholder.js"></script>

	<script type="text/javascript"
		src="../content/module/contact/contact.index.js"></script>
	<!--表单验证 s-->
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine.js"></script>
	<!--表单验证 e-->
	<script type="text/javascript"
		src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
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