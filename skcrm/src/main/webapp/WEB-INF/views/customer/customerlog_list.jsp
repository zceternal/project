<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>

<c:if test="${customerLog.size()==0 }">
	<div
		style="padding-top: 10px; text-align: left; border-top: none; padding-bottom: 3px; padding-left: 10px;">
		暂无数据</div>
</c:if>
<c:if test="${customerLog.size()!=0 }">
	<div style="padding-top: 10px;">
		<table width="100%">
			<tr>
				<td class="color999 font12">状态</td>
				<td class="color999 font12">操作人</td>
				<td class="color999 font12 " style="text-align: center">操作日期</td>
				<td class="color999 font12 " style="text-align: center">关联用户</td>
			</tr>
			<c:forEach items="${customerLog }" var="log">
				<tr style="cursor: pointer;"  height="30px">
				
				<td width="15%" class="color333">
				<c:choose>
				<c:when test="${log.type==0 }">新增</c:when>
				<c:when test="${log.type==1 }">共享</c:when>
				<c:when test="${log.type==2 }">转移</c:when>
				<c:when test="${log.type==-1 }">删除</c:when>
				<c:when test="${log.type==3 }">抢占</c:when>
				<c:when test="${log.type==4 }">公海</c:when>
				<c:when test="${log.type==5 }">更新状态</c:when>
				<c:otherwise>未知</c:otherwise>
				</c:choose>
				</td>
					<td width="20%" class="color333">${log.createName }</td>
					<td width="30%" class="color333" style="text-align: center"><fmt:formatDate value="${log.createTime }" type="both" pattern="yyyy/MM/dd hh:mm"/></td>
					<td class="color333" style="text-align: center" title="${log.accountNames }">${extf:subStr(log.accountNames,7) }<c:if test="${log.state==-1 }"><span style="color: red;">(已离职)</span></c:if></td>
				
				</tr>

			</c:forEach>
		</table>
	</div>
</c:if>
