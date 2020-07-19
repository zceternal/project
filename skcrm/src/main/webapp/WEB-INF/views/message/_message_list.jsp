<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="/sankai-fun" prefix="fun"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th><input type="checkbox" onclick="allChoose(this);">全选</th>
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
		<c:if test="${item.state == 0 }">
		<tr id="tr_read" data-id="${item.id }">
		</c:if>
		<c:if test="${item.state == 1 }">
		<tr id="tr_show" data-id="${item.id }">
		</c:if>	
				<td><input type="checkbox" value="${item.id }" class="checkbox"></td>
				<td style="text-align: left;" title="${item.title }">${fun:subStr(item.title,50) }</td>
				<td>${item.createId==-100?"系统管理员":elf:getAccountName(item.createId) }</td>
				<td><fmt:formatDate value="${item.createTime}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${item.state == 0 ? '未读':'已读' }</td>
				<td><c:if test="${item.state == 0 }">
						<span data-read-id="${item.id }"> <a class="colorblue edit"
							href="javascript:void(0)">查看</a></span>
					</c:if> <c:if test="${item.state == 1 }">
						<span data-show-id="${item.id }"> <a class="colorblue edit"
							href="javascript:void(0)">查看</a></span>
					</c:if> <span data-delete-id="${item.id }"> <a data-action="delete"
						class="colorred delete" href="javascript:void(0)">删除</a></span></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="Pagination" class="pager"></div>
<script type="text/javascript">
function allChoose(obj){
	if(obj.checked){
		$(".checkbox").each(function(){
			$(this).attr("checked",true);
		});
		btnStatus("enable");
	}else{
		$(".checkbox").each(function(){
			$(this).attr("checked",false);
		});
	}
}

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
			pagenumber : '${pager.pageNum}',
			pagesize : '${pager.pageSize}',
			pagecount : '${pager.pages }',
			totalcount : '${pager.total}',
			buttonClickCallback : pageClick,
			issel : true,//是否显示选择行数控件 默认为false
			selectChangeCallBack : "selVal"//每页行数下拉回调函数
		});
	});
</script>