<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.col-lg-11 {
	margin-left: 15px;
}

.customer_show_d>p {
	margin-left: 0px;
	margin-bottom: 5px;
}

.col-sm-1 {
	width: 50px;
}

.col-sm-1 img {
	border-radius: 50%;
	-webkit-border-radius: 50%;
}

.customer_show_block {
	border-bottom: solid 1px #e8e8e8;
	padding: 0px 10px 6px 10px;
	margin-bottom: 14px;
}
</style>

<c:if test="${pager.total == 0 }">

	<div class="panel choose_block">暂无处理记录</div>
</c:if>
<br>
<c:forEach items="${allRecords }" var="record" varStatus="status">
	<div class="panel choose_block customer_show_block">
		<div class="row">
			<%-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
				<img alt=""
					src="${record.avatar==null|| record.avatar == '' ?'../content/img/default_avatar.png':record.avatar}"
					height="50" width="50">
			</div> --%>
			<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11"
				style="width: 88.66666667%">
				<span style="float: right; color: #999"><font>*</font>${record.typeName }</span>
				<div class="customer_show_d">
					<p>
						<font class="color333 font16" style="margin-right: 15px;">${record.accountName }</font><font
							class="color999 font14"><fmt:formatDate
								value="${record.createTime }" pattern="yyyy/MM/dd/ HH:mm" /></font>
					</p>
					<div class="color333">
						<span>${record.deptName}</span> <span>${record.accountId==firstCustomerShare.allowAccountId?"&nbsp;&nbsp;|&nbsp;&nbsp;<font >销售第一负责人</font>":"" }</span>
						<div class="pull-right color999">
							<a data-reply-id="${record.id}" style="cursor: pointer"
								class="color999">回复</a> | <a id="thumbA_${record.id }"
								${record.liked==0?"style='cursor:pointer '":"" }> <img
								<c:if test="${record.liked==0 }">
									onclick="iLikeIt('${record.id }','${selfAccountId }');liked(this,${record.liked})"
								</c:if>
								alt=""
								src='${record.liked==0?"../content/img/thumb_01.png":"../content/img/thumb_02.png"}'
								onclick="liked(this,${record.liked})">
							</a><span id="likeQty_${record.id }">${record.likeQty } </span>

						</div>
					</div>
				</div>

				<c:if test="${status.index gt 4 }">
					<div style='cursor: pointer' id="shortDiv_${record.id }"
						onclick="checkDetail('${record.id }')">
						<%-- <p>${fn:substring(record.remark ,0,31)}${fn:length(record.remark )>=31?" ...":"" }&nbsp;&nbsp;&nbsp;<font --%>
						<p>${record.remark }<font
								color="green" onclick="checkDetail('${record.id }')">[展开]</font>
						</p>
					</div>
					<div id="longDiv_${record.id }" style="display: none;">
						<p>${record.remark }</p>

						<p style='cursor: pointer' onclick="hideDetail('${record.id }')">

							<c:if test="${record.listCustomerRecordRevert==null }">
								<div class="panel choose_block"></div>
							</c:if>

							<c:forEach items="${record.listCustomerRecordRevert}"
								var="recordRevert">
								<font color="brown">${recordRevert.accountName} </font>回复：
						 <font>${recordRevert.remark}</font>
								<br>
								<font size="2px"><fmt:formatDate
										value="${recordRevert.createTime}" pattern="yyyy/MM/dd/ HH:mm" /></font>
								<br>
							</c:forEach>
							<font color="green">[收起]</font>
						</p>
					</div>
				</c:if>
				<c:if test="${status.index le 4 }">
					<div style='cursor: pointer; display: none;'
						id="shortDivExt_${record.id }"
						onclick="checkDetailExt('${record.id }')">
						<%-- <p>${fn:substring(record.remark ,0,31)}${fn:length(record.remark )>=31?" ...":"" }&nbsp;&nbsp;&nbsp;<font --%>
						<p>${record.remark}<font
								color="green" onclick="checkDetailExt('${record.id }')">[展开]</font>
						</p>
					</div>
					<div id="longDivExt_${record.id }">
						<p>${record.remark }</p>

						<p style='cursor: pointer'
							onclick="hideDetailExt('${record.id }')">

							<c:if test="${record.listCustomerRecordRevert==null }">
								<div class="panel choose_block"></div>
							</c:if>

							<c:forEach items="${record.listCustomerRecordRevert}"
								var="recordRevert">
								<font color="brown">${recordRevert.accountName} </font>回复：
						 <font>${recordRevert.remark}</font>
								<br>
								<font size="2px"><fmt:formatDate
										value="${recordRevert.createTime}" pattern="yyyy/MM/dd/ HH:mm" /></font>
								<br>
							</c:forEach>
							<font color="green">[收起]</font>
						</p>
					</div>
				</c:if>

			</div>
		</div>
	</div>
</c:forEach>
<div id="Pagination" class="pager" style="border-top: none !important;"></div>

<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>

<script type="text/javascript">

		var pageClick = function(pageindex) {
			$("#page").val(pageindex);
			$("#form_customer_record_search").submit();
		};
		
		$("#Pagination").pager({
			pagenumber : '${pager.pageNum}',
			pagecount : '${pager.pages }',
			totalcount : '${pager.total}',
			buttonClickCallback : pageClick
		});
		
</script>


