<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
    String path = request.getContextPath();    
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>UpLoad And DownLoad</title>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>  
<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script> 
<script type="text/javascript" src="uploadify/jquery.uploadify.min.js"></script> 
<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css"> 
<script type="text/javascript">  
	$(function () {  
	    $('#fileName').uploadify({  
	        uploader: '<%=path%>/uploadFile.action',          // 服务器端处理地址  jsessionid=<%=session.getId()%> 解决在FireFox下的session问题
	        swf: 'uploadify/uploadify.swf',    // 上传使用的 Flash  
	        //cancelImg: 'uploadify/uploadify-cancel.png',取消上传的按钮可以在uploadify.css中设置
	        queueID : 'fileQueue',			    // 此处的queueID就是div中的ID
	        width: 80,                          // 按钮的宽度  
	        height: 23,                         // 按钮的高度  
	        buttonText: "上传文件",                 // 按钮上的文字  
	        buttonCursor: 'hand',                // 按钮的鼠标图标  
	        fileObjName: 'fileName',           // 上传参数名称 后台<span style="color:#ff0000;">action里面的属性uploadify</span>  
	        // 两个配套使用  
	        fileTypeExts: "*.jpg;*.png;*.txt;*.pdf",    // 扩展名 (限制上传的文件) 
	        fileTypeDesc: "请选择 jpg png txt 文件",     // 文件说明  
	        auto: false,                // 选择之后，自动开始上传  
	        multi: true,               // 是否支持同时上传多个文件  
	        queueSizeLimit: 5,         // 允许多文件上传的时候，同时上传文件的个数 
	    });
	});  
</script>
</head>
	<body>
		<div id="fileQueue" style=""></div>
		<input type="file" name="fileName" id="fileName" />  
        <a href="javascript:$('#fileName').uploadify('cancel', '*')">清除所有文件</a>  
        <a href="javascript:$('#fileName').uploadify('upload', '*')">上传所有文件</a> 
  	</body>
</html>