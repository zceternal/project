<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="main_content">
			<div class="panel panel-default choose_block">
				<form class="form-horizontal old_block" role="form" id="departfromedit"
					action="edit" method="post" data-ajax="true" data-ajax-success="onDepartmentSuccessEdit">
					
					<input id="key" value="${model.id }" name="id" type="hidden">
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">名称<em
						class="colorred">*</em>：</label>
						<div class="col-sm-20">
							<input type="text"
								class="form-control input300 validate[maxSize[10],required]" id="name"
				
								name="name" placeholder="请输入名称" value="${model.name }">
						</div>
					</div>


					<div class="form-group">
						<label for="" class="col-sm-2 control-label">备注：</label>
						<div class="col-sm-20">
							<textarea
								class="form-control input300 validate[maxSize[100]]" 
								id="remark" placeholder="请输入备注"  name="remark" value="${model.remark }">${model.remark }</textarea>
						</div>
					</div>
				</form>
		</div>
	</div>
<script type="text/javascript">
$("#departfromedit").validationEngine({ajaxFormValidationMethod: 'post',promptPosition:"centerRight"});</script>