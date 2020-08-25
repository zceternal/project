$(function() {
		var pageClick = function(pageindex) {
			$("#page").val(pageindex);
			$("#myformSelectCustomer").submit();
		};
		$("#Pagination").pager({
			pagenumber : '${pager.pageNum}',
			pagecount : '${pager.pages }',
			totalcount : '${pager.total}',
			buttonClickCallback : pageClick
		});

	});