package com.load.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.load.util.MySqlDB;
import com.opensymphony.xwork2.ActionSupport;

public class UpLoadAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -687927352861710571L;
	private HttpServletRequest request;
	private List<File> fileName;//这里的"fileName"一定要与表单中的文件域名相同  
//    private List<String> fileNameContentType;//格式同上"fileName"+ContentType  
    private List<String> fileNameFileName;//格式同上"fileName"+FileName  
    private String savePath;//文件上传后保存的路径  
//    private String allowTypes;//允许上传的文件类型  
	
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.request = req;
	}

	public List<File> getFileName() {
		return fileName;
	}

	public void setFileName(List<File> fileName) {
		this.fileName = fileName;
	}

//	public List<String> getFileNameContentType() {
//		return fileNameContentType;
//	}
//
//	public void setFileNameContentType(List<String> fileNameContentType) {
//		this.fileNameContentType = fileNameContentType;
//	}

	public List<String> getFileNameFileName() {
		return fileNameFileName;
	}

	public void setFileNameFileName(List<String> fileNameFileName) {
		this.fileNameFileName = fileNameFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

//	public String getAllowTypes() {
//		return allowTypes;
//	}
//
//	public void setAllowTypes(String allowTypes) {
//		this.allowTypes = allowTypes;
//	}
	
	public String execute() throws Exception {  
        //得到文件的类型  
//        List<String> fileTypes=getFileNameContentType();  
//        String []allowTypes=getAllowTypes().split(",");  
//        List<String> typesList=Arrays.asList(allowTypes);  
          
//        boolean allowFlag=true;//是否存在不允许上传的文件类型  
//        System.out.println(getFileName()+"    *******       ");
        System.out.println(getFileNameFileName()+"    *******       ");
//        for(String type:fileTypes){
//        	System.out.println("文件类型："+type);
//            if(!typesList.contains(type)){  
//                allowFlag=false;  
//                break;  
//            }  
//        }  
//        if(!allowFlag){  
//            request.setAttribute("fileTypeError",   
//                    "您上传的文件中存在不允许上传的类型,允许上传的类型为："+getAllowTypes());  
//            return INPUT;  
//        }
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowtime = sf.format(date);
	    MySqlDB conn = null;
	    PreparedStatement pstmt = null;
        //允许上传  
        File dir=new File(getSavePath());  
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        List<File> files=getFileName();  
        for(int i=0;i<files.size();i++){  
        	System.out.println("存放目录："+getSavePath());
            FileOutputStream fos=new FileOutputStream(getSavePath()+"//"+getFileNameFileName().get(i));  
            FileInputStream fis=new FileInputStream(getFileName().get(i));  
            byte []buffers=new byte[1024];  
            int len=0;  
            while((len=fis.read(buffers))!=-1){  
                fos.write(buffers,0,len);  
            }
            conn = new MySqlDB();
            String sql = "insert into uploadfiles(realfilesname,filesname) values (?,?)";
            pstmt = conn.preparedStatement(sql);
            pstmt.setString(1, getFileNameFileName().get(i));
            pstmt.setString(2, nowtime);
//            pstmt.execute();
        }  
          
//        List<String> fileHrefsList=new ArrayList<String>();  
//        for(String fileName:getFileNameFileName()){  
//            String href="<a href='/' mce_href='/downloadFile.action?fileName='+fileName+'/'>"+fileName+"</a>";  
//            fileHrefsList.add(href);  
//        }
//        System.out.println(fileHrefsList.size());
//        request.setAttribute("fileNameList", fileHrefsList);  
        return SUCCESS;  
    }
}
