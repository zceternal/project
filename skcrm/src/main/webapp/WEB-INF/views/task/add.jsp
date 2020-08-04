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
						<table class="table table-bordered table-hover" border="1" cellspacing="0" cellpadding="0">
							<thead>
							</thead>
							<tbody>
							<tr>
								<td rowspan="${taskxz.size()}">任务性质</td>
							</tr>

								<c:if test="${taskxz != null }">
									<c:forEach var="item" items="${taskxz }" varStatus="sta">
										<tr>
											<td><input class="dev-cpfw" name="buyService" ${sta.index == 0 ? 'checked':'' } type="radio" value="${item.id }" />${item.name }</td>
										</tr>
									</c:forEach>
								</c:if>

							</tbody>
						</table>
						<%--<ul class="wh_ul">
							<c:if test="${taskxz != null }">
								<c:forEach var="item" items="${taskxz }" varStatus="sta">
									<li><label><input class="dev-cpfw" name="buyService" ${sta.index == 0 ? 'checked':'' } type="radio" value="${item.id }" />${item.name }</label></li>
								</c:forEach>
							</c:if>
						</ul>--%>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">下一步工作计划：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[maxSize[500]]" id="synopsis" placeholder="请输入下一步工作计划" name="synopsis" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">计划标准：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[maxSize[500]]" id="synopsis" placeholder="请输入计划标准" name="synopsis" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
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
								<td>
									<span id="sp_channelPartner"></span>
									<input type="hidden" name="channelPartner" id="hid_channelPartner">
									<a href="javascript:void(0)" data-id="contact_list" data-bz="channelPartner">+</a>
								</td>
								<td>
                                    <span id="sp_trustPerson"></span>
                                    <input type="hidden" name="trustPerson" id="hid_trustPerson">
                                    <a href="javascript:void(0)" data-id="contact_list" data-bz="trustPerson">+</a>
                                </td>
								<td>
                                    <span id="sp_decisionPerson"></span>
                                    <input type="hidden" name="decisionPerson" id="hid_decisionPerson">
                                    <a href="javascript:void(0)" data-id="contact_list" data-bz="decisionPerson">+</a>
                                </td>
								<td>客户管理人</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
                                    <span id="sp_managePerson"></span>
                                    <input type="hidden" name="managePerson" id="hid_managePerson">
                                    <a href="javascript:void(0)" data-id="contact_list" data-bz="managePerson">+</a>
                                </td>
								<td>客户办事人</td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
                                    <span id="sp_handlePerson"></span>
                                    <input type="hidden" name="handlePerson" id="hid_handlePerson">
                                    <a href="javascript:void(0)" data-id="contact_list" data-bz="handlePerson">+</a>
                                </td>
								<td>客户业务人</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
                                    <span id="sp_professionalPerson"></span>
                                    <input type="hidden" name="professionalPerson" id="hid_professionalPerson">
                                    <a href="javascript:void(0)" data-id="contact_list" data-bz="professionalPerson">+</a>
                                </td>
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

	$("a[data-id=contact_list]").click(function() {
		var $this = $(this);
        var bz = $this.data("bz");
		dialog = $.sk.open({
			url : "select_contact_list",
			width : 1000,
			height : 600,
			overflow:scroll,
			title : "选择关系人",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					var ids = "";
					var names = "";
					$(":checkbox[class=sk_checkbox]:checked").each(function() {
						var ck = $(this);
						ids += ck.val() + ",";
						names += ck.data("name") + ",";
					});
					if (names) {
						names = names.substr(0, names.length - 1);
						ids = ids.substr(0, ids.length - 1);
					}
					$("#sp_"+bz).text(names);
					$("#hid_"+bz).val(ids);
				}
			} ]
		});
	});

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