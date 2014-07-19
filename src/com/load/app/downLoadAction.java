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
        
        // �ļ�����Ŀ¼·��  
//        String downloadDir = ServletActionContext.getServletContext().getRealPath("/download");  
        // �ļ�����·��  
//        String downloadFile = ServletActionContext.getServletContext().getRealPath(fullPath);  
//        java.io.File file = new java.io.File(downloadFile);  
//        downloadFile = file.getCanonicalPath();// ��ʵ�ļ�·��,ȥ�������..����Ϣ  
//        
//        System.out.println(downloadFile);
        // ������ͼ���ز��� /download �µ��ļ�, ����ʾ������  
//        if(!downloadFile.startsWith(downloadDir)) {  
//         return null;  
//        }  
        
        File file = new File(fullPath);
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        int len = 0;
        byte[] buffers = new byte [2048];
        //  ���response 
        response.reset();
//        response.setContentType("text/html;charset=utf-8");  
        response.setContentType( "application/x-msdownload" );
        //  ����response��Header 
        response.addHeader("Content-Disposition", "attachment;filename="+fileName);
//        response.addHeader( "Content-Length" ,  ""+fileName.length());
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        while((len=fis.read(buffers))!=-1){  
            out.write(buffers, 0, len);  
        }
        out.flush();
        out.close();
        return null;  //Ҫreturn null��return Success��ʱ�����session������
    }
}
