package qhtr.test;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PicTest {
	public static void main(String[] args) {
		
		String path = "http://www.7htr.com/qhtr/upload/userAvatar/20170610/20170610185823_378.jpeg";
		InputStream is = null;
		BufferedImage src = null;
		HttpURLConnection httpUrl = null;  
		
		 try {
			 
			 URL url = new URL(path);
			 httpUrl = (HttpURLConnection) url.openConnection();  
			 httpUrl.connect();
			 is = httpUrl.getInputStream();
			 src = javax.imageio.ImageIO.read(is);
			 int width = src.getWidth();
			 int height = src.getHeight();
			 System.out.println("width : " + width);
			 System.out.println("height : " + height);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
/*		  *//**
	     * 获取图片宽度
	     * @param file  图片文件
	     * @return 宽度
	     *//*
	    public static int getImgWidth(File file) {
	        InputStream is = null;
	        BufferedImage src = null;
	        int ret = -1;
	        try {
	            is = new FileInputStream(file);
	            src = javax.imageio.ImageIO.read(is);
	            ret = src.getWidth(null); // 得到源图宽
	            is.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return ret;
	    }
	      
	      
	    *//**
	     * 获取图片高度
	     * @param file  图片文件
	     * @return 高度
	     *//*
	    public static int getImgHeight(File file) {
	        InputStream is = null;
	        BufferedImage src = null;
	        int ret = -1;
	        try {
	            is = new FileInputStream(file);
	            src = javax.imageio.ImageIO.read(is);
	            ret = src.getHeight(null); // 得到源图高
	            is.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return ret;*/ 
	}
	
	public InputStream saveToFile(String destUrl) {  
		
		 FileOutputStream fos = null;  
		 BufferedInputStream bis = null;  
		 HttpURLConnection httpUrl = null;  
		 URL url = null;  
		 int BUFFER_SIZE = 1024;  
		 byte[] buf = new byte[BUFFER_SIZE];  
		 int size = 0;  
		 
		 try {  
			 
		 url = new URL(destUrl);  
		 httpUrl = (HttpURLConnection) url.openConnection();  
		 httpUrl.connect();
		 InputStream is = httpUrl.getInputStream();
		 return is;
		 } catch (Exception e) {  
			 e.printStackTrace();
		 }
		return bis;
		 }
}
