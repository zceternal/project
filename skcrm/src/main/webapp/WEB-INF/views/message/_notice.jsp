<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-xs-12 col-sm-12 div_bordered">
	<div class="panel panel-default">
		<div class="panel-heading">
			<form method="post" action="notice" class="form-inline" id="myform"
				data-ajax="true" data-ajax-mode="replace"
				data-ajax-update="#notice_update_result">
				<input id="page" name="page" type="hidden" value="${pager.pageNum }" />
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
					<button type="submit" class="btn btn_white20">查找</button>
				</div>
				<div class="pull-right text-right mr15">
					<a class="btn_blueg2 font14" href="sendNotice.html" data-id="dict_add">+发送公告
					</a>

				</div>
			</form>

		</div>
		<div id="notice_update_result">

			<jsp:include page="_notice_list.jsp" />

		</div>

	</div>
	<script type="text/javascript">
		$(".dev-state").click(function() {
			var $this = $(this);
			$("#stateTag").val($this.data("value"));
			$this.addClass("on").siblings().removeClass("on");
			$("#myform").submit();
		});
	</script>