var cusName = "";
var dialog;
$(function(){
	
	$(".right_main").width($(document.body).width()-$(".left_menu").width());
	
	window.onresize = function(){
		if($(".left_menu").width()<100){
			$("body").addClass("left_short");
		}else{
			$("body").removeClass("left_short");
		}
		$(".right_main").width($(document).width()-$(".left_menu").width());
	}
	
	var myFrame = document.getElementById("myFrame");
	
	myFrame.style.height = $(document).height() - 63 + "px";
	
	
	
	$("a[data-link]").click(function(){
		var $this = $(this);
		var title = $this.data("title");
		myFrame.src = $this.data("link");
		
		
		//CreateDiv($this.data("id"),$this.data("link"),title == undefined ? $this.text() : title);
	});
	
	/*$("a[data-link]").each(function(){
		if($(this).parent().hasClass("on")){
			$(this).click();
		}
	});*/
	
	//个人信息
	$("li[data-info-id]").live("click",function(){
		var $this=$(this);
		dialog=$.sk.open({
			url:"account/accountInfo",
			width:600,
			title:"个人信息",
			buttons:[
			        {
			        	html:"确定",
			        	"class": "btn btn-minier btn-success delay",
			        	click: function () {
			        		cusName = $("#name").val();
			        		$("#myformxAccountInfo").submit();
			        	}
			        }
			        ]
		});
	});
	
	//个人信息
	$("li[data-editpwd-id]").live("click",function(){
		var $this=$(this);
		dialog=$.sk.open({
			url:"account/editPwd",
			width:600,
			title:"修改密码",
			buttons:[
			        {
			        	html:"确定",
			        	"class": "btn btn-minier btn-success delay",
			        	click: function () {
			        		cusName = $("#name").val();
			        		$("#myformxeditPwd").submit();
			        
			        	}
			        }
			        ]
		});
	});
	
});

function onSaveAccountInfo(data){
	if(data.success){
		$("#loginName").text(cusName);
		$.sk.success("修改成功");
		$.sk.close(dialog);
	}
}
function onSavePwd(data){
	
	if(data.success){
		$.sk.success("密码修改成功，请重新登录！",function(){
			location.href = "logout";
		})
	}else{
		$.sk.error(data.msg);
	}
}