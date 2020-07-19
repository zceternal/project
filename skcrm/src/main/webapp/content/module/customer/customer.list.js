$(function() {
		var pageClick = function(pageindex) {
			$("#page").val(pageindex);
			$("#myform").submit();
		};
		$("#Pagination").pager({
			pagenumber : '${pager.pageNum}',
			pagecount : '${pager.pages }',
			totalcount : '${pager.total}',
			buttonClickCallback : pageClick
		});
		
		$("span[data-delete-accid]").click(function() {
			var $this = $(this);
			$.sk.confirm("您确定要删除" + $this.data("title") + "吗？", function(result) {
				if (result) {
					$.post("delete.ajax", {
						id : $this.data("delete-accid")
					}, function(data) {
						if (data.success) {
							$.sk.success("删除成功", function() {
								$("#myform").submit();
							});
						} else
							$.sk.error(data.msg);
					}, "json");
				}
			});
		});
		
		/* 置顶操作 */
		$("a[data-share-id]").click(function() {
			var $this = $(this);
			var shareId = $this.data("share-id");
			var allowAccountId = $this.data("allowaccount-id");
			var order = $this.data("order");
			$.ajax({
				type : "POST",
				url : "up.ajax",
				data : "shareId=" + shareId,
				success : function(data) {
					if (data.success) {
						$.sk.success("置顶成功", function() {
							$("#myform").submit();
						});
					} else
						$.sk.error(data.msg);
				}
			}, "json");
		});
		
		
	});