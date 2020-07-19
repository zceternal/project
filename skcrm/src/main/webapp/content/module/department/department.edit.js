
$("#myform").validationEngine({ajaxFormValidationMethod: 'post'});

function submit(data){
	
	if(data.success){
		$.sk.success("部门保存成功",function(){
			var ifm = parent.document.getElementById("div_department").contentWindow;
			$("#myform",ifm.document.body).submit();
			parent.RemoveDiv("department_edit_" + $("#key").val());
		});
	}else{
		$.sk.error(data.msg);
	}
	
	
}

$(function(){
	$("#cancel").click(function(){
		
		parent.RemoveDiv("department_edit_" + $(this).data("id"));
		
	});
	
	
})