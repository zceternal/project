<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
.deptSelTextformError {
	margin-left: 15px;
}

.fam_block .col-sm-2, .old_block .col-sm-2 {
	width: 120px !important;
	text-align: right;
}

.form-group {
	margin-left: 0px !important;
}

.form-group input[type="text"], .form-group input[type="password"] {
	display: inline-block;
	width: 190px
}

table tr td {
	text-align: left;
}
</style>
<!--多选下拉框 -->
	<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
	<script type="text/javascript" src="../content/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript" src="../content/js/jquery.validationEngine.js"></script>


<div class="main_content">
	<div class="panel panel-default">
		<form class="form-horizontal old_block" role="form" id="myformEdit"
			action="edit" method="post" data-ajax="true"
			data-ajax-success="submit">
			<input type="hidden" id="id" name="id" value="${model.id }">
			<table width="100%">
				<tr>
					<td width="100%"><input type="hidden" name="type" id="subtype">
						<div class="form-group ">
							<label for="" class="col-sm-2 control-label">群组名称<em class="colorred">*</em>：
							</label>
							<div class="col-sm-8">
								<input type="text"
									class="form-control validate[required,maxSize[50],ajax[ajaxGroupNameCall]]"
									id="name" name="name" value="${model.name }" placeholder="请输入群组名称">
							</div>
						</div></td>
				</tr>
				
				<tr>
					<td width="100%"><input type="hidden" name="type" id="subtype">
							<div class="form-group ">
								<label for="" class="col-sm-2 control-label">群组成员<!-- <em class="colorred">*</em> -->：</label>
								<div class="col-sm-8">
								<select data-placeholder=" 请选择群组成员"
									style="width: 625px;" class="chzn-select" data-no_results_text="没有匹配结果" multiple tabindex="6">
										<c:forEach items="${users }" var="item">
										<%-- <c:if test="${!fn:contains(model.accountList,item.name) }"> --%>
											<option value="${item.id }_${item.name }">${item.name }</option>
										<%-- </c:if> --%>
										</c:forEach>
								</select>
								<div id="selYXB_chzn" class="chzn-container chzn-container-multi" style="width: 625px;" title="">
								<input type="hidden" name="accountList" value="${model.accountList }" id="accountList" class="form-control validate[required]" />
								<input type="hidden" name="accountIdList" id="accountIdList" value="${model.accountIdList }" class="form-control validate[required]" />
								
								</div>
								</div>
							</div>
						</td>
				</tr>
				
				<tr>
					<td width="100%"><input type="hidden" name="type" id="subtype">
						<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">群组说明：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[maxSize[500]]" id="remark" placeholder="请输入群组说明" name="remark" style="width: 625px" data-prompt-position="inline"
								rows=4>${model.remark }</textarea>
						</div>
					</div>
						</div></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>

	
<script type="text/javascript">
	$("#myformAdd").validationEngine({
		ajaxFormValidationMethod : 'post',
		promptPosition : "centerRight"
	});
	
	var selectSubId = [];
	var selectSub = [];
	$(".chzn-select").chosen().change(function(select, value){

		if(value.selected){
			var list = value.selected.split('_');
			selectSubId.push(list[0]);
			selectSub.push(list[1]);
			$(".receivesformError").hide();
		}
		if(value.deselected){
			var list = value.deselected.split('_');
			selectSubId.remove(list[0]);
			selectSub.remove(list[1]);
		}
		if(selectSub.length == 0){
			$(".receivesformError").show();
		}
	    $("#accountList").val(selectSub.join(","));
	    $("#accountIdList").val(selectSubId.join(","));
	});

</script>
