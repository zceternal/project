$(function() {
	$("span[data-id=customer_pool_batch]").click(function() {
		var myArray = new Array();
		var ids = '';

		var goFlag;
		$("input[data-client='checkbox_share']:checked").each(function() {
			var $this = $(this);

			if (!$($this).data("isfirst")) {
				goFlag = false;
			}

			myArray.push(this.value);
		})
		if (goFlag == false) {
			$.sk.error("所选客户中存在销售负责人非自己，请重新选择");
			//$.sk.error("您所选客户中存在您不是第一负责人，请联系第一负责人投入公海！");
			return;
		} else {
			if (myArray.length == 0) {
				$.sk.error("请选择客户");
				return;
			}
			for (var x = 0; x < myArray.length; x++) {
				ids += myArray[x] + ",";
			}

			$.sk.confirm("确定要将选中的客户投入公海吗？", function(result) {
				if (result) {
					$.post("../customer/injectHighSeasBatch", {
						ids : ids
					}, function(obj) {
						if (obj.success) {
							$.sk.success(obj.msg)
							$("#myform").submit();
						} else {
							$.sk.error(obj.msg)
						}
					}, "json")
				}
			});
		}

	})
})