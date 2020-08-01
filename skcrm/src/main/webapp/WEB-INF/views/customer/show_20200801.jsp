<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${customerInfo.shortName}-客户详情</title>
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<script type="text/javascript">
	var rootPath = "../";
</script>


<link rel="stylesheet" href="../content/css/jquery.ptTimeSelect.css" />
<!-- 日历样式 -->
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
<!--表单验证css-->
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />



<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=2i6NaIyF6fP4NOFdLs4I9Fk9"></script>
<style type="text/css">
.echart_block {
	padding: 0px 0px 15px 0px;
	border-radius: 5px;
	border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px;
}

.echart_block h4 {
	padding: 14px 13px;
	text-align: left;
	margin-bottom: 0px;
	color: #fff;
	background: #959da8;
	border-top-right-radius: 5px;
	border-top-left-radius: 5px;
}

.echart_block>div {
	padding: 0px 10px;
}

.tab_button li {
	width: 77px;
	height: 26px;
	border: 0px;
	font-size: 14px;
	font-weight: 400;
	border-radius: 15px;
}

.tab_button li.on {
	background: #f19b2c;
	font-weight: 400;
	border: 0px
}
.flck{overflow: hidden;font-family: "微软雅黑";font-size: 14px;vertical-align: middle; overflow: hidden;margin: 10px 0px; padding-left: 24px;}
        .flck a{color: #333;text-decoration: none;padding:6px 10px;display: inline-block;line-height: 19px;float: left;}
        .flck a{border: 1px solid #ced6d8;border-left: 0px;}
        .flck a:first-child{border-left: solid 1px #ced6d8;-webkit-border-top-left-radius: 3px;-webkit-border-bottom-left-radius: 3px;border-top-left-radius: 3px;border-bottom-left-radius: 3px;}
        .flck a+a{border-right:solid 1px #ced6d8;}
		.flck a.last{border-top-right-radius: 3px;border-bottom-right-radius: 3px;-webkit-border-top-right-radius: 3px;-webkit-border-bottom-right-radius: 3px;}
		.flck a.on{ background-color:#26a2f1; color:#fff;border-color: #26a2f1;}
		.flck a.disabled{background-color:#e3e7e8;color: #CCCCCC;}
		.flck p{margin:0 0 0px !important;}
</style>
</head>
<body>
	<div class="main_content create_site">
		<div class="col-xs-12 col-sm-12">
			<div class="panel panel-default choose_block"
				style="padding-top: 13px;">
				<div class="panel-heading color333 mb0">
					<em style="font-weight: bold; font-size: 22px;">${customerInfo.shortName}<span class="color999 font14 "
					style="padding-bottom: 10px; display: inline-block;">(${customerInfo.name})</span></em>
					<a class="btn btn_blueg2 font14 pull-right"  id="backIndex" 
						style="margin-top: 10px" href="javascript:void(0);" onclick="goIndex();return false;">&lt;&lt;返回</a>
				</div>
				<form id="forIndexFrom" >
				<input id="indexPage" name="indexPage" type="hidden" value="${indexPage }" />
				<input id="indexPageSize" name="indexPageSize" type="hidden" value="${indexPageSize }" />
				<%-- <input id="indexTraceType" name="indexTraceType" type="hidden" value="${indexTraceType }" /> --%>
				<input id="indexStatus" name="indexStatus" type="hidden" value="${indexStatus }" />
				<input id="indexCustomerType" name="indexCustomerType" type="hidden" value="${indexCustomerType }" />
				<input id="indexSalesSuccessRate" name="indexSalesSuccessRate" type="hidden" value="${indexSalesSuccessRate }" />
				<input id="indexAccountId" name="indexAccountId" type="hidden" value="${indexAccountId }" />
				<input id="indexOrderType" name="indexOrderType" type="hidden" value="${indexOrderType }" />
				<input id="indexOrderField" name="indexOrderField" type="hidden" value="${indexOrderField }" />
				<input id="indexContent" name="indexContent" type="hidden" value="${indexContent }" />
				<input id="indexIsFrom" name="indexIsFrom" type="hidden" value="${indexIsFrom }" />  
				</form>
				<input type="hidden" id="statusVal" value="${customerInfo.status}"> 
				<div class="flck">
					
					<c:forEach items="${allStatus }" var="st"  varStatus="status">
					<c:if test="${customerInfo.status==st.id}">
						<c:set var="no" value="1"></c:set>
						
						<c:if test="${st.id > 40}">
							<a href="javascript:updateStatus('${customerId }','${st.id }','${status.index }');" data-index='${status.index }' class="on last" value="${st.id }"><p>${st.name }<p style="height:19px;"></p></a>
							</c:if>
							<c:if test="${st.id <= 40}">
							<a href="javascript:updateStatus('${customerId }','${st.id }','${status.index }');" data-index='${status.index }' class="on last" value="${st.id }"><p>${st.name }<p>(剩余${st.spendDay }天)</p></a>	
							</c:if>	
					</c:if>
					<c:if test="${customerInfo.status!=st.id}">
						<c:if test="${no==1}">
						<a href="javascript:updateStatus('${customerId }','${st.id }','${status.index }');" data-index='${status.index }' class="last" value="${st.id }"><p>${st.name }</p><p style="height:19px;"></p></a>	
						</c:if>
						<c:if test="${no!=1}">
							<c:if test="${st.id > 40}">
							<a href="javascript:;" class="disabled last" value="${st.id }"><p>${st.name }<p style="height:19px;"></p></a>
							</c:if>
							<c:if test="${st.id <= 40}">
							<a href="javascript:;" class="disabled last" value="${st.id }"><p>${st.name }<p>(花费${st.spendDay }天)</p></a>	
							</c:if>
						</c:if>							
					</c:if>
					</c:forEach>
		   		</div>
				
				<div class="divider"></div>
				<div class="site_block member_block">
					<div class="panel-body">

						<div class="row">
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"
								style="width: 30% !important; color: #333">
							 <%-- 	<div class="text-right mr15 font14 inline"
									style="padding-left: 0px">
									<input type="hidden" id="statusVal"value="${customerInfo.status}"> 
									客户状态： <select id="status" name="status" onchange="updateStatus('${customerId }',this.value)">
										<c:forEach items="${allStatus }" var="st">
											<option value="${st.id }"
												${customerInfo.status==st.id?"selected":"" }>${st.name }</option>
										</c:forEach>
									</select>
								</div>  --%>
								<div class="choose_block echart_block">

									<h4 align="center" style="margin-top: 0px;">
										项目负责人
										<div class="pull-right mr15">
											<c:if
												test="${firstCustomerShare.allowAccountId==selfAccountId }">
												<a href="javascript:void(0)"
													onclick="goShare('${customerId }')"> <span
													class="glyphicon glyphicon-edit" aria-hidden="true"></span>
												</a>
											</c:if>
										</div>
									</h4>
									<div style="padding-top: 10px; text-align: left;">
										<label class="color999 font12">销售第一负责人：</label>
										<div style="overflow: hidden">
											<div class="pull-left color333" style="">${firstCustomerShare.allowName}&nbsp;&nbsp;${firstCustomerShare.phone==null?"":firstCustomerShare.phone }</div>
											<%-- <div class="pull-left">
										   <label class="color999">电话：</label> ${firstCustomerShare.phone==null?"":firstCustomerShare.phone }
										    </div> --%>
										</div>

									</div>
									<form method="post" action="../cusRecord/refreshAllow"
										data-ajax="true" data-ajax-mode="replace"
										class="form-inline pl20" data-ajax-update="#allowList"
										role="form" id="form_allow_search" method="post">
										<input name="customerId" type="hidden" value="${customerId }" />
									</form>
									<div id="allowList" style="padding-top: 0px; text-align: left;">
										<jsp:include page="allow_list.jsp"></jsp:include>
									</div>
									<div style="padding-top: 0px; text-align: left;">
										<label class="color999 font12">推荐人：</label>
<%--										<div style="overflow: hidden">--%>
<%--											<div class="pull-left color333" style="">${customerInfo.recommender}</div>--%>
<%--										</div>--%>
									</div>
								</div>
								<form method="post" action="../cusRecord/refreshContact"
									data-ajax="true" data-ajax-mode="replace"
									class="form-inline pl20" data-ajax-update="#contactList"
									role="form" id="form_contact_search" method="post">
									<input name="customerId" type="hidden" value="${customerId }" />
								</form>
								<div class="choose_block echart_block" style="margin-top: 10px">
									<h4 align="center" style="margin-top: 0px;">
										客户联系人
										<div class="pull-right mr15">
											<a href="javascript:void(0)" id="addContactX"
												data-cusname="${customerInfo.name}"
												data-cusid="${customerId }"> <span
												class="glyphicon glyphicon-plus" aria-hidden="true"></span>
											</a>
										</div>
									</h4>

									<div id="contactList">
										<jsp:include page="contact_list.jsp"></jsp:include>
									</div>

								</div>

								<div class="choose_block echart_block" style="margin-top: 10px">

									<h4 align="center" style="margin-top: 0px;">
										客户基本信息
										<div class="pull-right mr15">
											<a href="javascript:void(0)" id="updateCusInfo"
												data-cusid="${customerId }"> <c:if
													test="${firstCustomerShare.allowAccountId==selfAccountId }">
													<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
												</c:if>
											</a>
										</div>
									</h4>
									<form method="post" action="../cusRecord/refreshCusInfo"
										data-ajax="true" data-ajax-mode="replace"
										class="form-inline pl20" data-ajax-update="#cusInfoList"
										role="form" id="form_cusInfo_search" method="post">
										<input name="customerId" type="hidden" value="${customerId }" />
									</form>
									<div id="cusInfoList">
										<jsp:include page="cusinfo_list.jsp"></jsp:include>
									</div>
								</div>

								<div class="choose_block echart_block" style="margin-top: 10px">
									<h4 align="center" style="margin-top: 0px;">客户日志</h4>

									<div id="contactList">
										<jsp:include page="customerlog_list.jsp"></jsp:include>
									</div>

								</div>

							</div>

							<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12"
								style="width: 70% !important;">
								<div class="panel panel-default dev-record">
									<!-- Default panel contents -->
									<div class="">
										<div class="site_change "
											style="height: 33px; margin-bottom: 20px; margin-top: 10px; border-bottom: solid 1px #ccc; margin-left: 20px;">
											<span class="colorshenblue font16"
												style="border-bottom: 2px #49b79d solid; padding: 0px 5px 10px 5px;">添加处理记录</span>
											<a class="btn btn_blueg2 font14 pull-right"  href="javascript:void(0)" onclick="closeRecordShow()" id="closerecord"
												 style="margin-right:18px;">关闭处理记录</a>	
										</div>
									</div>

									<form class="form-inline pl20" role="form" id="myformShow"
										method="post" action="../cusRecord/addRecord" data-ajax="true"
										data-ajax-mode="replace"
										data-ajax-success="recordAddOnSuccess"
										data-ajax-update="#customerRecordList">
										<input name="customerId" type="hidden" value="${customerId }" />
											<textarea id="container"  style="height:200px;width: 100%;"></textarea>
    								  		<input type="text"  id="remarkRecord" name="remark"
											class="form-control input900 validate[required]"
											style="width:0px;border: 0px;height: 32px;padding: 4px 0px;"
											placeholder="输入内容..."></input>

										<input type="hidden" id="logsTypeHid" name="type" value="${(getRecordType!=null && getRecordType.size() > 0) ? getRecordType[0].id : -1}" />

										<div class="form-group"
											style="padding: 15px 0px 5px; width: 100%">
											<lable
												style="float: left;line-height: 29px;font-size: 14px;
											">记录类别：</lable>
											<ul id="logsType" class="tab_button"
												style="margin-bottom: 0px;">
												<c:choose>
													<c:when test="${getRecordType!=null}">
														<c:forEach items="${getRecordType }" var="item"
															varStatus="status">															
															<c:choose>
																<c:when test="${status.index==0}">
																	<li class='on' data-id="${item.id }">${item.name }</li>
																</c:when>
																<c:otherwise>
																	<li data-id="${item.id }">${item.name }</li>
																</c:otherwise>
															</c:choose>

														</c:forEach>
													</c:when>
													<c:otherwise>
														<li class='on' data-id="111">公海</li>
													</c:otherwise>
												</c:choose>
											</ul>
											<div class="pull-right mr15">
												<button type="submit" class="btn btn_white20" onclick="return submitRecord();"
													style="width: 126px; height: 41px; font-size: 18px">发&nbsp;&nbsp;&nbsp;布</button>
											</div>
										</div>

									</form>
								</div>

								<div class="panel panel-default" style="margin-top: 20px;">
									<!-- Default panel contents -->
									<div class="">
										<div class="site_change "
											style="height: 33px; margin-bottom: 20px; margin-top: 10px; border-bottom: solid 1px #ccc; margin-left: 20px;">
											<span class="colorshenblue font16"
												style="border-bottom: 2px #49b79d solid; padding: 0px 5px 10px 5px;">处理记录列表</span>
												<a class="btn btn_blueg2 font14 pull-right"  href="getCustomer.html?customerId=${customerId }&isFirst=${isFirst }"
												 style="margin-right:18px;">记录历史</a>	
												 	
												<a class="btn btn_blueg2 font14 pull-right"  href="javascript:void(0)" onclick="addRecordShow()" id="addrecord"
												 style="margin-right:18px;">添加处理记录</a>	
													
												<a class="btn btn_blueg2 font14 pull-right"  href="javascript:void(0)" id="addremind"
												data-cusid="${customerId }" style="margin-right:18px;">添加提醒</a>
												
												<shiro:hasPermission name="highseas_transfer">
												<c:if test="${isFirst }">
												<a class="btn btn_blueg2 font14 pull-right"  href="javascript:void(0)" id="injecthighseas"
												data-cusid="${customerId }" style="margin-right:18px;">投入公海</a>
												</c:if>
												</shiro:hasPermission>
										</div>
									</div>
									<form method="post" action="../customer/show" data-ajax="true"
										data-ajax-mode="replace" class="form-inline"
										data-ajax-update="#customerRecordList" role="form"
										id="form_customer_record_search" method="post">
										<input id="page" name="page" type="hidden"
											value="${pager.pageNum }" /> <input name="customerId"
											id="customerId" type="hidden" value="${customerId }" /> <input
											name="accountId" id="accountId" value="" type="hidden" /> <input
											type="hidden" id="typeHid" name="type" value="" />

										<div class="form-group pl20" style="width: 100%">
											<lable
												style="float: left;line-height: 29px;font-size: 14px;
											">跟踪人员：</lable>
											<ul id="filterType" class="tab_button"
												style="margin-bottom: 0px;">
												<li class='on dev-record-accid' data-id="">全部</li>
												<li class="dev-record-accid" data-id="${selfAccountId }">自己</li>
												<c:if test="${isRecordShare }">
													<c:forEach items="${allPublishers }" var="record">
														<c:if test="${record.accountId != selfAccountId&&record.accountName!=null}">
															<li class="dev-record-accid"
																data-id="${record.accountId }">${record.accountName }</li>
														</c:if>
													</c:forEach>
												</c:if>
											</ul>
											<div class="form-group"
												style="padding: 15px 0px 5px 0px; width: 100%">
												<lable style="float: left;line-height: 29px;font-size: 14px; ">记录类别：</lable>
												<ul id="type" class="tab_button" style="margin-bottom: 0px;">
													<li class='on' data-id="">全部</li>
													<c:forEach items="${allRecordTypes }" varStatus="sta"
														var="recordType">
														<li data-id="${recordType.id }">${recordType.name }</li>
													</c:forEach>
												</ul>
											</div>
										</div>

									</form>

									<div id="customerRecordList" class="pl20">
										<jsp:include page="show_list.jsp"></jsp:include>
									</div>

								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <div class="main_content add_block">
		
	</div> -->
	<!-- 引用js -->
	<script type="text/javascript"
		src="../content/js/jquery.ptTimeSelect.js"></script>
	<script type="text/javascript" src="../content/js/common.js"></script>
	<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
	<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>

	<script type="text/javascript"
		src="../content/module/customer/customer_detail.js"></script>

	<!--表单验证 s-->
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine.js"></script>
	<!--表单验证 e-->
	<script type="text/javascript"
		src="../content/module/contact/contact.index.js"></script>
	<script type="text/javascript"
		src="../content/module/customer/customer_record_reply.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
	<script type="text/javascript">
	
	$(".flck a").not(".disabled").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
	})
	
		$(function() {
			$("#form_customer_record_search").submit();
			
			$("#logsType>li").click(function() {
				var $this = $(this);
				$("#logsTypeHid").val($this.data("id"));
				$this.addClass("on").siblings().removeClass("on");
			});

			$("#type>li").click(function() {
				var $this = $(this);

				$("#typeHid").val($this.data("id"));
				$("#accountId").val();

				$this.addClass("on").siblings().removeClass("on");
				$("#form_customer_record_search").submit();
			});

			$("#filterType>li").click(function() {
				var $this = $(this);
				$("#accountId").val($this.data("id"));
				$this.addClass("on").siblings().removeClass("on");
				$("#form_customer_record_search").submit();
			});

		});
		

	</script>
	
	<!-- 配置文件 -->
    <script type="text/javascript" src="../content/ueditor.1.4.3/ueditor.config.easy.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="../content/ueditor.1.4.3/ueditor.all.min.js"></script>
	 <!-- 实例化编辑器 -->
    <script type="text/javascript">
    

  //返回按钮
  function goIndex(){
  	var data = $("#forIndexFrom").serialize();
  	var url = "../customer/index?"+data;
  	window.location = url;
  }

    
        var ue = UE.getEditor('container');
        $(".dev-record").hide();
        $("#myformShow").validationEngine({
    		ajaxFormValidationMethod : 'post',
    		promptPosition : "centerRight"
    	});
        function submitRecord(){
        	var content = ue.getContent();
     		$("#remarkRecord").val(content);
     		return true;
        }
        //是否添加-关闭=“添加处理记录”
		function addRecordShow(){
			$("#addrecord").hide();
	        $(".dev-record").show("slow"); 
		}
		function closeRecordShow(){
			 $("#addrecord").text("添加处理记录");
			 $("#addrecord").show();
		     $(".dev-record").hide("slow");
		}
    </script>
	
	
</body>
</html>
