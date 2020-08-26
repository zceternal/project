
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>


<table class="table table-hover ">
	<thead>
		<tr>
			<!-- <th width="1%"><label class="control-label check_home">
					<label class="label_check"> <input type="checkbox"
						data-for="checkbox_share" /></label>
			</label></th> -->
			<c:if test="${isShowTop || myself.equals('1')}">
				<th width="5%">置顶
					<div id="top" class="empty"></div>
				</th>
			</c:if>
			<th width="10%">客户简称
				<div id="shortNameOrder" class="dev-order up"></div>
			</th>
			<th width="8%">客户状态
				<div id="statusName" class="dev-order up"></div>
			</th>
			<!-- <th width="">联系方式
				<div id="phone" class="dev-order up"></div>
			</th> -->

			<th width="8%">地区
				<div id="provinceName" class="dev-order up"></div>
			</th>
			<th width="10%">类型
				<div id="typeName" class="dev-order up"></div>
			</th>
			<th width="9%">负责人
				<div id="allowAccountName" class="dev-order up"></div>
			</th>
			<th width="10%">创建时间
				<div id="createTime" class="dev-order up"></div>
			</th>
			<th>操作
				<div id="opeartor" class="empty"></div>
			</th>
		</tr>
	</thead>
	<tbody>

		<c:if test="${pager.total == 0 }">
			<tr>
				<td colspan="${isShowTop || myself.equals('1')?11:10}" style="text-align: center">暂无数据</td>
			</tr>
		</c:if>
		<c:forEach var="item" items="${model }">
			<tr ondblclick="showDetail('${item.customerId }')">
				<%-- <td><label class="control-label check_home"> <label
						class="label_check"> <input type="checkbox"
							data-client="checkbox_share"  value="${item.customerId }"
							data-name="customer_share_check" data-isfirst="${(loginId<=0)?true:(item.allowAccountName==loginName?true:false)}" id="check_${item.customerId }"
							class="sk_checkbox" />
					</label>
				</label></td> --%>
				<c:if test="${isShowTop || myself.equals('1')}">
					<td xyz='z' data-customerid="${item.customerId }"><span
						class="colorblue edit"><a href="javascript:void(0)"
							data-share-id="${item.shareId }"
							<%-- data-allowaccount-id="${item.allowAccountId }" --%>
						data-order="${item.order }"
							class="colorblue">置顶</a></span></td>
				</c:if>
				<td xyz='z' data-customerid="${item.customerId }"
					title="${item.shortName }">${extf:subStr(item.shortName,9) }</td>
				<td xyz='z' data-customerid="${item.customerId }">${item.statusName }</td>
				<%-- <td xyz='z' data-customerid="${item.customerId }"}">${item.phone}</td> --%>
			
				<td xyz='z' data-customerid="${item.customerId }">${item.provinceName }</td>
				<td xyz='z' data-customerid="${item.customerId }"
					title="${item.typeName}">${extf:subStr(item.typeName,5)}</td>
				<td xyz='z' data-customerid="${item.customerId }">${item.allowAccountName}</td>
				<td xyz='z' data-customerid="${item.customerId }"><fmt:formatDate
						value="${item.createTime }" pattern="yyyy-MM-dd" /></td>
				<td>
					<%-- <span class="colorblue edit"><a href="javascript:void(0)"  onclick="showDetail('${item.customerId }')" title="查看详情" class="colorblue">详情</a></span> --%>
					<shiro:hasPermission name="highseas_list">
						<span class="colorblue edit"><a href="javascript:void(0)"
							data-id="customer_edit" data-edit-id="${item.customerId }"
							data-title="${item.shortName }" class="colorblue">抢占</a></span>
					</shiro:hasPermission> 
				</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<div id="Pagination" class="pager"></div>

<script type="text/javascript"
	src="../content/module/highseas/highseas.list.js"></script>
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
	function showDetail(customerId) {
		window.location = "../highseas/higheasshow?customerId=" + customerId;
	}
</script>

