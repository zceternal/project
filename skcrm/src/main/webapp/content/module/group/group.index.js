var dialog;

var $page, $formsearch;
$formsearch = $("#myform");
$(function() {
	$("#group_add").live("click", function() {
		var $this = $(this);
		dialog = $.sk.open({
			url : "add",
			width : 850,
			height: 450,
			title : "新增群组",
			buttons : [ {
				html : "保存",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformAdd").submit();

				}
			} ]
		});
	});

	$("a[data-edit-id]").live("click", function() {
		var $this = $(this);
		var id = $this.data("")
		dialog = $.sk.open({
			url : "edit",
			data : {
				id : $this.data("edit-id")
			},
			width : 850,
			height: 450,
			title : "修改群组",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformEdit").submit();
				}
			} ]
		});
	});

	$("span[data-delete-id]").live("click",function() {
		var $this = $(this);
		$.sk.confirm("您确定要删除<em>" + $this.data("title") + "</em>吗？", function(result) {
			if (result) {
				$.post("delete.ajax", {
					id : $this.data("delete-id")
				}, function(data) {
					if (data.success) {
						$("#myform").submit();
					} else
						$.sk.error(data.msg);
				}, "json");
			}
		});
	});
	

});

// 添加回调函数
function submit(data) {
	if (data.success) {
		$.sk.success("保存成功", function() {
			$formsearch.submit();
			$.sk.close(dialog);
			return;
		});
	} else {
		$.sk.error(data.msg);
	}

}

// 修改回调函数
function editSubmit(data) {
	if (data.success) {
		$.sk.success("保存成功", function() {
			$formsearch.submit();
			$.sk.close(dialog);
			return;
		});
	} else {
		$.sk.error(data.msg);
	}
}

function onSaveSuccess(data){
	if(data.success){
		$.sk.success("保存成功");
		$.sk.close(dialog);
	}
}
