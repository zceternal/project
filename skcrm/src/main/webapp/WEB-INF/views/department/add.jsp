<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="main_content create_site">

	<div class="panel panel-default choose_block">
		<form class="form-horizontal old_block " role="form"
			id="departfromadd" action="add" method="post" data-ajax="true"
			data-ajax-success="onDepartmentSuccessAdd">

			<div class="form-group">
				<input id="Pid" name="pid" type="hidden" value="0" /> <label for=""
					class="col-sm-2 control-label">父级节点：</label> <label style="padding-top:5px;">${name}</label>
			</div>

			<div class="form-group">

				<input id="Order" name="order" type="hidden" /> <label for=""
					class="col-sm-2 control-label">名称<em
						class="colorred">*</em>：</label>
				<div class="col-sm-20">
					<input type="text"
						class="form-control input300 validate[maxSize[10],required]"
						id="name" name="name" placeholder="部门名称">
				</div>
			</div>

			<div class="form-group">
				<label for="" class="col-sm-2 control-label">备注：</label>
				<div class="col-sm-20">
					<textarea class="form-control input300 validate[maxSize[100]]"
						id="remark" name="remark" placeholder="请输入备注"></textarea>
				</div>
			</div>
			<div class="well_head">
				<div class="line"></div>
				<span class="pull-left">权限分配</span>
			</div>
			<div class="form-group zhanghao_js_add" style="width: 557px;max-height:300px;overflow-y:auto">

				<c:forEach items="${model }" var="item" varStatus="stat">

					<div class="form-group">
						<label class="control-label"> <label
							class="label_check ${item.checked ? "c_on":"" }"> <input
								${item.checked ? "checked":"" } type="checkbox"
								data-for="checkbox_${stat.index }" class="sk_checkbox">${item.groupName }
						</label>
						</label>
						<div class="switch_box" style="display: block;">

							<c:forEach var="itm" items="${item.items }">

								<label class="checkbox-inline"> <label
									class='label_check ${itm.checked ? "c_on":"" }'> <input
										name="authKey" ${itm.checked ? "checked":"" }
										data-client="checkbox_${stat.index }" type="checkbox"
										class="sk_checkbox" value="${itm.key }"> ${itm.name }
								</label>
								</label>
							</c:forEach>

						</div>
					</div>
				</c:forEach>
			</div>

		</form>
	</div>
</div>
<script type="text/javascript">
	$("#departfromadd").validationEngine({
		ajaxFormValidationMethod : 'post',
		promptPosition : "centerRight"
	});
</script>

