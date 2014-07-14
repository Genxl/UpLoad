<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="ck" uri="http://ckeditor.com"%>  
<%  
    String path = request.getContextPath();    
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">  
<html>  
    <head>  
    <base href="<%=basePath%>">    
        <title>Ckeditor Test</title>  
        </head>  
        <body>     
        <textarea rows="15" cols="80" id="content"></textarea>    
        <ck:replace replace="content" basePath="ckeditor"></ck:replace>    
        <!-- tag标签使用replace或editor的区别：editor是增加，replace是替换原来的      
        <ck:editor editor="content" basePath="ckeditor" ></ck:editor>  
         -->  
        </body>    
</html>  