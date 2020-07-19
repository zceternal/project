<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="main_content" style="overflow-y: auto">
	<div class="col-xs-12 col-sm-12 div_bordered">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<form method="post" action="cuslist" class="form-inline pl20"
					role="form" id="customerListForm" data-ajax="true"
					data-ajax-mode="replace" data-ajax-update="#customerDv">
					<input id="pageCus" name="page" type="hidden"
						value="${pager.pageNum }" />
					<div class="form-group">
						<div class="search fl" style="width: 200px">
							<input type="text" name="content" placeholder="输入客户简称"
								style="width: 200px" />
						</div>

					</div>
					<div class="form-group">
						<button type="submit" class="btn btn_white20 dev-search-cuspage">查找</button>
					</div>
				</form>
			</div>
			<div id="customerDv">
				<jsp:include page="customer_list.jsp"></jsp:include>

			</div>
		</div>

	</div>
</div>

<script type="text/javascript">
	$(function() {
		//点击搜索 分页回到第一页
		$(".dev-search-cuspage").live("click", function() {
			$("pageCus").val("1");
		});
	});
</script>