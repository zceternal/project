var dialog;

$(function(){
	
	
	
})
//延后处理 回调函数
function onCustomerSuccessSaveRemind(data){
	if (data.success) {
		$.sk.success("修改提醒成功", function() {
			$("#myformcontent").submit();
			$.sk.close(dialog);
		});
	} else {
		$.sk.error(data.msg);
	}
}
//跟踪【客户详情】
function showDetail(customerId) {
			window.location = "../customer/show?customerId=" + customerId;
		}


