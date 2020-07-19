<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:if test="${contacts.size()==0 }">
	<div
		style="padding-top: 10px; text-align: left; border-top: none; padding-bottom: 3px; padding-left: 10px;">
		暂无共享联系人，请添加</div>
</c:if>
<c:if test="${contacts.size()!=0 }">
	<div style="padding-top: 10px;">
		<table width="100%">
			<tr>
				<td class="color999 font12">姓名</td>
				<td class="color999 font12">职称</td>
				<td class="color999 font12 " style="text-align: center">电话</td>
				<td class="color999 font12 " style="text-align: center">修改</td>
			</tr>
			<c:forEach items="${contacts }" var="contact">
				<tr style="cursor: pointer;" title="双击查看详情"
					ondblclick="dbShowDetails(${contact.id })" height="30px">
					<td width="20%" class="color333">${contact.name }</td>
					<td width="25%" class="color333">${contact.position!=null&&contact.position!=''?contact.position:"暂无" }</td>
					<td width="40%" class="color333" style="text-align: center">${contact.phone!=null&&contact.phone!=''?contact.phone:"暂无" }</td>
					<td width="15%" class="color333" style="text-align: center">
						<div class="colorblue edit">
							<shiro:hasPermission name="contact_edit">
								<a href="javascript:void(0)" id="customerContactId"
									data-editId="${contact.id }"><div class="glyphicon glyphicon-edit"></div></a>
							</shiro:hasPermission>
						</div>
					</td>
				</tr>

			</c:forEach>
		</table>
	</div>
</c:if>
