var zTree;
var setting = {
	async : {
		enable : true,
		url : "../department/ajaxManageTree"
	},
	view : {
		dblClickExpand : true,
		showLine : true,
		selectedMulti : false,
		autoCancelSelected: false
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",// 设置子节点
			pIdKey : "pid",// 设置父节点
		}
	},
	callback : {
		//beforeClick : beforeClick,
		onClick : onClick,
		onAsyncSuccess: function(){
			zTree.expandAll(true);
		}
	}
};

function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		$.sk.alert("只能选择子部门");
	return check;
}

function onClick(e, treeId, treeNode) {
	var node = zTree.getSelectedNodes()[0];

	$("#deptSelText").val(node.name);
	$("#deptId").val(node.id);
	hideMenu();
	$(".deptSelTextformError").hide();
}

function showMenu() {
	var cityObj = $("#deptSelText");
	var cityOffset = $("#deptSelText").offset();
	
	$("#menuContent").slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}

$(document).ready(function() {
	zTree = $.fn.zTree.init($("#tree"), setting);
	
	$("#sexTab>li").click(function() {
		var $this = $(this);
		$("#sex").val($this.data("id"));
		$this.addClass("on").siblings().removeClass("on");
	});
	$("#isDeptManagerTab>li").click(function() {
		var $this = $(this);
		$("#isDeptManager").val($this.data("id"));
		$this.addClass("on").siblings().removeClass("on");
	});
	$("#recordTypeTab>li").click(function() {
		var $this = $(this);
		$("#recordType").val($this.data("id"));
		$this.addClass("on").siblings().removeClass("on");
	});
});
