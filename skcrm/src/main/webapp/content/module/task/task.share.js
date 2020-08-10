var dialog;
$(function() {
	$("span[data-id=task_sharex]").click(function() {
		var ids = '';
		if(pushChecked()){
			if (selectIds.length == 0) {
				$.sk.error("请选择要共享的任务");
				return;
			}
			for (var x = 0; x < selectIds.length; x++) {
				ids = ids + selectIds[x] + ",";
			}
			if(ids){
				ids = ids.substr(0, ids.length - 1);
			}
			clearChecked();
			dialog = $.sk.open({
				url : "taskShare",
				data : {
					ids : ids,
				},
				width : 900,
				height : 600,
				title : "任务共享",
				buttons : [ {
					html : "确定",
					"class" : "btn btn-minier btn-success delay",
					click : function() {
						$("#taskShareForm").submit();
					}
				} ]
			});
		}else{
			if (selectIds.length == 0) {
				$.sk.error("请选择要共享的任务");
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

function onTaskShareSuccessAdd(data) {
	if (data.success) {
		$.sk.success("共享成功", function() {
			// $("#content").val("");
			// $page.val("1");
			// $formsearch.submit();
			$.sk.close(dialog);
		});
	} else {
		$.sk.error(data.msg);
		//$formsearch.submit();
	}
}
function pushChecked() {
	
	var isT = false;
	
	$(":checkbox[data-client=checkbox_share]:checked").each(function() {

		var $this = $(this);
		selectIds.push($this.data("value"));
		isT = true;
	});
	return isT;
}

function clearChecked() {
	selectIds = [];
	$(":checkbox[data-client=checkbox_share]").attr("checked", false).parent(
			'label').removeClass('c_on');
	$(":checkbox[data-for=checkbox_share]").attr("checked", false).parent(
			'label').removeClass('c_on');
}

function cuscheck(obj) {
	if (obj.checked) {
		selectIds.push(obj.value);
	} else {
		selectIds.remove(obj.value);
	}
}

function onCustomerShareSuccess(obj) {
	if (obj.success) {
		$.sk.success(obj.msg)
		$("#myform").submit();
		$.sk.close(dialog);
		selectIds = []
	} else {
		$.sk.error(obj.msg);
		// $.sk.close(dialog);
		// selectIds= []
	}
}

var tableStr = '';
function buildTable(obj) {

	for (var x = 0; x < obj.depts.length; x++) {
		var rsp = 1;
		if (obj.depts[x].hasNext) {
			rsp = obj.depts[x].size
		}
		tableStr += "<tr><td rowspan='" + rsp + "' id=" + obj.depts[x].deptId
				+ "'>" + obj.depts[x].name + "id=" + obj.depts[x].deptId
				+ " size=" + rsp + "</td>";
		if (obj.depts[x].hasNext) {
			tableStr += "<td>" + buildTable(obj.depts[x]) + "</td>";
		}
		tableStr += "</tr>";
	}
	return tableStr;
}