
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>


<div class="main_content member_list" style="overflow-y: auto">

	<div class="panel choose_block">
		<form method="post" role="form" id="shareAddForm"
			action="../customer_share/share" method="post" data-ajax="true"
			data-ajax-success="onCustomerShareSuccess">
			<input type="hidden" name="isShare" id="isShare" value="0">
			<div class="form-group" style="width: 100%">
				<div class="panel-heading color333">当前选择的需要共享客户</div>
				<table id="tbDate" class="table table-bordered  table-hover ">
					<thead>
						<tr>
							<th width="20%">客户名称</th>
							<th>地区</th>
							<th width="10%">类型</th>
							<th width="20%">分享跟踪记录</th>
							<th width="8%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${pager.total == 0 }">
							<tr>
								<td colspan="3" style="text-align: center">暂无数据</td>
							</tr>
						</c:if>
						<input type="hidden" name="cusnames" value="${cusnames }">
						<c:forEach items="${cusList }" var="cus">
							<tr id="tr_share_${cus.customerId }">
								<input type="hidden" name="customerId" value="${cus.customerId }">
								
								<td title="${cus.name }">${extf:subStr(cus.name,9) }</td>
								<td>${cus.address }</td>
								<td>${cus.typeName==null?"暂无": cus.typeName}</td>
								<td>
									<div id="state_id" class="switch" data-on="danger"
										data-off="primary">
										<input class="dev_checkbox" type="checkbox" />
									</div> <!-- <span>@(item.State == 0 ? "启用" : "禁用")</span> -->
								</td>
								<td><c:if test="${empty isRemove}">
										<a class="colorred delete"
											data-removeshare="${cus.customerId }"
											href="javascript:void(0)">移除</a>
									</c:if></td>
							</tr>
							<c:if test="${not empty cus.contacts[0].name}">
								<tr id="tr_contact_${cus.customerId }">
									<td style="text-align: center;">联系人：</td>
									<td style="text-align: left;" colspan="4"><c:forEach
											items="${cus.contacts}" var="contact">
											<c:if test="${not empty contact.name}">												
												<label style="margin-right: 10px; vertical-align: 0px;"
													class="label_check"> <input type="checkbox"
													value="${cus.customerId},${contact.id }" name="contactId" class="sk_checkbox" />
													 ${contact.name}
												</label>
											</c:if>
										</c:forEach></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>

				</table>
			</div>
			<div class="form-group" style="width: 100%; margin-top: 5px;">
				<div class="panel-heading color333">将客户共享给</div>
				<div class="form-group" style="width: 100%">
					<table id="deptTab" class="table table-bordered table-hover">
						<thead>

							<tr>
								<th width="15%">部门</th>
								<th width="15%">子部门</th>
								<th style="text-align: left;">姓名</th>
							</tr>
						</thead>
						<c:forEach items="${deptAcctsList}" var="deptAcc">
							<c:if test="${deptAcc.users.size()!=0 }">
								<tr>
									<td>${deptAcc.nameLevel1 }</td>
									<td>${deptAcc.nameLevel2 }</td>
									<td style="text-align: left;"><c:forEach
											items="${deptAcc.users}" var="users" varStatus="status">
											<label style="margin-right: 10px; vertical-align: 0px;"
												class="label_check"> <input type="checkbox"
												value="${users.accountId }" name="allowAccountId"
												class="sk_checkbox" /> ${users.accountName}
											</label>
										</c:forEach></td>
								</tr>
							</c:if>
						</c:forEach>
					</table>

				</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		/* 开启跟禁用的引用 */
		$('.dev_checkbox').bootstrapSwitch({
			onText : '共享',
			offText : "不共享",
			size : "mini",
			onSwitchChange : function() {
				var state = $(this).attr("checked") == undefined ? "0" : "1";
				$("#isShare").val(state);
				var id = $(this).data("id");
			}
		});
	});
</script>
