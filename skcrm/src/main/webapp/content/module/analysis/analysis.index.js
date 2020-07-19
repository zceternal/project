var $container = $(".ztree");
$container.height($(document).height() - 150);
var setting = {
	async : {
		enable : true,
		url : $container.data("url")
	},
	view : {
		dblClickExpand : true,
		showLine : true,
		selectedMulti : true,
		
	},
	check:{
		enable:true,
		
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",// 设置子节点
			pIdKey : "pid",// 设置父节点
		}
	},
	callback : {
		onNodeCreated : function(event, treeId, treeNode) {
			if(treeNode.id > 100000){
				
				treeNode.icon = "../content/ztree-3.5/zTreeStyle/img/diy/user.png";
				zTree.updateNode(treeNode);
				return true;
				
			}else{
				treeNode.icon = "../content/ztree-3.5/zTreeStyle/img/diy/dept.png";
				zTree.updateNode(treeNode);
				return true;
			}

		},
		onCheck : function (event, treeId, treeNode) { //选中事件
			//zTree.checkNode(treeNode, !treeNode.checked, true);
			var nodes = zTree.getCheckedNodes(true);
			var accountIdsIndex='';
			var accountNamesIndex='';
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].id>100000){
					
					accountIdsIndex+=(nodes[i].id / 100000) +',';
					accountNamesIndex+=nodes[i].name +',';
					
				}
			}
			var accountIds=accountIdsIndex.substring(0,accountIdsIndex.length-1);
			var accountName=accountNamesIndex.substring(0,accountNamesIndex.length-1);
			$("#accountIds").val(accountIds);
			$("#title").val(accountName);
			treeOnclick();//漏斗图
			treeOnCkickZ();//折线图
			return true;
		},
		onAsyncSuccess: function(event, treeId, treeNode, msg){//异步加载完之后
			var node = zTree.getNodes()[0];
			zTree.expandNode(node);//默认展开节点
			//zTree.checkNode(node,true,true);//默认选中节点
			var nodes = zTree.getCheckedNodes(true);
			var accountIdsIndex='';
			var accountNamesIndex='';
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].id>100000){
					
					accountIdsIndex+=(nodes[i].id / 100000) +',';
					accountNamesIndex+=nodes[i].name +',';
					
				}
			}
			var accountIds=accountIdsIndex.substring(0,accountIdsIndex.length-1);
			var accountName=accountNamesIndex.substring(0,accountNamesIndex.length-1);
			$("#accountIds").val(accountIds);
			$("#title").val(accountName);
			treeOnclick();//漏斗图
			treeOnCkickZ();//折线图
		}
	}
};
zTree = $.fn.zTree.init($container, setting);


function treeOnCkickZ(){
	
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var accountIds=$("#accountIds").val();	
	var title=$("#title").val();	
	
	$.post("../analysis/indexCount",{startTime:startTime,endTime:endTime,accountIds:accountIds},function(dataZ){
		dataZ.title=title;
		var myChart2 = echarts.init(document.getElementById('echart2'));

		var legendDataZ = [];
		for (var i = 0; i < dataZ.data.length; i++) {
			legendDataZ.push(dataZ.data[i].name);
		}

		var option2 = {
			title : {
				text : '',
				subtext : dataZ.title
			},
			tooltip : {
				trigger : 'axis',
				formatter : "{c}"
			},
			legend : {
				data : [ '客户总数' ],
				x : '50',
				itemHeight : '15'
			},
			toolbox : {
				show : false
			},
			calculable : false,
			color : [ '#20b0d5', '#cb4987' ],
			xAxis : [ {
				type : 'category',
				boundaryGap : false,
				axisTick : {
					show : true,
					inside : true
				},
				splitLine : {
					lineStyle : {
						color : '#e0e0e0',
						width : 1
					}
				},
				axisLine : {
					lineStyle : {
						color : '#e0e0e0',
						width : 1
					}
				},
				data : legendDataZ
			} ],
			yAxis : [ {
				type : 'value',
				axisLabel : {	
					formatter : '{value}'
				},
				boundaryGap : [ 0, 0 ],
				min : 0,
				splitNumber : 5,
				splitLine : {
					lineStyle : {
						color : '#e0e0e0',
					}
				},
				axisLine : {
					lineStyle : {
						color : '#e0e0e0',
						width : 1
					}
				}
			} ],
			series : [ {
				name : '客户总数',
					type : 'line',
				symbol : "circle",
				symbolSize : 4,
				data : dataZ.data
			}

			]
		};
		myChart2.setOption(option2);
		
	},"json")
	
	
}


function treeOnclick(){
		
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var accountIds=$("#accountIds").val();	
		var title=$("#title").val();	

		$.post("../analysis/index",{startTime:startTime,endTime:endTime,accountIds:accountIds},function(data){
			data.title=title;//重新命名title

			var myChart1 = echarts.init(document.getElementById('echart1'));
			var legendData = [];
			var totalNum = 0;
			for (var i = 0; i < data.data.length; i++) {
				legendData.push(data.data[i].name);
				totalNum+=data.data[i].value;
			}
			var option1 = {
				title : {
					text : '销售漏斗图 (客户总量:'+totalNum+')',
					subtext : data.title
				},
				tooltip : {
					trigger : 'item',
					//formatter : "{b} : {c}"
					formatter : "{b}"
				},
				toolbox : {
					feature : {
						dataView : {
							readOnly : false
						},
						restore : {},
						saveAsImage : {}
					}
				},
				legend : {
					data : legendData
				},
				calculable : true,
				series : [ {
					name : '漏斗图',
					type : 'funnel',
					left : '10%',
					top : 60,
					// x2: 80,
					bottom : 60,
					width : '80%',
					// height: {totalHeight} - y - y2,
					min : 0,
					max : 100,
					minSize : '0%',
					maxSize : '100%',
					sort : 'descending',
					gap : 2,
					label : {
						normal : {
							show : true,
							position : 'inside'
						},
						emphasis : {
							textStyle : {
								fontSize : 20
							}
						}
					},
					labelLine : {
						normal : {
							length : 10,
							lineStyle : {
								width : 1,
								type : 'solid'
							}
						}
					},
					itemStyle : {
						normal : {
							borderColor : '#fff',
							borderWidth : 1
						}
					},
					data : data.data
				} ]
			};
			myChart1.setOption(option1);
			
		},"json");
}
//JavaScript Document
$(function(){

       $("[input-type='date']").each(function(i,El){
         var picker = new Pikaday(
          {
                field: El,
                firstDay: 1,
                minDate: new Date('1900-01-01'),
                maxDate: new Date(),
                format: 'YYYY-MM-DD',
                onSelect: function(date) {

                    treeOnclick();//漏斗图
                    treeOnCkickZ();//折线图
                    
                },
                yearRange: [1900,2330]
          });
       });
       $(".date_icon").css("cursor","pointer").click(function(){
         $(this).siblings(":text").click();
      })
})

