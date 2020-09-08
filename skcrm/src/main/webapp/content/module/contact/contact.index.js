$(function() {

	$("a[data-id=contact_add]").click(function() {
		var $this = $(this);
		dialog = $.sk.open({
			url : "add",
			width : 1000,
			height : 650,
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
	

	// 分享联系人
	$("a[data-id=contact_share]").click(
			function() {
				var selectCheck = $("#tbDate>tbody").find(
						"input[type=checkbox]:checked");
				var $this = $(this);
				if (selectCheck.length > 0) {
					var data = $("#shareForm").serialize();
					dialog = $.sk.open({
						url : $this.data("url"),
						data : data,
						width : 900,
						height : 600,
						title : "联系人共享",
						buttons : [ {
							html : "确定",
							"class" : "btn btn-minier btn-success delay",
							click : function() {
								$("#contactShare").submit();
							}
						} ]
					});
				} else {
					$.sk.alert("请先选择您要共享的联系人。");
				}
			});
	// 删除列表数据
	$("span[data-delete-id]").live("click", function() {
		var $this = $(this);
		$.sk.confirm("您确定要删除<em>" + $this.data("title") + "</em>吗？", function(result) {
			if (result) {
				$.post("delete.ajax", {
					id : $this.data("delete-id")
				}, function(data) {
					if (data.success) {
						$.sk.success("删除成功", function() {
							$("#contactIndex").submit();
						});
					} else
						$.sk.error(data.msg);
				}, "json");
			}
		});
	});

	// 修改联系人
	$("a[data-edit-id]").live("click", function() {
		var $this = $(this);
		dialog = $.sk.open({
			url : "edit",
			data : {
				id : $this.data("edit-id"),
				type:1
			},
			width : 1000,
			height : 650,
			title : "编辑联系人",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#contactEditForm").submit();
				}
			} ]
		});
	});
	// 联系人详情
	$("a[data-details-id]").live("click", function() {
		var $this = $(this);
		dialog = $.sk.open({
			url : "details",
			data : {
				id : $this.data("details-id")
			},
			width : 600,
			height : 600,
			title : "联系人详情"
		});
	});

	/* 置顶操作 */
	$("a[data-sort]").live("click", function() {
		var $this = $(this);
		$.ajax({
			type : "POST",
			url : "up.ajax",
			data : {
				id : $this.data("id"),
				sort : $this.data("sort")
			},
			success : function(data) {
				if (data.success) {
					$.sk.success("置顶成功", function() {
						$("#contactIndex").submit();
					});
				} else
					$.sk.error(data.msg);
			}
		}, "json");
	});

	// 点击联系人客户,即可提交
	$(".dev-customerType").click(function() {
		var $this = $(this);
		$("#customerType").val($this.data("value"));
		$this.addClass("on").siblings().removeClass("on");
		$("#contactIndex").submit();
	});
	
	// 点击联系人角色，所有
	$(".dev-lxrjs-all").click(function() {
		var $this = $(this);
		$("#contactRole").val($this.data("value"));
		$this.addClass("on").siblings().removeClass("on");
		$("#contactIndex").submit();
	});
	
	// 点击销售负责人,即可提交
	$(".dev-acclist").click(function() {
		var $this = $(this);
		if ($this.attr("checked")) {
			$this.parent('label').addClass('c_on');
		} else {
			$this.parent('label').removeClass('c_on');
		}
		setTimeout(function(){
			$("#contactIndex").submit();
		}, 50);
		
		return true;
	});
	//点击搜索 分页回到第一页
	$(".dev-search-page").live("click",function(){
		$page.val("1");
	});
	
	// 点击联系人详情,即可提交
	$(".dev-contactDetails").click(function() {
		var $this = $(this);
		if ($this.attr("checked")) {
			$this.parent('label').addClass('c_on');
		} else {
			$this.parent('label').removeClass('c_on');
		}
		setTimeout(function(){
			$("#contactIndex").submit();
		}, 50);
		return true;
	});
	
});

function clearSearch() {
	$("#page").val(1);
	$("#content").val("");
}

// 添加回调函数
function onEditSuccess(data) {
	if (data.success) {
		$.sk.success("修改成功", function() {
			$.sk.close(dialog);
			$("#contactIndex").submit();
		});
	} else {
		$.sk.error(data.msg);
	}
}
// 添加回调函数
function contactAddSuccess(data) {
	if (data.success) {
		$.sk.success("添加成功", function() {
			$.sk.close(dialog);
			$("#contactIndex").submit();
		});
	} else {
		$.sk.error(data.msg);
	}
}
// 联系人分享成功的回调函数
function contactShareAddSuccess(data) {
	if (data.success) {
		$.sk.success("联系人共享成功")
		$.sk.close(dialog);
		$("#contactIndex").submit();

	} else {
		$.sk.error(data.msg);
	}
}
// 双击行 查看详情
function showDetails(contactId){
	var $this = $(this);
	dialog = $.sk.open({
		url : "details",
		data : {
			id : contactId
		},
		width : 600,
		height : 600,
		title : "联系人详情"
	});
}


