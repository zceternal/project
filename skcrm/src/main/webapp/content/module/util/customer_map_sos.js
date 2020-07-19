var map = new BMap.Map("allmap");
var geoc = new BMap.Geocoder();
var point;
var Longitude = $("#xCoord").val();
var Latitude = $("#yCoord").val();

//根据地区定位地图坐标点
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


if (Longitude == "" || Longitude == null) {
    var City = "北京市";
    map.centerAndZoom(City, 16);
} else {
    point = new BMap.Point(Longitude,Latitude);//, 
    map.centerAndZoom(point, 16);
    map.clearOverlays();
    var marker = new BMap.Marker(point);  // 创建标注
    map.addOverlay(marker);              // 将标注添加到地图中
    map.panTo(point);
    map.centerAndZoom(point, 16);
    
    //创建信息窗口

    geoc.getLocation(point, function (rs) {
        var addComp = rs.addressComponents;
        var mess = addComp.district + addComp.street + addComp.streetNumber;
        var infoWindow = new BMap.InfoWindow(mess, { enableMessage: false });
        var marker = new BMap.Marker(point);
        marker.addEventListener("mouseover", function () { this.openInfoWindow(infoWindow); });
        marker.addEventListener("mouseout", function () { this.closeInfoWindow(infoWindow); });
        map.addOverlay(marker);
    });
}

//创建缩放控件
map.addControl(new BMap.NavigationControl());
map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用


var overlays = [];
//回调获得覆盖物信息
var overlaycomplete = function (e) {
    overlays.push(e.overlay);
    if (e.drawingMode == BMAP_DRAWING_CIRCLE) {
        $("#CircleRadii").val(e.overlay.getRadius());
        $("#CircleOriginX").val(e.overlay.getCenter().lng);
        $("#CircleOriginY").val(e.overlay.getCenter().lat);
    }
    drawingManager.close();
};

var styleOptions = {
    strokeColor: "red",    //边线颜色。
    fillColor: "red",      //填充颜色。当参数为空时，圆形将没有填充效果。
    strokeWeight: 3,       //边线的宽度，以像素为单位。
    strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
    fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
    strokeStyle: 'solid' //边线的样式，solid或dashed。
}

var zoomIndex = 0;
// 定义一个控件类,即function
function ZoomControl() {
    // 默认停靠位置和偏移量
    this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
    this.defaultOffset = new BMap.Size(10, 10);
}

// 通过JavaScript的prototype属性继承于BMap.Control
ZoomControl.prototype = new BMap.Control();

// 创建控件
var myZoomCtrl = new ZoomControl();
// 添加到地图当中
map.addControl(myZoomCtrl);


