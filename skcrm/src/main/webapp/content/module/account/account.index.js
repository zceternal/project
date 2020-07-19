var dialog;

var $page, $formsearch;
$page = $(":hidden[name=page]");
$formsearch = $("#myform");
$(function() {

	$("#account_add").live("click", function() {
		var $this = $(this);
		dialog = $.sk.open({
			url : "add",
			width : 850,
			title : "新增用户",
			buttons : [ {
				html : "保存",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#subtype").val(1);
					$("#myformAdd").submit();

				}
			}, {
				html : "保存&分配权限",
				"class" : "btn btn-minier btn-success-width delay",
				click : function() {
					$("#subtype").val(2);
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
			width : 820,
			title : "修改用户",
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
		$.sk.confirm("您确定要离职<em>" + $this.data("title") + "</em>吗？", function(result) {
			if (result) {
				$.post("delete.ajax", {
					id : $this.data("delete-id"),
					state : $this.data("delete-state")
				}, function(data) {
					if (data.success) {
						$.sk.success("离职成功", function() {
							$("#myform").submit();
						});
					} else
						$.sk.error(data.msg);
				}, "json");
			}
		});
	});
	
	$("span[data-quit-id]").live("click",function() {
		var $this = $(this);
		$.sk.confirm("您确定要删除<em>" + $this.data("title") + "</em>吗？", function(result) {
			if (result) {
				$.post("delete.ajax", {
					id : $this.data("quit-id"),
					state : $this.data("delete-state")
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
	
	//离职人员，恢复操作
	$("span[data-recover-id]").live("click",function(){
		var $this = $(this);		
		$.post("recover.ajax", {
			id : $this.data("recover-id")
		}, function(data) {
			if (data.success) {
				$.sk.success("恢复成功", function() {
					$("#myform").submit();
				});
			} else
				$.sk.error(data.msg);
		}, "json");
	});

	$("a[data-id]").live("click",function() {
		var $this = $(this);
		$.sk.confirm("您确定要重置<em>" + $this.data("name") + "</em>吗？", function(result) {
			if (result) {
				$.post("resetPwd.ajax", {
					id : $this.data("id")
				}, function(data) {
					if (data.success) {
						$.sk.success("重置成功", function() {
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
	
	
	$("a[data-auth-id]").live("click",function(){
		var $this = $(this);
		dialog=$.sk.open({
			url:"auth",
			data:{id:$this.data("auth-id")},
			width:"80%",
			title:"分配权限",
			buttons:[
			        {
			        	html:"确定",
			        	"class": "btn btn-minier btn-success delay",
			        	click: function () {
			        		$("#moduleOpts").submit();
			        	}
			        }
			        ]
		});
		
		
	});

});

// 添加回调函数
function submit(data) {
	if (data.success) {
		$.sk.success("保存成功", function() {
			$("#nameSerch").val("");
			$page.val("1");
			$formsearch.submit();
			if(data.data.type==2){
				$.sk.close(dialog,openAuth(data));
			}else{
				$.sk.close(dialog);
			}
			
			return;
		});
	} else {
		$.sk.error(data.msg);
	}

}

function openAuth(data){
	if(data.success){
		dialog=$.sk.open({
			url:"auth",
			data:{id:data.data.id},
			width:"80%",
			title:"分配权限",
			buttons:[
			        {
			        	html:"确定",
			        	"class": "btn btn-minier btn-success delay",
			        	click: function () {
			        		$("#moduleOpts").submit();
			        	}
			        }
			        ]
		});
	}
	
}

// 添加回调函数
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

function clearSearch() {
	$("#page").val(1);
	$("#content").val("");
}

//选择人员状态
$("#selState").change(function(){
	var stateVal=$("#selState").val();	
	$("#state").val(stateVal);
	$("#myform").submit();
});
