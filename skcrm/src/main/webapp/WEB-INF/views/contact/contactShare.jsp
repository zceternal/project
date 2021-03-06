
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="main_content member_list" style="overflow-y: auto;">

				<div class="panel choose_block">
					<form method="post" action="../contactShare/add"
						class="form-inline pl20" role="form" id="contactShare"
						data-ajax="true" data-ajax-success="contactShareAddSuccess">
						
						<div class="form-group" style="width: 100%">
							<div class="panel-heading color333">当前选择的联系人列表
				</div>
							<table id="tbDate" class="table table-bordered  table-hover ">
								<thead>
									<tr>
										<th width="12%">姓名</th>
										<th >职务</th>
										<th width="32%">所属客户</th>
										<th width="10%">手机</th>
										<th width="8%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${pager.total == 0 }">
										<tr>
											<td colspan="4" style="text-align: center">暂无数据...</td>
										</tr>
									</c:if>
									<c:forEach var="item" items="${model}">										
										<tr>
										    <input type="hidden" name="contactId" value="${item.id}|${item.customerId }">										    
											<td>${item.name }</td>
											<td>${item.position }</td>
											<td>${item.customerName }</td>
											<td>${item.phone }</td>
											<td style="display: none;">${item.customerId }</td>
											<td><span class="colorred delete"
												data-delete-Shareid="${item.id }" data-title="${item.name }">移除</span>
											</td>
										</tr>
									</c:forEach>
								</tbody>

							</table>
						</div>
						<div class="form-group" style="width: 100%; margin-top: 5px;">
							<div class="panel-heading color333">将联系人共享给
				</div>
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
											<td style="text-align: left;"><c:forEach items="${deptAcc.users}" var="users"
													varStatus="status">
														<label  style="margin-right: 10px;vertical-align:0px;" class="label_check"> <input type="checkbox"
								value="${users.accountId }" name="allowAccountId"
								class="sk_checkbox" />
								 ${users.accountName}
						</label>
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
<script>
	$(function() {
		//临时消除共享的联系人数据
		$("span[data-delete-Shareid]").click(function() {
					var $this = $(this);
					$.sk.confirm("您确定要取消<em>" + $this.data("title") + "</em>的分享吗？",
							function(result) {
								if (result) {
									$this.parent().parent().remove();
								}
							});
				});
	});
</script>