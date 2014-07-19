package com.load.app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class downLoadAction extends ActionSupport implements ServletResponseAware {

	private static final long serialVersionUID = -7448748577778248376L;  
//    private HttpServletRequest request;  
    private HttpServletResponse response;  
    private String savePath;
    private String fileName;
    
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setServletResponse(HttpServletResponse resp) {
		// TODO Auto-generated method stub
		this.response=resp; 
	}

//	public void setServletRequest(HttpServletRequest req) {
//		// TODO Auto-generated method stub
//		this.request=req; 
//	}
	
	public String execute() throws Exception {  
        
        String fileName=getFileName(); 
        fileName=new String(fileName.getBytes("iso-8859-1"),"utf-8"); 
//        System.out.println("download==="+fileName);
        String fullPath=getSavePath()+"//"+fileName;  
        
        // 文件下载目录路径  
//        String downloadDir = ServletActionContext.getServletContext().getRealPath("/download");  
        // 文件下载路径  
//        String downloadFile = ServletActionContext.getServletContext().getRealPath(fullPath);  
//        java.io.File file = new java.io.File(downloadFile);  
//        downloadFile = file.getCanonicalPath();// 真实文件路径,去掉里面的..等信息  
//        
//        System.out.println(downloadFile);
        // 发现企图下载不在 /download 下的文件, 就显示空内容  
//        if(!downloadFile.startsWith(downloadDir)) {  
//         return null;  
//        }  
        
        File file = new File(fullPath);
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        int len = 0;
        byte[] buffers = new byte [2048];
        //  清空response 
        response.reset();
//        response.setContentType("text/html;charset=utf-8");  
        response.setContentType( "application/x-msdownload" );
        //  设置response的Header 
        response.addHeader("Content-Disposition", "attachment;filename="+fileName);
//        response.addHeader( "Content-Length" ,  ""+fileName.length());
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        while((len=fis.read(buffers))!=-1){  
            out.write(buffers, 0, len);  
        }
        out.flush();
        out.close();
        return null;  //要return null，return Success到时会出现session的问题
    }
}
