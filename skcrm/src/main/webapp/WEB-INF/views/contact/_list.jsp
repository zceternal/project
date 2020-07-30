<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>


<form id="shareForm">
	<table id="tbDate" class="table table-hover">
		<thead>
			<tr>
				<th width="1%"><label class="control-label check_home">
						<label class="label_check"> <input type="checkbox"
							data-for="selectOption" class="sk_checkbox" />
					</label>
				</label></th>
				<c:if test="${isShowTop || myself.equals('1')}">
				<th width="5%">置顶<div id="top" class="empty" ></div></th>
				</c:if>
				<th width="6%">姓名<div id="name" class="dev-order up"></div></th>
				<th width="9%">职务<div id="position" class="dev-order up"></div></th>
				<th width="5%">手机<div id="phone" class="empty" ></div></th>
				<!-- <th width="4%">QQ</th> -->
				<th width="200px;">邮箱<div id="email" class="empty" ></div></th>	
				<th width="15%">所属客户<div id="customerNameOrder" class="dev-order up"></div></th>
				<th width="7%">角色<div id="role" class="dev-order up"></div></th>
				<!-- <th width="8%">创建人</th> -->
				<th width="10%">创建日期<div id="createTime" class="dev-order up"></div></th>
				<th >操作<div id="opeator" class="empty" ></div></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${pager.total == 0 }">
				<tr>
					<td colspan="${isShowTop || myself.equals('1') ?11:10}" style="text-align: center">暂无数据</td>
				</tr>
			</c:if>

			<c:forEach var="item" items="${model}" >
				<tr ondblclick="showDetails(${item.id })">
					<td><label class="control-label check_home"> <label
							class="label_check"> <input type="checkbox"
								value=${item.id } data-name=${item.name} name="id" data-customerid=${item.customerId } data-client="selectOption"
								class="sk_checkbox" />
						</label>
					</label></td>
					<c:if test="${isShowTop || myself.equals('1')}">
					<td><span class="colorblue edit"><a
							href="javascript:void(0)" data-id="${item.id }"
							data-sort="${item.sort }" class="colorblue">置顶</a></span></td>
								</c:if>
					<td>${item.name }</td>
					<td title="${item.position }">${extf:subStr(item.position,5) }</td>
					<td>${item.phone }</td>
					<%-- <td>${item.qq }</td> --%>
					<td>${item.email }</td>
					<td title="${item.customerName }">${extf:subStr(item.customerName,9)}</td>
					<td>${elf:getDictName(item.role) }</td>
					<%-- <td>${item.CName }</td> --%>
					<td><fmt:formatDate value="${item.createTime}"
							pattern="yyyy-MM-dd" /></td>

					<td>
					<%-- <span> <shiro:hasPermission name="contact_details">
								<a href="javascript:void(0)" data-details-id="${item.id }"
									data-title="${item.name }" class="colorblue" title="查看详情" >查看</a>
							</shiro:hasPermission>
					</span> --%>
					 <span class="colorblue edit"> <shiro:hasPermission
								name="contact_edit">
								<a href="javascript:void(0)" data-edit-id="${item.id }"
									data-title="${item.name }" class="colorblue">修改</a>
							</shiro:hasPermission>
					</span> 
					<%-- <shiro:hasPermission name="contact_remove">
							<span class="colorred delete" data-delete-id="${item.id }"
								data-title="${item.name }">删除</span>
						</shiro:hasPermission> --%>
						
						</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</form>
<div id="Pagination" class="pager"></div>
	<link rel="stylesheet" href="../content/css/order.css" />
<script type="text/javascript">

//分页选择每页行数下拉回调函数
var selVal = function() {
	$("#pageSize").val($("#pageSizeNum").val());
	$("#contactIndex").submit();
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

			$("#contactIndex").submit();

		});
		// 表头排序 结束
		
	var pageClick = function(pageindex) {
			$("#page").val(pageindex);
			$("#contactIndex").submit();
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
