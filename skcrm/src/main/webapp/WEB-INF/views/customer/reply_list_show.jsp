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
.poi{cursor: pointer;}
</style>

<c:if test="${pager.total == 0 }">

	<div class="panel choose_block" style="text-align: center">暂无处理记录</div>
</c:if>
<br>
<c:if test="${pager.total != 0 }">
<table class="table table-hover ">
	<thead>
		<tr>
			<th width="20%">时间
				<div id="shortNameOrder" class="dev-order up"></div>
			</th>
			<th width="10%">人员
				<div id="statusName" class="dev-order up"></div>
			</th>
			<th width="10%">类别
				<div id="traceName" class="dev-order up"></div>
			</th>
			<th width="60%">内容
				<div id="finalTime" class="dev-order up"></div>
			</th>
			<!-- <th>操作
				<div id="opeartor" class="empty"></div>
			</th> -->
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${allRecords }" var="record" varStatus="status">
		 <tr>
			<td><fmt:formatDate value="${record.createTime }" pattern="yyyy-MM-dd HH:mm" /></td>
			<td>${record.accountName }</td>
			<td><input type="hidden" class="sub" value="${fn:substring(record.remark, 0, 30)}"/><input type="hidden" class="no" value="${record.remark}"/>${record.typeName }</td>
			<td>${fn:substring(record.remark, 0, 30)}&nbsp;&nbsp;<a class="colorblue poi" onclick="showMore(this);">展开</a></td>
		 </tr>
		</c:forEach>
	</tbody>
</table>
</c:if>
<div id="Pagination" class="pager" style="border-top: none !important;"></div>

<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<script type="text/javascript" src="../content/js/jquery.pager.js"></script>
<script type="text/javascript">
		var pageClick = function(pageindex) {
			$("#page").val(pageindex);
			$("#myform").submit();
		};
		
		$("#Pagination").pager({
			pagenumber : '${pager.pageNum}',
			pagecount : '${pager.pages }',
			totalcount : '${pager.total}',
			buttonClickCallback : pageClick
		});
function showMore(obj){
	var msg=$(obj).parents("td").prev().find(".no").val();
	$(obj).parents("td").html(msg+"&nbsp;&nbsp;<a class=\"colorblue poi\" onclick=\"hiddenMore(this);\">收起</a>");
}
function hiddenMore(obj){
	var msg=$(obj).parents("td").prev().find(".sub").val();
	$(obj).parents("td").html(msg+"&nbsp;&nbsp;<a class=\"colorblue poi\" onclick=\"showMore(this);\">展开</a>");
}
</script>