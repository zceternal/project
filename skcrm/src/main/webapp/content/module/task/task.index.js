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
	$("a[data-id=task_add]").live("click", function() {
		dialogaddtask = $.sk.open({
			url : "add",
			width :940,
			height:680,
			title : "新增任务",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#mytaskFormAdd").submit();
				}
			} ]
		});
	});

	// 任务反馈
/*
	$("a[data-id=task_back]").live("click", function() {

		var $this = $(this);
		dialog = $.sk.open({
			url : "back",
			width : 900,
			height:600,
			title : "任务反馈",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformBack").submit();
				}
			} ]
		});
	});
*/

	// 点击任务来源,即可提交
	$(".dev-rwly").click(function() {
		var $this = $(this);
		$this.addClass("on").siblings().removeClass("on");
		if ($this.attr("id")) {//清空所有选中的项
			$("input[class=dev-rwly]:checked").removeAttr("checked");
		}

		$("#source").val($this.data("value"));
		$("#myform").submit();
	});

	// 点击任务象限,即可提交
	$(".dev-khlx").click(function() {
		var $this = $(this);
		$this.addClass("on").siblings().removeClass("on");
		if ($this.attr("id")) {//清空所有选中的项
			 $("input[class=dev-khlx]:checked").removeAttr("checked");
		}
		
		$("#quadrant").val($this.data("value"));
		$("#myform").submit();
	});
	
	// 点击任务状态,即可提交
	$(".dev-khcgl").click(function() {
		var $this = $(this);
		$this.addClass("on").siblings().removeClass("on");
		if ($this.attr("id")) {//清空所有选中的项
			 $("input[class=dev-khcgl]:checked").removeAttr("checked");
		}
		
		$("#status").val($this.data("value"));
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

function onTaskSuccessAdd(data) {
	if (data.success) {
		$.sk.success("新增成功", function() {
			$("#content").val("");
			$page.val("1");
			$formsearch.submit();
			$.sk.close(dialogaddtask);
		});
	} else {
		$.sk.error(data.msg);
		$formsearch.submit();
	}
}

function onCustomerSuccessEdit(data) {
	if (data.success) {
		$.sk.success("任务修改成功", function() {
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
//选择库户来源
$("#selFrom").change(function(){	
	var fromVal=$("#selFrom").val();	
	$("#isFrom").val(fromVal);
	$("#myform").submit();
});