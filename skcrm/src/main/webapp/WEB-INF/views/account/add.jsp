<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<div class="main_content">
	<div class="panel panel-default">
		<form class="form-horizontal old_block" role="form" id="myformAdd"
			action="add" method="post" data-ajax="true"
			data-ajax-success="submit">
			<table width="100%">
				<tr>
					<td width="50%"><input type="hidden" name="type" id="subtype">
						<div class="form-group ">
							<label for="" class="col-sm-2 control-label">用户名<em
								class="colorred">*</em>：
							</label>
							<div class="col-sm-8">
								<input type="text"
									class="form-control validate[required,maxSize[50],ajax[ajaxLoginNameCall]]"
									id="loginName" name="loginName" placeholder="请输入用户名">
							</div>
						</div></td>
					<td width="50%" rowspan="4" style="vertical-align: top;">
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">头像： </label>
							<div class="col-sm-8 favorite">
								<!-- <input type="file" id="myFile" name="myFile"> <a
							href="javascript:void(0)" style="font-size: 16px; color: green;">
							<span id="uploadVistCard" class="glyphicon glyphicon-search"
							aria-hidden="true"></span>
						</a> -->
								<input id="avatar" name="avatar" value="" type="hidden">
								<input type="file" id="uploadVistCard" name="uploadVistCard"
									onchange="fileUpload()" />

								<div id="uploadImgPeanl"
									style="padding: 5px; border: 1px solid #ccc; margin-top: 5px; display: none;">
									<img id="uploadImg" src="${model.avatar }" height="116px"
										width="160px" alt="上传的头像" />
								</div>

							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">密码<em
								class="colorred">*</em>：
							</label>
							<div class="col-sm-8">
								<input type="password"
									class="form-control  validate[required,maxSize[50],minSize[6]]"
									id="newPassword" name="newPassword" placeholder="请输入密码">
							</div>
						</div>



					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">确认密码<em
								class="colorred">*</em>：
							</label>
							<div class="col-sm-8">
								<input type="password"
									class="form-control  validate[required,equals[newPassword],maxSize[50]]"
									id="confirmPassword" name="confirmPassword"
									placeholder="请输入确认密码">
							</div>
						</div>

					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group relative">
							<label for="" class="col-sm-2 control-label">记录类别： </label>
							<div class="col-sm-8 ">
								<c:forEach items="${dictJllx }" var="item" varStatus="status">
									<label><input name="recordType" class="dev-khly"
										type="checkbox" value="${item.id }" />${item.name }</label>
								</c:forEach>
								<!-- <input id="recordType" type="hidden" name="recordType" value="0"> -->
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group relative">
							<label for="" class="col-sm-2 control-label">选择部门<em
								class="colorred">*</em>：
							</label>
							<div class="col-sm-8">
								<input type="hidden" name="deptId" id="deptId"> <input
									id="deptSelText" type="text" readonly
									class="form-control  validate[required]" onclick="showMenu()" />
								<a href="javascript:void(0)" onclick="showMenu(); return false;"
									style="font-size: 16px; color: green;"> <span
									class="glyphicon glyphicon-search" aria-hidden="true"></span>
								</a>
							</div>
							<div id="menuContent" class="menuContent"
								style="display: none; position: absolute; left: 119px; top: 25px;">
								<ul id="tree" class="ztree"
									style="margin-top: 10px; border: 1px solid #617775; background: #f0f6e4; width: 230px; height: 200px; overflow-y: scroll; overflow-x: hidden;"></ul>
							</div>
						</div>
					</td>

				</tr>

				<tr>
					<td>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">部门负责人：</label>
							<div class="col-sm-8 ">
								<ul id="isDeptManagerTab" class="tab_button"
									style="margin-bottom: 0px;">

									<li data-id="1">是</li>
									<li class='on' data-id="0">否</li>
								</ul>
								<input id="isDeptManager" type="hidden" name="isDeptManager"
									value="0">
							</div>
						</div>

					</td>
					<td>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">用户工号：</label>
							<div class="col-sm-8">
								<input type="text"
									class="form-control  validate[minSize[3],maxSize[10],ajax[ajaxNumberCall]]"
									id="number" name="number" placeholder="请输入用户工号">
							</div>
						</div>


					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">姓名<em
								class="colorred">*</em>：
							</label>
							<div class="col-sm-8">
								<input type="text"
									class="form-control  validate[required,maxSize[20]]" id="name"
									name="name" placeholder="请输入姓名">
							</div>
						</div>
					</td>
					<td>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">性别：</label>
							<div class="col-sm-8 ">
								<ul id="sexTab" class="tab_button" style="margin-bottom: 0px;">

									<li class='on' data-id="1">男</li>
									<li data-id="0">女</li>
								</ul>
								<input id="sex" type="hidden" name="sex" value="1">
							</div>
						</div>

					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">联系电话<em
								class="colorred">*</em>：
							</label>
							<div class="col-sm-8">
								<input type="text"
									class="form-control  validate[required,maxSize[20],custom[mobile]]"
									id="phone" placeholder="请输入联系电话" name="phone">
							</div>
						</div>
					</td>
					<td>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">邮箱： </label>
							<div class="col-sm-8">
								<input type="text"
									class="form-control   validate[custom[email]]" id="email"
									placeholder="请输入电子邮箱" name="email">
							</div>
						</div>
					</td>
				</tr>

			</table>



		</form>
	</div>
</div>
<script type="text/javascript"
	src="../content/module/account/account.add.js"></script>
<script type="text/javascript">
	$("#myformAdd").validationEngine({
		ajaxFormValidationMethod : 'post',
		promptPosition : "centerRight"
	});

	//上传名片
	function fileUpload() {
		var fileData = $("#uploadVistCard").val();//取得上传文件的名称
		var fileName = fileData.substr(fileData.lastIndexOf("\\") + 1);
		var fileType = fileData.substr(fileData.indexOf(".") + 1);//取得上传文件的后缀

		if (fileType.toLowerCase() != "jpg" && fileType.toLowerCase() != "png" && fileType.toLowerCase() != "jpeg"
			&& fileType.toLowerCase() != "gif" && fileType.toLowerCase() != "bmp") {
		$.sk.error("请上传图片文件");
		return;
	}
		var file = document.getElementById("uploadVistCard").files;

		if (((file[0].size) / 1024.0 / 1024) > 10) {
			$.sk.error("上传的文件不能大于10M");
			return;
		}
		$.ajaxFileUpload({
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
			url : "../ueditor.do?action=uploadimage",
			secureuri : false, //是否启用安全提交,默认为false 
			fileElementId : 'uploadVistCard', //文件选择框的id属性
			dataType : 'json', //服务器返回的格式,可以是json或xml等
			success : function(data, status) { //服务器响应成功时的处理函数
				if (data.state = "SUCCESS") {
					$("#avatar").val(data.url);
					$("#uploadImgPeanl").css('display', 'inline-block');
					$("#uploadImg").attr('src', data.url);
				}
			},
			error : function(data, status, e) { //服务器响应失败时的处理函数
				$.sk.alert("上传失败!");
			}
		});
	}
</script>
