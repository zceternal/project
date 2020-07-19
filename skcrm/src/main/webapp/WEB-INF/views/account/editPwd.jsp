<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<div class="main_content">
			<div class="panel panel-default choose_block">
				<form class="form-horizontal old_block" role="form" id="myformxeditPwd"
					action="account/editPwd" method="post" data-ajax="true" data-ajax-success="onSavePwd">
					
					<input id="key" value="${model.id }" name="id" type="hidden">

					<div class="form-group">
						<label for="" class="col-sm-2 control-label">原密码：</label>
						<div>
							<input type="password"
								class="form-control input300 validate[minSize[6],maxSize[50],required]" id="oldPwd"
				
								name="oldPwd" placeholder="">
						</div>
					</div>
					
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">新密码：</label>
						<div>
							<input type="password"
								class="form-control input300 validate[minSize[6],maxSize[50],required]" id="newPwd"
				
								name="newPwd" placeholder="">
						</div>
					</div>
					
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">确认密码：</label>
						<div>
							<input type="password"
								class="form-control input300 validate[required,maxSize[50],equals[newPwd]]" id="qrnewPwd"
				
								name="qrnewPwd" placeholder="">
						</div>
					</div>



				</form>

			</div>
	</div>
<script type="text/javascript">
$("#myformxeditPwd").validationEngine({ajaxFormValidationMethod: 'post',promptPosition:"centerRight"});</script>