<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="site_block member_block">
	<div class="panel-body  other">
		<form class="form-horizontal zhanghao_js_add" id="moduleOpts"
			role="form" action="auth" method="post" data-ajax="true" data-ajax-success="onSaveSuccess">
			<input type="hidden" name="accountId" value="${param.id }">
			<c:forEach items="${model }" var="item" varStatus="stat">

				<div class="form-group">
					<label class="control-label">
						 <label class="label_check ${item.checked ? "c_on":"" }">
								<input ${item.checked ? "checked":"" } type="checkbox" data-for="checkbox_${stat.index }" class="sk_checkbox">${item.groupName }
						</label>
					</label>
					<div class="switch_box" style="display: block;">

						<c:forEach var="itm" items="${item.items }">

							<label class="checkbox-inline"> <label
								class='label_check ${itm.checked ? "c_on":"" }'> <input name="authKey" ${itm.checked ? "checked":"" } data-client="checkbox_${stat.index }"  type="checkbox" class="sk_checkbox"
									value="${itm.key }"> ${itm.name }
							</label>
							</label>
						</c:forEach>

					</div>
				</div>
			</c:forEach>
		</form>
	</div>