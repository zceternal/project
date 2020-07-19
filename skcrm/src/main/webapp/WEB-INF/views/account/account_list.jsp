<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<table class="table table-hover ">
	<thead>
		<tr>
			<th width="8%">姓名</th>
			<th width="6%">用户名</th>
			<th width="8%">工号</th>
			<th width="4%">性别</th>
			<th width="10%">联系电话</th>
			<th width="9%">所属部门</th>
			<th width="9%">部门负责人</th>
			<th width="9%">创建时间</th>
			<th width="10%">创建人</th>
			<th width="9%">在职状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<c:if test="${pager.total == 0 }">
			<tr>
				<td colspan="9" style="text-align: center">暂无数据</td>
			</tr>
		</c:if>


		<c:forEach var="item" items="${model}">
			<tr>
				<td>${item.name}</td>
				<td>${item.loginName }</td>
				<td>${item.number }</td>
				<td>${elf:sex(item.sex) }</td>
				<td>${item.phone }</td>
				<td>${elf:getDeptName(item.deptId) }</td>
				<td>${item.isDeptManager == 1 ? '是':'否' }</td>
				<td><fmt:formatDate value="${item.createTime}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${elf:getAccountName(item.createId) }</td>
				<td>
					<c:choose>
						<c:when test="${item.state==0 }">
							在职
						</c:when>
						<c:when test="${item.state==-1 }">
							离职
						</c:when>
					</c:choose>
				</td>
				<td><%-- <shiro:hasPermission name="account_reset_password"> --%>
						<span> <a href="javascript:void(0)"
							data-name="${item.name }" data-id="${item.id }" class="colorblue">重置密码</a>
						</span>
					<%-- </shiro:hasPermission> <shiro:hasPermission name="account_edit"> --%>
						<span class="colorblue edit"> <a href="javascript:void(0)"
							data-edit-id="${item.id }" data-title="${item.name }"
							class="colorblue">修改</a>
						</span>
					<%-- </shiro:hasPermission> <shiro:hasPermission name="account_auth"> --%>
						<span class="colorblue edit"> <a href="javascript:void(0)"
							data-auth-id="${item.id }" data-title="${item.name }"
							class="colorblue">分配权限</a>
						</span>
					<%-- </shiro:hasPermission> <shiro:hasPermission name="account_delete"> --%>
						<c:if test="${item.state==0 }"><span class="colorred delete" data-delete-id="${item.id }"
							data-delete-state="${item.state }" data-title="${item.name }">离职</span>
						</c:if>
						<c:if test="${item.state==-1 }"><span class="colorred delete" data-quit-id="${item.id }"
							data-delete-state="${item.state }" data-title="${item.name }">删除</span>
						</c:if>
					<%-- </shiro:hasPermission><shiro:hasPermission name="account_recover"> --%>
					<c:if test="${item.state==-1 }">
					  <span class="colorblue recover" data-recover-id="${item.id }"
							data-title="${item.name }">恢复 </span>
					</c:if>
					<%-- </shiro:hasPermission> --%>
				</td>				
			</tr>
		</c:forEach>
	</tbody>

</table>
<div id="Pagination" class="pager"></div>
<script type="text/javascript">
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