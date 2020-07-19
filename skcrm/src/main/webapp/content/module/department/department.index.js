var dialog; 

var $page, $formsearch;
$page = $(":hidden[name=page]");
$formsearch = $("#form_search");

$(function() {

	
	
	var thisId = null;
	var $container = $("#department");
	$container.height($(document).height() - 150);
	var setting = {
	        async: {
	            enable: true,
	            url: $container.data("url")
	        },
	        view: {
	            dblClickExpand: true,
	            showLine: true,
	            selectedMulti: false,
	            
	        },
	        data: {
	            simpleData: {
	                enable: true,
	                idKey: "id",//设置子节点
	                pIdKey: "pid",//设置父节点
	            }
	        },
	        callback: {
	           onAsyncSuccess: function() {
	                var nodes = zTree.getNodes();
	                if (nodes.length > 0) {
	                    zTree.selectNode(nodes[0]);
	                }
	           }, onClick:function (event, treeId, treeNode) {
	               if (thisId == treeNode.id) return;
	               $page.val("1");
	               $("#pid").val(treeNode.id);
	               $("#Id").val(treeNode.id);
	               $("#name").val(treeNode.name);
	               $("#form_search").submit();
	               thisId = treeNode.id;
	               
	           }
	        }
	    };
	zTree = $.fn.zTree.init($container, setting);
	
	 $("a[data-action=reloadTree]").click(function () {
	        zTree = $.fn.zTree.init($container, setting);
	    });
	    
	    $("a[data-action=reloadTable]").click(function () {
	        $page.val("1");
	        $formsearch.submit();
	   });
	
	
	
	$("a[data-id=department_add]").live("click",function(){
		var $this=$(this);
		var pid=$("#pid").val();
		dialog=$.sk.open({
			url:"add",
			data:{pid:pid},
			width:600,
			title:"新增部门",
			buttons:[
			        {
			        	html:"确定",
			        	"class": "btn btn-minier btn-success delay",
			        	click: function () {
			        		$("#Pid").val(pid);
			        		$("#departfromadd").submit();
			        		
			        	}
			        }
			        ]
		});
	});

	$("span[data-delete-id]").live("click",function() {
	    var $this = $(this);
	    var id=$this.data("delete-id");
	    $.sk.confirm("您确定要删除" + $this.data("title") + "吗？", function(result) {
	        if (result) {
	            $.post("delete.ajax", {
	                id: $this.data("delete-id")
	            }, function(data) {
	                if (data.success) {
	                    $.sk.success("删除成功", function() {
	                    	$formsearch.submit();
	                    	
	                    	var node = zTree.getNodeByParam("id", id, null);
                            zTree.removeNode(node)
                            
	                    });
	                } else $.sk.error(data.msg);
	            }, "json");
	        }
	    });
	});

	
	
	
	$("a[data-edit-id]").live("click",function() {
		var $this = $(this);
		dialog=$.sk.open({
			url:"edit",
			data:{id:$this.data("edit-id")},
			width:600,
			title:"修改部门",
			buttons:[
			        {
			        	html:"确定",
			        	"class": "btn btn-minier btn-success delay",
			        	click: function () {
			        		$("#departfromedit").submit();
			        	}
			        }
			        ]
		});
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


//点击搜索 分页回到第一页
$(".dev-search-page").live("click",function(){
	$page.val("1");
});


$("a[data-up-id]").live("click",function(){
	var $this=$(this);
	$.post("up.ajax", {
        id: $this.data("up-id"),
        pid: $this.data("pid")
    }, function(data) {
        if (data.success) {
        	var node = zTree.getNodeByParam("id", data.data);
            zTree.moveNode(node.getPreNode(), node, "prev");
        	 $formsearch.submit();
        } else $.sk.error(data.msg);
    }, "json");
})

$("a[data-down-id]").live("click",function(){
	var $this=$(this);
	$.post("down.ajax", {
        id: $this.data("down-id"),
        pid: $this.data("pid")
    }, function(data) {
        if (data.success) {
        	var node = zTree.getNodeByParam("id", data.data);
            zTree.moveNode(node.getNextNode(), node, "next");
            $formsearch.submit();
        } else $.sk.error(data.msg);
    }, "json");
})

function clearSearch(){
	$("#page").val(1);
	$("#content").val("");
}
//添加回调函数
function onDepartmentSuccessAdd(data){
	if(data.success){
		$.sk.success("保存成功",function(){
			var treeNode = zTree.getSelectedNodes()[0];
	        zTree.addNodes(treeNode, { "id": data.data.id, "name": data.data.name });
	        $page.val(1);
	        $formsearch.submit();
	        $.sk.close(dialog);
	        return;
		});
	}else{
		$.sk.error(data.msg);
	}
	
}
//添加回调函数
function onDepartmentSuccessEdit(data){
	if(data.success){
		$.sk.success("保存成功",function(){
			var node = zTree.getNodeByParam("id", data.data.id, null);
	        node.name = data.data.name;
	        zTree.updateNode(node);
	        $formsearch.submit();
			$.sk.close(dialog);
			return;
		});
	}else{
		$.sk.error(data.msg);
	}
}

function onSaveSuccess(data){
	if(data.success){
		$.sk.success("保存成功");
		$.sk.close(dialog);
	}
}
