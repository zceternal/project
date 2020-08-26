<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>联系人管理</title>

<link rel="stylesheet"
	href="content/vendor/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="content/css/common.css" />
<link rel="stylesheet" href="content/css/welcome.css" />
<title></title>
</head>
<body style="background: #FFFFFF;">
	<!--遮罩层-->
	<div class="zhy_zhezhao_pop " style="height: 800px;"></div>
	<!--遮罩层 end-->
	<div class="zhy_popa deletepop ">
		<p class="color66">提示</p>
		<div class="pop_content">
			<p>
				您确定要删除<font>林伟强</font>吗？
			</p>
			<div class="operate_btn pull-right">
				<button type="button" class="btn cancle_yes">确定</button>
				<button type="button" class="btn cancle_btn" onclick="hideEdit()">取消</button>
			</div>
		</div>
	</div>
	<div class="main_content member_list">
		<div class="col-xs-12 col-sm-12 div_bordered">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<div class="site_change">


						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="" class="col-sm-2 control-label">负责人：</label>
								<div class="col-sm-10 favorite">
									<div>
										<label class="checkbox-inline"> <input type="checkbox"
											id="" value=""> 自己
										</label> <label class="checkbox-inline"> <input
											type="checkbox" id="" value=""> 张三
										</label> <label class="checkbox-inline"> <input
											type="checkbox" id="" value=""> 李四
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-2 control-label">联系人角色：</label>
								<div class="col-sm-10 favorite">
									<div>
										<label class="checkbox-inline "> <input type="radio"
											checked="checked"> 普通人
										</label> <label class="checkbox-inline"> <input type="radio">
											决策人
										</label> <label class="checkbox-inline"> <input type="radio">
											信息决策人
										</label> <label class="checkbox-inline"> <input type="radio">
											技术决策人
										</label> <label class="checkbox-inline"> <input type="radio">
											使用人
										</label> <label class="checkbox-inline"> <input type="radio">
											意见影响人
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-2 control-label">联系人详情：</label>
								<div class="col-sm-10 favorite">
									<div>
										<label class="checkbox-inline"> <input type="checkbox"
											id="" value=""> 手机
										</label> <label class="checkbox-inline"> <input
											type="checkbox" id="" value=""> 微信
										</label> <label class="checkbox-inline"> <input
											type="checkbox" id="" value=""> QQ
										</label> <label class="checkbox-inline"> <input
											type="checkbox" id="" value=""> 邮件
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-2 control-label">联系关联客户：</label>
								<div class="col-sm-10 favorite">
									<div>
										<label class="checkbox-inline "> <input type="radio"
											checked="checked"> 已关联客户
										</label> <label class="checkbox-inline"> <input type="radio">
											未关联客户
										</label>
									</div>
								</div>
							</div>
						</form>
					</div>
					<form class="form-inline pl20" role="form">
						<div class="form-group">
							<div class="search">
								<input type="text" placeholder="请输入需要查询的会联系人姓名" />
							</div>
						</div>
						<div class="form-group">
							<button type="button" class="btn btn_white20">查找</button>
						</div>
						<div class="pull-right text-right mr5">
							<a class="btn_blueg2 " href="#.html">发邮件</a>
						</div>
						<div class="pull-right text-right mr5">
							<a class="btn_blueg2 " href="#.html">共享</a>
						</div>
						<div class="pull-right text-right mr5">
							<a class="btn_blueg2 " href="#.html">+新建联系人</a>
						</div>
					</form>

				</div>
				<table class="table table-bordered  table-hover">
					<thead>
						<tr>
							<th width="5%"><input type="checkbox" id="" value=""></th>
							<th width="5%">置顶</th>
							<th width="5%">姓名</th>
							<th width="11%">手机</th>
							<th width="9%">QQ</th>
							<th width="11%">邮件</th>
							<th width="8%">职务</th>
							<th width="8%">所属客户</th>
							<th width="8%">创建人</th>
							<th width="10%">创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox" id="" value=""></td>
							<td>↑</td>
							<td>林伟强</td>
							<td>13801023456</td>
							<td>382920001</td>
							<td>linweiqiang@guangdong.com</td>
							<td>内控总监</td>
							<td>泰成逸园</td>
							<td>张三</td>
							<td>2015/12/10  19:01:00</td>
							<td><span class="colorblue">查看</span> <span><a
									href="#.html" class="colorblue">修改</a></span> <span><a
									class="colorred" onclick="deleteM(this,'deletepop')">删除</a></span></td>
						</tr>
						<tr>
							<td><input type="checkbox" id="" value=""></td>
							<td>↑</td>
							<td>林伟叶</td>
							<td>13801023456</td>
							<td>382920001</td>
							<td>linweye@guangdong.com</td>
							<td>内控总监</td>
							<td>泰成逸园</td>
							<td>张三</td>
							<td>2015/12/10  19:01:00</td>
							<td><span class="colorblue">查看</span> <span><a
									href="#.html" class="colorblue">修改</a></span> <span><a
									class="colorred" onclick="deleteM(this,'deletepop')">删除</a></span></td>
						</tr>

					</tbody>
				</table>
			</div>
			<div class="row text-center">
				<div id="Pagination" class="pagination">
					<a class="prev " href="#">< 上一页</a> <a class="index" href="">|<</a>
					<a href="#">1</a> <a href="#">2</a> <span class="current">3</span>
					<a href="#">4</a> <span>...</span> <a href="#">8</a> <a
						class="index" href="">>|</a> <a class="next" href="#">下一页 > </a>
				</div>
			</div>

		</div>
	</div>

	<script type="text/javascript" src="content/vendor/jquery/jquery-2.1.1.min.js"></script>
	<script src="content/js/common.js"></script>
	
	<script type="text/javascript">
		//显示弹框
		function deleteM(obj, className, id) {
			$(".zhy_zhezhao_pop").show();
			var top = $('html', parent.document).scrollTop(); //兼容scrolltop写法
			var win_h = $("body", parent.document).height();
			$(".zhy_zhezhao_pop").height($("html").height());
			var this_h = $("." + className).height();
			var cha = win_h - this_h - 160;
			if (cha > 0) {
				top += cha / 2;
			} else {
				top += 0;
			}
			$("." + className).css("top", top).show();
		}
		// 隐藏修改弹框
		function hideEdit() {
			$(".zhy_zhezhao_pop").hide();
			$(".zhy_popa").hide();

		}
	</script>
</body>
</html>