<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="col-xs-12 col-sm-12 div_bordered">
	<div class="panel panel-default">
		<div class="panel-heading">
			<form method="post" action="manage" class="form-inline" id="myform"
				data-ajax="true" data-ajax-mode="replace"
				data-ajax-update="#message_update_result">
				<input id="page" name="page" type="hidden" value="${pager.pageNum }" value="1"/>
				<input id="pageSize" name="pageSize" type="hidden"
					value="${pager.pageSize }" /> <input type="hidden" name="state" value="0"
					id="stateTag">
				<div class="form-group form-inline">
					<ul id="stateTab" class="tab_button search_sk"
						style="margin-bottom: 0px; margin-top: 3px">
						<li class="dev-state on" data-value="0">未读</li>
						<li class="dev-state" data-value="1">已读</li>
					</ul>
				</div>


				<div class="form-group">
					<div class="search fl" style="width: 400px">
						<input type="text" name="content"
							placeholder="请输入标题关键字" style="width: 400px" />
					</div>
				</div>


				<div class="form-group">
					<button type="submit" class="btn btn_white20 dev-search">查找</button>
				</div>
				<%-- <shiro:hasPermission name="message_send">	 --%>
				<div class="pull-right text-right mr15">
					<a class="btn_blueg2 font14" href="javascript:;" onclick="removeAllId();">批量删除
					</a>
				</div>
				<div class="pull-right text-right mr15">
					<a class="btn_blueg2 font14" href="send" data-id="dict_add">+发送消息
					</a>
				</div>
				<%-- </shiro:hasPermission> --%>
			</form>

		</div>
		<div id="message_update_result">

			<jsp:include page="_message_list.jsp" />

		</div>

	</div>
	<script type="text/javascript">
	//点击搜索按钮，分页默认显示为第一页
/* 	$(".dev-search").click(function(){
		$("#page").val(1);
	}); */
		$(".dev-state").click(function() {
			var $this = $(this);
			$("#stateTag").val($this.data("value"));
			$this.addClass("on").siblings().removeClass("on");
			$("#myform").submit();
		});
	
	function removeAllId(){
		var ids="";
		$(".checkbox").each(function(){
			if(this.checked){
				if($(this).val()){
					if(ids==''){
						ids=$(this).val();
					}else{
						ids+=','+$(this).val();
					}
				}
			}
		});
		if(ids!=''){
			$.ajax({
				url:'../message/remove.ajax',
				type:'post',
				data:{"id":ids},
				dataType:'json',
				success:function(data){
					if(data.error==0){
						$.sk.error("删除成功！");
						setTimeout(function(){
							window.location.href='../message/index.html';
						},3000);
					}else{
						$.sk.error(data.msg);
					}
				}
			});
		}
	}
	</script>