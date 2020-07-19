<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>


				<table class="table table-bordered  table-hover ">
					<thead>
						<tr>
							<th>部门名称</th>
							<th>备注</th>
							<th>创建时间</th>
							<th width="300px">操作</th>
						</tr>
					</thead>
					<tbody>

						<c:if test="${pager.total == 0 }">
							<tr>
								<td colspan="4" style="text-align: center">暂无数据</td>
							</tr>
						</c:if>


						<c:forEach var="item" items="${model}">
							<tr>
								<td>${item.name}</td>
								<td>${item.remark }</td>
								<td><fmt:formatDate value="${item.createTime}"
										pattern="yyyy-MM-dd HH:mm" /></td>
								<td>
								<%-- <shiro:hasPermission name="dept_edit"> --%>
									<span class="colorblue edit">
										<a href="javascript:void(0)" data-up-id="${item.id }" data-pid="${item.pid }" data-action="up"  class="colorblue">上移</a>
									</span>
									<span class="colorblue edit">
										<a href="javascript:void(0)" data-down-id="${item.id }" data-pid="${item.pid }" data-action="down"  class="colorblue">下移</a>
									</span>
									<span class="colorblue edit">
										<a href="javascript:void(0)" data-edit-id="${item.id }" data-title="${item.name }" class="colorblue">修改</a>
									</span> 
								<%-- </shiro:hasPermission>
								<shiro:hasPermission name="dept_auth"> --%>
									<span class="colorblue edit">
										<a href="javascript:void(0)" data-auth-id="${item.id }" data-title="${item.name }" class="colorblue">分配权限</a>
									</span> 
								<%-- </shiro:hasPermission>	
								<shiro:hasPermission name="dept_delete"> --%>
									<span class="colorred delete" data-delete-id="${item.id }"
									data-title="${item.name }">删除</span></td>
								<%-- </shiro:hasPermission> --%>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			
			<div id="Pagination" class="pager"></div>
		

<script type="text/javascript">

//分页选择每页行数下拉回调函数
var selVal = function() {
	$("#pageSize").val($("#pageSizeNum").val());
	$("#form_search").submit();
}

		$(function() {
			var pageClick = function(pageindex) {
				$("#page").val(pageindex);
				$("#form_search").submit();
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