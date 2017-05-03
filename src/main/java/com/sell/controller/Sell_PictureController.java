package com.sell.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Picture;
import com.qhtr.service.PictureService;
import com.qhtr.utils.PictureUploadUtils;

@Controller
@RequestMapping("/sell_picture")
public class Sell_PictureController {
	@Resource
	public PictureService pictureService;
	
	@ResponseBody
	@RequestMapping(value="/upLoad")
	public Json upLoad(Json j,@RequestParam String picture,@RequestParam int type,Integer x,Integer y,Integer width,Integer height) throws Exception{
		String thePath = "";
		if(type == 1){
			thePath = "userAvatar";
		}else if(type == 2){
			thePath = "store";
		}else if(type == 3){
			thePath = "goods";
		}
		String url = PictureUploadUtils.saveFromBase64String(picture,thePath);
		if(url != null){
			Picture pic = new Picture();
			pic.setHeight(height +"");
			pic.setWidth(width+"");
			pic.setUrl(url);
			int result = pictureService.insert(pic);
			if(result == 1){
				j.setData(pic);
			}else{
				j.setCode(0);
				j.setMessage("图片上传失败!");
			}
		}else{
			j.setCode(0);
			j.setMessage("图片上传失败!");
		}
		
		/**
		 * 图片剪切
		 */
		if(x != null){
			String path = PictureUploadUtils.cut(url, x, y, width, height, thePath);
			PictureUploadUtils.deleteFile(url);
			j.setData(path);
		}
		return j;
	}
}
