<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
#allmap {
	width: 780px;
	height: 300px;
}

.dev-col-sm-120 {width:150px !important;}
.col-sm-4,.col-sm-10,.wh_ul{padding-left:0px}
</style>

	<div class="main_content dialog" >
		<div class="panel panel-default choose_block">
			<form class="form-horizontal old_block" role="form" id="myformEdit"
				action="edit" method="post" data-ajax="true"
				data-ajax-success="onCustomerSuccessEdit">
				<input type="hidden" id="id" name="id" value="${model.id}">
				<input type="hidden" id="contactId" name="contactId" value="${model.contactId}">
				<div class="form-group">
					<label for="" class="col-sm-2 dev-col-sm-120 control-label " >客户名称<em
						class="colorred">*</em>：</label>
						<div class="col-sm-4">
							<input type="text" data-prompt-position="inline"
								class="form-control input200 validate[required,maxSize[50]]"
								id="name" name="name" placeholder="请输入客户名称" keydown="kd()"  value="${model.name }">
					</div>
					<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户简称<em
						class="colorred">*</em>：</label>
						<div class="col-sm-4">
							<input type="text" data-prompt-position="inline"
								class="form-control input200 validate[required,maxSize[30]]"
								id="shortName" name="shortName" placeholder="请输入客户简称" value="${model.shortName }">
					</div>
				</div>
			 <div class="form-group" style="display:none;">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label " >联系人姓名：</label>
						<div class="col-sm-4">
							<input type="text" data-prompt-position="inline"
								class="form-control input200 "
								id="contactName" name="contactName" placeholder="请输入联系人姓名" value="${model.contactName}">
						
						</div>
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">联系人电话：
						</label>
						<div class="col-sm-4">
							<div>
								<input type="text" data-prompt-position="inline"
									class="form-control  input200 "
									id="contactPhone" name="contactPhone" placeholder="请输入联系人电话" value="${model.contactPhone}">
							</div>	
						</div>
					</div>
				

				<div class="form-group wh_group">
					<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户状态：</label>
					<ul class="wh_ul">
						<c:if test="${xszt != null }">
							<c:forEach var="item" items="${xszt }">
								<li><label>
								    <c:if test="${item.id==model.status}">
								    <c:set var="no" value="1"></c:set>
									<input class="dev-xszt" name="status" checked type="radio" value="${item.id }" />${item.name }
									</c:if>
									<c:if test="${item.id!=model.status}">
									<c:if test="${no==1 }">
									<input class="dev-xszt" name="status" type="radio" value="${item.id }" />${item.name }
									</c:if>
									<c:if test="${no!=1 }">
									${item.name }
									</c:if>
									</c:if>
								</label></li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<div class="form-group wh_group">
					<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户类型：</label>
					<ul class="wh_ul">
						<c:if test="${khlx != null }">
							<c:forEach var="item" items="${khlx }">
								<li><label><input name="type" class="dev-khlx"
										type="checkbox" value="${item.id }"
										${types.contains(String.valueOf(item.id))?"checked":""} />${item.name}</label></li>
							</c:forEach>
						</c:if>

					</ul>
				</div>
				<div class="form-group wh_group">
					<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户来源：</label>
					<ul class="wh_ul">
						<c:if test="${khly != null }">
							<c:forEach var="item" items="${khly }">
								<li><label><input name="source" class="dev-khly"
										type="checkbox" value="${item.id }"
										${sources.contains(String.valueOf(item.id))?"checked":""} />${item.name }</label></li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 dev-col-sm-120 control-label">平台版本：</label>
					<div class="col-sm-4">
						<ul class="wh_ul">
							<c:if test="${khcgl != null }">
								<c:forEach var="item" items="${khcgl }">
									<li><label><input class="dev-khcgl" name="salesSuccessRate"
										${item.id==model.salesSuccessRate?"checked":""} type="radio"
										value="${item.id }" />${item.name }</label></li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
					<%--<label for="" class="col-sm-2 dev-col-sm-120 control-label">推荐人：</label>
					<div class="col-sm-4">
						<input type="text" data-prompt-position="inline"
						 class="form-control input200 validate[maxSize[10]]"
							id="recommender" placeholder="请输入推荐人" value="${model.recommender }"
							name="recommender">
					</div>--%>
				</div>

				<div class="form-group">
					<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户网站：</label>
					<div class="col-sm-4">
						<input type="text"
							class="form-control input200 validate[custom[url],maxSize[100]]"
							id="url" name="url" value="${model.url }" placeholder="请输入客户网站">
					</div>
					<label for="" class="col-sm-2 dev-col-sm-120 control-label">联系方式：</label>
					<div class="col-sm-4">
						<input type="text" data-prompt-position="inline"
						 class="form-control input200 validate[custom[mobile_phone],maxSize[20]]"
							id="phone" placeholder="请输入联系方式" value="${model.phone }"
							name="phone">
					</div>
				</div>

				<div class="form-group">
					<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户简介：</label>
					<div class="col-sm-10">
						<textarea class="form-control validate[maxSize[5000]]" id="remark" placeholder="请输入客户简介" name="remark" style="width: 625px" data-prompt-position="inline"
								rows=4>${model.remark }</textarea>
					</div>
				</div>

				<div class="form-group">
					<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户地址：</label>
					<div class="col-sm-10">
						<select id="ResideProvince" style="width: 100px; height: 32px;"
							name="province" onchange="listCity(this)" class="valid inline">
							<option value="-1">请选择</option>
							<c:forEach items="${listProvs}" var="provs">
								<option value="${provs.code}"
									${provs.code==model.province?"selected":""}>${provs.name}</option>
							</c:forEach>
						</select> <select id="cities" name="city"
							style="width: 100px; height: 32px;" onchange="listDict(this)"
							class="valid inline">
							<option value="-1">请选择</option>
							<c:forEach items="${listCity}" var="item">
								<option value="${item.code}"
									${item.code==model.city?"selected":""}>${item.name}</option>
							</c:forEach>
						</select> <select id="dics" name="country"
							style="width: 100px; height: 32px;" class="valid inline">
							<option value="-1">请选择</option>
							<c:forEach items="${listCountry}" var="item">
								<option value="${item.code}"
									${item.code==model.country?"selected":""}>${item.name}</option>
							</c:forEach>
						</select> <input type="text" value="${model.address}"
							class="form-control input2form-control input200 inline validate[maxSize[100]]"
							style="width: 214px !important;" data-prompt-position="inline"
							id="address" name="address" placeholder="">
						<button id="search_map" type="button" class="btn btn_white20">搜索</button>
					</div>
					<br>
					<br> <input type="hidden" name="xCoord" id="xCoord"
						value="${model.xCoord }"> <input type="hidden"
						name="yCoord" id="yCoord" value="${model.yCoord }">
					<div
						style="width: 96%; margin: 0 auto; padding: 5px; border: 1px solid #CCC;">
						<div id="allmap" style="width: 100%; height: 300px;"></div>
					</div>
					</div>
			</form>

		</div>
	</div>


	<script type="text/javascript" src="../content/module/util/provsCities.js"></script>
	<!--引入地图搜索JS (2/2)  -->
	<script type="text/javascript" src="../content/module/util/customer_map_sos.js"></script>
