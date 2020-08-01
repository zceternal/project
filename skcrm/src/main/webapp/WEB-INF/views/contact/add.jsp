<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.site_change .control-label {
	text-align: right;
}

.site_change .contact .checkbox-inline {
	padding-right: 10px;
}
</style>
<div class="main_content" style="overflow-y: auto">
	<div class="panel panel-default">

		<div class="site_change contact">
			<form class="form-horizontal" role="form" id="contactAddForm"
				action="add" method="post" data-ajax="true"
				data-ajax-success="contactAddSuccess">

				<div class="form-group">
					<label for="" class="col-sm-2 control-label">姓名<em
						class="colorred">*</em>：
					</label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control input150 validate[required,maxSize[30],minSize[2]]"
								id="name" name="name" placeholder="请输入姓名">

						</div>
					</div>
					<label for="" class="col-sm-2 control-label">姓别： </label>
					<div class="col-sm-20 favorite">
						<div>
							<label class="checkbox-inline "> <input type="radio"
								checked="checked" name="sex" value="1"> 男
							</label> <label class="checkbox-inline "> <input type="radio"
								name="sex" value="0"> 女
							</label> <label class="checkbox-inline "> <input type="radio"
								name="sex" value="2"> 未知
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">职务： </label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control input150 validate[maxSize[10]]"
								id="position" name="position" placeholder="请输入职务">
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">部门： </label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control input150 validate[maxSize[20]]"
								id="department" name="department" placeholder="请输入部门">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">所属客户： </label>
					<div class="col-sm-10 favorite">

						<input id="customerId" name="customerId" value="0" type="hidden">
						<input type="text" class="form-control " id="customerName"
							name="customerName" placeholder="请选择客户" readonly="readonly"
							style="width: 400px !important; display: inline;"> <a
							href="javascript:void(0)" id="selectCustomer"
							style="font-size: 16px; color: green;"> <span
							class="glyphicon glyphicon-search" aria-hidden="true"></span>
						</a>

					</div>

				</div>
				<div class="form-group">
					<label for="lastname" class="col-sm-2 control-label">联系人角色：</label>
					<div class="col-sm-10 favorite">
						<c:forEach items="${dictLxr }" var="item" varStatus="status">
								<label class="checkbox-inline "> <input type="radio"
									name="role" value="${item.id }"
									${status.index==0?'checked':'' }> ${item.name }
								</label>
						</c:forEach>
					</div>
				</div>
				<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">联系人来源：</label>
				<div class="col-sm-10 favorite">
					<c:forEach items="${dictLxrly }" var="item" varStatus="status">
						<label class="checkbox-inline ">
							<input type="radio" name="source" value="${item.id }" ${status.index==0?'checked':'' }> ${item.name }
							<c:if test="${item.id==150}">
								<input type="text" id="referrerPerson" name="referrerPerson" class="form-control input100 validate[maxSize[10]]">
							</c:if>
						</label>
					</c:forEach>
				</div>
			</div>
				<div class="form-group">
					<label for="lastname" class="col-sm-2 control-label">产品与服务：</label>
					<div class="col-sm-10 favorite">
						<c:forEach items="${cpfwList }" var="item" varStatus="status">
							<label class="checkbox-inline ">
								<input type="radio" name="buyService" value="${item.id }" ${status.index==0?'checked':'' }> ${item.name }
							</label>
						</c:forEach>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">直属上级： </label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control input150 validate[maxSize[50]]"
								id="directSupervisor" name="directSupervisor"
								placeholder="请输入直属上级">
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">直属下级： </label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control input150 validate[maxSize[50]]"
								id="subordinate" name="subordinate" placeholder="请输入直属下级">
						</div>
					</div>
				</div>
				<div class="form-group">

					<label for="" class="col-sm-2 control-label">常用手机<em
						class="colorred">*</em>：
					</label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control  input150 validate[required,custom[mobile]]"
								id="phone" name="phone" placeholder="请输入常用手机">
						</div>	
					</div>
				
				</div>


				<div class="form-group">
					<label for="" class="col-sm-2 control-label">备用手机1： </label>
					<div class="col-sm-4 favorite">

						<input type="text"
							class="form-control input150 validate[custom[mobile]]"
							id="phone1" name="phone1" placeholder="请输入备用手机1"
							style="display: inline;" /> <a href="javascript:void(0)"
							onclick="setPhone(1)" style="font-size: 16px; color: #000;">
							<span class="glyphicon glyphicon-check" aria-hidden="true"></span>
						</a>

					</div>
					<label for="" class="col-sm-2 control-label">备用手机2： </label>
					<div class="col-sm-4 favorite">

						<input type="text"
							class="form-control  input150 validate[custom[mobile]]"
							id="phone2" name="phone2" placeholder="请输入备用手机2" /> <a
							href="javascript:void(0)" onclick="setPhone(2)"
							style="font-size: 16px; color: #000;"> <span
							class="glyphicon glyphicon-check" aria-hidden="true"></span>
						</a>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">微信： </label>
					<div class="col-sm-4 favorite">

						<input type="text" data-prompt-position="inline"
							class="form-control  input150 validate[maxSize[20]]" id="wechat"
							name="wechat" placeholder="请输入常微信">

					</div>
					<label for="" class="col-sm-2 control-label">座机： </label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control  input150 validate[maxSize[20],custom[phone]]"
								id="specialPhone" name="specialPhone" placeholder="请输入座机">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">QQ： </label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control  input150 validate[maxSize[13]]" id="qq"
								name="qq" placeholder="请输入QQ">
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">邮箱： </label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control input150  validate[custom[email]]"
								id="email" name="email" placeholder="请输入邮件">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">微博： </label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control  input150 validate[maxSize[50]]"
								id="microblog" name="microblog" placeholder="请输入微博">
						</div>
					</div>
					<label for="" class="col-sm-2 control-label">传真： </label>
					<div class="col-sm-4 favorite">
						<div>
							<input type="text" data-prompt-position="inline"
								class="form-control  input150 validate[maxSize[50]]" id="fax"
								name="fax" placeholder="请输入传真">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">生日： </label>
					<div class="col-sm-4"
						style="margin-left: -15px; padding-left: 0px;">

						<div class="inline relative mr0">
							<input type="text" class="form-control input150"
								placeholder="请输入生日" name="birthday" id="birthday"
								input-type="date" onchange="ages(this.value)"> <span
								class="date_icon" style="cursor: pointer;"><i></i></span>
						</div>


					</div>
					<label for="" class="col-sm-2 control-label">年龄： </label>
					<div class="col-sm-4 favorite">
						<div>
							<label id="lblAge" class="col-sm-1 control-label"></label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">爱好： </label>
					<div class="col-sm-4 favorite" style="padding-left: 0px;">
						<div>
							<textarea class="form-control validate[maxSize[500]]" name="hobby" style="width: 615px"
								rows=4></textarea>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="" class="col-sm-2 control-label">名片： </label>
					<div class="col-sm-10 favorite">
						<!-- <input type="file" id="myFile" name="myFile"> <a
							href="javascript:void(0)" style="font-size: 16px; color: green;">
							<span id="uploadVistCard" class="glyphicon glyphicon-search"
							aria-hidden="true"></span>
						</a> -->
						<input id="visitingCard" name="visitingCard" value=""
							type="hidden"> <input type="file" id="uploadVistCard"
							name="uploadVistCard" onchange="fileUpload()" />

						<div id="uploadImgPeanl"
							style="padding: 5px; border: 1px solid #ccc; margin-top: 5px; display: none;">
							<img id="uploadImg" src="${model.visitingCard }" height="216px"
								width="360px" alt="上传的名片" />
						</div>

					</div>
				</div>
			</form>
		</div>

	</div>
