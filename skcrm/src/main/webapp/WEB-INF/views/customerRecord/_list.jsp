
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>

<table class="table table-hover ">
	<thead>
		<tr>
			<th width="10%">客户简称	</th>
			<th width="20%">客户全称	</th>
			<th width="10%">联系人	</th>
			<th width="10%">联系人电话	</th>
			<th width="10%">回访时间	</th>
			<th width="10%">回访人</th>
			<th>回访记录</th>
			<td width="10%">操作</td>
		</tr>
	</thead>
	<tbody>

		<c:if test="${pager.total == 0 }">
			<tr>
				<td colspan="8" style="text-align: center">暂无数据</td>
			</tr>
		</c:if>
		<c:forEach var="item" items="${model }">
			<tr>
				<td title="${item.shortName }">${extf:subStr(item.shortName,9) }</td>
				<td title="${item.name }">${extf:subStr(item.name,18) }</td>
				<td>${item.contactName }</td>
				<td>${item.contactPhone }</td>
				<td><fmt:formatDate
						value="${item.createTime }" pattern="yyyy-MM-dd HH:mm" /></td>
					<td>${elf:getAccountName(item.accountId) }</td>
					<td title="${extf:removeTag(item.remark) }">${extf:removeTag(item.remark) }</td>	
				<td><shiro:hasPermission name="customer_list">
						<span class="colorblue edit"><a href="javascript:void(0)"  onclick="showDetail('${item.customerId }')" title="查看详情" class="colorblue">客户详情</a></span>
					</shiro:hasPermission></td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<div id="Pagination" class="pager"></div>

<script type="text/javascript">

//分页选择每页行数下拉回调函数
var selVal = function() {
	$("#pageSize").val($("#pageSizeNum").val());
	$("#myform").submit();
}

	$(function() {
		var pageClick = function(pageindex) {
			$("#page").val(pageindex);
			$("#myform").submit();
		};
		$("#Pagination").pager({
			pagenumber : '${search.page}',
			pagesize : '${search.pageSize}',
			pagecount : '${pager.pages }',
			totalcount : '${pager.total}',
			buttonClickCallback : pageClick,
			issel : true,//是否显示选择行数控件 默认为false
			selectChangeCallBack : "selVal"//每页行数下拉回调函数
		});
		
	
	});
	function showDetail(customerId) {
		window.open("../customer/show?customerId=" + customerId);
	}
</script>

