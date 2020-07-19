<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
.control-label {
	text-align: right;
}

.contact .checkbox-inline {
	padding-right: 10px;
}
.form-group label.col-sm-3,.form-group label.col-sm-2{color:#999;width:115px;padding-left:0px}
.form-group{margin-bottom:5px;}
.form-group+p{margin-top:20px}
.contact p.color333{margin-left:10px}
</style>
<div class="main_content" style="overflow-y: auto">
	<div class="panel panel-default">

		<div class="site_change contact">
			<form class="form-horizontal" role="form" id="contactDetailsForm"
				action="../contact/details" method="post" data-ajax="true" data-ajax-success="">
				<p class="color333 font14">基本信息1</p>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">姓名<em
						class="colorred">*</em>：
					</label>
					<div class="col-sm-3 favorite">
						<div>
							<label>${model.name }</label>
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">姓别： </label>
					<div class="col-sm-4 favorite">
						<div>
							<label class="checkbox-inline "> <input type="radio"
								${model.sex==1?'checked':'' } name="sex" value="1"> 男
							</label> <label class="checkbox-inline "> <input type="radio"
								${model.sex==0?'checked':'' } name="sex" value="0"> 女
							</label> <label class="checkbox-inline "> <input type="radio"
								${model.sex==2?'checked':'' } name="sex" value="2"> 未知
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">职务： </label>
					<div class="col-sm-3 favorite">
						<div>
							<label>${model.position }</label>
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">部门： </label>
					<div class="col-sm-4 favorite">
						<div>
							<label>${model.department }</label>

						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="" class="col-sm-3 control-label">所属客户： </label>
					<div class="col-sm-9 favorite">
						<div style="float: left;">
							<label>${model.customerName }</label>
						</div>
					</div>
				</div>

                <p class="color333 font14">基本信息2</p>
				<div class="form-group">
					<label for="lastname" class="col-sm-3 control-label">角色关系：</label>
					<div class="col-sm-9 favorite">
						<div>
							<c:forEach items="${dictLxr }" var="item">
								<label class="checkbox-inline "> <c:choose>
										<c:when test="${model.role==item.id}">
											<input type="radio" name="role" value="${item.id }" checked>
									        ${item.name }
										</c:when>
										<c:otherwise>
											<input type="radio" name="role" value="${item.id }">
									        ${item.name }
										</c:otherwise>
									</c:choose>

								</label>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">直属上级： </label>
					<div class="col-sm-3 favorite">
						<div>
							<label>${model.directSupervisor }</label>
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">直属下级： </label>
					<div class="col-sm-4 favorite">
						<div>
							<label>${model.subordinate }</label>
						</div>
					</div>
				</div>
				 <p class="color333 font14">基本信息3</p>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">常用手机<em
						class="colorred">*</em>：
					</label>
					<div class="col-sm-9 favorite">
						<div>
							<label>${model.phone }</label>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="" class="col-sm-3 control-label">备用手机1： </label>
					<div class="col-sm-3 favorite">
						<div>
							<label>${model.phone1 }</label>
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">备用手机2： </label>
					<div class="col-sm-4 favorite">
						<div>
							<label>${model.phone2 }</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">微信： </label>
					<div class="col-sm-3 favorite">
						<label>${model.wechat }</label>
					</div>
					<label for="" class="col-sm-2 control-label">座机： </label>
					<div class="col-sm-4 favorite">
						<div>
							<label>${model.specialPhone }</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">QQ： </label>
					<div class="col-sm-3 favorite">
						<div>
							<label>${model.qq }</label>
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">邮箱： </label>
					<div class="col-sm-4 favorite">
						<div>
							<label>${model.email }</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">微博： </label>
					<div class="col-sm-3 favorite">
						<div>
							<label>${model.microblog }</label>
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">传真： </label>
					<div class="col-sm-4 favorite">
						<div>
							<label>${model.fax }</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">生日： </label>
					<div class="col-sm-3 favorite">
						<div>
							<label id="lblBirthday"><fmt:formatDate
									value="${model.birthday }" pattern='yyyy-MM-dd' /></label>
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">年龄： </label>
					<div class="col-sm-4 favorite">
						<div>
							<label id="lblAge" class="col-sm-1 control-label"> </label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">爱好： </label>
					<div class="col-sm-9 favorite">
						<div style="padding-left: 1px;">
							<label>${model.hobby }</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-3 control-label">名片： </label>
					<div class="col-sm-9 favorite">
						<c:if test="${model.visitingCard != '' }">
							<div
								style="padding: 5px; border: 1px solid #ccc; margin-top: 5px;">
								<img id="uploadImg" src="${model.visitingCard }" height="216px"
									width="360px" alt="上传的名片" />
							</div>


						</c:if>
					</div>
				</div>
			</form>
		</div>

	</div>
</div>
<script type="text/javascript">
	$("#contactEditForm").validationEngine({
		ajaxFormValidationMethod : 'post'
	});
	$(function() {
		//根据生日，自动算出年龄
		var str = $("#lblBirthday").html();
		var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
		var d = new Date(r[1], r[3] - 1, r[4]);
		if (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
				&& d.getDate() == r[4]) {
			var Y = new Date().getFullYear();
			$("#lblAge").html(Y - r[1]);
			return;
		}

	})
</script>