</div>
<!--日历插件-->
<script type="text/javascript" src="../content/js/pickday.js"></script>
<script type="text/javascript">

	$("#contactAddForm").validationEngine({
		ajaxFormValidationMethod : 'post',
		promptPosition : "centerRight"
	});
	

	 $("input[name='name']").autocomplete({
	       source: function(request, response){
	    	   $.post("../contact/loadContact.ajax",{value:request.term,type:"1"},function(data){
	    		   response(data);
	    	   });
	    	   
	       },
	       select:function(event, ui){
		    	   var value=ui.item.value; 
		    	   var text=value.substr(0,value.indexOf(" "))
		    	   $("input[name='name']").val(text);
		    	   return false;
		       }
	     });
	 $("input[name='phone']").autocomplete({
	       source: function(request, response){
	    	   $.post("../contact/loadContact.ajax",{value:request.term,type:"2"},function(data){
	    		   response(data);
	    	   });
	    	   
	       },
	       select:function(event, ui){
		    	   var value=ui.item.value; 
		    	   var text=value.substr(0,value.indexOf(" "))
		    	   $("input[name='phone']").val(text);
		    	   return false;
		       }
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
				if (data.status = "SUCCESS") {
					$("#visitingCard").val(data.url);
					$("#uploadImgPeanl").css('display', 'block');
					$("#uploadImg").attr('src', data.url);
				}
			},
			error : function(data, status, e) { //服务器响应失败时的处理函数
				console.log(e);
				console.log(data);
				$.sk.alert("上传失败!");
			}
		});
	}
	//根据生日，自动算出年龄
	function ages(str) {
		var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);

		if (r == null)
			$.sk.alert("输入的日期格式错误！");
		var d = new Date(r[1], r[3] - 1, r[4]);
		if (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
				&& d.getDate() == r[4]) {
			var Y = new Date().getFullYear();
			$("#lblAge").html(Y - r[1]);
			return;
		}

	}
	//是否设置为常用手机
	function setPhone(value) {
		$.sk.confirm("您确定要设置为常用手机？", function(result) {
			if (result) {
				if (value == 1) {
					if ($("#phone1").val() == '') {
						$.sk.alert("请输入号码");
					} else {
						var phone = $("#phone").val();
						$("#phone").val($("#phone1").val());
						$("#phone1").val(phone);
					}
				} else {
					if ($("#phone2").val() == '') {
						$.sk.alert("请输入号码");
					} else {
						var phone = $("#phone").val();
						$("#phone").val($("#phone2").val());
						$("#phone2").val(phone);
					}
				}
			}
		});
	}
	$(function() {
		//选择客户
		$("#selectCustomer")
				.click(
						function() {
							$this = $(this);
							$uploaduserlist = $.sk
									.open({
										url : "customerList",
										width : 900,
										height : 600,
										title : "选择客户",
										buttons : [ {
											html : "确定",
											"class" : "btn btn-minier btn-success delay",
											click : function() {
												if ($(":input[data-name='choice']:checked").size() > 0) {
													var name = $(":input[data-name='choice']:checked").val();
													var id = $(":input[data-name='choice']:checked").attr("data_data");

													$("#customerName").val(name);
													$("#customerId").val(id);
													$.sk.close($uploaduserlist);
												} else {
													$.sk.alert("请先选择客户");
												}
											}
										} ],
									});
						});

	});
	
	
	
</script>





