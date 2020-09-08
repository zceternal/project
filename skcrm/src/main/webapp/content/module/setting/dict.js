var dialog;
var pid;
var iDate;
$(function(){
	$("a[data-id=dict_add]").live("click",function(){

		var $this=$(this);
		pid=$this.data("pid");
		dialog=$.sk.open({
			url:"../setting/add",
			data:{parentId:pid},
			width:600,
			title:"新增字典",
			buttons:[
			        {
			        	html:"确定",
			        	"class": "btn btn-minier btn-success delay",
			        	click: function () {
			        		$("#pid").val(pid);
			        		$("#dictAddForm").submit();
			        		
			        	}
			        }
			        ]
		});
	});
	
	$(".myDictList").delegate("a[data-action]","click",function(){
		var theUrl=$(this).data("url");
		var id=$(this).data("id");
		pid=$(this).data("pid");
		if($(this).data("action")=='update'){
			dialog=$.sk.open({
				url:theUrl,
				data:{id:id},
				width:600,
				title:"修改",
				buttons:[
				        {
				        	html:"确定",
				        	"class": "btn btn-minier btn-success delay",
				        	click: function () {
				        		$("#pid").val(pid);
				        		$("#dictUpdateForm").submit();
				        	}
				        }
				        ]
			});
		}else if($(this).data("action")=='delete'){
			var id=$(this).data("id");
			var pid=$(this).data("pid");
			var order=$(this).data("order");
			$.sk.confirm("您确定要删除名称为【"+$(this).data("title")+"】的数据吗？",function(result){
				
				
				if(result){
					$.post("../setting/deleteDict",{"id":id,"parentId":pid,"order":order},function(obj){
						if(obj.success){
							$("#tr_"+id+"_"+pid).remove();
						}
					},"json");
				}
				
			})
		}else if($(this).data("action")=="move"){
			var id=$(this).data("id");
			var pid=$(this).data("pid");//父级id
			var ppid=$(this).data("ppid");//主要用于查询
			var iOrder=$(this).data("order");
			var moveid=$(this).data("moveid");
			var move=$(this).data("move");
			var thisIndex=$("#table_"+pid+" tr").index($(this).parent().parent().parent());
			var lastIndex=$("#table_"+pid+" tr:last").index()+1;
			var firstIndex=$("#table_"+pid+" tr:first").index()+1;
			
			if(thisIndex==firstIndex&&move==-1){
				$.sk.error("已是最上层");
				return;
			}
			if(thisIndex==lastIndex&&move==1){
				$.sk.error("已是最底层");
				return;
			}
			
			$.post("../setting/move",{"id":id,"pid":pid,"iOrder":iOrder,"move":move},function(obj){
				if(obj.success){
					$("#dictForm_"+ppid).submit();
//					if(move==1){
//						$(this).removeAttr("data-order");
//						$(this).attr("data-order",iOrder+1);
//						$("#tr_"+id+"_"+pid).insertAfter($("#tr_"+id+"_"+pid).next("tr"))
//					}
//					if(move==-1){
//						$("#tr_"+id+"_"+pid).insertBefore($("#tr_"+id+"_"+pid).prev("tr"))
//					}
					
				}else{
					$.sk.error(obj.msg);
				}
			});
			
		}
		
	});
});

function onDictAddSuccess(data){
	if(data.success){
		 $.sk.close(dialog);
		 $.sk.success(data.msg);
		 
		 $("#dictForm_"+pid).submit();
//		$("#table_"+data.data.parentId).append(
//				"<tr id='tr_'"+data.data.id+"_"+data.data.parentId+"'>" +
//					"<td><span>" +
//						"<a data-action='move' data-id='"+data.data.id+"' data-pid='"+data.data.parentId+"' data-moveid='"+data.data.id+"_"+data.data.parentId+"' data-order='"+data.data.order+"' data-move=-1 class='colorblue edit' href='javascript:void(0)' >" +
//						"上移</a></span>" +
//						"<span><a data-action='move' data-id='"+data.data.id+"' data-pid='"+data.data.parentId+"' data-moveid='"+data.data.id+"_"+data.data.parentId+"' data-order='"+data.data.order+"' data-move=1 class='colorblue edit' href='javascript:void(0)' >" +
//						"下移</a></span></td>" +
//					"<td id='"+data.data.id+"_"+data.data.parentId+"_name'>"+data.data.name+"</td>" +
//					"<td id='"+data.data.id+"_"+data.data.parentId+"_value'>"+data.data.value+"</td>" +
//					"<td title='"+data.data.remark+"' id='"+data.data.id+"_"+data.data.parentId+"_remark'>"+data.data.remark.substring(0,31)+"</td>" +
//					"<td>"+data.data.createName+"</td>" +
//					"<td>"+data.data.createTimeStr+"</td>" +
//					"<td><span><a data-action='update' data-id='"+data.data.id+"' data-pid='"+data.data.parentId+"' data-url='../setting/updateDict' class='colorblue edit' href='javascript:void(0)'>编辑</a></span>" +
//					"<span><a data-action='delete' data-id='"+data.data.id+"' data-order='"+data.data.order+"' data-pid='"+data.data.parentId+"' data-title='"+data.data.name+"'  class='colorred delete' href='javascript:void(0)'>删除</a></span></td>" +
//				"<tr>"
//		
//		);
		
		
		
	}else{
		$.sk.error(data.msg);
	}
}

function onDictUpdateSuccess(data){
	if(data.success){
		 $.sk.close(dialog);
		 $("#"+data.data.id+"_"+data.data.parentId+"_name").html(data.data.name);
		 $("#"+data.data.id+"_"+data.data.parentId+"_value").html(data.data.value);
		 $("#"+data.data.id+"_"+data.data.parentId+"_remark").html(data.data.remark);
		 $.sk.success(data.msg);
	}else{
		$.sk.error(data.msg);
	}
}


