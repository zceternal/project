<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<link rel="stylesheet" href="../content/css/bootstrap.min.css" />
<link rel="stylesheet" href="../content/css/common.css" />


<link rel="stylesheet" href="../content/css/jquery-ui.css" />
<link rel="stylesheet" href="../content/css/site.css" />
</head>
<script type="text/javascript" src="../content/js/jquery-1.8.3.min.js"></script>
<!-- 上传文件 -->
<script type="text/javascript" src="../content/js/ajaxfileupload.js"></script>

<script type="text/javascript" src="../content/js/jquery.md5.js"></script>
	<script type="text/javascript" src="../content/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../content/js/sk.core.iniect.js"></script>
	<script type="text/javascript" src="../content/js/sk.dialog.iniect.js"></script>

<body>
<div class="col-sm-8 favorite">

								<input id="physicalPath" name="physicalPath"  value=""  type="text" style="width:300px;margin-top:5px">
								<input type="file" id="uploadVistCard" name="uploadVistCard"
									onchange="fileUpload()" />
									<button id=btnSbmit style="margin-left:310px;margin-top:-45px">导入</button>
							</div>
							
	
<script type="text/javascript">
	//上传
	function fileUpload() {
		var fileData = $("#uploadVistCard").val();//取得上传文件的名称
		
		
		$("#path").val(fileData);
		var fileName = fileData.substr(fileData.lastIndexOf("\\") + 1);
		
		var fileType = fileData.substr(fileData.indexOf(".") + 1);//取得上传文件的后缀
		
	 	if (fileType.toLowerCase() != "xls") {
	 		$.sk.alert("只支持.xls文件，请将后缀名改为xls！");
				return;
			} 	
		
		var file = document.getElementById("uploadVistCard").files;
		

		  if (((file[0].size) / 1024.0 / 1024) > 10) {
			  $.sk.error("上传的文件不能大于10M");
			return;
		} 
		 $.ajaxFileUpload({
			url : "../crmupload.do?action=uploadfile",
			secureuri : false, //是否启用安全提交,默认为false 
			fileElementId : 'uploadVistCard', //文件选择框的id属性
			dataType : 'json', //服务器返回的格式,可以是json或xml等
			success : function(data, status) { //服务器响应成功时的处理函数
				if (data.state = "SUCCESS") {
					$("#physicalPath").val(data.physicalPath);
				}
			},
			error : function(data, status, e) { //服务器响应失败时的处理函数
				$.sk.alert("上传失败!");
			}
		}); 
	}
	
	$("#btnSbmit").click(function(){
		var physicalPath= $("#physicalPath").val();
		if(physicalPath==""||physicalPath==null){
			$.sk.alert("请选择文件");
			return;
		}
		$.post("../customerImport/upload_excel",{physicalPath:physicalPath},function(data){
			if (data&&data.success) {
				$.sk.alert("导入成功");
			}else{
				$.sk.error(data.msg);
			}
		})
	})
</script>
</body>
</html>
