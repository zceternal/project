
//提示生成函数
function zhyAlert(str){
	var $div=$("<div class='zhy_alert'></div>");
	$div.text(str);
    $div.appendTo("body");
	setTimeout(function(){
	    $div.remove();
	},1000);
}
//自定义radio checkbox样式
function setupLabel(){
	if($('.label_check input').length) {
		$('.label_check').each(function(){
			$(this).removeClass('c_on');
		});
		$('.label_check input:checked').each(function(){
			$(this).parent('label').addClass('c_on');
		});
	};
	if($('.label_radio input').length) {
		$('.label_radio').each(function(){
			$(this).removeClass('r_on');
		});
		$('.label_radio input:checked').each(function(){
			$(this).parent('label').addClass('r_on');
		});
	};
}

$(function(){
	$('body').addClass('has-js');
	$(":checkbox").each(function(){
		if($(this).parent('.label_check').length==0){
				$(this).wrap('<label class="label_check"></label>');
		}
	});
	$(":radio").each(function(){
		if($(this).parent('.label_radio').length==0){
				$(this).wrap('<label class="label_radio"></label>')
		}
	});
	$('.label_check,.label_radio').live("click",function(){
		setupLabel();
	});
	setupLabel();
});

function subStringTitle(){
  $("td").each(function(){
    if($(this).attr("title")){
      var str = $(this).attr("title");
      var str_null='';
      var len =str.length;
      var row = parseInt(len/30);
      if(row == 0){
        $(this).attr("title",str)
      }else{
        var arr = new Array();
        for(var i= 0 ;i < row ;i++){
            var arr_0= str.substr(i*30,30)+"\r";
            str_null+=arr_0
        }
        $(this).attr("title",str_null)
      }
    }
  })
}

function calcPageHeightI(doc) {
    var height  = doc.body.clientHeight
    return height
}
function loadIfrHeight(obj){
    var ifr= obj;
    var iDoc = ifr.contentDocument || ifr.document;
    var height = calcPageHeightI(iDoc);
    ifr.style.height = height+80 + 'px';
//  console.log(height)
}
$(function(){
  subStringTitle();
	//下拉列表js
	$(".login_box").hover(function(){
		$(".dropdown-menu-header").show();
		$(this).find(".arrow_down").removeClass("arrow_down").addClass("arrow_up");
	},function(){
		$(".dropdown-menu-header").hide();
		$(this).find(".arrow_up").removeClass("arrow_up").addClass("arrow_down");
	})
	$(".dropdown-text,.dropdown-toggle").live("click",function(){
		$(".areaError").hide()
      if($(this).siblings(".dropdown-menu").css("display")=='none'){
        $(this).siblings(".dropdown-menu").show();
      }else{
        $(this).siblings(".dropdown-menu").hide();
      }
    });
  $(".dropdown-menu li").live("click",function(){
      $(this).parents("ul").siblings(".dropdown-text").text($(this).find("a").text());
      $(this).parents("ul").siblings(".dropdown-text").attr("value",$(this).find("a").attr("value"));
      $(this).parents("ul").hide();
  });
  	$(".dropdown-menu li").each(function(){
  		$(this).click(function(){
  			$(this).parents("ul").siblings(".dropdown-text").text($(this).find("a").text());
  			$(this).parents("ul").hide();
  		})
  	});
  	$(".dropdown-menu").mouseleave(function(){
  		$(this).hide();
  	})
  	//左侧菜单栏
  	$("#mainFrame li").click(function(){
  		$(this).addClass("on").siblings().removeClass("on");
  	});
  	//头部搜索
  	$(".header .search_btn_white").click(function(){
  		$(this).removeClass("search_btn_white").siblings("input").show();
  	});
  	$(".header .search").mouseleave(function(){
  		$(this).find(".search_btn").addClass("search_btn_white").siblings("input").hide();
  	})
    
})
	
