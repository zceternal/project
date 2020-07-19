<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>

	<table id="customerTb" class="table  table-hover ">
		<thead>
			<tr>
				<th width="7%">选择</th>
				<th width="18%">客户简称</th>
				<th width="13%">销售状态</th>
				<th width="15%">客户电话</th>
				<th>客户地址</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${pager.total == 0 }">
				<tr>
					<td colspan="5" style="text-align: center">暂无数据</td>
				</tr>
			</c:if>
			<c:forEach var="item" items="${model }">
				<tr>
					<td><input data-name="choice"
							data_data="${item.id }" data-shortName="${item.shortName }"
							name="choice" value="${item.shortName }" type="radio" style="margin-top: 11px;"/>
							</td>
					<td title="${item.shortName }">${extf:subStr(item.shortName,9) }</td>
					<td>${elf:getDictName(item.status) }</td>
					<td>${item.phone }</td>
					<td>${item.provinceName} ${item.cityName} ${item.countryName} ${item.address }</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
<div id="PaginationCus" class="pager"></div>

<script type="text/javascript">
	$(function() {
		var pageClick = function(pageindex) {
			$("#pageCus").val(pageindex);
			$("#customerListForm").submit();
		};
		$("#PaginationCus").pager({
			pagenumber : '${pager.pageNum}',
			pagecount : '${pager.pages }',
			totalcount : '${pager.total}',
			buttonClickCallback : pageClick
		});
	});
	//表格tr单击事件
	$("#customerTb>tbody>tr").click("click", function() {
		var rad = $(this).children().find(":input[type=radio]");
		rad.attr("checked", 'checked');
	});
</script>