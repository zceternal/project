<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-xs-12 col-sm-12 div_bordered">
	<div class="panel panel-default">
		<div class="panel-heading">
			<form method="post" action="mynotice" class="form-inline" id="myform"
				data-ajax="true" data-ajax-mode="replace"
				data-ajax-update="#mynotice_update_result">
				<input id="page" name="page" type="hidden" value="${pager.pageNum }" />
				<input id="pageSize" name="pageSize" type="hidden"
					value="${pager.pageSize }" />
				<div class="form-group">
					<div class="search fl" style="width: 400px">
						<input type="text" name="content"
							placeholder="请输入标题关键字" style="width: 400px" />
					</div>
				</div>


				<div class="form-group">
					<button type="submit" class="btn btn_white20">查找</button>
				</div>
			
			</form>
		</div>
		<div id="mynotice_update_result">

			<jsp:include page="_mynotice_list.jsp" />

		</div>

	</div>