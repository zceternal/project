<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<table class="table table-hover ">
	<thead>
		<tr>
			<th width="10%">名称</th>
			<th width="30%">成员</th>
			<th width="20%">备注</th>
			<th width="15%">创建时间</th>
			<th width="10%">创建人</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty model or model.size()==0}">
			<tr>
				<td colspan="6" style="text-align: center">暂无数据</td>
			</tr>
		</c:if>


		<c:forEach var="item" items="${model}">
			<tr>
				<td>${item.name}</td>
				<td>${item.accountList }</td>
				<td>${item.remark }</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${item.createName }</td>
				<td>
					<%-- <shiro:hasPermission name="group_edit"> --%>
						<span class="colorblue edit"> <a href="javascript:void(0)"
							data-edit-id="${item.id }" data-title="${item.name }"
							class="colorblue">修改</a>
						</span>
					<%-- </shiro:hasPermission> 
					<shiro:hasPermission name="group_delete"> --%>
						<span class="colorred delete" data-delete-id="${item.id }"
							data-title="${item.name }">删除 </span>
					<%-- </shiro:hasPermission> --%></td>
			</tr>
		</c:forEach>
	</tbody>

</table>