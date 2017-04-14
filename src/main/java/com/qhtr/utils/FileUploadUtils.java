package com.qhtr.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
		//request.getServletContext().getRealPath("/")
		// 文件保存目录路径 
		//static String baseSavePath =  "G:";   //localhost
		static String baseSavePath =  "/app";   //localhost
		
		// 文件保存目录URL
	    static String baseSaveUrl = "http://114.55.248.53:8080/qhtr"+"/upload/";
	    //static String baseSaveUrl = "localhost:8080"+"/upload/";
		
		/**
		 * 文件上传
		 * @param excelFile
		 * @return
		 * @throws Exception
		 */
		public static String uploadFile(MultipartFile excelFile,String thePath) throws Exception {
		String baseSavePath1 = baseSavePath + "/upload/" + thePath+"/";
		String baseSaveUrl1 = baseSaveUrl + thePath + "/";
		String newFileName = "";
		// 创建文件夹
		File saveDirFile = new File(baseSavePath1);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String savePath = baseSavePath1 + ymd + "/";
		String saveUrl = baseSaveUrl1 + ymd + "/";
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

		/**
		 * 流写入文件
		 * @param stream
		 * @param path
		 * @param savefile
		 * @throws IOException
		 */
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
		
		/**
		 *  base64字符串文件上传
		 * @param string
		 * @return
		 * @throws IOException
		 */
		public static String saveFromBase64String(String baseString,String url) throws IOException{
			String baseSavePath1 = baseSavePath + "/upload/" + url+"/";
			String baseSaveUrl1 = baseSaveUrl + url + "/";
			File saveDirFile = new File(baseSavePath1);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			String savePath = baseSavePath1 + ymd + "/";
			String saveUrl = baseSaveUrl1 + ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}	
			
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + ".png" ;

			 // 将base64 转 字节数组
			Base64 base = new Base64();
            byte[] decode = base.decode(baseString);
            InputStream fin = new ByteArrayInputStream(decode);
            SaveFileFromInputStream(fin, savePath, newFileName);
            return saveUrl+newFileName;
		}
		
		public static boolean deleteFile(String url){
			File file = new File(baseSavePath+subString(url));
			if(file.exists()){
				return file.delete();
			}else{
				return true;
			}
		}
		
		/**
	     * 
	     * 对图片裁剪，并把裁剪完的新图片保存 
		 * @param srcpath 源 路径
		 * @param x 剪切点x y坐标
		 * @param y
		 * @param width 剪切点宽度width   高width
		 * @param height
		 * @param thePath 文件夹名字
		 * @throws IOException
		 */
	    public static String cut(String srcpathOld,int x,int y,int width,int height,String thePath) throws IOException {
	    	String srcpath = subString(srcpathOld);
	        FileInputStream is = null;
	 
	        ImageInputStream iis = null;
	 
	        try {
	 
	            // 读取图片文件
	 
	            is = new FileInputStream(baseSavePath+srcpath);
	 
	            /**
	             * 
	             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
	             * 
	             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
	             * 
	             * (例如 "jpeg" 或 "tiff")等 。
	             * 
	             */
	 
	            Iterator<ImageReader> it = ImageIO
	                    .getImageReadersByFormatName("jpg");
	 
	            ImageReader reader = it.next();
	 
	            // 获取图片流
	 
	            iis = ImageIO.createImageInputStream(is);
	 
	            /**
	             * 
	             * <p>
	             * iis:读取源。true:只向前搜索
	             * </p>
	             * .将它标记为 ‘只向前搜索’。
	             * 
	             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
	             * 
	             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
	             * 
	             */
	 
	            reader.setInput(iis, true);
	 
	            /**
	             * 
	             * <p>
	             * 描述如何对流进行解码的类
	             * <p>
	             * .用于指定如何在输入时从 Java Image I/O
	             * 
	             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
	             * 
	             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
	             * 
	             * ImageReadParam 的实例。
	             * 
	             */
	 
	            ImageReadParam param = reader.getDefaultReadParam();
	 
	            /**
	             * 
	             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
	             * 
	             * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。
	             * 
	             */
	 
	            Rectangle rect = new Rectangle(x, y, width, height);
	 
	            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
	 
	            param.setSourceRegion(rect);
	 
	            /**
	             * 
	             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
	             * 
	             * 它作为一个完整的 BufferedImage 返回。
	             * 
	             */
	 
	            BufferedImage bi = reader.read(0, param);
	 
	            // 保存新图片
	 
	            String baseSavePath1 = baseSavePath + "/upload/" + thePath+"/";
				String baseSaveUrl1 = baseSaveUrl + thePath + "/";
				File saveDirFile = new File(baseSavePath1);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				String savePath = baseSavePath1 + ymd + "/";
				String saveUrl = baseSaveUrl1 + ymd + "/";
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}	
				
				
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + ".png" ;
				
				ImageIO.write(bi, "jpg", new File(savePath + newFileName));
				return saveUrl+newFileName;
	 
	        } finally {
	 
	            if (is != null)
	 
	                is.close();
	 
	            if (iis != null)
	 
	                iis.close();
	 
	        }
	 
	    }
	    
	    /**
	     * url截取
	     */
	    public static String subString(String url){
	    	return url.substring(url.indexOf("/upload/"),url.length());
	    }
}
