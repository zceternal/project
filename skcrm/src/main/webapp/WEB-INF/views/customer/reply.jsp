<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
<!--
.addressformError{}
-->
</style>
	<div class="main_content dialog">
			<div class="panel panel-default">
				<form class="form-horizontal old_block" role="form" id="recordRevertformAdd"
					action="../CustomerRecordRevert/reply" method="post" data-ajax="true" data-ajax-success="onCustomerRecordRevertSuccessAdd">
					<div class="form-group">
					<input type="hidden" id="recordId" name="recordId" value="">
						<label for="" class="col-sm-2 control-label">回复内容：</label>
						<div class="col-sm-20">
							<textarea type="text" data-prompt-position="inline"
								class="form-control input300 validate[required,maxSize[50],ajax[ajaxNameCall]]"
								id="remark" name="remark" placeholder="请输入回复内容"></textarea>
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
</script>