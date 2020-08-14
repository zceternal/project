var dialog;
$(function() {

	// 新增沟通记录
	$("#addNewRecord").on("click", function() {
		var $this = $(this);
		dialog = $.sk.open({
			url : "record",
			data : {
				customerId : $this.data("cusid")
			},
			width : 900,
			height:600,
			title : "新增沟通记录",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformAddRemind").submit();
				}
			} ]
		});
	});

	$("#addContactX").click(
			function() {
				var $this = $(this);
				var customerId = $(this).data("cusid");
				var customerName = $(this).data("cusname");
				dialog = $.sk.open({
					// url:"../contact/add?",
					url : "../cusRecord/addContact?id=" + customerId + "&name="
							+ customerName,
					width : 900,
					height : 600,
					title : "新增联系人",
					buttons : [ {
						html : "确定",
						"class" : "btn btn-minier btn-success delay",
						click : function() {
							$("#contactAddForm").submit();
						}
					} ]
				});
			});
	// 客户管理-客户联系人中修改联系人 type:0 客户不可选，其他的可选
	$("#customerContactId").live("click", function() {
		var $this = $(this);
		var contanctId = $this.data("editid");
		dialog = $.sk.open({
			url : "../contact/edit?id=" + contanctId + "&type=0",
			width : 1000,
			height : 600,
			title : "修改联系人",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#contactEditForm").submit();
					setTimeout('refresh()', 1000)
				}
			} ]
		});
	});
	// 修改客户基本信息
	$("#updateCusInfo").on("click", function() {
		var $this = $(this);
		dialog = $.sk.open({
			url : "../cusRecord/updateCusInfo",
			width : 900,
			height : 600,
			data : {
				id : $this.data("cusid")
			},
			title : "修改客户",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformEdit").submit();

				}
			} ]
		});
	});
	
	
	// 新增客户提醒信息
	$("#addremind").on("click", function() {		
		var $this = $(this);
		dialog = $.sk.open({
			url : "addRemind",
			data : {
				id : $this.data("cusid")
			},
			width : 560,
			height : 340,
			title : "新增客户提醒",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformAddRemind").submit();
				}
			} ]
		});
	});
	
	
	// 投入公海
	$("#injecthighseas").on("click", function() {;
		var $this = $(this);		
		
		$.sk.confirm("确定要将客户移入公海吗？", function(result) {
			if (result) {
				$.post("../customer/injectHighSeas",{id:$this.data("cusid")},function(data){
					if(data.success){
						$.sk.success(data.msg,function(){
							window.location.href="../customer/index.html?cache=true";
						});
						
					}else{
						$.sk.error(data.msg);
					}
						
				});
			}
		});
		
		
		
	});
	

})


//提醒回调函数
function onCustomerSuccessAddRemind(data) {
	if (data.success) {
		$.sk.success("新增提醒成功", function() {
			//$("#myformAddRemind").submit();
			$.sk.close(dialog);
		});
	} else {
		$.sk.error(data.msg);
	}
}

// 点赞
function iLikeIt(recordId, loginId) {
	$.post("../cusRecord/iLikeIt", {
		"loginId" : loginId,
		"recordId" : recordId
	}, function(obj) {
		if (obj.success == true) {
			$("#likeQty_" + recordId).html(obj.likeQty);
			$("#thumbA_" + recordId).removeAttr("style");

		}

	}, "json");
}
// 点赞切换图片
function liked(self, link) {
	var $this = $(self);
	if (link == 0) {
		$this.attr("src", "../content/img/thumb_02.png");
	}
}

function goFilter() {
	$("#form_customer_record_search").submit();
}

function recordAddOnSuccess(data) {
	if (data.isSuccess) {
		$("#remarkRecord").val("");//情况处理记录
		ue.execCommand("clearDoc");//清空富文本框
		$("#form_customer_record_search").submit();

		$.sk.success(data.msg);
	} else {
		$.sk.error(data.msg);
		$("#form_customer_record_search").submit();
	}

}

function contactAddSuccessForShare(data) {
	if (data.success) {
		$.sk.success("添加成功", function() {
			$("#form_contact_search").submit();
			$.sk.close(dialog);
		});
	} else {
		$.sk.error(data.msg);
	}
}

function onCustomerSuccessEdit(data) {
	if (data.success) {
		$.sk.success("客户修改成功", function() {
			var khztshare = $("input:radio[id='status_share']:checked").val();

			$("#status option[value='" + khztshare + "']").attr("selected",
					"selected");

			$("#form_cusInfo_search").submit();
			$.sk.close(dialog);
		});
	} else {
		$.sk.error(data.msg);
	}

}

function updateStatus(customerId, status,index) {
	if($("#statusVal").val()==status)return;
	if($("a[data-index="+index+"]").hasClass("disabled"))return;
	$.sk.confirm("状态修改后不可逆，您确定修改客户状态吗？", function(result) {
		if (result) {
			$.post("../customer/updateStatus", {
				"customerId" : customerId,
				"status" : status
			}, function(obj) {
				if (obj.success) {
					$("#statusVal").val(status);
					$.sk.success(obj.msg);
					$("a[data-index]").each(function(i,v){
						var $this = $(v);
						if(!$this.hasClass("disabled")&&$this.data("index")<index){
							$this.addClass("disabled");
						}
					})
				} else {
					$.sk.error(obj.msg);
				}
			}, "json");
		}
	});

}

function checkDetail(recordId) {
	$("#shortDiv_" + recordId).hide();
	$("#longDiv_" + recordId).slideDown(1000);

}
function hideDetail(recordId) {
	$("#longDiv_" + recordId).slideUp();
	$("#shortDiv_" + recordId).slideDown();

}

function checkDetailExt(recordId) {
	$("#shortDivExt_" + recordId).hide();
	$("#longDivExt_" + recordId).slideDown(1000);

}

function hideDetailExt(recordId) {
	$("#longDivExt_" + recordId).slideUp();
	$("#shortDivExt_" + recordId).slideDown();

}
function goShare(cusid) {
	dialog = $.sk.open({
		url : "../customer_share/share",
		data : {
			cusids : cusid,
			typeName : 1
		},
		width : 900,
		title : "客户共享",
		buttons : [ {
			html : "确定",
			"class" : "btn btn-minier btn-success delay",
			click : function() {

				$("#shareAddForm").submit();
			}
		} ]
	});
}

// 修改联系人成功后，回调函数????能否执行
function refresh() {
	$("#form_contact_search").submit();
}

// 双击行 查看详情
function dbShowDetails(contactId) {
	var $this = $(this);
	dialog = $.sk.open({
		url : "../contact/details",
		data : {
			id : contactId
		},
		width : 600,
		height : 600,
		title : "联系人详情"
	});
}

function onCustomerShareSuccess(obj) {
	if (obj.success) {
		$.sk.success(obj.msg)
		$("#form_allow_search").submit();
		$.sk.close(dialog);

	} else {
		$.sk.error(obj.msg);
	}

}
