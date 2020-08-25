<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.dev-col-sm-120 {width:134px !important;}
.col-sm-4,.col-sm-10,.wh_ul{padding-left:0px}
</style>
	<div class="main_content dialog">
			<div class="panel panel-default">
				<form class="form-horizontal old_block" id="myformAdd"
					action="add" method="post" role="form" data-ajax="true" data-ajax-success="onTaskSuccessAdd">
					<input id="customerId" name="customerId" type="hidden" title="现有客户id" value="${search.page }" />
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">任务名称<em
								class="colorred">*</em>：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control validate[required,maxSize[50]]" id="name" placeholder="请输入任务名称" name="name" style="width: 625px" data-prompt-position="inline" >
						</div>
					</div>

                    <div class="form-group">
                        <label class="col-sm-2 dev-col-sm-120 control-label">任务性质：</label>
                        <div class="wh_ul">
                          <c:if test="${taskxz != null }">
                              <c:forEach var="item" items="${taskxz }" varStatus="sta" >
                                    <label class="col-sm-2 control-label wh_lab" style="text-align: left; padding-left:0px; margin-right: -20px;">${item.name }</label>
                                    <ul class="wh_ul">
                                        <c:if test="${item.childList != null }">
                                            <c:forEach var="child" items="${item.childList }">
                                                <li><label><input  name="taskNature" class="dev-khly" type="radio" value="${child.id }" />${child.name }</label>
													<c:if test="${child.id == 167}">
														<li><label><a href="javascript:void(0)" data-id="customer_list" id="a_selName">选择客户+</a></label></li>
													</c:if>
												</li>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                              </c:forEach>
                           </c:if>
                        </div>
                    </div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">下一步工作计划<em
								class="colorred">*</em>：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[required,maxSize[500]]" id="nextPlan" placeholder="请输入下一步工作计划" name="nextPlan" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">计划标准<em
								class="colorred">*</em>：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[required,maxSize[200]]" id="planStandard" placeholder="请输入计划标准" name="planStandard" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label">计划执行人<em
								class="colorred">*</em>：</label>
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
						<label class="col-sm-2 dev-col-sm-120 control-label">任务象限<em
								class="colorred">*</em>：</label>
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
						<label class="col-sm-2 dev-col-sm-120 control-label">计划反馈时间：</label>
						<div class="col-sm-10">
							<div class="inline relative mr0">
								<input type="text" class="form-control input150" placeholder="请输入计划反馈时间" name="backTime" id="backTime" input-type="date" >
								<span class="date_icon" style="cursor: pointer;"><i></i></span>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 dev-col-sm-120 control-label">提醒方式：</label>
						<div class="col-sm-10">
							<div class="">
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

	$("a[data-id=customer_list]").click(function() {
		var $this = $(this);
		$selectCustomerForTask = $.sk.open({
			url : "select_customer_list",
			width : 1000,
			height : 600,
			overflow:scroll,
			title : "选择客户",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					var ids = "";
					var names = "";
					var selectCustomers = $(":checkbox[class=sk_checkbox]:checked");
					if(selectCustomers.length>1){
						$.sk.error("只能选择一个客户！");
						return;
					}
					$(":checkbox[class=sk_checkbox]:checked").each(function() {
						var ck = $(this);
						ids += ck.val() + ",";
						names += ck.data("cusname") + ",";
					});
					if (names) {
						names = names.substr(0, names.length - 1);
						ids = ids.substr(0, ids.length - 1);
					}
					//$("#a_selName").text(names);
					$("#customerId").val(ids);
					$.sk.close($selectCustomerForTask);
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