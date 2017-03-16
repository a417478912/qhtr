package com.qhtr.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
		// 文件保存目录路径
		static String savePath =  "G:/upload/userAvatar/";//request.getServletContext().getRealPath("/")

		// 文件保存目录URL
		static String saveUrl = "/upload/userAvatar/";
		static String newFileName = "";
		public static String uploadFile(MultipartFile excelFile) throws Exception {
		// 创建文件夹
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}	
			
		if (excelFile != null) {
		System.out.println(excelFile.getName()+"--"+excelFile.getSize());
		String fileName = excelFile.getOriginalFilename(); 
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		SaveFileFromInputStream(excelFile.getInputStream(),savePath,newFileName);//保存到服务器的路径

		}
		return saveUrl+newFileName;
		}

		//将MultipartFile 转换为File
		private static void SaveFileFromInputStream(InputStream stream,String path,String savefile) throws IOException
		   {      
		       FileOutputStream fs=new FileOutputStream( path + "/"+ savefile);
		       System.out.println("------------"+path + "/"+ savefile);
		       byte[] buffer =new byte[1024*1024];
		       int bytesum = 0;
		       int byteread = 0; 
		       while ((byteread=stream.read(buffer))!=-1)
		       {
		          bytesum+=byteread;
		          fs.write(buffer,0,byteread);
		          fs.flush();
		       } 
		       fs.close();
		       stream.close();      
		   }       
}
