<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="/sankai-fun" prefix="fun"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>标题</th>
			<th width="10%">发送人</th>
			<th width="10%">发送时间</th>
			<th width="8%">状态</th>
			<th width="200px">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${pager.total == 0 }">
			<tr>
				<td colspan="5" style="text-align: center">暂无数据</td>
			</tr>
		</c:if>
		<c:forEach items="${message }" var="item">
		<tr>
			<td style="text-align: left;" title="${item.title }" >${fun:subStr(item.title,50) }</td>
			<td>${elf:getAccountName(item.createId) }</td>
			<td><fmt:formatDate value="${item.createTime}"
						pattern="yyyy-MM-dd HH:mm" /></td>
			<td>${item.state == 0 ? '未读':'已读' }</td>
			<td><span data-show-id="${item.id }"> <a class="colorblue edit"
					href="javascript:void(0)">查看</a></span> <span data-delete-id="${item.id }"> <a
					data-action="delete" class="colorred delete"
					href="javascript:void(0)">删除</a></span></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div id="PaginationNotice" class="pager"></div>
<script type="text/javascript">
	//分页选择每页行数下拉回调函数
	var selValNotice = function() {
		$("#pageSize").val($("#pageSizeNum").val());
		$("#myform").submit();
	}
	$(function() {
		var pageClick = function(pageindex) {
			$("#page").val(pageindex);
			$("#myform").submit();
		};
		$("#PaginationNotice").pager({
			pagenumber : '${pager.pageNum}',
			pagesize : '${pager.pageSize}',
			pagecount : '${pager.pages }',
			totalcount : '${pager.total}',
			buttonClickCallback : pageClick,
			issel : true,//是否显示选择行数控件 默认为false
			selectChangeCallBack : "selValNotice"//每页行数下拉回调函数
		});
	});
</script>