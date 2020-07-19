var dialog;
$(function() {

	$("span[data-id=customer_sharex]").click(function() {
		var cusids = '';
		var cusnames = '';
	
		if(pushChecked()){
			if (selectIds.length == 0) {
				$.sk.error("请选择要共享的客户");
				return;
			}
			for (var x = 0; x < selectIds.length; x++) {
				cusids = cusids + selectIds[x] + ",";
				cusnames = cusnames + selectNames[x] + ",";
			}
			clearChecked();
			dialog = $.sk.open({
				url : "../customer_share/share",
				data : {
					cusids : cusids,
					cusnames : cusnames
				},
				width : 900,
				height : 600,
				title : "客户共享",
				buttons : [ {
					html : "确定",
					"class" : "btn btn-minier btn-success delay",
					click : function() {
						$("#shareAddForm").submit();
					}
				} ]
			});
		}else{
			if (selectIds.length == 0) {
				$.sk.error("请选择要共享的客户");
				return;
			}
		}
	});

	$("td[xyz=z]").live("click", function() {

		/*
		 * var csid=$(this).data("customerid"); var $this = $("#check_"+csid);
		 * var state = $this.attr("checked") == undefined ? false : true;
		 * $this.attr("checked",!state); if (state) {
		 * $this.parent('label').addClass('c_on'); $(":checkbox[data-client='" +
		 * $this.data("for") + "']") .parent('label').addClass('c_on');
		 *  } else { $this.parent('label').removeClass('c_on');
		 * $(":checkbox[data-client='" + $this.data("for") + "']")
		 * .parent('label').removeClass('c_on');
		 *  }
		 */
	})

	$("a[data-removeshare]").live("click", function() {
		$(this).parent().parent().remove();
		if ($("#tr_contact_" + $(this).data("removeshare")).length > 0) {
			$("#tr_contact_" + $(this).data("removeshare")).remove();
		}
	});

});

function pushChecked() {
	
	var isT = false;
	
	$(":checkbox[data-client=checkbox_share]:checked").each(function() {

		var $this = $(this);
	    
		/*if(!$($this).data("isfirst"))
			{
			$.sk.error("对不起,您不是共享客户的负责人");
			return false;
			}*/
		if (selectIds.indexOf($this.val()) != -1)
			{
			return false;
			}
		selectIds.push($this.val());
		selectNames.push($this.data("cusname"));
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
		selectIds.push(obj.data("cusname"));
	} else {
		selectIds.remove(obj.value);
		selectIds.remove(obj.data("cusname"));
	}
}

function onCustomerShareSuccess(obj) {
	if (obj.success) {
		$.sk.success(obj.msg)
		$("#myform").submit();
		$.sk.close(dialog);
		selectIds = []
		selectNames = []
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