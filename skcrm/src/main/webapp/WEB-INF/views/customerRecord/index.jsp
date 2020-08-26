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
<title>处理记录查询</title>
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
<link rel="stylesheet" href="../content/css/jquery.ptTimeSelect.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>

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

.dev-col-sm-1 {
	width: 120px !important;
}

.dev-col-sm-11 {
	width: 88.66666667% !important;
}

.search_sk li {
	font-weight: 400;
	color: #005aa0;
	font-family: Arial, Verdana, 宋体;
	margin-top: 5px;
	width: 80px;
	overflow: hidden;
}

.search_sk li:hover {
	color: #dd3b43
}

.search_sk li.on {
	font-family: Arial, Verdana, 宋体
}

.search_sk li.on:hover {
	color: #fff
}

a.btn_blueg2:hover, span.btn_blueg2:hover {
	background: #fff;
	color: #21b2cc
}
</style>
</head>
<body>
	<div class="main_content member_list">
		<div class="col-xs-12 col-sm-12 div_bordered">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<form method="post" action="index" class="form-inline pl20"
						role="form" id="myform" data-ajax="true" data-ajax-mode="replace"
						data-ajax-update="#result">
						<input id="page" name="page" type="hidden" value="${search.page }" />

						<input id="pageSize" name="pageSize" type="hidden"
							value="${search.pageSize }" /> <input id="accountId"
							name="accountId" type="hidden" value="${search.accountId }" />


						<div class="form-group form-inline" style="padding: 5px 0px;">
							<label class="col-sm-1 control-label dev-col-sm-1">负责人：</label>
							<div class="col-sm-11 favorite dev-col-sm-11">
								<ul id="acclistTab" class="tab_button search_sk"
									style="margin-bottom: 0px; margin-top: 3px">
									<li class="dev-acclist ${search.accountId == -1 ? "
										on":"" }" id="dev-acclist-all" data-value="-1">全选</li>
									<c:if test="${accList != null }">
										<c:forEach var="item" items="${accList }">
											<li title="${item.name }"
												class="dev-acclist ${search.accountId == item.id ? "
												on":"" }" data-value="${item.id }">${item.isMySelf()?"自己":item.name }</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>

						<div class="divider"
							style="width: 100%; margin: 15px 0px 15px -10px"></div>
						<div class="form-group">
							<label for="lastname" class="col-sm-1 control-label dev-col-sm-1">时间条件：</label>
							<div class="col-sm-11 dev-col-sm-11">

								<button type="button" style="margin-right: 20px"
									class="btn btn_white20 dev-pdate">上一天</button>
								<div class="inline relative mr0">

									<input type="text" class="form-control input150"
										readonly="readonly" placeholder="请输入开始时间" name="startTime"
										id="startTime" input-type="date" value="${initDate }">
										<span class="date_icon"
										style="cursor: pointer; padding-left: 0px;"><i></i></span>
								</div>
								至
								<div class="inline relative mr0" style="margin-left: 8px;">
									<input type="text" readonly="readonly"
										class="form-control input150" placeholder="请输入结束时间"
										name="endTime" id="endTime" input-type="date"
										value="${initDate }"> <span class="date_icon"
										style="cursor: pointer; padding-left: 0px;"><i></i></span>
								</div>

								<button type="button" style="margin-left: 18px"
									class="btn btn_white20 dev-ndate">下一天</button>

								<button type="submit" style="margin-left: 20px"
									class="btn btn_white20 dev-search-page">查找</button>
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
		src="../content/js/jquery.ptTimeSelect.js"></script>
	<!--日历插件-->
	<script type="text/javascript"
		src="../content/js/jquery.JPlaceholder.js"></script>
	<script type="text/javascript">
		$(function() {
			var lib = true;
			var pickerStart = new Pikaday({
				field : $("#startTime").get(0),
				firstDay : 1,
				minDate : new Date('1900-01-01'),
				format : 'YYYY-MM-DD',
				onSelect : function(date) {
					pickerEnd.setMinDate(date);
					if(lib) $("#myform").submit();
				},
				yearRange : [ 1900, 2330 ]
			});

			var pickerEnd = new Pikaday({
				field : $("#endTime").get(0),
				firstDay : 1,
				minDate : new Date('1900-01-01'),
				format : 'YYYY-MM-DD',
				onSelect : function(date) {
					pickerStart.setMaxDate(date);
					if(lib) $("#myform").submit();
				},
				yearRange : [ 1900, 2330 ]
			});

			pickerStart.setMaxDate(pickerEnd.getDate());
			pickerEnd.setMinDate(pickerStart.getDate());
			
			$(".date_icon").css("cursor", "pointer").click(function() {
				$(this).siblings(":text").click();
			});

			$(".dev-pdate").click(
					function() {
						lib = false;
						var start = pickerStart.getDate();
						var end = pickerEnd.getDate();
						pickerStart.setDate(addDate(start,-1));
						pickerEnd.setDate(addDate(end,-1));
						lib = true;
						$("#myform").submit();
					});
			$(".dev-ndate").click(
					function() {
						lib = false;
						var start = pickerStart.getDate();
						var end = pickerEnd.getDate();
						pickerStart.setDate(addDate(start,1));
						pickerEnd.setDate(addDate(end,1));
						lib = true;
						$("#myform").submit();
					});

			// 点击销售负责人,即可提交
			$(".dev-acclist").click(
					function() {
						var $this = $(this);
						if ($this.attr("id")) {//清空所有选中的项
							$("input[class=dev-acclist]:checked").removeAttr(
									"checked");
						}
						$this.addClass("on").siblings().removeClass("on");
						$("#accountId").val($this.data("value"));
						$("#myform").submit();
					});

		})

		function addDate(d, dadd) {
			d = d.valueOf()
			d = d + dadd * 24 * 60 * 60 * 1000
			d = new Date(d)
			return d;
		};
	</script>
</body>
</html>