<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>

<div class="main_content member_list" style="overflow-y: auto">

	<div class="panel choose_block">
		<form method="post" role="form" id="transferAddForm"
			action="transfer" method="post" data-ajax="true"
			data-ajax-success="onCustomertransferSuccess">

			<div class="form-group" style="width: 100%">
				<div class="panel-heading color333">当前选择的需要转移客户</div>
				<table id="tbDate" class="table table-bordered  table-hover ">
					<thead>
						<tr>
							<th width="20%">客户名称</th>
							<th width="10%">产品及服务</th>
							<th>地区</th>
							<th width="10%">类型</th>
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
								<input type="hidden" name="customerId"
									value="${cus.customerId }">
									<input type="hidden" name="customerShareId"
									value="${cus.customerShareId }">
								<td title="${cus.name }">${extf:subStr(cus.name,9) }</td>
								<td >${cus.buyService }</td>
								<td>${cus.address }</td>
								<td>${cus.typeName==null?"暂无": cus.typeName}</td>
								<td><a class="colorred delete" data-removeshare="${cus.customerId }" href="javascript:void(0)">移除</a></td>

							</tr>

						</c:forEach>
					</tbody>

				</table>
			</div>
			<div class="form-group" style="width: 100%; margin-top: 5px;">
				<div class="panel-heading color333">将客户转移给</div>
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
												<label style="margin: 3px 18px 0px 2px;cursor: pointer;">
												<input type="radio"
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

