package com.qhtr.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @author Harry
 * @Description 获取图片宽高的工具类
 * @date  2017年6月14日
 */
public class PicUtils {
	
	/**
	 * 获取图片的宽
	 * @param path
	 * @return
	 */
	public static int getImgWidth(String path){
		
		InputStream is = null;
		BufferedImage src = null;
		HttpURLConnection httpUrl = null;  
		int width = 0;
		 try {
			URL url = new URL(path);
			 httpUrl = (HttpURLConnection) url.openConnection();  
			 httpUrl.connect();
			 is = httpUrl.getInputStream();
			 src = ImageIO.read(is);
			 
				 width = src.getWidth(null);
				 return width;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return width;
	}
	
	/**
	 * 获取图片的高
	 * @param path
	 * @return
	 */
	public static int getImgHeight(String path){
		
		InputStream is = null;
		BufferedImage src = null;
		HttpURLConnection httpUrl = null;  
		int height = 0;
		try {
			URL url = new URL(path);
			httpUrl = (HttpURLConnection) url.openConnection();  
			httpUrl.connect();
			is = httpUrl.getInputStream();
			src = ImageIO.read(is);
			height = src.getHeight(null);
			return height;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return height;
	}
	
}
