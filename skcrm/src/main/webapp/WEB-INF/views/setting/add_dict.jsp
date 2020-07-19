<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="main_content">
		
			<div class="panel panel-default choose_block">
				<form class="form-horizontal old_block" role="form" id="dictAddForm"
					action="../setting/add" method="post" data-ajax="true" data-ajax-success="onDictAddSuccess">
					
					<input type="hidden" id="pid" name="parentId" value="${parentId }">
					<div class="form-group">
					
						<label for="" class="col-sm-2 control-label">名称<em
						class="colorred">*</em>：</label>
						<div class="col-sm-20">
							<input type="text"
								class="form-control input300 validate[maxSize[10],required]"
								id="name" name="name" placeholder="字典名称">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">值：</label>
						<div class="col-sm-20">
							<input type="text"
								class="form-control input300 validate[custom[number],required]"
								id="value" name="value" value=0>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">备注：</label>
						<div class="col-sm-20">
							<textarea
								class="form-control input300 validate[maxSize[500]]"
								id="remark" name="remark" placeholder="请输入备注"></textarea>
						</div>
					</div>
				</form>

			</div>
		
	</div>
	
	
<script type="text/javascript">
$("#dictAddForm").validationEngine({ajaxFormValidationMethod: 'post',promptPosition:"centerRight"});</script>

