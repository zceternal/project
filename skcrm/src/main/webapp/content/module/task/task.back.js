var dialog;
$(function() {

	$("span[data-id=task_back]").click(function() {
		var taskid = '';
	
		if(pushChecked()){
			if (selectIds.length == 0) {
				$.sk.error("请选择一条要反馈的任务");
				return;
			}
			if (selectIds.length <= 0 || selectIds.length >= 2) {
				$.sk.error("只能选择一条任务");
				return;
			}
			taskid = selectIds[0];
			clearChecked();
			dialog = $.sk.open({
				url : "back",
				data : {
					taskId : taskid
				},
				width : 900,
				height : 600,
				title : "任务反馈",
				buttons : [ {
					html : "确定",
					"class" : "btn btn-minier btn-success delay",
					click : function() {
						$("#myformBack").submit();
					}
				} ]
			});
		}else{
			if (selectIds.length == 0) {
				$.sk.error("请选择一条要反馈的任务");
				return;
			}
		}
	});

	$("a[data-removeshare]").live("click", function() {
		$(this).parent().parent().remove();
		if ($("#tr_contact_" + $(this).data("removeshare")).length > 0) {
			$("#tr_contact_" + $(this).data("removeshare")).remove();
		}
	});

});

function pushChecked() {
	selectIds=[];
	var isT = false;
	$(":checkbox[data-client=checkbox_share]:checked").each(function() {
		var $this = $(this);

		selectIds.push($this.data("shareid"));
		//selectNames.push($this.data("cusname"));
		isT = true;
	});
	return isT;
}

function clearChecked() {
	selectIds = [];
	selectNames = [];
	$(":checkbox[data-client=checkbox_share]").attr("checked", false).parent(
			'label').removeClass('c_on');
	$(":checkbox[data-for=checkbox_share]").attr("checked", false).parent(
			'label').removeClass('c_on');
}

function cuscheck(obj) {
	if (obj.checked) {
		selectIds.push(obj.value);
		//selectIds.push(obj.data("cusname"));
	} else {
		selectIds.remove(obj.value);
		//selectIds.remove(obj.data("cusname"));
	}
}
