<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/sankai-ext" prefix="elf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.dev-col-sm-120 {width:134px !important;}
.col-sm-4,.col-sm-10,.wh_ul{padding-left:0px}
</style>
	<div class="main_content dialog">
			<div class="panel panel-default">
			<%--	<form class="form-horizontal old_block" role="form" id="myformBack"
					action="back" method="post" data-ajax="true" data-ajax-success="onCustomerSuccessAdd" enctype="multipart/form-data">--%>
                <form class="form-horizontal old_block" id="myformBack" action="back" method="post" enctype="multipart/form-data"
					  role="form" data-ajax="true" data-ajax-success="onTaskBackSuccessAdd">
					<input id="taskId" name="taskId" type="hidden" value="${task.id }" />
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">任务名称：</label>
						<div class="col-sm-10">
							${task.name}
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label " >任务性质：</label>
						<div class="col-sm-4">
							${elf:getDictName(task.taskNature)}
						</div>
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">任务象限：</label>
						<div class="col-sm-4">
							${elf:getDictName(task.quadrant)}
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">下一步工作计划：</label>
						<div class="col-sm-10">
							${task.nextPlan}
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">计划标准：</label>
						<div class="col-sm-10">
							${task.planStandard}
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label " >计划执行人：</label>
						<div class="col-sm-4">
							${task.planExecutorAll}
						</div>
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">告知执行人方式：</label>
						<div class="col-sm-4">
							${elf:executeWay(task.executeWay)}
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label " >计划反馈时间：</label>
						<div class="col-sm-4">
							<fmt:formatDate value="${task.backTime }" pattern="yyyy-MM-dd" />
						</div>
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">提醒方式：</label>
						<div class="col-sm-4">
							${elf:backWay(task.backWay)}
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">反馈成果：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[maxSize[500]]" id="content" placeholder="请输入反馈成果" name="content" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
					</div>

					<div class="form-group" id="div_batchUploadFile">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">反馈附件/图片：</label>
						<div id="div_file" class="col-sm-10">
							<input id="uploadFile_0" name="uploadFile"  type="file" onchange="fileUpload(this)" />
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 dev-col-sm-120 control-label">小结：</label>
						<div class="col-sm-10">
							<textarea class="form-control validate[maxSize[500]]" id="summary" placeholder="请输入小结" name="summary" style="width: 625px" data-prompt-position="inline"
									  rows=4></textarea>
						</div>
					</div>
				</form>

		</div>
	</div>
	<!--日历插件-->
	<%--<script type="text/javascript" src="../content/js/pickday.js"></script>--%>
<script type="text/javascript">
	//上传名片
	function fileUpload(obj) {
		var $this = $(obj);
		var fileData = $this.val();//取得上传文件的名称
		var fileName = fileData.substr(fileData.lastIndexOf("\\") + 1);
		$("#div_file").append(fileName+"&nbsp;|&nbsp;");
		var id = $this.attr("id");
		var no = parseInt(id.substr(id.lastIndexOf("_")+1,id.length))+1;
		$this.hide();
		$("#div_file").append("<input id=\"uploadFile_"+no+"\" name=\"uploadFile\" type=\"file\" onchange=\"fileUpload(this)\" />");

	}
</script>