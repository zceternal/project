var isNeed = true;//专用 客户转移提示

$(function() {
	$("span[data-id=customer_transfer]").click(function() {

		var $this = $(this);
		if (pushCheckedTran()) {
			var cusids = '';
			if (selectIds.length == 0) {
				$.sk.error("请选择要转移的客户");
				return;
			}
			for (var x = 0; x < selectIds.length; x++) {
				cusids = cusids + selectIds[x] + ",";
			}

			clearChecked();

			dialog = $.sk.open({
				url : $this.data("href"),
				data : {
					cusids : cusids
				},
				width : 900,
				height : 600,
				title : "客户转移",
				buttons : [ {
					html : "确定",
					"class" : "btn btn-minier btn-success delay",
					click : function() {
						$("#transferAddForm").submit();
					}
				} ]
			});
		}else{
			if (selectIds.length == 0&&isNeed) {
				$.sk.error("请选择要转移的客户");
				return;
			}
		}
	});

});

function pushCheckedTran() {

	var isT = false;
	var no = 0;
	$(":checkbox[data-client=checkbox_share]:checked").each(function() {

		var $this = $(this);

		if (!$($this).data("isfirst")) {
			$.sk.error("对不起,您不是转移客户的负责人");
			no = 1;
			isNeed = false;
			return false;
		}
		if (selectIds.indexOf($this.val()) != -1) {
			isNeed = true;
			no = 0;
			return false;
		}
		selectIds.push($this.val());
		isT = true;
	});
	if(no==0)isNeed = true;
	return isT;
}

function onCustomertransferSuccess(obj) {
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

	alert(obj.depts.length)
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
