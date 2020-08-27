var dialog;

var $page, $formsearch;
$page = $(":hidden[name=page]");
$formsearch = $("#myform");

$(function() {

	$("span[data-delete-id]").click(function() {
		var $this = $(this);
		$.sk.confirm("您确定要删除<em>" + $this.data("title") + "</em>吗？", function(result) {
			if (result) {
				$.post("delete.ajax", {
					id : $this.data("delete-id")
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

	//点击搜索 分页回到第一页
	$(".dev-search-page").live("click",function(){
		$page.val("1");
	});
	

	// 新增
	$("a[data-id=customer_add]").live("click", function() {
		
		var $this = $(this);
		dialog = $.sk.open({
			url : "add",
			width : 900,
			height: 700,
			title : "新增客户",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformAdd").submit();
				}
			} ]
		});
	});

	// 修改
	$("a[data-id=customer_edit]").live("click", function() {
		var $this = $(this);
		$.post("transfer",{cusId : $this.data("edit-id")},function(data){
			if (data.success) {
				$.sk.success(data.msg, function() {
					$formsearch.submit();
					$.sk.close(dialog);
				});
			}else{
				$.sk.success(data.msg, function() {
					$formsearch.submit();
					$.sk.close(dialog);
				});
			}
		})
		
	});

	;
	

	// 点击跟 踪 状 态,即可提交
	$(".dev-trace").click(function() {
		var $this = $(this);
		$("#traceType").val($this.data("value"));
		$this.addClass("on").siblings().removeClass("on");
		$("#myform").submit();
	});

	// 点击销售 状 态,即可提交
	$(".dev-xszt").click(function() {
		var $this = $(this);
		$("#status").val($this.data("value"));
		$this.addClass("on").siblings().removeClass("on");
		$("#myform").submit();
	});

	// 点击客户类型,即可提交
	$(".dev-khlx").click(function() {
		var $this = $(this);
		$this.addClass("on").siblings().removeClass("on");
		if ($this.attr("id")) {//清空所有选中的项
			 $("input[class=dev-khlx]:checked").removeAttr("checked");
		}
		
		$("#customerType").val($this.data("value"));
		$("#myform").submit();
	});
	
	// 点击客户成功率,即可提交
	$(".dev-khcgl").click(function() {
		var $this = $(this);
		$this.addClass("on").siblings().removeClass("on");
		if ($this.attr("id")) {//清空所有选中的项
			 $("input[class=dev-khcgl]:checked").removeAttr("checked");
		}
		
		$("#salesSuccessRate").val($this.data("value"));
		$("#myform").submit();
	});

	// 点击销售负责人,即可提交
	$(".dev-acclist").click(function() {
		var $this = $(this);
		if ($this.attr("id")) {//清空所有选中的项
			 $("input[class=dev-acclist]:checked").removeAttr("checked");
		}
		$this.addClass("on").siblings().removeClass("on");
		$("#accountId").val($this.data("value"));
		$("#myform").submit();
	});


});

function onCustomerSuccessAdd(data) {
	if (data.success) {
		$.sk.success("客户新增成功", function() {
			$("#content").val("");
			$page.val("1");
			$formsearch.submit();
			$.sk.close(dialog);
		});
	} else {
		$.sk.error(data.msg);
		$formsearch.submit();
	}

}

function onCustomerSuccessEdit(data) {
	if (data.success) {
		$.sk.success("客户修改成功", function() {
			$formsearch.submit();
			$.sk.close(dialog);
		});
	} else {
		$.sk.error(data.msg);
	}

}

function clearSearch() {
	$("#page").val(1);
	$("#content").val("");
}