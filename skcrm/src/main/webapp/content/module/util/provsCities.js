//根据省份列出城市
var option = "<option value='-1'>请选择</option>";
function listCity(prov){
	$("#dics option:gt(0)").remove();
	$.post("../address/listCitiesDics",{"parentId":prov.value},function (obj){
		$("#cities option:gt(0)").remove();
		for(var x=0;x<obj.allData.length;x++){
			var cdcode=obj.allData[x].code;
			var cdname=obj.allData[x].name;
			$("#cities").append("<option value='"+cdcode+"'>"+cdname+"</option>");
			
		}
	},"json");
}
//根据城市列出区县
function listDict(city){
	$.post("../address/listCitiesDics",{"parentId":city.value},function (obj){
		$("#dics option:gt(0)").remove();
		for(var x=0;x<obj.allData.length;x++){
			var cdcode=obj.allData[x].code;
			var cdname=obj.allData[x].name;
			$("#dics").append("<option value='"+cdcode+"'>"+cdname+"</option>");
			
		}
	},"json");
	
}
//根据区县列出乡镇
function listStreet(street){
	$.post("../address/listCitiesDics",{"parentId":street.value},function (obj){
		
		$("#street option:gt(0)").remove();
		for(var x=0;x<obj.allData.length;x++){
			var cdcode=obj.allData[x].code;
			var cdname=obj.allData[x].name;
			$("#street").append("<option value='"+cdcode+"'>"+cdname+"</option>");
			
		}
	},"json");
	
}

//根据户籍省份列出户籍城市
function listCity1(prov){
	$("#dics1 option:gt(0)").remove();
	$.post("../address/listCitiesDics",{"parentId":prov.value},function (obj){
		$("#cities1 option:gt(0)").remove();
		
		
		for(var x=0;x<obj.allData.length;x++){ 
			var cdcode=obj.allData[x].code;
			var cdname=obj.allData[x].name;
			$("#cities1").append("<option value='"+cdcode+"'>"+cdname+"</option>");
			
		}
	 
	},"json");
	
}
//根据户籍城市列出户籍区县
function listDict1(city){ 
	 
		$.post("../address/listCitiesDics",{"parentId":city.value},function (obj){
			$("#dics1 option:gt(0)").remove();
			for(var x=0;x<obj.allData.length;x++){
				var cdcode=obj.allData[x].code;
				var cdname=obj.allData[x].name;
				$("#dics1").append("<option value='"+cdcode+"'>"+cdname+"</option>");
				
			}
		},"json");
	 
}
//根据区县列出乡镇
function listStreet1(street){
	
	$.post("../address/listCitiesDics",{"parentId":street.value},function (obj){
		
		$("#street1 option:gt(0)").remove();
		for(var x=0;x<obj.allData.length;x++){
			var cdcode=obj.allData[x].code;
			var cdname=obj.allData[x].name;
			$("#street1").append("<option value='"+cdcode+"'>"+cdname+"</option>");
			
		}
	},"json");
	
}


//列出所有省份，并根据传入的省份code选中
function checkedProv(provCode){
	var checked1 = "";
	$("#porvinces").empty();
	$("#porvinces").append(option);
	$.post("../address/get_all_province",function (obj){
		for(var x=0;x<obj.allData.length;x++){
			var cdcode=obj.allData[x].code;
			var cdname=obj.allData[x].name;
			if(provCode == cdcode){
				checked1 = 'selected="selected"';
			}else{
				checked1 = "";
			}
			$("#porvinces").append("<option value='"+cdcode+"' "+checked1+">"+cdname+"</option>");
		}
	},"json");
}
//根据省份code列出所有城市，并根据传入的城市code选中
function checkedCity(provCode,cityCode){
	var checked2 = "";
	$("#cities").empty();
	$("#cities").append(option);
	$.post("../address/listCitiesDics",{"parentId":provCode},function (obj){
		for(var x=0;x<obj.allData.length;x++){
			var cdcode=obj.allData[x].code;
			var cdname=obj.allData[x].name;
			if(cityCode == cdcode){
				checked2 = 'selected="selected"';
			}else{
				checked2 = "";
			}
			$("#cities").append("<option value='"+cdcode+"' "+checked2+">"+cdname+"</option>");
		}
	},"json");
}

//根据城市code列出所有县，并根据传入的县code选中
function checkedArea(cityCode,areaCode){
	var checked3 = "";
	$("#dics").empty();
	$("#dics").append(option);
	$.post("../address/listCitiesDics",{"parentId":cityCode},function (obj){
		for(var x=0;x<obj.allData.length;x++){
			var cdcode=obj.allData[x].code;
			var cdname=obj.allData[x].name;
			if(areaCode == cdcode){
				checked3 = 'selected="selected"';
			}else{
				checked3 = "";
			}
			$("#dics").append("<option value='"+cdcode+"' "+checked3+">"+cdname+"</option>");
		}
	},"json");
}




