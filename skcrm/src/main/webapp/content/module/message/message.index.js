var dialog;
$("span[data-delete-id]").live("click",function() {
		var $this = $(this);
		$.sk.confirm("您确定要删除此消息吗？", function(result) {
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

$("span[data-show-id]").live("click",function() {
	var $this = $(this);
	
	dialog = $.sk.open({
		url : "show",
		width:800,
		data : {
			id : $this.data("show-id")
		},
		width : 820,
		title : "查看信息"
	});
});
$("#tr_show").live("dblclick",function() {
	var $this = $(this);
	
	dialog = $.sk.open({
		url : "show",
		width:800,
		data : {
			id : $this.data("id")
		},
		width : 820,
		title : "查看信息"
	});
});


$("#tr_read").live("dblclick",function() {
	var $this = $(this);
	dialog = $.sk.open({
		url : "show",
		width:800,
		data : {
			id : $this.data("id")
		},
		width : 820,
		title : "查看信息",
		buttons : [ {
			html : "设置已读",
			"class" : "btn btn-minier btn-success delay",
			click : function() {
				
				$.post("setread.ajax",{id:$this.data("id")},function(data){
					if(data.success){
						parent.$("#msgCount").text(data.data);
						$("#myform").submit();
						$.sk.close(dialog);
					}
				},"json");
				
			}
		}]
	});
	
});
$("span[data-read-id]").live("click",function() {
	var $this = $(this);
	dialog = $.sk.open({
		url : "show",
		width:800,
		data : {
			id : $this.data("read-id")
		},
		width : 820,
		title : "查看信息",
		buttons : [ {
			html : "设置已读",
			"class" : "btn btn-minier btn-success delay",
			click : function() {
				
				$.post("setread.ajax",{id:$this.data("read-id")},function(data){
					if(data.success){
						parent.$("#msgCount").text(data.data);
						$("#myform").submit();
						$.sk.close(dialog);
					}
				},"json");
				
			}
		}]
	});
	
});

