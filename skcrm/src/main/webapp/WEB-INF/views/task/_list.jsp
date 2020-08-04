
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
			<th width="1%"><label class="control-label check_home">
					<label class="label_check"> <input type="checkbox"
						data-for="checkbox_share" /></label>
			</label></th>
			<c:if test="${isShowTop || myself.equals('1')}">
				<th width="5%">置顶
					<div id="top" class="empty"></div>
				</th>
			</c:if>
			<th width="10%">任务象限
				<div id="shortNameOrder" class="dev-order up"></div>
			</th>
			<th width="8%">下一步工作计划
				<div id="statusName" class="dev-order up"></div>
			</th>
			<th width="12%">计划标准
				<div id="finalTime" class="dev-order up"></div>
			</th>
			<th width="8%">计划执行人
				<div id="provinceName" class="dev-order up"></div>
			</th>
			<th width="10%">计划反馈时间
				<div id="typeName" class="dev-order up"></div>
			</th>
			<th width="9%">任务性质
				<div id="allowAccountName" class="dev-order up"></div>
			</th>
			<th width="9%">指派者
				<div id="createTime" class="dev-order up"></div>
			</th>
			<th width="10%">指派时间
			<div id="opeartor" class="empty"></div>
			</th>
			<th>任务状态
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
			<tr ondblclick="showDetail('${item.taskId }','${item.allowAccountName==loginName }')">
				<td><label class="control-label check_home">
					<label class="label_check">
					<input type="checkbox" data-client="checkbox_share" data-shareid="${item.shareId }" id="check_${item.id }" class="sk_checkbox" />
					</label>
				</label></td>
				<c:if test="${isShowTop || myself.equals('1')}">
					<td  data-taskid="${item.id }"><span
						class="colorblue edit"><a href="javascript:void(0)"
							data-share-id="${item.shareId }"
						data-order="${item.order }"
							class="colorblue">置顶</a></span></td>
				</c:if>
				<td  data-taskid="${item.id }">${item.quadrant }</td>
				<td  data-taskid="${item.id }">${item.nextPlan }</td>
				<td  data-taskid="${item.id }">${item.planStandard}</td>
				<td  data-taskid="${item.id }">${item.planExecutor }</td>
				<td  data-taskid="${item.id }"><fmt:formatDate value="${item.backTime }" pattern="yyyy-MM-dd" /></td>
				<td  data-taskid="${item.id }">${item.taskNature}</td>
				<td  data-taskid="${item.id }">${item.assignPerson}</td>
				<td  data-taskid="${item.id }"><fmt:formatDate value="${item.assignTime }" pattern="yyyy-MM-dd" /></td>
				<td  data-taskid="${item.id }">${item.status}</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<div id="Pagination" class="pager"></div>

<script type="text/javascript" src="../content/module/task/task.list.js"></script>
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

</script>

