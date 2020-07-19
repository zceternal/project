<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>

<div style="padding-top: 10px; text-align: left;">
<label class="color999 font12">客户网址：</label> 
<div class="color333">
<a target="_blank" style="color: #23b2cc;" href='${customerInfo.url==null?"javascript:void(0)":customerInfo.url }' > ${extf:subStrByBr((customerInfo.url==null?"":customerInfo.url),30) }</a></div>
</div>
<div style="padding-top: 10px; text-align: left;">
<label class="color999 font12">联系方式：</label><div class="color333">${customerInfo.phone }</div>
</div>
<div style="padding-top: 10px; text-align: left;">
<label class="color999 font12">客户地址：</label> <div class="color333">
	<c:if test="${customerInfo.provinceName!=null }">
		${customerInfo.provinceName }
	</c:if> <c:if test="${customerInfo.cityName!=null }">
		${customerInfo.cityName }
	</c:if> <c:if test="${customerInfo.countryName!=null }">
		${customerInfo.countryName }
	</c:if> <c:if test="${customerInfo.address!=null }">
		${customerInfo.address }
	</c:if>

	</div>
</div>

<div style="padding-top: 10px; text-align: left;">
<label class="color999 font12">客户简介：</label><div class="color333">${customerInfo.remark }</div>
</div>
									
