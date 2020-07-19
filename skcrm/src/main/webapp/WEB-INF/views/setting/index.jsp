<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />
<link rel="stylesheet" href="../content/css/welcome.css" />
<link rel="stylesheet" href="../content/css/jquery.pager.css" />
<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
<link rel="stylesheet" href="../content/css/jquery.ptTimeSelect.css" />
<link rel="stylesheet" href="../content/css/glyphicon.css" />
<link rel="stylesheet" href="../content/css/validationEngine.jquery.css" />
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>

<title></title>
</head>
<body>
	<div class="main_content">
		<div class="col-xs-12 col-sm-12 ">
			<div class="site_toggle_title">
				<div class="nav_head ">
					<ul id="">
						<li class="on">系统参数</li>
						<li class="">数据字典配置</li>
					</ul>
				</div>
				<div style="height: 20px; background: #edf0f2;"></div>
			</div>
			<div class="echart_page bggr">
				<div class="row">
					<div class="col-sm-12 text-center">
						<table class="table table-striped ">
							<thead>
								<tr>
									<th width="80px">#</th>
									<th width="150px">参数类别</th>
									<th width="200px">参数名称</th>
									<th>参数值</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" varStatus="stat" items="${resultList}">
									<tr>
										<td>${stat.index+1}</td>
										<td style="text-align: left;">${item.type}</td>
										<td style="text-align: left;">${item.name}</td>
										<td style="text-align: left;">${item.value}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

				</div>
			</div>


			<div class="list_page" style="display: none;">
				<div class="well" style="border:none">
					<ul class="site_tab_ul">
						<c:forEach items="${dictParentList }" var="pDict" varStatus="status">
							<li ${status.index==0?"class='on'":"" }><a  ${status.index==0?"class='color333'":"" } href="javascript:void(0)"  >${pDict.name }</a></li>
						</c:forEach>
						
					</ul>
					<div class="row" id="site_tab_tab">
						<c:forEach items="${allDictsMap }" var="dictsMap" varStatus="statusx">
									<div class="col-xs-12 col-sm-12 div_bordered" ${statusx.index!=0?"style='display: none'":"" }>
										<div class="panel panel-default">
											<div class="panel-heading">
												<form method="post" action="../setting/searchDict?pid=${dictsMap.pid }" class="form-inline"  data-ajax="true"  data-ajax-mode="replace" 
													 id="dictForm_${dictsMap.pid }" data-ajax-update="#dictList_${dictsMap.pid }" >
													<div class="form-group">
														<div class="search fl" style="width: 200px">
															<input type="text" name="keyWord" value="${param.content }"
																placeholder="请输入名称/简拼/全拼" style="width: 200px" />
			
														</div>
			
													</div>
													<div class="form-group">
														<button type="submit" class="btn btn_white20">查找</button>
													</div>
													<div class="pull-right text-right mr15">
													<a class="btn_blueg2 font14" href="javascript:void(0)" data-pid="${dictsMap.pid }"
														data-id="dict_add" >+新增 </a>
												</div>
												</form>
												
											</div>
											
										<div class="myDictList" id="dictList_${dictsMap.pid }">
											<table class="table table-bordered table-hover" id="table_${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }">
												<thead>
													<tr>
														<th width="10%">排序</th>
														<th width="10%">名称</th>
														<th width="10%">数值</th>
														<th>备注</th>
														<th width="8%">创建人</th>
														<th width="200px">创建时间</th>
														<th width="200px">操作</th>
													</tr>
												</thead>
												
												<tbody>
													<c:forEach items="${dictsMap.dictList }" var="dict">
														<tr id="tr_${dict.id }_${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }">
															<td>
																<span>
																<a data-action="move" data-id="${dict.id }" data-pid="${dict.parentId }" data-moveid="${dict.id }_${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }"
																 	data-order="${dict.order }" data-move=-1 data-url="../setting/move" class="colorblue edit" href="javascript:void(0)">上移</a>
																</span>
																<span>
																<a data-action="move" data-id="${dict.id }" data-pid="${dict.parentId }" 
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
																<span>
																<a data-action="update" data-id="${dict.id }" data-pid="${dict.parentId }" data-url="../setting/updateDict" class="colorblue edit" href="javascript:void(0)">编辑</a></span>
																<span>
																 <a data-action="delete" class="colorred delete"  data-id="${dict.id }" data-pid="${dictsMap.pid==null||dictsMap.pid==''?pid:dictsMap.pid }" data-title="${dict.name }" 
																 	data-order="${dict.order }"
																 >删除</a></span>
															
															</td>
														</tr>
													</c:forEach>
												</tbody>
										
											</table>
										</div>
									</div>
								</div>
						</c:forEach>
						
						
					</div>
				</div>

			</div>
		</div>
	</div>
	


	<script>
		//切换页面
		$(".nav_head li").click(function() {
			var eq = $(this).index();
			$(this).addClass("on").siblings().removeClass("on");
			if (eq == 0) {
				$(".echart_page").show().siblings(".list_page").hide();

			} else {
				$(".list_page").show().siblings(".echart_page").hide();

			}
		})
		
		$(".site_tab_ul li").click(function(){
			$(this).addClass("on").siblings().removeClass("on");
			$("#site_tab_tab>div").eq($(this).index()).show().siblings().hide();
			
		});
	</script>
	
<script type="text/javascript"
		src="../content/js/jquery.unobtrusive-ajax.min.js"></script>
		 <script type="text/javascript" src="../content/module/setting/dict.js"></script>
		 
		 	<!--表单验证 s-->
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.validationEngine.js"></script>
	<!--表单验证 e-->
		 
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
	<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>
	<script type="text/javascript"
		src="../content/js/jquery.JPlaceholder.js"></script>

</body>
</html>
