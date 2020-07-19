
$("#myform").validationEngine({ajaxFormValidationMethod: 'post'});

function onDepartmentSuccess(data){

	if(data.success){
		$.sk.success("部门添加成功",function(){
			var ifm = parent.document.getElementById("div_department").contentWindow;
			ifm.clearSearch();
			$("#myform",ifm.document.body).submit();
			parent.RemoveDiv("department_add");
		});
	}else{
		$.sk.error(data.msg);
	}
	
	
}

$(function(){
	$("#cancel").click(function(){
		
		parent.RemoveDiv("department_add");
		
	});
	
	
})