var dialogForAdd;
var dialogForEdit;

var $page, $formsearch;
$page = $(":hidden[name=page]");
$formsearch = $("#myformSelectCustomer");

$(function() {
	//点击搜索 分页回到第一页
	$(".dev-search-customer-page").live("click",function(){
		$page.val("1");
	});

});

function clearSearch() {
	$("#page").val(1);
	$("#content").val("");
} 
//选择库户来源
$("#selFrom").change(function(){
	var fromVal=$("#selFrom").val();
	$("#isFrom").val(fromVal);
	$("#myformSelectCustomer").submit();
});