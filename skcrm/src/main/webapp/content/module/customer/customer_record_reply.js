var dialog;
$(function() {
	$("a[data-reply-id]").live("click", function() {
		var $this = $(this);
		var customerId = $(this).data("cusid");
		var customerName = $(this).data("cusname");
		dialog = $.sk.open({
			url : "../CustomerRecordRevert/reply",
			width : 500,
			title : "回复",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#recordId").val($this.data("reply-id"));
					$("#recordRevertformAdd").submit();
					
				}
			} ]
		});
	});

});
function onCustomerRecordRevertSuccessAdd(data) {
	if (data.success) {
		zhyAlert("添加成功");
		var customerId=$("#customerId").val();
		var recordId=$("#recordId").val();
		$.sk.close(dialog);
		$.ajax({
			type: "POST", 
			 url: "../customer/show",
			 data:{customerId:customerId},
			 async:false, 
			 success: function(html){ 
				 $("#customerRecordList").html(html);
				 checkDetail(recordId);
		        } 
		});
		
		
	} else {
		$.sk.error(data.msg);
	}
}
