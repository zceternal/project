
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
			<th width="1%"><label class="control-label check_home">
					<label class="label_check"> <input type="checkbox"
						data-for="checkbox_share" /></label>
			</label></th>
			<c:if test="${isShowTop || myself.equals('1')}">
				<th width="5%">置顶
					<div id="top" class="empty"></div>
				</th>
			</c:if>
			<th width="10%">客户简称
				<div id="shortNameOrder" class="dev-order up"></div>
			</th>
			<th width="5%">地区
				<div id="provinceName" class="dev-order up"></div>
			</th>
			<th width="10%">产品及服务
				<div id="buyServiceName" class="dev-order up"></div>
			</th>
			<th width="8%">销售推进状态
				<div id="followStateName" class="dev-order up"></div>
			</th>
			<th width="6%">负责人
				<div id="allowAccountName" class="dev-order up"></div>
			</th>
			<th width="8%">销售形式
				<div id="cusSourceName" class="dev-order up"></div>
			</th>
			<th >最近一次推进记录
			<%--	<div id="allowAccountName" class="dev-order up"></div>--%>
			</th>
			<th width="14%">下一步计划
				<%--<div id="allowAccountName" class="dev-order up"></div>--%>
			</th>
			<th width="7%">计划配合人
				<%--<div id="statusName" class="dev-order up"></div>--%>
			</th>
			<th width="6%">计划状态
				<div id="taskStatus" class="dev-order up"></div>
			</th>
			<th width="6%">操作
				<div id="opeartor" class="empty"></div>
			</th>
		</tr>
	</thead>
	<tbody>

		<c:if test="${pager.total == 0 }">
			<tr>
				<td colspan="${isShowTop || myself.equals('1')?13:12}" style="text-align: center">暂无数据</td>
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
				<c:if test="${isShowTop || myself.equals('1')}">
					<td xyz='z' data-customerid="${item.customerId }"><span
						class="colorblue edit"><a href="javascript:void(0)"
							data-share-id="${item.shareId }"
						data-order="${item.order }"
							class="colorblue">置顶</a></span></td>
				</c:if>
				<td xyz='z' data-customerid="${item.customerId }" title="${item.shortName }">${extf:subStr(item.shortName,7) }</td>
				<td xyz='z' data-customerid="${item.customerId }">${item.provinceName }</td>
				<td xyz='z' data-customerid="${item.customerId }">${elf:getDictName(item.buyService) }</td>
				<td xyz='z' data-customerid="${item.customerId }">${elf:getDictName(item.followState) }</td>
				<td xyz='z' data-customerid="${item.customerId }">${item.allowAccountName}</td>
				<c:if test="${item.cusSourceType ==1 }">
					<td xyz='z' data-customerid="${item.customerId }">渠道-${item.cusSource}</td>
				</c:if>
				<c:if test="${item.cusSourceType !=1 }">
					<td xyz='z' data-customerid="${item.customerId }">直销-${elf:getDictName(item.cusSource)}</td>
				</c:if>
				<td xyz='z' data-customerid="${item.customerId }" title="${item.nextReport }">${extf:subStr(item.nextReport,12) }</td>
				<td xyz='z' data-customerid="${item.customerId }" title="${item.nextPlan }">${extf:subStr(item.nextPlan,12) }</td>
				<td xyz='z' data-customerid="${item.customerId }">${item.planExecutorAll }</td>
				<td xyz='z' data-customerid="${item.customerId }">${item.status }</td>
				<td>
					<shiro:hasPermission name="customer_edit">
						<span class="colorblue edit"><a href="javascript:void(0)"
							data-id="customer_edit" data-edit-id="${item.customerId }"
							data-title="${item.shortName }" class="colorblue">修改</a></span>
					</shiro:hasPermission> 
					<%-- <shiro:hasPermission name="customer_remove">
						<span class="colorred delete" data-delete-accid="${item.shareId }"
							data-title="${item.shortName }">删除</span>
					</shiro:hasPermission> --%>
				</td>
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
	$("#myform").submit();
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
			
			$("#myform").submit();

		});
		// 表头排序 结束

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
	//customerId 客户id
	//isFirst 是否是第一负责人
	function showDetail(customerId,isFirst) {
		var data = $("#myform").serialize();
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

