<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.dev-col-sm-120 {width:120px !important;}
</style>
	<div class="main_content dialog">
			<div class="panel panel-default">
				<form class="form-horizontal old_block" role="form" id="myformAddRemind"
					action="addRemind" method="post" data-ajax="true" data-ajax-success="onCustomerSuccessAddRemind">
					
					
					<input type="hidden" id="customerId" name="customerId" value="${customerId }"> 
					
					<div class="form-group">
						<label for="" class="dev-col-sm-120 control-label text-right" >提醒时间<em
						class="colorred">*</em>：</label>
						<div class="inline relative">
								<input type="text" class="form-control input150 validate[required]"
								placeholder="请输入提醒时间" name="remindTime" id="remindTime"
								input-type="date" > <span class="date_icon" style="cursor: pointer;"><i></i></span>
						</div>
					</div>

					<div class="form-group">
						<label for="" class=" dev-col-sm-120 control-label text-right" style="float:left;">提醒内容：</label>
						<div class="inline">
							<textarea class="form-control validate[maxSize[5000]]" id="remark" placeholder="请输入提醒内容" name="remark"  style="width:300px" data-prompt-position="inline"
								rows=4></textarea>
						</div>
					</div>

				</form>
		</div>
	</div>
	

	
<script type="text/javascript">
	$("#myformAddRemind").validationEngine({
		ajaxFormValidationMethod : 'post',
		promptPosition : "centerRight"
	});
	$(function(){

	       $("[input-type='date']").each(function(i,El){
	         var picker = new Pikaday(
	          {
	                field: El,
	                firstDay: 1,
	                //minDate: new Date(),
	                /* minDate: new Date('1900-01-01'), */
	                /* maxDate: new Date(), */
	                format: 'YYYY-MM-DD',
	                yearRange: [1900,2330]
	          });
	       });
	     
	       $(".date_icon").css("cursor","pointer").click(function(){
	         $(this).siblings(":text").click();
	      })
	})




</script>