<script type="text/javascript">

	$("#myformEdit").validationEngine({
		ajaxFormValidationMethod : 'post',
		promptPosition : "centerRight"
	});
	$("#name").autocomplete({
	       source: function(request, response){
	    	   $.post("../customer/loadCus.ajax",{value:request.term,type:"1"},function(data){
	    		   response(data);
	    	   });
	    	   
	       },
	       select:function(event, ui){
		    	  // var lable=ui.item.lable;
		    	   var value=ui.item.value; 
		    	   var text=value.substr(0,value.indexOf(" "))
		    	   $("#name").val(text);
		    	   return false;
		       }
	     });
	 
	 $("#shortName").autocomplete({
	       source: function(request, response){
	    	   $.post("../customer/loadCus.ajax",{value:request.term,type:"2"},function(data){
	    		   response(data);
	    	   });
	    	   
	       },
	       select:function(event, ui){
		    	  // var lable=ui.item.lable;
		    	   var value=ui.item.value; 
		    	   var text=value.substr(0,value.indexOf(" "))
		    	   $("#shortName").val(text);
		    	   return false;
		       }
	     });
	 
	 $("input[name='contactName']").autocomplete({
	       source: function(request, response){
	    	   $.post("../contact/loadContact.ajax",{value:request.term,type:"1"},function(data){
	    		   response(data);
	    	   });
	    	   
	       },
	       select:function(event, ui){
		    	   var value=ui.item.value; 
		    	   var text=value.substr(0,value.indexOf(" "))
		    	   $("input[name='contactName']").val(text);
		    	   return false;
		       }
	     });
	 $("input[name='contactPhone']").autocomplete({
	       source: function(request, response){
	    	   $.post("../contact/loadContact.ajax",{value:request.term,type:"2"},function(data){
	    		   response(data);
	    	   });
	    	   
	       },
	       select:function(event, ui){
		    	   var value=ui.item.value; 
		    	   var text=value.substr(0,value.indexOf(" "))
		    	   $("input[name='contactPhone']").val(text);
		    	   return false;
		       }
	     })
</script>

