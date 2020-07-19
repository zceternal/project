<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<label class="color999 font12">其他负责人：</label>
<div  class="color333">
<c:if test="${customerShares.size()==0 }">
	暂无
</c:if>
<c:forEach items="${customerShares }" var="share">
<c:if test="${share.allowName!=null && share.allowName!='' }">
	${share.allowName }&nbsp;&nbsp;${share.phone }</br>
	</c:if>
</c:forEach>
</div>					
