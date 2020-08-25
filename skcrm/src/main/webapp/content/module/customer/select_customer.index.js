var dialogForAdd;
var dialogForEdit;

var $page, $formsearch;
$page = $(":hidden[name=page]");
$formsearch = $("#myformSelectCustomer");

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
							$("#myformSelectCustomer").submit();
						});
					} else
						$.sk.error(data.msg);
				}, "json");
			}
		});
	});

	//点击搜索 分页回到第一页
	$(".dev-search-customer-page").live("click",function(){
		$page.val("1");
	});
	

	// 新增
	$("a[data-id=customer_add]").live("click", function() {
		
		var $this = $(this);
		dialogForAdd = $.sk.open({
			url : "add",
			width : 900,
			height:600,
			title : "新增客户",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformSelectCustomerAdd").submit();
				}
			} ]
		});
	});

	// 修改
	$("a[data-id=customer_edit]").live("click", function() {
		var $this = $(this);
		dialogForEdit = $.sk.open({
			url : "edit",
			width : 900,
			height:600,
			data : {
				id : $this.data("edit-id")
			},
			title : "修改客户",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformSelectCustomerEdit").submit();
				}
			} ]
		});
	});

	

	// 点击跟 踪 状 态,即可提交
	$(".dev-trace").click(function() {
		var $this = $(this);
		$("#traceType").val($this.data("value"));
		$this.addClass("on").siblings().removeClass("on");
		$("#myformSelectCustomer").submit();
	});

	// 点击销售 状 态,即可提交
	$(".dev-xszt").click(function() {
		var $this = $(this);
		$("#status").val($this.data("value"));
		$this.addClass("on").siblings().removeClass("on");
		$("#myformSelectCustomer").submit();
	});

	// 点击客户类型,即可提交
	$(".dev-khlx").click(function() {
		var $this = $(this);
		$this.addClass("on").siblings().removeClass("on");
		if ($this.attr("id")) {//清空所有选中的项
			 $("input[class=dev-khlx]:checked").removeAttr("checked");
		}
		
		$("#customerType").val($this.data("value"));
		$("#myformSelectCustomer").submit();
	});
	
	// 点击客户成功率,即可提交
	$(".dev-khcgl").click(function() {
		var $this = $(this);
		$this.addClass("on").siblings().removeClass("on");
		if ($this.attr("id")) {//清空所有选中的项
			 $("input[class=dev-khcgl]:checked").removeAttr("checked");
		}
		
		$("#salesSuccessRate").val($this.data("value"));
		$("#myformSelectCustomer").submit();
	});

	// 点击销售负责人,即可提交
	$(".dev-acclist").click(function() {
		var $this = $(this);
		if ($this.attr("id")) {//清空所有选中的项
			 $("input[class=dev-acclist]:checked").removeAttr("checked");
		}
		$this.addClass("on").siblings().removeClass("on");
		$("#accountId").val($this.data("value"));
		$("#myformSelectCustomer").submit();
	});
	// 点击客户来源(销售形式),即可提交
	$(".dev-cusSourceList").click(function() {
		var $this = $(this);
		if ($this.attr("id")) {//清空所有选中的项
			$("input[class=dev-cusSourceList]:checked").removeAttr("checked");
		}
		$this.addClass("on").siblings().removeClass("on");
		$("#cusSource").val($this.data("value"));
		$("#myformSelectCustomer").submit();
	});
	// 点击产品及服务,即可提交
	$(".dev-buyServiceList").click(function() {
		var $this = $(this);
		if ($this.attr("id")) {//清空所有选中的项
			$("input[class=dev-buyServiceList]:checked").removeAttr("checked");
		}
		$this.addClass("on").siblings().removeClass("on");
		$("#buyService").val($this.data("value"));
		$("#myformSelectCustomer").submit();
	});
	// 点击销售推进状态,即可提交
	$(".dev-followStateList").click(function() {
		var $this = $(this);
		if ($this.attr("id")) {//清空所有选中的项
			$("input[class=dev-followStateList]:checked").removeAttr("checked");
		}
		$this.addClass("on").siblings().removeClass("on");
		$("#followState").val($this.data("value"));
		$("#myformSelectCustomer").submit();
	});
	// 点击下一步计划状态,即可提交
	$(".dev-nextPlanStateList").click(function() {
		var $this = $(this);
		if ($this.attr("id")) {//清空所有选中的项
			$("input[class=dev-nextPlanStateList]:checked").removeAttr("checked");
		}
		$this.addClass("on").siblings().removeClass("on");
		$("#status").val($this.data("value"));
		$("#myformSelectCustomer").submit();
	});


});

function onCustomerSuccessAdd(data) {
	if (data.success) {
		$.sk.success("客户新增成功", function() {
			$("#content").val("");
			$page.val("1");
			$formsearch.submit();
			$.sk.close(dialogForAdd);
		});
	} else {
		$.sk.error(data.msg);
		$formsearch.submit();
	}

}

function onCustomerSuccessEdit(data) {
	if (data.success) {
		$.sk.success("修改客户成功", function() {
			$("#content").val("");
			$page.val("1");
			$formsearch.submit();
			$.sk.close(dialogForEdit);
		});
	} else {
		$.sk.error(data.msg);
	}

}

function clearSearch() {
	$("#page").val(1);
	$("#content").val("");
} 
//选择库户来源
$("#selFrom").change(function(){	
	var fromVal=$("#selFrom").val();	
	$("#isFrom").val(fromVal);
	$("#myformSelectCustomer").submit();
});