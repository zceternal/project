<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.dev-col-sm-120 {width:134px !important;}
.col-sm-4,.col-sm-10,.wh_ul{padding-left:0px}
</style>
	<div class="main_content dialog">
			<div class="panel panel-default">
				<form class="form-horizontal old_block" role="form" id="myformAdd"
					action="add" method="post" data-ajax="true" data-ajax-success="onCustomerSuccessAdd">
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label " >客户名称<em
						class="colorred">*</em>：</label>
						<div class="col-sm-4">
							<input type="text" data-prompt-position="inline"
								class="form-control input200 validate[required,maxSize[50],ajax[ajaxNameCall]]"
								id="name" name="name" placeholder="请输入客户名称" keydown="kd()">
						</div>
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户简称<em
						class="colorred">*</em>：</label>
						<div class="col-sm-4">
							<input type="text" data-prompt-position="inline"
								class="form-control input200 validate[required,maxSize[30],ajax[ajaxShortNameCall]]"
								id="shortName" name="shortName" placeholder="请输入客户简称">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label " >联系人姓名<em
						class="colorred">*</em>：</label>
						<div class="col-sm-4">
							<input type="text" data-prompt-position="inline"
								class="form-control input200 validate[required,maxSize[30],minSize[2]]"
								id="contactName" name="contactName" placeholder="请输入联系人姓名">
						</div>
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">联系人电话<em class="colorred">*</em>：
					    </label>
						<div class="col-sm-4">
							<div>
								<input type="text" data-prompt-position="inline"
									class="form-control  input200 validate[required,custom[mobile]]"
									id="contactPhone" name="contactPhone" placeholder="请输入联系人电话">
							</div>	
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">产品及服务：</label>
						<ul class="wh_ul">
							<c:if test="${cpfw != null }">
								<c:forEach var="item" items="${cpfw }" varStatus="sta">
									<li><label><input class="dev-cpfw" name="buyService" ${sta.index == 0 ? 'checked':'' } type="radio" value="${item.id }" />${item.name }</label></li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
					<%--<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户状态：</label>
						<ul class="wh_ul">
								<c:if test="${xszt != null }">
								<c:forEach var="item" items="${xszt }" varStatus="sta">
									<li><label><input class="dev-xszt" name="status" ${sta.index == 0 ? 'checked':'' } type="radio" value="${item.id }" />${item.name }</label></li>
									</c:forEach>
								</c:if>
						</ul>
					</div>--%>
					<%--<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户类型：</label>
						<ul class="wh_ul">
								<c:if test="${khlx != null }">
								<c:forEach var="item" items="${khlx }">
									<li><label><input  name="type" class="dev-khlx" type="checkbox" value="${item.id }" />${item.name }</label></li>
									</c:forEach>
								</c:if>
						</ul>
					</div>--%>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户来源：</label>
						<div class="wh_ul">
							<label class="col-sm-2 control-label wh_lab" style="text-align: left; padding-left:0px; margin-right: -20px;">渠道-</label>
							<ul class="wh_ul">
								<c:if test="${lxr != null }">
									<c:forEach var="item" items="${lxr }">
										<li><label><input  name="cusSource" class="dev-khly" type="radio" value="${item.id }" />${item.name }</label></li>
									</c:forEach>
								</c:if>
							</ul>
							<label class="col-sm-2 control-label wh_lab" style="text-align: left; padding-left:0px; margin-right: -20px;">直销-</label>
							<ul class="wh_ul">
								<c:if test="${khly != null }">
									<c:forEach var="item" items="${khly }">
										<li><label><input  name="cusSource" class="dev-khly" type="radio" value="${item.id }" />${item.name }</label></li>
									</c:forEach>
								</c:if>

							</ul>
						</div>
					</div>
					
					<%--<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label">平台版本：</label>
						<div class="col-sm-4">
							<ul class="wh_ul">
								<c:if test="${ptbb != null }">
								<c:forEach var="item" items="${ptbb }" varStatus="sta">
									<li><label><input class="dev-khcgl" name="salesSuccessRate" ${sta.index == 0 ? 'checked':'' } type="radio" value="${item.id }" />${item.name }</label></li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">推荐人：</label>
						<div class="col-sm-4">
							<input type="text" data-prompt-position="inline"
								class="form-control input200 validate[maxSize[10]]"
								id="recommender" placeholder="请输入推荐人" name="recommender" maxLength = 10>
						</div>
					</div>--%>
					
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户网站：</label>
						<div class="col-sm-4">
							<input type="text" data-prompt-position="inline"
								class="form-control input200 validate[maxSize[100],custom[url]]"
								id="url" name="url" placeholder="请输入客户网站">
						</div>
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">联系方式：</label>
						<div class="col-sm-4">
							<input type="text" data-prompt-position="inline"
								class="form-control input200 validate[maxSize[20],custom[mobile_phone]]"
								id="phone" placeholder="请输入联系方式" name="phone">
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户地址：</label>
						<div class="col-sm-10">
						   <select id="ResideProvince" style="width:100px;height:32px;" name="province" onchange="listCity(this)" class="valid inline">
								<option value="-1">请选择</option>	
								<c:forEach items="${listProvs}" var="provs">
									<option value="${provs.code}">${provs.name} </option>
								</c:forEach>
							</select>
							<select id="cities" name="city" style="width:100px;height:32px;" onchange="listDict(this)" class="valid inline">
								<option value="-1">请选择</option>
							</select>
							
							<select id="dics" name="country" style="width:100px;height:32px;" class="valid inline">
								<option value="-1">请选择</option>
							</select>

							<input type="text"
								class="form-control input200 validate[maxSize[100]] inline" style="width:214px!important" data-prompt-position="inline"
								id="address" name="address" placeholder="">
								<button id="search_map" type="button" class="btn btn_white20">搜索</button>
						</div><br><br>
						<input type="hidden" name="xCoord" id="xCoord">
						<input type="hidden" name="yCoord" id="yCoord">
						<div style="width: 96%;margin: 0 auto;padding: 5px;border: 1px solid #CCC;">
						<div id="allmap" style="width:100%;height:300px;"></div>
						</div>
						</div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户简介：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[maxSize[500]]" id="synopsis" placeholder="请输入客户简介" name="synopsis" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
					</div>
					<%--<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户行业：</label>
					</div>--%>

					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户出钱性质：</label>
						<ul class="wh_ul">
							<c:if test="${cqxz != null }">
								<c:forEach var="item" items="${cqxz }">
									<li><label><input  name="payNature" class="dev-khcqxz" type="radio" value="${item.id }" />${item.name }</label></li>
								</c:forEach>
							</c:if>
						</ul>
					</div>

					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">销售推进状态：</label>
						<ul class="wh_ul">
							<c:if test="${xstjzt != null }">
								<c:forEach var="item" items="${xstjzt }">
									<li><label><input  name="followState" class="dev-cjzt" type="radio" value="${item.id }" />${item.name }</label></li>
									<c:if test="${item.childList != null }">
										<c:forEach var="child" items="${item.childList }">
											<li><label><input  name="followStateDetails" class="dev-cjztmx" type="checkbox" value="${child.id }" />${child.name }</label></li>
										</c:forEach>
									</c:if>
								</c:forEach>
							</c:if>
						</ul>
					</div>

					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">人际关系图：</label>
						<div class="col-sm-12">
							<table class="table table-bordered table-hover" border="1" cellspacing="0" cellpadding="0">
							<thead>
							</thead>

							<tbody>
							<tr>
								<td>负责人</td>
								<td>渠道伙伴</td>
								<td>客户信任人</td>
								<td>客户决策人</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>自己</td>
								<td><a>+</a></td>
								<td><a>+</a></td>
								<td><a>+</a></td>
								<td>客户管理人</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><a>+</a></td>
								<td>客户办事人</td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><a>+</a></td>
								<td>客户业务人</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><a>+</a></td>
							</tr>
							</tbody>
						</table>
						</div>
					</div>
				</form>

		</div>
	</div>

	<script type="text/javascript" src="../content/module/util/provsCities.js"></script>
	<!--引入地图搜索JS (2/2)  -->
	<script type="text/javascript" src="../content/module/util/baidu_map_util.js"></script>
<script type="text/javascript">



	$("#myformAdd").validationEngine({
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
	     })
	 
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