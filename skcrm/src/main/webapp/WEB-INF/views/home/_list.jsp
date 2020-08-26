
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="/sankai-fun" prefix="extf"%>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="">
			<div class="site_change "
				style="height: 33px; margin-bottom: 20px; margin-top: 10px; border-bottom: solid 1px #ccc; ">
				<span class="colorshenblue font16"
					style="border-bottom: 2px #49b79d solid; padding: 0px 5px 10px 5px;">提醒记录</span>
			</div>
			
		</div>
	</div>
 
				 	<div  style="margin-top: 10px;">
						<!--<ul class="site_tab_ul">
							<li class="on"><a class="color333" href="javascript:void(0)">提醒客户记录</a></li>
							 <li><a class="color333" href="javascript:void(0)">单次待提醒客户记录</a></li>
							<li><a class="color333" href="javascript:void(0)">长期待提醒客户记录</a></li>
						</ul> -->
						<div class="echart_page bggr">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th width="17%">客户简称</th>
										<th width="8%">客户状态</th>
										<th >提醒内容</th>
										<th width="10%">负责人</th>
										<th width="12%">提醒时间</th>
										<th width="8%">超时天数</th>
										<th width="180px">操作</th>
									</tr>
								</thead>

								<tbody>
								<c:if test="${pager.total == 0 }">
								<tr>
									<td colspan="7" style="text-align: center">暂无数据</td>
								</tr>
								</c:if>
								<c:forEach var="item" items="${model }">
									<tr ondblclick="showDetail('${item.customerId }')">
										<td title="${item.shortName }">${extf:subStr(item.shortName,10) }</td>
									    <td>${item.statusName }</td>
										<td title="${item.remark }">${extf:subStr(item.remark,2000) }</td>
										<td>${item.accountName }</td>
										<td><fmt:formatDate value="${item.remindTime }" pattern="yyyy-MM-dd" /></td>
										<td class="colorred">
										<c:choose>
										<c:when test="${item.overTime == 0}">提醒中</c:when>
										<c:when test="${item.overTime < 0}">超时[${-item.overTime }]天</c:when>
										<c:when test="${item.overTime > 0}">剩余[${item.overTime }]天</c:when>										
										</c:choose>
										</td>
										<td><span class="colorblue edit" > <a href="javascript:void(0);" class="colorblue" onclick="showDetail('${item.customerId }')">跟踪</a></span>
												
												<span class="colorblue edit"> <a data-id="customer_edit" data-edit-id="${item.id }"
							data-title="${item.shortName }" class="colorblue" title="延后">延后</a></span>
												
												 <span class="colorred delete" data-delete-id="${item.id }"
							data-title="${item.shortName }" title="取消">取消</span></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
					 </div> 

<script type="text/javascript">
$(function(){
	

	// 延后处理
	$("a[data-id='customer_edit']").on("click", function() {

		var $this = $(this);
		dialog = $.sk.open({
			url : "../customer/saveRemind",
			data : {
				id : $this.data("edit-id")
			},
			width : 560,
			height : 340,
			title : "延后处理",
			buttons : [ {
				html : "确定",
				"class" : "btn btn-minier btn-success delay",
				click : function() {
					$("#myformSaveRemind").submit();
				}
			} ]
		});
	});
	
	//删除
	$("span[data-delete-id]").click(function() {
		var $this = $(this);
		$.sk.confirm("您确定要取消<em>" + $this.data("title") + "</em>提醒吗？", function(result) {
			if (result) {
				$.post("delete.ajax", {
					id : $this.data("delete-id")
				}, function(data) {
					if (data.success) {
						$.sk.success("取消提醒成功", function() {
							$("#myformcontent").submit();
						});
					} else
						$.sk.error(data.msg);
				}, "json");
			}
		});
	});
	
})
</script>