<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>

<div style="padding-top: 10px; text-align: left;">
<label class="color999 font12">渠道伙伴：</label><div class="color333">${customerRelations.channelPartner }</div>
</div>
<div style="padding-top: 10px; text-align: left;">
	<label class="color999 font12">客户信任人：</label><div class="color333">${customerRelations.trustPerson }</div>
</div>
<div style="padding-top: 10px; text-align: left;">
	<label class="color999 font12">客户决策人：</label><div class="color333">${customerRelations.decisionPerson }</div>
</div>
<div style="padding-top: 10px; text-align: left;">
	<label class="color999 font12">客户管理人：</label><div class="color333">${customerRelations.managePerson }</div>
</div>
<div style="padding-top: 10px; text-align: left;">
	<label class="color999 font12">客户办事人：</label><div class="color333">${customerRelations.handlePerson }</div>
</div>
<div style="padding-top: 10px; text-align: left;">
	<label class="color999 font12">客户业务人：</label><div class="color333">${customerRelations.professionalPerson }</div>
</div>

									
