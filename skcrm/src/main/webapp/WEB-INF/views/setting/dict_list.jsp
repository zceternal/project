<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<table class="table table-bordered table-hover" id="table_${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }">
		<thead>
			<tr>
				<th width="10%">排序</th>
				<th width="10%">名称</th>
				<th width="10%">数值</th>
				<th>备注</th>
				<th width="8%">创建人</th>
				<th width="8%">创建时间</th>
				<th width="8%">操作</th>
			</tr>
		</thead>
		
		<tbody>
			<c:if test="${allDicts.size()==0 }">
				<tr>
					<td colspan="6">暂无数据</td>
				</tr>
			</c:if>
			<c:forEach items="${allDicts }" var="dict">
			
				
				<tr id="tr_${dict.id }_${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }">
					<td>
						<span>
						<a data-action="move" data-id="${dict.id }" data-pid="${dict.parentId }" data-ppid="${dict.ppid}" data-moveid="${dict.id }_${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }"
						 	data-order="${dict.order }" data-move=-1 data-url="../setting/move" class="colorblue edit" href="javascript:void(0)">上移</a>
						</span>
						<span>
						<a data-action="move" data-id="${dict.id }" data-pid="${dict.parentId }"  data-ppid="${dict.ppid}"
						 	data-order="${dict.order }" data-move=1 data-url="../setting/move" class="colorblue edit" href="javascript:void(0)">下移</a>
						</span>
						
					</td>
					<td id="${dict.id }_${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }_name">${dict.name }</td>
					<td id="${dict.id }_${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }_value">${dict.value }</td>
					<td title="${dict.remark }" id="${dict.id }_${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }_remark"> ${fn:substring(dict.remark ,0,31)}${fn:length(dict.remark )>=31?" ...  ":"" }</td>
					<td>${dict.createName }</td>
					<td>
						<fmt:formatDate value="${dict.createTime }" pattern="yyyy-MM-dd" />
					
					</td>
					<td>
					<a data-action="update" data-id="${dict.id }" data-pid="${dict.parentId }" data-url="../setting/updateDict" class="btn btn-link" href="#">修改</a>
					<a data-action="delete" class="colorred delete"  data-id="${dict.id }" data-pid="${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }" data-title="${dict.name }" data-order="${dict.order }">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
								
	
