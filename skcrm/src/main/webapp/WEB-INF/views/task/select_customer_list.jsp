
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
			<th width="1%"></th>
			<th width="10%">客户简称
			</th>
			<th width="10%">地区
			</th>
			<th width="8%">产品及服务
			</th>
			<th width="8%">负责人
			</th>
		</tr>
	</thead>
	<tbody>

		<c:if test="${pager.total == 0 }">
			<tr>
				<td colspan="5" style="text-align: center">暂无数据</td>
			</tr>
		</c:if>
		<c:forEach var="item" items="${model }">
			<tr ondblclick="showDetail('${item.customerId }','${item.allowAccountName==loginName }')">
				<td><label class="control-label check_home"> <label
						class="label_check"> <input type="checkbox"
							data-client="checkbox_share" data-shareid="${item.shareId }" data-cusname="${item.shortName }" value="${item.customerId }"
							data-name="customer_share_check" data-isfirst="${(loginId<=0)?true:(item.allowAccountName==loginName?true:false)}" id="check_${item.customerId }"
							class="sk_checkbox" />
					</label>
				</label></td>
				<td xyz='z' data-customerid="${item.customerId }" title="${item.shortName }">${extf:subStr(item.shortName,9) }</td>
				<td xyz='z' data-customerid="${item.customerId }">${item.provinceName }</td>
				<td xyz='z' data-customerid="${item.customerId }">${elf:getDictName(item.buyService) }</td>
				<td xyz='z' data-customerid="${item.customerId }">${item.allowAccountName}</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<div id="Pagination" class="pager"></div>

<link rel="stylesheet" href="../content/css/order.css" />
<script type="text/javascript">

//分页选择每页行数下拉回调函数
var selVal = function() {
	$("#pageSize").val($("#pageSizeNum").val());
	$("#myformSelectCustomer").submit();
}

	$(function() {
		// 表头排序 开始
		var oldField = $("#orderField").val();//原始排序字段
		var oldType = $("#orderType").val();//原始排序类别

		if (oldType == "") {
			$("#" + oldField).removeClass("up");
			$("#" + oldField).addClass("down");

		} else {
			$("#" + oldField).removeClass("down");
			$("#" + oldField).addClass("up");

		}

		$(".dev-order").click(function() {
			var $this = $(this);
			var type = $this.attr("id");

			$("#orderField").val(type);//记录排序字段
			
				if ($this.hasClass("up")) {
					$this.removeClass("up");
					$this.addClass("down");
					$("#orderType").val("")
				} else {
					$this.removeClass("down");
					$this.addClass("up");
					$("#orderType").val("DESC")
				}
			
			$("#myformSelectCustomer").submit();

		});
		// 表头排序 结束

		var pageClick = function(pageindex) {
			$("#page").val(pageindex);
			$("#myformSelectCustomer").submit();
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
	//customerId 客户id
	//isFirst 是否是第一负责人
	function showDetail(customerId,isFirst) {
		var data = $("#myformSelectCustomer").serialize();
		if (!parent.$(".left_slide").hasClass("left_slide_show")) {
			parent.$(".left_slide").addClass("left_slide_show");
			parent.$(".left_menu").css("width", "70px");
			parent.$(".right_main").width(
					parent.$("body").width() - parent.$(".left_menu").width());
			parent.$("body").addClass("left_short");
			parent.$(".left_menu ul li span").css("display", "none");
		}
		window.location = "../customer/show?customerId=" + customerId+"&isFirst="+isFirst+"&"+data;
	}
</script>

