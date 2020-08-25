<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>

<style>
.dev-col-sm-120 {width:134px !important;}
.col-sm-4,.col-sm-10,.wh_ul{padding-left:0px}
</style>
	<div class="main_content dialog">
			<div class="panel panel-default">
				<form class="form-horizontal old_block" role="form" id="myformAddRemind"
					action="record" method="post" data-ajax="true" data-ajax-success="onRecordSuccessAdd">
					<div class="form-group">
						<input id="id" name="id" type="hidden" title="客户id" value="${model.id }" />
						<input id="customerId" name="customerId" type="hidden" title="客户id" value="${model.id }" />

						<label for="" class="col-sm-2 dev-col-sm-120 control-label">客户简称：</label>
						<div class="col-sm-4">
							${model.shortName}
						</div>
						<label for="" class="col-sm-2 dev-col-sm-120 control-label " >产品与服务：</label>
						<div class="col-sm-4">
							${elf:getDictName(model.buyService)}
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label " >沟通日期：</label>
						<div class="col-sm-4">
							<div class="inline relative mr0">
							<input type="text" class="form-control input150" placeholder="请输入计划反馈时间" name="communicationTime" id="communicationTime" input-type="date" value="${currentDate}">
							<span class="date_icon" style="cursor: pointer;"><i></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">沟通方式：</label>
						<ul class="wh_ul">
							<c:if test="${gtfs != null }">
								<c:forEach var="item" items="${gtfs }" varStatus="sta">
									<li><label><input class="dev-gtfs" name="communicationWay" ${sta.index == 0 ? 'checked':'' } type="radio" value="${item.id }" />${item.name }</label></li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户联系人：</label>
						<ul class="wh_ul">
								<table class="table table-bordered table-hover" border="1" cellspacing="0" cellpadding="0">
									<thead> </thead>
									<tbody>
									<tr>
										<td>决策人</td>
										<td>管理人</td>
										<td>办事人</td>
										<td>业务人</td>
										<td><a>+</a></td>
									</tr>
									<tr>
										<c:if test="${jcrs == null}"><td></td></c:if>
										<c:if test="${jcrs != null}">
											<td>
											<c:forEach var="item" items="${jcrs }" varStatus="sta">
												${item}&nbsp;
											</c:forEach>
											</td>
										</c:if>

										<c:if test="${glrs == null}"><td></td></c:if>
										<c:if test="${glrs != null}">
											<td>
											<c:forEach var="item" items="${glrs }" varStatus="sta">
												${item}&nbsp;
											</c:forEach>
											</td>
										</c:if>

										<c:if test="${bsrs == null}"><td></td></c:if>
										<c:if test="${bsrs != null}">
											<td>
											<c:forEach var="item" items="${bsrs }" varStatus="sta">
												${item}&nbsp;
											</c:forEach>
											</td>
										</c:if>

										<c:if test="${ywrs == null}"><td></td></c:if>
										<c:if test="${ywrs != null}">
											<c:forEach var="item" items="${ywrs }" varStatus="sta">
												${item}&nbsp;
											</c:forEach>
											</td>
										</c:if>
									<td></td>
									</tr>
									</tbody>
								</table>
						</ul>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户信任人：</label>
						<ul class="wh_ul">
							<c:if test="${contacts != null }">
								<c:forEach var="item" items="${contacts }" varStatus="sta">
									<c:if test="${item.role == 113}">
										<li>${item.name }</li>
									</c:if>
								</c:forEach>
							</c:if>
						</ul>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">渠道伙伴：</label>
						<ul class="wh_ul">
							<c:if test="${contacts != null }">
								<c:forEach var="item" items="${contacts }" varStatus="sta">
									<c:if test="${item.role == 112}">
										<li>${item.name }</li>
									</c:if>
								</c:forEach>
							</c:if>
						</ul>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">供应商：</label>
						<ul class="wh_ul">
							<c:if test="${contacts != null }">
								<c:forEach var="item" items="${contacts }" varStatus="sta">
									<c:if test="${item.role == 118}">
										<li>${item.name }</li>
									</c:if>
								</c:forEach>
							</c:if>
						</ul>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户来源：</label>
						<div class="wh_ul">
							<label class="col-sm-2 control-label wh_lab" style="text-align: left; padding-left:0px; margin-right: -20px;">渠道-</label>
							<ul class="wh_ul">
								<c:if test="${lxr != null }">
									<c:forEach var="item" items="${lxr }">
										<li><label><input  name="cusSource" class="dev-khly" type="radio" ${item.id == model.cusSource ? 'checked':'' } value="${item.id }" />${item.name }</label></li>
									</c:forEach>
								</c:if>
							</ul>
							<label class="col-sm-2 control-label wh_lab" style="text-align: left; padding-left:0px; margin-right: -20px;">直销-</label>
							<ul class="wh_ul">
								<c:if test="${khly != null }">
									<c:forEach var="item" items="${khly }">
										<li><label><input  name="cusSource" class="dev-khly" type="radio" ${item.id == model.cusSource ? 'checked':'' } value="${item.id }" />${item.name }</label></li>
									</c:forEach>
								</c:if>

							</ul>
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">推进记录：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[maxSize[500]]" id="reportRemark" placeholder="请输入推进记录" name="reportRemark" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">客户出钱性质：</label>
						<ul class="wh_ul">
							<c:if test="${cqxz != null }">
								<c:forEach var="item" items="${cqxz }">
									<li><label><input  name="payNature" class="dev-khcqxz" ${item.id==model.payNature?"checked":""} type="radio" value="${item.id }" />${item.name }</label></li>
								</c:forEach>
							</c:if>
						</ul>
					</div>

					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">销售推进状态：</label>
						<ul class="wh_ul">
							<c:if test="${xstjzt != null }">
								<c:forEach var="item" items="${xstjzt }">
									<li><label><input  name="followState" class="dev-cjzt" ${item.id==model.followState?"checked":""} type="radio" value="${item.id }" />${item.name }</label></li>
									<c:if test="${item.childList != null }">
										<c:forEach var="child" items="${item.childList }">
											<li><label><input  name="followStateDetails" ${(model.followStateDetails!=null && model.followStateDetails.concat(",").contains(String.valueOf(child.id).concat(",")))?"checked":""} class="dev-cjztmx" type="checkbox" value="${child.id }" />${child.name }</label></li>
										</c:forEach>
									</c:if>
								</c:forEach>
							</c:if>
						</ul>
					</div>

					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label wh_lab">人际关系图：</label>
						<input type="hidden" name="relationsId" id="hid_relationsId" value="${customerRelations.id}">
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
									<input type="hidden" name="channelPartner" id="hid_channelPartner" value="${customerRelations.channelPartner}">
									${customerRelations.channelPartnerName}
									<a href="javascript:void(0)" data-id="contact_list" data-bz="channelPartner">+</a>
								</td>
								<td>
                                    <span id="sp_trustPerson"></span>
                                    <input type="hidden" name="trustPerson" id="hid_trustPerson" value="${customerRelations.trustPerson}">
									${customerRelations.trustPersonName}
                                    <a href="javascript:void(0)" data-id="contact_list" data-bz="trustPerson">+</a>
                                </td>
								<td>
                                    <span id="sp_decisionPerson"></span>
                                    <input type="hidden" name="decisionPerson" id="hid_decisionPerson" value="${customerRelations.decisionPerson}">
									${customerRelations.decisionPersonName}
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
                                    <input type="hidden" name="managePerson" id="hid_managePerson" value="${customerRelations.managePerson}">
									${customerRelations.managePersonName}
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
                                    <input type="hidden" name="handlePerson" id="hid_handlePerson" value="${customerRelations.handlePerson}">
									${customerRelations.handlePersonName}
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
                                    <input type="hidden" name="professionalPerson" id="hid_professionalPerson" value="${customerRelations.professionalPerson}">
									${customerRelations.professionalPersonName}
                                    <a href="javascript:void(0)" data-id="contact_list" data-bz="professionalPerson">+</a>
                                </td>
							</tr>
							</tbody>
						</table>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">下一步工作计划：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[maxSize[500]]" id="nextPlan" placeholder="请输入下一步工作计划" name="nextPlan" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">计划标准：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[maxSize[500]]" id="planStandard" placeholder="请输入计划标准" name="planStandard" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label">计划执行人：</label>
						<div class="wh_ul">
							<ul class="wh_ul">
								<c:if test="${accList != null }">
									<c:forEach var="item" items="${accList }">
										<li class="dev-acclist ${search.accountId == item.id ? "on":"" }" data-value="${item.id }">
											<input  name="planExecutorUser" class="dev-cjztmx" type="checkbox" value="${item.id }" />${item.isMySelf()?"自己":item.name }
										</li>
									</c:forEach>
								</c:if>
							</ul>
							<ul class="wh_ul">
								<c:if test="${xxrList != null }">
									<c:forEach var="item" items="${xxrList }">
										<li class="dev-acclist " data-value="${item.id }">
											<input  name="planExecutorContact" class="dev-cjztmx" type="checkbox" value="${item.id }" />${item.name }
										</li>
									</c:forEach>
								</c:if>
							</ul>
							<ul class="wh_ul">
								<c:if test="${qdhbList != null }">
									<c:forEach var="item" items="${qdhbList }">
										<li class="dev-acclist " data-value="${item.id }">
											<input  name="planExecutor" class="dev-cjztmx" type="checkbox" value="${item.id }" />${item.name }
										</li>
									</c:forEach>
								</c:if>
							</ul>

						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label">告知执行人方式：</label>
						<div class="wh_ul">
							<ul class="wh_ul">
								<li class="dev-acclist " >
									<input  name="executeWay" class="dev-cjztmx" type="checkbox" value="1" />电话
								</li>
								<li class="dev-acclist " >
									<input  name="executeWay" class="dev-cjztmx" type="checkbox" value="2" />微信
								</li>
								<li class="dev-acclist " >
									<input  name="executeWay" class="dev-cjztmx" type="checkbox" value="3" />邮件
								</li>
								<li class="dev-acclist " >
									<input  name="executeWay" class="dev-cjztmx" type="checkbox" value="4" />当面开会
								</li>
								<li class="dev-acclist " >
									<input  name="executeWay" class="dev-cjztmx" type="checkbox" value="5" />视频会议
								</li>
							</ul>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label">任务象限：</label>
						<div class="wh_ul">
							<c:if test="${taskxx != null }">
								<c:forEach var="item" items="${taskxx }" varStatus="sta" >
									<label class="col-sm-2 control-label wh_lab" style="text-align: left; padding-left:0px; margin-right: -20px;">
										<input  name="quadrant" class="dev-cjztmx" type="radio" value="${item.id}" />${item.name }
									</label>
								</c:forEach>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">计划反馈时间：</label>
						<div class="col-sm-4" style="margin-left: -15px; padding-left: 0px;">
							<div class="inline relative mr0">
								<input type="text" class="form-control input150" placeholder="请输入计划反馈时间" name="backTime" id="backTime" input-type="date" >
								<span class="date_icon" style="cursor: pointer;"><i></i></span>
							</div>
						</div>
						<label for="" class="col-sm-2 control-label">提醒方式：</label>
						<div class="col-sm-6 favorite">
							<div class="wh_ul">
								<ul class="wh_ul">
									<li class="dev-acclist " >
										<input  name="backWay" class="dev-cjztmx" type="radio" value="1" />提前一天
									</li>
									<li class="dev-acclist " >
										<input  name="backWay" class="dev-cjztmx" type="radio" value="2" />具体时间
									</li>
									<li class="dev-acclist " >
										<input  name="backWay" class="dev-cjztmx" type="radio" value="3" />具体节点
									</li>
									<li class="dev-acclist " >
										<input  name="backWay" class="dev-cjztmx" type="radio" value="4" />不提醒
									</li>
									<li class="dev-acclist " >
										<input  name="backWay" class="dev-cjztmx" type="radio" value="5" />搁置
									</li>
								</ul>
							</div>
						</div>
					</div>
				</form>

		</div>
	</div>
<!--日历插件-->
<script type="text/javascript" src="../content/js/pickday.js"></script>
<script type="text/javascript">


	$("a[data-id=contact_list]").click(function() {
		var $this = $(this);
        var bz = $this.data("bz");
		dialogForRecord = $.sk.open({
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
					$.sk.close(dialogForRecord);
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