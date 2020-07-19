$(function(){
//	var map = new BMap.Map("allmap");          
//	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
//	var local = new BMap.LocalSearch(map, {
//		renderOptions:{map: map}
//	});
	
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	
	
	
	
	$("#search_map").click(function(){
		var map = new BMap.Map("allmap");
		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		var pCode=$("#ResideProvince").val();
		var cCode=$("#cities").val();
		var aCode=$("#dics").val();
		var address=$("#address").val();
		var pName;
		var cName;
		var aName;
		var fullAddr;
		$.post("../address/fullName",{"pCode":pCode,"cCode":cCode,"aCode":aCode},function(obj){
			if(obj.success){
				fullAddr=obj.pName+" "+obj.cName+" "+obj.aName+" "+address;
				myGeo.getPoint(fullAddr, function(point){
					if (point) {
						map.centerAndZoom(point, 16);
						map.addOverlay(new BMap.Marker(point));
			          $("#xCoord").val(point.lng);//经度
			          $("#yCoord").val(point.lat);//纬度
					}else{
						$.sk.error("您选择地址没有解析到结果!");
					}
				}, obj.pName);
				
				//local.search(fullAddr);
			}
			
		},"json");
	})
});

var map = new BMap.Map("allmap");          
map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
var local = new BMap.LocalSearch(map, {
	renderOptions:{map: map}
});