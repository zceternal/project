<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="main_content">
	<div class="panel panel-default choose_block">
		<form class="form-horizontal old_block" role="form"
			id="myformxAccountInfo" action="account/accountInfo" method="post"
			data-ajax="true" data-ajax-success="onSaveAccountInfo">


			<div class="form-group">
				<label for="" class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-20">
					<input type="text"
						class="form-control input300 validate[required,maxSize[20]]"
						id="name" name="name" placeholder="请输入姓名" value="${model.name }">
				</div>
			</div>

			<div class="form-group">
				<label for="" class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-20 ">
					<div class="checkbox-inline">
						<label> <input type="radio"
							${model.sex == 1 ? 'checked':'' } name="sex" value="1"> 男
						</label>
					</div>
					<div class="checkbox-inline">
						<label> <input type="radio"
							${model.sex == 0 ? 'checked':'' } name="sex" value="0"> 女
						</label>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="" class="col-sm-2 control-label">联系电话：</label>
				<div class="col-sm-20">
					<input type="text"
						class="form-control input300 validate[required,maxSize[20]]"
						id="phone" placeholder="请输入联系电话" name="phone"
						value="${model.phone }">
				</div>
			</div>
			<div class="form-group">
				<label for="" class="col-sm-2 control-label">电子邮箱：</label>
				<div class="col-sm-20">
					<input type="text"
						class="form-control input300  validate[custom[email]]"
						id="email" placeholder="请输入电子邮箱" name="email"
						value="${model.email }">
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$("#myformxAccountInfo").validationEngine({
		ajaxFormValidationMethod : 'post',
		promptPosition : "centerRight"
	});
</script>